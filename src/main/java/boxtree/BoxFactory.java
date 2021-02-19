package boxtree;

import java.util.ArrayList;

public class BoxFactory extends AbstractFabric {

    @Override
    public Box makeNode(int capacity) {
        return new Box(capacity, BoxCondition.COLLECTED);
    }

    public Box makeHierarchy(int capacity, int nestingLevels){
        BoxFactory boxFactory = new BoxFactory();
        Box box = boxFactory.makeNode(capacity) ;
        int count = 0;
        boxFactory.makeHierarchyRecursive(box, capacity, nestingLevels);
        return box;

    }

    private void makeHierarchyRecursive(Box box, int capacity, int nestingLevels){
        int howManyObjInside = (int) ((Math.random() * (10 - 1)) + 1);
        int percentOfFullness = (int) ((Math.random() * (100 -1)) +1);
        int realFullness = capacity * percentOfFullness / 100;
        int capacityOfNestedObj = 0;
        if (nestingLevels <= 0){
            return;
        }
        BoxFactory boxFactory = new BoxFactory();
        while ((realFullness > 1) && (box.getNestedElem().size() < howManyObjInside)){
            capacityOfNestedObj =(realFullness *
                    ((int) (Math.random() * (percentOfFullness - 1)) + 1)) / 100;
            realFullness -= capacityOfNestedObj;
            Box box1 = boxFactory.makeNode(capacityOfNestedObj);
            makeHierarchyRecursive(box1, capacityOfNestedObj, nestingLevels);
            box.putInBox(box1);
        }

    }
    private void makeHierarchyRecursive(Box box, int capacity, int nestingLevels, Box parentsBox){
        int howManyObjInside = (int) ((Math.random() * (10 - 1)) + 1);
        int percentOfFullness = (int) ((Math.random() * (100 -1)) +1);
        int realFullness = capacity * percentOfFullness / 100;
        int capacityOfNestedObj = 0;
        if (box.getNestedElem().size() == 0){
            nestingLevels -= 1;
        }
        if (nestingLevels <= 0){
            return;
        }
        BoxFactory boxFactory = new BoxFactory();
        while ((realFullness > 1) && (box.getNestedElem().size() < howManyObjInside)){
            capacityOfNestedObj =(realFullness *
                    ((int) (Math.random() * (percentOfFullness - 1)) + 1)) / 100;
            realFullness -= capacityOfNestedObj;
            Box box1 = boxFactory.makeNode(capacityOfNestedObj);
            makeHierarchyRecursive(box1, capacityOfNestedObj, nestingLevels, box);
            box.putInBox(box1);
        }



}
}
