package kayttoliittyma;

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
import datat.Tuote;

public class TuoteLisays extends JFrame {

    private JLabel lbTuoteID = new JLabel("TuoteID");
    private JTextField tfTuoteID = new JTextField(20);
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
    private JButton bTallenna = new JButton("Tallenna");
    private JButton bTyhjenna = new JButton("Tyhjennä");
    private JPanel pohjapaneeli = new JPanel();
    private Tietovarasto kanta = new Tietovarasto();

    public TuoteLisays(Tietovarasto kanta) {
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
        seliteX.addComponent(lbKokolajitelma);
        seliteX.addComponent(lbSesonki);

        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup();
        kentatX.addComponent(tfTuoteID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfTuotetyyppi, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfValmistajaID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfMateriaaliID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKokolajitelma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfSesonki, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup ylaX = asettelu.createSequentialGroup();
        ylaX.addGroup(seliteX);
        ylaX.addGroup(kentatX);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaX);
        pohjaX.addComponent(bTallenna);
        pohjaX.addComponent(bTyhjenna); 

        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.ParallelGroup tuoteidriviY = asettelu.createParallelGroup();
        tuoteidriviY.addComponent(lbTuoteID);
        tuoteidriviY.addComponent(tfTuoteID);

        GroupLayout.ParallelGroup tuotetyyppiriviY = asettelu.createParallelGroup();
        tuotetyyppiriviY.addComponent(lbTuotetyyppi);
        tuotetyyppiriviY.addComponent(tfTuotetyyppi);

        GroupLayout.ParallelGroup materiaaliidriviY = asettelu.createParallelGroup();
        materiaaliidriviY.addComponent(lbMateriaaliID);
        materiaaliidriviY.addComponent(tfMateriaaliID);

        GroupLayout.ParallelGroup valmistajaidriviY = asettelu.createParallelGroup();
        valmistajaidriviY.addComponent(lbValmistajaID);
        valmistajaidriviY.addComponent(tfValmistajaID);

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
        pohjaY.addGroup(kokolajitelmariviY);
        pohjaY.addGroup(sesonkiriviY);
        pohjaY.addComponent(bTallenna);
        pohjaY.addComponent(bTyhjenna);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setLocation(100, 100);
        this.setTitle("Tuotetietojen syöttö");
        this.pack();
       
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });
        bTallenna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lisaaTuoteKantaan();
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

    private void lisaaTuoteKantaan() {
        if (tfTuoteID.getText().isEmpty() || tfTuotetyyppi.getText().isEmpty() || tfValmistajaID.getText().isEmpty() || tfMateriaaliID.getText().isEmpty() || tfKokolajitelma.getText().isEmpty() || tfSesonki.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tiedot puutteellisia. Tuotetta ei lisätty", "Virhe", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                kanta.lisaaTuote(new Tuote(tfTuoteID.getText(), (tfValmistajaID.getText()), Integer.parseInt(tfMateriaaliID.getText()), tfTuotetyyppi.getText(), tfKokolajitelma.getText(), tfSesonki.getText()));
                suoritaLopputoimet();
            } catch (Exception e) {
                System.out.println("Tapahtui virhe:" + e);
            }
        }
    }

    private void TyhjennaKentat() {
        tfTuoteID.setText("");
        tfValmistajaID.setText("");
        tfMateriaaliID.setText("");
        tfTuotetyyppi.setText("");
        tfKokolajitelma.setText("");
        tfSesonki.setText("");
    }

}

