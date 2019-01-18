package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import denis.easyweather.app.R

class TemperatureBarView : View {

    private var bluePaint: Paint
    private var redPaint: Paint
    private var barLength: Int = 0

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
        if (attrs != null) {
            val attributes = context.obtainStyledAttributes(attrs, R.styleable.TemperatureBarViewStyle)
            barLength = attributes.getInt(R.styleable.TemperatureBarViewStyle_barValue, 0)
            attributes.recycle()
        }
    }

    init {
        bluePaint = initPaint(Color.BLUE, 40F)
        redPaint = initPaint(Color.RED, 40F)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            val tempPaint = if(barLength > 0) redPaint else bluePaint
            drawLine(0F, height/2.toFloat(), Math.abs(barLength.toFloat()), height/2.toFloat(), tempPaint)

        }
    }

    fun setBarValue(value: Int){
        barLength = value
    }

    private fun initPaint(clr: Int, strWidth: Float): Paint {
        val paint = Paint()
        paint.apply {
            color = clr
            style = Paint.Style.STROKE
            strokeWidth = strWidth
        }
        return paint
    }

    companion object {
        val TAG = TemperatureBarView::class.java.simpleName
    }
}