
public class Launcher{

	public static void main(String[] args) {
       final int NUM_ITER = 200;
       final int SIZE = 40;
	   GameBoard game = new GameBoard(SIZE, SIZE, 7);
       game.printBoard();
       
       for(int i=0; i<NUM_ITER; i++) {
    	   spaces(41-SIZE);
    	   try
    	   {
    	       Thread.sleep(100);
    	   }
    	   catch(InterruptedException ex)
    	   {
    	       Thread.currentThread().interrupt();
    	   }
    	   game.updateBoard();
    	   game.printBoard();
       }
	}

	private static void spaces(int n)
	{
		for(int i=0; i<n; i++) {
			System.out.println();
		}
	}
}
