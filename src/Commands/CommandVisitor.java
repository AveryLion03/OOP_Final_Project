package Commands;


/**
 * The {@code CommandVisitor} interface defines a visitor for handling different types of commands
 * in the MyFoodora system. Implementations of this interface should provide specific behavior 
 * for each type of command based on the role (Manager, Customer, Courier, Restaurant, or Anyone).
 * 
 * <p>The visitor pattern is used here to separate the operations performed on the commands from 
 * the objects themselves, promoting better organization and scalability.</p>
 * 
 * @version 1.0, 2024-06-07
 * @since 1.0
 */
public interface CommandVisitor {
    /**
     * Visit and execute a command intended for a Manager.
     * 
     * @param command the command parameters as a String array
     */
    void visitManagerCommand(String[] command);

    /**
     * Visit and execute a command intended for a Customer.
     * 
     * @param command the command parameters as a String array
     */
    void visitCustomerCommand(String[] command);

    /**
     * Visit and execute a command intended for a Courier.
     * 
     * @param command the command parameters as a String array
     */
    void visitCourierCommand(String[] command);

    /**
     * Visit and execute a command intended for a Restaurant.
     * 
     * @param command the command parameters as a String array
     */
    void visitRestaurantCommand(String[] command);

    /**
     * Visit and execute a command intended for anyone (generic commands).
     * 
     * @param command the command parameters as a String array
     */
    void visitAnyoneCommand(String [] command);
}
