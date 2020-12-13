package generics;

import java.util.ArrayList;

public class GenericExample<T> {

    private final ArrayList<T> ts;

    GenericExample(ArrayList<T> ts) {
        this.ts = ts;
    }

    void add(T element) {
        ts.add(element);
    }

    T get(int index) {
        return ts.get(index);
    }


    // static methods
    static <T> T getFirst(GenericExample<T> g) {
        return g.get(0);
    }

    static <T> GenericExample<T> newGenericExample(ArrayList<T> ts) {
        return new GenericExample<>(ts);
    }


    // main
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        GenericExample<String> g1 = new GenericExample<>(strings);

        ArrayList<Integer> ints = new ArrayList<>();
        GenericExample<Integer> g2 = newGenericExample(ints);

        g1.add("123");
        System.out.println(getFirst(g1));
    }


}
