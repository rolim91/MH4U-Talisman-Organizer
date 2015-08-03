package talisman.dao;

import java.util.List;
import talisman.model.Talisman;

public interface TalismanDAO {

	public void deleteTalisman(Talisman talisman);
	public List<Talisman> checkInsert(Talisman talisman); //checks which talisman to delete
	public void insertTalisman(Talisman talisman, int index); //insert talisman
	public void deleteThenInsert(Talisman talisman, int index); ////delete the conflicts and insert talisman
	public List<Talisman> retrieveList();
}
