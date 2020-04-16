package Slime.Games;

public class WindowBounds {
	private int minX;
	private int minY;
	private int maxX;
	private int maxY;
	
	
	public WindowBounds(int width, int height) {
		setMinX(0);
		setMinY(0);
		setMaxX(width - 1);
		setMaxY(height - 1);
	}
	public void set(int x, int y, int width, int height) {
		setMinX(x);
		setMinY(y);
	    setMaxX(x + width - 1);
	    setMaxY(y + height - 1);
	 }
	public int getMinX() {
		return minX;
	}
	public void setMinX(int minX) {
		this.minX = minX;
	}
	public int getMinY() {
		return minY;
	}
	public void setMinY(int minY) {
		this.minY = minY;
	}
	public int getMaxX() {
		return maxX;
	}
	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}
	public int getMaxY() {
		return maxY;
	}
	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}
	
}
