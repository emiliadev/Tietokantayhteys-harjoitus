package tietokanta;

import java.sql.*;

public class YhteydenHallinta {

    public static Connection avaaYhteys(String ajuri, String url, String kayttaja, String salasana) throws SQLException, Exception {
        try {
            Class.forName(ajuri).newInstance();
            return DriverManager.getConnection(url, kayttaja, salasana);
        } catch (SQLException sqle) {
            throw new SQLException("Yhteyden avaaminen ei onnistunut", sqle);
        } catch (ClassNotFoundException e) {
            throw new Exception("Ajuria ei löytynyt");
        } catch (InstantiationException ie) {
            throw new Exception("Ajurivirhe");
        } catch (IllegalAccessException iae) {
            throw new Exception("Ajurivirhe");
        }
    }

    public static boolean suljeYhteys(Connection yhteys) {
        if (yhteys != null) {
            try {
                yhteys.close();
                return true;
            } catch (SQLException sqle) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void suljeLause(Statement suljettavaLause) {
        if (suljettavaLause != null) {
            try {
                suljettavaLause.close();
            } catch (SQLException sqle) {
            }
        }
    }

    public static boolean suljeTulosjoukko(ResultSet suljettavaTulosjoukko) {
        if (suljettavaTulosjoukko != null) {
            try {
                suljettavaTulosjoukko.close();
                return true;
            } catch (SQLException sqle) {
                return false;
            }
        }
        return false;
    }
}
