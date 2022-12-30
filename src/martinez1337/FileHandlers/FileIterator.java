package martinez1337.FileHandlers;

import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

class FileIterator implements Iterator<String> {
    private final BufferedReader READER;
    private String nextLine;

    /**
     * Конструктор файлового итератора.
     * @param path Путь к файлу.
     * @throws UncheckedIOException если файл не найден.
     */
    public FileIterator(String path) {
        try {
            READER = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException ex) {
            throw new UncheckedIOException(ex);
        }

        nextLine = null;
    }

    /**
     * Метод определения существования следующей строки.
     * @return true - строка есть
     * <br>false - строки нет</br>
     * @throws UncheckedIOException если проблема при чтении из файла.
     */
    @Override
    public boolean hasNext() {
        if (nextLine != null) {
            return true;
        } else {
            try {
                nextLine = READER.readLine();
                return (nextLine != null);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }
    }

    /**
     * Метод возвращает следующую строку из файла.
     * @return Следующая строка.
     * @throws UncheckedIOException если проблема с закрытием файлового потока.
     * @throws NoSuchElementException если нет следующей строки.
     */
    @Override
    public String next() {
        if (hasNext()) {
            String resultLine = nextLine;
            nextLine = null;
            return resultLine;
        } else {
            nextLine = null;
            try {
                READER.close();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
            throw new NoSuchElementException();
        }
    }
}
