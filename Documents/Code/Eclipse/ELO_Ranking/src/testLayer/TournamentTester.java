package testLayer;

import java.util.ArrayList;

import logicLayer.Season;
import objectLayer.PlayerList;

public class TournamentTester {

	public static void main(String[] args) {
		String[] tournamentRecords = {
				"./TournamentRecords/CCF_8-19-17.txt",
				"./TournamentRecords/CCF_9-9-17.txt", 
				"./TournamentRecords/CCF_9-16-17.txt",
				"./TournamentRecords/CCF_9-30-17.txt",
				"./TournamentRecords/CCF_10-14-17.txt",
				"./TournamentRecords/CCF_10-28-17.txt",
				"./TournamentRecords/CCF_12-2-17.txt"
		};
		ArrayList<String> tAL = new ArrayList<String>();
		for (int x = 0; x < tournamentRecords.length; ++x) {
			tAL.add(tournamentRecords[x]);
		}
		
		Season s = new Season(true, tAL);
		
		s.runSeason();
		
		PlayerList output = s.getPlayerList();
		
		for (int x = 0; x < output.getListSize(); ++x) {
			System.out.println(output.getPlayer(x).getPlayerTag() + " " + output.getPlayer(x).getRankValue());
		}
	}
}
