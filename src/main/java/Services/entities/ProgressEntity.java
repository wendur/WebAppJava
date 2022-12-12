package Services.entities;

import Services.PlayerService;

public class ProgressEntity extends AbstractEntity {
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
}
