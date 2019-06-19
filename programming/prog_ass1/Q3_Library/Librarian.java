
public class Librarian
{  
    Library library;
    
    public Librarian()
    {
        library = new Library();
    }

    public void unitTest()
    {
        // Create a set of 4 books books
        Book book1 = new Book("Objects First with Java", "Barnes & Kolling", 250, "978-0-13-249266-9");
        Book book2 = new Book("Head First Android Development","Griffiths & Griffiths", 250, "978-1-449362188");  
        Book book3 = new Book("Learning Android", "Gargenta & Nakamura", 600, "978-1-449-31923-6");
        Book book4 = new Book("Android Programming", "Phillips & Hardy", 500, "978-0-321804334");
        
        // Add to library
        library.add(book1);
        library.add(book2);
        library.add(book3);
        library.add(book4);
        //library.add(book4);
        
        // Borrow and return
        book1.borrow();
        book1.returns();
        book1.borrow();
        
        // Print details & output number books
        System.out.println("Adding 4 books to library");
        library.printDetailsAll();
        System.out.println("Number books in library is " + library.numberBooks());
       
        // Remove a book, verify book no longer in library
        System.out.println("Removing a book from library stock");
        library.removeBook(book4);
        System.out.println("Number books in library is " + library.numberBooks());
        System.out.println(library.hasBook(book4));
        library.printDetailsAll();
        
        // Restore book
        System.out.println("Putting back the book we removed");
        library.add(book4);
        System.out.println(library.hasBook(book4));
        System.out.println("Number books in library is " + library.numberBooks());
        
        // Remove all books and verify library empty
        System.out.println("Austerity bites. Shutting down library. Selling off all books");
        library.removeAllBooks();
        System.out.println("Number books in library is " + library.numberBooks());
    }
    
}
