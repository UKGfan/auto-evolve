package auto;

import java.util.Random;

public class Offset {

	int rowOffset;
	
	int columnOffset;
	
	boolean dead;
	
	
	
	public Offset(int rate){
	
		Random noise = new Random(this.hashCode());
		
		this.rowOffset = noise.nextInt(rate) - rate/2;
		this.columnOffset = noise.nextInt(rate) - rate/2;
		
	}
	
	public Offset(int rowOffset, int columnOffset){
		
		this.rowOffset = rowOffset;
		this.columnOffset = columnOffset;
	}
	
	public int getOffCellPos(int getPos, int width){
		
		getPos += columnOffset;
		
		getPos += (rowOffset * width);
		
		if(getPos < 0)return -1;											// check top
		if(getPos >= width * width)return -1;								// check bottom
		if((getPos) % width > ((getPos + columnOffset) % width)) return -1; // check left 
		if((getPos) % width < ((getPos - columnOffset) % width)) return -1; // check right
		
		return getPos;														// return valid cell position
		
	}
	
	public void mutate(int rateBound, int rate){
		
		Random noise = new Random(this.hashCode());
		
		if(noise.nextInt(rateBound) < rate){
			
			this.rowOffset += (noise.nextInt(rate) - rate/2);
		}
		
		if(noise.nextInt(rateBound) < rate){
			
			this.columnOffset += (noise.nextInt(rate) - rate/2);
		}
		
		if(noise.nextInt(rateBound) < rate){
			
			int temp = this.rowOffset;
			this.rowOffset = this.columnOffset;
			this.columnOffset = temp;
		}
	}
	
	public void checkAlive(int width){
		
		// check to see if this offset ever writes inside the frame
		
		if(Math.abs(rowOffset) > width || Math.abs(columnOffset) > width){
			
			dead = true;
		}
		
	}
	
	public Offset clone(int rate){
		
		if(dead) return null;
		
		else return new Offset(this.rowOffset, this.columnOffset);
	}
}
