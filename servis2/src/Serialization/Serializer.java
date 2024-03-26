package Serialization;

import com.google.gson.Gson;

public class Serializer {
    public static String serialize(Quote q){
        Gson g = new Gson();
        return g.toJson(q,Quote.class);
    }
}
