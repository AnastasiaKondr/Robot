package log.model;

public class LogEntry
{
    private LogLevel logLevel;
    private String message;

    public LogEntry(LogLevel logLevel, String message)
    {
        this.message = message;
        this.logLevel = logLevel;
    }

    public String getMessage()
    {
        return message;
    }

    public LogLevel getLevel()
    {
        return logLevel;
    }
}

