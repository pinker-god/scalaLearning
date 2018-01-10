import java.util.Date;
import java.util.function.Function;

/**
 * @author pinker on 2018/1/9
 */
public class LoggerDemo {
    public static void main(String[] args) {
        String msg = "Insufficient funds";
        Function<String, String> shortFunction;
        Function<String, String> timeStampFunction;
        if (msg.length() < 15) {
            shortFunction = (str) -> {
                System.out.println("我被调用了,我在ShortLogger里");
                return str;
            };
        } else {
            shortFunction = str -> {
                System.out.println("我被调用了,我在ShortLogger里");
                return str.substring(0, str.length() - 3) + "...";
            };
        }
        timeStampFunction = str -> {
            System.out.println("我被调用了,我在TimestampLogger里");
            return new Date() + "\t" + str;
        };
        MyLogger logger=new LoggerImpl(timeStampFunction);
    }
}
