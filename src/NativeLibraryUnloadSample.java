import java.io.IOException;

public class NativeLibraryUnloadSample {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException, InterruptedException {
        //Loading native library
        NativeLookup nativeLookup = new NativeLookup();

        //Calling the native function through
        //the instance loaded in another class loader.
        TestInterface testInstance = (TestInterface) nativeLookup.createImplInstance(TestInterface.class);
        int result = testInstance.add(1, 2);
        System.out.println("result = " + result);

        //Releasing native library.
        /*
        There are many ways to make a class reachable and thus prevent it from being eligible for GC:

                 - objects of that class are still reachable.
                 - the Class object representing the class is still reachable
                 - the ClassLoader that loaded the class is still reachable
                 - other classes loaded by the ClassLoader are still reachable

        When none of those are true, then the ClassLoader and all classes it loaded are eligible for GC.
        */

        //First of all, release references to all the instances bound to particular class loader.
        testInstance = null;
        //Then, release the reference to the class loader itself
        nativeLookup.releaseClassLoader();
        // After all, trigger GC.
        System.gc();

        //Final timeout
        Thread.sleep(1000);
    }
}
