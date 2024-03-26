package http;

import Serialization.Quote;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Server {

    public static final int TCP_PORT = 8110;
    private static final List<Quote> quotes = Arrays.asList(
            new Quote("Autor1", "Sjajne stvari nikada ne dolaze iz zone komfora."),
            new Quote("Autor2", "Uspeh je najbolja osveta."),
            new Quote("Autor3", "Budite promena koju zelite da vidite u svetu."),
            new Quote("Autor4", "Ne usporavajte da biste se prilagodili neprijatelju; napravite ga da se prilagodi vama."),
            new Quote("Autor5", "Budite hrabri. Cak i ako niste, ponasajte se kao hrabri ljudi. To je trening, a potom cete postati hrabri.")
    );

    public static Quote getQuote(){
        return quotes.get(new Random().nextInt(quotes.size()));
    }

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
