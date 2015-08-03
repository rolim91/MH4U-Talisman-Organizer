package talisman.test;

import talisman.dao.*;
import talisman.model.Talisman;

public class testDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TalismanDAOImpl dao = new TalismanDAOImpl();
		Talisman talisman = new Talisman();
		
		String sql = 	"SELECT * " +
				"FROM talisman_list " +
				"WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = " + talisman.getSkill_2() + " AND Skill2_Value <= " + talisman.getSkill2_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
				"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = " + talisman.getSkill_1() + " AND Skill2_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
				"(Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ") OR " +
				"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ")";
		System.out.println(sql);
	}

}
