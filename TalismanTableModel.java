import javax.swing.table.DefaultTableModel;


public class TalismanTableModel extends DefaultTableModel{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8854771373861055405L;

	public TalismanTableModel()
	{
		String[] columnNames = {"", "", "", "", "", ""};
		
	}
	
	public TalismanTableModel(Object rowData[][], Object columnNames[])
	{
		super(rowData, columnNames);
	}
	
	
	public TalismanTableModel(int i, int j) {
		super(i, j);
	}


	public boolean isCellEditable(int rowIndex, int mColIndex){
		return false;
	}
	
	public void addTalisman(Talisman thisTalisman)
	{
		Object[] thisObject = new Object[6];
		thisObject[0] = thisTalisman.getSkill_1();
		thisObject[1] = thisTalisman.getSkill1_Value();
		thisObject[2] = thisTalisman.getSkill_2();
		thisObject[3] = thisTalisman.getSkill2_Value();
		thisObject[4] = thisTalisman.getSlots();
		thisObject[5] = thisTalisman.getRarity();
		
		this.addRow(thisObject);
	}
}
