package stackcalculator;

public class BinarSum extends BinarStackOperation {
    @Override
    public int getElementCount(int i) {
        return 2;
    }

    @Override
    public Integer apply(Integer[] integers) {
        return integers[0] + integers[1];
    }
}
