package Services.servlets;

import Models.Player;
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

@WebServlet("/player")
public class PlayerServlet extends HttpServlet {
    private final PlayerService playerService;

    public PlayerServlet(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    // read
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var id = req.getParameter("id");

        if (id == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(playerService.printPlayer(Integer.parseInt(id)).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    // update
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var id = req.getParameter("id");
        final var nick = req.getParameter("nickname");

        if ((id == null) || (nick == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updatePlayer(new Player(Integer.parseInt(id), nick));
    }

    @Override
    // create
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var id = req.getParameter("id");
        final var nick = req.getParameter("nickname");

        if ((id == null) || (nick == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Player already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createPlayer(new Player(Integer.parseInt(id), nick));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    // delete
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var id = req.getParameter("id");

        if ((id == null)) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deletePlayer(Integer.parseInt(id));
    }
}

// curl GET http://localhost8888/player/?id=1000
// curl PUT http://localhost8888/player?id=1000&nick=qwerty
