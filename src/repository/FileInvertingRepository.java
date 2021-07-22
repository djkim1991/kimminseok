package repository;

import domain.FrequencyVO;
import domain.WordFrequencyVO;

import java.util.*;
import java.util.stream.Collectors;

public class FileInvertingRepository {
    private final List<String> words;
    private final Map<String, List<FrequencyVO>> wordFrequencyMap;

    public FileInvertingRepository() {
        this.words = new ArrayList<>();
        this.wordFrequencyMap = new HashMap<>();
    }

    /**
     * 단어 빈도수 정보 저장
     * @param word 단어
     * @param frequencyVO 빈도수 정보
     */
    public void save(final String word, final FrequencyVO frequencyVO) {
        final List<FrequencyVO> frequencyList = this.wordFrequencyMap.get(word);

        if (frequencyList == null) {
            words.add(word);
            this.wordFrequencyMap.put(word, new ArrayList<>(Collections.singletonList(frequencyVO)));
        } else {
            frequencyList.add(frequencyVO);
        }
    }

    /**
     * 단어 빈도수 정보 조회
     * @return List<WordFrequencyVO>
     */
    public List<WordFrequencyVO> findAll() {
        return this.words.stream()
                .sorted(Comparator.naturalOrder()) // 단어 정렬
                .map(word -> {
                    final List<FrequencyVO> wordFrequency = this.wordFrequencyMap.get(word);

                    // 빈도수, doc 정렬
                    Collections.sort(wordFrequency);

                    return new WordFrequencyVO(word, wordFrequency);
                })
                .collect(Collectors.toList());
    }
}
