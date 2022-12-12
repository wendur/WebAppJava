package Services.servlets;

import Models.Item;
import Services.PlayerService;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
    private final PlayerService playerService;

    public ItemServlet(PlayerService playerService) {
        this.playerService = playerService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");

        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexItem(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Item not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(playerService.printItem(Integer.parseInt(playerId), Integer.parseInt(id))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var count = req.getParameter("count");
        final var level = req.getParameter("level");


        if (id == null || playerId == null || sourceId == null || count == null || level == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexItem(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Item already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createItem(new Item(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), Integer.parseInt(count), Integer.parseInt(level)), true);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var count = req.getParameter("count");
        final var level = req.getParameter("level");


        if (id == null || playerId == null || count == null || level == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexItem(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Item already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateItem(new Item(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(count), Integer.parseInt(level)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");


        if (id == null || playerId == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexItem(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Item already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteItem(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
