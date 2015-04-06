import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MainWindow implements ActionListener, ChangeListener {
	
	//Support Variables
	private static String skillListLocation = "bin/resources/skill.txt";
	private static ArrayList<Skill> primarySkill, secondarySkill;
	private static String[] primSkillArray, secSkillArray;
	private static TalismanList listOfTalismans;

	//Add Talisman Variables
	private static String selectedPrim, selectedSec;
	private static int selectedPrimVal, selectedSecVal, slotVal, rarityVal;
	
	//UI variables
	private JFrame frmMonsterHunter;
	private JComboBox primarySkillBox, secondarySkillBox;
	private JSpinner primarySpinner, secondarySpinner, slotsSpinner, raritySpinner;
	private JPanel actionsPanel, talismanTablePanel;
	private JMenuItem mntmImport, mntmExport, mntmQuit;
	private JButton addTalismanButton;
	private JScrollPane scrollPane;
	private JTable talismanTable;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private TalismanTableModel talismanModel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		initVariables();
		setUILook();
		
		frmMonsterHunter = new JFrame();
		frmMonsterHunter.setTitle("Monster Hunter 4 Talisman Organizer");
		frmMonsterHunter.setBounds(100, 100, 644, 472);
		frmMonsterHunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultComboBoxModel<String> tempPrimModel = new DefaultComboBoxModel<String>(primSkillArray);
		DefaultComboBoxModel<String> tempSecModel = new DefaultComboBoxModel<String>(secSkillArray);
		
		JPanel addTalismanPanel = new JPanel();
		addTalismanPanel.setBorder(new TitledBorder(null, "Add Talisman", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		actionsPanel = new JPanel();
		actionsPanel.setBorder(new TitledBorder(null, "Actions", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		talismanTablePanel = new JPanel();
		talismanTablePanel.setBorder(new TitledBorder(null, "My Talismans", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		
		GroupLayout groupLayout = new GroupLayout(frmMonsterHunter.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(addTalismanPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(talismanTablePanel, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(actionsPanel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(addTalismanPanel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(actionsPanel, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
						.addComponent(talismanTablePanel, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_talismanTablePanel = new GroupLayout(talismanTablePanel);
		gl_talismanTablePanel.setHorizontalGroup(
			gl_talismanTablePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
		);
		gl_talismanTablePanel.setVerticalGroup(
			gl_talismanTablePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
		);
		
		//Talisman Table
		talismanModel = new TalismanTableModel(); 
		talismanTable = new JTable(talismanModel);
		scrollPane.setViewportView(talismanTable);
		talismanTablePanel.setLayout(gl_talismanTablePanel);
		
		//Spinner declaration
		primarySpinner = new JSpinner();
		secondarySpinner = new JSpinner();
		slotsSpinner = new JSpinner();
		raritySpinner = new JSpinner();
		raritySpinner.setValue(1);
		
		//Skill box declaration
		secondarySkillBox = new JComboBox(tempSecModel);
		secondarySkillBox.setToolTipText("Secondary Skill");
		primarySkillBox = new JComboBox(tempPrimModel);
		primarySkillBox.setToolTipText("Primary Skill");
		
		JLabel primaryLabel = new JLabel("Primary Skill");
		JLabel secondaryLabel = new JLabel("Secondary Skill");
		JLabel slotsLabel = new JLabel("Slots");
		JLabel rarityLabel = new JLabel("Rarity");
		
		addTalismanButton = new JButton("Add Talisman");
		
		
		GroupLayout gl_addTalismanPanel = new GroupLayout(addTalismanPanel);
		gl_addTalismanPanel.setHorizontalGroup(
			gl_addTalismanPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addTalismanPanel.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_addTalismanPanel.createSequentialGroup()
							.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(raritySpinner))
						.addGroup(gl_addTalismanPanel.createSequentialGroup()
							.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_addTalismanPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(primarySkillBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addComponent(primaryLabel)
								.addComponent(secondaryLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(rarityLabel)
								.addComponent(slotsLabel)
								.addComponent(slotsSpinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addComponent(addTalismanButton)
					.addGap(237))
		);
		gl_addTalismanPanel.setVerticalGroup(
			gl_addTalismanPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addTalismanPanel.createSequentialGroup()
					.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(primaryLabel)
						.addComponent(slotsLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(primarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(slotsSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondaryLabel)
						.addComponent(rarityLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_addTalismanPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(raritySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(addTalismanButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		addTalismanPanel.setLayout(gl_addTalismanPanel);
		
		//Listeners
		primarySkillBox.addActionListener(this);
		secondarySkillBox.addActionListener(this);
		addTalismanButton.addActionListener(this);
		secondarySpinner.addChangeListener(this);
		primarySpinner.addChangeListener(this);
		raritySpinner.addChangeListener(this);
		slotsSpinner.addChangeListener(this);
		frmMonsterHunter.getContentPane().setLayout(groupLayout);
		
		
		//Menu Bar and Menu Bar Items
		menuBar = new JMenuBar();
		frmMonsterHunter.setJMenuBar(menuBar);
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mntmImport = new JMenuItem("Import");
		mnFile.add(mntmImport);
		mntmExport = new JMenuItem("Export");
		mnFile.add(mntmExport);
		mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == primarySkillBox)
		{
			int index = primarySkillBox.getSelectedIndex();
			selectedPrim = handleSkillBoxActions(primarySpinner, primarySkill, index);
			//System.out.println("Primary Skill: " + selectedPrim);
		}
		else if(e.getSource() == secondarySkillBox)
		{
			int index = secondarySkillBox.getSelectedIndex();
			selectedSec = handleSkillBoxActions(secondarySpinner, secondarySkill, index);
			//System.out.println("Secondary Skill: " + selectedSec);
		}
		else if(e.getSource() == addTalismanButton)
		{
			addTalisman();
		}
	}
	
	/*
	 * Handles SkillBox Actions.
	 * Sets the value of the selected spinner.
	 * 
	 * Returns the string of the selected actions box.
	 */
	private String handleSkillBoxActions(JSpinner thisSpinner, ArrayList<Skill> thisSkillList, int index)
	{
		String tempString = thisSkillList.get(index).getStringName();
		thisSpinner.setValue(thisSkillList.get(index).getMin());
		
		return tempString;
	}
	
	
	private void addTalisman()
	{
		System.out.println("Add Talisman");
		
		//create Talisman;
		if(!selectedPrim.equals("--"))
		{
			String tempSelectedSec = selectedSec;
			if(selectedSec.equals("--"))
				tempSelectedSec = null;
			
			Talisman thisTalisman = new Talisman(selectedPrim, tempSelectedSec, selectedPrimVal, selectedSecVal, slotVal, rarityVal);
			
			//add thisTalisman to TalismanList
			listOfTalismans.addTalisman(thisTalisman);
			talismanModel.addTalisman(thisTalisman);
		}
		else
			System.out.println("Cannot add empty primary skill");
	}
	
	public void stateChanged(ChangeEvent e)
	{
		//Check if valid value is placed in primarySpinner
		if(e.getSource() == primarySpinner)
		{
			int value = (int)primarySpinner.getValue();
			int index = primarySkillBox.getSelectedIndex();
			
			if(value < primarySkill.get(index).getMin())
				primarySpinner.setValue(primarySkill.get(index).getMin());
			else if(value > primarySkill.get(index).getMax())
				primarySpinner.setValue(primarySkill.get(index).getMax());
			
			selectedPrimVal = (int)primarySpinner.getValue();
			//System.out.println(primarySpinner.getValue());
		}
		//Check if valid value is placed in secondarySpinner
		else if(e.getSource() == secondarySpinner)
		{
			int value = (int)secondarySpinner.getValue();
			int index = secondarySkillBox.getSelectedIndex();
			
			if(value < secondarySkill.get(index).getMin())
				secondarySpinner.setValue(secondarySkill.get(index).getMin());
			else if(value > secondarySkill.get(index).getMax())
				secondarySpinner.setValue(secondarySkill.get(index).getMax());
			
			selectedSecVal = (int)secondarySpinner.getValue();
			//System.out.println(secondarySpinner.getValue());
		}
		else if(e.getSource() == raritySpinner)
		{
			int value = (int)raritySpinner.getValue();
			
			if(value < 1)
				raritySpinner.setValue(1);
			else if(value > 10)
				raritySpinner.setValue(10);
			
			rarityVal = (int)raritySpinner.getValue();
		}
		else if(e.getSource() == slotsSpinner)
		{
			int value = (int)slotsSpinner.getValue();
			
			if(value < 0)
				slotsSpinner.setValue(0);
			else if(value > 3)
				slotsSpinner.setValue(3);
			
			slotVal = (int)slotsSpinner.getValue();
		}
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
	
	/*
	 * Initialize Variables
	 * 
	 * 
	 */
	public static void initVariables()
	{
		initSkills();
		
		listOfTalismans = new TalismanList();
		primSkillArray = Utils.populateSkillArray(primarySkill);
		secSkillArray = Utils.populateSkillArray(secondarySkill);
		
		selectedPrim = "--";
		selectedSec = "--";
		selectedPrimVal = 0;
		selectedSecVal = 0;
		slotVal = 0;
		rarityVal = 1;
	}
	
	/*
	 * Initialize List of primary and secondary skills
	 * 
	 * returns nothing
	 */
	public static void initSkills()
	{
		
		primarySkill = new ArrayList<Skill>();
		secondarySkill = new ArrayList<Skill>();
		
		try{
			Utils.populateSkillList(skillListLocation, primarySkill, secondarySkill);
		} catch (IOException e) {
			System.out.println("File not found");
			System.exit(0);
		}  
		
		
		//System.out.println(primarySkill.get(0));
	}
	
	public static void testSkills(ArrayList<Skill> thisSkill)
	{
		
		for(int i = 0; i < thisSkill.size(); i++)
		{
			System.out.println(thisSkill.get(i));
		}
		
	}
}
