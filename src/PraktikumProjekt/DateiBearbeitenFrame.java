package PraktikumProjekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DateiBearbeitenFrame extends JFrame {
    /**
	 * 0.0.0.1 Team
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldIdEingeben;
    private JTextField textFieldNeuUsernameEingeben;
    private JTextField textFieldNeuNumerSchieldEingeben;

    public DateiBearbeitenFrame() {
        setTitle("Datei bearbeiten");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(447, 300);
        getContentPane().setLayout(null);

        JLabel lblIDWaehlen = new JLabel("ID");
        lblIDWaehlen.setBounds(10, 31, 46, 14);
        getContentPane().add(lblIDWaehlen);

        textFieldIdEingeben = new JTextField();
        textFieldIdEingeben.setBounds(141, 28, 250, 20);
        getContentPane().add(textFieldIdEingeben);
        textFieldIdEingeben.setColumns(10);

        JLabel lblUsernameEingeben = new JLabel("Neu Username:");
        lblUsernameEingeben.setBounds(10, 81, 115, 14);
        getContentPane().add(lblUsernameEingeben);

        textFieldNeuUsernameEingeben = new JTextField();
        textFieldNeuUsernameEingeben.setBounds(141, 78, 250, 20);
        getContentPane().add(textFieldNeuUsernameEingeben);
        textFieldNeuUsernameEingeben.setColumns(10);

        JLabel lblNeuNummerSchield = new JLabel("Neu Nummerschield:");
        lblNeuNummerSchield.setBounds(10, 129, 110, 14);
        getContentPane().add(lblNeuNummerSchield);

        textFieldNeuNumerSchieldEingeben = new JTextField();
        textFieldNeuNumerSchieldEingeben.setBounds(141, 126, 250, 20);
        getContentPane().add(textFieldNeuNumerSchieldEingeben);
        textFieldNeuNumerSchieldEingeben.setColumns(10);

        JButton btnZurueck = new JButton("Zurück");
        btnZurueck.setBounds(10, 227, 110, 23);
        getContentPane().add(btnZurueck);

        JButton btnSpeichern = new JButton("Speichern");
        btnSpeichern.setBounds(311, 227, 110, 23);
        getContentPane().add(btnSpeichern);
        
        // Заменяем слушателей кнопок вызовами методов из DateiBearbeitenButtonAktionen
        btnZurueck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DateiBearbeitenButtonAktionen.handleZurueck(DateiBearbeitenFrame.this);                
            }
        });
        btnSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = textFieldIdEingeben.getText();
                String newUserName = textFieldNeuUsernameEingeben.getText();
                String newUserSchield = textFieldNeuNumerSchieldEingeben.getText();

                // Проверяем, введены ли ID и хотя бы одно из полей нового значения
                if (id.isEmpty() || (newUserName.isEmpty() && newUserSchield.isEmpty())) {
                    JOptionPane.showMessageDialog(null, "ID und mindestens ein neuer Wert müssen eingegeben werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Проверяем, было ли введено новое значение для user_name
                if (!newUserName.isEmpty()) {
                    DateiBearbeitenButtonAktionen.handleSpeichern(id, newUserName, true);
                }

                // Проверяем, было ли введено новое значение для user_schield
                if (!newUserSchield.isEmpty()) {
                    DateiBearbeitenButtonAktionen.handleSpeichern(id, newUserSchield, false);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DateiBearbeitenFrame window = new DateiBearbeitenFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
