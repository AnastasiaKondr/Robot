package log;

public final class Logger
{
    private static final LogDelegator defaultLogSource;
    static {
        defaultLogSource = new LogDelegator(100);
    }

    private Logger()
    {
    }

    public static void debug(String strMessage)
    {
        defaultLogSource.append(LogLevel.Debug, strMessage);
    }

    public static void error(String strMessage)
    {
        defaultLogSource.append(LogLevel.Error, strMessage);
    }

    public static LogDelegator getDefaultLogSource()
    {
        return defaultLogSource;
    }
}
