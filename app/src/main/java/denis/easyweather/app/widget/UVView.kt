package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import denis.easyweather.app.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class UVView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {
    private lateinit var paintGreenCircle: Paint
    private lateinit var paintYellowCircle: Paint
    private lateinit var paintOrangeCircle: Paint
    private lateinit var paintRedCircle: Paint
    private lateinit var oval: RectF
    private lateinit var paintArrow: Paint
    private lateinit var paintPoint: Paint
    private var xMiddle = 0F
    private var yMiddle = 0F
    private var xCoord: Float = 0F
    private var yCoord: Float = 0F
    private var uvVal: Float = 0F

//    constructor(context: Context?) : super(context) {
//        init()
//    }
//
//    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
//        init()
//    }
//
//    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
//        init()
//    }

    fun setUv(uv: Float) {
        uvVal = uv
        invalidate()
        requestLayout()
    }

    init {
        paintGreenCircle = Paint().apply {
            color = Color.GREEN
            style = Paint.Style.STROKE
            strokeWidth = 60F
        }


        paintYellowCircle = Paint()
        paintYellowCircle.color = Color.YELLOW
        paintYellowCircle.style = Paint.Style.STROKE
        paintYellowCircle.strokeWidth = 60F

        paintOrangeCircle = Paint()
        paintOrangeCircle.color = resources.getColor(R.color.orange)
        paintOrangeCircle.style = Paint.Style.STROKE
        paintOrangeCircle.strokeWidth = 60F

        paintRedCircle = Paint()
        paintRedCircle.color = Color.RED
        paintRedCircle.style = Paint.Style.STROKE
        paintRedCircle.strokeWidth = 60F

        oval = RectF()
        Log.d(TAG, " init x: " + xMiddle + " y: " + yMiddle)

        paintArrow = Paint()
        paintArrow.color = Color.BLUE
        paintArrow.style = Paint.Style.STROKE
        paintArrow.strokeWidth = 5F

//        xCoord = (xMiddle + 40 * sin(1.57)).toFloat()
//        yCoord = (yMiddle + 40 * cos(1.57)).toFloat()

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
        Log.d(TAG, " onSize x: " + xMiddle + " y: " + yMiddle)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            oval.apply {
                left = width / 2 - 200F
                top = height / 2 - 200F
                right = width / 2 + 200F
                bottom = height / 2 + 200F
            }

            drawArc(oval, 180F, 54F, false, paintGreenCircle)
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

        val theta = (270 - ex) * PI/180
        val xEnd = (xMiddle + radius * sin(theta)).toFloat()
        val yEnd = (yMiddle + radius * cos(theta)).toFloat()

        return Pair(xEnd, yEnd)
    }

    companion object {
        val TAG = UVView::class.java.simpleName
    }
}