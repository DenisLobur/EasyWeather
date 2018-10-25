package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import denis.easyweather.app.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class UVView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {

    private var paintGreenCircle: Paint
    private var paintYellowCircle: Paint
    private var paintOrangeCircle: Paint
    private var paintRedCircle: Paint
    private var oval: RectF
    private var paintArrow: Paint
    private var paintPoint: Paint
    private var xMiddle = 0F
    private var yMiddle = 0F
    private var xCoord: Float = 0F
    private var yCoord: Float = 0F
    private var uvVal: Float = 0F

    fun setUv(uv: Float) {
        uvVal = uv
        invalidate()
        requestLayout()
    }

    init {
        paintGreenCircle = initPaint(Color.GREEN, 15F)
        paintYellowCircle = initPaint(Color.YELLOW, 15F)
        paintOrangeCircle = initPaint(resources.getColor(R.color.orange), 15F)
        paintRedCircle = initPaint(Color.RED, 15F)

        oval = RectF()

        paintArrow = initPaint(Color.BLUE, 5F)
        paintPoint = initPaint(Color.RED, 20F)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        xMiddle = w / 2F
        yMiddle = h / 2F
        xCoord = (xMiddle + 40 * sin(Math.PI)).toFloat()
        yCoord = (yMiddle + 40 * cos(Math.PI)).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            oval.apply {
                left = 5f
                top = 5f
                right = width - 5f
                bottom = height - 5f
            }

            drawArc(oval, 120F, 90F, false, paintGreenCircle)
            drawArc(oval, 210F, 90F, false, paintYellowCircle)
            drawArc(oval, 300F, 60F, false, paintOrangeCircle)
            drawArc(oval, 360F, 60F, false, paintRedCircle)

            val xStop = getEndPointArrow(uvVal).first
            val yStop = getEndPointArrow(uvVal).second

            drawLine(xMiddle, yMiddle, xStop, yStop, paintArrow)
            drawPoint(xMiddle, yMiddle, paintPoint)
        }
    }

    private fun getEndPointArrow(uvValue: Float): Pair<Float, Float> {
        val ex = uvValue * 30 // degrees per one UV value
        val radius = 100

        val theta = (330 - ex) * PI / 180
        val xEnd = (xMiddle + radius * sin(theta)).toFloat()
        val yEnd = (yMiddle + radius * cos(theta)).toFloat()

        return Pair(xEnd, yEnd)
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
        val TAG = UVView::class.java.simpleName
    }
}