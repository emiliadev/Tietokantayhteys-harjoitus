package tietokanta;

import datat.Materiaali;
import java.sql.*;

import datat.Tuote;
import java.awt.List;
import java.util.ArrayList;

public class Tietovarasto {

    private String ajuri = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/tuotehallinta";
    private String kayttajatunnus = "root";
    private String salasana = "";

    String tuotteenLisaysSQL = "insert into Tuote(tuoteID, valmistajaID,materiaaliID,tuotetyyppi,kokolajitelma,sesonki)values(?,?,?,?,?,?)";//hakulauseet
    String tuotteenHakuSQL = "select tuoteID, tuotetyyppi,valmistajaID,Materiaali.materiaaliID,kuvaus,koostumus,hinta,kokolajitelma,sesonki FROM Tuote JOIN Materiaali ON Tuote.materiaaliID=Materiaali.materiaaliID where tuoteID=?";
    String tuotteidenHakuSQL = "select * from Tuote order by tuoteID";
    String materiaalienHakuSQL = "select * from Materiaali order by materiaaliID";
    String sqlMuutaMateriaalinTietoja = "update Materiaali set kuvaus=?, koostumus=?, hinta=? WHERE materiaaliID=?";
    String sqlPoistaTuote = "delete from Tuote where tuoteID=?";

    public Tuote haeTuote(String tuoteID) {
        Connection yhteys = null;
        PreparedStatement tuotteenHaku = null;
        Tuote aputuote = null;
        Materiaali apumateriaali = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
            tuotteenHaku = yhteys.prepareStatement(tuotteenHakuSQL);
            tuotteenHaku.setString(1, tuoteID);
            ResultSet tuotetulos = tuotteenHaku.executeQuery();
            if (tuotetulos.next()) {
                apumateriaali = new Materiaali(tuotetulos.getInt(4), tuotetulos.getString(5), tuotetulos.getString(6), tuotetulos.getDouble(7));
                aputuote = new Tuote(apumateriaali, tuotetulos.getString(1),
                        tuotetulos.getString(3),
                        tuotetulos.getInt(4),
                        tuotetulos.getString(2),
                        tuotetulos.getString(8),
                        tuotetulos.getString(9));

            }
        } catch (SQLException sqle) {
            System.out.println("Sql-virhe: " + sqle);
            return null;
        } catch (Exception e) {
            System.out.println("Virhe: " + e);
        } finally {
            YhteydenHallinta.suljeLause(tuotteenHaku);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
        return aputuote;
    }

    public void poistaTuote(String tuoteID) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }

        PreparedStatement poistaTuote = null;
        try {
            poistaTuote = yhteys.prepareStatement(sqlPoistaTuote);
            poistaTuote.setString(1, tuoteID);
            poistaTuote.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Tuotteen poistaminen ei onnistunut", sqle);
        } finally {
            YhteydenHallinta.suljeLause(poistaTuote);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public void lisaaTuote(Tuote lisattava) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki.", e);
        }
        PreparedStatement tuotteenLisays = null;
        try {
            tuotteenLisays = yhteys.prepareStatement(tuotteenLisaysSQL);
            tuotteenLisays.setString(1, lisattava.getTuoteID());
            tuotteenLisays.setString(2, lisattava.getValmistajaID());
            tuotteenLisays.setInt(3, lisattava.getMateriaaliID());
            tuotteenLisays.setString(4, lisattava.getTuotetyyppi());
            tuotteenLisays.setString(5, lisattava.getKokolajitelma());
            tuotteenLisays.setString(6, lisattava.getSesonki());
            tuotteenLisays.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new Exception("Tuotteen lisäys ei onnistu.", sqle);
        }
    }

    public ArrayList<Tuote> haeKaikkiTuotteet() throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement haeKaikkiTuotteet
                = null;
        ResultSet tulos
                = null;
        try {
            ArrayList<Tuote> apulista = new ArrayList<Tuote>();
            haeKaikkiTuotteet = yhteys.prepareStatement(tuotteidenHakuSQL);
            tulos = haeKaikkiTuotteet.executeQuery();
            while (tulos.next()) {
                apulista.add(new Tuote(tulos.getString(1),
                        tulos.getString(2),
                        tulos.getInt(3),
                        tulos.getString(4),
                        tulos.getString(5),
                        tulos.getString(6)));
            }
            return apulista;
        } catch (SQLException sqle) {
            throw new Exception("Materiaalien haku epäonnistui", sqle);
        } finally {
            YhteydenHallinta.suljeTulosjoukko(tulos);
            YhteydenHallinta.suljeLause(haeKaikkiTuotteet);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    

    public ArrayList<Materiaali> haeKaikkiMateriaalit() throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement haeKaikkiMateriaalit
                = null;
        ResultSet tulos
                = null;
        try {
            ArrayList<Materiaali> apulista = new ArrayList<Materiaali>();
            haeKaikkiMateriaalit = yhteys.prepareStatement(materiaalienHakuSQL);
            tulos = haeKaikkiMateriaalit.executeQuery();
            while (tulos.next()) {
                apulista.add(new Materiaali(tulos.getInt(1),
                        tulos.getString(2),
                        tulos.getString(3),
                        tulos.getDouble(4)));
            }
            return apulista;
        } catch (SQLException sqle) {
            throw new Exception("Materiaalien haku epäonnistui", sqle);
        } finally {
            YhteydenHallinta.suljeTulosjoukko(tulos);
            YhteydenHallinta.suljeLause(haeKaikkiMateriaalit);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

    public void muutaMateriaalinTietoja(Materiaali muutettuMateriaali) throws Exception {
        Connection yhteys = null;
        try {
            yhteys = YhteydenHallinta.avaaYhteys(ajuri, url, kayttajatunnus, salasana);
        } catch (Exception e) {
            throw new Exception("Tietovarasto ei ole auki", e);
        }
        PreparedStatement muutaMateriaalinTietoja = null;
        try {
            muutaMateriaalinTietoja = yhteys.prepareStatement(sqlMuutaMateriaalinTietoja);
            muutaMateriaalinTietoja.setString(1, muutettuMateriaali.getKuvaus());
            muutaMateriaalinTietoja.setString(2, muutettuMateriaali.getKoostumus());
            muutaMateriaalinTietoja.setDouble(3, muutettuMateriaali.getHinta());
            muutaMateriaalinTietoja.setInt(4, muutettuMateriaali.getMateriaaliID());
            muutaMateriaalinTietoja.executeUpdate();
        } catch (SQLException sqle) {
            throw new Exception("Materiaalin tietojen muuttaminen ei onnistunut", sqle);
        } finally {
            YhteydenHallinta.suljeLause(muutaMateriaalinTietoja);
            YhteydenHallinta.suljeYhteys(yhteys);
        }
    }

}
