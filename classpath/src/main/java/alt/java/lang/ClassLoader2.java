package alt.java.lang;

public abstract class ClassLoader2 {

    protected ClassLoader2() {}

    protected abstract Class2 loadClass(String name, boolean resolve);

    protected final native Class2 defineClass(byte[] data, int offset, int length);

    protected final native void resolveClass(Class2 clazz);
    
    protected final native Class2 findSystemClass(String name);

    public static native ClassLoader2 getCallingClassLoader();
}
