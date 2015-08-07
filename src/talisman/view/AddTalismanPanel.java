package talisman.view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("rawtypes")
public class AddTalismanPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JSpinner primarySpinner; 
	private JSpinner secondarySpinner;
	
	private JComboBox primarySkillBox; 
	private JComboBox secondarySkillBox; 
	private JSpinner slotSpinner;
	private JSpinner raritySpinner;
	
	JButton addTalismanButton;
	
	public AddTalismanPanel() {
		setBorder(new TitledBorder(null, "Add Talisman", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel primaryLabel = new JLabel("Primary Skill");
		
		primarySpinner = new JSpinner();
		
		JLabel secondaryLabel = new JLabel("Secondary Skill");
		
		secondarySpinner = new JSpinner();
		
		primarySkillBox = new JComboBox();
		primarySkillBox.setToolTipText("Primary Skill");
		
		secondarySkillBox = new JComboBox();
		secondarySkillBox.setToolTipText("Secondary Skill");
		
		slotSpinner = new JSpinner();
		
		JLabel slotLabel = new JLabel("Slots");
		
		JLabel rarityLabel = new JLabel("Rarity");
		
		raritySpinner = new JSpinner();
		raritySpinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		
		addTalismanButton = new JButton("Add Talisman");
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(raritySpinner, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(addTalismanButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(primarySkillBox, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
								.addComponent(primaryLabel)
								.addComponent(secondaryLabel))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(slotSpinner)
								.addComponent(rarityLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(slotLabel))))
					.addGap(41))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(primaryLabel)
						.addComponent(slotLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(primarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(slotSpinner, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(primarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondaryLabel)
						.addComponent(rarityLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(secondarySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(secondarySkillBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(raritySpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(addTalismanButton))
					.addContainerGap(149, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}

	public JSpinner getPrimarySpinner() {
		return primarySpinner;
	}

	public JSpinner getSecondarySpinner() {
		return secondarySpinner;
	}


	public JComboBox getPrimarySkillBox() {
		return primarySkillBox;
	}


	public JComboBox getSecondarySkillBox() {
		return secondarySkillBox;
	}


	public JSpinner getSlotSpinner() {
		return slotSpinner;
	}


	public JSpinner getRaritySpinner() {
		return raritySpinner;
	}

	public JButton getAddTalismanButton() {
		return addTalismanButton;
	}
	
	
}
