package PraktikumProjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DateiLoeschenButtonAktionen {

    // Параметры подключения к базе данных PostgreSQL
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Database_1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "6891114";

    public static void handleZurueck(JFrame frame) {
        // Создаем и открываем новое окно MainFrame
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        // Закрываем текущее окно DateiLoeschenFrame
        frame.dispose();
    }

    public static void handleLoeschen(JFrame frame, String id) {
        // Выполняем удаление записи из базы данных по ID
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM public.user_data WHERE user_id = CAST(? AS bigint)")) {
            statement.setString(1, id);
            int rowsAffected = statement.executeUpdate();

            // Проверяем, была ли удалена хотя бы одна строка
            if (rowsAffected > 0) {
                // Оповещаем пользователя об успешном удалении
                JOptionPane.showMessageDialog(frame, "Datensatz erfolgreich gelöscht.");
            } else {
                // Если ни одна строка не была удалена, значит, запись с таким ID не найдена
                JOptionPane.showMessageDialog(frame, "Datensatz mit dieser ID nicht gefunden.", "Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Оповещаем пользователя об ошибке при удалении
            JOptionPane.showMessageDialog(frame, "Fehler beim Löschen des Datensatzes.");
        }
    }
}
