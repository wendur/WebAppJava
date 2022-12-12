package Services.entities;

import Services.PlayerService;

public class PlayerEntity extends AbstractEntity {

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
}
