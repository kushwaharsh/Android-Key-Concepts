package com.example.chatapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapplication.databinding.ActivityChatBoxBinding
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException

class ChatBoxActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBoxBinding
    private lateinit var socket: Socket
    private lateinit var chatBoxAdapter: ChatBoxAdapter
    private val messageList = mutableListOf<Message>()
    private lateinit var nickname: String

    companion object {
        private const val TAG = "ChatBoxActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBoxBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve nickname from intent extras
        nickname = intent.getStringExtra(MainActivity.NICKNAME) ?: ""

        // Initialize Socket.IO connection
        try {
            socket = IO.socket("http://192.168.3.177:3000") // Use the appropriate IP address or domain
            socket.connect()

            socket.on(Socket.EVENT_CONNECT) {
                Log.d(TAG, "Socket connected")
                socket.emit("join", nickname)
                Log.d(TAG, "Join message sent with nickname: $nickname")
            }

            socket.on(Socket.EVENT_DISCONNECT) {
                Log.d(TAG, "Socket disconnected")
            }

            socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                runOnUiThread {
                    Log.e(TAG, "Socket connection error: ${args[0]}")
                    Toast.makeText(this, "Connection error: ${args[0]}", Toast.LENGTH_LONG).show()
                }
            }

        } catch (e: URISyntaxException) {
            e.printStackTrace()
            Log.e(TAG, "URISyntaxException: ${e.message}")
            Toast.makeText(this, "Invalid server URL", Toast.LENGTH_LONG).show()
        }

        // Set up RecyclerView
        setupRecyclerView()

        // Handle send button click
        binding.send.setOnClickListener {
            val message = binding.message.text.toString()
            if (message.isNotEmpty()) {
                // Add the sent message to the list
                val messageItem = Message(nickname, message, isSent = true)
                /*messageList.add(messageItem)
                chatBoxAdapter.notifyItemInserted(messageList.size - 1)*/

                // Send the message to the server
                socket.emit("messagedetection", nickname, message)
                binding.message.text.clear()
            }
        }

        // Handle incoming messages
        socket.on("userjoinedthechat", Emitter.Listener { args ->
            runOnUiThread {
                val data = args[0] as String
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
        })

        socket.on("userdisconnect", Emitter.Listener { args ->
            runOnUiThread {
                val data = args[0] as String
                Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
            }
        })

        socket.on("message", Emitter.Listener { args ->
            runOnUiThread {
                val data = args[0] as JSONObject
                try {
                    Log.e("Socket" , Gson().toJson(args[0]))
                    val senderNickname = data.getString("senderNickname")
                    val message = data.getString("message")
                    // Assume received messages are not sent by the local user
                    val messageItem = Message(senderNickname, message, isSent = false)
                    messageList.add(messageItem)
                    chatBoxAdapter.notifyItemInserted(messageList.size - 1)
                    binding.messagelist.smoothScrollToPosition(messageList.size-1)

                } catch (e: JSONException) {
                    e.printStackTrace()
                    Log.e(TAG, "JSONException: ${e.message}")
                }
            }
        })
    }

    private fun setupRecyclerView() {
        chatBoxAdapter = ChatBoxAdapter(messageList, nickname)
        binding.messagelist.layoutManager = LinearLayoutManager(this)
        binding.messagelist.adapter = chatBoxAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
    }
}
