package utils;

public class StringUtil {
    /**
     * 특수 문자 일괄 변경
     * @param str 입력 문자열
     * @param replaceTo 변경할 문자열
     * @return String
     */
    public static String replaceSpecialChar(final String str, final String replaceTo){
        return str.replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]", replaceTo);
    }

    /**
     * 특수 문자가 제거된 단어 추출
     * @param str 입력 문자열
     * @return String[]
     */
    public static String[] extractWords(final String str) {
        return StringUtil.replaceSpecialChar(str, " ").split("\\s+");
    }
}
