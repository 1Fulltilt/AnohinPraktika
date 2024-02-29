package ru.btpit.nmedia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.activity.viewModels
import ru.btpit.nmedia.databinding.ActivityMain2Binding
import ru.btpit.nmedia.databinding.CardPostBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


class MainActivity2 : AppCompatActivity(),PostAdapter.Listener {
    val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = PostAdapter (this)
        binding.listItem.adapter = adapter
        viewModel.data.observe(this) {Post ->
            adapter.submitList(Post)
        }

    }

    override fun onClickLike(post: Post) {
        viewModel.like(post.id)
    }

    override fun onClickShare(post: Post) {
        viewModel.shareById(post.id)

    }

    override fun onClickMore(post: Post, view: View, binding: CardPostBinding) {
        val popupMenu = PopupMenu(this,view)
        popupMenu.inflate(R.menu.menu_post)
        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.remov -> viewModel.removeDyId(post.id)
            }
            true
        }
        popupMenu.show()

    }

    override fun cancelEditPost(post: Post, binding: CardPostBinding) {

    }

    override fun saveEditPost(post: Post, binding: CardPostBinding) {

    }

    override fun editModeOn(binding: CardPostBinding, content: String) {

    }
}






