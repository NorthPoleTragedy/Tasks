package boxtree;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Box implements BoxTreeObject {
    private final int capacity;
    private double weight;
    private int nestedCapacity;
    private final ArrayList<BoxTreeObject> nestedElem = new ArrayList<>();
    private final float specificWeight;
    private final double boxWeight;
    private BoxCondition boxCondition;
    private Box insideOf;

    //constructor
    public Box(int capacity, float specificWeight, BoxCondition boxCondition){
        this.capacity = capacity;
        this.specificWeight = specificWeight;
        boxWeight = 6 * specificWeight * Math.pow(capacity, 2/3);
        this.boxCondition = boxCondition;

    }

    public ArrayList<BoxTreeObject> getNestedElem(){
        return nestedElem;
    }
    @Override
    public String toString(){
        return "itsBox";
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public int getNestedElements(){
        return nestedElem.size();
    }

    public BoxCondition getBoxCondition(){
        return boxCondition;
    }

    public static ArrayList<Bottle> findBottle(String name, Box box){
        ArrayList<Bottle> bottles;

    }

    private static BoxTreeObject recursiveSearchBottle(String name, Box box){
        if (box == null) {
            throw new NullPointerException();
        }
         switch (box.boxCondition){
             case SEALED:
             case NOT_COLLECTED:
                 return box;
             default: break;
         }
        while(box.getNestedElem().listIterator().hasNext()){
            if (name == box.getNestedElem().listIterator().next().toString()){
                
            }
        }


    }


}
