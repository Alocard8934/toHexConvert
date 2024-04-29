package PraktikumProjekt;

import javax.swing.*;
import java.util.List;

public class KundenController {
    private Kunden model;
    private KundenView view;

    public KundenController(Kunden model, KundenView view) {
        this.model = model;
        this.view = view;
    }

    // Методы для установки и получения данных из модели
    public void setKundenName(String kundenName) {
        model.setKundenName(kundenName);
    }

    public void setNummerSchield(String nummerSchield) {
        model.setNummerSchield(nummerSchield);
    }

    public String getKundenName() {
        return model.getKundenName();
    }

    public String getNummerSchield() {
        return model.getNummerSchield();
    }

    // Метод для обновления представления JTextArea
    public void updateViewTextArea() {
        // Получаем данные о клиентах из модели
        List<Kunden> kundenList = model.retrieveAllKundenFromDatabase();
        if (kundenList != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Kunden kunden : kundenList) {
                stringBuilder.append("Kunden Name: ").append(kunden.getKundenName()).append("\n");
                stringBuilder.append("Kunden Nummer Schield: ").append(kunden.getNummerSchield()).append("\n\n");
            }
            view.displayKundenDetailsTextArea(stringBuilder.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Fehler beim Abrufen der Kundendaten.");
        }
    }

    // Метод для обновления представления JTable
    public void updateViewTable() {
        // Получаем данные о клиентах из модели
        List<Kunden> kundenList = model.retrieveAllKundenFromDatabase();
        if (kundenList != null) {
            // Передаем список клиентов в метод displayKundenDetailsTable() представления
            view.displayKundenDetailsTable(kundenList);
        } else {
            JOptionPane.showMessageDialog(null, "Fehler beim Abrufen der Kundendaten.");
        }
    }
}
