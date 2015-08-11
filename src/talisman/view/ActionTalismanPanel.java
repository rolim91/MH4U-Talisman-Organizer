package talisman.view;

/**
 * ActionTalismanPanel.java
 *
 * Name: rolim91
 *
 * Description: Actions to the table model are handled here
 * 
 * Features:
 * 				Sets Visibility of buttons
 * 				Refreshes table
 * 				Search the database
 *
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - Implemented above features
 *
 * rolim91 
 */

import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;

import javax.swing.SwingConstants;

@SuppressWarnings("rawtypes")
public class ActionTalismanPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel primaryLabel;
	private JComboBox primaryBox;
	private JComboBox secondaryBox;
	private JButton searchButton;
	private JButton showButton;
	private JButton deleteButton;
	
	public ActionTalismanPanel() {
		setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSearchTable = new JLabel("Search Table");
		GridBagConstraints gbc_lblSearchTable = new GridBagConstraints();
		gbc_lblSearchTable.anchor = GridBagConstraints.WEST;
		gbc_lblSearchTable.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchTable.gridx = 0;
		gbc_lblSearchTable.gridy = 0;
		add(lblSearchTable, gbc_lblSearchTable);
		
		primaryLabel = new JLabel("Primary");
		GridBagConstraints gbc_primaryLabel = new GridBagConstraints();
		gbc_primaryLabel.insets = new Insets(0, 0, 5, 0);
		gbc_primaryLabel.gridx = 0;
		gbc_primaryLabel.gridy = 1;
		add(primaryLabel, gbc_primaryLabel);
		
		primaryBox = new JComboBox();
		GridBagConstraints gbc_primaryBox = new GridBagConstraints();
		gbc_primaryBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_primaryBox.insets = new Insets(0, 0, 5, 0);
		gbc_primaryBox.gridx = 0;
		gbc_primaryBox.gridy = 2;
		add(primaryBox, gbc_primaryBox);
		
		JLabel lblSecondary = new JLabel("Secondary");
		GridBagConstraints gbc_lblSecondary = new GridBagConstraints();
		gbc_lblSecondary.insets = new Insets(0, 0, 5, 0);
		gbc_lblSecondary.gridx = 0;
		gbc_lblSecondary.gridy = 3;
		add(lblSecondary, gbc_lblSecondary);
		
		secondaryBox = new JComboBox();
		GridBagConstraints gbc_secondaryBox = new GridBagConstraints();
		gbc_secondaryBox.insets = new Insets(0, 0, 5, 0);
		gbc_secondaryBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_secondaryBox.gridx = 0;
		gbc_secondaryBox.gridy = 4;
		add(secondaryBox, gbc_secondaryBox);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 0);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 5;
		add(separator_1, gbc_separator_1);
		
		searchButton = new JButton("Search");
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.NORTHEAST;
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.gridx = 0;
		gbc_searchButton.gridy = 6;
		add(searchButton, gbc_searchButton);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.WHITE);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 7;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.weightx = 1.0;
		add(separator, gbc_separator);
		
		JLabel lblTableFunctions = new JLabel("Table Function");
		lblTableFunctions.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTableFunctions = new GridBagConstraints();
		gbc_lblTableFunctions.anchor = GridBagConstraints.WEST;
		gbc_lblTableFunctions.insets = new Insets(0, 0, 5, 0);
		gbc_lblTableFunctions.gridx = 0;
		gbc_lblTableFunctions.gridy = 8;
		add(lblTableFunctions, gbc_lblTableFunctions);
		
		showButton = new JButton("Show All");
		GridBagConstraints gbc_showButton = new GridBagConstraints();
		gbc_showButton.insets = new Insets(0, 0, 5, 0);
		gbc_showButton.gridx = 0;
		gbc_showButton.gridy = 9;
		add(showButton, gbc_showButton);
		
		deleteButton = new JButton("Delete");
		GridBagConstraints gbc_deleteButton = new GridBagConstraints();
		gbc_deleteButton.anchor = GridBagConstraints.SOUTH;
		gbc_deleteButton.gridx = 0;
		gbc_deleteButton.gridy = 11;
		add(deleteButton, gbc_deleteButton);
	}

	/**
	 * @return the primaryLabel
	 */
	public JLabel getPrimaryLabel() {
		return primaryLabel;
	}

	/**
	 * @return the primaryBox
	 */
	public JComboBox getPrimaryBox() {
		return primaryBox;
	}

	/**
	 * @return the secondaryBox
	 */
	public JComboBox getSecondaryBox() {
		return secondaryBox;
	}

	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * @return the showButton
	 */
	public JButton getShowButton() {
		return showButton;
	}

	/**
	 * @return the deleteButton
	 */
	public JButton getDeleteButton() {
		return deleteButton;
	}

	/**
	 * Set button visibility
	 */
	public void setVisibility(boolean visibility)
	{
		this.showButton.setEnabled(visibility);
		this.deleteButton.setEnabled(visibility);
		this.searchButton.setEnabled(visibility);
	}
	
	
}
