package boxtree;

import java.util.ArrayList;

import static boxtree.Box.BoxIsExistAndCollected;

public class BoxUtils {
    public static Box moveBottlesToBox(Box box){

        ArrayList<Bottle> bottles = new ArrayList<>();
        int capacity = 0;
        recursiveMoveBottleToBox(box, bottles);
        while(bottles.listIterator().hasNext()){
            capacity += bottles.listIterator().next().getCapacity();
        }
        Box newBox = new Box(capacity,BoxCondition.COLLECTED);
        newBox.putInBox((BoxTreeObject) bottles);

        return newBox;
    }
    private static void recursiveMoveBottleToBox(Box box, ArrayList bottles){
        BoxTreeObject BTO;
        while (box.getNestedElem().listIterator().hasNext()){
            BTO = box.getNestedElem().listIterator().next();
            switch(BTO.whatIsObject()){
                case "Bottle" : {bottles.add(BTO);
                                box.getNestedElem().remove(BTO);break;}
                case "Collected Box", "Sealed Box" : Box box1 = (Box) BTO;
                    recursiveMoveBottleToBox(box1, bottles); break;
                case "Not Collected Box" : break;
            }

        }

    }
    public static Box cloneWithoutSpecificBottles(Box box, String bottleName){
        if(box == null && bottleName == null){
            throw new NullPointerException();
        }

        Box newBox = box.clone();
        recursiveRemoveBottles(newBox, bottleName);
        return newBox;

    }

    private static void recursiveRemoveBottles(Box box, String bottleName){
        BoxTreeObject BTO;
        while (box.getNestedElem().listIterator().hasNext()){
            BTO = box.getNestedElem().listIterator().next();
            switch(BTO.whatIsObject()){
                case "Collected Box","Sealed Box": Box box1 = (Box) BTO;
                                                    recursiveRemoveBottles(box1, bottleName);
                                                    break;
                case "Not Collected Box": break;
                case "Bottle": if(BTO.toString().equals(bottleName)){
                    box.removeFromBox(BTO);
                    break;}
                    break;
            }

        }
    }
}
