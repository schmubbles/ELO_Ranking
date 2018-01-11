package objectLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

//a class which can create a List of Players. This allows for different inputs.
public class PlayerList {
	List<Player> playerList;
	
	public PlayerList() {
		playerList = new ArrayList<Player>();
	}
	
	//adds a player to the list with name playerTag playerTag and rankValue rankValue.
	//tests if player is already in the list. returns false if player already exists
	public boolean addPlayer(String playerTag, int rankValue) {
		boolean ret = true;
		
		if (isPlayerUnique(playerTag)) {
			Player newPlayer = new Player(playerTag, rankValue);
			playerList.add(newPlayer);
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	//another addPlayer method, but this one doesn't take in a rankValue parameter and defaults the value to 0
	public boolean addPlayer(String playerTag) {
		return this.addPlayer(playerTag, 0);
	}
	
	//tests if the player playerTag is unique to the playerList (i.e. doesn't exist)
	public boolean isPlayerUnique(String playerTag) {
		boolean isUnique = true;
		
		ListIterator<Player> litr = playerList.listIterator();
		while (litr.hasNext()) {
			Player temp = litr.next();
			if (temp.getPlayerTag().compareToIgnoreCase(playerTag) == 0) {
				isUnique = false;
			}
		}
		
		return isUnique;
	}

	//==================================================================
	//====================getters and setters===========================
	//==================================================================
	public int getListSize() {
		return this.playerList.size();
	}
	
	//used to create listIterators in Tournament class
	public List<Player> getPlayerList(){
		return this.playerList;
	}
	
	//gets player at index
	public Player getPlayer(int index) {
		return this.playerList.get(index);
	}
		
	//gets player with name
	public Player getPlayer(String playerTag) {
		int index = -1;
		
		ListIterator<Player> litr = playerList.listIterator();
		while (litr.hasNext()) {
			++index;
			Player temp = litr.next();
			if (temp.getPlayerTag().compareToIgnoreCase(playerTag) == 0) {
				break;
			}
		}
		
		return this.getPlayer(index);
	}
	
	//arbitrarily change a Player's rank. return true if successful, false if not
	public boolean setPlayerRankValue(String playerTag, int rankValue) {
		boolean ret = true;
		
		if (!isPlayerUnique(playerTag)) {//if the player does exist
			playerList.get(getPlayerIndex(playerTag)).setRankValue(rankValue);		
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	
	//returns true if match is recorded, false if it is not
	public boolean recordMatch(String winnerTag, String loserTag) {
		boolean ret = true;
		
		if (!isPlayerUnique(winnerTag) && !isPlayerUnique(loserTag)) {
			playerList.get(getPlayerIndex(winnerTag)).wonAgainst(playerList.get(getPlayerIndex(loserTag)));
		} else {
			ret = false;
		}
		
		return ret;
	}
	
	
	
	
	//==================================================================
	//======================private methods=============================
	//==================================================================
	
	//gets the index where the playerTag is located
	private int getPlayerIndex(String playerTag) {
		int index = -1;
		if (isPlayerUnique(playerTag)) {
			return index;
		}
		int count = 0;
		ListIterator<Player> litr = playerList.listIterator();
		while (litr.hasNext()) {
			Player temp = litr.next();
			if (temp.getPlayerTag().compareToIgnoreCase(playerTag) == 0) {
				index = count;
				break;
			}
			++count;
		}
		
		return index;
	}
	
	
}
