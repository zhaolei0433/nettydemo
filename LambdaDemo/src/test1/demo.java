package test1;

/**
 * @author zhaolei
 * Create: 2019/2/28 14:20
 * Modified By:
 * Description:
 */
public class demo {

    private String a;

    private static String c;


    public demo(String a) {
        this.a = a;
    }

    public static demo getDidGenerateDB() {
        return new demo("hahahh");
    }

    public String hello(){
        return c;
    }
}
