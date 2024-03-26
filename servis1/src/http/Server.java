package http;

import com.google.gson.Gson;
import serialization.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Server {
    private static final List<Quote> quotes = new ArrayList<>();

    public static void addToQuotes(Quote quote){
        quotes.add(quote);
    }
    public static List<Quote> getQuotes(){
        return quotes;
    }
    public static final int TCP_PORT = 8113;

    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(TCP_PORT);
            while (true) {
                Socket sock = ss.accept();
                new Thread(new ServerThread(sock)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
