/**
 * @author pinker on 2018/1/9
 */
public interface MyLogger {
    void log(String msg);

    default void info(String msg) {
        log("INFO\t" + msg);
    }

    default void warn(String msg) {
        log("WARN\t" + msg);
    }

    default void err(String msg) {
        log("ERR\t" + msg);
    }
}

