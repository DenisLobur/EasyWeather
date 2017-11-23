package denis.easyweather.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class WindDirectionView : View {
    var paintAuxiliaryLines: Paint? = null
    var paintDirection: Paint? = null
    var paintCircle: Paint? = null
    var linePath: FloatArray = FloatArray(1)
    val startAngle = 90.0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paintAuxiliaryLines = Paint(Paint.ANTI_ALIAS_FLAG)
        paintAuxiliaryLines!!.color = Color.BLUE
        paintAuxiliaryLines!!.strokeWidth = 0.5f
        paintDirection = Paint(Paint.ANTI_ALIAS_FLAG)
        paintDirection!!.color = Color.RED
        paintDirection!!.textSize = 40f
        paintCircle = Paint()
        paintCircle!!.strokeWidth = 2f
        paintCircle!!.color = Color.MAGENTA
        linePath = FloatArray(4)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        width = w
//        height = h
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //        canvas.drawCircle(50, 50, 10, paintAuxiliaryLines);
        //        canvas.drawLine();
        //        Log.d(TAG, "w= " + width + " h= " + height);
        //canvas.drawColor(Color.WHITE);
        val centerX = width.toFloat() / 2
        val centerY = height.toFloat() / 2
        val radianAngle = Math.toRadians(startAngle).toFloat()

        linePath[0] = 0f
        linePath[1] = 0f
        linePath[2] = width.toFloat()
        linePath[3] = height.toFloat()
        canvas.drawLines(linePath!!, paintAuxiliaryLines!!)

        linePath[0] = width.toFloat()
        linePath[1] = 0f
        linePath[2] = 0f
        linePath[3] = height.toFloat()
        canvas.drawLines(linePath!!, paintAuxiliaryLines!!)

        linePath[0] = 0f
        linePath[1] = (height / 2).toFloat()
        linePath[2] = width.toFloat()
        linePath[3] = (height / 2).toFloat()
        canvas.drawLines(linePath!!, paintAuxiliaryLines!!)

        linePath[0] = (width / 2).toFloat()
        linePath[1] = 0f
        linePath[2] = (width / 2).toFloat()
        linePath[3] = height.toFloat()
        canvas.drawLines(linePath!!, paintAuxiliaryLines!!)

        canvas.drawText("NW", 10f, 40f, paintDirection!!)
        canvas.drawText("N", (width / 2 - 15).toFloat(), 40f, paintDirection!!)
        canvas.drawText("NE", (width - 55).toFloat(), 40f, paintDirection!!)
        canvas.drawText("E", (width - 45).toFloat(), (height / 2 + 15).toFloat(), paintDirection!!)
        canvas.drawText("SE", (width - 55).toFloat(), (height - 20).toFloat(), paintDirection!!)
        canvas.drawText("S", (width / 2 - 15).toFloat(), (height - 20).toFloat(), paintDirection!!)
        canvas.drawText("SW", 10f, (height - 20).toFloat(), paintDirection!!)
        canvas.drawText("W", 10f, (height / 2 + 15).toFloat(), paintDirection!!)

        //        float x = centerX + centerX * (float) Math.cos(Math.toRadians(200));
        //        float y = centerY + centerY * (float) Math.sin(Math.toRadians(200));

        //canvas.drawLine(x, y, centerX, centerY, paintDirection);
        val radius = 200
        var counter = 0f
        while (counter < 2 * Math.PI) {
            val x = radius * Math.cos(counter.toDouble()).toFloat() + width / 2
            val y = radius * Math.sin(counter.toDouble()).toFloat() + height / 2
            canvas.drawPoint(x, y, paintCircle!!)
            counter += 0.01f
        }

        canvas.drawPoint(10f, 10f, paintCircle!!)
    }

    companion object {

        private val TAG = WindDirectionView::class.java.name
    }
}
