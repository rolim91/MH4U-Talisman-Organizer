package talisman.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import skill.Skill;
import talisman.dao.TalismanDAOImpl;
import talisman.model.Talisman;
import talisman.view.*;
import utilities.Utils;

public class TalismanController implements ActionListener, ChangeListener, WindowListener {

	//Support Variables
	private static String skillListLocation = "bin/resources/skill.txt";
	private static ArrayList<Skill> primarySkill, secondarySkill;
	
	//View Variables
	private ActionTalismanPanel actionTalismanPanel;
	private AddTalismanPanel addTalismanPanel;
	private TableTalismanPanel tableTalismanPanel;
	private TalismanTableModel talismanTableModel;
	private TalismanDialog deleteDialog;
	private TalismanTableModel deleteTableModel;
	private TalismanMenuBar talismanMenuBar;
	private JFileChooser fileChooser;
	
	//Database Variables
	private TalismanDAOImpl talismanDAOImpl;
	
	//Add Talisman Variables
	private int id = 0;
	private Talisman currentTalisman;
	
	public TalismanController(ActionTalismanPanel actionTalismanPanel, AddTalismanPanel addTalismanPanel, TableTalismanPanel tableTalismanPanel, TalismanTableModel talismanTableModel) {
		
		//set the variables
		this.actionTalismanPanel = actionTalismanPanel;
		this.addTalismanPanel = addTalismanPanel;
		this.tableTalismanPanel = tableTalismanPanel;
		this.talismanTableModel = talismanTableModel;
		this.talismanDAOImpl = new TalismanDAOImpl();
		this.deleteTableModel = new TalismanTableModel();
		this.deleteDialog = new TalismanDialog(this.deleteTableModel);
		
		//initialize variables
		this.initVariables();
		
		//initialize listeners
		this.initListeners();
		
	}
	
	public TalismanController(ActionTalismanPanel actionTalismanPanel, AddTalismanPanel addTalismanPanel, TableTalismanPanel tableTalismanPanel, TalismanTableModel talismanTableModel, TalismanMenuBar talismanMenuBar) {
		
		//set the variables
		this.actionTalismanPanel = actionTalismanPanel;
		this.addTalismanPanel = addTalismanPanel;
		this.tableTalismanPanel = tableTalismanPanel;
		this.talismanTableModel = talismanTableModel;
		this.talismanMenuBar = talismanMenuBar;
		this.talismanDAOImpl = new TalismanDAOImpl();
		this.deleteTableModel = new TalismanTableModel();
		this.deleteDialog = new TalismanDialog(this.deleteTableModel);
		
		//initialize variables
		this.initVariables();
		
		//initialize listeners
		this.initListeners();
		
	}
	
	
	/*
	 * 
	 */
	public void initListeners()
	{
		this.addTalismanPanel.getAddTalismanButton().addActionListener(this);
		this.addTalismanPanel.getPrimarySkillBox().addActionListener(this);
		this.addTalismanPanel.getSecondarySkillBox().addActionListener(this);
		this.addTalismanPanel.getPrimarySpinner().addChangeListener(this);
		this.addTalismanPanel.getSecondarySpinner().addChangeListener(this);
		this.addTalismanPanel.getSlotSpinner().addChangeListener(this);
		this.addTalismanPanel.getRaritySpinner().addChangeListener(this);
		this.deleteDialog.getDoneButton().addActionListener(this);
		this.deleteDialog.getCancelButton().addActionListener(this);
		this.deleteDialog.addWindowListener(this);
		this.talismanMenuBar.getSaveItem().addActionListener(this);
		this.talismanMenuBar.getLoadItem().addActionListener(this);
		this.talismanMenuBar.getExitItem().addActionListener(this);
		this.actionTalismanPanel.getSearchButton().addActionListener(this);
		this.actionTalismanPanel.getShowButton().addActionListener(this);
		this.actionTalismanPanel.getDeleteButton().addActionListener(this  );
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		
		//Check if valid value is placed in primarySpinner
		if(arg0.getSource() == this.addTalismanPanel.getPrimarySpinner())
		{
			int value = (int)this.addTalismanPanel.getPrimarySpinner().getValue();
			int index = this.addTalismanPanel.getPrimarySkillBox().getSelectedIndex();
			
			if(value < primarySkill.get(index).getMin())
				this.addTalismanPanel.getPrimarySpinner().setValue(primarySkill.get(index).getMin());
			else if(value > primarySkill.get(index).getMax())
				this.addTalismanPanel.getPrimarySpinner().setValue(primarySkill.get(index).getMax());
			
			//System.out.println(this.addTalismanPanel.getPrimarySpinner().getValue());
		}
		//Check if valid value is placed in secondarySpinner
		else if(arg0.getSource() == this.addTalismanPanel.getSecondarySpinner())
		{
			int value = (int)this.addTalismanPanel.getSecondarySpinner().getValue();
			int index = this.addTalismanPanel.getSecondarySkillBox().getSelectedIndex();
			
			if(value < secondarySkill.get(index).getMin())
				this.addTalismanPanel.getSecondarySpinner().setValue(secondarySkill.get(index).getMin());
			else if(value > secondarySkill.get(index).getMax())
				this.addTalismanPanel.getSecondarySpinner().setValue(secondarySkill.get(index).getMax());
			
			//System.out.println(this.addTalismanPanel.getSecondarySpinner().getValue());
		}
		else if(arg0.getSource() == this.addTalismanPanel.getRaritySpinner())
		{
			int value = (int)this.addTalismanPanel.getRaritySpinner().getValue();
			
			if(value < 1)
				this.addTalismanPanel.getRaritySpinner().setValue(1);
			else if(value > 10)
				this.addTalismanPanel.getRaritySpinner().setValue(10);
			
		}
		else if(arg0.getSource() ==  this.addTalismanPanel.getSlotSpinner())
		{
			int value = (int)this.addTalismanPanel.getSlotSpinner().getValue();
			
			if(value < 0)
				this.addTalismanPanel.getSlotSpinner().setValue(0);
			else if(value > 3)
				this.addTalismanPanel.getSlotSpinner().setValue(3);
			
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(arg0.getSource() == this.addTalismanPanel.getPrimarySkillBox())
		{
			int index = this.addTalismanPanel.getPrimarySkillBox().getSelectedIndex();
			handleSkillBoxActions(this.addTalismanPanel.getPrimarySpinner(), primarySkill, index);
			
			//System.out.println("Primary Skill: " + String.valueOf(this.addTalismanPanel.getPrimarySkillBox().getSelectedItem()));
		}
		else if(arg0.getSource() ==  this.addTalismanPanel.getSecondarySkillBox())
		{
			int index = this.addTalismanPanel.getSecondarySkillBox().getSelectedIndex();
			handleSkillBoxActions(this.addTalismanPanel.getSecondarySpinner(), secondarySkill, index);
			
			//System.out.println("Secondary Skill: " + String.valueOf(this.addTalismanPanel.getSecondarySkillBox().getSelectedItem()));
		}
		else if(arg0.getSource() == this.addTalismanPanel.getAddTalismanButton())
		{
			System.out.println("Add Talisman");
			addTalisman();
		}
		else if(arg0.getSource() == this.deleteDialog.getDoneButton())
		{
			deleteDialog.setVisible(false);
			this.talismanDAOImpl.deleteThenInsert(currentTalisman);
			this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
			this.addTalismanPanel.getAddTalismanButton().setEnabled(true);
			currentTalisman = null;
		}
		else if(arg0.getSource() == this.deleteDialog.getCancelButton())
		{
			this.cancelOperation();
		}
		else if(arg0.getSource() == this.talismanMenuBar.getSaveItem())
		{
			System.out.println("Save Item");
			this.saveTalismans();
		}
		else if(arg0.getSource() == this.talismanMenuBar.getLoadItem())
		{
			System.out.println("Load Item");
			this.loadTalismans();
		}
		else if(arg0.getSource() == this.talismanMenuBar.getExitItem())
		{
			System.out.println("Exit Item");
			System.exit(0);
		}
		else if(arg0.getSource() == this.actionTalismanPanel.getSearchButton())
		{
			this.searchTable();
		}
		else if(arg0.getSource() == this.actionTalismanPanel.getShowButton())
		{
			this.showTable();
		}
		else if(arg0.getSource() == this.actionTalismanPanel.getDeleteButton())
		{
			this.deleteSelectedTalisman();
		}
		
	}
	
	public void searchTable()
	{
		System.out.println("Search Table"); 
	}
	
	public void showTable()
	{
		System.out.println("Show Table");
	}
	
	public void deleteSelectedTalisman()
	{
		System.out.println("Delete Talisman");
	}
	
	/*
	 * Initialize Variables
	 * 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void initVariables()
	{
		initSkills();
		
		DefaultComboBoxModel<String> tempPrimModelAdd = new DefaultComboBoxModel<String>(Utils.populateSkillArray(primarySkill));
		this.addTalismanPanel.getPrimarySkillBox().setModel(tempPrimModelAdd);
		
		DefaultComboBoxModel<String> tempSecModelAdd = new DefaultComboBoxModel<String>(Utils.populateSkillArray(secondarySkill));
		this.addTalismanPanel.getSecondarySkillBox().setModel(tempSecModelAdd);
		
		DefaultComboBoxModel<String> tempPrimModelAction = new DefaultComboBoxModel<String>(Utils.populateSkillArray(primarySkill));
		this.actionTalismanPanel.getPrimaryBox().setModel(tempPrimModelAction);
		
		DefaultComboBoxModel<String> tempSecModelAction = new DefaultComboBoxModel<String>(Utils.populateSkillArray(secondarySkill));
		this.actionTalismanPanel.getSecondaryBox().setModel(tempSecModelAction);
		
		File tempFile = new File(".");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter( "Text File", "txt"));
		fileChooser.setCurrentDirectory(tempFile);
		fileChooser.setSelectedFile(new File("My_Talismans.txt"));
	}
	
	
	/*
	 * Initialize List of primary and secondary skills
	 * 
	 * returns nothing
	 */
	public void initSkills()
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
		if(!String.valueOf(this.addTalismanPanel.getPrimarySkillBox().getSelectedItem()).equals("--"))
		{
			currentTalisman = this.createTalisman();
			//System.out.println(insertTalisman);
			this.insertTalisman();
			
		}
		else
		{
			JFrame frame = new JFrame("Message");
			JOptionPane.showMessageDialog(frame, "Cannot add Talisman with empty primary skill.");
		}
	}
	
	/*
	 * Create talisman from the set of AddTalismanPanel variables
	 */
	private Talisman createTalisman()
	{
		int id = this.id;
		String skill_1 = String.valueOf(this.addTalismanPanel.getPrimarySkillBox().getSelectedItem());
		String skill_2 = String.valueOf(this.addTalismanPanel.getSecondarySkillBox().getSelectedItem());
		int skill1_val = (Integer)this.addTalismanPanel.getPrimarySpinner().getValue();
		int skill2_val = (Integer)this.addTalismanPanel.getSecondarySpinner().getValue();
		int slot = (Integer)this.addTalismanPanel.getSlotSpinner().getValue();
		int rarity = (Integer)this.addTalismanPanel.getRaritySpinner().getValue();
		
		this.id++;
		
		return new Talisman(id, skill_1, skill_2, skill1_val, skill2_val, slot, rarity);
	}
	
	/*
	 * Try to insert the talisman
	 */
	private void insertTalisman()
	{
		List<Talisman> deleteTalisman = talismanDAOImpl.checkInsert(currentTalisman);
		
		//Check if talisman can be inserted
		if(deleteTalisman != null)
		{
			if(deleteTalisman.isEmpty())
			{
				JFrame frame = new JFrame("Message");
				JOptionPane.showMessageDialog(frame, "Talisman can be kept. No conflicting talismans found.");
				this.talismanDAOImpl.insertTalisman(currentTalisman);
				this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
				currentTalisman = null;
			}
			else
			{
				this.showDeleteTalisman(deleteTalisman);
			}
		}
		else
		{
			//DIALOG saying "There is a Talisman with higher value."
			this.id--;
			JFrame frame = new JFrame("Message");
			JOptionPane.showMessageDialog(frame, "There is a Talisman with higher value.");
			currentTalisman = null;
		}
	}
	
	/*
	 * Show Window with talismans that are required to be deleted
	 */
	private void showDeleteTalisman(List<Talisman> deleteTalisman)
	{
		deleteTableModel.refreshTalismanList(deleteTalisman);
		deleteDialog.setDeleteTalismanModel(deleteTableModel);
		deleteDialog.pack();
		this.addTalismanPanel.getAddTalismanButton().setEnabled(false);
		deleteDialog.setVisible(true);
	}




	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		this.cancelOperation();
	}




	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void cancelOperation()
	{
		deleteDialog.setVisible(false);
		this.addTalismanPanel.getAddTalismanButton().setEnabled(true);
		currentTalisman = null;
	}
	
	private void loadTalismans()
	{
		//reset
		
		int status = this.fileChooser.showOpenDialog(null);
		if(status == JFileChooser.APPROVE_OPTION)
		{
			File f = new File(this.fileChooser.getSelectedFile().getAbsolutePath());
			
			if(f.exists())
			{
				try{
					ArrayList<String> loadThis = Utils.openFromFile(this.fileChooser.getSelectedFile().getAbsolutePath());
					Talisman[] tempTalisman = this.createTalismanList(loadThis);
					this.insertLoadedToTable(tempTalisman);
					
					
				} catch (FileNotFoundException | NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Wrong file, file corrupted or missing. Please choose another file");
					this.loadTalismans();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "File does not exists.");
				this.loadTalismans();
			}
		}
		
	}
	
	private Talisman[] createTalismanList(ArrayList<String> stringList)
	{
		Talisman[] talismanList = new Talisman[stringList.size()];
		Scanner wordScanner;
		
		for(int i = 0; i < stringList.size(); i++)
		{
			wordScanner = new Scanner(stringList.get(i));
			String skill_1 = wordScanner.next();
			String skill_2 = wordScanner.next();
			int skill1_val = Integer.parseInt(wordScanner.next());
			int skill2_val = Integer.parseInt(wordScanner.next());
			int slot = Integer.parseInt(wordScanner.next());
			int rarity = Integer.parseInt(wordScanner.next());
			
			Talisman tempTalisman = new Talisman(i+1, skill_1, skill_2, skill1_val, skill2_val, slot, rarity);
			talismanList[i] = tempTalisman;
		}
		
		
		return talismanList;
	}
	
	private void insertLoadedToTable(Talisman[] talismanArray)
	{
		this.talismanDAOImpl.clearTable();
		this.id = talismanArray.length+1;
		
		for(int i = 0; i < talismanArray.length; i++)
			this.talismanDAOImpl.insertTalisman(talismanArray[i]);
		
		this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
		
	}
	
	private void saveTalismans()
	{
		//TEST
		//testSave();
		
		//save
		
		int status = this.fileChooser.showSaveDialog(null);
		
		if(status == JFileChooser.APPROVE_OPTION)
		{
			List<Talisman> saveThis = this.talismanDAOImpl.retrieveList();
			String[] saveStrings = this.saveToFile(saveThis);
			try {
				String filename = this.fileChooser.getSelectedFile().getAbsolutePath();
				this.checkSaveFileExists(filename, saveStrings);
			} catch ( FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Something went wrong");
			} catch (NullPointerException e) {
				System.out.println("Cancelled Operation");
			}
		}
		
	}
	
	private void checkSaveFileExists(String filename, String[] saveStrings) throws FileNotFoundException, UnsupportedEncodingException
	{
		File f = new File(filename);
		if(f.exists()){
            int result = JOptionPane.showConfirmDialog(null,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
            switch(result){
                case JOptionPane.YES_OPTION:
        			Utils.writeToFile(saveStrings, filename);
                    return;
                case JOptionPane.CANCEL_OPTION:
                	this.saveTalismans();
                    return;
                default:
                	return;
            }
        }
		
		Utils.writeToFile(saveStrings, filename);
	}
	
	private String[] saveToFile(List<Talisman> talisList)
	{
		String[] stringList = new String[talisList.size()];
		
		for(int i = 0; i < talisList.size(); i++)
		{
			stringList[i] = talisList.get(i).getSkill_1().replaceAll(" ", "_") + "\t" + 
							talisList.get(i).getSkill_2().replaceAll(" ", "_") + "\t" + 
							talisList.get(i).getSkill1_Value() + "\t" + 
							talisList.get(i).getSkill2_Value() + "\t" + 
							talisList.get(i).getSlots() + "\t" + 
							talisList.get(i).getRarity();
			
			//System.out.println(stringList[i]);
		}
		
		return stringList;
	}
	
	
	private void testSave()
	{
		ArrayList<Talisman> highList = new ArrayList<Talisman>();
		highList.add(new Talisman(2, "Anti-Theft", "--", 5, 0, 2, 0));
		highList.add(new Talisman(3, "Anti-Theft", "--", 7, 0, 2, 0));
		highList.add(new Talisman(4, "Anti-Theft", "--", 7, 0, 3, 0));
		highList.add(new Talisman(5, "Anti-Theft", "--", 5, 0, 3, 0));
		
		highList.add(new Talisman(6, "Attack", "Anti-Theft", 5, 5, 2, 0));
		highList.add(new Talisman(7, "Attack", "Anti-Theft", 7, 5, 2, 0));
		highList.add(new Talisman(8, "Attack", "Anti-Theft", 7, 7, 2, 0));
		highList.add(new Talisman(9, "Attack", "Anti-Theft", 7, 7, 3, 0));
		highList.add(new Talisman(10, "Attack", "Anti-Theft", 5, 7, 2, 0));
		highList.add(new Talisman(11, "Attack", "Anti-Theft", 5, 7, 3, 0));
		highList.add(new Talisman(12, "Attack", "Anti-Theft", 5, 5, 3, 0));
		highList.add(new Talisman(13, "Anti-Theft", "Attack", 7, 5, 2, 0));
		
		addTalismansToDatabase(highList);
	}
	
	private void addTalismansToDatabase(List<Talisman> taliList)
	{
		for(int i = 0; i < taliList.size(); i++)
			this.talismanDAOImpl.insertTalisman(taliList.get(i));
	}
}
