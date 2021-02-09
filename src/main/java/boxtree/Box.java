package boxtree;

import java.util.ArrayList;

public class Box implements BoxTreeObject {
    private int capacity;
    private final int maxCapacity;
    private int nestedCapacity;
    private final ArrayList<BoxTreeObject> nestedElem = new ArrayList<>();
    private final double boxWeight;
    private BoxCondition boxCondition;
    private Box insideOf;
    private double totalWeight;

    //constructor
    public Box(int capacity, BoxCondition boxCondition) {
        this.boxCondition = boxCondition;
        maxCapacity = capacity;
        if (boxCondition == BoxCondition.NOT_COLLECTED){
            capacity = 0;
        } else{
        this.capacity = maxCapacity;}

        double SPECIFIC_WEIGHT = 0.003;
        boxWeight = 6 * SPECIFIC_WEIGHT * Math.pow(capacity, 2 / 3);


    }


    public ArrayList<BoxTreeObject> getNestedElem() {
        return nestedElem;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Box(");

        for(int i = 0; i <= nestedElem.size(); i++){
            if (nestedElem.get(i).whatIsObject().equals("Collected Box")){
                str.append(nestedElem.get(i).toString());
            } else if (nestedElem.get(i).whatIsObject().equals("bottle")){
                str.append(nestedElem.get(i).toString());
            } else if (nestedElem.get(i).whatIsObject().equals("Sealed Box")){
                str.append("Sealed Box");
            } else if (nestedElem.get(i).whatIsObject().equals("Not Collected Box")){
                str.append("Not Collected Box");
                for(; i <= nestedElem.size() - 1; str.append(", "));
            }
            str.append(")");
        }
        return str.toString();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getWeight() {
        if (whatIsObject().equals("Not Collected")){
            return boxWeight;
        }
        double totalWeight = boxWeight;
        calculateWeight(totalWeight);
        return totalWeight;
    }

    private double calculateWeight(double totalWeight){
        
        if (nestedElem.size() >= 1){
            while(nestedElem.listIterator().hasNext()){
                BoxTreeObject obj = nestedElem.listIterator().next();
                switch (obj.whatIsObject()) {
                    case "Bottle",
                            "Not Collected Box" -> totalWeight += obj.getWeight();
                    case "Collected Box",
                            "Sealed Box" ->{
                        Box box = (Box) obj;
                        totalWeight += box.calculateWeight(totalWeight);
                    }
                }
            }
        }
        return totalWeight;
    }


    public double getBoxWeight() {
        return boxWeight;
    }

    @Override
    public boolean isBox() {
        return true;
    }

    @Override
    public String whatIsObject() {
        String s = switch (boxCondition) {
            case COLLECTED -> "Collected Box";
            case SEALED -> "Sealed Box";
            case NOT_COLLECTED -> "Not Collected Box";
        };
        return s;
    }

    public int getNumberOfElements(Box box){
        return nestedElem.size();
    }


    public BoxCondition getBoxCondition() {
        return boxCondition;
    }

    private static Boolean BoxIsExistAndCollected(Box box) {
        if (box == null) {
            throw new NullPointerException();
        }
        return switch (box.boxCondition) {
            case COLLECTED -> true;
            case SEALED, NOT_COLLECTED -> false;
        };
    }

    public static ArrayList<Bottle> recursiveSearchBottle(Bottle bottle, Box box) {
        ArrayList<Bottle> bottles = new ArrayList<>();
        findBottle(bottle, box, bottles);
        return bottles;

    }

    private static void findBottle(Bottle bottle, Box box, ArrayList<Bottle> bottles) {
        if (!BoxIsExistAndCollected(box)) {
            return;
        }
        while (box.getNestedElem().listIterator().hasNext()) {
            BoxTreeObject boxTreeObject = box.getNestedElem().listIterator().next();
            if (bottle.getName().equals(boxTreeObject.toString())) {
                bottles.add((Bottle) boxTreeObject);
            } else if (boxTreeObject.isBox()) {
                Box box1 = (Box) boxTreeObject;
                findBottle(bottle, box1, bottles);
            }
        }
    }

    public static int recursivelySearchNumberOfElements(Box box) {
        int numberOfItems = 1;
        searchNumber(box, numberOfItems);
        return numberOfItems;
    }

    private static void searchNumber(Box box, int numberOfItems) {
        if (!BoxIsExistAndCollected(box)) {
            return;
        }
        numberOfItems += box.nestedElem.size();
        while (box.getNestedElem().listIterator().hasNext()) {
            BoxTreeObject boxTreeObject = box.getNestedElem().listIterator().next();
            if (boxTreeObject.isBox()) {
                Box box1 = (Box) boxTreeObject;
                searchNumber(box1, numberOfItems);
            }
        }
    }

    public static BoxTreeObject recursivelySearchHeaviestObject(Box box) {
        Box obj = new Box(0, BoxCondition.SEALED);
        searchHeaviestObject(box, obj);
        return obj;
    }

    public static void searchHeaviestObject(Box box, BoxTreeObject obj) {
        if (!BoxIsExistAndCollected(box)) {
            return ;
        }
        while (box.getNestedElem().listIterator().hasNext()) {
            BoxTreeObject boxTreeObject = box.getNestedElem().listIterator().next();
            if ((boxTreeObject.getWeight() > obj.getWeight()) &&
                (!(boxTreeObject.whatIsObject().equals("Collected Box")) ||
                boxTreeObject.whatIsObject().equals("Bottle"))){
                obj = boxTreeObject;
            }
            else if (boxTreeObject.isBox()) {
                Box box1 = (Box) boxTreeObject;
                searchHeaviestObject(box1, obj);
            }
        }

    }


    @Override
    public Box clone() {
        if (whatIsObject().equals("Not Collected Box")){
        return new Box(getCapacity(), getBoxCondition()); }
        Box box = new Box(getCapacity(), getBoxCondition());
        while(nestedElem.listIterator().hasNext()){
            BoxTreeObject BTO = nestedElem.listIterator().next();
            switch (BTO.whatIsObject()){
                case "Not Collected Box","Bottle": box.nestedElem.add(BTO);
                break;
                case "Sealed Box", "Collected Box": box.nestedElem.add(BTO.clone());
            }

        }
        return box;
    }

    private boolean putInBox(BoxTreeObject BTO){
        if (BTO == null){
            throw new NullPointerException();
        }
        else if (capacity < capacity + BTO.getCapacity()){
            return false;
        } else if (boxCondition.equals(BoxCondition.SEALED)){
            return false;
        }
        nestedElem.add(BTO);
        return true;
    }

    private boolean removeFromBox(BoxTreeObject BTO){
        if (BTO == null){
            throw new NullPointerException();
        } else if (nestedElem.size() < 1){
            return false;
        }
        while (nestedElem.listIterator().hasNext()){
            BoxTreeObject BTOPresent = nestedElem.listIterator().next();
            if (BTOPresent.equals(BTO)){
                nestedElem.remove(BTO);
                return true;
            }
        }
        return false;
    }

    private boolean sealTheBox(){
        if (whatIsObject().equals("Sealed Box") &&
                whatIsObject().equals("Not Collected Box")){
            return  false;
        }
        boxCondition = BoxCondition.SEALED;
        return true;
    }

    private boolean unsealTheBox(){
        if (whatIsObject().equals("Collected Box") &&
                whatIsObject().equals("Not Collected Box")){
            return  false;
        }
        boxCondition = BoxCondition.COLLECTED;
        return true;
    }

    private boolean disassembleTheBox(){
        if (whatIsObject().equals("Not Collected Box")){
            return  false;
        } else if (nestedElem.size() > 0){
            return false;
        }
        boxCondition = BoxCondition.COLLECTED;
        return true;
    }

    private boolean collectTheBox(){
        if(whatIsObject().equals("Collected Box") &&
                whatIsObject().equals("Sealed Box")){
            return false;
        }
        boxCondition = BoxCondition.COLLECTED;
        return true;
    }
}
