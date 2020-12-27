package boxtree;

public class Box implements BoxTreeObject {
    private int capacity;
    private double weight;



    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
