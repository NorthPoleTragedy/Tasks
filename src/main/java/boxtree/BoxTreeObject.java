package boxtree;

public interface BoxTreeObject {
    int getCapacity();
    double getWeight();
    boolean isBox();
    String whatIsObject();
    BoxTreeObject clone();
}
