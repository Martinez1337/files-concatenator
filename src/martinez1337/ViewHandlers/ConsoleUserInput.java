package martinez1337.ViewHandlers;

import java.util.Scanner;

public class ConsoleUserInput {
    private static final Scanner INPUT_SCANNER = new Scanner(System.in);

    /**
     * Метод чтения из консоли пути к файлу.
     * @return Путь к файлу.
     */
    public static String readFilePath() {
        return INPUT_SCANNER.nextLine().trim();
    }
}
