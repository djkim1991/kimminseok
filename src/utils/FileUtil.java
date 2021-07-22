package utils;

import java.io.*;

public class FileUtil {

    /**
     * 파일 읽기
     * @param filePath 읽을 파일 경로
     * @return BufferedReader
     * @throws IOException 파일 I/O
     */
    public static BufferedReader getBufferedReader(String filePath) throws IOException {
        FileReader rw = new FileReader(filePath);
        return new BufferedReader(rw);
    }

    /**
     * 파일 쓰기
     * @param filePath 파일 저장 경로
     * @return BufferedWriter
     * @throws IOException 파일 I/O
     */
    public static  BufferedWriter getBufferedWriter(String filePath) throws IOException {
        return new BufferedWriter(new FileWriter(filePath, false));
    }
}
