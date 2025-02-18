import java.util.Scanner;

/**
 * MUDController (Skeleton):
 * A simple controller that reads player input and orchestrates
 * basic commands like look around, move, pick up items,
 * check inventory, show help, etc.
 */
public class MUDController {

    private final Player player;
    private boolean running;

    /**
     * Constructs the controller with a reference to the current player.
     */
    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    /**
     * Main loop method that repeatedly reads input from the user
     * and dispatches commands until the game ends.
     */
    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
    }

    /**
     * Handle a single command input (e.g. 'look', 'move forward', 'pick up sword').
     */
    public void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                pickUp(argument);
                break;
            case "inventory":
                checkInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    /**
     * Look around the current room: describe it and show items/NPCs.
     */
    private void lookAround() {
        Room currentRoom = player.getCurrentRoom();
        System.out.println("Room: " + currentRoom.getName());
        System.out.println(currentRoom.getDescription());

        System.out.print("Items here: ");
        for (Item item : currentRoom.getItems()) {
            System.out.print(item.getName() + " ");
        }
        System.out.println();

        // You can extend to list NPCs or other entities in the room if needed
    }

    /**
     * Move the player in a given direction (forward, back, left, right).
     */
    private void move(String direction) {
        Room currentRoom = player.getCurrentRoom();

        Room nextRoom = currentRoom.getConnectedRoom(direction);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
            System.out.println("You move " + direction + ".");
            lookAround(); // Describe the new room
        } else {
            System.out.println("You can't go that way!");
        }
    }

    /**
     * Pick up an item (e.g. "pick up sword").
     */
    private void pickUp(String arg) {
        Room currentRoom = player.getCurrentRoom();
        Item item = currentRoom.getItem(arg);

        if (item != null) {
            player.addToInventory(item);
            currentRoom.removeItem(item);
            System.out.println("You pick up the " + item.getName() + ".");
        } else {
            System.out.println("No item named " + arg + " here!");
        }
    }

    /**
     * Check the player's inventory.
     */
    private void checkInventory() {
        if (player.getInventory().isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("You are carrying:");
            for (Item item : player.getInventory()) {
                System.out.println("- " + item.getName());
            }
        }
    }

    /**
     * Show help commands
     */
    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Look around the current room.");
        System.out.println("move <forward|back|left|right> - Move in a specified direction.");
        System.out.println("pick up <itemName> - Pick up an item from the room.");
        System.out.println("inventory - Check your inventory.");
        System.out.println("help - Show this help message.");
        System.out.println("quit/exit - Exit the game.");
    }
}