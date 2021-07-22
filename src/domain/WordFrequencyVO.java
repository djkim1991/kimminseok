package domain;

import java.util.List;
import java.util.stream.Collectors;

public class WordFrequencyVO {
    private final String word;
    private final List<FrequencyVO> frequencies;

    public WordFrequencyVO(final String word, final List<FrequencyVO> frequencies) {
        this.word = word;
        this.frequencies = frequencies;
    }

    public String toString() {
        final String frequenciesStr = frequencies.stream().map(FrequencyVO::toString).collect(Collectors.joining(" "));
        return word + " " + frequenciesStr;
    }
}
