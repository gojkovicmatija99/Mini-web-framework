package server;

import server.dto.RequestMethod;
import server.model.Route;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

public class ServerThread implements Runnable {

    private final Server server;

    private final Socket socket;

    public ServerThread(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Route route = routeAdapter(bufferedReader.readLine());
            String response;
            if (server.getRoutes().containsKey(route)) {
                response = (String) server.getRoutes().get(route).callMethod(null);
            } else {
                response = "400: Bad request";
            }
            socket.getOutputStream().write(response.getBytes("UTF-8"));
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Route routeAdapter(String request) {
        String[] requestInfo = request.split(" ");
        RequestMethod requestMethod = Objects.equals(requestInfo[0], "POST") ? RequestMethod.POST : RequestMethod.GET;
        Route route = new Route(requestInfo[1], requestMethod);
        return route;
    }
}
