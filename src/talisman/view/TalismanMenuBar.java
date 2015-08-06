package talisman.view;

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
	
	public JMenu getMnFile() {
		return mnFile;
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
