package com.example.test2ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test2ui.databinding.EachItemLayoutBinding

class RecyclerAdapter(
    private var commentList: MutableList<CommentDataClassItem>,
    private val onItemClick: (type: String, pos: Int) -> Unit
) : RecyclerView.Adapter<RecyclerAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val binding = EachItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentList[position]
        holder.bind(comment)
        holder.binding.root.setOnClickListener {
            onItemClick("", position)
        }

        holder.binding.deleteComment.setOnClickListener {
            onItemClick("delete", position)
        }
    }

    override fun getItemCount(): Int = commentList.size

    fun updateData(newList: List<CommentDataClassItem>) {
        commentList.clear()
        commentList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(val binding: EachItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CommentDataClassItem) {
            binding.userName.text = comment.name
            binding.userEmail.text = comment.email
            binding.userComment.text = comment.body
            binding.userId.text = comment.id.toString()
        }
    }
}
