package objectLayer;

//a class to represent any particular player
public class Player {
	private int rankValue;
	private String playerTag;
	
	public Player(String playerTag, int rankValue) {
		this.rankValue = rankValue;
		this.playerTag = playerTag;
	}
	
	//adjust this player's and @param's rankValue according to the elo algorithm. 
	//this is the winner, @param is the loser.
	public void wonAgainst(Player player) {
		int kValue = 32;
		
		//get the rank values for computation
		int winnerRankValue = this.getRankValue();
		int loserRankValue = player.getRankValue();
		
		double winnerExponent = (double)(loserRankValue - winnerRankValue) / 400.0f;
		double expectedWinner = (1 / (1 + Math.pow(10, winnerExponent)));
		
		
		double loserExponent = (double)(winnerRankValue - loserRankValue) / 400.0f;
		double expectedLoser = (1 / (1 + Math.pow(10, loserExponent)));
		
		
		winnerRankValue = (int)Math.ceil((winnerRankValue + kValue * (1 - expectedWinner)));
		loserRankValue = (int)Math.ceil((loserRankValue + kValue * (0 - expectedLoser)));
		
		//set the player's rank values to the calculated amounts
		this.setRankValue(winnerRankValue);
		player.setRankValue(loserRankValue);
	}
	
	
	//================================================================
	//===================Getters and Setters==========================
	//================================================================
	public int getRankValue() {
		return this.rankValue;
	}
	
	public void setRankValue(int rankValue) {
		this.rankValue = rankValue;
	}
	
	public String getPlayerTag() {
		return this.playerTag;
	}
	
	public void setPlayerTag(String playerTag) {
		this.playerTag = playerTag;
	}
}
