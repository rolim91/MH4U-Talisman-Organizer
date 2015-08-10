package talisman.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import talisman.model.Talisman;



public class TalismanTableModel extends AbstractTableModel{


	private String[] columnNames = 	{ "Skill 1", 	"Value 1", 		"Skill 2", 		"Value 2", 		"Slots", 		"Rarity"};
	private List<Talisman> talismanList = new ArrayList<Talisman>();
	
	private static final long serialVersionUID = -8854771373861055405L;
	
	/*
	 * Default Constructor
	 */
	public TalismanTableModel()
	{
		super();
	}
	
	/*
	 * Constructor
	 */
	public TalismanTableModel(ArrayList<Talisman> talismanList)
	{
		super();
		this.talismanList = talismanList;
		
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return talismanList.size();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Talisman tempTalisman = talismanList.get(row);

		switch(column) 
		{
			case 0:
				return tempTalisman.getSkill_1();
			case 1:
				return tempTalisman.getSkill1_Value();
			case 2:
				return tempTalisman.getSkill_2();
			case 3:
				return tempTalisman.getSkill2_Value();
			case 4:
				return tempTalisman.getSlots();
			case 5:
				return tempTalisman.getRarity();
			default:
				return String.class;
		}
		
		
	}
	
	public void addRow(Talisman newTalisman) {
		talismanList.add(newTalisman);
		this.fireTableDataChanged();
	}
	
	public void removeRow(int rowIndex) {
		talismanList.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public void refreshTalismanList(List<Talisman> talismanList)
	{
		this.talismanList = talismanList;
		
		this.fireTableDataChanged();
	}
	
	public Talisman getTalisman(int index)
	{
		return this.talismanList.get(index);
	}
	
	public List<Talisman> getTalismanList()
	{
		return this.talismanList;
	}
	
	@Override
	public Class<?> getColumnClass(int colNum) {
		
		switch(colNum) {
		
		case 0:
			return String.class;
		case 1:
			return Integer.class;
		case 2:
			return String.class;
		case 3:
			return Integer.class;
		case 4:
			return Integer.class;
		case 5:
			return Integer.class;
		default:
			return String.class;
		}
		
	}
	
}
