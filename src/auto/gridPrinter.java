package auto;

public class gridPrinter {
	
	
	public static void main(String[]args ){
		
		int gridsize = 9;
		
		for(int i = 0; i < 9; i++){
			System.out.println();
			
			for(int n = 0; n < 9; n++){
				
				int f = n + (i * 9);
				
				if(f == 49)
					System.out.print("  O ");
				else if ( f == 10 || f == 16 || f == 28 || f == 34 || f == 64 || f == 70 )
					System.out.print("  X ");
				else
					System.out.format("%4d", f);
				
				
				
			}
			
		}
		
		
	}

}
