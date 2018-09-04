package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class UVView : View {
    lateinit var paintGreenCircle: Paint
    lateinit var paintYellowCircle: Paint
    lateinit var paintOrangeCircle: Paint
    lateinit var paintRedCircle: Paint
    lateinit var ovalGreen: RectF
    lateinit var ovalYellow: RectF
    lateinit var ovalOrange: RectF
    lateinit var ovalRed: RectF

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
        paintGreenCircle = Paint()
        paintGreenCircle.color = Color.GREEN
        paintGreenCircle.style = Paint.Style.STROKE
        paintGreenCircle.strokeWidth = 20F

        paintYellowCircle = Paint()
        paintYellowCircle.color = Color.YELLOW
        paintYellowCircle.style = Paint.Style.STROKE
        paintYellowCircle.strokeWidth = 20F

        paintOrangeCircle = Paint()
        paintOrangeCircle.color = Color.CYAN
        paintOrangeCircle.style = Paint.Style.STROKE
        paintOrangeCircle.strokeWidth = 20F

        paintRedCircle = Paint()
        paintRedCircle.color = Color.RED
        paintRedCircle.style = Paint.Style.STROKE
        paintRedCircle.strokeWidth = 20F

        ovalGreen = RectF(0F, 0F, 800F, 800F)
//        ovalYellow = RectF(0F, 0F, 800F, 800F)
//        ovalOrange = RectF(0F, 0F, 800F, 800F)
//        ovalRed = RectF(0F, 0F, 800F, 800F)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawArc(ovalGreen, 180F, 45F, false, paintGreenCircle)
        canvas?.drawArc(ovalGreen, 225F, 45F, false, paintYellowCircle)
        canvas?.drawArc(ovalGreen, 270F, 45F, false, paintOrangeCircle)
        canvas?.drawArc(ovalGreen, 315F, 45F, false, paintRedCircle)
//        canvas?.drawCircle(100F, 100F, 200F, paintCircle)
    }
}