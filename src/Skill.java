
public class Skill {
	
	private String stringName;
	private int min;
	private int max;
	
	public Skill()
	{
		stringName = "skill_name";
		min = 0;
		max = 10;
	}
	
	public Skill(String stringName, int min, int max)
	{
		this.stringName = stringName;
		this.min = min;
		this.max = max;
	}

	public String getStringName() {
		return stringName;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	@Override
	public String toString() {
		return "Skill [stringName=" + stringName + ", min=" + min + ", max="
				+ max + "]";
	}

	
}
