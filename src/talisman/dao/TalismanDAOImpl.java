package talisman.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import talisman.model.Talisman;

public class TalismanDAOImpl implements TalismanDAO {

	Connection c;
	
	public TalismanDAOImpl()
	{
		this.createDatabase();
		this.createTable();
	}
	
	public void createDatabase()
	{
		//setup SQLite 
		File f = new File("TalismanDatabase.sqlite");
		if(f.exists() ) 
		{  
			System.out.println("Exists");
			f.delete();
		}
		
		c = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:TalismanDatabase.sqlite");
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Opened database successfully");
	}
	
	public void createTable()
	{
		Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");

	    	stmt = c.createStatement();
	    	String sql = "CREATE TABLE talisman_list " +
	                   	"(talismanID INT PRIMARY KEY 	NOT NULL, " +
	                   	" Skill_1 				TEXT 	NOT NULL, " + 
	                   	" Skill1_Value 			INT 	NOT NULL, " + 
	                   	" Skill_2 				TEXT 	NOT NULL, " + 
	                   	" Skill2_Value 			INT 	NOT NULL, " +
	                   	" Slots 				INT 	NOT NULL, " + 
	                   	" Rarity 				INT, " + 
	                   	" Type 					INT 	NOT NULL) ";
	    	stmt.executeUpdate(sql);
	    	stmt.close();
	    } catch ( Exception e ) {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	System.exit(0);
	    }
	    
	    System.out.println("Table created successfully");
	}
	
	@Override
	public void deleteTalisman(Talisman talisman) {
		// TODO Auto-generated method stub
		int id = talisman.getId();
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list " + 
						 	"WHERE talismanID == " + id;
		    stmt.executeUpdate(sql);
			stmt.close();
		    
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
	}

	@Override
	public List<Talisman> checkInsert(Talisman talisman) {
		
		if(talisman.getType() == 1)
			return checkInsertSingle(talisman);
		else
			return checkInsertDouble(talisman);
	}
	
	private List<Talisman> checkInsertSingle(Talisman talisman)
	{
	
		Statement stmt = null;
	
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = "SELECT count(*) " +
						 "FROM talisman_list " +
						 "WHERE " +
						 "(Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + ") OR (Skill_2 = " + talisman.getSkill_1() + " AND Skill2_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + ")";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int rowCount = rs.getInt(1);
			
			if(rowCount == 0)
				return null;
			else
				return this.checkDeleteSingle(talisman);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		return null;
	}
	
	private List<Talisman> checkDeleteSingle(Talisman talisman)
	{
		
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = 	"SELECT * " +
							"FROM talisman_list " +
							"WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + " AND Skill_2 = '--' )";
			
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
	}
	
	private List<Talisman> convertToTalismanList(ResultSet rs)
	{
		List<Talisman> talismanList = new ArrayList<Talisman>();
		
		try
		{
			while(rs.next())
			{
				int talismanID = rs.getInt("talismanID");
				String skill_1 = rs.getString("Skill_1");
				String skill_2 = rs.getString("Skill_2");;
				int skill1_Value = rs.getInt("Skill1_Value");
				int skill2_Value = rs.getInt("Skill2_Value");
				int slots = rs.getInt("Slots");
				int rarity = rs.getInt("Rarity");
				
				Talisman tempTalisman = new Talisman(talismanID, skill_1, skill_2, skill1_Value, skill2_Value, slots, rarity);
				talismanList.add(tempTalisman);
			}
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);
		}
		
		return talismanList;
	}

	
	private List<Talisman> checkInsertDouble(Talisman talisman)
	{
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = "SELECT count(*) " +
						 "FROM talisman_list " +
						 "WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value >= " + talisman.getSkill1_Value() + " AND Skill_2 = " + talisman.getSkill_2() + " AND Skill2_Value >= " + talisman.getSkill2_Value() + " AND Slots >= " + talisman.getSlots() + ") OR " + 
						 "(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value >= " + talisman.getSkill2_Value() +" AND Skill_2 = " + talisman.getSkill_1() + " AND Skill2_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + ") ";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int rowCount = rs.getInt(1);
			
			if(rowCount == 0)
				return null;
			else
				return this.checkDeleteDouble(talisman);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		return null;
	}

	private List<Talisman> checkDeleteDouble(Talisman talisman) {
		
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = 	"SELECT * " +
							"FROM talisman_list " +
							"WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = " + talisman.getSkill_2() + " AND Skill2_Value <= " + talisman.getSkill2_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = " + talisman.getSkill_1() + " AND Skill2_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ")";
							
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
	}
	
	@Override
	public void deleteThenInsert(Talisman talisman, int index) {
		// TODO Auto-generated method stub
		if(talisman.getType() == 1)
			this.deleteThenInsertSingle(talisman);
		else
			this.deleteThenInsertDouble(talisman);
		
		insertTalisman(talisman, index);
	}
	
	private void deleteThenInsertSingle(Talisman talisman)
	{
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list " +
							"WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + " AND Skill_2 = '--' )";
			stmt.executeUpdate(sql);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	private void deleteThenInsertDouble(Talisman talisman)
	{
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list " +
							"WHERE (Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = " + talisman.getSkill_2() + " AND Skill2_Value <= " + talisman.getSkill2_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = " + talisman.getSkill_1() + " AND Skill2_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_1() + " AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = " + talisman.getSkill_2() + " AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ")";
			stmt.executeUpdate(sql);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	@Override
	public void insertTalisman(Talisman talisman, int index) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		
		
		try {
			
			stmt = c.createStatement();
			String sql = 	"INSERT INTO talisman_list (talismanID, Skill_1, Skill1_Value, Skill_2, Skill2_Value, Slots, Rarity, Type)"  +
							"VALUES (" + 	index + ", " + 
											talisman.getSkill_1() + ", " + 
											talisman.getSkill1_Value() + ", " + 
											talisman.getSkill_2() + ", " + 
											talisman.getSkill2_Value() + ", " + 
											talisman.getSlots() + ", " + 
											talisman.getRarity() + ", " + 
											talisman.getType() + ")";
			
			stmt.executeUpdate(sql);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}

	}

	@Override
	public List<Talisman> retrieveList() {
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"SELECT * " +
							"FROM talisman_list" ;
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
	}



}
