import java.sql.*;

public class DBConn {

    Connection con;

    public DBConn() throws SQLException {
        this.con = null;
    }

    public void openDB() throws SQLException {
        /*
         *Crea un oggetto per connettersi al DB tramite una stringa URL indicando in essa rispettivamente:
         * 1. il tipo di db a cui mi voglio collegare
         * 2. indirizzo e porta su cui gira il server DB
         * 3. il nome del DB su cui collegarsi
         * 4. credenziali di accesso al DB: user, passwd
         */
        this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelf", "root", "parabellum3");
    }

    public void closeDB() throws SQLException {
        //Chiude la connessione con il DB
        this.con.close();
    }

    public void createBook(String ISBN, String title) throws SQLException {
        PreparedStatement pst = this.con.prepareStatement("insert into book(ISBN, title) values (?,?)");
        pst.setString(1, ISBN);
        pst.setString(2, title);
        pst.executeUpdate();
    }

    public static void main(String[] args) {
        try {
            System.out.println("Good to go");
        } catch (Exception E) {
            System.out.println("JDBC Driver error");
        }
    }
}