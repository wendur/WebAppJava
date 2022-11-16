package Services.CRUDServices;

import Models.Currency;
import Models.Item;
import Models.Player;
import Models.Progress;
import Services.Entity;
import Services.PlayerService;

import java.util.Scanner;

public class ConsoleCreateService {

    private final Scanner scanner = new Scanner(System.in);
    public void reader(PlayerService playerService) {
        System.out.print("Enter entity name (player, progress, currency, item) - ");
        String entity = scanner.nextLine();

        Entity.value(entity).create(playerService,true, -1);
    }

    public void createPlayer(PlayerService playerService, boolean flag) {
        Player player = new Player();

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        player.setPlayerId(id);

        System.out.print("Enter nickname - ");
        player.setNickname(scanner.nextLine());

        int num = 0;
        System.out.print("Enter number of progress - ");
        num = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<num; i++) {
            player.addProgress(createProgress(playerService, false, id));
        }

        System.out.print("Enter number of currency - ");
        num = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<num; i++) {
            player.addCurrency(createCurrency(playerService, false, id));
        }

        System.out.print("Enter number of item - ");
        num = scanner.nextInt();
        scanner.nextLine();
        for (int i=0; i<num; i++) {
            player.addItem(createItem(playerService, false, id));
        }

        playerService.createPlayer(player);

    }

    public Progress createProgress(PlayerService playerService, boolean flag, int playerId) {
        Progress progress = new Progress();
        System.out.println("Creating new progress:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        progress.setId(id);

        if (playerId == -1) {
            System.out.print("Enter playerId - ");
            playerId = scanner.nextInt();
            scanner.nextLine();
        }
        progress.setPlayerId(playerId);

        System.out.print("Enter resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        progress.setResourceId(resourceId);

        System.out.print("Enter score - ");
        int score = scanner.nextInt();
        scanner.nextLine();
        progress.setScore(score);

        System.out.print("Enter maxScore - ");
        int maxScore = scanner.nextInt();
        scanner.nextLine();
        progress.setMaxScore(maxScore);

        if (flag) {
            playerService.createProgress(progress, true);
        }

        return progress;
    }

    public Currency createCurrency(PlayerService playerService, boolean flag, int playerId) {
        Currency currency = new Currency();
        System.out.println("Creating new currency:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        currency.setId(id);

        if (playerId == -1) {
            System.out.print("Enter playerId - ");
            playerId = scanner.nextInt();
            scanner.nextLine();
        }
        currency.setPlayerId(playerId);

        System.out.print("Enter resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        currency.setResourceId(resourceId);

        System.out.print("Enter name - ");
        String name = scanner.nextLine();
        currency.setName(name);

        System.out.print("Enter count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        currency.setCount(count);

        if (flag) {
            playerService.createCurrency(currency, true);
        }

        return currency;
    }

    public Item createItem(PlayerService playerService, boolean flag, int playerId) {
        Item item = new Item();
        System.out.println("Creating new item:");

        System.out.print("Enter id - ");
        int id = scanner.nextInt();
        scanner.nextLine();
        item.setId(id);

        if (playerId == -1) {
            System.out.print("Enter playerId - ");
            playerId = scanner.nextInt();
            scanner.nextLine();
        }
        item.setPlayerId(playerId);

        System.out.print("Enter resourceId - ");
        int resourceId = scanner.nextInt();
        scanner.nextLine();
        item.setResourceId(resourceId);

        System.out.print("Enter count - ");
        int count = scanner.nextInt();
        scanner.nextLine();
        item.setCount(count);

        System.out.print("Enter level - ");
        int level = scanner.nextInt();
        scanner.nextLine();
        item.setLevel(level);

        if (flag) {
            playerService.createItem(item, true);
        }

        return item;
    }
}
