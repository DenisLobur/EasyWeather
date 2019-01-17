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
    private var positiveBarLength: Double = 0.0

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
            positiveBarLength = attributes.getFloat(R.styleable.TemperatureBarViewStyle_barValue, 0F).toDouble()
            attributes.recycle()
        }
    }

    init {
        bluePaint = initPaint(Color.BLUE, 20F)
        redPaint = initPaint(Color.RED, 20F)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            drawLine(0F, 0F, positiveBarLength.toFloat(), positiveBarLength.toFloat(), bluePaint)
        }
    }

    fun setBarValue(value: Double){
        positiveBarLength = value
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