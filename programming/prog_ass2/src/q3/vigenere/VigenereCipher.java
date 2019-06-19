package q3.vigenere;

/**
 * Cipher class that allows encryption and decryption of strings using the Vigenere method.
 * Messages are encrypted and decrypted through a vigenere cipher object.
 * Manual encryption and decryption is facilitated by printing the table.
 * 
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 */
public class VigenereCipher {

  /**
   * Lowest permissable char in range
   */
  private static final char LOWER_CHAR_LIMIT = 'A';

  /**
   * Highest permissable char in range
   */
  private static final char UPPER_CHAR_LIMIT = 'Z';
  
  /**
   * Permissable range of chars
   */
  private static final int CHAR_RANGE= (int) (UPPER_CHAR_LIMIT - LOWER_CHAR_LIMIT + 1);

  /**
   * 2d array representing vigenere cipher reference table
   */
  static char[][] vigenereTable;

  /**
   * Constructs a new VigenereCipher object
   */
  public VigenereCipher() { }
  
  /**
   * Constructs a new Vigenere Cipher object and calls testVigenere()
   */
  public static void main(String[] args) {
    VigenereCipher vc1 = new VigenereCipher();
    vc1.testVigenere();    
  }
  
  /**
   * Test method by: 
   *  Generating and printing Vigenere Table,
   *  Generating the key using the keyword,
   *  Encrypting a message (plain text),
   *  Decrypting the cipher text.
   *  Printing the table, key, message, ciphertext & decrypted ciphertext.
   */
  public void testVigenere() {
    generateVigenereTable();

    String messageText = "MICHIGANTECHNOLOGICALUNIVERSITY";
    String key = generateKey("HOUGHTON", messageText.length());
    String cipherText = encrypt(key, messageText);
    String decrypted = decrypt(key, cipherText);

    printVigenereTable();
    printKey(key);
    printMessage(messageText);
    printCipher(cipherText);
    printMessage(decrypted);
  } 
  
  /**
   * Generates the Vigenere Table
   */
  public static void generateVigenereTable() {
    /* generate table if it has not been done so already */
    if (vigenereTable == null) {
      /* initialise table array object */
      vigenereTable = new char[26][26];
      
      /* populate table on basis [0][0] is 'A', and each subsequent row/ column 
         is 1 character greater than the directly preceding row/ column */
      for (int i = 0; i < 26; i++) {
        for (int j = 0; j < 26; j++) {
          vigenereTable[i][j] = shiftChar(LOWER_CHAR_LIMIT, i + j);
        }
      }
    }
  }
  
  /**
   * Generates a key of the specified length using the keyword.
   * Checks desired keylength is a positive number.
   * Any punctuation in the keyword is removed.
   * Keyword is transformed to uppercase.
   *
   * @param keyLength The number of characters in the key.
   * @param keyword The keyword used to generate the key
   * @return The key for use in encrypting a message whose length matches the key length.
   */
  public String generateKey(String keyword, int keyLength) {
    
    if (keyLength < 1) { //check keylength is positive
      System.err.println("Key length must be a positive number");
      return null;
    } else if (keyword.replaceAll("[^a-zA-Z]", "").length() == keyLength) {
      return keyword.replaceAll("[^a-zA-Z]", "").toUpperCase();
    } else {
      keyword = keyword.replaceAll("[^a-zA-Z]", "").toUpperCase();
      String key = "";
      for (int i = 0; i < keyLength; i++) {
        key += keyword.charAt(i % keyword.length());
      }
      return key;
    }    
  }

  /**
   * Encrypts plain text (message text) with the key using the Vigenere Table.
   * 
   * @param plainText The plain text (message text) to be encrypted
   * @param key The key under which the plain text is encrypted
   * @return The cipher text created by encrypting the plaintext under the generated key.
   */
  public String encrypt(String key, String plainText) {
    
    /* generate key if necessary */
    if (key.replaceAll("[^a-zA-Z]", "").length() != plainText.replaceAll("[^a-zA-Z]", "").length()) {
      key = generateKey(key, plainText.length());
    }
    
    /* build and return encrypted string */
    key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
    plainText = plainText.replaceAll("[^a-zA-Z]", "").toUpperCase();
    String cipherText = "";
    for (int i = 0; i < plainText.length(); i++) {
      
      /* use the plaintext and key char to derive how many characters need to be added
       * to the tables anchor element ([0][0]/'A') to return the encrypted char */
      int charShift = (plainText.charAt(i) + key.charAt(i)) % LOWER_CHAR_LIMIT;
      cipherText += shiftChar(LOWER_CHAR_LIMIT, charShift);			
    }
    return cipherText;
  }

  /**
   * Decrypts cipher text under the key using the Vigenere Table.
   * 
   * @param key The key under which the cipher text is decrypted
   * @param cipherText The cipher text to be decrypted.
   * @return The plain text obtained by decrypting the cipher text under the generated key.
   */
  public String decrypt(String key, String cipherText) {
    
    /* generate key if necessary */ 
    if (key.replaceAll("[^a-zA-Z]", "").length() != cipherText.replaceAll("[^a-zA-Z]", "").length()) {
      key = generateKey(key, cipherText.length());
    }

    /* build and return encrypted string */
    key = key.replaceAll("[^a-zA-Z]", "").toUpperCase();
    cipherText = cipherText.replaceAll("[^a-zA-Z]", "").toUpperCase();    
    String plainText = "";
    for (int i = 0; i < cipherText.length(); i++) {
      
      /* use the cipher and key char to derive how many characters need to be added
       * to the tables anchor element ([0][0]/'A') to return the decrypted char */
      int charShift = (cipherText.charAt(i) - key.charAt(i)) % LOWER_CHAR_LIMIT;
      plainText += shiftChar(LOWER_CHAR_LIMIT, charShift);			
    }
    return plainText;
  }

  /**
   * Prints the vigenere table
   */
  public void printVigenereTable() {
    
    /* initialise and generate the table if necessary */
    if (vigenereTable == null) {
      generateVigenereTable();
    }
    
    /* print the table */
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        System.out.print(vigenereTable[i][j] + " ");
        if (j == 25) {
          System.out.print("\n");
        }
      }
    }
  }

  /**
   * Print the key.
   * 
   * @param key The key used for encryption and decryption.
   */
  public void printKey(String key) {
    System.out.println("Encryption Key:\t" + key);	
  }

  /**
   * Print the plain text (message text).
   * 
   * @param plainText The plain text (message text).
   */
  public void printMessage(String plainText) {
    System.out.println("Message Text:\t" + plainText);
  }

  /**
   * Print the cipher text.
   * 
   * @param cipherText The encrypted message.
   */
  public void printCipher(String cipherText) {
    System.out.println("Cipher Text:\t" + cipherText);
  }
  
  /**
   * Shifts char value within char range by a specified number of characters 
   * 
   * @param ch char to shift
   * @param shift number of characters to amount to shift char by
   * @return shifted char
   */
  static char shiftChar(char ch, int shift) {
    char shiftedChar = (char) (ch + shift % CHAR_RANGE);
    
    // ensure shifted char is kept within the boundaries of our char range 
    if (shiftedChar <= UPPER_CHAR_LIMIT && shiftedChar >= LOWER_CHAR_LIMIT) {
      return shiftedChar;
    } else if (shiftedChar > UPPER_CHAR_LIMIT) {
      return (char) (LOWER_CHAR_LIMIT + shiftedChar % UPPER_CHAR_LIMIT - 1);
    } else {
      return (char) (UPPER_CHAR_LIMIT - LOWER_CHAR_LIMIT % shiftedChar + 1);
    }
  }
  
}