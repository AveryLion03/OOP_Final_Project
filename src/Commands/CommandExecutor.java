package Commands;

public class CommandExecutor implements CommandVisitor {
    @Override
    public void visitManagerCommand(String command) {
        // Execute manager commands
        System.out.println("Executing manager command: " + command);
    }

    @Override
    public void visitCustomerCommand(String command) {
        // Execute customer commands
        System.out.println("Executing customer command: " + command);
    }

    @Override
    public void visitCourierCommand(String command) {
        // Execute courier commands
        System.out.println("Executing courier command: " + command);
    }

    @Override
    public void visitRestaurantCommand(String command) {
        // Execute restaurant commands
        System.out.println("Executing restaurant command: " + command);
    }

    @Override
    public void visitAnyoneCommand(String command) {
        // Execute commands for anyone
        System.out.println("Executing command for anyone: " + command);
    }
}
