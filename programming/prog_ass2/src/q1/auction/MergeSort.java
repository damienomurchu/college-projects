package q1.auction;

import java.util.ArrayList;

/**
 * Merge sort class to sort arrays of ints, or arraylists of Bid objects.
 * 
 * Array/ arraylist contents are sorted in ascending order.
 * (Bids are sorted by bid amount).
 * 
 * @author j. fitzgerald
 * @version 07.3.2016
 *
 * @author Damien Murphy
 * @version 1.0, 2016-04-02
 *
 */
public class MergeSort {
  
  /**
   * Temporary Bid array for sorting purposes
   */
  static Bid[] bidAux;
  
  /**
   * Temporary int array for sorting purposes
   */
  static int[] intAux;  
  
  /**
   * Creates an unsorted array of ints, prints the unsorted array, sorts it, and
   * prints the sorted array
   */
  public static void main(String[] args) {
    int[] a = {20, 24, 17, 12, 11, 14, 22, 19};
    print(a);
    sort(a);
    print(a);
  }

  /** 
   * Prints an array of ints passed in
   * 
   * @param a the array of ints to be printed 
   */
  public static void print(int[] a) {
    for (int i : a) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
  
  /**
   * Sorts an arraylist of Bid objects.
   * Bid objects are sorted by their bid amount.
   * 
   * @param bids the arraylist of Bid objects to be sorted
   */
  public static void sort(ArrayList<Bid> bids) {
    bidAux = new Bid[bids.size()];
    int N = bids.size();

    for (int size = 1; size < N; size = size + size) {
      for (int lo = 0; lo < N - size; lo += size + size) {
        merge(bids, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
      }
    }
  }
  
  /**
   * Sorts an arraylist of ints
   * 
   * @param a the array of ints to be sorted
   */
  public static void sort(int[] a) {
    intAux = new int[a.length];
    int N = a.length;

    for (int size = 1; size < N; size = size + size) {
      for (int lo = 0; lo < N - size; lo += size + size) {
        merge(a, lo, lo + size - 1, Math.min(lo + size + size - 1, N - 1));
      }
    }
  } 
    
  /**
   * Merges arraylist of bids for sorting purposes
   * 
   * @param bids arraylist of bids to be merged
   * @param lo first element in the arraylist to be sorted
   * @param mid mid element in the arraylist to be sorted
   * @param hi last element in the arraylist to be sorted
   */
  private static void merge(ArrayList<Bid> bids, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      bidAux[k] = bids.get(k);
    }

    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid)              
        bids.set(k, bidAux[j++]);
      else if (j > hi)          
        bids.set(k, bidAux[i++]);
      else if (bidAux[j].amountBid < bidAux[i].amountBid) 
        bids.set(k, bidAux[j++]);
      else                      
        bids.set(k, bidAux[i++]);
    }
  }

  /**
   * Merges array of bids for sorting purposes
   * 
   * @param a array of bids to be merged
   * @param lo first element in the array to be sorted
   * @param mid mid element in the array to be sorted
   * @param hi last element in the array to be sorted
   */
  private static void merge(int[] a, int lo, int mid, int hi) {
    for (int k = lo; k <= hi; k++) {
      intAux[k] = a[k];
    }

    int i = lo;
    int j = mid + 1;
    for (int k = lo; k <= hi; k++) {
      if (i > mid)                    a[k] = intAux[j++];
      else if (j > hi)                a[k] = intAux[i++];
      else if (intAux[j] < intAux[i]) a[k] = intAux[j++];
      else                            a[k] = intAux[i++];
    }
  }
  
}