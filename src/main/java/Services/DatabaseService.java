package Services;

import Models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final Connection connect = BaseConnection.getConnection();

    public void setToDB(List<Player> players) {
        for (Player player : players) {
            insert("players", "(id, nickname)", "(" + player.getPlayerId() + ", '" + player.getNickname() + "')");

            for (Progress progress : player.getProgresses()) {
                insert("progresses", "(id, fk_players, resource_id, score, max_score)",
                        "(" + progress.getId() + ", "
                                + progress.getPlayerId() + ", "
                                + progress.getResourceId() + ", "
                                + progress.getScore() + ", "
                                + progress.getMaxScore() + ")");
            }

            for (Currency currency : player.getCurrencies()) {
                insert("currencies", "(id, fk_players, resource_id, name, count)",
                        "(" + currency.getId() + ", "
                                + currency.getPlayerId() + ", "
                                + currency.getResourceId() + ", '"
                                + currency.getName() + "', "
                                + currency.getCount() + ")");
            }

            for (Item item : player.getItems()) {
                insert("items", "(id, fk_players, resource_id, count, level)",
                        "(" + item.getId() + ", "
                                + item.getPlayerId() + ", "
                                + item.getResourceId() + ", "
                                + item.getCount() + ", "
                                + item.getLevel() + ")");
            }
        }
    }

    public List<Player> getPlayersFromDataBase() {
        List<Player> players = new ArrayList<>();
        try (Statement statement = connect.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM players;");

            while (resultSet.next()) {
                Player player = new Player();
                int id = resultSet.getInt("id");
                player.setPlayerId(id);
                player.setNickname(resultSet.getString("nickname"));
                players.add(player);
            }

            for (Player player : players) {
                ResultSet progResult = statement.executeQuery(String.format("SELECT * FROM progresses WHERE fk_players=%d", player.getPlayerId()));
                while(progResult.next()) {
                    player.addProgress(new Progress(progResult.getInt("id"),
                            progResult.getInt("fk_players"),
                            progResult.getInt("resource_id"),
                            progResult.getInt("score"),
                            progResult.getInt("max_score")));
                }

                ResultSet curResult = statement.executeQuery(String.format("SELECT * FROM currencies WHERE fk_players=%d", player.getPlayerId()));
                while(curResult.next()) {
                    player.addCurrency(new Currency(curResult.getInt("id"),
                            curResult.getInt("fk_players"),
                            curResult.getInt("resource_id"),
                            curResult.getString("name"),
                            curResult.getInt("count")));
                }

                ResultSet itemResult = statement.executeQuery(String.format("SELECT * FROM items WHERE fk_players=%d", player.getPlayerId()));
                while(itemResult.next()) {
                    player.addItem(new Item(itemResult.getInt("id"),
                            itemResult.getInt("fk_players"),
                            itemResult.getInt("resource_id"),
                            itemResult.getInt("count"),
                            itemResult.getInt("level")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return players;
    }

    public void delete(String table, int id) {
        String query = "DELETE FROM " + table + " WHERE id = " + id;
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String table, String values, int id) {
        String query = "UPDATE " + table + " SET " + values + " WHERE id = " + id;
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(String table, String fields, String values) {
        String query = "INSERT INTO " + table + " " + fields + " VALUES " + values;
        //System.out.println(query);
        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            int affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
