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

class MainActivity2 : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            1,
            "БТПИТ",
            "В Борисоглебском техникуме промышленных и информационных технологий в преддверии Международного дня родного языка советником директора по воспитанию и по взаимодействию с детскими общественными объединениями Алехиной Светланой Вадимовной совместно с преподавателем литературы Винниченко Виктории Витальевной была организована и проведена акция «Путешествие к Лукоморью». Отрывок из поэмы «Руслан и Людмила» Александра Сергеевича Пушкина «У лукоморья дуб зеленый» на родном языке прочитал студент 1 курса специальности «Технология машиностроения» Ризозода Абдулло Киёмидин. Данная акция способствовала развитию интереса к изучению родного языка, сознательного отношения к нему как явлению культуры; осознанию эстетической ценности родного языка.",
            "20 февраля 2024 года 17:39",
            0,
            0,
            false,
            false,
        )

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                textView2.text = post.author
                textView5.text = post.content

                textView4.text = post.amountlike.toString()
                textView6.text = post.amountrepost.toString()

                imageButton2.setBackgroundResource(
                    if(post.likedByMe) R.drawable.liked
                    else R.drawable.likes
                )
                imageButton2.setOnClickListener {
                    viewModel.like()
                }
                imageButton3.setOnClickListener {
                    viewModel.repost()
                }

                }

                /*var imgbtn = findViewById<ImageButton>(R.id.imageButton2)
             var text2 = findViewById<TextView>(R.id.textView4)
             var count = true;
             imgbtn.setOnClickListener {
                 if (count) {
                     text2.setText("1")
                     imgbtn.setBackgroundResource(R.drawable.liked)
                 } else {
                     text2.setText("0")
                     imgbtn.setBackgroundResource(R.drawable.likes)

                 }
                 count = count.not()
             }

     var imagebutt = findViewById<ImageButton>(R.id.imageButtonRepost)
     var text1 = findViewById<TextView>(R.id.textView6)
                 imagebutt.setOnClickListener {
                     present_value_int+=1;
                     text1.setText(likescount(present_value_int));
             */
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

    }
