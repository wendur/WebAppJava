package org.example.Handlers;

import Models.Currency;
import Models.Item;
import Models.Player;
import Models.Progress;
import Services.PlayerService;
import Services.Web;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateHandler extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        final var table = request.getParameter("table");
        Web.value(table).create(request);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void createPlayer(PlayerService playerService, HttpServletRequest request) {
        Player player = new Player();
        player.setPlayerId(Integer.parseInt(request.getParameter("id")));
        player.setNickname(request.getParameter("nickname"));
        playerService.createPlayer(player);
    }

    public void createProgress(PlayerService playerService, HttpServletRequest request) {
        Progress progress = new Progress();
        progress.setId(Integer.parseInt(request.getParameter("id")));
        progress.setPlayerId(Integer.parseInt(request.getParameter("playerId")));
        progress.setResourceId(Integer.parseInt(request.getParameter("resourceId")));
        progress.setScore(Integer.parseInt(request.getParameter("score")));
        progress.setMaxScore(Integer.parseInt(request.getParameter("maxScore")));
        playerService.createProgress(progress, true);
    }

    public void createCurrency(PlayerService playerService, HttpServletRequest request) {
        Currency currency = new Currency();
        currency.setId(Integer.parseInt(request.getParameter("id")));
        currency.setPlayerId(Integer.parseInt(request.getParameter("playerId")));
        currency.setResourceId(Integer.parseInt(request.getParameter("resourceId")));
        currency.setName(request.getParameter("name"));
        currency.setCount(Integer.parseInt(request.getParameter("count")));
        playerService.createCurrency(currency, true);
    }

    public void createItem(PlayerService playerService, HttpServletRequest request) {
        Item item = new Item();
        item.setId(Integer.parseInt(request.getParameter("id")));
        item.setPlayerId(Integer.parseInt(request.getParameter("playerId")));
        item.setResourceId(Integer.parseInt(request.getParameter("resourceId")));
        item.setLevel(Integer.parseInt(request.getParameter("level")));
        item.setCount(Integer.parseInt(request.getParameter("count")));
        playerService.createItem(item, true);
    }
}
