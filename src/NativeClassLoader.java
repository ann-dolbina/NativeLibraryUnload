import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;

public class NativeClassLoader extends URLClassLoader {

    public static final String PERFORMANCE_TEST_JNI = "PerformanceTestJni";

    public NativeClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }


    protected String findLibrary(String libName) {
        //A more complex library locator can be implemented here.
        if (libName.equals(PERFORMANCE_TEST_JNI)) {
            System.out.println("Finding library into NativeClassLoader: " + libName);
            return new File(libName + ".dll").getAbsolutePath();
        } else {
            return super.findLibrary(libName);
        }
    }

    private String getRelativePath(String className) {
        return className.
                replace('.', File.separatorChar)
                + ".class";
    }

    private byte[] getBytes(InputStream input) throws IOException {
        byte[] classBytes = new
                byte[input.available()];
        input.read(classBytes);
        return classBytes;
    }

    public Class loadNativeClass(String name) throws ClassNotFoundException, IOException {

        byte[] classData = getClassBytesFromResource(getRelativePath(name));
        return defineClass(name,
                classData, 0, classData.length);
    }

    private byte[] getClassBytesFromResource(String resource) throws IOException {
        return getBytes(getResourceAsStream(resource));
    }

    protected void finalize() throws Throwable {
        System.out.println("NativeClassLoader finalized");
    }
}
