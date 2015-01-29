package auto;

public class tripleCell5 {
	
	Cell7 cell1;
	Cell7 cell2;
	Cell7 cell3;
	



	public tripleCell5(Cell7 leftParent, Cell7 centerParent, Cell7 rightParent) {
		
		this.cell1 = leftParent;
		this.cell2 = centerParent;
		this.cell3 = rightParent;
		
	}



	public Cell7 getCell1(){
		
		return cell1;
	}
	
	public Cell7 getCell2(){
		
		return cell2;
	}

	public Cell7 getCell3(){
	
	return cell3;
}
	
}