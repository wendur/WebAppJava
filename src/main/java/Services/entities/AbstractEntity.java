package Services.entities;

import Services.CRUDServices.ConsoleCreateService;
import Services.CRUDServices.ConsoleUpdateService;
import Services.PlayerService;

public abstract class AbstractEntity {
    protected final ConsoleCreateService consoleCreateService = new ConsoleCreateService();
    protected final ConsoleUpdateService consoleUpdateService = new ConsoleUpdateService();

    public abstract void create(PlayerService playerService, boolean flag, int id);

    public abstract void update(PlayerService playerService);

    public abstract void delete(int playerId, int id, PlayerService playerService);
}
