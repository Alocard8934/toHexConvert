package PraktikumProjekt;

public class KundenMVCPattern {
    public static void main(String[] args) {
        // Создание модели данных (клиентов) и представления
        Kunden model = new Kunden();
        KundenView view = new KundenView(null, null); // Передайте здесь JTable

        // Создание контроллера и передача модели и представления
        KundenController controller = new KundenController(model, view);

        // Обновление представления
        controller.updateViewTable(); // Используйте метод для обновления представления с использованием JTable
    }
}
