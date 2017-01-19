
public class NativeFunctions {
    static{
        System.loadLibrary("PerformanceTestJni");
    }
    public static native void emptyFunction();
    public static native void intFunction(int param);
    public static native void dblFunction(double param);
    public static native void ptrFunction(long ptrParam);

    public static native int returnInt();
    public static native double returnDbl();
    public static native long returnPtr();

    public static native void intFunction2(int param1,int param2);
    public static native void dblFunction2(double param1, double param2);
    public static native void ptrFunction2(long ptrParam1, long ptrParam2);

    public static native int sumInt(int param1,int param2);
    public static native double sumDbl(double param1, double param2);
}
