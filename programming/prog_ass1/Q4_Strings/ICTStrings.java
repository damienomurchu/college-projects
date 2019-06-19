/**
 * @file    ICTStrings.java
 * @brief   This class illustrates a variety of String manipulation methods
 * @author  Damien Murphy 
 * @version 1.0, 2016-02-09
 */

public class ICTStrings
{
    /**
     * Check is s1 is equal to s2
     * @param s1 a string
     * @param s2 another string
     * @return true if strings equal else false
     */
    public static boolean isEqual(String s1, String s2)
    {
        return s1.equals(s2);
    }

    /**
     * Check is s1 is equal to s2 ignoring case
     * @param s1 a string
     * @param s2 another string
     * @return true if strings equal, ignoring case, else false
     */
    public static boolean isEqualIgnoreCase(String s1, String s2)
    {
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * Check if s1 has prefix
     * @param s1 a string
     * @param prefix another string 
     * @return true if s1 starts with prefix
     */
    public static boolean hasPrefix(String s1, String prefix)
    {
        return s1.startsWith(prefix);
    }

    /**
     * Length string comprising concatenated strings s1 and s2
     * @param s1 a string
     * @param s2 another string
     * @return length of s1 concatenated with s2
     */
    public static int length(String s1, String s2)
    {
        String s3 = s1 + s2;
        return s3.length();
    }

    /**
     * Create string the uppercase version of s1
     * @param s1 a string
     * @return copy of s1 all in upper case
     */
    public static String toUpper(String s1)
    {
        return s1.toUpperCase();
    }

    /**
     * Returns true if string ends with suffix.
     * Example if s1 is "this is a string" and suffix is "ring" the return value will be true.
     * @param s1 a string
     * @param suffix a string
     * @return true if s1 ends in suffix
     */
    public static boolean endsWith(String s1, String suffix)
    {
        return s1.endsWith(suffix);
    }

    /**
     * Create substring of s1 from indexStart to indexEnd excluding indexEnd
     * That is: range is [indexStart, indexEnd)
     * Example String "Hello ICTSkills"
     * indexStart = 2;
     * indexEnd = 8;
     * substring is "llo IC"
     * @param s1 a string
     * @param indexStart of s1 becomes zeroth index of substring
     * @param indexEnd of s1 determines end of substring
     * @return the substring
     */
    public static String subString(String s1, int indexStart, int indexEnd)
    {
        return s1.substring(indexStart, indexEnd);
    }

    /**
     * Create a string, the reverse of string using StringBuilder
     * @param string the string to be reversed
     * @return a copy of string reversed
     */
    public static String reverse_1(String string)
    {
        StringBuilder sb = new StringBuilder(string);
        return "" + sb.reverse();
    }

    /**
     * Create a string, the reverse of string
     * An array of char type of same size of string is instantiated.
     * In a for loop the string characters are assigned to the array in reverse order.
     * The array is used to create a string which is then returned.
     * This string will be the reverse of the actual parameter string.
     * 
     * @param string the string to be reversed
     * @return a copy of string reversed
     */
    public static String reverse_2(String string)
    {
        // temporary char array to hold each char in string
        char[] tempArray = string.toCharArray();
        // left and right index values for start and end of char array
        int leftIndex = 0;
        int rightIndex = tempArray.length - 1;
        // traverse char array and reverse order of the elements
        for ( leftIndex = 0; leftIndex < rightIndex; leftIndex++, rightIndex--){
            // temp char variable to hold content of current element
            char temp = tempArray[leftIndex];
            // swap current left of center element with its equivalent on the right 
            tempArray[leftIndex] = tempArray[rightIndex];
            tempArray[rightIndex] = temp;
        }
        return new String(tempArray);
    }

    /**
     * Reverses a string using the StringBuffer class 
     * @param string the string to be reversed
     * @return a copy of string reversed
     */
    public static String reverse_3(String string)
    {       
        StringBuffer sbf = new StringBuffer(string);
        return "" + sbf.reverse();
    }
    
    /** 
     * Check if string is a palindrome
     * @param s Is s a palindrome?
     * @return Returns true if argument a palindrome, else false.
     */
    public static boolean isPalindrome(String s)
    {
        String s1 = s.replaceAll("[^a-zA-Z]", "");
        String s2 = "" + new StringBuilder(s1).reverse();
        return s2.equalsIgnoreCase(s1);
    }
}
