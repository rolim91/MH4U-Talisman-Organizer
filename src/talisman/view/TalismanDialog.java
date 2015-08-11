package talisman.view;

/**
 * TalismanDialog.java
 *
 * Name: rolim91
 *
 * Description: Shows a user a dialog window for actions taken by user when deleting or inserting a talisman
 * 
 * Features: 	Table of talisman
 * 				Button of actions
 * 				
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - Implemented above features
 *
 * rolim91 
 */

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class TalismanDialog extends JDialog {
	
	private JTable table;
	JLabel dialogLabel;
	private TalismanTableModel deleteTalismanModel;
	private JButton doneButton;
	private JButton cancelButton;
	
	
	public TalismanDialog(TalismanTableModel talismanModel) {
		setTitle("Delete Talismans");
		
		dialogLabel = new JLabel("Delete Conflicting Talisman(s) in your game");
		dialogLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		dialogLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		this.deleteTalismanModel = talismanModel;
		table = new JTable(deleteTalismanModel);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
		table.setDefaultRenderer(String.class, centerRenderer);
		scrollPane.setViewportView(table);
		
		TableRowSorter<TalismanTableModel> rowSorter = new TableRowSorter<TalismanTableModel>(deleteTalismanModel);
		table.setRowSorter(rowSorter);
		
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
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(dialogLabel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(dialogLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		doneButton = new JButton("Done");
		panel.add(doneButton);
		
		cancelButton = new JButton("Cancel");
		panel.add(cancelButton);
		
		
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Return the reference to the done button
	 * @return JButton of the done button
	 */
	public JButton getDoneButton() {
		return doneButton;
	}

	/**
	 * Set the table model to the given parameter
	 * @param deleteTalismanModel
	 */
	public void setDeleteTalismanModel(TalismanTableModel deleteTalismanModel) {
		this.deleteTalismanModel = deleteTalismanModel;
	}

	/**
	 * Return the reference to the cancel button
	 * @return JButton of the cancel button
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}
	
	
}
