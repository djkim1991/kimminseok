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
                    this.wordFrequencyMap.get(word).sort(this.sortFrequencyDto()); // 빈도수, doc 정렬
                    return new WordFrequencyVO(word, wordFrequency);
                })
                .collect(Collectors.toList());
    }

    /**
     * 빈도수 정보 정렬 Comparator
     * 정렬 기준: 1. 빈도수 2. 문서 ID
     * @return Comparator<FrequencyVO>
     */
    private Comparator<FrequencyVO> sortFrequencyDto() {
        return (o1, o2) -> {
            final int o1Doc = o1.getDoc();
            final int o2Doc = o2.getDoc();
            final int o1Frequency = o1.getFrequency();
            final int o2Frequency = o2.getFrequency();

            if (o2Frequency == o1Frequency) {
                if (o1Doc == o2Doc) {
                    return 0;
                }
                return o1Doc > o2Doc ? 1 : -1;
            }

            return (o2Frequency > o1Frequency) ? 1 : -1;
        };
    }
}
