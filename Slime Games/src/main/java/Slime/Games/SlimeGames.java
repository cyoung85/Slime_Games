package Slime.Games;

public class SlimeGames {
	private static int p1score = 0;
	private static int p2score = 0;
	
	public static void main(String args[])
	{
		MainMenu test = new MainMenu();
	}
	public static void resetScore() {
		setP1Score(0);
		setP2Score(0);
	}
	public static int getP1Score() {
		return p1score;
	}
	public static void setP1Score(int p1score) {
		SlimeGames.p1score = p1score;
	}
	public static int getP2Score() {
		return p2score;
	}
	public static void setP2Score(int p2score) {
		SlimeGames.p2score = p2score;
	}
}
