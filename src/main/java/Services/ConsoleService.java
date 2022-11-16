package Services;

import java.util.Scanner;

public class ConsoleService {
    public void console() {
        Scanner scanner = new Scanner(System.in);
        PlayerService playerService = new PlayerService();
        playerService.load();

        while (true) {
            System.out.println("Commands:\n" +
                            "1.delete\n" +
                            "2.create\n" +
                            "3.update\n" +
                            "4.exit");

            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                System.out.println("Console mod exit.");
                return;
            }

            CRUD.value(command).make(playerService);

        }
    }
}
