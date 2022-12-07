package org.example;

import Services.Web;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.example.Handlers.CreateHandler;
import org.example.Handlers.DeleteHandler;
import org.example.Handlers.UpdateHandler;


public class ServerStart {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        Web.load();

        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(DeleteHandler.class, "/delete");
        handler.addServletWithMapping(CreateHandler.class, "/create");
        handler.addServletWithMapping(UpdateHandler.class, "/update");

        server.setHandler(handler);

        server.start();
    }
}
