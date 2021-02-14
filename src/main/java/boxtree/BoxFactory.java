package boxtree;

public class BoxFactory extends AbstractFabric {

    @Override
    public Box makeNode(int capacity) {
        return new Box(capacity, BoxCondition.COLLECTED);
    }

    public Box makeHierarchy(int capacity, int nestingLevels){
        Box box = new Box(capacity, BoxCondition.COLLECTED);
        while(nestingLevels > 0){
           for (int i; i < 0;)
            box.putInBox(new Box(capacity /=2, BoxCondition.COLLECTED));

        }
    }
}
