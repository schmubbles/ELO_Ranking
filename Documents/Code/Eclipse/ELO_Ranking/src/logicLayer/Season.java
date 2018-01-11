package logicLayer;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;

import objectLayer.Player;
import objectLayer.PlayerList;



public class Season {
	
	PlayerList playerList;//the list of players present during this tournament
	boolean isRankingCumulative;//do the values from the previous tournament affect this one?
	List<String> tournamentFileLocations;//an array of strings holding the tournamentLocations
	
	//a constructor for if we have all three
	public Season (PlayerList playerList, boolean isRankingCumulative, ArrayList<String> tournamentFileLocations) {
		this.playerList = playerList;
		this.isRankingCumulative = isRankingCumulative;
		this.tournamentFileLocations = tournamentFileLocations;
	}
	//a constructor for if we already have tournamentFileLocations but no playerList
	public Season(boolean isRankingCumulative, ArrayList<String> tournamentFileLocations) {
		this.playerList = new PlayerList();
		this.isRankingCumulative = isRankingCumulative;
		this.tournamentFileLocations = tournamentFileLocations;
	}
	//a constructor for if the playerList is pre-defined, i.e. players are given pre-season rank values
	public Season(PlayerList playerList, boolean isRankingCumulative) {
		this.playerList = playerList;
		this.isRankingCumulative = isRankingCumulative;		
		this.tournamentFileLocations = new ArrayList<String>();
	}
	//a constructor for if the playerList is not pre-defined, i.e. all players start with the same rank
	public Season(boolean isRankingCumulative) {
		this.playerList = new PlayerList();
		this.isRankingCumulative = isRankingCumulative;
		this.tournamentFileLocations = new ArrayList<String>();
	}
	
	public void runSeason() {
		ListIterator<String> litr = tournamentFileLocations.listIterator();
		while (litr.hasNext()) {
			String tournamentLocation = litr.next();
			Tournament tourny;
			if (isRankingCumulative) {
				tourny = new Tournament(tournamentLocation, this.playerList, "name");
			} else {
				tourny = new Tournament(tournamentLocation, "name");
			}
			tourny.runTournament();
			
			this.recordTournament(tourny);
			
		
		}
	}
	
	//========================================================================
	//=====================getters and setters================================
	//========================================================================
	public void addTournamentFileLocation(String fileLocation) {
		this.tournamentFileLocations.add(fileLocation);
	}
	
	public boolean removeTournamentFileLocation(String fileLocation) {
		boolean wasRemoved = false;
		
		if (this.tournamentFileLocations.remove(fileLocation)) {
			wasRemoved = true;
		}
		
		return wasRemoved;
	}
	
	//returns the playerList
	public PlayerList getPlayerList() {
		return this.playerList;
	}
	
	//========================================================================
	//=========================private methods================================
	//========================================================================
	//a method which takes care of adding new players to the season and updating scores
	//if season is cumulative, scores are replaces. if not cumulative, scores are added
	private void recordTournament(Tournament tourny) {
		PlayerList tournyList = tourny.getPlayerList();
		if (this.isRankingCumulative) {
			addCumulative(tournyList);
		} else {
			addNonCumulative(tournyList);			
		}
	}
	
	//adds players from parameter to this.playerList in a cumulative point manner, ie scores are replaced
	private void addCumulative(PlayerList addPlayerList) {
		for (int x = 0; x < playerList.getListSize(); ++x) {
			if (this.playerList.isPlayerUnique(addPlayerList.getPlayer(x).getPlayerTag())) {
				//they're new, add them as they are
				this.playerList.addPlayer(addPlayerList.getPlayer(x).getPlayerTag(), addPlayerList.getPlayer(x).getRankValue());
			} else {
				//they're in our list, replace this player's score
				this.playerList.getPlayer(addPlayerList.getPlayer(x).getPlayerTag()).setRankValue(addPlayerList.getPlayer(x).getRankValue());
			}
		}
	}
	
	//adds players from parameter to this.playerList in a noncumulative manner, ie scores are added
	private void addNonCumulative(PlayerList addPlayerList) {
		for (int x = 0; x < playerList.getListSize(); ++x) {
			if (this.playerList.isPlayerUnique(addPlayerList.getPlayer(x).getPlayerTag())) {
				//they're new, add them as they are
				this.playerList.addPlayer(addPlayerList.getPlayer(x).getPlayerTag(), addPlayerList.getPlayer(x).getRankValue());
			} else {
				//they're in our list, add this player's score
				int tempRankValue = addPlayerList.getPlayer(x).getRankValue() + this.playerList.getPlayer(addPlayerList.getPlayer(x).getPlayerTag()).getRankValue();
				this.playerList.getPlayer(addPlayerList.getPlayer(x).getPlayerTag()).setRankValue(tempRankValue);
			}
		}
	}
}
