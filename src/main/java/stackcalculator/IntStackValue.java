package stackcalculator;

import java.util.Objects;

public class IntStackValue implements StackValue {
    Integer value;
    @Override
    public int getElementCount(int i) {
        return 0;
    }

    @Override
    public Integer apply(Integer[] integers) {
        return null;
    }
    @Override
    public Integer getValue() {
        return value;
    }

    public IntStackValue(int val){
        value = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntStackValue)) return false;
        IntStackValue that = (IntStackValue) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
