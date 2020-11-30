package stackcalculator;

public class BinarBiggest extends BinarStackOperation {
    @Override
    public int getElementCount(int i) {
        return 2;
    }

    @Override
    public Integer apply(Integer[] integers) {
        if (integers[0] > integers[1]){
            return integers[0];
        } else {
            return  integers[1];
        }
    }
}
