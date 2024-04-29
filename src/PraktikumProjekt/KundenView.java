package PraktikumProjekt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class KundenView {
    private JTextArea textArea;
    private JTable table;

    public KundenView(JTextArea textArea, JTable table) {
        this.textArea = textArea;
        this.table = table;
    }

    // Метод для отображения данных о клиентах в JTextArea
    public void displayKundenDetailsTextArea(String data) {
        textArea.setText(data);
    }

    // Метод для отображения данных о клиентах в JTable
    public void displayKundenDetailsTable(List<Kunden> kundenList) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Очищаем таблицу перед отображением новых данных

        for (Kunden kunden : kundenList) {
            model.addRow(new Object[]{kunden.getNummerSchield(), kunden.getKundenName()});
        }
    }
}
