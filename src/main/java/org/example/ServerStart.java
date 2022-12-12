package org.example;

import Services.PlayerService;
import Services.servlets.CurrencyServlet;
import Services.servlets.ItemServlet;
import Services.servlets.PlayerServlet;
import Services.servlets.ProgressServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerStart {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        PlayerService playerService = new PlayerService();
        playerService.load();

        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(new ServletHolder(new PlayerServlet(playerService)), "/player");
        handler.addServletWithMapping(new ServletHolder(new ProgressServlet(playerService)), "/progress");
        handler.addServletWithMapping(new ServletHolder(new CurrencyServlet(playerService)), "/currency");
        handler.addServletWithMapping(new ServletHolder(new ItemServlet(playerService)), "/item");

        server.setHandler(handler);

        server.start();
    }
}
