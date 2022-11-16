package Services;

import Services.CRUDServices.ConsoleCreateService;
import Services.CRUDServices.ConsoleDeleteService;
import Services.CRUDServices.ConsolePrintService;
import Services.CRUDServices.ConsoleUpdateService;

import java.util.Arrays;

public enum CRUD {

    DELETE("delete") {
        @Override
        public void make(PlayerService playerService) {
            consoleDeleteService.reader(playerService);
        }
    },
    CREATE("create") {
        @Override
        public void make(PlayerService playerService) {
            consoleCreateService.reader(playerService);
        }
    },
    UPDATE("update") {
        @Override
        public void make(PlayerService playerService) {
            consoleUpdateService.reader(playerService);
        }
    },
    PRINT("print") {
        @Override
        public void make(PlayerService playerService) {
            consolePrintService.reader(playerService);
        }
    };

    private final String command;
    protected final ConsoleDeleteService consoleDeleteService = new ConsoleDeleteService();
    protected final ConsoleUpdateService consoleUpdateService = new ConsoleUpdateService();
    protected final ConsoleCreateService consoleCreateService = new ConsoleCreateService();

    protected final ConsolePrintService consolePrintService = new ConsolePrintService();

    CRUD(String command) {
        this.command = command;
    }

    public abstract void make(PlayerService playerService);

    public static CRUD value(String command) {
        return Arrays.stream(values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow();
    }
}

