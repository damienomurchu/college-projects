
/**
 * Write a description of class TestDemoArray here.
 * 
 * @author jfitzgerald
 * @version 29.1.2016
 */
public class TestDemoArray
{
    public static void testDemoArray()
    {
        DemoArray obj = new DemoArray();
		System.out.print("DemoArray\n");
		obj.demoArray();
		System.out.print("\nDemoArray2\n");
		obj.demoArray2();
		System.out.print("\nDemoArray3\n");
		obj.demoArray3();
		System.out.print("\nDemoArray4\n");
		obj.demoArray4();
		System.out.print("\n");
		obj.sumEven(0, 10);

    }
}
