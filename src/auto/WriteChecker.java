package auto;

import java.util.ArrayList;

final class WriteChecker {

	static boolean writeCheck(ArrayList<ArrayList<Boolean>> writeConditions, ArrayList<Boolean> read){
	
		
		
		boolean ret = false;
		
		//loop through all the write conditions
		for(ArrayList<Boolean> list: writeConditions){
			
			//System.out.println(read.size());
			
			System.out.println("WriteCondition \\/ ");
			for(Boolean b: list){
				System.out.print("   " + b);
			}
			
			System.out.println();
			
			int matchCount = 0;
			
			// see if our write condition l is anywhere in the read
			for(int i = 0; i < read.size(); i++){
			
				System.out.print("Read bit: " + read.get(i));
				
				if(read.get(i) == list.get(matchCount)){
					
					System.out.println("    Match");
					matchCount++;
					//System.out.println("Match!! i:" + i + " count: " + matchCount);
				}
				else{
					System.out.println("    Miss");
					matchCount = 0;
					//System.out.println("Miss..  i:" + i + " count: " + matchCount);
				}
				
				System.out.println();
				
				if (matchCount == list.size()){
					return true;
				}
			}// interior loop for each write term
		}// loop for all write terms
		
		return false;
	}
	
	
	// maybe do this by proportion instead of by sequence
	
	// something to think about
	
	
	public static void main(String[] args){
		
		ArrayList<ArrayList<Boolean>> Conditions = new ArrayList<ArrayList<Boolean>>();
		
		ArrayList<Boolean> cond1 = new ArrayList<Boolean>();
		cond1.add(false); cond1.add(true); cond1.add(true);
		
		ArrayList<Boolean> cond2 = new ArrayList<Boolean>();
		cond2.add(false); cond2.add(true); cond2.add(false);
		
		ArrayList<Boolean> cond3 = new ArrayList<Boolean>();
		cond3.add(false); cond3.add(true); cond3.add(true);
		
		Conditions.add(cond1); Conditions.add(cond2); Conditions.add(cond3);
		
		ArrayList<Boolean> read1 = new ArrayList<Boolean>();
		
		read1.add(true); read1.add(true); read1.add(true); read1.add(false); 
		read1.add(true); read1.add(false); read1.add(true); read1.add(true);
		read1.add(true); read1.add(true); read1.add(true); read1.add(true);
		
		ArrayList<Boolean> read2 = new ArrayList<Boolean>();
		
		read2.add(false); read2.add(false); read2.add(true); read2.add(false); 
		read2.add(false); read2.add(false); read2.add(false); read2.add(false);
		read2.add(false); read2.add(false); read2.add(false); read2.add(false);
		
		System.out.println("1: " + writeCheck (Conditions, read1));
		
		System.out.println("2: " + writeCheck (Conditions, read2));
		
	}
	
	
}
