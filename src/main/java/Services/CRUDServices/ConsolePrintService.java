package Services.CRUDServices;

import Models.Player;
import Services.Entity;
import Services.PlayerService;

import java.util.Scanner;

public class ConsolePrintService {
    private final Scanner scanner = new Scanner(System.in);
    public void reader(PlayerService playerService) {
        System.out.print("Enter playerId - ");
        int playerId = scanner.nextInt();
        scanner.nextLine();

        playerService.printPlayer(playerId);
    }
}
