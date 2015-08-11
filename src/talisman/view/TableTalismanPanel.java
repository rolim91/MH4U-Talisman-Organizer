package talisman.view;

/**
 * TableTalismanPanel.java
 *
 * Name: rolim91
 *
 * Description: Shows the user a table from a table model
 * 
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - Implemented this panel
 *
 * rolim91 
 */

import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import talisman.model.Talisman;

public class TableTalismanPanel extends JPanel {
	
	private JTable talismanTable;
	private TalismanTableModel talismanModel;
	
	public TableTalismanPanel(TalismanTableModel talismanModel) {
		setBorder(new TitledBorder(null, "Talisman List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
		);
		
		this.talismanModel = talismanModel;
		talismanTable = new JTable(talismanModel);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		talismanTable.setDefaultRenderer(String.class, centerRenderer);
		
		TableRowSorter<TalismanTableModel> rowSorter = new TableRowSorter<TalismanTableModel>(talismanModel);
		talismanTable.setRowSorter(rowSorter);
		
		//set comparator for  3rd column, to output ordered strings
		rowSorter.setComparator(2, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				
				System.out.println(o1);
				
				if(o1.equals("--") && o2.equals("--"))
					return 0;
				else if(o1.equals("--"))
					return 1;
				else if(o2.equals("--"))
					return -1;
				
				return o1.compareTo(o2);
			}
		});
		
		scrollPane.setViewportView(talismanTable);
		setLayout(groupLayout);
		
	}

	/**
	 * @return the talismanTable
	 */
	public JTable getTalismanTable() {
		return talismanTable;
	}
	
	
}
