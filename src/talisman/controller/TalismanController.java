package talisman.controller;

/**
 * TalismanController.java
 *
 * Name: rolim91
 *
 * Description: Controller for the application
 * 
 * Features: This program controls all of the application's buttons and interfaces
 * 				
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - 
 *
 * rolim91 
 */

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
	
	
	/**
	 * Initialize listeners
	 */
	private void initListeners()
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
			this.actionTalismanPanel.setVisibility(true);
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
			this.deleteSelectedConfirmation();
		}
		
	}

	
	/**
	 * Initialize Variables
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
		this.fileChooser = new JFileChooser();
		this.fileChooser.setFileFilter(new FileNameExtensionFilter( "Standard Program File", "txt"));
		this.fileChooser.setCurrentDirectory(tempFile);
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.addChoosableFileFilter(new FileNameExtensionFilter( "Athena Charm File", "txt"));
		this.fileChooser.setFileFilter(this.fileChooser.getChoosableFileFilters()[1]);
		this.fileChooser.setSelectedFile(new File("My_Talismans.txt"));
	}
	
	
	/**
	 * Initialize List of primary and secondary skills
	 * 
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

	/**
	 * Handles SkillBox Actions.
	 * Sets the value of the selected spinner.
	 * @param JSpinner - the spinner being changed
	 * @param thisSkillList - the list of skills of which the spinner is referenced to
	 * @param index - the index of the specific skill
	 * @return the string of the selected actions box.
	 */
	private String handleSkillBoxActions(JSpinner thisSpinner, ArrayList<Skill> thisSkillList, int index)
	{
		String tempString = thisSkillList.get(index).getStringName();
		thisSpinner.setValue(thisSkillList.get(index).getMin());
		
		return tempString;
	}
	
	/**
	 * Add a talisman to database
	 */
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
	
	/**
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
	
	/**
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
	
	/**
	 * Show Window with talismans that are required to be deleted
	 */
	private void showDeleteTalisman(List<Talisman> deleteTalisman)
	{
		deleteTableModel.refreshTalismanList(deleteTalisman);
		deleteDialog.setDeleteTalismanModel(deleteTableModel);
		deleteDialog.pack();
		this.addTalismanPanel.getAddTalismanButton().setEnabled(false);
		this.actionTalismanPanel.setVisibility(false);
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
	
	/**
	 * Load Talisman to program from file
	 */
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
					Talisman[] tempTalisman = null;
					
					if(this.fileChooser.getFileFilter() == this.fileChooser.getChoosableFileFilters()[1])
					{
						System.out.println("Athena");
						tempTalisman = this.createTalismanListAthena(loadThis);
					}
					else
					{
						System.out.println("Standard");
						tempTalisman = this.createTalismanList(loadThis);
					}
					
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
	
	/**
	 * Create a list of Talisman object from a given array list of strings
	 * @param stringList
	 * @return Talisman array
	 */
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
			wordScanner.close();
		}
		
		return talismanList;
	}
	
	/**
	 * create a talisman list from a string list taken from Athena's ASS charms file
	 * @param stringList
	 * @return Talisman array
	 */
	private Talisman[] createTalismanListAthena(ArrayList<String> stringList)
	{
		Talisman[] talismanList = new Talisman[stringList.size() - 1];
		Scanner wordScanner;
		
		for(int i = 1; i < stringList.size(); i++)
		{
			wordScanner = new Scanner(stringList.get(i));
			wordScanner.useDelimiter(",");
			int slot =  Integer.parseInt(wordScanner.next());
			String skill_1 = wordScanner.next();
			int skill1_val = Integer.parseInt(wordScanner.next());
			
			String skill_2 = wordScanner.next();
			int skill2_val;

			if(!skill_2.equals(""))
			{
				skill2_val = Integer.parseInt(wordScanner.next());
			}
			else
			{
				skill_2 = "--";
				skill2_val = 0;
			}
			

			
			Talisman tempTalisman = new Talisman(i, skill_1, skill_2, skill1_val, skill2_val, slot, 0);
			System.out.println(tempTalisman);
			talismanList[i-1] = tempTalisman;
			wordScanner.close();
		}
		
		return talismanList;
	}
	
	/**
	 * Insert the talisman array to the database
	 * @param talismanArray
	 */
	private void insertLoadedToTable(Talisman[] talismanArray)
	{
		this.talismanDAOImpl.clearTable();
		this.id = talismanArray.length+1;
		
		for(int i = 0; i < talismanArray.length; i++)
			this.talismanDAOImpl.insertTalisman(talismanArray[i]);
		
		this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
		
	}
	
	/**
	 * Save the talismans to a file
	 */
	private void saveTalismans()
	{
		
		//save
		
		int status = this.fileChooser.showSaveDialog(null);
		
		if(status == JFileChooser.APPROVE_OPTION)
		{
			List<Talisman> saveThis = this.talismanDAOImpl.retrieveList();
			String[] saveStrings = null;
			
			if(this.fileChooser.getFileFilter() == this.fileChooser.getChoosableFileFilters()[1])
			{
				System.out.println("Athena");
				saveStrings = this.saveToFileAthena(saveThis);
			}
			else
			{
				System.out.println("Regular");
				saveStrings = this.saveToFile(saveThis);
			}
			
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
	
	/**
	 * Checks if file exists and ask to overwrite
	 * @param filename
	 * @param saveStrings
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 */
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
	
	/**
	 * Get a string of Strings that is to be saved to a file
	 * @param talisList - a list of talisman to save
	 * @return String array of talisman lines
	 */
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
	
	/**
	 * Get a string of Strings that is to be saved to a file
	 * @param talisList - a list of talisman to save
	 * @return String array of talisman lines
	 */
	private String[] saveToFileAthena(List<Talisman> talisList)
	{
		String[] stringList = new String[talisList.size() + 1];
		stringList[0] = "#Format: Slots,Skill1,Points1,Skill2,Points2";
		System.out.println(stringList.length);
		
		for(int i = 1; i <= talisList.size(); i++)
		{
			stringList[i] = talisList.get(i-1).getSlots() + "," + 
							talisList.get(i-1).getSkill_1().replaceAll("_", " ") + "," + 
							talisList.get(i-1).getSkill1_Value() + ",";
			
			if(talisList.get(i-1).getSkill_2().equals("--"))
			{
				stringList[i] += ",";
			}
			else
			{
				stringList[i] += 	talisList.get(i-1).getSkill_2().replaceAll("_", " ") + "," + 
									talisList.get(i-1).getSkill2_Value();
			}
							
			//System.out.println(stringList[i]);
		}
		
		return stringList;
	}
	
	 /**
	  * Search table for values
	  */
	public void searchTable()
	{
		System.out.println("Search Table");
		
		String primaryString = this.actionTalismanPanel.getPrimaryBox().getSelectedItem().toString();
		String secondaryString = this.actionTalismanPanel.getSecondaryBox().getSelectedItem().toString();
		
		if(primaryString.equals("--") && secondaryString.equals("--"))
		{
			this.showTable();
		}
		else if(!primaryString.equals("--"))
		{
			List<Talisman> searchResult;
			
			if(secondaryString.equals("--"))
				searchResult = this.talismanDAOImpl.searchSingle(primaryString);
			else
				searchResult = this.talismanDAOImpl.searchDouble(primaryString, secondaryString);

			this.talismanTableModel.refreshTalismanList(searchResult);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Cannot search or Talisman(s) with empty primary skill.");
		}
	}
	
	/**
	 * Shows all the talismans in the database
	 */
	public void showTable()
	{
		System.out.println("Show Table");
		this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
	}
	
	/**
	 * Confirms user if the selected talismans is to be deleted
	 */
	public void deleteSelectedConfirmation()
	{
		if(this.tableTalismanPanel.getTalismanTable().getSelectedRows().length > 0)
		{
		
			int result = JOptionPane.showConfirmDialog(null,"Delete the selected talisman(s)?","Delete Talisman(s)", JOptionPane.YES_NO_OPTION);
	        switch(result){
	            case JOptionPane.YES_OPTION:
	            	this.deleteSelectedTalisman();
	                return;
	            case JOptionPane.NO_OPTION:
	                return;
	            default:
	            	return;
	        }
		}
 
	}
	
	/**
	 * Delete selected talismans
	 */
	public void deleteSelectedTalisman()
	{
		System.out.println("Delete Talisman");
		int[] rowIndices = this.tableTalismanPanel.getTalismanTable().getSelectedRows();
		Talisman[] deleteTalismans = new Talisman[rowIndices.length];
		List<Talisman> saveList = this.talismanTableModel.getTalismanList();
		boolean deleted = false;
		
		for(int i = 0; i < rowIndices.length; i++)
		{
			System.out.println(this.talismanTableModel.getTalisman(rowIndices[i]));
			deleteTalismans[i] = this.talismanTableModel.getTalisman(rowIndices[i]);
		}
		
		for(int i = deleteTalismans.length - 1; i >= 0; i--)
		{
			this.talismanDAOImpl.deleteTalisman(deleteTalismans[i]);
			saveList.remove(rowIndices[i]);
			deleted = true;
		}
		
		if(deleted = true)
			this.talismanTableModel.refreshTalismanList(saveList);
		
	}
	
	/**
	 * tests save feature
	 */
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
	
	/*
	 * Add talisman to databases
	 */
	private void addTalismansToDatabase(List<Talisman> taliList)
	{
		for(int i = 0; i < taliList.size(); i++)
			this.talismanDAOImpl.insertTalisman(taliList.get(i));
	}
	
	/**
	 * TestAll
	 */
	private void testAll()
	{
		//test Primary
		this.testPrimary();
		
		//test Secondary
		this.testSecondary();
		
		this.talismanTableModel.refreshTalismanList(this.talismanDAOImpl.retrieveList());
	}
	
	/**
	 * testPrimary
	 */
	private void testPrimary()
	{
		
		for(int i = 1; i < TalismanController.primarySkill.size(); i++)
		{
			Skill thisSkill = TalismanController.primarySkill.get(i);
			
			for(int j = thisSkill.getMin(); j <= thisSkill.getMax(); j++)
			{
				this.talismanDAOImpl.insertTalisman(new Talisman(this.id, thisSkill.getStringName(), "--", j, 0, 0, 0));
				this.id++;
			}
			
		}
		
	}
	
	/**
	 * testSecondary
	 */
	private void testSecondary()
	{
		for(int i = 1; i < TalismanController.secondarySkill.size(); i++)
		{
			Skill thisSkill = TalismanController.secondarySkill.get(i);
			
			for(int j = thisSkill.getMin(); j <= thisSkill.getMax(); j++)
			{
				this.talismanDAOImpl.insertTalisman(new Talisman(this.id, "Attack", thisSkill.getStringName(), 0, j, 0, 0));
				this.id++;
			}
			
		}
		
	}
	
}
