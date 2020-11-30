package stackcalculator;

public class UnarSquare extends UnarStackOperation {

    @Override
    public Integer apply(Integer[] integer) {
        Integer x = integer[0];
        return x * x;
    }

    @Override
    public int getElementCount(int i) {
        return 1;
    }


}
