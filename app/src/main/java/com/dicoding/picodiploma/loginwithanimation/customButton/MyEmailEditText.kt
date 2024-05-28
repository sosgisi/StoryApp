package com.dicoding.picodiploma.loginwithanimation.customButton

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import java.util.regex.Pattern

class MyEmailEditText : AppCompatEditText {
    constructor(context: Context?) : super(context!!) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context!!, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(p0.length < 8){
                    setError("Email tidak valid")
                }
//                checkEmail(p0)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

//    private fun checkEmail(text: CharSequence?){
//        if(text == null || text.isEmpty()){
//            error = null
//            return
//        }
//
//        val emailPatterns = "[a-zA-Z0-9._-]+[a-z]+\\.+[a-z]+"
//        val patterns = Pattern.compile(emailPatterns)
//        val match = patterns.matcher(text)
//
//        if(!match.matches()){
//            setError("Email tidak valid", null)
//        }else{
//            error = null
//        }
//    }
}