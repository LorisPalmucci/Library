package Bookshelf;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Book is a class that create a type object book with some parameters like ISBN, Author....
 *
 * @author Palmucci Loris
 */
public class Book implements Comparable {
    private String ISBN;
    private String title;
    private String[] author;

    private String type;
    //data di stampa/scrittura del libro
    private GregorianCalendar date;
    //contiene la data d'inserimento del libro nell'archivio
    private ZonedDateTime insertDate;
    private double price;

    /**
     *This constructor build a new book object that take these parameters:
     *
     * @param ISBN      univoque ID
     * @param title     book title
     * @param author    book author
     * @param date      book print/write date
     * @param price     price in "€"
     */
    public Book(String ISBN, String title, String author, String type, GregorianCalendar date, double price){
        if ((ISBN == null) || (title == null) || (author == null))
            throw new NullPointerException("ISBN o Titolo o Autore non sono validi");

        this.ISBN = ISBN;
        this.title = title;
        this.author = new String[10];
        this.author[0] = author;
        this.type = type;
        this.date = date;
        //this.insertDate = ZonedDateTime.now();
        this.price = price;
    }

    /**
     * @param ISBN ISBN
     */
    public void setISBN(String ISBN){
        this.ISBN = ISBN;
    }

    /**
     * @param title title
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * @param author author
     */
    public void setAuthor(String[] author){
        this.author = author;
    }

    /**
     * @param type
     */
    public void setType(String type){
        this.type = type;
    }

    /**
     * @param year      publication year
     * @param month     publication month
     * @param day       publication day
     */
    public  void setBookYear(int day, int month, int year){
        this.date = new GregorianCalendar(year, month, day);
    }

    /**
     *
     * @param year
     */
    public void setBookYear(int year) {
        this.date = new GregorianCalendar(year, 1, 1);
    }

    /**
     * @param price
     */
    public void setPrice(double price){
        if ((Object) price == null)
            price = 0;
        this.price = price;
    }

    /**
     * @return
     */
    public String getISBN(){return this.ISBN;}

    /**
     * @return
     */
    public String getTitle(){return this.title;}

    /**
     * @return
     */
    public String[] getAuthor(){return this.author;}

    /**
     * @return
     */
    public String getType(){return this.type;}

    /**
     * @return
     */
    public GregorianCalendar getBookYear(){return this.date;}

    /**
     * @return
     */
    public int getMonth(){return this.date.get(Calendar.MONTH);}
    /**
     * @return
     */
    public int getDay(){return this.date.get(Calendar.DAY_OF_MONTH);}


    /**
     *
     * @return
     */
    public String getInsertDate(){
        this.insertDate = ZonedDateTime.now();
        return insertDate.getDayOfMonth() + "/"
                + insertDate.getMonthValue() + "/"
                    +insertDate.getYear() + " - "
                        + insertDate.getHour() + ":"
                            + insertDate.getMinute();
    }

    /**
     * @return
     */
    public double getPrice(){return this.price;}

    /**
     * @return  the HashCode from ISBN, authors, title and genre
     */
    @Override
    public int hashCode(){
        int res = 1;
        int authorHash = 0;
        for (String s : this.author) {
            authorHash += (s == null) ? (1) : (s.hashCode());
        }
        res *= 31 + this.ISBN.hashCode()
                * authorHash
                    * this.title.hashCode()
                        * this.type.hashCode();
        return res;
    }

    /**
     * Two books are equals if their ISB are equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (o instanceof Book){
            Book anotherBook = (Book) o;
            if (this.ISBN.equals(anotherBook.ISBN))
                return true;
        }
        return false;
    }

    /**
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Book anotherBook = (Book) o;
        //se gli anni sono diversi va a controllare quale sia il più grande
        if (this.insertDate.getYear() != anotherBook.insertDate.getYear())
            //l'anno di questo libro è minore
            if (this.insertDate.getYear() < anotherBook.insertDate.getYear())
                return -1;
            //l'anno di questo libro è maggiore
            else
                return 1;
        //se gli anni sono uguali controlla se i mesi sono diversi e va a controllare quale sia il più grande
        if (this.insertDate.getMonth().compareTo(anotherBook.insertDate.getMonth()) != 0)
            //il mese di questo libro è minore
            if (this.insertDate.getMonth().compareTo(anotherBook.insertDate.getMonth()) < 1)
                return -1;
            //il mese di questo libro è maggiore
            else
                return 1;
        //se i mesi sono uguali controlla se i giorni sono diversi e va a controllare quale sia il più grande
        if (this.insertDate.getDayOfMonth() != anotherBook.insertDate.getDayOfMonth())
            //il giorno di questo libro è minore
            if (this.insertDate.getDayOfMonth() < anotherBook.insertDate.getDayOfMonth())
                return -1;
        //il giorno di questo libro è maggiore quindi è il più grande
        return 1;
    }
}
