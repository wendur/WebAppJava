package Services;

import Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PlayerService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DatabaseService databaseService = new DatabaseService();
    private List<Player> players;

    public PlayerService() {}

    public void load() {
        players = databaseService.getPlayersFromDataBase();
    }

    public List<Player> playersFromJsonFile(File filename) {
        try {
            return List.of(mapper.readValue(filename, Player[].class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printPlayer(int id) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(players.get(findIndex(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printProgress(int playerId, int id) {
        try {
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(players.get(findIndex(playerId))
                                                .getProgresses().get(findIndexProgress(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printCurrency(int playerId, int id) {
        try {
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(players.get(findIndex(playerId))
                            .getCurrencies().get(findIndexCurrency(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String printItem(int playerId, int id) {
        try {
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(players.get(findIndex(playerId))
                            .getItems().get(findIndexItem(id)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int findIndex(int playerId) {
        for (Player pl : players) {
            if (pl.getPlayerId() == playerId) {
                return players.indexOf(pl);
            }
        }
        return -1;
    }

    public int findIndexProgress(int id) {
        for (Player player : players) {
            for (Progress progress : player.getProgresses()) {
                if (progress.getId() == id) {
                    //System.out.println(player.getProgresses().indexOf(progress));
                    return player.getProgresses().indexOf(progress);
                }
            }
        }
        return -1;
    }

    public int findIndexCurrency(int id) {
        for (Player player : players) {
            for (Currency currency : player.getCurrencies()) {
                if (currency.getId() == id) {
                    return player.getCurrencies().indexOf(currency);
                }
            }
        }
        return -1;
    }

    public int findIndexItem(int id) {
        for (Player player : players) {
            for (Item item : player.getItems()) {
                if (item.getId() == id) {
                    return player.getItems().indexOf(item);
                }
            }
        }
        return -1;
    }

//    public void delete(String table, int playerId, int id) {
//        int index = findIndex(playerId);
//        if (index == -1) {
//            System.out.println("Player with this ID does not exist!!!");
//            return;
//        }
//
//        //проверка на существование ???
//
//        //Entity.value(table).delete(index, id, players);
//
//        if (table.equals("player")) {
//            databaseService.delete(table, playerId);
//        } else {
//            databaseService.delete(table, id);
//        }
//    }

    public void deletePlayer(int playerId) {
        int index = findIndex(playerId);
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        players.remove(index);
        databaseService.delete("players", playerId);
    }

    public void deleteProgress(int playerId, int id) {
        int index = findIndex(playerId);
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        players.get(index).getProgresses().remove(findIndexProgress(id));
        databaseService.delete("progresses", id);
    }

    public void deleteCurrency(int playerId, int id) {
        int index = findIndex(playerId);
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        players.get(index).getCurrencies().remove(findIndexCurrency(id));
        databaseService.delete("currencies", id);
    }

    public void deleteItem(int playerId, int id) {
        int index = findIndex(playerId);
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }
        players.get(index).getItems().remove(findIndexItem(id));
        databaseService.delete("items", id);
    }

    public Player onlyPlayer(Player player) {
        Player temp = new Player();
        temp.setPlayerId(player.getPlayerId());
        temp.setNickname(player.getNickname());
        return temp;
    }
    public void createPlayer(Player player) {
        if (findIndex(player.getPlayerId()) != -1) {
            System.out.println("Player with this ID already exists!!!");
            return;
        }

        players.add(onlyPlayer(player));

        databaseService.insert("players", "(id, nickname)", "(" + player.getPlayerId() + ", '"
                + player.getNickname() + "')");

        for (Progress progress : player.getProgresses()) {
            createProgress(progress, true);
        }

        for (Currency currency : player.getCurrencies()) {
            createCurrency(currency, true);
        }

        for (Item item : player.getItems()) {
            createItem(item, true);
        }
    }

    public void createProgress(Progress progress, boolean flag) {

        if (findIndex(progress.getPlayerId()) == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        if (findIndexProgress(progress.getId()) != -1) {
            System.out.println("Progress with this ID already exists!!!");
            return;
        }

        if (flag) {
            players.get(findIndex(progress.getPlayerId())).addProgress(progress);
        }

        databaseService.insert("progresses", "(id, fk_players, resource_id, score, max_score)",
                "(" + progress.getId() + ","
                          + progress.getPlayerId() + ","
                          + progress.getResourceId() + ","
                          + progress.getScore() + ","
                          + progress.getMaxScore() + ")");
    }

    public void createCurrency(Currency currency, boolean flag) {
        if (findIndex(currency.getPlayerId()) == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        if (findIndexCurrency(currency.getId()) != -1) {
            System.out.println("Currency with this ID already exists!!!");
            return;
        }

        if (flag) {
            players.get(findIndex(currency.getPlayerId())).addCurrency(currency);
        }

        databaseService.insert("currencies", "(id, fk_players, resource_id, name, count)",
                "(" + currency.getId() + ","
                        + currency.getPlayerId() + ","
                        + currency.getResourceId() + ",'"
                        + currency.getName() + "',"
                        + currency.getCount() + ")");
    }

    public void createItem(Item item, boolean flag) {
        if (findIndex(item.getPlayerId()) == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        if (findIndexItem(item.getId()) != -1) {
            System.out.println("Item with this ID already exists!!!");
            return;
        }

        if (flag) {
            players.get(findIndex(item.getPlayerId())).addItem(item);
        }

        databaseService.insert("items", "(id, fk_players, resource_id, count, level)",
                "(" + item.getId() + ","
                        + item.getPlayerId() + ","
                        + item.getResourceId() + ","
                        + item.getCount() + ","
                        + item.getLevel() + ")");
    }

    public void updatePlayer(Player player) {
        int index = findIndex(player.getPlayerId());
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        players.get(index).setNickname(player.getNickname());
        databaseService.update("players", "nickname='" + player.getNickname() + "'", player.getPlayerId());
    }

    public void updateProgress(Progress progress) {
        int index = findIndex(progress.getPlayerId());
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        int indexEntity = findIndexProgress(progress.getId());
        if (indexEntity == -1) {
            System.out.println("Progress with this ID does not exists!!!");
            return;
        }

        players.get(index).getProgresses().get(indexEntity).setScore(progress.getScore());
        players.get(index).getProgresses().get(indexEntity).setMaxScore(progress.getMaxScore());
        databaseService.update("progresses", "score=" + progress.getScore()
                                                        + ", max_score=" + progress.getMaxScore(), progress.getId());
    }

    public void updateCurrency(Currency currency) {
        int index = findIndex(currency.getPlayerId());
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        int indexEntity = findIndexCurrency(currency.getId());
        if (indexEntity == -1) {
            System.out.println("Currency with this ID does not exists!!!");
            return;
        }

        players.get(index).getCurrencies().get(indexEntity).setName(currency.getName());
        players.get(index).getCurrencies().get(indexEntity).setCount(currency.getCount());
        databaseService.update("progresses", "name='" + currency.getName()
                + "', count=" + currency.getCount(), currency.getId());
    }

    public void updateItem(Item item) {
        int index = findIndex(item.getPlayerId());
        if (index == -1) {
            System.out.println("Player with this ID does not exist!!!");
            return;
        }

        int indexEntity = findIndexItem(item.getId());
        if (indexEntity == -1) {
            System.out.println("Item with this ID does not exists!!!");
            return;
        }

        players.get(index).getItems().get(indexEntity).setCount(item.getCount());
        players.get(index).getItems().get(indexEntity).setLevel(item.getLevel());
        databaseService.update("items", "count=" + item.getCount()
                + ", level=" + item.getLevel(), item.getId());
    }
}
