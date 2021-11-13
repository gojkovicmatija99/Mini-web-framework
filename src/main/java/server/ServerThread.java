package server;

import com.google.gson.Gson;
import server.model.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Server server;

    private Socket socket;

    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            Gson gson = new Gson();
            Route request = gson.fromJson(bufferedReader.readLine(), Route.class);
            String response;
            if (server.getRoutes().containsKey(request)) {
                response = (String) server.getRoutes().get(request).callMethod(null);
            } else {
                response = "400: Bad request";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
