package ru.btpit.nmedia

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import ru.btpit.nmedia.databinding.ActivityMain2Binding
import ru.btpit.nmedia.databinding.ActivityMainBinding
import ru.btpit.nmedia.databinding.CardPostBinding
class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter {
            viewModel.like(it.id)
        }
        binding.listItem.adapter = adapter
        viewModel.data.observe(this) {Post ->
            adapter.list = Post
        }

    }
}




        fun likescount(count: Int): String {
            return when (count) {
                in 0..999 -> count.toString()
                in 1000..10000000 -> ((count / 100).toFloat() / 10).toString() + "К"
                in 1000000..1000000000 -> ((count / 100000).toFloat() / 10).toString() + "М"
                else -> "Больше млрд"
            }
        }

