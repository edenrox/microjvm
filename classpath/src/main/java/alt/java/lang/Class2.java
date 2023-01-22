package alt.java.lang;

public final class Class2 extends Object2 {
    private static final String2 S2_CLASS = new String2(new byte[] {'c','l','a','s','s'});
    private static final String2 S2_INTERFACE = new String2(new byte[] {'i','n','t','e','r','f','a','c','e'});

    private Class2() {}

    public native String2 getName();

    public native ClassLoader2 getClassLoader();

    public native Class2[] getInterfaces();

    public native Class2 getSuperclass();

    public native boolean isInterface();

    private native boolean isPrimitive();

    public native Object2 newInstance();

    public String2 toString2() {
        String2 prefix;
        if (isInterface()) {
            prefix = S2_INTERFACE;
        } else if (isPrimitive()) {
            prefix = String2.EMPTY;
        } else {
            prefix = S2_CLASS;
        }
        return prefix.concat(getName());
    }

    public static Class2 forName(String name) {
        return ClassLoader2.getCallingClassLoader().loadClass(name, true);
    }
}
