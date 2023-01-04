import Bookshelf.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Stack;

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
    private ListView list;
    DBConn db;
    public void initialize() {
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
        this.listBookButton();
    }

    /*
     * Questo metodo prende in ingresso i parametri passati dall'interfaccia, contente i dati di un libro
     * e poi chiama il metodo 'createBook' che consente la memorizzazione nel DB dei dati inseriti
     */
    @FXML
    private void insertButton() {
        //crea un nuovo libro con i parametri passati
        Book b = new Book(ISBN.getText(),
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
        this.listBookButton();
        //this.db.closeDB();
    }

    @FXML
    private void listBookButton(){
        this.list.getItems().clear();
        ArrayList<String> stringa = db.returnBook();
        for (String str :
                stringa) {
            this.list.getItems().add(str);
        }
    }

    @FXML
    private void deleteBook() {
        Object m = list.getSelectionModel().getSelectedItem();
        this.db.removeSingleBook(m);
        this.listBookButton();
    }

    @FXML
    private void addBookshelf() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newBookShelf.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
