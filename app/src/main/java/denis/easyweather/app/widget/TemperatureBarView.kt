package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class TemperatureBarView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    private var bluePaint: Paint
    private var redPaint: Paint

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
            drawLine(0F,0F, 10F, 10F, bluePaint)
        }
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