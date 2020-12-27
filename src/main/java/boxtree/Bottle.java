package boxtree;

public class Bottle implements BoxTreeObject {
    private final int  capacity;
    private double weight;
    private String name;
    private Liquids liquid;
    private double bottleWeight;
    private double CONSISTENCE;
    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public String getName(){
        return name;
    }

    public String getLiquid(){
        return liquid.toString();
    }
    private static int getRadiusOfBottle() {
        return 15;                       //радиус включая стенки(2mm) бутылки

    }
    public void SetName(String name){
        this.name = name;
    }


    // constructor
    public Bottle(int capacity, String name, Liquids liquid, int fullness){
        this.capacity = capacity;
        this.name = name;
        this.liquid = liquid;
        this.bottleWeight = 0.005 * (capacity - getEmptySpace(capacity));
        fillTheBottle(fullness, liquid);

    }
    private static double getEmptySpace(int capacity){
        double areaOfBottle = getRadiusOfBottle() * getRadiusOfBottle() * Math.PI;
        double emptyArea = (getRadiusOfBottle()-2) * (getRadiusOfBottle()) * Math.PI;
        double heightOfBottle = capacity / areaOfBottle;
        return emptyArea * heightOfBottle;
    }


    public void emptyTheBottle(){
        liquid = Liquids.empty;
        CONSISTENCE = 0;
        weight = bottleWeight;
    }
    public void fillTheBottle(int fullness, Liquids liquid){
        emptyTheBottle();
        switch (liquid) {
            case water -> CONSISTENCE = 0.997;
            case cola -> CONSISTENCE = 1.040;
            case milk -> CONSISTENCE = 1.027;
            default -> CONSISTENCE = 0; //empty
        }
        weight = bottleWeight + CONSISTENCE * (capacity *(100 - fullness));
    }


}