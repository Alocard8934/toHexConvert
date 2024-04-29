package PraktikumProjekt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

public class MainFrameButtonAktionen {
    public static void handleShowTable(JTable table) {
        List<Kunden> kundenList = Kunden.retrieveAllKundenFromDatabase();
        if (kundenList != null) {
            KundenTableModel tableModel = new KundenTableModel(kundenList);
            table.setModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(null, "Fehler beim Abrufen der Kundendaten.");
        }
    }
    public static void handleDeleteTable(JTable table) {
        table.setModel(new DefaultTableModel());
    }
    public static void handleConvertToHex(JFrame frame) {
        // Закрытие текущего окна
        frame.dispose();

        // Создание и отображение нового окна HexFrame
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HexFrame hexFrame = new HexFrame();
                    hexFrame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void handleSaveToHex(JFrame frame) {
        // Получаем текст из JTextArea
        String textToSave = ""; // Заменено на пустую строку

        // Отображаем диалоговое окно для выбора места сохранения файла
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Получаем выбранный файл
            java.io.File fileToSave = fileChooser.getSelectedFile();

            try {
                // Записываем текст в выбранный файл
                java.io.PrintWriter writer = new java.io.PrintWriter(fileToSave);
                writer.print(textToSave);
                writer.close();

                JOptionPane.showMessageDialog(frame, "Die Datei wurde erfolgreich gespeichert.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Fehler beim Speichern einer Datei.");
            }}
        }
    public static void handleDateiEingeben(JFrame frame) {
            // Закрыть текущее окно MainFrame
            frame.dispose();
            
            // Создать и отобразить новое окно NeuDateiEingeben
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        NeuDateiEingeben neuDateiEingeben = new NeuDateiEingeben();
                        neuDateiEingeben.getFrame().setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
    
        }
    public static void handleDateiBearbeiten(JFrame frame) {
    	 // Закрыть текущее окно
    	frame.dispose();

        // Создать и открыть новое окно DateiBearbeitenFrame
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DateiBearbeitenFrame window = new DateiBearbeitenFrame();
                    window.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void handleDateiLoeschen(JFrame frame) {
    	// Закрыть текущее окно
    	frame.dispose();

        // Создать и открыть новое окно DateiLoeschenFrame
        DateiLoeschenFrame window = new DateiLoeschenFrame();
        window.getFrame().setVisible(true); // вызовите метод getFrame() в классе DateiLoeschenFrame
   }
}
