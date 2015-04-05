
public class Talisman {
	
	private int numOfSkills;
	private String skill_1;
	private String skill_2;
	private int skill1_Value;
	private int skill2_Value;
	private int slots;
	private int type;	//1 for  single, 2 for double
	private int rarity;
	
	public Talisman() {
		super();
		this.numOfSkills = 2;
		this.skill_1 = "skill_1";
		this.skill_2 = "skill_2";
		this.skill1_Value = 0;
		this.skill2_Value = 0;
		this.slots = 0;
		this.type = 0;
	}

	public Talisman(int numOfSkills, String skill_1, String skill_2, int skill1_Value, int skill2_Value, int slots) {
		super();
		
		this.numOfSkills = numOfSkills;
		this.skill_1 = skill_1;
		this.skill_2 = skill_2;
		this.skill1_Value = skill1_Value;
		this.skill2_Value = skill2_Value;
		this.slots = slots;
		this.type = 1;
		
		if(skill_2 == null)
			this.type = 2;
		
	}

	public int getNumOfSkills() {
		return numOfSkills;
	}

	public String getSkill_1() {
		return skill_1;
	}

	public String getSkill_2() {
		return skill_2;
	}

	public int getSkill1_Value() {
		return skill1_Value;
	}

	public int getSkill2_Value() {
		return skill2_Value;
	}

	public int getSlots() {
		return slots;
	}

	public int getType() {
		return type;
	}

	public int getRarity() {
		return rarity;
	}

	@Override
	public String toString() {
		return "Talisman [numOfSkills=" + numOfSkills + ", skill_1=" + skill_1
				+ ", skill_2=" + skill_2 + ", skill1_Value=" + skill1_Value
				+ ", skill2_Value=" + skill2_Value + ", slots=" + slots
				+ ", type=" + type + "]";
	}
	
	
}
