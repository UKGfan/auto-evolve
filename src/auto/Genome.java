package auto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Genome {

	public ArrayList<Integer> modList;
	
	public ArrayList<Offset> writeCells;
	
	public ArrayList<Offset> readCells;
	
	public int rate;
	
	public int rateBound;
	
	private Random noise;
	
	
	// copy constructor
	public Genome(Genome original){
		
		this.modList = new ArrayList<Integer>();
		for(Integer i: original.modList){
			this.modList.add(i);
		}
		
		this.writeCells = new ArrayList<Offset>();
		for(Offset o: original.writeCells){
			this.writeCells.add(o.clone(rate));
		}
		
		this.readCells = new ArrayList<Offset>();
		for(Offset o: original.readCells){
			this.writeCells.add(o.clone(rate));
		}
		
		this.rate = original.rate;
		
		this.rateBound = original.rateBound;
		
		this.noise = new Random(this.hashCode());
	}
	
	// random init
	public Genome(){
		
		this.modList = new ArrayList<Integer>();
		
		this.writeCells = new ArrayList<Offset>();
		
		this.readCells = new ArrayList<Offset>();
		
		this.rate = 9;
		
		this.rateBound = 10;
		
		
	}
	
	public void mutate(){
		
		// modList mutations (might be better to lock this at one modulus, but idk)
		
		this.modListMutations();
		
		// WriteCell Mutations
		
		this.offSetMutations(writeCells);
		
		// ReadCell Mutations
		
		this.offSetMutations(readCells);
		
		// mutate mutation rate
		
		int tempRate = rate;
		
		if(noise.nextInt(rateBound) < tempRate){
			rate += noise.nextInt(rate) - rate/2;
		}
		
		if(noise.nextInt(rateBound) < tempRate){
			rateBound += noise.nextInt(rate) - rate/2;
		}
		
	}
	
	private void offSetMutations(ArrayList<Offset> target){
		
		// add offsets
		if(noise.nextInt(rateBound) < rate){
			for(int i = 0; i < noise.nextInt(rate); i++){
			target.add(new Offset(rate));
			}
		}
		
		// mutate offsets
		for(Offset o: target){
			if(noise.nextInt(rateBound) < rate){
				o.mutate(rateBound, rate);;
			}
		}
		
		// remove offsets
		for(Offset o: target){
			if(noise.nextInt(rateBound) < rate){
				modList.remove(0);
			}
		}
	}
	
	private void modListMutations(){
	
		// add mods
		if(noise.nextInt(rateBound) < rate){
			for(int i = 0; i < noise.nextInt(rate); i++){
				modList.add(noise.nextInt(rate));
			}
		}
		
		// increment mods
		for(Integer i: modList){
			if(noise.nextInt(rateBound) < rate){
				i += noise.nextInt(rate);
			}
		}
		
		// decrement mods
		for(Integer i: modList){
			if(noise.nextInt(rateBound) < rate){
				
				if(i != null){
					i -= noise.nextInt(rate);
				}
			}
		}	
		
		// remove mods
		for(Integer i: modList){
			if(noise.nextInt(rateBound) < rate){
				if(i != null){
					modList.remove(i);
				}
			}
		}
		
	}
}
