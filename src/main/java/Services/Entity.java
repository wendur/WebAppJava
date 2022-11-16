package Services;

import Models.Currency;
import Models.Item;
import Models.Player;
import Models.Progress;
import Services.CRUDServices.ConsoleCreateService;
import Services.CRUDServices.ConsoleUpdateService;

import java.util.Arrays;
import java.util.List;

public enum Entity {

    PLAYER("player") {
        @Override
        public void create(PlayerService playerService, boolean flag, int id) {
            consoleCreateService.createPlayer(playerService, flag);
        }

        @Override
        public void update(PlayerService playerService) {
            consoleUpdateService.updatePlayer(playerService);
        }

        @Override
        public void delete(int playerId, int id, PlayerService playerService) {
            playerService.deletePlayer(playerId);
        }
    },
    PROGRESS("progress") {
        @Override
        public void create(PlayerService playerService, boolean flag, int id) {
            consoleCreateService.createProgress(playerService, flag, id);
        }
        @Override
        public void update(PlayerService playerService) {
            consoleUpdateService.updateProgress(playerService);
        }
        @Override
        public void delete(int playerId, int id, PlayerService playerService) {
            playerService.deleteProgress(playerId, id);
        }
    },
    CURRENCY("currency") {
        @Override
        public void create(PlayerService playerService, boolean flag, int id) {
            consoleCreateService.createCurrency(playerService, flag, id);
        }
        @Override
        public void update(PlayerService playerService) {
            consoleUpdateService.updateCurrency(playerService);
        }
        @Override
        public void delete(int playerId, int id, PlayerService playerService) {
            playerService.deleteCurrency(playerId, id);
        }
    },
    ITEM("item") {
        @Override
        public void create(PlayerService playerService, boolean flag, int id) {
            consoleCreateService.createItem(playerService, flag, id);
        }
        @Override
        public void update(PlayerService playerService) {
            consoleUpdateService.updateItem(playerService);
        }
        @Override
        public void delete(int playerId, int id, PlayerService playerService) {
            playerService.deleteItem(playerId, id);
        }
    };

    private final String name;

    Entity(String name) {
        this.name = name;
    }

    public abstract void create(PlayerService playerService, boolean flag, int id);
    public abstract void update(PlayerService playerService);
    public abstract void delete(int playerId, int id, PlayerService playerService);

    protected final ConsoleCreateService consoleCreateService = new ConsoleCreateService();
    protected final ConsoleUpdateService consoleUpdateService = new ConsoleUpdateService();
    //protected final PlayerService playerService = new PlayerService();


    public static Entity value(String name) {
        return Arrays.stream(values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow();
    }
}
