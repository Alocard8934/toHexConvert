package PraktikumProjekt;

import javax.swing.*;
import java.awt.EventQueue;


public class NeuDateiEingeben {

    private JFrame frmNeuDateiEingeben;
    private JTextField textFieldUserNameEingeben;
    private JTextField textFieldNummerSchieldEingeben;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NeuDateiEingeben window = new NeuDateiEingeben();
                    window.getFrame().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NeuDateiEingeben() {
        initialize();
    }

    private void initialize() {
        frmNeuDateiEingeben = new JFrame();
        frmNeuDateiEingeben.setTitle("Neu Datei eingeben");
        frmNeuDateiEingeben.setBounds(100, 100, 450, 300);
        frmNeuDateiEingeben.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmNeuDateiEingeben.getContentPane().setLayout(null);

        JLabel lblUserNameEingeben = new JLabel("Username:");
        lblUserNameEingeben.setBounds(10, 35, 111, 14);
        frmNeuDateiEingeben.getContentPane().add(lblUserNameEingeben);

        JLabel lblNumberSchieldEingeben = new JLabel("Nummer Schield:");
        lblNumberSchieldEingeben.setBounds(10, 89, 116, 14);
        frmNeuDateiEingeben.getContentPane().add(lblNumberSchieldEingeben);

        textFieldUserNameEingeben = new JTextField();
        textFieldUserNameEingeben.setBounds(136, 36, 276, 20);
        frmNeuDateiEingeben.getContentPane().add(textFieldUserNameEingeben);
        textFieldUserNameEingeben.setColumns(10);

        textFieldNummerSchieldEingeben = new JTextField();
        textFieldNummerSchieldEingeben.setBounds(136, 86, 276, 20);
        frmNeuDateiEingeben.getContentPane().add(textFieldNummerSchieldEingeben);
        textFieldNummerSchieldEingeben.setColumns(10);

        JButton btnZurueckButtonNeuDateiEingeben = new JButton("Zurück");
        btnZurueckButtonNeuDateiEingeben.setBounds(10, 227, 111, 23);
        frmNeuDateiEingeben.getContentPane().add(btnZurueckButtonNeuDateiEingeben);

        JButton btnSpeichernNeuDateiEingeben = new JButton("Speichern");
        btnSpeichernNeuDateiEingeben.setBounds(313, 227, 110, 23);
        frmNeuDateiEingeben.getContentPane().add(btnSpeichernNeuDateiEingeben);
        
        NeuDateiEingebenButtonAktionen.handleZurueckButton(btnZurueckButtonNeuDateiEingeben, frmNeuDateiEingeben);
        NeuDateiEingebenButtonAktionen.handleSpeichernButton(btnSpeichernNeuDateiEingeben, textFieldUserNameEingeben, textFieldNummerSchieldEingeben);


    }

    public JFrame getFrame() {
        return frmNeuDateiEingeben;
    }
}
