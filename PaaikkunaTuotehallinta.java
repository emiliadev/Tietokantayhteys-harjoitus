package kayttoliittyma;

import datat.Materiaali;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import tietokanta.Tietovarasto;

public class PaaikkunaTuotehallinta extends JFrame {

    private JButton haeTuote = new JButton("Hae tuote");
    private JButton lisaaTuote = new JButton("Lisää tuote");
    private JButton poistaTuote = new JButton("Poista tuote");
    private JButton haeMateriaalit = new JButton("Hae kaikki materiaalit");
    private JButton muutaMateriaali = new JButton("Muuta materiaalin tietoja");
    private JButton sulje = new JButton("Sulje");
    private JPanel pohjapaneeli = new JPanel();
    private Tietovarasto kanta = new Tietovarasto();

    public PaaikkunaTuotehallinta() {
        GroupLayout asettelu = new GroupLayout(pohjapaneeli);
        pohjapaneeli.setLayout(asettelu);

        asettelu.setAutoCreateGaps(true);
        asettelu.setAutoCreateContainerGaps(true);

        //X-suunta
        GroupLayout.ParallelGroup pohjaX = asettelu.createParallelGroup();
        pohjaX.addComponent(haeTuote);
        pohjaX.addComponent(lisaaTuote);
        pohjaX.addComponent(poistaTuote);
        pohjaX.addComponent(haeMateriaalit);
        pohjaX.addComponent(muutaMateriaali);

        pohjaX.addComponent(sulje);

        asettelu.linkSize(haeTuote, lisaaTuote, poistaTuote, haeMateriaalit, muutaMateriaali, sulje);
        asettelu.setHorizontalGroup(pohjaX);

        //Y-suunta
        GroupLayout.SequentialGroup pohjaY = asettelu.createSequentialGroup();
        pohjaY.addComponent(haeTuote);
        pohjaY.addComponent(lisaaTuote);
        pohjaY.addComponent(poistaTuote);
        pohjaY.addComponent(haeMateriaalit);
        pohjaY.addComponent(muutaMateriaali);
        pohjaY.addComponent(sulje);

        asettelu.setVerticalGroup(pohjaY);
        this.add(pohjapaneeli);
        this.setLocation(100, 100);
        this.pack();

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                suoritaLopputoimet();
            }
        });

        lisaaTuote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tuotteenLisays();
            }
        });

        haeTuote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tuotteenHaku();
            }
        });

        poistaTuote.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tuotteenPoisto();
            }
        });

        haeMateriaalit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                haeMateriaalit();
            }
        });
        muutaMateriaali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materiaalinMuutos();
            }
        });

        sulje.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suoritaLopputoimet();
            }
        });

    }

    private void tuotteenHaku() {
        new TuoteHaku(kanta).setVisible(true);
    }

    private void tuotteenPoisto() {
        new TuotePoisto(kanta).setVisible(true);
    }

    private void tuotteenLisays() {
        new TuoteLisays(kanta).setVisible(true);
    }

    private void materiaalinMuutos() {
        new MateriaaliMuutos(kanta).setVisible(true);
    }

    private void suoritaLopputoimet() {
        System.exit(0);
    }

    private void haeMateriaalit() { 

        JTextArea kaikkiMateriaalit = new JTextArea(30, 70);
        try {
            for (Materiaali alkio : kanta.haeKaikkiMateriaalit()) {
                kaikkiMateriaalit.append(alkio.toString());
                kaikkiMateriaalit.append("\n");
            }
            JOptionPane.showMessageDialog(this, new JScrollPane(kaikkiMateriaalit), "Kaikki materiaalit",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "virhe", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PaaikkunaTuotehallinta().setVisible(true);
            }
        });
    }
}
