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


public class MainWindow implements ActionListener, ChangeListener {
	
	//Support Variables
	private static String skillListLocation = "bin/resources/skill.txt";
	private static ArrayList<Skill> primarySkill;
	private static ArrayList<Skill> secondarySkill;
	private static String[] primSkillArray;
	private static String[] secSkillArray;

	//Add Talisman Variables
	private static String selectedPrim, selectedSec;
	private static int selectedPrimVal, selectedSecVal;
	
	//UI variables
	private JFrame frmMonsterHunter;
	private JComboBox primarySkillBox, secondarySkillBox;
	private JSpinner primarySpinner, secondarySpinner;
	
	

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
		frmMonsterHunter.setBounds(100, 100, 536, 367);
		frmMonsterHunter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultComboBoxModel<String> tempPrimModel = new DefaultComboBoxModel<String>(primSkillArray);
		primarySkillBox = new JComboBox(tempPrimModel);
		primarySkillBox.addActionListener(this);
		
		DefaultComboBoxModel<String> tempSecModel = new DefaultComboBoxModel<String>(secSkillArray);
		secondarySkillBox = new JComboBox(tempSecModel);
		secondarySkillBox.addActionListener(this);
		
		primarySpinner = new JSpinner();
		primarySpinner.addChangeListener(this);
		
		secondarySpinner = new JSpinner();
		secondarySpinner.addChangeListener(this);
		
		
		GroupLayout groupLayout = new GroupLayout(frmMonsterHunter.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
						.addComponent(primarySkillBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addGap(334))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(primarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(252, Short.MAX_VALUE))
		);
		frmMonsterHunter.getContentPane().setLayout(groupLayout);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == primarySkillBox)
		{
			int index = primarySkillBox.getSelectedIndex();
			selectedPrim = primarySkill.get(index).getStringName();
			primarySpinner.setValue(primarySkill.get(index).getMin());
			//System.out.println("Primary Skill: " + selectedPrim);
		}
		else if(e.getSource() == secondarySkillBox)
		{
			int index = secondarySkillBox.getSelectedIndex();
			selectedSec = secondarySkill.get(index).getStringName();
			secondarySpinner.setValue(secondarySkill.get(index).getMin());
			//System.out.println("Secondary Skill: " + selectedSec);
		}
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
			
			//System.out.println(secondarySpinner.getValue());
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
		populatePrimaryArray();
		populateSecondaryArray();
		
		selectedPrim = "--";
		selectedSec = "--";
		selectedPrimVal = 0;
		selectedSecVal = 0;
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

	public static void populatePrimaryArray()
	{
		int size = primarySkill.size();
		
		primSkillArray = new String[size];
		
		//System.out.print(primSkillArray.length);
		
		for (int i = 0; i < size; i++)
		{
			primSkillArray[i] = primarySkill.get(i).getStringName().replaceAll("_", " ");
			//System.out.println(primSkillArray[i]);
		}
	}
	
	public static void populateSecondaryArray()
	{
		int size = secondarySkill.size();
		
		secSkillArray = new String[size];
		
		//System.out.print(secSkillArray.length);
		
		for (int i = 0; i < size; i++)
		{
			secSkillArray[i] = secondarySkill.get(i).getStringName().replaceAll("_", " ");
			//System.out.println(secSkillArray[i]);
		}
	}
	
	
	public static void testSkills(ArrayList<Skill> thisSkill)
	{
		
		for(int i = 0; i < thisSkill.size(); i++)
		{
			System.out.println(thisSkill.get(i));
		}
		
	}
}
