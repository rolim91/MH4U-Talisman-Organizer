package talisman.view;

/**
 * TalismanMenuBar.java
 *
 * Name: rolim91
 *
 * Description: Menu Bar that is shows to the user that handles I/O file operations
 * 
 * Features:
 * 				Button to save, load and exit.
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - Implemented above features
 *
 * rolim91 
 */

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TalismanMenuBar extends JMenuBar {
	
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem exitItem;
	private JMenu mnFile;
	
	public TalismanMenuBar() {
		
		mnFile = new JMenu("File");
		add(mnFile);
		
		saveItem = new JMenuItem("Save");
		mnFile.add(saveItem);
		
		loadItem = new JMenuItem("Load");
		mnFile.add(loadItem);
		
		exitItem = new JMenuItem("Exit");
		mnFile.add(exitItem);
	}
	
	/**
	 * Get JMenu object
	 * @return JMenu object
	 */
	public JMenu getMnFile() {
		return mnFile;
	}

	/**
	 * Get Save Menu Item
	 * @return JMenuItem - save button
	 */
	public JMenuItem getSaveItem() {
		return saveItem;
	}

	/**
	 * Get Load Menu Item
	 * @return JMenuItem - load button
	 */
	public JMenuItem getLoadItem() {
		return loadItem;
	}

	/**
	 * Get Exit Menu Item
	 * @return JMenuItem - exit button
	 */
	public JMenuItem getExitItem() {
		return exitItem;
	}

	

}
