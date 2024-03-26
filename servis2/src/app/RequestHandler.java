package app;

import http.HttpMethod;
import http.Request;
import http.response.Response;

public class RequestHandler {
    public Response handle(Request request) throws Exception {
        if (request.getPath().equals("/quote-of-day") && request.getHttpMethod().equals(HttpMethod.GET)) {
            return (new QuoteOfDayController(request)).doGet();
        }

        throw new Exception("Page: " + request.getPath() + ". Method: " + request.getHttpMethod() + " not found!");
    }
}
