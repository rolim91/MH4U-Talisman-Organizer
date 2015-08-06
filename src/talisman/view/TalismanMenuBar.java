package talisman.view;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class TalismanMenuBar extends JMenuBar {
	
	private JMenuItem saveItem;
	private JMenuItem loadItem;
	private JMenuItem exitItem;
	
	public TalismanMenuBar() {
		
		JMenu mnFile = new JMenu("File");
		add(mnFile);
		
		saveItem = new JMenuItem("Save");
		mnFile.add(saveItem);
		
		loadItem = new JMenuItem("Load");
		mnFile.add(loadItem);
		
		exitItem = new JMenuItem("Exit");
		mnFile.add(exitItem);
	}

	public JMenuItem getSaveItem() {
		return saveItem;
	}

	public JMenuItem getLoadItem() {
		return loadItem;
	}

	public JMenuItem getExitItem() {
		return exitItem;
	}

}
