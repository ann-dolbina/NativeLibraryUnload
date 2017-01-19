public class TestInterfaceImpl implements TestInterface {

    public int add(int a, int b) {
        return NativeFunctions.sumInt(a,b);
    }

}
