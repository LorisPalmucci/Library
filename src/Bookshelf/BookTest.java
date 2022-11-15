package Bookshelf;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    Book b1 = new Book("9788804682561", "Necronomicon", "Howard Philips Lovecraft", "Horror",new GregorianCalendar(2021,1,1), 25.00);
    Book b2 = new Book("9788879845397", "Kojiki", "Luni Editore", "Racconti",new GregorianCalendar(2020,1,1), 32.00);
    Book b3 = new Book("9788804682561", "Necronomicon", "", "Horror",new GregorianCalendar(2021,1,1), 25.00);
    Book b4 = new Book("", "Concetti di informatica e fonamenti di Java", "Cay Horstman", "",new GregorianCalendar(0,1,1), 0);

    @Test
    public void setTest(){
        b4.setISBN("9788850329564");
        assertTrue(b4.getISBN() == "9788850329564");

        b4.setBookYear(2021);
        assertTrue(b4.getBookYear().get(Calendar.YEAR) == 2021);

        b4.setBookYear(5,10,2021);
        assertEquals(new GregorianCalendar(2021,10,5), b4.getBookYear());

        b4.setType("Manuale Programmazione");
        assertTrue(b4.getType() == "Manuale Programmazione");

        b4.setPrice(45.00);
        assertTrue(b4.getPrice() == 45.00);
    }

    @Test
    public void getTest(){
        assertTrue(b1.getISBN() == "9788804682561");
        assertTrue(b1.getTitle() == "Necronomicon");
        assertTrue(b1.getAuthor()[0] == "Howard Philips Lovecraft");
        assertTrue(b1.getType() == "Horror");
        assertTrue(b1.getPrice() == 25.00);
        assertEquals(new GregorianCalendar(2021,1,1), b1.getBookYear());

        b1.setBookYear(23,8,2015);
        assertTrue(b1.getBookYear().get(Calendar.YEAR) == 2015);
        assertTrue(b1.getBookYear().get(Calendar.MONTH) == 8);
        assertTrue(b1.getBookYear().get(Calendar.DAY_OF_MONTH) == 23);

        b1.setPrice(45.00);
        assertTrue(b1.getPrice() == 45.00);
    }

    @Test
    public void compareToTest(){
        assertEquals(1, b1.compareTo(b2));
    }

    @Test
    public void equalsTest(){
        assertFalse(b1.equals(b2));
        assertTrue(b1.equals(b3));
    }

}
