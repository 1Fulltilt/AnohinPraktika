package ru.btpit.nmedia

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.btpit.nmedia.databinding.CardPostBinding
import com.google.android.material.snackbar.Snackbar


class PostDiffCallback : DiffUtil.ItemCallback<Post>(){
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return  oldItem == newItem
    }

}
class PostViewHolder(private val binding: CardPostBinding)
    :RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post, listener: PostAdapter.Listener) {
        binding.apply {
            textViewHeader.text = post.author
            textViewContent.text = post.content
            textViewDateTime.text = post.published
            textViewLike.text = post.amountlike.toString()
            textViewRepost.text = post.amountrepost.toString()
            imagebutnlike.setImageResource(
                if (post.likedByMe) R.drawable.liked else R.drawable.likes
            )
            imagebutnlike.setOnClickListener {
                listener.onClickLike(post)
            }
            imagebutnRepost.setOnClickListener {
                listener.onClickShare(post)
            }
            imageViewmenu.setOnClickListener{
                listener.onClickMore(post,it,binding)
            }
        }
    }
}


class PostAdapter(
    private val listener: Listener,
):ListAdapter<Post, PostViewHolder>(PostDiffCallback()) {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PostViewHolder, position:Int){
        val post = getItem(position)
        holder.bind(post, listener)
    }

    interface Listener{
        fun onClickLike(post: Post)
        fun onClickShare(post: Post)
        fun onClickMore(post:Post, view: View,binding: CardPostBinding)
        fun cancelEditPost(post:Post,binding: CardPostBinding)
        fun saveEditPost(post:Post, binding: CardPostBinding)
        fun editModeOn(binding: CardPostBinding,content:String)
    }
}
private fun convertToString(count:Int):String{
    return when(count){
        in 0..<1_000 -> count.toString()
        in 1000..<1_100-> "1K"
        in 1_100..<10_000 -> ((count/100).toFloat()/10).toString() + "K"
        in 10_000..<1_000_000 -> (count/1_000).toString() + "K"
        in 1_000_000..<1_100_000 -> "1M"
        in 1_100_000..<10_000_000 -> ((count/100_000).toFloat()/10).toString() + "M"
        in 10_000_000..<1_000_000_000 -> (count/1_000_000).toString() + "M"
        else -> "ꚙ"
    }
}
