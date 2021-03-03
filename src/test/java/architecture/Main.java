package architecture;


import java.util.Objects;

public class Main {
    private static void main(){
        SinglTone singlTone;
        if ((singlTone = SinglTone.getInstance()) == null){
            throw new NullPointerException("govno, ne rabotaet");
        }
        assert (Objects.requireNonNull(singlTone).typeOOO().equals("OOO"));
    }
}
