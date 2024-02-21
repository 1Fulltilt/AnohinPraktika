package ru.btpit.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    var present_value_int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var imgbtn = findViewById<ImageButton>(R.id.imageButton2)
        var text2 = findViewById<TextView>(R.id.textView4)
        var count = true;
        imgbtn.setOnClickListener {
            if (count)
            {
                text2.setText("1")
                imgbtn.setBackgroundResource(R.drawable.liked)
            }
            else
            {
                text2.setText("0")
                imgbtn.setBackgroundResource(R.drawable.likes)

            }
            count = count.not()

var text1 = findViewById<TextView>(R.id.textView6)
            var imgbtn1 = findViewById<ImageButton>(R.id.imageButton)
            imgbtn1.setOnClickListener {
                present_value_int+=1;
                text1.setText(likescount(present_value_int));
            }
        }
    }
    fun likescount(count:Int):String
    {
        return when(count){
            in 0..999 -> count.toString()
            in 1000.. 10000000 ->((count/100).toFloat()/10).toString() + "К"
            in 1000000.. 1000000000 -> ((count/100000).toFloat()/10).toString() + "М"
            else -> "Больше млрд"
        }
    }

}