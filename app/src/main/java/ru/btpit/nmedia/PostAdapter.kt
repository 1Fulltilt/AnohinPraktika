package ru.btpit.nmedia

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.btpit.nmedia.databinding.CardPostBinding

typealias  OnLikeListener = (post: Post) -> Unit
class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: OnLikeListener
) : RecyclerView.ViewHolder(binding.root){
    fun bind(post: Post){
        binding.apply {
            textViewHeader.text = post.author
            textViewContent.text = post.content
            textViewDateTime.text = post.published
            textViewLike.text = post.amountlike.toString()
            textViewRepost.text = post.amountrepost.toString()
            imagebutnlike.setImageResource(
                if (post.likedByMe) R.drawable.liked else R.drawable.likes
            )
            imagebutnlike.setOnClickListener{
                onLikeListener(post)
            }
        }
    }
}

class PostAdapter(private val onLikeListener: OnLikeListener): RecyclerView.Adapter<PostViewHolder>(){
    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onLikeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size

}