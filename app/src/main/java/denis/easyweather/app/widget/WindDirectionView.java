package denis.easyweather.app.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Denis on 07-Feb-17.
 */

public class WindDirectionView extends View {

    private static final String TAG = WindDirectionView.class.getName();
    private Paint paintAuxiliaryLines;
    private Paint paintDirection;
    private int width;
    private int height;
    private float[] linePath;
    private double startAngle = 90.0;

    public WindDirectionView(Context context) {
        super(context);
        init();
    }

    public WindDirectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WindDirectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paintAuxiliaryLines = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintAuxiliaryLines.setColor(Color.BLUE);
        paintAuxiliaryLines.setStrokeWidth(0.5F);
        paintDirection = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDirection.setColor(Color.RED);
        paintDirection.setTextSize(40F);
        linePath = new float[4];
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(50, 50, 10, paintAuxiliaryLines);
//        canvas.drawLine();
//        Log.d(TAG, "w= " + width + " h= " + height);
        //canvas.drawColor(Color.WHITE);
        float centerX = (float) width / 2;
        float centerY = (float) height / 2;
        float radianAngle = (float) Math.toRadians(startAngle);

        linePath[0] = 0f;
        linePath[1] = 0f;
        linePath[2] = width;
        linePath[3] = height;
        canvas.drawLines(linePath, paintAuxiliaryLines);

        linePath[0] = width;
        linePath[1] = 0;
        linePath[2] = 0;
        linePath[3] = height;
        canvas.drawLines(linePath, paintAuxiliaryLines);

        linePath[0] = 0;
        linePath[1] = height / 2;
        linePath[2] = width;
        linePath[3] = height / 2;
        canvas.drawLines(linePath, paintAuxiliaryLines);

        linePath[0] = width / 2;
        linePath[1] = 0;
        linePath[2] = width / 2;
        linePath[3] = height;
        canvas.drawLines(linePath, paintAuxiliaryLines);

        canvas.drawText("NW", 10, 40, paintDirection);
        canvas.drawText("N", width / 2 - 15, 40, paintDirection);
        canvas.drawText("NE", width - 55, 40, paintDirection);
        canvas.drawText("E", width - 45, height / 2 + 15, paintDirection);
        canvas.drawText("SE", width - 55, height - 20, paintDirection);
        canvas.drawText("S", width / 2 - 15, height - 20, paintDirection);
        canvas.drawText("SW", 10, height - 20, paintDirection);
        canvas.drawText("W", 10, height / 2 + 15, paintDirection);

        float x = centerX + centerX * (float) Math.cos(Math.toRadians(200));
        float y = centerY + centerY * (float) Math.sin(Math.toRadians(200));

        canvas.drawLine(x, y, centerX, centerY, paintDirection);
    }
}
