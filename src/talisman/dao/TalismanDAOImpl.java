package talisman.dao;

/**
 * TalismanDAOImpl.java
 *
 * Name: rolim91
 *
 * Description: Implementation of the TalismanDAO Interface
 * 
 * Features:
 * 				Handles database activity specifically sqlite
 * 				Creates database and tables
 * 				Delete talisman in database
 * 				Insert talisman
 * 					-Checks if insertion is valid
 * 				Search for talisman
 * 				Clear table
 * 				Retrieve all talismans in database
 *
 * 
 * BUGS: NONE
 *
 *
 * Versions: 	1.0 - Implemented above features
 *
 * rolim91 
 */

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
	
	/*
	 * Create the sqlite database
	 */
	private void createDatabase()
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
	
	/*
	 * Create the talisman table of the sqlite database
	 */
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
	/*
	 * (non-Javadoc)
	 * @see talisman.dao.TalismanDAO#deleteTalisman(talisman.model.Talisman)
	 */
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
	/*
	 * (non-Javadoc)
	 * @see talisman.dao.TalismanDAO#checkInsert(talisman.model.Talisman)
	 */
	public List<Talisman> checkInsert(Talisman talisman) {
		
		if(talisman.getType() == 1)
			return checkInsertSingle(talisman);
		else
			return checkInsertDouble(talisman);
	}
	
	/*
	 * Queries database to check if the single talisman is a valid input
	 * @param talisman - using the talisman parameter, check if the talisman should be inserted or not
	 * @return null if talisman cannot be inserted, List<Talisman> that needs to be deleted before given talisman is inserted
	 */
	private List<Talisman> checkInsertSingle(Talisman talisman)
	{
		System.out.println("checkInsertSingle");
	
		Statement stmt = null;
	
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = "SELECT count(*) " +
						 "FROM talisman_list " +
						 "WHERE " +
						 "(Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + " AND Skill2_value >= 0) OR (Skill_2 = '" + talisman.getSkill_1() + "' AND Skill2_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + ")";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int rowCount = rs.getInt(1);
			
			//System.out.println(rowCount);
			
			if(rowCount > 0)
				return null;
			else
				return this.checkDeleteSingle(talisman);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		return null;
	}
	
	/*
	 * Retrieves a list of Talisman that is in conflict with the talisman parameter
	 * @param talisman - talisman reference
	 * @return List<Talisman> a list of conflicting talisman
	 */
	private List<Talisman> checkDeleteSingle(Talisman talisman)
	{
		
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = 	"SELECT * " +
							"FROM talisman_list " +
							"WHERE (Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + " AND Skill2_value <= 0 )";
			
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
	}
	
	/*
	 * Convert the ResultSet parameter into a List of Talisman Objects
	 * @param ResultSet rs - a ResultSet of the returned query from the database
	 * @return List<Talisman> a list of Talisman from the ResultSet
	 */
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
		
		System.out.println("Size is: " + talismanList.size());
		return talismanList;
	}

	/*
	 * Queries database to check if the single talisman is a valid input
	 * @param talisman - using the talisman parameter, check if the talisman should be inserted or not
	 * @return null if talisman cannot be inserted, List<Talisman> that needs to be deleted before given talisman is inserted
	 */
	private List<Talisman> checkInsertDouble(Talisman talisman)
	{
		Statement stmt = null;
		
		System.out.println("checkInsertDouble");
		
		if(talisman.getSkill2_Value() < 0)
		{
			return this.checkInsertSingle(talisman);
		}
		else
		{
			//create and execute query
			try {
				
				stmt = c.createStatement();
				
				String sql = "SELECT count(*) " +
							 "FROM talisman_list " +
							 "WHERE (Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value >= " + talisman.getSkill1_Value() + " AND Skill_2 = '" + talisman.getSkill_2() + "' AND Skill2_Value >= " + talisman.getSkill2_Value() + " AND Slots >= " + talisman.getSlots() + ") OR " + 
							 "(Skill_1 = '" + talisman.getSkill_2() + "' AND Skill1_Value >= " + talisman.getSkill2_Value() +" AND Skill_2 = '" + talisman.getSkill_1() + "' AND Skill2_Value >= " + talisman.getSkill1_Value() + " AND Slots >= " + talisman.getSlots() + ") ";
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				int rowCount = rs.getInt(1);
				
				System.out.println(rowCount);
				
				if(rowCount > 0)
					return null;
				else
					return this.checkDeleteDouble(talisman);
				
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		
		}
		return null;
	}

	/*
	 * Retrieves a list of Talisman that is in conflict with the talisman parameter
	 * @param talisman - talisman reference
	 * @return List<Talisman> a list of conflicting talisman
	 */
	private List<Talisman> checkDeleteDouble(Talisman talisman) {
		
		Statement stmt = null;
		
		if(talisman.getSkill2_Value() < 0)
		{
			return this.checkDeleteSingle(talisman);
		}
		else
		{
			//create and execute query
			try {
				
				stmt = c.createStatement();
				
				String sql = 	"SELECT * " +
								"FROM talisman_list " +
								"WHERE (Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '" + talisman.getSkill_2() + "' AND Skill2_Value <= " + talisman.getSkill2_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
								"(Skill_1 = '" + talisman.getSkill_2() + "' AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '" + talisman.getSkill_1() + "' AND Skill2_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
								"(Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ") OR " +
								"(Skill_1 = '" + talisman.getSkill_2() + "' AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ")";
								
				ResultSet rs = stmt.executeQuery(sql);
				return this.convertToTalismanList(rs);
				
				
			} catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			}
		}
		
		return null;
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see talisman.dao.TalismanDAO#deleteThenInsert(talisman.model.Talisman)
	 */
	public void deleteThenInsert(Talisman talisman) {
		// TODO Auto-generated method stub
		if(talisman.getType() == 1)
			this.deleteThenInsertSingle(talisman);
		else
			this.deleteThenInsertDouble(talisman);
		
		insertTalisman(talisman);
	}
	
	/*
	 * Delete the conflicts in the given Single talisman with the list given by checkInsert
	 * @param talisman - talisman used as a reference of the talisman in the database that is to be deleted
	 */
	private void deleteThenInsertSingle(Talisman talisman)
	{
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list " +
							"WHERE (Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + " AND Skill2_value <= 0 )";
			stmt.executeUpdate(sql);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	/*
	 * Delete the conflicts in the given Double talisman with the list given by checkInsert
	 * @param talisman - talisman used as a reference of the talisman in the database that is to be deleted
	 */
	private void deleteThenInsertDouble(Talisman talisman)
	{
		Statement stmt = null;
		
		//create and execute query
		try {
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list " +
							"WHERE (Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '" + talisman.getSkill_2() + "' AND Skill2_Value <= " + talisman.getSkill2_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = '" + talisman.getSkill_2() + "' AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '" + talisman.getSkill_1() + "' AND Skill2_Value <= " + talisman.getSkill1_Value() + " AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = '" + talisman.getSkill_1() + "' AND Skill1_Value <= " + talisman.getSkill1_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ") OR " +
							"(Skill_1 = '" + talisman.getSkill_2() + "' AND Skill1_Value <= " + talisman.getSkill2_Value() + " AND Skill_2 = '--' AND Slots <= " + talisman.getSlots() + ")";
			
			stmt.executeUpdate(sql);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}
	
	@Override
	public void insertTalisman(Talisman talisman) {
		// TODO Auto-generated method stub
		Statement stmt = null;

		//System.out.println("Inserting talisman: " + talisman);
		
		try {
			
			stmt = c.createStatement();
			String sql = 	"INSERT INTO talisman_list (talismanID, Skill_1, Skill1_Value, Skill_2, Skill2_Value, Slots, Rarity, Type)"  +
							" VALUES (" + 	talisman.getId() + ", '" + 
											talisman.getSkill_1() + "', " + 
											talisman.getSkill1_Value() + ", '" + 
											talisman.getSkill_2() + "', " + 
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
	/*
	 * (non-Javadoc)
	 * @see talisman.dao.TalismanDAO#retrieveList()
	 */
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


	@Override
	/*
	 * Delete the entries in the table
	 */
	public void clearTable() {
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			String sql = 	"DELETE FROM talisman_list";
			stmt.executeUpdate(sql);
			
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	}

	@Override
	public List<Talisman> searchSingle(String singleSkill) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = 	"SELECT * " +
							"FROM talisman_list " +
							"WHERE (Skill_1 = '" + singleSkill + "') OR (Skill_2 = '" + singleSkill + "')";
							
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
		
		
	}

	@Override
	public List<Talisman> searchDouble(String skill_1, String skill_2) {
		
		Statement stmt = null;
		
		//create and execute query
		try {
			
			stmt = c.createStatement();
			
			String sql = 	"SELECT * " +
							"FROM talisman_list " +
							"WHERE (Skill_1 = '" + skill_1 + "' AND Skill_2 = '" + skill_2 + "') OR (Skill_1 = '" + skill_2 + "' AND Skill_2 = '" + skill_1 + "')";
							
			ResultSet rs = stmt.executeQuery(sql);
			return this.convertToTalismanList(rs);
			
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		
		return null;
	}

}
