public class GameBoard {
	private Cell[][] gameBoard;
	
	public GameBoard(int width, int height, int prob) {
		Cell[][] temp = new Cell[height][width];
		int randInt = 0;
		
		for(int row=0; row<height; row++) {
			for(int col=0; col<width; col++) {
				randInt = (int)(Math.random()*prob);
				if(randInt<prob-1) {
					temp[row][col] = new Cell(row, col, false);
				}else if(randInt==prob-1) {
					temp[row][col] = new Cell(row, col, true);
				}
				
			}
		}
		
		gameBoard = temp;
	}
	
	
	public void updateBoard() {
		int specialNum = 0;
		int cellCol = 0;
		int cellRow = 0;
		Cell cur;
		Cell[][] temp = gameBoard.clone();
		
		for(int row=0; row<temp.length; row++) {
			for(int col=0; col<temp[0].length; col++) {
				
				cellCol = temp[row][col].getCol();
				cellRow = temp[row][col].getRow();
				cur = gameBoard[row][col];
				
				//edge cases for top and bottom rows
				if(row==0) {
					if(cellCol!=0) {
						//diagonal check if not top left corner
						if(temp[cellRow+1][cellCol-1].getAlive()) {
							specialNum++;
						}
						if(temp[cellRow][cellCol-1].getAlive()) {
							specialNum++;
						}
					}
					
					if(temp[cellRow+1][cellCol].getAlive()) {
						specialNum++;
					}
					
					if(cellCol!=temp[row].length-1) {
						//diagonal check if not top right corner
						if(temp[cellRow+1][cellCol+1].getAlive()) {
							specialNum++;
						}
						if(temp[cellRow][cellCol+1].getAlive()) {
							specialNum++;
						}
					}
					
					updateCell(cur, specialNum);
					specialNum = 0;
				
				
				}else if(row==temp.length-1){
					if(cellCol!=0) {
						//diagonal check if not bottom left corner
						if(temp[cellRow-1][cellCol-1].getAlive()) {
							specialNum++;
						}
						if(temp[cellRow][cellCol-1].getAlive()) {
							specialNum++;
						}
					}
					
					if(temp[cellRow-1][cellCol].getAlive()) {
						specialNum++;
					}
					
					if(cellCol!=temp[row].length-1) {
						//diagonal check if not bottom right corner
						if(temp[cellRow-1][cellCol+1].getAlive()) {
							specialNum++;
						}
						if(temp[cellRow][cellCol+1].getAlive()) {
							specialNum++;
						}
					}
					updateCell(cur, specialNum);
					specialNum = 0;
				
				}else if(cellCol==0) { //edge cases for right and left columns, corners already checked so skip over them

					if(temp[cellRow][cellCol+1].getAlive()) {
						specialNum++;
					}
					if(temp[cellRow-1][cellCol+1].getAlive()) {
						specialNum++;
					}
					if(temp[cellRow+1][cellCol+1].getAlive()) {
						specialNum++;
					}
					updateCell(cur, specialNum);
					specialNum = 0;
					
				}else if(cellCol==temp[0].length-1) {
					if(temp[cellRow][cellCol-1].getAlive()) {
						specialNum++;
					}
					if(temp[cellRow-1][cellCol-1].getAlive()) {
						specialNum++;
					}
					if(temp[cellRow+1][cellCol-1].getAlive()) {
						specialNum++;
					}
					updateCell(cur, specialNum);
					specialNum = 0;
				}else {
					updateCell(cur);
				}
			}
		}
		gameBoard = temp;
	}
	
	private void updateCell(Cell other) {
		int num = surroundingCells(other);
		
		if(other.getAlive()) {
			if(num<2) {
				other.setAlive(false);
			}else if(num>3) {
				other.setAlive(false);
			}
		}else if(num==3){
			other.setAlive(true);
		}
	}
	
	private void updateCell(Cell other, int num) {
		if(other.getAlive()) {
			if(num<2) {
				other.setAlive(false);
			}else if(num>3) {
				other.setAlive(false);
			}
		}else if(num==3){
			other.setAlive(true);
		}
	}
	
	private int surroundingCells(Cell Cur) {
		return checkDiagonals(Cur) + checkHorizontal(Cur) + checkVertical(Cur);
	}
	
	private int checkDiagonals(Cell Cur) {
		int count = 0;
		
		if(gameBoard[Cur.getRow()-1][Cur.getCol()-1].getAlive()) {
			count++;
		}
		if(gameBoard[Cur.getRow()-1][Cur.getCol()+1].getAlive()) {
			count++;
		}
		if(gameBoard[Cur.getRow()+1][Cur.getCol()-1].getAlive()) {
			count++;
		}
		if(gameBoard[Cur.getRow()+1][Cur.getCol()+1].getAlive()) {
			count++;
		}
		
		return count;
	}
	
	private int checkHorizontal(Cell Cur) {
		int count = 0;
		
		if(gameBoard[Cur.getRow()][Cur.getCol()-1].getAlive()) {
			count++;
		}
		if(gameBoard[Cur.getRow()][Cur.getCol()+1].getAlive()) {
			count++;
		}
		
		return count;
	}

	private int checkVertical(Cell Cur) {
		int count = 0;
		
		if(gameBoard[Cur.getRow()-1][Cur.getCol()].getAlive()) {
			count++;
		}
		if(gameBoard[Cur.getRow()+1][Cur.getCol()].getAlive()) {
			count++;
		}
		
		return count;
	}
	
	public void printBoard() {
		for(Cell[] row : gameBoard) {
			for(Cell item : row) {
				System.out.print(item+" ");
			}
			System.out.println();
		}
	}
	
}
