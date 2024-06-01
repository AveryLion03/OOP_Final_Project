package Commands;

public interface CommandVisitor {
	void visitManagerCommand(String [] command);
    void visitCustomerCommand(String [] command);
    void visitCourierCommand(String [] command);
    void visitRestaurantCommand(String [] command);
    void visitAnyoneCommand(String [] command);
}
