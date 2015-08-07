package talisman.dao;

import java.util.List;
import talisman.model.Talisman;

public interface TalismanDAO {

	/*
	 * Delete a specific given talisman in the database
	 * @param talisman - talisman that is to be deleted from the database
	 */
	public void deleteTalisman(Talisman talisman);
	
	/*
	 * Checks if talisman can be inserted to not
	 * @param talisman - using the talisman parameter, check if the talisman should be inserted or not
	 * @return null if talisman cannot be inserted, List<Talisman> that needs to be deleted before given talisman is inserted
	 */
	public List<Talisman> checkInsert(Talisman talisman); 
	
	/*
	 * Insert the talisman in the database
	 * @param talisman - talisman that is to be inserted in the database
	 */
	public void insertTalisman(Talisman talisman);
	
	/*
	 * Delete the conflicts in the list given by checkInsert
	 * @param talisman - talisman used as a reference of the talisman in the database that is to be deleted
	 */
	public void deleteThenInsert(Talisman talisman); //delete the conflicts and insert talisman
	
	/*
	 * Return a list of talismans in the database
	 * @return List<Talisman> a list of all of the talisman inside the database
	 */
	public List<Talisman> retrieveList();
	
	/*
	 * Delete all entries from database
	 */
	public void clearTable();
	
	/*
	 * Search Single Skill
	 */
	public List<Talisman> searchSingle(String singleSkill);
	
	/*
	 * Search Double Skills
	 */
	public List<Talisman> searchDouble(String skill_1, String skill_2);
}
