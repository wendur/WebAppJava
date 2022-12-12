package Services;

import org.example.Handlers.CreateHandler;
import org.example.Handlers.UpdateHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public enum Web {

    PLAYER("player") {
        @Override
        public void create(HttpServletRequest request) {
            createHandler.createPlayer(playerService, request);
        }

        @Override
        public void update(HttpServletRequest request) {
            updateHandler.updatePlayer(playerService, request);
        }

        @Override
        public void delete(int playerId, int id) {
            playerService.deletePlayer(playerId);
        }
    },
    PROGRESS("progress") {
        @Override
        public void create(HttpServletRequest request) {
            createHandler.createProgress(playerService, request);
        }

        @Override
        public void update(HttpServletRequest request) {
            updateHandler.updateProgress(playerService, request);
        }

        @Override
        public void delete(int playerId, int id) {
            playerService.deleteProgress(playerId, id);
        }
    },
    CURRENCY("currency") {
        @Override
        public void create(HttpServletRequest request) {
            createHandler.createCurrency(playerService, request);
        }

        @Override
        public void update(HttpServletRequest request) {
            updateHandler.updateCurrency(playerService, request);
        }

        @Override
        public void delete(int playerId, int id) {
            playerService.deleteCurrency(playerId, id);
        }
    },
    ITEM("item") {
        @Override
        public void create(HttpServletRequest request) {
            createHandler.createItem(playerService, request);
        }

        @Override
        public void update(HttpServletRequest request) {
            updateHandler.updateItem(playerService, request);
        }

        @Override
        public void delete(int playerId, int id) {
            playerService.deleteItem(playerId, id);
        }
    };
    private final String command;
    private static final PlayerService playerService = new PlayerService();
    private static final UpdateHandler updateHandler = new UpdateHandler();
    private static final CreateHandler createHandler = new CreateHandler();

    Web(String command) {
        this.command = command;
    }

    public static void load() {
        playerService.load();
    }

    public abstract void create(HttpServletRequest request);
    public abstract void update(HttpServletRequest request);
    public abstract void delete(int playerId, int id);

    public static Web value(String command) {
        return Arrays.stream(values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow();
    }
}
