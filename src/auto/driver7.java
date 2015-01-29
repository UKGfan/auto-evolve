package auto;

import java.util.ArrayList;
import org.jtransforms.*;


// this version is trying to cylce through 25 write position for each pixel

// http://java.dzone.com/articles/understanding-sunmiscunsafe

public class driver7 {

	static int width = 187;
	
	static int offSetRadius = 0;
	
	static int startPoint = ((width * width) / 2);
	
	static int initValueStart = 1;
	
	static int initValueEnd = 1;
	
	static int frames = 31;
	
	static boolean pulseStart = false;
	
	static int modStart = 2;
	
	static int modEnd = 2;
	
	public static void main(String[] args){
		
		
		//boolean reduce = true;
		
		//int width = 15;
		
		//int offSetRadius = 1;
		
		Integer mod = 0;
		
		ArrayList<Cell7> oldFrame = null;
		ArrayList<Cell7> newFrame = null;
		
		int start = 0;
		
		// init first board

		
		//Short rule;
		
		for(int exclude = 0; exclude <4; exclude++){
			

		
		for(int directions = 617; directions < 618; directions++){
			

			
		for(int init = initValueStart; init <= initValueEnd; init++){
		

		//int[]offSetArray = {10, 16, 28, 34, 46, 52, 64, 70};
		
		int offSetDiameter = offSetRadius + offSetRadius + 1;
		
		for(int offSet = 0; offSet < offSetDiameter * offSetDiameter; offSet++){
		

		//for(int offSet: offSetArray){
		
			
		for(mod = modStart; mod <= modEnd; mod++){
			
			Short rule = 6; //rule;
			
			oldFrame = new ArrayList<Cell7>();
			
			newFrame = new ArrayList<Cell7>();
			
			//.println("just made frames");
			
				//mod = 4;
				
				for(int i = 0; i < width * width; i ++){
					
					
					
					start = 0;
			
					if (i == startPoint){ // (int)(width*width)/1 - 300 - (width + width + width + width)){
						////.println("middle check triggered");
						start = init;  // || i == 180000 || i == 2300000 ) start = 1;
					}
		
					////.println("i:  " + i + " half measure: " + (int)(width*width)/2);
					
					
					////.println("i:           " + i);
					////.println("Start value: " + start);
			
					// int position, int width, short rule, int value, int mod, int direction
					
					
					
					oldFrame.add(new Cell7(i, width, (short)rule, start, mod, offSet, offSetRadius));
					newFrame.add(new Cell7(i, width, (short)rule, start, mod, offSet, offSetRadius));
					
				}
				
				int count = 0;
				
				
				//for(Cell7 n: oldFrame){
				//	//.print(count++ + "  ");
				//	//.println("oldF value:" + n.getValue());
				//}
				
				//count = 0;
				//for(Cell7 n: newFrame){
				//	//.print(count++ + "  ");
				//	//.println("newF value:" + n.getValue());
				//}
				
				
				//frame iterator loop
				for(int i = 0; i <= frames; i++){
					
					ImageWriter2.save_image(getValues(oldFrame), init, width, offSet, mod, directions, i, exclude);
					
					
					for(Cell7 n: oldFrame){
						
						n.setBoards(oldFrame, newFrame);
						
						n.implementRules1(directions);
						
					}
					
					for(Cell7 n: newFrame){
						
						if(pulseStart && i == 0){ 
							
							// this will pulse the starting pixel for one frame instead of keeping it lit
							// if pusleStart is set to true
						
							if (n.position == startPoint)n.setValue(0);
						}
						
						switch(exclude){
						
						case(0):
							
							// up axis exclude
						if(n.position % width == startPoint % width && n.position < startPoint){
							
							n.setValue(0);
						}
						
						case(1):
							
							// up axis exclude
						if(n.position / width == startPoint / width && n.position < startPoint){
							
							n.setValue(0);
						}
						
						case(2):
							
							// up axis exclude
						if(n.position % width == startPoint % width && n.position > startPoint){
							
							n.setValue(0);
						}
						
						case(3):
							
							// up axis exclude
						if(n.position / width == startPoint / width && n.position > startPoint){
							
							n.setValue(0);
						}
						
						}
						// apply the mod if it's not 1
						
						if(mod > 1) n.applyMod();
						
					}
					
					//printAscii(getValues(oldFrame));
							
					// clear the old frame (we used it already to calculate newFrame
					oldFrame = new ArrayList<Cell7>();
					
					// set new frame to oldFrame
					for(Cell7 n: newFrame){
						
						oldFrame.add(n.clone());
					}
					
				} // frame
		
		} //mod
		} // offset
		} // start value
		} // directions
		} // exclude
	}
	
	public static void printAscii(ArrayList<Integer> list){
		
		int count = 0;
		for(Integer n: list ){
			if(count++ % width == 0)System.out.println();
			if(n != 0)System.out.print("O");
			else System.out.print("_");
			
		}
		
	System.out.println();	
		
	}
	
	public static ArrayList<Integer> getValues(ArrayList<Cell7> oldFrame){
		
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for(int i = 0; i < oldFrame.size(); i++){
			
			values.add(oldFrame.get(i).getValue());
			
		}
		
		return values;
		
	}
	
	public static void scanStarts(ArrayList<Cell7> oldFrame, int init, int offSet, int mod, int i){
		
		if(init % 1000000 == 0)
			//ImageWriter2.save_image(getValues(oldFrame), init, width, offSet, mod, i);
		
		if(i == 18  && init % 3 != 0)
			if(oldFrame.get(startPoint).getValue() != 0 
			&& oldFrame.get(startPoint - 1).getValue() == 0
			&& oldFrame.get(startPoint - 2).getValue() == 0
			&& oldFrame.get(startPoint - 3).getValue() == 0
			&& oldFrame.get(startPoint - 4).getValue() == 0
			&& oldFrame.get(startPoint - 5).getValue() == 0
			&& oldFrame.get(startPoint - 6).getValue() == 0
			&& oldFrame.get(startPoint - 7).getValue() == 0) return;
			//	ImageWriter2.save_image(getValues(oldFrame), init, width, offSet, mod, i);
	}
	
}