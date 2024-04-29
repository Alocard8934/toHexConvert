package PraktikumProjekt;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class KundenTableModel extends AbstractTableModel {
    /**
	 * 0.0.0.1 Team
	 */
	private static final long serialVersionUID = 1L;
	private List<Kunden> kundenList;
    private String[] columnNames = {"ID", "Name", "Nummer"};

    public KundenTableModel(List<Kunden> kundenList) {
        this.kundenList = kundenList;
    }

    @Override
    public int getRowCount() {
        return kundenList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kunden kunden = kundenList.get(rowIndex);
        switch (columnIndex) {
            case 0: return kunden.getUserId(); // Предполагается, что у вас есть метод getUserId() в классе Kunden
            case 1: return kunden.getKundenName();
            case 2: return kunden.getNummerSchield();
            default: return null;
        }
    }
}
