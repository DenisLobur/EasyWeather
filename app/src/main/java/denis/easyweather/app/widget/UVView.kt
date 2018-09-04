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
import kotlin.math.cos
import kotlin.math.sin

class UVView : View {
    lateinit var paintGreenCircle: Paint
    lateinit var paintYellowCircle: Paint
    lateinit var paintOrangeCircle: Paint
    lateinit var paintRedCircle: Paint
    lateinit var oval: RectF
    lateinit var paintArrow: Paint
    lateinit var paintPoint: Paint
    var xMiddle = 0F
    var yMiddle = 0F

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
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

        xCoord = 500 * cos(234F)
        yCoord = 500 * sin(234F)

        paintPoint = Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 20F
        }
    }

    var xCoord: Float = 0F
    var yCoord: Float = 0F

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        xMiddle = w/2F
        yMiddle = h/2F
        Log.d(TAG, " onSize x: " + xMiddle + " y: " + yMiddle)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.apply {
            oval.apply {
                left = width/2 - 100F
                top = height/2 - 100F
                right = width/2 + 100F
                bottom = height/2 + 100F
            }

            drawArc(oval, 180F, 54F, false, paintGreenCircle)
            drawArc(oval, 234F, 54F, false, paintYellowCircle)
            drawArc(oval, 288F, 36F, false, paintOrangeCircle)
            drawArc(oval, 324F, 36F, false, paintRedCircle)
//            drawCircle(xMiddle, yMiddle, yMiddle - 20, paintGreenCircle)

            drawLine(0f, 0f, xMiddle, yMiddle, paintArrow)
            drawPoint(xMiddle, yMiddle, paintPoint)



        }
    }

    companion object {
        val TAG = UVView::class.java.simpleName
    }
}