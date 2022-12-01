import java.sql.*;

public class DBConn {

    Connection con;

    PreparedStatement pst;
    public DBConn() throws SQLException {
        this.con = null;
        PreparedStatement pst;
    }

    public void openDB() throws SQLException {
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

    public void createBook(String ISBN, String title) throws SQLException {
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

    public void returnBook(){
        try {
            this.pst = this.con.prepareStatement("SELECT ISBN,title FROM book");
            ResultSet res = pst.executeQuery();
            while (res.next()){

                System.out.println(res.getInt(1 ));
                System.out.println(res.getString(2) +"\n");
            }
            System.out.println("OK");
        }catch (Exception E){
            System.out.println(E);
        }
    }
}