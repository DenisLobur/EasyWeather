package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import denis.easyweather.app.R


class EditTextWithClean : AppCompatEditText {

    private var cleanButtonRes = R.drawable.ic_close_black

    constructor(context: Context) : super(context) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setup()
    }

    private fun setup() {
        setOnTouchListener(object : View.OnTouchListener {
            internal var handled = false

            override fun onTouch(view: View, event: MotionEvent): Boolean {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (compoundDrawables[DRAWABLE_RIGHT] != null && event.rawX >= right - compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                        setText("")
                        handled = true
                        return true
                    }
                } else if (event.action == MotionEvent.ACTION_UP && handled) {
                    handled = false
                    return true
                }
                return false
            }
        })
        addTextChangedListener(object : AbstractTextWatcher() {
            override fun afterTextChanged(s: Editable) {
                super.afterTextChanged(s)
                if (!TextUtils.isEmpty(s) && isFocused && isEnabled) {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, cleanButtonRes, 0)
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }
        })
    }

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
        if (!focused || !isEnabled) {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        } else {
            if (!TextUtils.isEmpty(text.toString())) {
                setCompoundDrawablesWithIntrinsicBounds(0, 0, cleanButtonRes, 0)
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        if (!enabled) {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        } else {
            if (!TextUtils.isEmpty(text.toString())) {
                setCompoundDrawablesWithIntrinsicBounds(0, 0, cleanButtonRes, 0)
            }
        }
    }

    override fun setError(error: CharSequence?) {
        if (!TextUtils.isEmpty(error)) {
            cleanButtonRes = R.drawable.ic_close_red
        } else {
            cleanButtonRes = R.drawable.ic_close_black
        }
        if (!TextUtils.isEmpty(text.toString()) && isFocused && isEnabled) {
            setCompoundDrawablesWithIntrinsicBounds(0, 0, cleanButtonRes, 0)
        }
    }

    companion object {
        private val DRAWABLE_LEFT = 0
        private val DRAWABLE_TOP = 1
        private val DRAWABLE_RIGHT = 2
        private val DRAWABLE_BOTTOM = 3
    }
}
