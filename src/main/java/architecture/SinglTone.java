package architecture;

public class SinglTone {
    private final String abc;
    static SinglTone singlTone;

    private SinglTone(){
        singlTone = new SinglTone();
        abc = "OOO";
    }

    public static SinglTone getInstance(){
        return singlTone;
    }

    public String typeOOO(){
        return abc;
    }

}
