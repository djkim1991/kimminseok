package domain;

public class FrequencyVO {
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

    public String toString() {
        return doc + " " + frequency;
    }
}
