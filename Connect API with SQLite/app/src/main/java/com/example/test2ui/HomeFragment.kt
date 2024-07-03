package com.example.test2ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test2ui.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var adapter: RecyclerAdapter
    private var progressDialog: ProgressDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbHelper = DatabaseHelper(requireContext())

        progressDialog = ProgressDialog(requireContext())
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)

        // Show progress dialog before making the network request
        progressDialog?.show()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getCommentData()

        retrofitData.enqueue(object : Callback<List<CommentDataClassItem>> {
            override fun onResponse(call: Call<List<CommentDataClassItem>>, response: Response<List<CommentDataClassItem>>) {
                progressDialog?.dismiss() // Dismiss progress dialog once data is fetched
                val commentList = response.body()

                commentList?.forEach { comment ->
                    dbHelper.insertComment(comment)
                }
                // Retrieve and display products from the database
                displayComments()
            }

            override fun onFailure(call: Call<List<CommentDataClassItem>>, t: Throwable) {
                progressDialog?.dismiss() // Dismiss progress dialog on failure
                Log.d("HomeFragment", "onFailure: " + t.message)
            }
        })

        // Set up RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        displayComments()
    }

    private fun displayComments() {
        val storedComments = dbHelper.getAllComments()
        adapter = RecyclerAdapter(storedComments) { type, position ->
            if (type == "delete") {
                val comment = storedComments[position]
                showDeleteConfirmationDialog(comment.id, position, storedComments)
            }
        }
        binding.recyclerView.adapter = adapter
    }

    private fun deleteComment(commentId: Int) {
        dbHelper.deleteComment(commentId)
        updateRecyclerView()
    }

    private fun updateRecyclerView() {
        val updatedComments = dbHelper.getAllComments()
        adapter.updateData(updatedComments)
    }

    private fun showDeleteConfirmationDialog(commentId: Int, position: Int, storedComments: MutableList<CommentDataClassItem>) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete Comment")
        builder.setMessage("Are you sure you want to delete this comment?")
        builder.setPositiveButton("Yes") { dialog, _ ->
            deleteComment(commentId)
            storedComments.removeAt(position)
            adapter.notifyItemRemoved(position)
            dialog.dismiss()
            Toast.makeText(requireContext() , "Comment Deleted" , Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}
