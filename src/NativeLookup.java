import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NativeLookup {

    public static final String NATIVE_FUNCTIONS_CLASS = "NativeFunctions";


    private NativeClassLoader classLoader;

    //Known interface implementations
    private Map implementations = new HashMap(){{
        put(TestInterface.class,"TestInterfaceImpl");
    }};

    public NativeLookup() {
        ClassLoader classLoader = getClass().getClassLoader();
        this.classLoader = new NativeClassLoader(new URL[]{}, classLoader);
        try {
            this.classLoader.loadNativeClass(NATIVE_FUNCTIONS_CLASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object createImplInstance(Class interfaceClass) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        String classname = ((String) implementations.get(interfaceClass));
        return classLoader.loadNativeClass(classname).newInstance();
    }

    public void releaseClassLoader(){
        classLoader = null;
    }

}
