package martinez1337;

import martinez1337.FileHandlers.*;
import martinez1337.ViewHandlers.*;

import java.io.File;
import java.io.IOException;

public class MasterLogicService {
    /**
     * Метод главного исполняемого процесса программы.
     */
    public void mainProcess() {
        ConsoleInfoOutput.printLabel();

        String directoryPath = getDirectoryPath();

        doConcatenation(directoryPath);
    }

    /**
     * Метод получения от пользователя пути до директории.
     * @return Путь до директории.
     */
    private String getDirectoryPath() {
        ConsoleInfoOutput.printDirectoryPathRequest();

        String path;
        File file;

        do {
            path = ConsoleUserInput.readFilePath();
            if (path.isEmpty()) {
                break;
            }

            file = new File(path);
            try {
                if (!file.isDirectory()) {
                    ConsoleInfoOutput.printRetryRequest();
                }
            } catch (SecurityException e) {
                ConsoleInfoOutput.printNoAccessMessage();
                ConsoleInfoOutput.printRetryRequest();
            }
        } while (!file.isDirectory());

        return path;
    }

    /**
     * Метод конкатенации файлов.
     * @param directoryPath Путь до директории с файлами.
     */
    private void doConcatenation(String directoryPath) {
        if (directoryPath.isEmpty()) {
            ConsoleInfoOutput.printEmptyDirectoryMessage();
            return;
        }

        try {
            var txtFilesConcatenator = new TxtFilesConcatenator(directoryPath);
            txtFilesConcatenator.concatenateFiles();
            ConsoleInfoOutput.printSuccessMessage();
        } catch (IOException | RuntimeException e) {
            ConsoleInfoOutput.printLine(e.getMessage());
            ConsoleInfoOutput.printErrorMessage();
        }
    }
}
