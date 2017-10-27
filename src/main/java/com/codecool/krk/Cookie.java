package com.codecool.krk;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpCookie;

public class Cookie implements HttpHandler {
    int counter = 0;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        counter++;
        String response = "Page was visited: " + counter + " times!";


        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        HttpCookie cookie;
        boolean isNewSession;
        if (cookieStr != null) {  // Cookie already exists
            cookie = HttpCookie.parse(cookieStr).get(0);
            isNewSession = false;
        } else { // Create a new cookie
            cookie = new HttpCookie("sessionId", String.valueOf(counter)); // it's not a good way to create sessionId
            isNewSession = true;
            httpExchange.getResponseHeaders().add("Set-Cookie", cookie.toString());
        }

        response += "\n isNewSession: " + isNewSession;
        response += "\n session id: " + cookie.getValue();


        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
