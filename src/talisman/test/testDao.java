package talisman.test;

import talisman.dao.*;
import talisman.model.Talisman;

public class testDao {

	private static TalismanDAOImpl dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		dao = new TalismanDAOImpl();
		
		testSingle();
	}

	
	public static void testSingle()
	{
		//Pick a test single talisman
		Talisman testTalisman = new Talisman(1, "Anti-Theft", "--", 5, 0, 2, 0);
		
		//test cases to show higher or equals
		Talisman highTalisman1 = new Talisman(2, "Anti-Theft", "--", 5, 0, 2, 0);
		Talisman highTalisman2 = new Talisman(3, "Anti-Theft", "--", 7, 0, 2, 0);
		Talisman highTalisman3 = new Talisman(4, "Anti-Theft", "--", 7, 0, 3, 0);
		Talisman highTalisman4 = new Talisman(5, "Anti-Theft", "--", 5, 0, 3, 0);
	
		Talisman highTalisman5 = new Talisman(6, "Attack", "Anti-Theft", 5, 5, 2, 0);
		Talisman highTalisman6 = new Talisman(7, "Attack", "Anti-Theft", 7, 5, 2, 0);
		Talisman highTalisman7 = new Talisman(8, "Attack", "Anti-Theft", 7, 7, 2, 0);
		Talisman highTalisman8 = new Talisman(9, "Attack", "Anti-Theft", 7, 7, 3, 0);
		Talisman highTalisman9 = new Talisman(10, "Attack", "Anti-Theft", 5, 7, 2, 0);
		Talisman highTalisman10 = new Talisman(11, "Attack", "Anti-Theft", 5, 7, 3, 0);
		Talisman highTalisman11 = new Talisman(12, "Attack", "Anti-Theft", 5, 5, 3, 0);
		
		//add test cases to database
		dao.insertTalisman(highTalisman1);
		dao.insertTalisman(highTalisman2);
		dao.insertTalisman(highTalisman3);
		dao.insertTalisman(highTalisman4);
		dao.insertTalisman(highTalisman5);
		dao.insertTalisman(highTalisman6);
		dao.insertTalisman(highTalisman7);
		dao.insertTalisman(highTalisman8);
		dao.insertTalisman(highTalisman9);
		dao.insertTalisman(highTalisman10);
		dao.insertTalisman(highTalisman11);
		
		
		
		//check tests
		
		//test cases to show lower or equals
		
		//check tests
		
		//delete all entries in database
	}
	
	public static void testDouble()
	{
		//Pick a test single talisman
		
		//test cases to show higher or equals
		
		//check tests
		
		//test cases to show lower or equals
		
		//check tests
		
		//delete all entries in database
	}
	
}
