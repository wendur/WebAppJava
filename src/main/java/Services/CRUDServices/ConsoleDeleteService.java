package Services.CRUDServices;

import Services.Entity;
import Services.PlayerService;

import java.util.Scanner;

public class ConsoleDeleteService {
    private final Scanner scanner = new Scanner(System.in);
    public void reader(PlayerService playerService) {
        System.out.print("Enter entity name (player, progress, currency, item) - ");
        String table = scanner.nextLine();

        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();

        int id = -1;
        if (!table.equals("player")) {
            System.out.print("Enter id - ");
            id = scanner.nextInt();
            scanner.nextLine();
        }

        Entity.value(table).delete(playerId, id, playerService);
        //playerService.delete(table, playerId, id);
    }
}
