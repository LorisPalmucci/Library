import Bookshelf.Book;
import javafx.fxml.FXML;
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
    Book b;

    public void initialize() {
        // TODO
        year.setText(String.valueOf(ZonedDateTime.now().getYear()));
        insDate.setText(ZonedDateTime.now().getDayOfMonth() + "/"
                + ZonedDateTime.now().getMonthValue() + "/"
                + ZonedDateTime.now().getYear() + " - "
                + ZonedDateTime.now().getHour() + ":"
                + ZonedDateTime.now().getMinute());
    }

    @FXML
    private void insertButton() throws SQLException {
        DBConn db = new DBConn();
        db.openDB();
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

        db.createBook(b.getISBN(), b.getTitle());
        db.closeDB();


    }
}
