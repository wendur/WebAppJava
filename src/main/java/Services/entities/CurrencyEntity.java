package Services.entities;

import Services.PlayerService;

public class CurrencyEntity extends AbstractEntity{
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
}
