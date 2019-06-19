import java.util.ArrayList;

/**
 * @file    Library.java
 * @brief   This class defines the basic operations of a library, namely the 
 *          holding and lending of books.
 * @version 1.0, 2016-02-10 
 * @author  Damien Murphy 
 */

public class Library{
    
    private ArrayList<Book> books;
    
    /**
     * Constructs a new library object, initialising ArrayList to hold books    
     */
    public Library(){
        books = new ArrayList<Book>();
    }
    
    /**
     * Adds book to library
     */
    public void add(Book book){
        books.add(book);
    }
    
    /**
     * Returns number of books held in library
     * @return the number of books in the library
     */
    public int numberBooks(){
        return books.size();
    }
    
    /**
     * Retrieves loan status of a book
     * @param book book to check
     * @return the loan status of the book, or a message the book is not in the library
     */
    public String loanStatus(Book book){
        if (books.contains(book)){
            return book.loanStatus();
        }
        return "This library does not have that book";
    }
    
    /**
     * Checks whether library contains book
     * @param book book to check library for
     * @return the availability of the book, or a message the book is not in the library
     */
    public String hasBook(Book book){
        if (books.contains(book) && book.isBorrowed()){
            return new String ("Book title " + book.getTitle() + " in currently on loan and unavailable to borrow");
        } else if (books.contains(book) && !(book.isBorrowed()) ){
            return new String ("Book title " + book.getTitle() + " in stock and available to borrow");
        } else {
            return new String ("We do not stock book title " + book.getTitle());
        }
    }
    
    /**
     * Removes a book from the library
     * @param book book to remove from library
     * @return a boolean indicating the success of the removal
     */
    public boolean removeBook(Book book){     
        return books.remove(book);
    }
    
    /**
     * Removes all books from the library
     */
    public void removeAllBooks(){
        books.clear();
    }
    
    /**
     * Print all book details of books held in library
     */
    public void printDetailsAll(){
        for (Book book : books){
            book.printDetails();
        }
    }    
}