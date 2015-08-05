package talisman.view;

import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class ActionTalismanPanel extends JPanel {
	
	private EventListener listener;
	
	public ActionTalismanPanel() {
		setBorder(new TitledBorder(null, "Action", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

}
