package PraktikumProjekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NeuDateiEingebenButtonAktionen {
    // Метод для обработки действия кнопки "Zurück"
    public static void handleZurueckButton(JButton button, JFrame frame) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current window
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
    // Метод для обработки действия кнопки "Speichern"
    public static void handleSpeichernButton(JButton button, JTextField textFieldUserName, JTextField textFieldNummerSchield) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Получаем данные из текстовых полей
                String username = textFieldUserName.getText();
                String nummerSchield = textFieldNummerSchield.getText();

                // Проверяем, были ли данные введены
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fehler: Bitte füllen Sie \"Username\" Feld aus.");
                    return; // Прекращаем выполнение метода, если одно из полей пустое
                }

                // Устанавливаем параметры подключения к базе данных PostgreSQL
                String url = "jdbc:postgresql://localhost:5432/Database_1";
                String user = "postgres";
                String password = "6891114";

                try {
                    // Подключаемся к базе данных
                    Connection connection = DriverManager.getConnection(url, user, password);

                    // Создаем запрос на вставку данных в таблицу
                    String query = "INSERT INTO public.user_data (user_name, user_schield) VALUES (?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, username);
                    statement.setString(2, nummerSchield);

                    // Выполняем запрос
                    statement.executeUpdate();

                    // Закрываем соединение и освобождаем ресурсы
                    statement.close();
                    connection.close();

                    // Выводим сообщение об успешном сохранении данных
                    JOptionPane.showMessageDialog(null, "Die Daten wurden erfolgreich in der Datenbank gespeichert!");
                } catch (SQLException ex) {
                    // Обрабатываем возможные ошибки при выполнении запроса
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Fehler beim Speichern von Daten in der Datenbank:" + ex.getMessage());
                }
            }
        });
    }
}
