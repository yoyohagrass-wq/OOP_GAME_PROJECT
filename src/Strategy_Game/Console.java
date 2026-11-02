/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Strategy_Game;

/**
 *
 * @author yoyoh
 */
import java.util.Scanner;

public class Console {

    private Player player1;
    private Player player2;
    private Game game;
    private final GameMap gameMap = new GameMap();
    private CounterSystem counterSystem = new CounterSystem();
    private final CombatSystem combat = new CombatSystem();
    private static final Scanner scanner = new Scanner(System.in);
    private int nextUnitID = 1;

    public Console(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        game = new Game(player1, player2);
    }

    public void showMainMenu() {
        System.out.println("\n========== STRATEGY GAME ==========");
        System.out.println("1. View Player Information");
        System.out.println("2. Recruit Units");
        System.out.println("3. Collect Gold");
        System.out.println("4. Start Battle");
        System.out.println("5. View Buildings");
        System.out.println("6. Attack Building");
        System.out.println("7. View Map");
        System.out.println("8. View Statistics");
        System.out.println("9. Save Player");
        System.out.println("10. Load Player");
        System.out.println("11. Delete Save");
        System.out.println("12. Exit");
        System.out.print("Choose an option: ");
    }

    public Player choosePlayer() {
        System.out.println("1. " + player1.getName());
        System.out.println("2. " + player2.getName());
        System.out.print("Choose player: ");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            return player1;
        }

        if (choice.equals("2")) {
            return player2;
        }

        System.out.println("Invalid player!");
        return null;
    }

    public void showPlayerInformation(Player player) {
        System.out.println("\n========== " + player.getName() + " ==========");
        System.out.println("Civilization: " + player.getCivilization().getName());
        System.out.println("Gold: " + player.getGold());
        System.out.println("Army Size: " + player.getArmy().getArmySize());
    }

    public void recruitUnits() {
        System.out.println("\n========== RECRUIT UNITS ==========");

        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        System.out.println("Gold: " + player.getGold());

        System.out.println("1. Archer - 100 Gold");
        System.out.println("2. Knight - 150 Gold");
        System.out.println("3. Cavalry - 200 Gold");
        System.out.println("4. Legionary - 175 Gold (Romans)");
        System.out.println("5. Berserker - 180 Gold (Vikings)");
        System.out.println("6. Chariot - 200 Gold (Egyptians)");
        System.out.println("7. Spearman");
        System.out.println("8. Crossbowman");
        System.out.println("9. Longbowman");
        System.out.println("10. Guardian");

        System.out.print("Choose unit: ");

        Class<? extends Unit> unitType;

        switch (scanner.nextLine()) {
            case "1":
                unitType = Archer.class;
                break;

            case "2":
                unitType = Knight.class;
                break;

            case "3":
                unitType = Cavalry.class;
                break;

            case "4":
                unitType = Legionary.class;
                break;

            case "5":
                unitType = Berserker.class;
                break;

            case "6":
                unitType = Chariot.class;
                break;

            case "7":
                unitType = Spearman.class;
                break;

            case "8":
                unitType = Crossbowman.class;
                break;

            case "9":
                unitType = Longbowman.class;
                break;

            case "10":
                unitType = Guardian.class;
                break;

            default:
                System.out.println("Invalid unit!");
                return;
        }

        boolean recruited = player.getBarracks().recruitUnit(
                player, unitType, nextUnitID
        );

        if (recruited) {
            nextUnitID++;
            System.out.println("Unit recruited!");
            System.out.println("Remaining Gold: " + player.getGold());
        } else {
            System.out.println("Recruitment failed!");
        }
    }

    public void collectGold() {
        System.out.println("\n========== COLLECT GOLD ==========");

        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        int before = player.getGold();

        player.getGoldMine().collectGold(player);

        System.out.println("Gold collected: "
                + (player.getGold() - before));

        System.out.println("Total gold: " + player.getGold());
    }

    public Unit selectUnit(Army army) {
        while (true) {
            System.out.println("\n========== SELECT UNIT ==========");

            for (int i = 0; i < army.getUnits().size(); i++) {

                Unit unit = army.getUnits().get(i);

                if (!unit.isDestroyed()) {
                    System.out.println((i + 1) + ". "
                            + unit.getClass().getSimpleName()
                            + " - HP: " + unit.getHealth());
                }
            }

            System.out.print("Choose unit (0 to cancel): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    return null;
                }

                if (choice > 0 && choice <= army.getUnits().size()) {

                    Unit unit = army.getUnits().get(choice - 1);

                    if (!unit.isDestroyed()) {
                        return unit;
                    }
                }

                System.out.println("Invalid unit!");

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public void startBattle() {
        Army army1 = player1.getArmy();
        Army army2 = player2.getArmy();

        if (army1.isDefeated() || army2.isDefeated()) {
            System.out.println("Both players need living soldiers!");
            return;
        }

        Battle battle = new Battle(army1, army2);

        System.out.println("\n========== BATTLE STARTED ==========");

        while (battle.getWinner() == null) {

            Player attacking;
            Player defending;

            if (battle.getCurrentTurn() == 1) {
                attacking = player1;
                defending = player2;
            } else {
                attacking = player2;
                defending = player1;
            }

            System.out.println("\n" + attacking.getName() + "'s Turn");

            System.out.println("Choose attacker:");
            Unit attacker = selectUnit(attacking.getArmy());

            if (attacker == null) {
                return;
            }

            System.out.println("Choose target:");
            Unit target = selectUnit(defending.getArmy());

            if (target == null) {
                return;
            }

            boolean useCharge = false;

            if (attacker instanceof Chariot) {
                System.out.print("Use Charge? (y/n): ");
                useCharge = scanner.nextLine().equalsIgnoreCase("y");
            }

            int before = target.getHealth();

            boolean success = battle.takeTurn(
                    attacker, target, useCharge
            );

            if (success) {
                System.out.println("Damage: "
                        + (before - target.getHealth()));

                System.out.println("Enemy HP: " + target.getHealth());
            } else {
                System.out.println("Attack failed!");
            }
        }

        Player winner;

        if (battle.getWinner() == army1) {
            winner = player1;
        } else {
            winner = player2;
        }

        counterSystem.recordBattle(winner, player1, player2);

        System.out.println("\n" + winner.getName()
                + " wins the battle!");
    }

    public void showBuildings(Player player) {
        System.out.println("\n========== "
                + player.getName() + "'s BUILDINGS ==========");

        System.out.println("Barracks: "
                + player.getBarracks().getHealth() + " HP");

        System.out.println("GoldMine: "
                + player.getGoldMine().getHealth() + " HP");

        System.out.println("DefenseTower: "
                + player.getDefenseTower().getHealth() + " HP");

        System.out.println("TownHall: "
                + player.getTownHall().getHealth() + " HP");
    }

    public void attackBuilding() {
        System.out.println("\n========== ATTACK BUILDING ==========");

        Player attacking = choosePlayer();

        if (attacking == null) {
            return;
        }

        Player defending;

        if (attacking == player1) {
            defending = player2;
        } else {
            defending = player1;
        }

        if (attacking.getArmy().isDefeated()) {
            System.out.println("You have no living soldiers!");
            return;
        }

        Unit attacker = selectUnit(attacking.getArmy());

        if (attacker == null) {
            return;
        }

        System.out.println("1. Barracks - "
                + defending.getBarracks().getHealth() + " HP");

        System.out.println("2. GoldMine - "
                + defending.getGoldMine().getHealth() + " HP");

        System.out.println("3. DefenseTower - "
                + defending.getDefenseTower().getHealth() + " HP");

        System.out.println("4. TownHall - "
                + defending.getTownHall().getHealth() + " HP");

        System.out.print("Choose enemy building: ");

        Buildings target;

        switch (scanner.nextLine()) {
            case "1":
                target = defending.getBarracks();
                break;

            case "2":
                target = defending.getGoldMine();
                break;

            case "3":
                target = defending.getDefenseTower();
                break;

            case "4":
                target = defending.getTownHall();
                break;

            default:
                System.out.println("Invalid building!");
                return;
        }

        if (target.isDestroyed()) {
            System.out.println("Building already destroyed!");
            return;
        }

        int before = target.getHealth();

        combat.performAttack(attacker, target);

        System.out.println("Damage: "
                + (before - target.getHealth()));

        System.out.println("Building HP: " + target.getHealth());

        if (target.isDestroyed()) {
            System.out.println("Building destroyed!");
        }

        Player winner = game.getWinner();

        if (winner != null) {

            winner.recordWin();

            if (winner == player1) {
                player2.recordLoss();
            } else {
                player1.recordLoss();
            }

            System.out.println("\nGAME OVER!");
            System.out.println(winner.getName() + " wins!");

            boolean saved1 = SaveManager.savePlayer(
                    player1, "player1.txt"
            );

            boolean saved2 = SaveManager.savePlayer(
                    player2, "player2.txt"
            );

            if (saved1 && saved2) {
                System.out.println("Player results saved.");
            } else {
                System.out.println("Could not save all player results.");
            }
        }
    }

    public void showMap() {
        System.out.println("\n========== GAME MAP ==========");

        for (int row = 0; row < gameMap.getSize(); row++) {

            for (int col = 0; col < gameMap.getSize(); col++) {

                System.out.print("[ "
                        + gameMap.getCell(row, col) + " ] ");
            }

            System.out.println();
        }

        System.out.println("1 = " + player1.getName() + "'s Base");
        System.out.println("2 = " + player2.getName() + "'s Base");
        System.out.println("X = Battlefield");
        System.out.println(". = Empty Land");
    }

    public void showStatistics() {
        System.out.println("\n========== STATISTICS ==========");

        System.out.println(player1.getName()
                + " - Games: " + player1.getGamesPlayed()
                + ", Wins: " + player1.getWins()
                + ", Losses: " + player1.getLosses());

        System.out.println(player2.getName()
                + " - Games: " + player2.getGamesPlayed()
                + ", Wins: " + player2.getWins()
                + ", Losses: " + player2.getLosses());

        System.out.println("\nCompleted army battles: "
                + counterSystem.getBattlesPlayed());

        System.out.println(player1.getName()
                + " - Battle Wins: " + counterSystem.getPlayer1Wins()
                + ", Battle Losses: " + counterSystem.getPlayer1Losses()
                + ", Survivors: "
                + counterSystem.getSurvivingUnits(player1.getArmy()));

        System.out.println(player2.getName()
                + " - Battle Wins: " + counterSystem.getPlayer2Wins()
                + ", Battle Losses: " + counterSystem.getPlayer2Losses()
                + ", Survivors: "
                + counterSystem.getSurvivingUnits(player2.getArmy()));
    }

    public String saveFile(Player player) {
        if (player == player1) {
            return "player1.txt";
        }

        return "player2.txt";
    }

    public void savePlayer() {
        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        boolean saved = SaveManager.savePlayer(
                player, saveFile(player)
        );

        if (saved) {
            System.out.println("Player saved!");
        } else {
            System.out.println("Could not save player.");
        }
    }

    public void loadPlayer() {
        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        Player loaded = LoadManager.loadPlayer(saveFile(player));

        if (loaded == null) {
            System.out.println("Save file missing or invalid.");
            return;
        }

        if (player == player1) {
            player1 = loaded;
        } else {
            player2 = loaded;
        }

        game = new Game(player1, player2);
        counterSystem = new CounterSystem();

        System.out.println("Player loaded!");
        System.out.println("Army and buildings start fresh.");
    }

    public void deleteSave() {
        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        boolean deleted = SaveManager.deleteSave(
                saveFile(player)
        );

        if (deleted) {
            System.out.println("Save deleted!");
        } else {
            System.out.println("No save deleted.");
        }
    }

    public void startMenu() {
        String choice = "";

        while (!choice.equals("12") && game.getWinner() == null) {

            showMainMenu();

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showPlayerInformation(player1);
                    showPlayerInformation(player2);
                    break;

                case "2":
                    recruitUnits();
                    break;

                case "3":
                    collectGold();
                    break;

                case "4":
                    startBattle();
                    break;

                case "5":
                    showBuildings(player1);
                    showBuildings(player2);
                    break;

                case "6":
                    attackBuilding();
                    break;

                case "7":
                    showMap();
                    break;

                case "8":
                    showStatistics();
                    break;

                case "9":
                    savePlayer();
                    break;

                case "10":
                    loadPlayer();
                    break;

                case "11":
                    deleteSave();
                    break;

                case "12":
                    System.out.println("Exiting Game...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static Civilization chooseCivilization(String name) {
        while (true) {
            System.out.println("\n" + name
                    + ", choose your civilization:");

            System.out.println("1. Romans");
            System.out.println("2. Vikings");
            System.out.println("3. Egyptians");
            System.out.print("Choose: ");

            switch (scanner.nextLine()) {
                case "1":
                    return new Civilization("Romans");

                case "2":
                    return new Civilization("Vikings");

                case "3":
                    return new Civilization("Egyptians");

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("========== NEW GAME ==========");

        System.out.print("Enter Player 1 name: ");
        String name1 = scanner.nextLine().trim();

        if (name1.isEmpty()) {
            name1 = "Player 1";
        }

        Civilization civilization1 = chooseCivilization(name1);

        System.out.print("\nEnter Player 2 name: ");
        String name2 = scanner.nextLine().trim();

        if (name2.isEmpty()) {
            name2 = "Player 2";
        }

        Civilization civilization2 = chooseCivilization(name2);

        Player player1 = new Player(
                1, name1, 500, civilization1
        );

        Player player2 = new Player(
                2, name2, 500, civilization2
        );

        System.out.println("\n========== GAME STARTED ==========");

        System.out.println(name1 + " - "
                + civilization1.getName());

        System.out.println(name2 + " - "
                + civilization2.getName());

        System.out.println("Starting Gold: 500 each");

        Console ui = new Console(player1, player2);
        ui.startMenu();
    }
}
