import java.util.ArrayList;
import java.util.Iterator;

/**
 * @file    DemoArray.java
 * @brief   This class demonstrates the various ways in which arrays may be manipulated with loops.
 * 
 * @version 1.0, 2016-02-09
 * @author  Damien Murphy
 */

public class DemoArray {

    private int[] intArray;
    private int size = 10;  // used to set capacity of arrays

    /**
     * Constructs DemoArray with intArray capacity equal to size variable
     */
    public DemoArray() {
        intArray = new int[size];
    }

    /**
     * Populates and prints all elements of objects intArray using for loops.
     */
    public void demoArray() {
        // initialise all elements in array to 100 + i
        for (int i = 0; i < intArray.length; i++){
            intArray[i] = 100 + i;
        }
        // output all values
        for (int j = 0; j < intArray.length; j++){
            System.out.println("Element at index " + j + " is " + intArray[j]);
        }
    }

    /**
     * Populates and prints all elements of objects intArray using while loops.
     */
    public void demoArray2() {
        // initialise all elements in array to 200 + 2*i
        int i = 0;
        while (i < intArray.length){
            intArray[i] = 200 + (2 * i);
            i++;
        }
        // out all values
        int j = 0;
        while (j < intArray.length){
            System.out.println("Element at index " + j + " is " + intArray[j]);
            j++;
        }
    }

    /**
     * Populates and prints all elements of objects intArray with do-while loops.
     */
    public void demoArray3() {
        // initialise all elements in array to 300 + 3*i
        int i = 0;
        do{
            intArray[i] = 300 + 3*i;
            i++;
        } while (i < intArray.length);
        // out all values
        int j = 0;
        do{
            System.out.println("Element at index " + j + " is " + intArray[j]);
            j++;
        } while (j < intArray.length);
    }

    /**
     * Creates and populates a new ArrayList, printing all elements with an interator
     */
    public void demoArray4() {
        ArrayList<Integer> ar = new ArrayList<>();
        // initialise all elements in ArrayList to 400 + 4*i
        for (int i = 0; i < size; i++){
            ar.add(400+4*i);
        }
        // output all values with an iterator
        Iterator it = ar.iterator();
        while (it.hasNext()){
            Integer intVal = (Integer)it.next();
            int index = ar.lastIndexOf(intVal);
            System.out.println("Element at index " + index + " is " + intVal);
        }
    }

    /**
     * Sums all even numbers from low to hi (inclusive) and prints result
     * @param low The minimum range value inclusive
     * @param hi The maximum range value inclusive
     */
    public void sumEven(int low, int hi){
        // check low and hi are even
        if ( low % 2 != 0 || hi % 2 != 0 ){
            System.out.println("Please enter even arguments");
        } else {
            // create temporary array to hold even values
            int range = ((hi-low)/2) + 1;
            int index = 0;
            int[] sumArray = new int[range];
            // populate array with even values
            for (int i = low; i <= hi; i+=2){
                sumArray[index] = i;
                index++;
            }
            // sum all values in array and print total
            int result = 0;
            for (int j = 0; j < sumArray.length; j++){
                result += sumArray[j];
            }
            System.out.println("Sum even numbers from " + low + " to " + hi + " inclusive is " + result);  
        }   
    }
}
