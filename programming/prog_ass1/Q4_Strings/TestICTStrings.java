public class TestICTStrings
{
    public static void test_isEqual()
    {
        String s1 = "ICTSkills Studio";
        String s2 = "ictskills studio";
        System.out.println("isEqual:  "+ICTStrings.isEqual(s1, s1));
    }
    
    public static void test_isEqualIgnoreCase()
    {
        String s1 = "ICTSkills Studio";
        String s2 = "ictskills studio";
        System.out.println("isEqualIgnoreCase:  "+ICTStrings.isEqual(s1, s1));
    }
    
    public static void test_hasPrefix()
    {
        String s1 = "ICTSkills Studio";
        String prefix = "ICT";
        System.out.println("hasPrefix:  "+ICTStrings.hasPrefix(s1, prefix));
    }
    
    public static void test_length()
    {
        String s1 = "ICTSkills ";
        String s2 = "Studio";
        System.out.println("length:  "+ICTStrings.length(s1, s2));
    }
    public static void test_toUpperCase()
    {
        String s1 = "ictskills studio";
        System.out.println("toUpper:  "+ICTStrings.toUpper(s1));
    }
    
    public static void test_endsWith()
    {
        String s1 = "ictskills studio";
        System.out.println("String " + s1 + " ends with udio? " + ICTStrings.endsWith(s1, "udio"));
    }
    
    public static void test_subString()
    {
      String s1 = "Able was I";
      int startIndex = 2;
      int endIndex =  8;
      System.out.println("subString:  "+ICTStrings.subString(s1, startIndex, endIndex));
    }
    
    public static void test_reverse_1()
    {
        String s1 = "able was I ere I saw elba";
        System.out.println("reverse:  "+ICTStrings.reverse_1(s1));
    }
    
    public static void test_reverse_2()
    {
        String s1 = "able was I ere I saw elba";
        System.out.println("reverse:  "+ICTStrings.reverse_2(s1));
    }
  
    public static void test_reverse_3()
    {
        String s1 = "able was I ere I saw elba";
        System.out.println("reverse:  "+ICTStrings.reverse_3(s1));
    }
    
    public static void test_isPalindrome()
    {
        String s = "No lemon no melon";
        boolean b = ICTStrings.isPalindrome(s);
        if (b)
        {
            System.out.println(s + " is a palindrome");
        }
        else
        {
            System.out.println(s + " is not a palindrome");
        }
    }
    
    public static void test_all()
    {
        test_isEqual();
        test_isEqualIgnoreCase();
        test_hasPrefix();
        test_length();
        test_toUpperCase();
        test_endsWith();
        test_subString();
        test_reverse_1();
        test_reverse_2();
        test_reverse_3();
        test_isPalindrome();
    }
}
