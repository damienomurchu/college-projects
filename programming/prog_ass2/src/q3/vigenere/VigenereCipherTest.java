package q3.vigenere;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the VigenereCipher class
 * 
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 */
public class VigenereCipherTest {

  VigenereCipher vc1 = new VigenereCipher();
  
  /**
   * Generates VigenereTable to facilitate testing
   * 
   * @throws Exception
   */
  @Before
  public void setUp() throws Exception {
    VigenereCipher.generateVigenereTable();
  }

  /**
   * Tests generateVigenereTable(). 
   * Tests by vaildating the values of all elements in the VigenereTable.
   */
  @Test
  public void testGenerateVigenereTable() {
    //check each element is one char greater than in the column immediately preceding it
    VigenereCipher.generateVigenereTable();
    for (int i = 0; i < 26; i++) {
      for (int j = 0; j < 26; j++) {
        assert(VigenereCipher.vigenereTable[i][j] == (char) ('A' + i + j));  
      }
    }
  }

  /**
   * Tests generateKey().
   * Checks negative values for keyLength are handled appropriately.
   * Checks punctuation and case validation on input text is performed.
   */
  @Test
  public void testGenerateKey() {
    assertEquals("ABCDEABCDEAB", vc1.generateKey("ABCDE", 12));
    assertEquals(null, vc1.generateKey(".,'[}12345abcdeFGHIJ", -1));
    assertNotEquals(".,'[}12345abcdeFGHIJ", vc1.generateKey(".,'[}12345abcdeFGHIJ", 20));
    assertEquals("ABCDEFGHIJ", vc1.generateKey(".,'[}12345abcdeFGHIJ", 10));
    assertEquals("ABCDEFGHIJABC", vc1.generateKey(".,'[}12345abcdeFGHIJ", 13));
  }

  /**
   * Tests encrypt().
   * Checks permutations of keylength and keyword length are handled correctly.
   * Checks expected encrypted string values are returned.
   * Checks punctuation and case validation is performed on input text. 
   */
  @Test
  public void testEncrypt() {
    assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZA", 
        vc1.encrypt("BBBBBB", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
        vc1.encrypt("AAAAAA", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZA", 
        vc1.encrypt("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZA", 
        vc1.encrypt("BBBBBB", ",A.B/C|D!E£F$G%H^I&J*K(L)M-N=O_P+Q~R@S`T:U;V[W]X?Y<Z>#"));
    assertEquals("BCDEFGHIJKLMNOPQRSTUVWXYZA", 
        vc1.encrypt(",B.B/B?B'B#B£", ",A.B/C|D!E£F$G%H^I&J*K(L)M-N=O_P+Q~R@S`T:U;V[W]X?Y<Z>#"));
  }

  /**
   * Tests decrypt().
   * Checks permutations of keylength and keyword length are handled correctly.
   * Checks expected decrypted string values are returned.
   * Checks punctuation and case validation is performed on input text. 
   */
  @Test
  public void testDecrypt() {
    assertEquals("ZABCDEFGHIJKLMNOPQRSTUVWXY", 
        vc1.decrypt("BBBBBB", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
        vc1.decrypt("AAAAAA", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("ZABCDEFGHIJKLMNOPQRSTUVWXY", 
        vc1.decrypt("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    assertEquals("ZABCDEFGHIJKLMNOPQRSTUVWXY", 
        vc1.decrypt("BBBBBB", ",A.B/C|D!E£F$G%H^I&J*K(L)M-N=O_P+Q~R@S`T:U;V[W]X?Y<Z>#"));
    assertEquals("ZABCDEFGHIJKLMNOPQRSTUVWXY", 
        vc1.decrypt(",B.B/B?B'B#B£", ",A.B/C|D!E£F$G%H^I&J*K(L)M-N=O_P+Q~R@S`T:U;V[W]X?Y<Z>#"));
  }
  
  /**
   * Tests private method shiftChar().
   * Checks expected char values are returned from shifts from positive and negative shifts.
   * Checks returned char values are kept in permitted char range.
   * Checks positive and negative shifts greater than the range are handled.
   */
  @Test
  public void testShiftChar() {
    assertEquals('B', VigenereCipher.shiftChar('A', 1));
    assertEquals('Z', VigenereCipher.shiftChar('A', -1));
    assertEquals('A', VigenereCipher.shiftChar('Z', 1));
    assertEquals('Y', VigenereCipher.shiftChar('Z', -1));
    assertEquals('A', VigenereCipher.shiftChar('A', 26));
    assertEquals('A', VigenereCipher.shiftChar('A', 52));
    assertEquals('A', VigenereCipher.shiftChar('A', -26));
    assertEquals('A', VigenereCipher.shiftChar('A', -52));
  }

}
