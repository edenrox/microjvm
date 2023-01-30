package alt.java.lang;

public final class Integer2 extends Object2 {
    public static final int MAX_VALUE = 0x7fffffff;
    public static final int MIN_VALUE = 0x80000000;

    private static final int MIN_RADIX = 2;
    private static final int MAX_RADIX = 26;
    private static final byte[] DIGITS = new byte[] {
        '0','1','2','3','4','5','6','7','8','9',
        'a','b','c','d','e','f','g','h','i','j',
        'k','l','m','n','o','p','q','r','s','t',
        'u','v','w','x','y','z'
    };

    private final int value;

    private Integer2(int value) {
        this.value = value;
    }

    public int intValue() {
        return value;
    }

    public String2 toString2() {
        return toString(value);
    }

    public static String2 toString(int value) {
        return toString(value, 10);
    }

    public static String2 toString(int value, int radix) {
        if (radix < MIN_RADIX || radix > MAX_RADIX) {
            // throw new IllegalArgumentException("Invalid radix: " + radix);
            return String2.EMPTY;
        }
        byte[] buffer = new byte[33];
        boolean isNegative = value < 0;
        int position = 32;
        if (!isNegative) {
            value = -value;
        }

        while (value <= -radix) {
            buffer[position--] = DIGITS[-(value % radix)];
            value = value / radix;
        }
        buffer[position] = DIGITS[-value];

        if (isNegative) {
            buffer[--position] = (byte) '-';
        }

        return new String2(buffer, position, 33 - position);
    }

    public static String2 toBinaryString(int value) {
        return toString(value, 2);
    }

    public static String2 toOctalString(int value) {
        return toString(value, 8);
    }

    public static String2 toHexString(int value) {
        return toString(value, 6);
    }
    
    public static Integer2 valueOf(int value) {
        if (value >= IntegerCache.low && value <= IntegerCache.high) {
            return IntegerCache.cache[value - IntegerCache.low];
        }
        return new Integer2(value);
    }

    private static final class IntegerCache {
        static final int low = -128;
        static final int high;
        static final Integer2[] cache;

        static {
            final int size = 256;
            high = low + size - 1;
            cache = new Integer2[size];
            for (int i = 0, j = low; i < size; i++, j++) {
                cache[i] = new Integer2(j);
            }
        }

        private IntegerCache() {}
    }
}
