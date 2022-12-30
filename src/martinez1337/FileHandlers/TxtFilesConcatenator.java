package martinez1337.FileHandlers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TxtFilesConcatenator {
    private final String DIRECTORY_PATH;

    /**
     * Конструктор конкатенатора
     * @param path Путь до корневой директории
     */
    public TxtFilesConcatenator(String path) {
        DIRECTORY_PATH = path;
    }

    /**
     * Метод конкатенации файлов в директории.
     * @throws IOException при ошибке в чтении/записи из/в файлах.
     */
    public void concatenateFiles() throws IOException {
        List<String> dependenceOrderedFileList = getFileDependenceOrderedList();

        try (var out = new PrintWriter(new BufferedWriter(new FileWriter("result.txt")))) {
            for (String filePath : dependenceOrderedFileList) {
                var fileIterator = new FileIterator(filePath);
                fileIterator.forEachRemaining(out::println);
            }
        }
    }

    /**
     * Метод для получения списка всех txt файлов в порядке их положения в главной директории.
     * @return Список всех txt файлов в порядке положения в директории.
     * @throws IOException при возникновении ошибки при чтении файлов из директории.
     */
    private List<String> getAllTxtFilesDirOrderedList()
            throws IOException {
        try (Stream<Path> stream = Files.walk(Path.of(DIRECTORY_PATH), Integer.MAX_VALUE)) {
            return stream
                    .map(String::valueOf)
                    .sorted()
                    .filter((line) -> {
                        var file = new File(line);

                        String fileName = file.toString();
                        String fileExtension = "";

                        int index = fileName.lastIndexOf('.');
                        if (index > 0) {
                            fileExtension = fileName.substring(index + 1);
                        }

                        return !file.isDirectory() && fileExtension.equals("txt");
                    })
                    .toList();
        }
    }

    /**
     * Метод для получения списка файлов согласно их зависимостям.
     * @return Отсортированный список файлов по их зависимостям.
     */
    private List<String> getFileDependenceOrderedList() throws IOException {
        List<String> allTxtFilesList = getAllTxtFilesDirOrderedList();

        ArrayList<String> independentFilesList = findIndependentFiles(allTxtFilesList);

        ArrayList<String> mainListWithoutIndependentFiles = new ArrayList<>(allTxtFilesList);
        mainListWithoutIndependentFiles.removeAll(independentFilesList);

        ArrayList<String> resultList = new ArrayList<>();
        for (int i = 0; i < mainListWithoutIndependentFiles.size(); i++) {
            String currentFilePath = mainListWithoutIndependentFiles.get(i);
            List<String> fileDependenceList = getCurrentFileDependenceList(currentFilePath);

            if (resultList.contains(currentFilePath)) {
                int indexOfCurrentFile = resultList.indexOf(currentFilePath);

                for (int j = 0; j < fileDependenceList.size() - 1; j++) {
                    if (resultList.contains(fileDependenceList.get(i))) {
                        int indexOfDepFile = resultList.indexOf(fileDependenceList.get(i));

                        if (indexOfCurrentFile < indexOfDepFile) {
                            throw new RuntimeException("Cyclic dependence found in file "
                                    + currentFilePath + " !!!");
                        }
                    } else {
                        resultList.add(indexOfCurrentFile, fileDependenceList.get(i));
                    }
                }
            } else {
                for (String s : fileDependenceList) {
                    if (!resultList.contains(s)) {
                        resultList.add(s);
                    }
                }
            }
        }

        resultList.removeAll(independentFilesList);
        independentFilesList.addAll(resultList);
        resultList = independentFilesList;

        return resultList;
    }

    /**
     * Получения списка зависимости данного файла.
     * @param filePath Путь к файлу.
     * @return Список зависимостей файла.
     */
    private List<String> getCurrentFileDependenceList(String filePath) throws IOException {
        ArrayList<String> resultList = new ArrayList<>();

        try(BufferedReader stream = new BufferedReader(new FileReader(filePath))) {
            String line;
            do {
                line = stream.readLine();
                if (line != null) {
                    String[] subLines = line.split("‘");

                    if (subLines[0].trim().equals("require") && subLines.length > 1) {
                        String reqFilePath = DIRECTORY_PATH + System.getProperty("file.separator")
                                        + subLines[1].substring(0, subLines[1].length() - 1);

                        var file = new File(reqFilePath);

                        if (file.exists() && !file.isDirectory()) {
                            resultList.add(reqFilePath);
                        }
                    }
                }
            } while (line != null);
        }

        resultList.add(filePath);

        return resultList;
    }

    /**
     * Метод поиска независимых файлов.
     * @param filePaths Список путей к файлам.
     * @return Список независимых файлов.
     */
    private ArrayList<String> findIndependentFiles(List<String> filePaths)
            throws IOException {
        ArrayList<String> resultList = new ArrayList<>();

        for (String path : filePaths) {
            List<String> currentFileDepList = getCurrentFileDependenceList(path);

            if (currentFileDepList.size() <= 1) {
                resultList.add(path);
            }
        }

        return resultList;
    }
}
