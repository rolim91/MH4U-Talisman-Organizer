package talisman.test;

import java.util.ArrayList;
import java.util.List;

import talisman.dao.*;
import talisman.model.Talisman;

public class testDao {

	private static TalismanDAOImpl dao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		dao = new TalismanDAOImpl();
		
		//testSingle();
		//testDouble();
		testSearch();
	}

	
	public static void testSingle()
	{
		//Pick a test single talisman
		Talisman testTalisman = new Talisman(1, "Anti-Theft", "--", 5, 0, 2, 0);
		
		//test cases to show higher or equals
		
		//***showing bigger values
		
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
		
		//check tests -- should output nothing (will fail)
		insertTest(testTalisman); 
		
		//test cases to show lower or equals
		dao.clearTable();
		highList.clear();
		
		highList.add(new Talisman(13, "Anti-Theft", "--", 4, 0, 2, 0));
		highList.add(new Talisman(14, "Anti-Theft", "--", 4, 0, 1, 0));
		highList.add(new Talisman(15, "Anti-Theft", "--", 5, 0, 1, 0));
		highList.add(new Talisman(16, "Attack", "Anti-Theft", 5, 4, 2, 0)); // will not show up
		highList.add(new Talisman(17, "Attack", "Anti-Theft", 5, 5, 1, 0)); // will not show up
		highList.add(new Talisman(18, "Attack", "Anti-Theft", 5, 4, 1, 0)); // will not show up
		highList.add(new Talisman(19, "Anti-Theft", "Attack", 4, 5, 2, 0)); // will not show up
		highList.add(new Talisman(20, "Anti-Theft", "Attack", 5, 5, 1, 0)); // will not show up
		highList.add(new Talisman(21, "Anti-Theft", "Attack", 4, 5, 1, 0)); // will not show up
		
		addTalismansToDatabase(highList);
		
		//check tests -- should output nothing (will fail)
		insertTest(testTalisman); 
		
		//delete all entries in database
		dao.deleteThenInsert(testTalisman);
		
		dao.clearTable();
		highList.clear();
	}
	
	public static void testDouble()
	{
		//Pick a test double talisman
		Talisman testTalisman = new Talisman(1, "Anti-Theft", "Attack", 5, 10, 2, 0);
		
		//test cases to show higher or equals
		ArrayList<Talisman> highList = new ArrayList<Talisman>();
		highList.add(new Talisman(2, "Anti-Theft", "Attack", 5, 10, 2, 0));
		highList.add(new Talisman(3, "Anti-Theft", "Attack", 7, 10, 2, 0));
		highList.add(new Talisman(4, "Anti-Theft", "Attack", 7, 11, 2, 0));
		highList.add(new Talisman(5, "Anti-Theft", "Attack", 7, 11, 3, 0));
		highList.add(new Talisman(6, "Anti-Theft", "Attack", 5, 11, 2, 0));
		highList.add(new Talisman(7, "Anti-Theft", "Attack", 5, 11, 3, 0));
		highList.add(new Talisman(8, "Anti-Theft", "--", 7, 0, 3, 0)); //will not show
		highList.add(new Talisman(9, "Attack", "--", 11, 0, 2, 0)); // will not show
		highList.add(new Talisman(10, "Attack", "Anti-Theft", 10, 5, 2, 0));
		highList.add(new Talisman(11, "Attack", "Anti-Theft", 10, 7, 2, 0));
		highList.add(new Talisman(12, "Attack", "Anti-Theft", 11, 7, 2, 0));
		highList.add(new Talisman(13, "Attack", "Anti-Theft", 11, 7, 3, 0));
		highList.add(new Talisman(14, "Attack", "Anti-Theft", 11, 5, 2, 0));
		highList.add(new Talisman(15, "Attack", "Anti-Theft", 11, 5, 3, 0));
		
		addTalismansToDatabase(highList);
		
		//check tests
		insertTest(testTalisman);
		
		//test cases to show lower or equals
		dao.clearTable();
		highList.clear();
		
		// 10, 5, 2
		
		highList.add(new Talisman(16, "Anti-Theft", "Attack", 4, 10, 2, 0));
		highList.add(new Talisman(17, "Anti-Theft", "Attack", 4, 9, 2, 0));
		highList.add(new Talisman(18, "Anti-Theft", "Attack", 4, 9, 1, 0));
		highList.add(new Talisman(19, "Anti-Theft", "Attack", 5, 9, 2, 0));
		highList.add(new Talisman(20, "Anti-Theft", "Attack", 5, 9, 1, 0));
		highList.add(new Talisman(21, "Anti-Theft", "Attack", 5, 10, 1, 0));
		highList.add(new Talisman(22, "Attack", "Anti-Theft", 9, 5, 2, 0));
		highList.add(new Talisman(23, "Attack", "Anti-Theft", 9, 4, 2, 0));
		highList.add(new Talisman(24, "Attack", "Anti-Theft", 9, 4, 1, 0));
		highList.add(new Talisman(25, "Attack", "Anti-Theft", 10, 4, 2, 0));
		highList.add(new Talisman(26, "Attack", "Anti-Theft", 10, 4, 1, 0));
		highList.add(new Talisman(27, "Attack", "Anti-Theft", 10, 5, 1, 0));
		highList.add(new Talisman(28, "Anti-Theft", "--", 5, 0, 2, 0));
		highList.add(new Talisman(29, "Anti-Theft", "--", 4, 0, 2, 0));
		highList.add(new Talisman(30, "Anti-Theft", "--", 4, 0, 1, 0));
		highList.add(new Talisman(31, "Anti-Theft", "--", 5, 0, 1, 0));
		highList.add(new Talisman(32, "Attack", "--", 10, 0, 2, 0));
		highList.add(new Talisman(33, "Attack", "--", 9, 0, 2, 0));
		highList.add(new Talisman(34, "Attack", "--", 9, 0, 1, 0));
		highList.add(new Talisman(35, "Attack", "--", 10, 0, 1, 0));
		
		addTalismansToDatabase(highList);
		
		//check tests
		insertTest(testTalisman); 
		

		dao.deleteThenInsert(testTalisman);
		
		//delete all entries in database
		

		dao.clearTable();
		highList.clear();
	}
	
	public static void insertTest(Talisman testTalisman)
	{
		try {
			checkTest(dao.checkInsert(testTalisman));
		} catch (Exception e) {
			System.out.println("****It is empty");
		}
	}
	
	public static void addTalismansToDatabase(List<Talisman> taliList)
	{
		for(int i = 0; i < taliList.size(); i++)
			dao.insertTalisman(taliList.get(i));
	}
	
	public static void checkTest(List<Talisman> taliList) {
		System.out.println("Number of Entries: " + taliList.size());
		
		for(int i = 0; i < taliList.size(); i++)
			System.out.println(taliList.get(i));
	}
	
	public static void testSearch()
	{
		Talisman testTalisman = new Talisman(1, "Anti-Theft", "Attack", 5, 10, 2, 0);
		
		//test cases to show higher or equals
		ArrayList<Talisman> highList = new ArrayList<Talisman>();
		
		// 10, 5, 2
		
		highList.add(new Talisman(16, "Support", "Attack", 4, 10, 2, 0));
		highList.add(new Talisman(17, "Support", "Attack", 4, 9, 2, 0));
		highList.add(new Talisman(18, "Support", "Attack", 4, 9, 1, 0));
		highList.add(new Talisman(19, "Support", "Attack", 5, 9, 2, 0));
		highList.add(new Talisman(20, "Support", "Attack", 5, 9, 1, 0));
		highList.add(new Talisman(21, "Support", "Attack", 5, 10, 1, 0));
		highList.add(new Talisman(22, "Attack", "Anti-Theft", 9, 5, 2, 0));
		highList.add(new Talisman(23, "Attack", "Anti-Theft", 9, 4, 2, 0));
		highList.add(new Talisman(24, "Attack", "Anti-Theft", 9, 4, 1, 0));
		highList.add(new Talisman(25, "Attack", "Anti-Theft", 10, 4, 2, 0));
		highList.add(new Talisman(26, "Attack", "Anti-Theft", 10, 4, 1, 0));
		highList.add(new Talisman(27, "Attack", "Anti-Theft", 10, 5, 1, 0));
		highList.add(new Talisman(28, "Anti-Theft", "--", 5, 0, 2, 0));
		highList.add(new Talisman(29, "Anti-Theft", "--", 4, 0, 2, 0));
		highList.add(new Talisman(30, "Anti-Theft", "--", 4, 0, 1, 0));
		highList.add(new Talisman(31, "Anti-Theft", "--", 5, 0, 1, 0));
		highList.add(new Talisman(32, "Attack", "--", 10, 0, 2, 0));
		highList.add(new Talisman(33, "Attack", "--", 9, 0, 2, 0));
		highList.add(new Talisman(34, "Attack", "--", 9, 0, 1, 0));
		highList.add(new Talisman(35, "Attack", "--", 10, 0, 1, 0));
		
		addTalismansToDatabase(highList);
		
		//checkTest(dao.searchSingle("Anti-Theft"));
		checkTest(dao.searchDouble("Support", "Attack"));
	}
}
