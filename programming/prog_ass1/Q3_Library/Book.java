/**
 * @file    Book.java
 * @brief   This class defines the basic description of a book and provides some
 *          characteristic methods.
 * @version 1.0, 2016-02-10 
 * @author  Damien Murphy 
 */

public class Book{
    private String title;
    private String author;
    private String isbn;
    private int numberPages;
    private boolean borrowed;
    private int numberBorrowings;
    
    /**
     * Book constructor that takes title, author, number of pages, and isbn
     */
    public Book(String title, String author, int numberPages, String isbn){
        this.title = title;
        this.author = author;
        this.numberPages = numberPages;
        this.isbn = isbn;
    }
    
    /**
     * Borrow a book
     */
    public void borrow(){
        borrowed = true;
        numberBorrowings++;
    }
    
    /**
     * Checks whether a book is borrowed
     */
    public boolean isBorrowed(){
        return borrowed;
    }
    
    /**
     * Returns a book
     */
    public void returns(){
        borrowed = false;
    }
    
    /**
     * Prints loan status of book
     */
    public String loanStatus(){
        if(isBorrowed()){
            return title + " not available: presently on loan";
        } else {
            return title + " is available";
        }
    }
    
    /**
     * Prints details of book
     */
    public void printDetails(){
        System.out.println("Title\t\t: " + title);
        System.out.println("Author\t\t: " + author);
        System.out.println("Pages\t\t: " + numberPages);
        System.out.println("ISBN\t\t: " + isbn);
        System.out.println("Borrowed\t: " + numberBorrowings + " times");
        System.out.println("Availability\t: " + loanStatus() +"\n");
    }
    
    /**
     * Prints title of book
     */
    public String getTitle(){
        return title;
    }
}