package boxtree;

public class BottleFactory extends AbstractFabric {

    @Override
    public Bottle makeNode(int capacity) {
        return new Bottle(capacity, "name",Liquids.empty, 0);
    }
}
