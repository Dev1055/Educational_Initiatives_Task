// Target interface
interface NewLoggingSystem {
    void log(String message);
}

// Adaptee class (old logging system)
class LegacyLogger {
    public void writeToLogFile(String message) {
        System.out.println("Legacy Logger: " + message);
    }
}

// Adapter class
class LoggerAdapter implements NewLoggingSystem {
    private LegacyLogger legacyLogger;

    public LoggerAdapter(LegacyLogger legacyLogger) {
        this.legacyLogger = legacyLogger;
    }

    @Override
    public void log(String message) {
        legacyLogger.writeToLogFile(message);
    }
}

// Test Adapter Pattern
public class AdapterPatternDemo {
    public static void main(String[] args) {
        LegacyLogger legacyLogger = new LegacyLogger();
        NewLoggingSystem logger = new LoggerAdapter(legacyLogger);
        logger.log("Logging with Adapter.");
    }
}
