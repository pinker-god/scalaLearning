import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Function;

/**
 * @author pinker on 2018/1/9
 */
@AllArgsConstructor
public class LoggerImpl implements MyLogger {
    Function<String, String> function;

    @Override
    public void log(String msg) {
        printMsg(msg, function);
    }

    public void printMsg(String msg, Function<String, String> action) {
        System.out.println(action.apply(msg));
    }
}
