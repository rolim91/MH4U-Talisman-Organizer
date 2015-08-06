package talisman.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
			handleSkillBoxActions(this.addTalismanPanel.getPrimarySpinner(), secondarySkill, index);
			
			//System.out.println("Secondary Skill: " + String.valueOf(this.addTalismanPanel.getSecondarySkillBox().getSelectedItem()));
		}
		else if(arg0.getSource() == this.addTalismanPanel.getAddTalismanButton())
		{
			System.out.println("Add Talisman");
			addTalisman();
		}
		else if(arg0.getSource() == this.deleteDialog.getDoneButton())
		{
			deleteDialog.setVisible(true);
			this.talismanDAOImpl.insertTalisman(currentTalisman);
			this.talismanTableModel.addTalismanList(this.talismanDAOImpl.retrieveList());
			this.addTalismanPanel.getAddTalismanButton().setEnabled(true);
			currentTalisman = null;
		}
		
	}
	
	
	/*
	 * 
	 */
	public void initListeners()
	{
		addTalismanPanel.getAddTalismanButton().addActionListener(this);
		addTalismanPanel.getPrimarySkillBox().addActionListener(this);
		addTalismanPanel.getSecondarySkillBox().addActionListener(this);
		addTalismanPanel.getPrimarySpinner().addChangeListener(this);
		addTalismanPanel.getSecondarySpinner().addChangeListener(this);
		addTalismanPanel.getSlotSpinner().addChangeListener(this);
		addTalismanPanel.getRaritySpinner().addChangeListener(this);
		deleteDialog.getDoneButton().addActionListener(this);
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
		
		DefaultComboBoxModel<String> tempPrimModel = new DefaultComboBoxModel<String>(Utils.populateSkillArray(primarySkill));
		this.addTalismanPanel.getPrimarySkillBox().setModel(tempPrimModel);
		

		DefaultComboBoxModel<String> tempSecModel = new DefaultComboBoxModel<String>(Utils.populateSkillArray(secondarySkill));
		this.addTalismanPanel.getSecondarySkillBox().setModel(tempSecModel);
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
		
		id++;
		
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
				this.talismanTableModel.addTalismanList(this.talismanDAOImpl.retrieveList());
				currentTalisman = null;
			}
			else
			{
				this.showDeleteTalisman(deleteTalisman);
				this.talismanDAOImpl.deleteThenInsert(currentTalisman);
			}
		}
		else
		{
			//DIALOG saying "There is a Talisman with higher value."
			id--;
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
		deleteTableModel.addTalismanList(deleteTalisman);
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
}
