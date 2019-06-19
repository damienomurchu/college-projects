/**
 * @file    Sphere.java
 * @brief   This class describes a sphere and includes methods to calculate 
 *          properties such as surface area and volume.
 * @author  Damien Murphy 
 * @version 1.0, 2016-02-10
 */
public class Sphere{
    double radius = 0;
    static int numberSpheres = 0;   // track number of spheres created

    /**
     * Construct sphere with default radius of 10.0
     */
    public Sphere(){
        setState(10.0);
        numberSpheres++;
    }

    /**
     * Construct sphere from radius value passed
     * @param radius radius of new sphere
     */
    public Sphere(double radius){
        if (isValid(radius)){
            setState(radius);
            numberSpheres++;
        } else {
            System.out.println("You have attempted to create a sphere with a negative radius." 
                                + "\nOnly positive radii permitted.");
        }
    }

    // check if value is positive
    private boolean isValid(double value){
        return value > 0 ? true : false;
    }

    // set radius if argument is valid
    private void setState(double radius){
        if (isValid(radius)){
            this.radius = radius;
        } else { 
            System.out.println("Error - attempt to assign negative value to radius");
        }
    }

    /**
     * Calculates surface area of sphere object 
     * @return the surface area of sphere
     */
    public double surfaceArea(){
        return 4 * Math.PI * radius * radius;
    }

    /**
     * Calculates volume of a sphere object
     * @return the volume of sphere
     */
    public double volume(){ 
        return (4 * Math.PI * radius * radius * radius) / 3;
    }

    /**
     * Calculates circumference of a sphere object
     * @return the circumference of sphere
     */
    public double greatCircle(){ 
        return 2 * Math.PI * radius;
    }

    /**
     * Changes radius value of a sphere object 
     * @param radius new radius of sphere
     */
    public void changeSize(double radius){ 
        setState(radius);
    }

    /**
     * Prints number of sphere objects that have been created 
     * @return the number of spheres created
     */
    public int getNumberSpheres(){ 
        return numberSpheres;
    }
}
