package Bookshelf;

import java.util.ArrayList;

/**
 *
 */
public class Bookshelf {
    //larghezza della libreria
    private double width;
    //altezza della libreria
    private double height;
    //luogo dove si trova la libreria
    private String location;
    //numero di mensole
    private int numOfShelf;
    //Array contenente tutte le mensole della libreria
    private ArrayList shelfList;

    /**
     * Crea una nuova libreria con una larghezza e un certo numero di mensole
     *
     * @param width
     * @param numOfShelf
     *
     * @throws IllegalArgumentException
     *                              se il valore di <code>larghezza</code>, o
     *      *                                  <code>numOfShelf</code> è 0
     */
    public Bookshelf(double width, int numOfShelf){
        this.width = width;
        this.numOfShelf = numOfShelf;
        if ((width == 0)
                || (numOfShelf == 0))
            throw new IllegalArgumentException("Il numero passato non può essere 0");
    }

    /**
     * Crea una nuova libreria con una larghezza, altezza, locazione della libreria
     * e un numero di mensole
     *
     * @param width
     * @param height
     * @param location
     * @param numOfShelf
     *
     * @throws IllegalArgumentException
     *                                  se il valore di <code>larghezza</code>, <code>altezza</code> o
     *                                  <code>numOfShelf</code> è 0
     *
     * @throws NullPointerException
     *                                  se il valore di <code>location</code> è null
     */
    public Bookshelf(double width, double height, String location, int numOfShelf) {
        this.width = width;
        this.height = height;
        this.numOfShelf = numOfShelf;
        this.location = location;
    }

    /**
     * Modifica il valore della larghezza
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Modifica il valore dell'altezza
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Modifica il luogo dove è posizionata la libreria
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Modifica il numero di mensole
     *
     * @param numOfShelf
     */
    public void setNumOfShelf(int numOfShelf) {
        this.numOfShelf = numOfShelf;
    }

    /**
     *
     * @return la larghezza della libreria
     */
    public double getWidth() {
        return width;
    }

    /**
     *
     * @return l'altezza della libreria
     */
    public double getHeight() {
        return height;
    }

    /**
     *
     * @return il luogo dove è posizionata la libreria
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @return il numero di mensole della libreria
     */
    public int getNumOfShelf() {
        return numOfShelf;
    }

    private class Shelf{
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
}
