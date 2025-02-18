public class Main {
    public static void main(String[] args) {
        // Create rooms
        Room room1 = new Room("Room 1", "A small stone chamber.");
        Room room2 = new Room("Room 2", "A dark cave with a damp smell.");

        // Create items
        Item sword = new Item("sword");
        room1.addItem(sword);

        // Create player and set starting room
        Player player = new Player("Hero", room1);

        // Connect rooms
        room1.connectRoom("forward", room2);

        // Start the MUD Controller
        MUDController controller = new MUDController(player);
        controller.runGameLoop(); // Start the game loop
    }
}