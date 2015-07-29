package talisman.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainTalismanWindow {

	private JFrame frmMonsterHunter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainTalismanWindow window = new MainTalismanWindow();
					window.frmMonsterHunter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainTalismanWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonsterHunter = new JFrame();
		frmMonsterHunter.setTitle("Monster Hunter 4 Talisman Organizer");
		frmMonsterHunter.setBounds(100, 100, 709, 541);
		frmMonsterHunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		AddTalismanPanel addTalismanPanel = new AddTalismanPanel();
		
		TableTalismanPanel tableTalismanPanel = new TableTalismanPanel();
		
		ActionTalismanPanel actionTalismanPanel = new ActionTalismanPanel();
		GroupLayout groupLayout = new GroupLayout(frmMonsterHunter.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableTalismanPanel, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(actionTalismanPanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
						.addComponent(addTalismanPanel, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(addTalismanPanel, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tableTalismanPanel, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
						.addComponent(actionTalismanPanel, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
					.addContainerGap())
		);
		frmMonsterHunter.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmMonsterHunter.setJMenuBar(menuBar);
	}
}
