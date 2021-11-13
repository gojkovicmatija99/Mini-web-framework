package app.client;

import com.google.gson.Gson;
import server.dto.RequestMethod;
import server.model.Route;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PostClient {

    public static void main(String[] args) throws IOException {
        int port = 3000;
        String ip = "127.0.0.1";
        Socket socket = new Socket(ip, port);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        orderProduct(printWriter, bufferedReader);
    }

    private static void orderProduct(PrintWriter printWriter, BufferedReader bufferedReader) throws IOException {
        Gson gson = new Gson();
        Route route = new Route("app/orderProduct", RequestMethod.POST);
        printWriter.println(gson.toJson(route));
        System.out.println("[CLIENT] Request: POST app/order -> Response: " + bufferedReader.readLine());
    }
}
