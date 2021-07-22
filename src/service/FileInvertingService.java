package service;

import domain.FrequencyVO;
import domain.WordFrequencyVO;
import repository.FileInvertingRepository;
import utils.FileUtil;
import utils.StringUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileInvertingService {
    private final FileInvertingRepository fileInvertingRepository;

    /**
     * 분석 대상 파일 경로로 객체 생성
     * @param filePath 분석 대상 파일 경로
     */
    public FileInvertingService() {
        this.fileInvertingRepository = new FileInvertingRepository();
    }

    /**
     * 파일을 읽고 분석한 내용을 저장
     * @param filePath 입력 파일 경로
     */
    public void read(final String filePath) {
        try {
            final BufferedReader br = FileUtil.getBufferedReader(filePath);

            String readLine;
            while ((readLine = br.readLine()) != null) {
                // get words
                final String[] words = StringUtil.extractWords(readLine);

                // get Frequency
                final Map<String, FrequencyVO> wordFrequencyMap = this.getWordFrequency(words);

                // save Frequency
                wordFrequencyMap.forEach(fileInvertingRepository::save);
            }
        } catch (IOException e) {
            System.out.println("파일 분석 실패!! 파일 경로: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * 분석된 내용을 파일로 출력
     * @param filePath 출력 파일 경로
     */
    public void write(final String filePath) {
        try {
            final BufferedWriter bw = FileUtil.getBufferedWriter(filePath);
            final List<WordFrequencyVO> wordFrequencies =  fileInvertingRepository.findAll();

            wordFrequencies.forEach(wordFrequencyVO -> {
                try {
                    bw.write(wordFrequencyVO.toString()+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("파일 쓰기 실패!! 파일 경로: " + filePath);
            e.printStackTrace();
        }
    }

    /**
     * 단어의 빈도수 정보를 구함
     * @param words 단어 목록(첫 번째 단어는 문서 ID)
     * @return Map<단어, 빈도수 정보>
     */
    private Map<String, FrequencyVO> getWordFrequency(String[] words) {
        // first word is doc
        final int doc = Integer.parseInt(words[0]);

        // init Map<word, count>
        final Map<String, Integer> wordCountMap = new HashMap<>();

        // count words
        Arrays.stream(words)
                .skip(1) // first word is doc
                .map(String::toLowerCase)
                .forEach((word) -> wordCountMap.merge(word, 1, Integer::sum));

        // get Map<word, FrequencyDto>
        final Map<String, FrequencyVO> wordFrequencyMap = new HashMap<>();
        wordCountMap.forEach((word, count) -> wordFrequencyMap.put(word, new FrequencyVO(doc, count)));

        return wordFrequencyMap;
    }
}
