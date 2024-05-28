package com.dicoding.picodiploma.loginwithanimation.customButton

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class MyPasswordEditText : AppCompatEditText {
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
        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(p0.length < 8){
                    setError("Password tidak boleh kurang dari 8")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }
}

//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.drawable.Drawable
//import android.provider.Settings.System.getString
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//import androidx.appcompat.widget.AppCompatEditText
//import androidx.core.content.ContextCompat
//import com.dicoding.picodiploma.loginwithanimation.R
//import com.google.android.material.textfield.TextInputLayout
//
//class MyEditText @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null
//) : AppCompatEditText(context, attrs), View.OnTouchListener {
//
//    private var clearButtonImage: Drawable
//    private var passwordEditText = findViewById<MyEditText>(R.id.passwordEditText)
//    private var myButton = findViewById<MyButton>(R.id.signupButton)
//
//    init {
//        // Menginisialisasi gambar clear button
//        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close_black_24dp) as Drawable
//
//        // Menambahkan aksi kepada clear button
//        setOnTouchListener(this)
//
//        // Menambahkan aksi ketika ada perubahan text akan memunculkan clear button
//        addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                setMyButtonEnable()
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//        })
//
//        addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
//                // Do nothing.
//            }
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (s.toString().isNotEmpty()) {
//                    showClearButton()
//                    if (s.toString().length < 8) {
//                        passwordEditText.error = "Password tidak boleh kurang dari 8 karakter"
//                    } else {
//                        passwordEditText.error = null
//                    }
//                } else {
//                    hideClearButton()
//                }
//            }
//
//            override fun afterTextChanged(s: Editable) {
//                // Do nothing.
//            }
//        })
//    }
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//        // Menambahkan hint pada editText
//        hint = "Masukkan nama Anda"
//
//        // Menambahkan text aligmnet pada editText
//        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
//    }
//
//    // Menampilkan clear button
//    private fun showClearButton() {
//        setButtonDrawables(endOfTheText = clearButtonImage)
//    }
//
//    // Menghilangkan clear button
//    private fun hideClearButton() {
//        setButtonDrawables()
//    }
//
//    //Mengkonfigurasi button
//    private fun setButtonDrawables(startOfTheText: Drawable? = null, topOfTheText:Drawable? = null, endOfTheText:Drawable? = null, bottomOfTheText: Drawable? = null){
//        // Sets the Drawables (if any) to appear to the left of,
//        // above, to the right of, and below the text.
//        setCompoundDrawablesWithIntrinsicBounds(startOfTheText, topOfTheText, endOfTheText, bottomOfTheText)
//    }
//
//    override fun onTouch(v: View?, event: MotionEvent): Boolean {
//        if (compoundDrawables[2] != null) {
//            val clearButtonStart: Float
//            val clearButtonEnd: Float
//            var isClearButtonClicked = false
//
//            if (layoutDirection == View.LAYOUT_DIRECTION_RTL) {
//                clearButtonEnd = (clearButtonImage.intrinsicWidth + paddingStart).toFloat()
//                when {
//                    event.x < clearButtonEnd -> isClearButtonClicked = true
//                }
//            } else {
//                clearButtonStart = (width - paddingEnd - clearButtonImage.intrinsicWidth).toFloat()
//                when {
//                    event.x > clearButtonStart -> isClearButtonClicked = true
//                }
//            }
//            if (isClearButtonClicked) {
//                when (event.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close_black_24dp) as Drawable
//                        showClearButton()
//                        return true
//                    }
//                    MotionEvent.ACTION_UP -> {
//                        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_close_black_24dp) as Drawable
//                        when {
//                            text != null -> text?.clear()
//                        }
//                        hideClearButton()
//                        return true
//                    }
//                    else -> return false
//                }
//            } else return false
//        }
//        return false
//    }
//    private fun setMyButtonEnable() {
//        val result = passwordEditText.text
//        myButton.isEnabled = result != null && result.toString().isNotEmpty()
//    }
//}