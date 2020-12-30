public class ExitException extends Exception {
    private int exitCode;
    public static final int WIN = 1;
    public static final int DRAW = 0;

    public ExitException(int code) {
        exitCode = code;
    }

    public int getExitCode() {
        return exitCode;
    }

}
