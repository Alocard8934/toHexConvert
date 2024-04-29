package PraktikumProjekt;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HexFrameButtonAktionen {

    private static final String url = "jdbc:postgresql://localhost:5432/Database_1";
    private static final String user = "postgres";
    private static final String password = "6891114";

    public static void handleZurueckButton(JFrame frame) {
        JButton btnZurueck = new JButton("Zurück");
        btnZurueck.setBounds(10, 395, 150, 23);
        frame.getContentPane().add(btnZurueck);

        btnZurueck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            MainFrame mainFrame = new MainFrame();
                            mainFrame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public static void handleInHexSpeichernButton(JFrame frame) {
        JButton btnInHexSpeichern = new JButton("In Hex speichern");
        btnInHexSpeichern.setBounds(506, 395, 150, 23);
        frame.getContentPane().add(btnInHexSpeichern);

        btnInHexSpeichern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Получаем данные из таблицы PostgreSQL
                try (Connection connection = DriverManager.getConnection(url, user, password)) {
                    String query = "SELECT * FROM public.user_data";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query);

                    // Создаем StringBuilder для сохранения данных в формате CQD
                    StringBuilder cqdData = new StringBuilder();

                    // Получаем названия столбцов
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        cqdData.append(metaData.getColumnName(i)).append("|");
                    }
                    cqdData.append("\n");

                    // Проходимся по результатам запроса
                    while (resultSet.next()) {
                        // Получаем данные из каждой колонки
                        for (int i = 1; i <= columnCount; i++) {
                            // Добавляем данные в формате CQD
                            cqdData.append(resultSet.getString(i)).append("|");
                        }
                        // Добавляем перенос строки после каждой строки
                        cqdData.append("\n");
                    }

                    // Открываем диалог сохранения файла
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Speichern als...");
                    int userSelection = fileChooser.showSaveDialog(frame);

                    // Если пользователь выбрал файл для сохранения
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                            // Записываем данные в файл
                            writer.write(cqdData.toString());
                            JOptionPane.showMessageDialog(frame, "Datei erfolgreich gespeichert.", "Erfolg", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(frame, "Fehler beim Speichern der Datei: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Fehler beim Zugriff auf die Datenbank: " + ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });
    }
}
