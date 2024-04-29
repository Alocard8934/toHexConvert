package PraktikumProjekt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class imageTableModel extends DefaultTableModel {

    /**
	 * 0.0.0.1 Team
	 */
	private static final long serialVersionUID = 1L;
	private Image image;

    public imageTableModel(Image image) {
        super(new Object[][]{}, new Object[]{});
        this.image = image;
    }

    @Override
    public int getRowCount() {
        // Возвращаем 1, чтобы отображать только одну строку с изображением,
        // когда таблица пустая
        return super.getRowCount() == 0 ? 1 : super.getRowCount();
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (getRowCount() == 0) {
            // Если таблица пустая, возвращаем изображение
            return new ImageIcon(image.getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        } else {
            // Возвращаем null для заполнения остальных ячеек в случае, если таблица не пустая
            return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        // Запрещаем редактирование ячеек
        return false;
    }
}
