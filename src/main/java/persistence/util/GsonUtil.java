package persistence.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static Gson gson = null;
    
    public static Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().serializeNulls().create();
        return gson;
    }
}
