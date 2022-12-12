package Services.servlets;

import Models.Progress;
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

@WebServlet("/progress")
public class ProgressServlet extends HttpServlet {
    private final PlayerService playerService;

    public ProgressServlet(PlayerService playerService) {
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

        if (playerService.findIndexProgress(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Progress not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(playerService.printProgress(Integer.parseInt(playerId), Integer.parseInt(id))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var score = req.getParameter("score");
        final var maxScore = req.getParameter("maxScore");


        if (id == null || playerId == null || sourceId == null || score == null || maxScore == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexProgress(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Progress already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createProgress(new Progress(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), Integer.parseInt(score), Integer.parseInt(maxScore)), true);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var score = req.getParameter("score");
        final var maxScore = req.getParameter("maxScore");


        if (id == null || playerId == null || score == null || maxScore == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexProgress(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Progress not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateProgress(new Progress(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(score), Integer.parseInt(maxScore)));
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

        if (playerService.findIndexProgress(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Progress not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteProgress(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
