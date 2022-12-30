package martinez1337.ViewHandlers;

public class ConsoleInfoOutput {
    private static final String LABEL = "---------File Concatenator---------";
    private static final String DIRECTORY_PATH_REQUEST = "Enter a filepath to some directory:\n--> ";
    private static final String RETRY_REQUEST = "Filepath leads to invalid directory! " +
            "Please retry input or press ENTER to quit...\n--> ";
    private static final String NO_ACCESS_MESSAGE = "No access to this file/directory!";
    private static final String ERROR_MESSAGE = "Program execution failed with an error!!";
    private static final String SUCCESS_MESSAGE = "SUCCESS!!";
    private static final String EMPTY_DIRECTORY_MESSAGE = "This directory is empty!";

    /**
     * Печать лого.
     */
    public static void printLabel() {
        System.out.println(LABEL);
    }

    /**
     * Печать запроса ввода пути до директории.
     */
    public static void printDirectoryPathRequest() {
        System.out.print(DIRECTORY_PATH_REQUEST);
    }

    /**
     * Печать запроса повтора.
     */
    public static void printRetryRequest() {
        System.out.print(RETRY_REQUEST);
    }

    /**
     * Печать сообщения об отсутствии доступа к файлу
     */
    public static void printNoAccessMessage() {
        System.out.println(NO_ACCESS_MESSAGE);
    }

    /**
     * Печать сообщения о пустой папке.
     */
    public static void printEmptyDirectoryMessage() {
        System.out.println(EMPTY_DIRECTORY_MESSAGE);
    }

    /**
     * Печать сообщения об ошибке.
     */
    public static void printErrorMessage() {
        System.out.println(ERROR_MESSAGE);
    }

    /**
     * Печать произвольной строки в консоль.
     * @param line Строка.
     */
    public static void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Печать сообщения об успехе.
     */
    public static void printSuccessMessage() {
        System.out.println(SUCCESS_MESSAGE);
    }
}
