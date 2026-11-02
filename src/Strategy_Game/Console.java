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
    private int nextUnitID = 1;

    private Scanner scanner = new Scanner(System.in);

    public Console(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.game = new Game(player1, player2);
    }

    public void showMainMenu() {

        System.out.println("\n========== STRATEGY GAME ==========");
        System.out.println("1. View Player Information");
        System.out.println("2. Recruit Units");
        System.out.println("3. Collect Gold");
        System.out.println("4. Start Battle");
        System.out.println("5. View Buildings");
        System.out.println("6. Attack Building");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }

    public void showPlayerInformation(Player player) {

        System.out.println("\n========== PLAYER INFORMATION ==========");
        System.out.println("Name: " + player.getName());
        System.out.println("Civilization: " + player.getCivilization().getName());
        System.out.println("Gold: " + player.getGold());
        System.out.println("Army Size: " + player.getArmy().getArmySize());
    }

    public Player choosePlayer() {

        System.out.println("1. Player 1");
        System.out.println("2. Player 2");
        System.out.print("Choose player: ");

        String choice = scanner.nextLine();

        if (choice.equals("1")) {
            return player1;
        }
        else if (choice.equals("2")) {
            return player2;
        }

        System.out.println("Invalid player!");
        return null;
    }

    public void recruitUnits() {

        System.out.println("\n========== RECRUIT UNITS ==========");

        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        System.out.println("Gold: " + player.getGold());

        System.out.println("\n1. Archer - 100 Gold");
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

        String choice = scanner.nextLine();

        Class<? extends Unit> unitType;

        switch (choice) {

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
                player, unitType, nextUnitID);

        if (recruited) {
            nextUnitID++;

            System.out.println("Unit recruited successfully!");
            System.out.println("Remaining Gold: " + player.getGold());
            System.out.println("Army Size: " + player.getArmy().getArmySize());
        }
        else {
            System.out.println("Recruitment failed!");
        }
    }

    public void collectGold() {

        System.out.println("\n========== COLLECT GOLD ==========");

        Player player = choosePlayer();

        if (player == null) {
            return;
        }

        int goldBefore = player.getGold();

        player.getGoldMine().collectGold(player);

        int goldCollected = player.getGold() - goldBefore;

        System.out.println("Gold Collected: " + goldCollected);
        System.out.println("Total Gold: " + player.getGold());
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

            }
            catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
                continue;
            }

            System.out.println("Invalid unit!");
        }
    }

    public void startBattle() {

        Army army1 = player1.getArmy();
        Army army2 = player2.getArmy();

        if (army1.isDefeated() || army2.isDefeated()) {
            System.out.println("Both players need soldiers!");
            return;
        }

        Battle battle = new Battle(army1, army2);

        System.out.println("\n========== BATTLE STARTED ==========");

        while (battle.getWinner() == null) {

            Player attackerPlayer;
            Player defenderPlayer;

            if (battle.getCurrentTurn() == 1) {
                attackerPlayer = player1;
                defenderPlayer = player2;
            }
            else {
                attackerPlayer = player2;
                defenderPlayer = player1;
            }

            System.out.println("\n" + attackerPlayer.getName() + "'s Turn");

            System.out.println("Choose attacker:");
            Unit attacker = selectUnit(attackerPlayer.getArmy());

            if (attacker == null) {
                System.out.println("Battle cancelled!");
                return;
            }

            System.out.println("Choose target:");
            Unit target = selectUnit(defenderPlayer.getArmy());

            if (target == null) {
                System.out.println("Battle cancelled!");
                return;
            }

            boolean useCharge = false;

            if (attacker instanceof Chariot) {

                System.out.print("Use Charge? (y/n): ");

                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    useCharge = true;
                }
            }

            int oldHealth = target.getHealth();

            boolean success = battle.takeTurn(attacker, target, useCharge);

            if (success) {

                System.out.println("Damage: "
                        + (oldHealth - target.getHealth()));

                System.out.println("Enemy HP: " + target.getHealth());

                if (target.isDestroyed()) {
                    System.out.println("Enemy unit destroyed!");
                }
            }
            else {
                System.out.println("Attack failed!");
            }
        }

        Army winner = battle.getWinner();

        if (winner == army1) {
            System.out.println("\n" + player1.getName() + " wins the battle!");
        }
        else {
            System.out.println("\n" + player2.getName() + " wins the battle!");
        }
    }

    public void showBuildings(Player player) {

        System.out.println("\n========== " + player.getName() + "'s BUILDINGS ==========");

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

        Player attackingPlayer = choosePlayer();

        if (attackingPlayer == null) {
            return;
        }

        Player defendingPlayer;

        if (attackingPlayer == player1) {
            defendingPlayer = player2;
        }
        else {
            defendingPlayer = player1;
        }

        if (attackingPlayer.getArmy().isDefeated()) {
            System.out.println("You have no living soldiers!");
            return;
        }

        System.out.println("\nChoose your attacking unit:");

        Unit attacker = selectUnit(attackingPlayer.getArmy());

        if (attacker == null) {
            return;
        }

        System.out.println("\n========== ENEMY BUILDINGS ==========");

        System.out.println("1. Barracks - "
                + defendingPlayer.getBarracks().getHealth() + " HP");

        System.out.println("2. GoldMine - "
                + defendingPlayer.getGoldMine().getHealth() + " HP");

        System.out.println("3. DefenseTower - "
                + defendingPlayer.getDefenseTower().getHealth() + " HP");

        System.out.println("4. TownHall - "
                + defendingPlayer.getTownHall().getHealth() + " HP");

        System.out.print("Choose building: ");

        String choice = scanner.nextLine();

        Buildings target;

        switch (choice) {

            case "1":
                target = defendingPlayer.getBarracks();
                break;

            case "2":
                target = defendingPlayer.getGoldMine();
                break;

            case "3":
                target = defendingPlayer.getDefenseTower();
                break;

            case "4":
                target = defendingPlayer.getTownHall();
                break;

            default:
                System.out.println("Invalid building!");
                return;
        }

        if (target.isDestroyed()) {
            System.out.println("Building already destroyed!");
            return;
        }

        int oldHealth = target.getHealth();

        CombatSystem combat = new CombatSystem();

        combat.performAttack(attacker, target);

        int damage = oldHealth - target.getHealth();

        System.out.println("\nAttack successful!");
        System.out.println("Damage dealt: " + damage);
        System.out.println("Building HP: " + target.getHealth());

        if (target.isDestroyed()) {
            System.out.println("Building destroyed!");
        }

        Player winner = game.getWinner();

        if (winner != null) {
            System.out.println("\nGAME OVER!");
            System.out.println(winner.getName() + " WINS THE GAME!");
        }
    }

    public void startMenu() {

        String choice = "";

        while (!choice.equals("7") && game.getWinner() == null) {

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
                    System.out.println("Exiting Game...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {

        Civilization romans = new Civilization("Romans");
        Civilization vikings = new Civilization("Vikings");

        Player player1 = new Player(1, "Player 1", 500, romans);
        Player player2 = new Player(2, "Player 2", 500, vikings);

        Console ui = new Console(player1, player2);

        ui.startMenu();
    }
}