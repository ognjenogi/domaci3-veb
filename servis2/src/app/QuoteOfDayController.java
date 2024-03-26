package app;

import Serialization.Quote;
import Serialization.Serializer;
import http.Request;
import http.Server;
import http.response.HtmlResponse;
import http.response.Response;

public class QuoteOfDayController extends Controller {

    public QuoteOfDayController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        Quote quoteOfTheDay = fetchQuoteOfDay();

        String htmlResponse = generateHtmlResponse(quoteOfTheDay);

        return new HtmlResponse(htmlResponse);
    }

    @Override
    public Response doPost() {
        return null;
    }

    private Quote fetchQuoteOfDay() {
        return Server.getQuote();
    }

    private String generateHtmlResponse(Quote quoteOfTheDay) {
        return Serializer.serialize(quoteOfTheDay);
    }
}