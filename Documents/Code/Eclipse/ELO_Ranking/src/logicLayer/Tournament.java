package logicLayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import objectLayer.PlayerList;

public class Tournament {
	PlayerList playerList;//all players
	PlayerList newPlayerList;//players new to this tournament
	String tournamentLocation;//in the file system
	String tournamentName;//given name to the tournament
	
	//default constructor. TournamentLocation is the location of the tournament in the file system
	public Tournament(String tournamentLocation, String tournamentName) {
		this.playerList = new PlayerList(); 
		this.newPlayerList = new PlayerList();
		this.tournamentLocation = tournamentLocation;
		this.tournamentName = tournamentName;
	}
	
	//a constructor for if this tournament is to be considered in a string of other tournaments
	//i.e. the values for players are preserved from previous tournaments
	public Tournament(String tournamentLocation, PlayerList playerList, String tournamentName) {
		this.playerList = playerList;
		this.newPlayerList = new PlayerList();
		this.tournamentLocation = tournamentLocation;
		this.tournamentName = tournamentName;
	}
	
	//runs the tournament
	public void runTournament() {
		addNewPlayersFromFile();
		recordMatches();
	}
	
	//=============================================================
	//====================getters and setters======================
	//=============================================================

	//returns the playerList in this object, presumably to preserve values after tournament
	public PlayerList getPlayerList() {
		return this.playerList;
	}
	
	public PlayerList getNewPlayerList() {
		return this.newPlayerList;
	}
	
	public String getTournamentName() {
		return this.tournamentName;
	}
	
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	
	//=============================================================
	//======================private methods========================
	//=============================================================
	
	//records the matches specified in the tournamentLocation document in the format of <winner loser>
	private void recordMatches() {
		String line = null;
		try {
			FileReader fr = new FileReader(this.tournamentLocation);
			BufferedReader br = new BufferedReader(fr);
			
			while ((line = br.readLine()) != null){
				String[] temp = line.trim().split(" ");
				String playerWon = temp[0];
				String playerLost = temp[1];
				
				playerList.recordMatch(playerWon, playerLost);
				
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (IOException e) {
			System.out.println("ErrorReading");			
		}
	}
	
	//iterate through the file and add players with unique playerTags with the default value
	//stores them in both playerList and newPlayerList
	private void addNewPlayersFromFile() {
		String line = null;
		try {
			FileReader fr = new FileReader(this.tournamentLocation);
			BufferedReader br = new BufferedReader(fr);
		
			while ((line = br.readLine()) != null) {
				String[] temp = line.trim().split(" ");
			
				addPlayers(temp[0]);
				addPlayers(temp[1]);
			}
			
			br.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		} catch (IOException e) {
			System.out.println("ErrorReading");			
		}
	}
	
	//tries to add Player to the playerList. If the potentialPlayer is unique to the list,
	//add it to both playerList and newPlayerList
	private void addPlayers(String potentialPlayer) {
		if (playerList.isPlayerUnique(potentialPlayer)) {
			playerList.addPlayer(potentialPlayer);
			newPlayerList.addPlayer(potentialPlayer);
		}
	}
	
}
