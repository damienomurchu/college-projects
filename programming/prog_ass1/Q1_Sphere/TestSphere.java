/**
 * Write a description of class TestSphere here.
 * 
 * @author jfitzgerald
 * @version 29-1-2016
 */
public class TestSphere
{
    
    public static void testAll()
    {
        testDefaultSphere();
        testSpecificSphere(20);
        testInvalidSphere();
    }
    
    public static void testInvalidSphere()
    {
        Sphere sphere = new Sphere(-10);
    }
    
    public static void testDefaultSphere()
    {
        
        // Test default sphere
        Sphere sphere = new Sphere();
        System.out.println("Default sphere");
        System.out.println("radius: " + sphere.radius);
        System.out.println("number Sphere objects: " + sphere.getNumberSpheres());
        System.out.println("surface area: " + sphere.surfaceArea());
        System.out.println("volume: " + sphere.volume());
        System.out.println();
    }
    
    public static void testSpecificSphere(double radius)
    {
        // Test sphere whose radius is argument 'radius'
        Sphere sphere = new Sphere(radius);
        System.out.println("Specific sphere");
        System.out.println("radius: " + sphere.radius);
        System.out.println("number Sphere objects: " + sphere.getNumberSpheres());
        System.out.println("surface area: " + sphere.surfaceArea());
        System.out.println("volume: " + sphere.volume());
        System.out.println();
    }
}
