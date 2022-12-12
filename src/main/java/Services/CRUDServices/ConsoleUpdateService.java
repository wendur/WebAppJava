package Services.CRUDServices;

import Models.Currency;
import Models.Item;
import Models.Player;
import Models.Progress;
import Services.Entity;
import Services.PlayerService;

import java.util.Scanner;

public class ConsoleUpdateService {

    private final Scanner scanner = new Scanner(System.in);
    public void reader(PlayerService playerService) {
        System.out.print("Enter entity name (player, progress, currency, item) - ");
        String entity = scanner.nextLine();

        Entity.value(entity).instance().update(playerService);
    }

    public void updatePlayer(PlayerService playerService) {
        Player player = new Player();

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        player.setPlayerId(id);

        System.out.print("Enter new nickname - ");
        player.setNickname(scanner.nextLine());

        playerService.updatePlayer(player);
    }

    public void updateProgress(PlayerService playerService) {
        Progress progress = new Progress();
        System.out.println("Updating progress:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        progress.setId(id);

        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        progress.setPlayerId(playerId);

        System.out.print("Enter score - ");
        int score = scanner.nextInt();
        scanner.nextLine();
        progress.setScore(score);

        System.out.print("Enter maxScore - ");
        int maxScore = scanner.nextInt();
        scanner.nextLine();
        progress.setMaxScore(maxScore);

        playerService.updateProgress(progress);
    }

    public void updateCurrency(PlayerService playerService) {
        Currency currency = new Currency();
        System.out.println("Updating currency:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        currency.setId(id);

        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        currency.setPlayerId(playerId);

        System.out.print("Enter name - ");
        String name = scanner.nextLine();
        currency.setName(name);

        System.out.print("Enter count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        currency.setCount(count);

        playerService.updateCurrency(currency);
    }

    public void updateItem(PlayerService playerService) {
        Item item = new Item();
        System.out.println("Updating item:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        item.setId(id);

        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();
        item.setPlayerId(playerId);

        System.out.print("Enter count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        item.setCount(count);

        System.out.print("Enter level - ");
        int level = scanner.nextInt();
        scanner.nextLine();
        item.setLevel(level);

        playerService.updateItem(item);
    }
}
