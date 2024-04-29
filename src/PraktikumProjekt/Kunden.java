package PraktikumProjekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Kunden {
    private String userId;
    private String nummerSchield;
    private String kundenName;

    // Геттеры и сеттеры для userId, nummerSchield и kundenName

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNummerSchield() {
        return nummerSchield;
    }

    public void setNummerSchield(String nummerSchield) {
        this.nummerSchield = nummerSchield;
    }

    public String getKundenName() {
        return kundenName;
    }

    public void setKundenName(String kundenName) {
        this.kundenName = kundenName;
    }

    // Метод для извлечения всех клиентов из базы данных
    public static List<Kunden> retrieveAllKundenFromDatabase() {
        List<Kunden> kundenList = new ArrayList<>();
        Connection connection = null;
        try {
            // Установление соединения с базой данных PostgreSQL
            String url = "jdbc:postgresql://localhost:5432/Database_1";
            String username = "postgres";
            String password = "6891114";
            connection = DriverManager.getConnection(url, username, password);

            // SQL-запрос для извлечения данных о клиентах
            String query = "SELECT user_id, user_schield, user_name FROM public.user_data ORDER BY user_id ASC"; // Добавляем user_id в запрос
            PreparedStatement statement = connection.prepareStatement(query);

            // Выполнение запроса и получение результатов
            ResultSet resultSet = statement.executeQuery();

            // Создание объектов Kunden на основе результатов запроса
            while (resultSet.next()) {
                Kunden kunden = new Kunden();
                kunden.setUserId(resultSet.getString("user_id")); // Устанавливаем userId
                kunden.setKundenName(resultSet.getString("user_name"));
                kunden.setNummerSchield(resultSet.getString("user_schield"));
                kundenList.add(kunden);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Закрытие соединения
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return kundenList;
    }
}
