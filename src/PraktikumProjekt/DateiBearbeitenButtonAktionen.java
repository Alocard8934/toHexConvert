package PraktikumProjekt;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DateiBearbeitenButtonAktionen {
    
	public static void handleZurueck(JFrame frame) {
        // Закрыть текущее окно
        frame.dispose();
        
        // Создать и отобразить новое главное окно MainFrame
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
	public static void handleSpeichern(String userIdString, String neuWertName, boolean isUsername) {
	    if (userIdString.isEmpty() || neuWertName.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "ID und mindestens ein neuer Wert müssen eingegeben werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Преобразуем userId из строки в long
	    long userId = Long.parseLong(userIdString);

	    // Выполняем обновление записи в базе данных
	    String columnName = isUsername ? "user_name" : "user_schield";
	    updateRecordInDatabase1(userId, neuWertName, columnName);
	}

	private static void updateRecordInDatabase1(long userId, String newValue, String columnName) {
	    String url = "jdbc:postgresql://localhost:5432/Database_1";
	    String username = "postgres";
	    String password = "6891114";

	    try (Connection connection = DriverManager.getConnection(url, username, password)) {
	        String query = "UPDATE public.user_data SET " + columnName + " = ? WHERE user_id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, newValue);
	            statement.setLong(2, userId);
	            statement.executeUpdate();
	            JOptionPane.showMessageDialog(null, "Der Datensatz wurde erfolgreich aktualisiert.");
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Fehler beim Aktualisieren eines Datensatzes: " + ex.getMessage());
	    }
	}

	
	
    
}
