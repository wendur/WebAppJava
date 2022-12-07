package org.example.Handlers;

import Services.Web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        final var table = request.getParameter("table");
        final var playerId = Integer.parseInt(request.getParameter("playerId"));

        var id = -1;
        if (!table.equals("player")) {
            id = Integer.parseInt(request.getParameter("id"));
        }

        Web.value(table).delete(playerId, id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
