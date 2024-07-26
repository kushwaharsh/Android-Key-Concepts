package com.example.chatapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapplication.databinding.ItemMessageRecievedBinding
import com.example.chatapplication.databinding.ItemMessageSentBinding

class ChatBoxAdapter(private val messageList: List<Message>, private val nickname: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_SENT = 1
        const val VIEW_TYPE_RECEIVED = 2
    }

    // ViewHolder for sent messages
    inner class SentViewHolder(val binding: ItemMessageSentBinding) : RecyclerView.ViewHolder(binding.root)

    // ViewHolder for received messages
    inner class ReceivedViewHolder(val binding: ItemMessageRecievedBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SENT -> {
                val binding = ItemMessageSentBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                SentViewHolder(binding)
            }
            VIEW_TYPE_RECEIVED -> {
                val binding = ItemMessageRecievedBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ReceivedViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]

        when (holder) {
            is SentViewHolder -> {
                holder.binding.senderNickname.text = message.nickname
                holder.binding.senderMessage.text = message.message
            }
            is ReceivedViewHolder -> {
                holder.binding.recieverNickname.text = message.nickname
                holder.binding.recieverMessage.text = message.message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return if (message.nickname.equals(nickname)) VIEW_TYPE_SENT else VIEW_TYPE_RECEIVED
    }

    override fun getItemCount(): Int = messageList.size
}
