package auto;

import java.util.ArrayList;

public class Cell7 {
	
	boolean reduce = true;
	
	int position;
	int width;
	int value;
	int nextValue;
	
	// writecell offset values
	
	int offSet;
	int offSetRadius;
	
	
	int mod;
	
	boolean fill;
	
	short rule;
	
	ArrayList<Cell7> thisBoard;
	ArrayList<Cell7> nextBoard;
	
	
	// constructor to initialize Cell7 at 0
	
	//public Cell7(int position, int width, short rule, int mod){
		
	//	this(position, width, rule, 0, mod);
	//}
	
	// Constructor to initialize a Cell7 with a specific value
	
	public Cell7(int position, int width, short rule, int value, int mod, int offSet, int offSetRadius){
		
		this.position = position;
		this.width = width;
		this.rule = rule;
		this.value = value;
		
		////////System.out.println(this.rule);
		
		this.mod = mod;
		
		//////////System.out.println("Value: " + this.value);
		
		this.offSet = offSet;
		this.offSetRadius = offSetRadius;
		
	}
	
	public Cell7 clone(){
		
		return new Cell7(position, width, rule, value, mod, offSet, offSetRadius);
	}
	
	public int getValue(){
		
		return value;
		
	}
	

	public void applyMod() {
		
		if(this.value % mod == 0) this.value = 0;
		// TODO Auto-generated method stub
		
	}
	
	public void setMod(int setMod){
		
		if (setMod != 0)
			
			mod = setMod;
	}
	
	public void setValue(int setValue){
		
		
		//if (setValue % mod == 0) setValue = 0;
		
		//if (setValue % 41 == 0) setValue = 0;
		
		//if (this.position % width == width - 300 && position < (width*width) - (width*5)) setValue = 0;  // exculsion stain for harmonics
			
		
		this.value = setValue;
		
		////System.out.println("setvalue: " + setValue + " value: " + value);
	}
	
	
	
	
	private tripleCell5 getTop(){
		
	
		return new tripleCell5(this.getLeftParent(thisBoard),this.getCenterParent(thisBoard),this.getRightParent(thisBoard));
	}
	
	private tripleCell5 getLeft(){
		
		return new tripleCell5(this.getSWCell(thisBoard), this.getLeftCell(thisBoard), this.getLeftParent(thisBoard));
	}
	
	private tripleCell5 getRight(){
		
		
		
		return new tripleCell5(this.getRightParent(thisBoard), this.getRightCell(thisBoard), this.getSECell(thisBoard));		
	}
	
	private tripleCell5 getDown(){
		
		return new tripleCell5(this.getSECell(thisBoard), this.getSCell(thisBoard), this.getSWCell(thisBoard));		
		
	}
	
	public void setBoards(ArrayList<Cell7> thisBoard, ArrayList<Cell7> nextBoard){
		
		this.thisBoard = thisBoard;
		this.nextBoard = nextBoard;
		
	}
	
	public void implementRules1(int directions){
		
		int direction1 = directions % 5;
		directions = directions / 5;
		int direction2 = directions % 5;
		directions = directions / 5;
		int direction3 = directions % 5;
		directions = directions / 5;
		int direction4 = directions % 5;		
		
		int writeValue = 0;
		tripleCell5 row = null;
		Cell7 writeCell = null;
		int modp1 = 0;
		int modp2 = 0;
		int modp3 = 0;
		int modchild = 0;
		
		// left row writing right cell
		
		row = this.getLeft();
		writeCell = getGridCell2(offSet, offSetRadius, direction1, nextBoard); 
		if(writeCell != null){

			writeValue = implementRules2(row);

			modchild = 0;
			
			if(writeValue != 0){
			
			modchild = writeCell.mod;

			}
			
			writeCell.setValue(writeValue + writeCell.getValue());
			
			////System.out.println(writeCell.getValue());
		}
		
		// top row writing south cell
		
		row = this.getTop();
		writeCell = getGridCell2(offSet, offSetRadius, direction2, nextBoard); 
		if(writeCell != null){
		
			
			writeValue = implementRules2(row);
			
			writeCell.setValue(writeValue + writeCell.getValue());
			
			////System.out.println(writeCell.getValue());
		}
		
		
		// bottom row writing top cell
		
		row = this.getDown();
		writeCell = getGridCell2(offSet, offSetRadius, direction3, nextBoard); 
		if(writeCell != null){
			
			writeValue = implementRules2(row);

			writeCell.setValue(writeValue + writeCell.getValue());

			////System.out.println(writeCell.getValue());
		
		}
		
		row = this.getRight();
		writeCell = getGridCell2(offSet, offSetRadius, direction4, nextBoard); 
		if(writeCell != null){
			
			writeValue = implementRules2(row);
			
			writeCell.setValue(writeValue + writeCell.getValue());
			
			////System.out.println(writeCell.getValue());
		}
		
		}
		
	private int[] getMods(tripleCell5 modsRow){
		
		return new int[]{modsRow.cell1.mod,modsRow.cell2.mod,modsRow.cell3.mod};
		
		
	}
	
	
	private int implementRules2(tripleCell5 oppositeRow){
	
		
		int tempRule = this.rule;
		
		Cell7 leftParent = oppositeRow.getCell1();
		Cell7 centerParent = oppositeRow.getCell2();
		Cell7 rightParent = oppositeRow.getCell3();
		
		int leftParentValue;
		int centerParentValue;
		int rightParentValue;
		
		if (leftParent == null){
			
			leftParentValue = 0;
		}
		else leftParentValue = leftParent.getValue();
		
		if (centerParent == null){
			   centerParentValue = 0;
		}
		else centerParentValue = centerParent.getValue();
		
		if (rightParent == null){
			rightParentValue = 0;
		}
		else rightParentValue = rightParent.getValue();
		
		////System.out.println("L: " + leftParentValue + " C: " + centerParentValue + " R: " + rightParentValue);
		////System.out.println("Rule: " + rule);
		
		boolean rule0;
		boolean rule1;
		boolean rule2;
		boolean rule3;
		boolean rule4;
		boolean rule5;
		boolean rule6;
		boolean rule7;

		
		if(tempRule%2 == 1){
			rule0 = true;
		}
		else rule0 = false;
		tempRule = (short) (rule / 2);
		
		
		if(tempRule%2 == 1){
			rule1 = true;
		}
		else rule1 = false;
		tempRule = (short) (rule / 2);
		
		
		if(tempRule%2 == 1){
			rule2 = true;
		}
		else rule2 = false;
		tempRule = (short) (rule / 2);
		
		if(tempRule%2 == 1){
			rule3 = true;
		}
		else rule3 = false;
		tempRule = (short) (rule / 2);
		
		
		if(tempRule%2 == 1){
			rule4 = true;
		}
		else rule4 = false;
		tempRule = (short) (rule / 2);
		
		
		if(tempRule%2 == 1){
			rule5 = true;
		}
		else rule5 = false;
		tempRule = (short) (rule / 2);
		
		if(tempRule%2 == 1){
			rule6 = true;
		}
		else rule6 = false;
		tempRule = (short) (rule / 2);
		
		if(tempRule%2 == 1){
			rule7 = true;
		}
		else rule7 = false;
		tempRule = (short) (rule / 2);
		
		////////System.out.print(rule7);
		////////System.out.print(rule6);
		////////System.out.print(rule5);
		////////System.out.print(rule4);
		////////System.out.print(rule3);
		////////System.out.print(rule2);
		////////System.out.print(rule1);
		////////System.out.println(rule0);
		
		
		
		//this.value = leftParent.getValue() + centerParent.getValue() + rightParent.getValue();

		////////System.out.println("Left:  " + leftParent.getValue() + "  Center:  "  + centerParent.getValue() + "  Right:  " + rightParent.getValue());
		
		if((leftParentValue != 0) && (centerParentValue != 0) && (rightParentValue != 0)){
			
			//////System.out.println("Rule 7 fill");
			
			fill = rule7;
		
			
			
		}
		
		if((leftParentValue != 0) && (centerParentValue != 0) && (rightParentValue == 0)){
			
			fill = rule6;
			
			//////System.out.println("Rule 6 fill");
			
		}
		
		if((leftParentValue != 0) && (centerParentValue == 0) && (rightParentValue != 0)){
			
			fill = rule5;
			
			//////System.out.println("Rule 5 fill");
			
		}
		
		if((leftParentValue != 0) && (centerParentValue == 0) && (rightParentValue == 0)){
			
			fill = rule4;
			
			//////System.out.println("Rule 4 fill");
			
		}
		
		if((leftParentValue == 0) && (centerParentValue != 0) && (rightParentValue != 0)){
			
			fill = rule3;
			
			//////System.out.println("Rule 3 fill");
			
		}
		
		if((leftParentValue == 0) && (centerParentValue != 0) && (rightParentValue == 0)){
			
			fill = rule2;
			
			//////System.out.println("Rule 2 fill");
			
		}
		
		if((leftParentValue == 0) && (centerParentValue == 0) && (rightParentValue != 0)){
			
			fill = rule1;
			
			//////System.out.println("Rule 1 fill");
			
		}
		
		if((leftParentValue == 0) && (centerParentValue == 0) && (rightParentValue == 0)){
			
			fill = rule0;
			
			//////System.out.println("Rule 0 fill");
			
		}
		
		int writeValue = 0;
		
		if (fill){
			
			////////System.out.println("INHERITED FILL_____________________________~~~~~~~~~~~~~~~~~~~");
				
				writeValue = (leftParentValue + centerParentValue + rightParentValue);
			
			
		}
		
		return writeValue;
		//if (this.value != 0) //////////System.out.println("   "  + this.value);
		//else //////////System.out.println();
	}
	
	
	// Parent Calls after this
	
	
	private Cell7 getLeftParent(ArrayList<Cell7> getBoard){
		
		int leftParentIndex = position - width - 1;
		
		//////////System.out.println(leftParentIndex);
		
		//////////System.out.println(leftParentIndex%width - position);
		
		if(leftParentIndex >= 0){
			
			if(position%width == 0) return null;
			
			return getBoard.get(leftParentIndex);
			
		}
		
		return null;
		
	}
	
	private Cell7 getCenterParent(ArrayList<Cell7> getBoard){
		
		int centerParentIndex = position - width;
		
		if(centerParentIndex >= 0){
			
			return getBoard.get(centerParentIndex);
			
		}
		
		return null;
	}

	private Cell7 getRightParent(ArrayList<Cell7> getBoard){
		
		int rightParentIndex = position - width + 1;
		
		if(rightParentIndex >= 0){
			
			//////////////System.out.println(rightParentIndex);
			
			if(position%width == (width-1)) return null;
			
			return getBoard.get(rightParentIndex);
			
		}
		
		return null;
		
	}
	
	private Cell7 getSWCell(ArrayList<Cell7> getBoard){
		
		int SWIndex = position + width - 1;
		
		//////////System.out.println(leftParentIndex);
		
		//////////System.out.println(leftParentIndex%width - position);
		
		if(SWIndex < Math.pow(width,2)){
			
			if(position%width == 0) return null;
			
			return getBoard.get(SWIndex);
			
		}
		
		return null;
		
	}
	
	private Cell7 getSCell(ArrayList<Cell7> getBoard){
		
		int SIndex = position + width;
		
		if(SIndex < width * width){
			
			////////System.out.println(SIndex);
			
			return getBoard.get(SIndex);
			
		}
		
		return null;
	}

	private Cell7 getSECell(ArrayList<Cell7> getBoard){
		
		int SEIndex = position + width + 1;
		
		if(SEIndex < Math.pow(width, 2)){
			
			//////////////System.out.println(rightParentIndex);
			
			if(position%width == (width-1)) return null;
			
			return getBoard.get(SEIndex);
			
		}
		
		return null;
		
	}
		
	private Cell7 getLeftCell(ArrayList<Cell7> getBoard){
		
		int leftCell7Index = position - 1;
		
	
		if(position%width == 0) return null;
			
		return getBoard.get(leftCell7Index);
			
	}
	
	private Cell7 getRightCell(ArrayList<Cell7> getBoard){
		
		int rightCell7Index = position + 1;
		
	
		if(position%width == width-1) return null;
			
		return getBoard.get(rightCell7Index);
			
	}
	
	private Cell7 getGridCell(int offsetNo, int offsetRadius, ArrayList<Cell7> getBoard){
	
		////System.out.println(offsetNo + "  ~~");
		
		int offsetDiameter = offsetRadius + offsetRadius + 1;
		
		int getPos = this.position;
		
		//System.out.println("1: " + getPos);
		
		getPos = getPos - offsetRadius - (width * offsetRadius);
	
		//System.out.println("2: " + getPos);
		
		int row = offsetNo / offsetDiameter;
		
		//System.out.println("Row: " + row);
		
		//System.out.println("3: " + getPos);
		
		//System.out.println(offsetNo % offsetDiameter);
		
		getPos = getPos + (offsetNo % offsetDiameter) + (width * row);
		
		//System.out.println("4: " + getPos);
		
		if(getPos < 0)return null;
		if(getPos >= width * width)return null;
		if((getPos) % width > ((getPos + offsetRadius) % width)) return null; // this tells us if we are off the board to the left 
		if((getPos) % width < ((getPos - offsetRadius) % width)) return null; // this tells us if we are off the board to the right
	
		//System.out.println("5: " + getPos);
		
		return getBoard.get(getPos);
	
	}
	
	private Cell7 getGridCell2(int lookUp, int radius, int direction, ArrayList<Cell7> getBoard){
		
		int diameter = radius + radius + 1;
		
		//System.out.println(lookUp);
		
		int inverseLookUp = (diameter * diameter) - lookUp - 1;
		
		//System.out.println(inverseLookUp);
		
		// row = lookUp / offsetDiameter;
		// column = lookUp % offsetDiameter;
		// inverseLookup = (offsetDiameter * offsetDiameter - lookUp ) / offsetDiameter;
		
		// try and figure out these rotations
		
		int getPos = this.position;									// first get the position of the cell in parent-array
		
		//System.out.println("1: " + getPos);
		
		getPos = getPos - radius - (width * (radius));	// set local index for big array to the cell at 0,0 in getCell sub-array
	
		switch(direction){
		
			case 0: 
				
				// normal traversal: row, column
				getPos += (width * (lookUp / diameter)) + (lookUp % diameter);		
				break;
				
			case 1:
		
				// right corner first traversal: column, row'
				getPos += (width * (lookUp % diameter)) + ( inverseLookUp / diameter );
				break;
			
			case 2:
			
				// bottom right corner first: row', column' 
				getPos += (width * (inverseLookUp / diameter)) + (inverseLookUp % diameter);
				break;
			
			case 3:
				
				// bottom left corner first: column', row
				getPos += (width * (inverseLookUp % diameter)) + (lookUp / diameter);
				break;
			case 4:
				
				// case 4 is off
				return null;
			
		}
		
				
		// check if we are off the board
		
		if(getPos < 0)return null;										// check top
		if(getPos >= width * width)return null;							// check bottom
		if((getPos) % width > ((getPos + radius) % width)) return null; // check left 
		if((getPos) % width < ((getPos - radius) % width)) return null; // check right
		
		return getBoard.get(getPos);									// return valid cell on getBoard
		
	}
}
