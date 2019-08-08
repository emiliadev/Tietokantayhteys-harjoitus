package kayttoliittyma;

import datat.Materiaali;
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

public class MateriaaliMuutos extends JFrame {

    private JLabel lbMateriaaliID = new JLabel("MateriaaliID:");
    private JLabel lbKuvaus = new JLabel("Kuvaus:");
    private JLabel lbKoostumus = new JLabel("Koostumus:");
    private JLabel lbHinta = new JLabel("Hinta €/m:");
    private JComboBox cbMateriaaliID;
    private JTextField tfKuvaus = new JTextField(50);
    private JTextField tfKoostumus = new JTextField(30);
    private JTextField tfHinta = new JTextField (8);
    private JButton bTallenna = new JButton("Tallenna muutokset");
    private JPanel pohjapaneeli = new JPanel();
    private Tietovarasto kanta = new Tietovarasto();
    private Map<Integer, Materiaali> materiaalilista = new HashMap<Integer, Materiaali>();

    public MateriaaliMuutos(Tietovarasto kanta) {
        this.kanta = kanta;
        try {
            for (Materiaali alkio : kanta.haeKaikkiMateriaalit()) {
                materiaalilista.put(alkio.getMateriaaliID(), alkio);
            }
            if (materiaalilista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ei muutettavia materiaaleja", "virhe", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            } else {
                cbMateriaaliID = new JComboBox(materiaalilista.keySet().toArray());
                paivitaMateriaali();
                asetteleKomponentit();
            }
        } catch (Exception e) {
            System.out.println("Tapahtui virhe:" + e);
        }
    }

    private void asetteleKomponentit() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup seliteX = asettelu.createParallelGroup();
        seliteX.addComponent(lbMateriaaliID);
        seliteX.addComponent(lbKuvaus);
        seliteX.addComponent(lbKoostumus);
        seliteX.addComponent(lbHinta);

        GroupLayout.ParallelGroup kentatX = asettelu.createParallelGroup();
        kentatX.addComponent(cbMateriaaliID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKuvaus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfKoostumus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        kentatX.addComponent(tfHinta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);

        GroupLayout.SequentialGroup ylaX = asettelu.createSequentialGroup();
        ylaX.addGroup(seliteX);
        ylaX.addGroup(kentatX);

        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addGroup(ylaX);
        pohjaX.addComponent(bTallenna); 
        
        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.ParallelGroup materiaaliidriviY = asettelu.createParallelGroup();
        materiaaliidriviY.addComponent(lbMateriaaliID);
        materiaaliidriviY.addComponent(cbMateriaaliID);

        GroupLayout.ParallelGroup kuvausriviY = asettelu.createParallelGroup();
        kuvausriviY.addComponent(lbKuvaus);
        kuvausriviY.addComponent(tfKuvaus);

        GroupLayout.ParallelGroup koostumusriviY = asettelu.createParallelGroup();
        koostumusriviY.addComponent(lbKoostumus);
        koostumusriviY.addComponent(tfKoostumus);
        
        GroupLayout.ParallelGroup hintariviY = asettelu.createParallelGroup();
        hintariviY.addComponent(lbHinta);
        hintariviY.addComponent(tfHinta);

        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addGroup(materiaaliidriviY);
        pohjaY.addGroup(kuvausriviY);
        pohjaY.addGroup(koostumusriviY);
        pohjaY.addGroup(hintariviY);
        pohjaY.addComponent(bTallenna);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setLocation(100, 100);
        this.setTitle("Materiaalitietojen syöttö");
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });
        bTallenna.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muutaMateriaalinTiedot();
            }
        });
        cbMateriaaliID.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                paivitaMateriaali();

            }
        });
    }

    private void suoritaLopputoimet() {
        this.dispose();
    }

    private void paivitaMateriaali() {
        Integer valittuMateriaaliID = (Integer) cbMateriaaliID.getSelectedItem();
        if (valittuMateriaaliID != null) {
            Materiaali valittuMateriaali = materiaalilista.get(valittuMateriaaliID);
            tfKuvaus.setText(valittuMateriaali.getKuvaus());
            tfKoostumus.setText(valittuMateriaali.getKoostumus());
            tfHinta.setText(""+valittuMateriaali.getHinta());
        }
    }

    private void muutaMateriaalinTiedot() {
        try {
           Integer valittuMateriaaliID = (Integer) cbMateriaaliID.getSelectedItem(); 
            Materiaali valittuMateriaali = materiaalilista.get(valittuMateriaaliID);
            kanta.muutaMateriaalinTietoja(new Materiaali(valittuMateriaali.getMateriaaliID(), tfKuvaus.getText(), tfKoostumus.getText(), (Double.parseDouble(tfHinta.getText()))));
            suoritaLopputoimet();
        } catch (Exception e) {
            System.out.println("Tapahtui virhe:" + e);
        }
    }
}
