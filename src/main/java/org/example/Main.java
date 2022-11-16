package org.example;

import Models.Player;
import Services.ConsoleService;
import Services.DatabaseService;
import Services.PlayerService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

        //PlayerService ps = new PlayerService();
        //List<Player> players = ps.playersFromJsonFile(new File("players.json"));

        //ps.printPlayer(players.get(0));

        //DatabaseService ds = new DatabaseService();
        //ds.setToDB(players);

        //List<Player> playersFromDB = ds.getPlayersFromDataBase();

        //System.out.println(playersFromDB.size());

        ConsoleService cs = new ConsoleService();
        cs.console();

    }

}