public class Cell {
	private int row, col;
	private boolean isAlive;
	
	public Cell(int row, int col, boolean isAlive) {
		this.row = row;
		this.col = col;
		this.isAlive = isAlive;
	}
	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public boolean getAlive() {
		return isAlive;
	}
	public String toString() {
		if(isAlive) {
			return "*";
		}else {
			return ".";
		}
	}
	
}
