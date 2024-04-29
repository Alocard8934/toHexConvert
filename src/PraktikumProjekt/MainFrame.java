package PraktikumProjekt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainFrame() {
        setTitle("Database");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 723, 459);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnMenu = new JMenu("Menu");
        menuBar.add(mnMenu);

        JMenuItem mntmDateiEingeben = new JMenuItem("Datei eingeben");
        mnMenu.add(mntmDateiEingeben);

        JMenuItem mntmDateiBearbeiten = new JMenuItem("Datei bearbeiten");
        mnMenu.add(mntmDateiBearbeiten);

        JMenuItem mntmDateiLoeschen = new JMenuItem("Datei löschen");
        mnMenu.add(mntmDateiLoeschen);

        JMenu mnTabelle = new JMenu("Tabelle");
        menuBar.add(mnTabelle);
        mnTabelle.setHorizontalAlignment(SwingConstants.LEFT);

        JMenuItem mntmTabelleZeigen = new JMenuItem("Tabelle zeigen");
        mnTabelle.add(mntmTabelleZeigen);

        JMenuItem mntmTabelleLoeschen = new JMenuItem("Tabelle löschen");
        mnTabelle.add(mntmTabelleLoeschen);

        JMenu mnHex = new JMenu("Hex");
        menuBar.add(mnHex);
        mnHex.setHorizontalAlignment(SwingConstants.LEFT);

        JMenuItem mntmInHexKonvertieren = new JMenuItem("In Hex konvertieren");
        mnHex.add(mntmInHexKonvertieren);

        JMenuItem mntmInHexSpeichern = new JMenuItem("In Hex speichern");
        mnHex.add(mntmInHexSpeichern);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        // Загрузка изображения
        ImageIcon icon = new ImageIcon("C:\\Users\\yuriy\\eclipse-workspace\\PraktikumProjekt\\src\\img\\pngegg.png");

        // Создание модели таблицы с изображением
        imageTableModel model = new imageTableModel(icon.getImage());

        // Создание JTable с этой моделью таблицы
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        mntmTabelleZeigen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleShowTable(table);
            }
        });
        mntmTabelleLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleDeleteTable(table);
            }
        });
        mntmInHexKonvertieren.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleConvertToHex(MainFrame.this);
            }
        });
        mntmInHexSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleSaveToHex(MainFrame.this);
            }
        });
        mntmDateiEingeben.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrameButtonAktionen.handleDateiEingeben(MainFrame.this);
            }
        });
        mntmDateiBearbeiten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleDateiBearbeiten(MainFrame.this);
            }
        });
        mntmDateiLoeschen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	MainFrameButtonAktionen.handleDateiLoeschen(MainFrame.this);
            }
        });
    }
}
