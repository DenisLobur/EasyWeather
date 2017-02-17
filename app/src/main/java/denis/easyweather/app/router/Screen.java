package denis.easyweather.app.router;

import android.os.Bundle;

public class Screen {

    public final Type type;
    public final Bundle arguments;

    public Screen(Type type, Bundle arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    public static Screen fromType(Type type) {
        return new Screen(type, new Bundle());
    }

    public enum Type {
        MAIN,
        DETAIL
    }
}
