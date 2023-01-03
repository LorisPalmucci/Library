package Bookshelf;


import java.util.ArrayList;

/**
 * Questa classe rappresenta una mensola di una determinata lunghezza che può contenere un determinato numeri di libri.
 * Una mensola è parte di una sola libreria e non può esistere in un'altra libreria
 */
public class Shelf {

    private double width;

    private ArrayList arrayOfBook;

    private int shelfID;

    public Shelf(double width){
        this.width = width;
        this.shelfID = 0;
    }

    public void setShelfID(){
        this.shelfID++;
    }

    public void addBook(Book book){
        arrayOfBook.add(book);

    }
}
