package stackcalculator;

public class UnarFactorial extends UnarStackOperation {
    @Override
    public int getElementCount(int i) {
        return 1;
    }

    @Override
    public Integer apply(Integer[] integers) {
        Integer x = 1;
        for (Integer count = 1; count <= integers[0]; count++){
            x *= count;
        }
        return x;
    }
}
