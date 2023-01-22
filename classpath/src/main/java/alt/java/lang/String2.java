package alt.java.lang;

public final class String2 extends Object2 {
    static final String2 EMPTY = new String2();
    static final String2 NULL = new String2(new byte[] {'n', 'u', 'l', 'l'});
    static final String2 BOOLEAN_TRUE = new String2(new byte[] {'t', 'r', 'u', 'e'});
    static final String2 BOOLEAN_FALSE = new String2(new byte[] {'f','a', 'l', 's', 'e'});

    private final byte[] buffer;
    private int hashCode;

    private String2() {
        this.buffer = new byte[0];
    }

    public String2(byte[] buffer) {
        this.buffer = arrayCopyOf(buffer, 0, buffer.length);
    }

    public String2(byte[] buffer, int offset, int length) {
        this.buffer = arrayCopyOf(buffer, offset, length);
    }

    private String2(byte[] buffer, boolean isCopy) {
        this.buffer = buffer;
    }

    private static byte[] arrayCopyOf(byte[] src, int offset, int length) {
        byte[] result = new byte[length];
        System.arraycopy(src, offset, result, 0, length);
        return result;
    }

    public byte charAt(int i) {
        return buffer[i];
    }

    public String2 concat(String2 that) {
        if (that.isEmpty()) {
            return this;
        }
        byte[] newBuffer = new byte[buffer.length + that.buffer.length];
        System.arraycopy(buffer, 0, newBuffer, 0, buffer.length);
        System.arraycopy(that.buffer, 0, newBuffer, buffer.length, that.buffer.length);
        return new String2(newBuffer, /* isCopy= */ false);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof String2) {
            String2 that = (String2) o;
            if (buffer.length != that.buffer.length) {
                return false;
            }
            for (int i = 0; i < buffer.length; i++) {
                if (buffer[i] != that.buffer[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return buffer.length == 0;
    }

    public byte[] getBytes() {
        return arrayCopyOf(buffer, 0, buffer.length);
    }

    @Override
    public int hashCode() {
        if (hashCode != 0 || buffer.length == 0) {
            return hashCode;
        }
        int h = 0;
        for (int i = 0; i < buffer.length; i++) {
            h = 31 * h + buffer[i];
        }
        return hashCode = h;
    }

    public int length() {
        return buffer.length;
    }

    public boolean regionMatches(int thisOffset, String2 other, int otherOffset, int length) {
        if (thisOffset + length > length()) {
            return false;
        }
        if (otherOffset + length > other.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (buffer[thisOffset + i] != other.buffer[otherOffset + i]) {
                return false;
            }
        }
        return true;
    }

    public boolean endsWith(String2 value) {
        if (value.length() > buffer.length) {
            return false;
        }
        int thisOffset = length() - value.length();
        return regionMatches(thisOffset, value, 0, value.length());
    }

    public boolean startsWith(String2 value) {
        if (value.length() > buffer.length) {
            return false;
        }
        return regionMatches(0, value, 0, value.length());
    }

    public String2 substring(int beginIndex) {
        return substring(beginIndex, buffer.length);
    }

    public String2 substring(int beginIndex, int endIndex) {
        return new String2(buffer, beginIndex, endIndex - beginIndex);
    }

    @Override
    public String2 toString2() {
        return this;
    }

    public static String2 valueOf(boolean value) {
        return value ? BOOLEAN_TRUE : BOOLEAN_FALSE;
    }

    public static String2 valueOf(int value) {
        throw new UnsupportedOperationException();
    }

    public static String2 valueOf(Object object) {
        if (object == null) {
            return NULL;
        }
        object.toString();
        return null;
    }

}