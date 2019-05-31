package com.example.kotlindrawing

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.Button

class MainActivity : AppCompatActivity() {

    //    var dv: DrawView? = null
//    val clearBtn: Button? = null
//    var tflite: KanjiClassifier? = null

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dv = findViewById<DrawView>(R.id.dv_canvas)
        val clearBtn = findViewById<Button>(R.id.bt_clear_canvas)
        val predictBtn = findViewById<Button>(R.id.bt_predict)

        clearBtn.setOnClickListener {
            dv.clearCanvas()
        }

        predictBtn.setOnClickListener {
            predictCanvas()
        }
//        tflite = KanjiClassifier(this)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    fun predictCanvas() {
//        tflite!!.recognizeImage(null)
    }
}