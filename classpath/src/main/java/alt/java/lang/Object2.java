package alt.java.lang;

public class Object2 {
    private static final String2 S2_AT = new String2(new byte[] {'@'});
    public Object2() {}

    public native final Class2 getClass2();

    public native int hashCode2();

    public boolean equals(Object that) {
        return this == that;
    }

    public String2 toString2() {
        return getClass2().getName().concat(S2_AT).concat(Integer2.toHexString(hashCode2()));
    }
}
