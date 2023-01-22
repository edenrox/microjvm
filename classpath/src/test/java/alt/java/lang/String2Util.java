package alt.java.lang;

import java.nio.charset.StandardCharsets;

public class String2Util {
    public static String fromString2(String2 src) {
        return new String(src.getBytes(), StandardCharsets.US_ASCII);
    }
    public static String2 toString2(String src) {
        return new String2(src.getBytes(StandardCharsets.US_ASCII));
    }
}
