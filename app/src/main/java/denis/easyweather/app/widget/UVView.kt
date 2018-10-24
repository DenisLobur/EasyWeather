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
        paintGreenCircle = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        paintYellowCircle = Paint().apply {
            color = Color.YELLOW
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        paintOrangeCircle = Paint().apply {
            color = resources.getColor(R.color.orange)
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        paintRedCircle = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        oval = RectF()

        paintArrow = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.STROKE
            strokeWidth = 5F
        }

        paintPoint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 20F
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        xMiddle = w / 2F
        yMiddle = h / 2F
        xCoord = (xMiddle + 40 * sin(3.14)).toFloat()
        yCoord = (yMiddle + 40 * cos(3.14)).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            oval.apply {
                left = 0f
                top = 0f
                right = width+1f
                bottom = height+1f
            }

            drawArc(oval, 120F, 54F, false, paintGreenCircle)
            drawArc(oval, 234F, 54F, false, paintYellowCircle)
            drawArc(oval, 288F, 36F, false, paintOrangeCircle)
            drawArc(oval, 324F, 36F, false, paintRedCircle)

            val xStop = getEndPointArrow(uvVal).first
            val yStop = getEndPointArrow(uvVal).second

            drawLine(xMiddle, yMiddle, xStop, yStop, paintArrow)
            drawPoint(xMiddle, yMiddle, paintPoint)
        }
    }

    private fun getEndPointArrow(uvValue: Float): Pair<Float, Float> {
        val ex = uvValue * 18 // degrees per one UV value
        val radius = 100

        val theta = (270 - ex) * PI / 180
        val xEnd = (xMiddle + radius * sin(theta)).toFloat()
        val yEnd = (yMiddle + radius * cos(theta)).toFloat()

        return Pair(xEnd, yEnd)
    }

    companion object {
        val TAG = UVView::class.java.simpleName
    }
}