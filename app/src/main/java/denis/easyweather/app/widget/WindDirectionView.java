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

    private Paint paint;

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
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(50, 50, 50, paint);
    }
}
