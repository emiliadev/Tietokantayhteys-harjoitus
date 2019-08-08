package kayttoliittyma;

import datat.Tuote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tietokanta.Tietovarasto;

public class TuoteHaku extends JFrame {

    private JLabel lbTuoteID = new JLabel("TuoteID");
    private JTextField tfTuoteID = new JTextField(20);
    private JLabel lbTuotetyyppi = new JLabel("Tuotetyyppi");
    private JTextField tfTuotetyyppi = new JTextField(20);
    private JLabel lbValmistajaID = new JLabel("Valmistaja");
    private JTextField tfValmistajaID = new JTextField(20);
    private JLabel lbMateriaaliID = new JLabel("MateriaaliID");
    private JTextField tfMateriaaliID = new JTextField(20);
    private JLabel lbKuvaus = new JLabel("Materiaalin kuvaus");
    private JTextField tfKuvaus = new JTextField(50);
    private JLabel lbKoostumus = new JLabel("Materiaalin koostumus");
    private JTextField tfKoostumus = new JTextField(30);
    private JLabel lbHinta = new JLabel("Materiaalin hinta €/m");
    private JTextField tfHinta = new JTextField(10);
    private JLabel lbKokolajitelma = new JLabel("Kokolajitelma");
    private JTextField tfKokolajitelma = new JTextField(20);
    private JLabel lbSesonki = new JLabel("Sesonki");
    private JTextField tfSesonki = new JTextField(20);
    private JButton bHae = new JButton("Hae");
    private JButton bTyhjenna = new JButton("Tyhjennä");
    private JPanel pohjapaneeli = new JPanel();
    private Tietovarasto kanta = new Tietovarasto();

    public TuoteHaku(Tietovarasto kanta) {
        this.kanta = kanta;
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup seliteX = asettelu.createParallelGroup();
        seliteX.addComponent(lbTuoteID);
        seliteX.addComponent(lbTuotetyyppi);
        seliteX.addComponent(lbValmistajaID);
        seliteX.addComponent(lbMateriaaliID);
        seliteX.addComponent(lbKuvaus);
        seliteX.addComponent(lbKoostumus);
        seliteX.addComponent(lbHinta);
        seliteX.addComponent(lbKokolajitelma);
        seliteX.addComponent(lbSesonki);

        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup();
        kentatX.addComponent(tfTuoteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfTuotetyyppi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfValmistajaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfMateriaaliID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKuvaus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKoostumus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfHinta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKokolajitelma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfSesonki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        tfTuotetyyppi.setEditable(false);
        tfValmistajaID.setEditable(false);
        tfMateriaaliID.setEditable(false);
        tfKuvaus.setEditable(false);
        tfKoostumus.setEditable(false);
        tfKokolajitelma.setEditable(false);
        tfSesonki.setEditable(false);
        tfHinta.setEditable(false);

        GroupLayout.SequentialGroup ylaX = asettelu.createSequentialGroup();
        ylaX.addGroup(seliteX);
        ylaX.addGroup(kentatX);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaX);
        pohjaX.addComponent(bHae); 
        pohjaX.addComponent(bTyhjenna);

        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.ParallelGroup tuoteidriviY = asettelu.createParallelGroup();
        tuoteidriviY.addComponent(lbTuoteID);
        tuoteidriviY.addComponent(tfTuoteID);

        GroupLayout.ParallelGroup tuotetyyppiriviY = asettelu.createParallelGroup();
        tuotetyyppiriviY.addComponent(lbTuotetyyppi);
        tuotetyyppiriviY.addComponent(tfTuotetyyppi);

        GroupLayout.ParallelGroup valmistajaidriviY = asettelu.createParallelGroup();
        valmistajaidriviY.addComponent(lbValmistajaID);
        valmistajaidriviY.addComponent(tfValmistajaID);

        GroupLayout.ParallelGroup materiaaliidriviY = asettelu.createParallelGroup();
        materiaaliidriviY.addComponent(lbMateriaaliID);
        materiaaliidriviY.addComponent(tfMateriaaliID);

        GroupLayout.ParallelGroup kuvausriviY = asettelu.createParallelGroup();
        kuvausriviY.addComponent(lbKuvaus);
        kuvausriviY.addComponent(tfKuvaus);

        GroupLayout.ParallelGroup koostumusriviY = asettelu.createParallelGroup();
        koostumusriviY.addComponent(lbKoostumus);
        koostumusriviY.addComponent(tfKoostumus);
        
        GroupLayout.ParallelGroup hintariviY = asettelu.createParallelGroup();
        hintariviY.addComponent(lbHinta);
        hintariviY.addComponent(tfHinta);

        GroupLayout.ParallelGroup kokolajitelmariviY = asettelu.createParallelGroup();
        kokolajitelmariviY.addComponent(lbKokolajitelma);
        kokolajitelmariviY.addComponent(tfKokolajitelma);

        GroupLayout.ParallelGroup sesonkiriviY = asettelu.createParallelGroup();
        sesonkiriviY.addComponent(lbSesonki);
        sesonkiriviY.addComponent(tfSesonki);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(tuoteidriviY);
        pohjaY.addGroup(tuotetyyppiriviY);
        pohjaY.addGroup(valmistajaidriviY);
        pohjaY.addGroup(materiaaliidriviY);
        pohjaY.addGroup(kuvausriviY);
        pohjaY.addGroup(koostumusriviY);
        pohjaY.addGroup(hintariviY);
        pohjaY.addGroup(kokolajitelmariviY);
        pohjaY.addGroup(sesonkiriviY);
        pohjaY.addComponent(bHae);
        pohjaY.addComponent(bTyhjenna);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setLocation(100, 100);
        this.setTitle("Tuotteiden haku");
        this.pack();
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });
        bHae.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                haeTuote();
            }
        });
        bTyhjenna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TyhjennaKentat();
            }
        });
    }

    private void suoritaLopputoimet() {
        this.dispose();
    }

    private void haeTuote() {

        try {

            Tuote haettuTuote = kanta.haeTuote(tfTuoteID.getText());
            System.out.println(haettuTuote.getMatsku().getKuvaus());
            tfValmistajaID.setText(haettuTuote.getValmistajaID());
            tfMateriaaliID.setText("" + haettuTuote.getMatsku().getMateriaaliID());  //muuttaa stringiksi
            tfKuvaus.setText(haettuTuote.getMatsku().getKuvaus());
            tfKoostumus.setText(haettuTuote.getMatsku().getKoostumus());
            tfHinta.setText(""+haettuTuote.getMatsku().getHinta());
            tfTuotetyyppi.setText(haettuTuote.getTuotetyyppi());
            tfKokolajitelma.setText(haettuTuote.getKokolajitelma());
            tfSesonki.setText(haettuTuote.getSesonki());
        } catch (Exception vt) {
            JOptionPane.showMessageDialog(this, vt.getMessage(), "Virhe", JOptionPane.ERROR_MESSAGE);
            tfTuoteID.setText("");
            tfValmistajaID.setText("");          //tyhjenna etunimi
            tfMateriaaliID.setText("");         //tyhjenna sukunimi
            tfKuvaus.setText("");
            tfKoostumus.setText("");
            tfHinta.setText("");
            tfKokolajitelma.setText("");
            tfSesonki.setText("");
            tfTuoteID.requestFocusInWindow();  //kohdistin kenttaan
            tfTuoteID.selectAll();             //valitaan kaikki teksti kentasta
        }
    }

    private void TyhjennaKentat() {
        tfTuoteID.setText("");
        tfValmistajaID.setText("");
        tfMateriaaliID.setText("");
        tfTuotetyyppi.setText("");
        tfKokolajitelma.setText("");
        tfSesonki.setText("");
        tfKuvaus.setText("");
        tfKoostumus.setText("");
        tfHinta.setText("");
    }

}
