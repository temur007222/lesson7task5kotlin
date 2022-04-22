package com.example.lesson7task5kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val span = "I #would like to do #something similar to the twitter app"

        setTags(findViewById(R.id.text), span)
    }

    private fun setTags(pTextView: TextView, pTagString: String) {
        val string = SpannableString(pTagString)
        var start = -1
        var i = 0
        while (i < pTagString.length) {
            if (pTagString[i] == '#') {
                start = i
            } else if (pTagString[i] == ' ' ||  pTagString[i] == '\n' &&  i == pTagString.length - 1 && start != -1) {
                if (start != -1) {
                    if (i == pTagString.length - 1) {
                        i++ // case for if hash is last word and there is no
                        // space after word
                    }
                    val tag = pTagString.substring(start, i)
                    string.setSpan(object : ClickableSpan() {

                        override fun updateDrawState(ds: TextPaint) {
                            // link color
                            ds.color = Color.parseColor("#33b5e5")
                            ds.isUnderlineText = false
                        }

                        override fun onClick(p0: View) {}

                    }, start, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = -1
                }
            }
            i++
        }
        pTextView.movementMethod = LinkMovementMethod.getInstance()
        pTextView.text = string
    }
}