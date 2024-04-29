package PraktikumProjekt;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DateiLoeschenFrame {

    private JFrame frmDateiLschen;
    private JTextField textFieldIDDateiLoeschen;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DateiLoeschenFrame window = new DateiLoeschenFrame();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DateiLoeschenFrame() {
        initialize();
    }

    private void initialize() {
        frmDateiLschen = new JFrame();
        frmDateiLschen.setTitle("Datei löschen");
        frmDateiLschen.setBounds(100, 100, 550, 372);
        frmDateiLschen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmDateiLschen.getContentPane().setLayout(null);
        
        JButton btnZurueck = new JButton("Zurück");
        btnZurueck.setBounds(10, 299, 110, 23);
        frmDateiLschen.getContentPane().add(btnZurueck);
        
        JButton btnDateiLoeschen = new JButton("Löschen");
        btnDateiLoeschen.setBounds(414, 299, 110, 23);
        frmDateiLschen.getContentPane().add(btnDateiLoeschen);
        
        JLabel lblText = new JLabel("Geben Sie die ID-Nummer ein, die Sie aus der Tabelle entfernen möchten");
        lblText.setBounds(51, 21, 473, 80);
        frmDateiLschen.getContentPane().add(lblText);
        
        JLabel lblIDLoeschen = new JLabel("ID:");
        lblIDLoeschen.setBounds(46, 114, 46, 14);
        frmDateiLschen.getContentPane().add(lblIDLoeschen);
        
        textFieldIDDateiLoeschen = new JTextField();
        textFieldIDDateiLoeschen.setBounds(147, 111, 257, 20);
        frmDateiLschen.getContentPane().add(textFieldIDDateiLoeschen);
        textFieldIDDateiLoeschen.setColumns(10);
        
        btnZurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DateiLoeschenButtonAktionen.handleZurueck(frmDateiLschen);
            }
        });
        
        btnDateiLoeschen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFieldIDDateiLoeschen.getText().trim();
                
                // Проверяем, что строка не пуста
                if (!id.isEmpty()) {
                    DateiLoeschenButtonAktionen.handleLoeschen(frmDateiLschen, id);
                } else {
                    // Оповещаем пользователя о необходимости ввода ID
                    JOptionPane.showMessageDialog(frmDateiLschen, "Bitte geben Sie eine gültige ID ein.", "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    public JFrame getFrame() {
        return frmDateiLschen;
    }
}
