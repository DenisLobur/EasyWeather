package denis.easyweather.app.router;

import android.os.Bundle;

/**
 * Created by denis on 9/19/16.
 */

public class ViewPort {

    public final Type type;
    public final Bundle arguments;

    public ViewPort(Type type, Bundle arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    public static ViewPort fromType(Type type) {
        return new ViewPort(type, new Bundle());
    }

    public enum Type {
        MAIN, DETAIL
    }
}
