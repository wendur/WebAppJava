package Services.servlets;

import Models.Currency;
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

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
    private final PlayerService playerService;

    public CurrencyServlet(PlayerService playerService) {
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

        if (playerService.findIndexCurrency(Integer.parseInt(id)) == -1) {
            resp.getWriter().println("Currency not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            outputStream.write(playerService.printCurrency(Integer.parseInt(playerId), Integer.parseInt(id))
                    .getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var sourceId = req.getParameter("sourceId");
        final var name = req.getParameter("name");
        final var count = req.getParameter("count");


        if (id == null || playerId == null || sourceId == null || name == null || count == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexCurrency(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Currency already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.createCurrency(new Currency(Integer.parseInt(id), Integer.parseInt(playerId),
                Integer.parseInt(sourceId), name, Integer.parseInt(count)), true);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var playerId = req.getParameter("playerId");
        final var id = req.getParameter("id");
        final var name = req.getParameter("name");
        final var count = req.getParameter("count");


        if (id == null || playerId == null || name == null || count == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndex(Integer.parseInt(playerId)) == -1) {
            resp.getWriter().println("Player not exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        if (playerService.findIndexCurrency(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Currency already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.updateCurrency(new Currency(Integer.parseInt(id), Integer.parseInt(playerId),
                name, Integer.parseInt(count)));
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

        if (playerService.findIndexCurrency(Integer.parseInt(id)) != -1) {
            resp.getWriter().println("Currency already exist!!!");
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        playerService.deleteCurrency(Integer.parseInt(playerId), Integer.parseInt(id));
    }
}
