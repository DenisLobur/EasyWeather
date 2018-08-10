package denis.easyweather.app.widget

import android.content.Context
import android.support.annotation.StringRes
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.widget.RelativeLayout
import denis.easyweather.app.R
import kotlinx.android.synthetic.main.view_input_field.view.*

open class InputFieldView : RelativeLayout {

    private var showError: Boolean = false

    constructor(context: Context) : super(context) {
        setup(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup(context, attrs)
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        if (isInEditMode) {
            return
        }
        LayoutInflater.from(context).inflate(R.layout.view_input_field, this, true)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.InputFieldView)
            val n = a.indexCount
            for (i in 0 until n) {
                val attr = a.getIndex(i)
                when (attr) {
                    R.styleable.InputFieldView_hint -> input_field.hint = a.getString(attr)
                //note that you are accessing standart attributes using your attrs identifier
                    R.styleable.InputFieldView_android_inputType -> input_field.inputType = a.getInt(attr, EditorInfo.TYPE_TEXT_VARIATION_NORMAL)
                    R.styleable.InputFieldView_android_imeOptions -> input_field.imeOptions = a.getInt(attr, 0)
                    R.styleable.InputFieldView_android_text -> input_field.setText(a.getString(attr))
                    R.styleable.InputFieldView_android_maxLength -> {
                        val fArray = arrayOfNulls<InputFilter>(1)
                        fArray[0] = InputFilter.LengthFilter(a.getInt(attr, Integer.MAX_VALUE))
                        input_field.filters = fArray
                    }
                    R.styleable.InputFieldView_label -> label!!.text = a.getString(attr)
                    R.styleable.InputFieldView_showLabel -> {
                        if (a.getBoolean(attr, true)) {
                            label!!.visibility = VISIBLE
                        } else {
                            label!!.visibility = GONE
                        }
                    }
                    R.styleable.InputFieldView_showError -> {
                        showError = a.getBoolean(attr, true)
                        if (showError) {
                            error!!.visibility = VISIBLE
                        } else {
                            error!!.visibility = GONE
                        }
                    }
                    R.styleable.InputFieldView_android_ems -> input_field.setEms(a.getInt(attr, 10))
                }
            }
            a.recycle()
        }
    }

    override fun setEnabled(isEnable: Boolean) {
        super.setEnabled(isEnable)
        //Util.enableEditTextEditing(input_field, isEnable)
    }

    fun setError(error: String?) {
        if (!showError) {
            return
        }
        if (error == null) {
            input_field.setBackgroundResource(R.drawable.white_rect_with_gray_border)
            this.error!!.text = ""
            this.error!!.visibility = INVISIBLE
        } else {
            input_field.setBackgroundResource(R.drawable.red_rect_with_gray_border)
            this.error!!.text = error
            this.error!!.visibility = VISIBLE
        }
        input_field.setError(error)
    }

    fun setError(@StringRes error: Int) {
        setError(resources.getString(error))
    }
}
