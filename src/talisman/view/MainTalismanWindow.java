package talisman.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.LayoutStyle.ComponentPlacement;

import talisman.controller.TalismanController;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MainTalismanWindow {

	private JFrame frmMonsterHunter;
	private AddTalismanPanel addTalismanPanel;
	private ActionTalismanPanel actionTalismanPanel;
	private TableTalismanPanel tableTalismanPanel;
	private TalismanTableModel talismanTableModel;
	private TalismanController talismanController;
	private TalismanMenuBar talismanMenuBar;
	
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
		frmMonsterHunter.setBounds(100, 100, 819, 641);
		frmMonsterHunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addTalismanPanel = new AddTalismanPanel();
		
		talismanTableModel = new TalismanTableModel();
		tableTalismanPanel = new TableTalismanPanel(talismanTableModel);
		
		actionTalismanPanel = new ActionTalismanPanel();
		GroupLayout groupLayout = new GroupLayout(frmMonsterHunter.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(addTalismanPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tableTalismanPanel, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(actionTalismanPanel, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(addTalismanPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(actionTalismanPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tableTalismanPanel, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
					.addGap(6))
		);
		frmMonsterHunter.getContentPane().setLayout(groupLayout);
		
		talismanMenuBar = new TalismanMenuBar();
		frmMonsterHunter.setJMenuBar(talismanMenuBar);
		
		talismanController = new TalismanController(actionTalismanPanel, addTalismanPanel, tableTalismanPanel, talismanTableModel, talismanMenuBar);
		
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
