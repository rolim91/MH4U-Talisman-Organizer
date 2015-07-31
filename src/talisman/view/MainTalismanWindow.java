package talisman.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.LayoutStyle.ComponentPlacement;


public class MainTalismanWindow {

	private JFrame frmMonsterHunter;
	private AddTalismanPanel addTalismanPanel;
	private ActionTalismanPanel actionTalismanPanel;
	private TableTalismanPanel tableTalismanPanel;
	private TalismanTableModel talismanTableModel;
	
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
		
		setUILook();
		
		frmMonsterHunter = new JFrame();
		frmMonsterHunter.setTitle("Monster Hunter 4 Talisman Organizer");
		frmMonsterHunter.setBounds(100, 100, 709, 541);
		frmMonsterHunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addTalismanPanel = new AddTalismanPanel();
		
		talismanTableModel = new TalismanTableModel();
		tableTalismanPanel = new TableTalismanPanel(talismanTableModel);
		
		actionTalismanPanel = new ActionTalismanPanel();
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
	
	
	public void setUILook()
	{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch ( ClassNotFoundException e ) {
		        System.out.println("Class Not Found");
		        System.exit(0);
		    } catch ( InstantiationException e ) {
		        System.out.println("Instantiation Error");
		        System.exit(0);
		    } catch ( IllegalAccessException e ) {
		        System.out.println("IllegalAccessException");
		        System.exit(0);
		    } catch ( UnsupportedLookAndFeelException e ) {
		        System.out.println("UnsupportedLookAndFeelException");
		        System.exit(0);
		    }
			
	}
}
