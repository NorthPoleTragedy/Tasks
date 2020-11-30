package stackcalculator;

public interface StackCalculator {

    void push(StackElement stackElement) throws ElementCountIsNegativeException;

    Integer pop();
}
