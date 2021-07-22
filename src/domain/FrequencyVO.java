package domain;

import java.util.Comparator;

public class FrequencyVO implements Comparable<FrequencyVO> {
    private final int doc;
    private final int frequency;

    public FrequencyVO(final int doc, final int frequency) {
        this.doc = doc;
        this.frequency = frequency;
    }

    public int getDoc() {
        return doc;
    }

    public int getFrequency() {
        return frequency;
    }

    @Override
    public String toString() {
        return doc + " " + frequency;
    }

    /**
     * 빈도수 정보 정렬 Comparator
     * 정렬 기준: 1. 빈도수 2. 문서 ID
     * @param FrequencyVO 비교 대상
     * @return int
     */
    @Override
    public int compareTo(FrequencyVO o2) {
        final FrequencyVO o1 = this;

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
    }
}
