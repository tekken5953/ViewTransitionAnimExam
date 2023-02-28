package com.example.viewtransitionanimexam

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Pair
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val title: TextView = findViewById(R.id.titleTextMain)
        val img: ImageView = findViewById(R.id.imageViewMain)
        val content: TextView = findViewById(R.id.contentTextMain)

        val btn: Button = findViewById(R.id.btnMain)
        btn.setOnClickListener {
            val options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(title, "titleTran"),
                Pair.create(img, "imageTran"),
                Pair.create(content, "contentTran")
            )

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("title", title.text.toString())
            intent.putExtra("image", sendImage(android.R.mipmap.sym_def_app_icon))
            intent.putExtra("content", content.text.toString())
            startActivity(intent, options.toBundle())
        }
    }

    private fun sendImage(image: Int) : ByteArray {
        val sendBitmap = BitmapFactory.decodeResource(resources, image)
        val stream = ByteArrayOutputStream()
        sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }
}