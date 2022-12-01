import Bookshelf.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.util.GregorianCalendar;

public class GuiController {

    @FXML
    private TextField ISBN;
    @FXML
    private TextField titolo;
    @FXML
    private TextField author;
    @FXML
    private TextField type;
    @FXML
    private TextField price;
    @FXML
    private TextField day;
    @FXML
    private TextField month;
    @FXML
    private TextField insDate;
    @FXML
    private TextField year;

    @FXML
    private Button bookList;
    Book b;
    DBConn db;
    public void initialize() throws SQLException {
        // TODO
        //Crea ed apre la connessione al DB
        this.db = new DBConn();
        this.db.openDB();
        //----------------
        year.setText(String.valueOf(ZonedDateTime.now().getYear()));
        insDate.setText(ZonedDateTime.now().getDayOfMonth() + "/"
                + ZonedDateTime.now().getMonthValue() + "/"
                + ZonedDateTime.now().getYear() + " - "
                + ZonedDateTime.now().getHour() + ":"
                + ZonedDateTime.now().getMinute());
    }

    /*
     * Questo metodo prende in ingresso i parametri passati dall'interfaccia, contente i dati di un libro
     * e poi chiama il metodo 'createBook' che consente la memorizzazione nel DB dei dati inseriti
     */
    @FXML
    private void insertButton() throws SQLException {
        //crea un nuovo libro con i parametri passati
        b = new Book(ISBN.getText(),
                titolo.getText(),
                author.getText(),
                type.getText(),
                new GregorianCalendar(Integer.parseInt(day.getText()),
                        Integer.parseInt(month.getText()),
                        Integer.parseInt(year.getText())),
                Double.parseDouble(price.getText()));
        System.out.println(b.getBookYear());
        insDate.setText(b.getInsertDate());
        //chiama il metodo per inserire il libro nel DB
        this.db.createBook(b.getISBN(), b.getTitle());
        this.db.closeDB();
    }

    @FXML
    private void addAuthor() throws SQLException {
       this.db.returnBook();
    }
}
