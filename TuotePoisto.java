package kayttoliittyma;

import datat.Tuote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import tietokanta.Tietovarasto;

public class TuotePoisto extends JFrame{

    private JLabel lbTuoteID = new JLabel("TuoteID");
    private JComboBox cbTuoteID;
    private JLabel lbTuotetyyppi = new JLabel("Tuotetyyppi");
    private JTextField tfTuotetyyppi = new JTextField(20);
    private JLabel lbValmistajaID = new JLabel("Valmistaja");
    private JTextField tfValmistajaID = new JTextField(20);
    private JLabel lbMateriaaliID = new JLabel("MateriaaliID");
    private JTextField tfMateriaaliID = new JTextField(20);
    private JLabel lbKokolajitelma = new JLabel("Kokolajitelma");
    private JTextField tfKokolajitelma = new JTextField(20);
    private JLabel lbSesonki = new JLabel("Sesonki");
    private JTextField tfSesonki = new JTextField(20);
    private JButton bPoista = new JButton("Poista");
    private JPanel pohjapaneeli = new JPanel();
    private Tietovarasto kanta;
    private Map<String, Tuote> tuotelista = new HashMap<String, Tuote>();

    public TuotePoisto(Tietovarasto kanta) {
        this.kanta = kanta;
        try {
            for (Tuote alkio : kanta.haeKaikkiTuotteet()) {
                tuotelista.put(alkio.getTuoteID(), alkio);
            }
            if (tuotelista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ei poistettavia tuotteita", "virhe", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            } else {
                cbTuoteID = new JComboBox(tuotelista.keySet().toArray());
                paivitaTuote();
                asetteleKomponentit();
            }
        } catch (Exception vt) {
            JOptionPane.showMessageDialog(this, vt, "virhe", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup seliteX = asettelu.createParallelGroup();
        seliteX.addComponent(lbTuoteID);
        seliteX.addComponent(lbValmistajaID);
        seliteX.addComponent(lbMateriaaliID);
        seliteX.addComponent(lbTuotetyyppi);
        seliteX.addComponent(lbKokolajitelma);
        seliteX.addComponent(lbSesonki);

        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup();
        kentatX.addComponent(cbTuoteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfValmistajaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfMateriaaliID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfTuotetyyppi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKokolajitelma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfSesonki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        cbTuoteID.setEditable(false);
        tfValmistajaID.setEditable(false);
        tfMateriaaliID.setEditable(false);
        tfTuotetyyppi.setEditable(false);
        tfKokolajitelma.setEditable(false);
        tfSesonki.setEditable(false);

        GroupLayout.SequentialGroup ylaX = asettelu.createSequentialGroup();
        ylaX.addGroup(seliteX);
        ylaX.addGroup(kentatX);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaX);
        pohjaX.addComponent(bPoista);

        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.ParallelGroup tuoteIDriviY = asettelu.createParallelGroup();
        tuoteIDriviY.addComponent(lbTuoteID);
        tuoteIDriviY.addComponent(cbTuoteID);

        GroupLayout.ParallelGroup valmistajaIDriviY = asettelu.createParallelGroup();
        valmistajaIDriviY.addComponent(lbValmistajaID);
        valmistajaIDriviY.addComponent(tfValmistajaID);

        GroupLayout.ParallelGroup materiaaliIDriviY = asettelu.createParallelGroup();
        materiaaliIDriviY.addComponent(lbMateriaaliID);
        materiaaliIDriviY.addComponent(tfMateriaaliID);
        
        GroupLayout.ParallelGroup tuotetyyppiriviY = asettelu.createParallelGroup();
        tuotetyyppiriviY.addComponent(lbTuotetyyppi);
        tuotetyyppiriviY.addComponent(tfTuotetyyppi);
        
        GroupLayout.ParallelGroup kokolajitelmariviY = asettelu.createParallelGroup();
        kokolajitelmariviY.addComponent(lbKokolajitelma);
        kokolajitelmariviY.addComponent(tfKokolajitelma);
        
        GroupLayout.ParallelGroup sesonkiriviY = asettelu.createParallelGroup();
        sesonkiriviY.addComponent(lbSesonki);
        sesonkiriviY.addComponent(tfSesonki);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(tuoteIDriviY);
        pohjaY.addGroup(valmistajaIDriviY);
        pohjaY.addGroup(materiaaliIDriviY);
        pohjaY.addGroup(tuotetyyppiriviY);
        pohjaY.addGroup(kokolajitelmariviY);
        pohjaY.addGroup(sesonkiriviY);
        pohjaY.addComponent(bPoista);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setLocation(100, 100);
        this.setTitle("Materiaalitietojen poisto");
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });
        bPoista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muutaTuotteenTiedot();
            }
        });
        cbTuoteID.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                paivitaTuote();

            }
        });
    }

    private void suoritaLopputoimet() {
        this.dispose();
    }

    private void paivitaTuote() {
        String valittuTuoteID = (String) cbTuoteID.getSelectedItem();
        if (valittuTuoteID != null) {
            Tuote valittuTuote = tuotelista.get(valittuTuoteID);
            tfValmistajaID.setText(valittuTuote.getValmistajaID());
            tfMateriaaliID.setText(""+valittuTuote.getMateriaaliID());
            tfTuotetyyppi.setText(valittuTuote.getTuotetyyppi());
            tfKokolajitelma.setText(valittuTuote.getKokolajitelma());
            tfSesonki.setText(valittuTuote.getSesonki());
        }
    }

    private void muutaTuotteenTiedot() {
        try {
            String valittuTuoteID = (String) cbTuoteID.getSelectedItem();
            kanta.poistaTuote(valittuTuoteID);
            suoritaLopputoimet();
        } catch (Exception vt) {
            JOptionPane.showMessageDialog(this, vt.getMessage(), "Virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

}
