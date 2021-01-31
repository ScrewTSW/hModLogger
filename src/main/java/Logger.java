public class Logger {

    private static LogLevel LOG_LEVEL = LogLevel.INFO;
    private static String pluginName;
    private static Plugin parent = null;
    private static boolean initialized = false;

    public static void init(LogLevel logLevel, Plugin plugin) {
        if (!Logger.initialized) {
            if (plugin != null) {
                Logger.parent = plugin;
                Logger.pluginName = "[" + Logger.parent.getName() + "]";
            } else {
                Logger.pluginName = "[Logger]";
            }
            if (logLevel != null) Logger.LOG_LEVEL = logLevel;
            Logger.initialized = true;
        }
    }

    public static void init(LogLevel logLevel) {
        init(logLevel, null);
    }

    public static void init() {
        init(LogLevel.INFO);
    }

    public static void error(String str) {
        if (!Logger.initialized) Logger.init();
        if (Logger.LOG_LEVEL == LogLevel.TRACE ||
                Logger.LOG_LEVEL == LogLevel.DEBUG ||
                Logger.LOG_LEVEL == LogLevel.INFO ||
                Logger.LOG_LEVEL == LogLevel.WARN ||
                Logger.LOG_LEVEL == LogLevel.ERROR)
        System.out.println(Logger.pluginName + " [ERROR] " + str);
    }

    public static void warn(String str) {
        if (!Logger.initialized) Logger.init();
        if (Logger.LOG_LEVEL == LogLevel.TRACE ||
                Logger.LOG_LEVEL == LogLevel.DEBUG ||
                Logger.LOG_LEVEL == LogLevel.INFO ||
                Logger.LOG_LEVEL == LogLevel.WARN)
        System.out.println(Logger.pluginName + " [WARN] " + str);
    }

    public static void info(String str) {
        if (!Logger.initialized) Logger.init();
        if (Logger.LOG_LEVEL == LogLevel.TRACE ||
                Logger.LOG_LEVEL == LogLevel.DEBUG ||
                Logger.LOG_LEVEL == LogLevel.INFO)
        System.out.println(Logger.pluginName + " [INFO] " + str);
    }

    public static void debug(String str) {
        if (!Logger.initialized) Logger.init();
        if (Logger.LOG_LEVEL == LogLevel.TRACE ||
                Logger.LOG_LEVEL == LogLevel.DEBUG)
        System.out.println(Logger.pluginName + " [DEBUG] " + str);
    }

    public static void trace(String str) {
        if (!Logger.initialized) Logger.init();
        if (Logger.LOG_LEVEL == LogLevel.TRACE)
        System.out.println(Logger.pluginName + " [TRACE] " + str);
    }

    public static LogLevel getLogLevel() {
        return Logger.LOG_LEVEL;
    }

    public enum LogLevel{
        ERROR("ERROR"),
        WARN("WARN"),
        INFO("INFO"),
        DEBUG("DEBUG"),
        TRACE("TRACE");

        private final String level;

        LogLevel(String level) {
            this.level = level;
        }

        public static LogLevel parse(String level) {
            switch(level) {
                case "ERROR": return ERROR;
                case "WARN": return WARN;
                case "INFO": return INFO;
                case "DEBUG": return DEBUG;
                case "TRACE": return TRACE;
                default: return DEBUG;
            }
        }

        public String getValue() {
            return this.level;
        }
    }
}
