package serialization;

import com.google.gson.Gson;

public class Serializer {

    public static Quote getQuote(String json) {

        Gson gson = new Gson();
        Quote quote = gson.fromJson(json, Quote.class);

        return quote;

    }

}
