package stackcalculator;

import java.util.stream.IntStream;

public class MultiSum extends MultiStackOperation{
    @Override
    public int getElementCount(int i) {
        return i;
    }

    @Override
    public Integer apply(Integer[] integers) {
        Integer x = 0;
        for (int count = 0; count <= integers.length; count++){
            x += integers[count];
        }
        return x;
    }
}
