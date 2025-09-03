import java.util.Date;

public class Logger {
    protected int num = 1;
    private static Logger logger = null;

    private Logger() {}

    public void log(String msg) {
        Date date = new Date();
        System.out.println("[" + date + " " + num++ + "] " + msg);
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }
}