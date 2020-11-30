package stackcalculator;

import java.util.ArrayList;
import java.util.List;

public class RealStackCalculator implements StackCalculator {

    private final List<StackElement> stackElements = new ArrayList<>();

     private void putInStackElements(StackElement stackElement){
         Integer[] nums = new Integer[pop()];
         StackOperation sE = (StackOperation) stackElement;
         IntStackValue intStackValue = new IntStackValue(sE.apply(nums));
         stackElements.add(intStackValue);
    }
    private void putInStackElements(StackElement stackElement, Integer[] integers){
        StackOperation sE = (StackOperation) stackElement;
         IntStackValue intStackValue = new IntStackValue(sE.apply(integers));
         stackElements.add(intStackValue);
    }
    @Override
    public void push(StackElement stackElement) throws ElementCountIsNegativeException {
        if (stackElement == null){
            throw new NullPointerException();
        }
        if (stackElement.getElementCount(stackElements.size()) < 0){        //не уверен в смысле етого, но пущай на
            throw new ElementCountIsNegativeException();                    //всякий висит
        }
        switch (stackElement.getElementCount(stackElements.size())) {
            case 0 -> stackElements.add(stackElement);
            case 1 -> putInStackElements(stackElement);
            case 2 -> {
                Integer[] binarArray = new Integer[2];
                binarArray[1] = pop();
                binarArray[0] = pop();
                putInStackElements(stackElement, binarArray);
            }
            default -> {
                Integer[] multiArray = new Integer[stackElements.size()];
                for (int count = 0; count < stackElements.size(); count++) {
                    multiArray[count] = pop();
                }
                putInStackElements(stackElement, multiArray);
            }
        }
    }

    @Override
    public Integer pop() {
        StackValue sE = (StackValue) stackElements.remove(stackElements.size() - 1);
        return sE.getValue() ;
    }
}
