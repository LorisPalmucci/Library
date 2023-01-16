import Bookshelf.Book;

import java.sql.*;
import java.util.ArrayList;

public class DBConn {

    Connection con;
    Statement stm;
    PreparedStatement pst;
    public DBConn() {
        this.con = null;
        this.stm = null;
    }


    public void openDB() {
        /*
         * Crea un oggetto per connettersi al DB tramite una stringa URL indicando in essa rispettivamente:
         * 1. il tipo di db a cui mi voglio collegare ----> jdbc:derby;
         * 2. indirizzo e porta su cui gira il server DB in caso il DB risieda in remoto;
         * 3. il nome del DB su cui collegarsi ----> bookshelf;
         * 4. credenziali di accesso al DB: user, passwd se necessarie;
         * 5. 'create=true' per creare il DB
         */
        try {
            this.con = DriverManager.getConnection("jdbc:derby:bookshelf; create=true");
            System.out.println("Good to go");
        } catch (Exception E) {
            System.out.println("JDBC Driver error");
        }
    }

    public void closeDB() throws SQLException {
        //Chiude la connessione con il DB
        this.con.close();
    }

    public void createBook(String ISBN, String title) {
        /*
         * L'istruzione a seguire serve a creare una tabella:
         *
         * PreparedStatement pst = this.con.prepareStatement("create table book(ISBN int, title varchar(45))");
         */
        try {
            /*
             * Inserisce un libro tramite un preparedStatement che prende in ingresso una stringa SQL
             * i cui parametri sono:
             * 1. ISBN
             * 2. Titolo
             *
             * nella sezione 'values' i punti interrogativi servono per prendere dei parametri in ingresso da altre
             * variabili. Infatti la variabile pst setta il primo '?' come 'ISBN' e il secondo '?' come 'Titolo'
             * Infine si esegue l'update della variabile che memorizza i parametri nel DB
             *
             */
            this.pst = this.con.prepareStatement("Insert into book (ISBN, title) values (?,?)");
            this.pst.setString(1, ISBN);
            this.pst.setString(2, title);
            this.pst.executeUpdate();
            System.out.println("Book added");
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public ArrayList<String> returnBook() {
        ArrayList<String> s = new ArrayList<>();
        try {
            this.pst = this.con.prepareStatement("SELECT ISBN,title FROM book");
            ResultSet res = pst.executeQuery();
            while (res.next()){
                s.add(res.getString(2));
            }
            System.out.println("Returned List");
        }catch (Exception E){
            System.out.println(E);
        }
        return s;
    }

    public void removeSingleBook(Object book) {
        try {
            this.pst = this.con.prepareStatement("DELETE FROM book WHERE title = ?");
            this.pst.setString(1, (String) book);
            this.pst.executeUpdate();

            System.out.println("Book Deleted");
        }catch (Exception E){
            System.out.println(E);
        }
    }

    /**
     * Create new DB Table:
     * 1.Bookshelfs that contains all library
     * 2.Shlefs that contains all shelfs. Every shelf is binded to a single Bookshelfs
     * 3.Books that contanins all books. Every book is binded to a single shelf
     */
    public void intializeAll() {
        String sqlTable;
        try {
            this.stm = con.createStatement();
            sqlTable = "CREATE TABLE BOOKSHELFS(" +
                    "bookshelfsID INT NOT NULL PRIMARY KEY," +
                    "widht DOUBLE," +
                    "height DOUBLE," +
                    "location VARCHAR(25)," +
                    "numshelf INT)";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Create 'Bookshelfs Table' successfully");

            sqlTable = "CREATE TABLE SHELFS(" +
                    "shelfsID INT NOT NULL PRIMARY KEY," +
                    "widht DOUBLE)";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Create 'Shelfs Table' successfully");

            sqlTable = "CREATE TABLE BOOKS(" +
                    "isbn INT," +
                    "title VARCHAR(30) NOT NULL PRIMARY KEY," +
                    "author VARCHAR(25)," +
                    "type VARCHAR(25)," +
                    "printDate DATE," +
                    "insertDate DATE," +
                    "price DOUBLE)";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Create 'Books Table' successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Drop all DB's Tables
     */
    public void dropAll() {
        String sqlTable;
        try {
            this.stm = this.con.createStatement();
            sqlTable = "DROP TABLE BOOKS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Books");

            sqlTable = "DROP TABLE SHELFS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Shelfs");

            sqlTable = "DROP TABLE BOOKSHELFS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Bookshelfs");

        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Drop Bookshelfs Table
     */
    public void dropBookshelfs() {
        try {
            String sqlTable = "DROP TABLE BOOKSHELFS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Bookshelfs");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Drop Shelfs Table
     */
    public void dropShelfs() {
        try {
            String sqlTable = "DROP TABLE SHELFS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Shelfs");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Drop Books Table
     */
    public void dropBooks() {
        try {
            String sqlTable = "DROP TABLE BOOKS";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Dropped table Books");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Initialize Books Table
     */
    public void initializeBooks() {
            try{
                this.stm = this.con.createStatement();
                String sqlTable = "CREATE TABLE BOOKS(" +
                        "isbn INT," +
                        "title VARCHAR(30) NOT NULL PRIMARY KEY," +
                        "author VARCHAR(25)," +
                        "type VARCHAR(25)," +
                        "printDate DATE," +
                        "insertDate DATE," +
                        "price DOUBLE)";
                this.stm.executeUpdate(sqlTable);
                System.out.println("Create 'Books Table' successfully");
            }catch (Exception e){
                System.out.println(e);
            }
        }

    /**
     * Initialize Shelfs Table
     */
    public void initializeShelfs() {
        try {
            this.stm = this.con.createStatement();
            String sqlTable = "CREATE TABLE SHELFS(" +
                    "shelfsID INT NOT NULL PRIMARY KEY," +
                    "widht DOUBLE)";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Create 'Shelfs Table' successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * Initialize Bookshelfs Table
     */
    public void initializeBookshelfs() {
        try {
            this.stm = con.createStatement();
            String sqlTable = "CREATE TABLE BOOKSHELFS(" +
                    "bookshelfsID INT NOT NULL PRIMARY KEY," +
                    "widht DOUBLE," +
                    "height DOUBLE," +
                    "location VARCHAR(25)," +
                    "numshelf INT)";
            this.stm.executeUpdate(sqlTable);
            System.out.println("Create 'Bookshelfs Table' successfully");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
