package alt.java.lang;

/**
 * Basic implementation of {@link java.lang.System}.
 * 
 * All methods are static and implemented in native code.
 */
public final class System2 {
    public static native final void arraycopy(Object src, int srcOffset, Object dest, int destOffset, int length);

    public static native final void gc();

    public static native final void identityHashCode(Object obj);

    private System2() {}
}
