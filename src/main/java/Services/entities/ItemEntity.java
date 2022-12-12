package Services.entities;

import Services.PlayerService;

public class ItemEntity extends AbstractEntity{
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
}
