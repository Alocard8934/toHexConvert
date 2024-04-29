package PraktikumProjekt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class HexFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static final String url = "jdbc:postgresql://localhost:5432/Database_1";
    private static final String user = "postgres";
    private static final String password = "6891114";
    private static final String tableName = "public.user_data";
    DefaultTableModel tableModel = new DefaultTableModel();
    JTable table = new JTable(tableModel);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    HexFrame frame = new HexFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public HexFrame() {
        setTitle("Hex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 682, 468);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        HexFrameButtonAktionen.handleZurueckButton(this);
        HexFrameButtonAktionen.handleInHexSpeichernButton(this);

        JScrollPane scrollPane = new JScrollPane(table);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Отображение данных из базы данных в JTable в шестнадцатеричном формате
        handleShowTable(table);
    }

    // Метод для отображения данных из базы данных в JTable в шестнадцатеричном формате
    public static void handleShowTable(JTable table) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Создание SQL запроса для выборки всех записей из таблицы
            String query = "SELECT * FROM " + tableName;

            // Выполнение запроса
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Создание модели данных для JTable
            DefaultTableModel tableModel = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                tableModel.addColumn(metaData.getColumnName(columnIndex));
            }

            // Добавление данных из ResultSet в модель данных для JTable в шестнадцатеричном формате
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    // Преобразование значения в шестнадцатеричный формат
                    String hexValue = toHex(resultSet.getObject(columnIndex).toString().getBytes());
                    rowData[columnIndex - 1] = hexValue;
                }
                tableModel.addRow(rowData);
            }

            // Установка модели данных для JTable
            table.setModel(tableModel);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ошибка при получении данных: " + e.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Метод для конвертации данных в шестнадцатеричный формат
    private static String toHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            // Преобразуем каждый байт в двузначное шестнадцатеричное число и добавляем пробел
            hexString.append(String.format("%02X ", b));
        }
        return hexString.toString();
    }
}
