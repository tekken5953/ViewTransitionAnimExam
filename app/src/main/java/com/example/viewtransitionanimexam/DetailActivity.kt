package com.example.viewtransitionanimexam

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.*
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.sharedElementEnterTransition = TransitionSet().apply {
            interpolator = OvershootInterpolator(0.7f)
            ordering = TransitionSet.ORDERING_TOGETHER
            addTransition(ChangeBounds().apply{
                pathMotion = ArcMotion()
            })
            duration = 2000
            addTransition(ChangeTransform())
            addTransition(ChangeClipBounds())
            addTransition(ChangeImageTransform())
        }

        val title: TextView = findViewById(R.id.titleTextDetail)
        val content: TextView = findViewById(R.id.contentTextDetail)
        val img: ImageView = findViewById(R.id.imageViewDetail)

        title.text = intent.extras?.getString("title")
        content.text = intent.extras?.getString("content")
        img.setImageDrawable(getImage())
    }

    private fun getImage() : Drawable {
        val arr = intent.getByteArrayExtra("image")
        return BitmapFactory.decodeByteArray(arr, 0, arr!!.size).toDrawable(resources)
    }
}