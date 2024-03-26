package app;

import http.Server;

import http.Request;
import http.response.HtmlResponse;
import http.response.RedirectResponse;
import http.response.Response;
import serialization.Quote;
import serialization.Serializer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class QuoteController extends Controller {

    public QuoteController(Request request) {
        super(request);
    }

    @Override
    public Response doGet() {
        String quoteOfTheDay = null;
        try {
            quoteOfTheDay = getQuoteOfTheDayFromHelperService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var q = Serializer.getQuote(quoteOfTheDay);
        quoteOfTheDay = q.getAuthor()+":"+q.getQuote();

        StringBuilder htmlBody = new StringBuilder("<h2>Citat dana:</h2><p>" + quoteOfTheDay + "</p>" +
                "<form method=\"POST\" action=\"/save-quote\">" +
                "<label>Author: </label><input name=\"author\"><br><br>" +
                "<label>Quote: </label><input name=\"quote\"><br><br>" +
                "<button>Save Quote</button>" +
                "</form>");

        htmlBody.append("<h2>Istorija Citata:</h2>");

        for (Quote quote : Server.getQuotes()) {
            htmlBody.append("<p>").append(quote.getAuthor()).append(": ").append(quote.getQuote()).append("</p>");
        }

        String content = "<html><head><title>Unos citata</title></head>\n";
        content += "<body>" + htmlBody + "</body></html>";

        return new HtmlResponse(content);
    }

    @Override
    public Response doPost() {
        String requestBody = request.getBody();
        String[] values = requestBody.split("&");
        String author = null;
        String quote = null;

        for (String pair : values) {
            String[] keyValue = pair.split("=");
            if (keyValue[0].equals("author")) {
                author = keyValue[1].replace("+"," ");
            } else if (keyValue[0].equals("quote")) {
                quote = keyValue[1].replace("+"," ");
            }
        }

        saveQuote(author, quote);


        return new RedirectResponse("/quote");
    }

    private String getQuoteOfTheDayFromHelperService() throws IOException {
        URL url = new URL("http://localhost:8110/quote-of-day");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private void saveQuote(String author, String quote) {
        Quote q = new Quote();
        q.setAuthor(author);
        q.setQuote(quote);
        Server.addToQuotes(q);
    }
}