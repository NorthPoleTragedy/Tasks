package stackcalculator;

public class UnarSameNumber extends UnarStackOperation {
    @Override
    public int getElementCount(int i) {
        return 1;
    }

    @Override
    public Integer apply(Integer[] integers) {
        return integers[0];
    }
}
