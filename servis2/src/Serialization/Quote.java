package Serialization;

public class Quote {
    public Quote(String author, String quote) {
        Author = author;
        Quote = quote;
    }

    private String Author;
    private String Quote;

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
    }
}
