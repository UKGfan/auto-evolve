package auto;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import java.util.* ;

public class ImageWriter2 {



/* Save image from an Integer buffer (assumes integers are between [0-255]) */
   public static int save_image(ArrayList<Integer> sorted, int init, int N, int offSet, int mod, int directions, int iteration, int exclude){
         int width = N;
         int height = N;

         System.out.println("saving IMAGE");
         
         /* Make sure buffer is the correct size */
         if (sorted.size() != N*N){
            System.out.println("ERROR: Arrays of different sizes\n");
            return -1;
         }
       
         int[] d = directionParse(directions); 
         
         /* Create buffered image and loop through buffer adding pixels */
	 try {
          BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

          /*Loop through buffer and assign pixel value*/
          int i=0;
          int x=0, y=0;
          Integer value=0;
          Iterator<Integer> iter = sorted.iterator();
          while (iter.hasNext()) {
             value = iter.next();
             if(value != 0 ) value = value + 9000000;
             bufferedImage.setRGB((int)i%width, (int)i/width, (value << 16) + (value << 8) + value);
             i+=1;
          }

          //Save image
          //new File("oddmod_key_offsets2//" + offSet + "//" + mod + "//").mkdirs();
          //ImageIO.write(bufferedImage, "bmp", new File( "oddmod_key_offsets2//" + offSet + "//" + mod + "//" + iteration + ".bmp"));
          
          
         new File("exclude_test//").mkdirs();
         ImageIO.write(bufferedImage, "bmp", new File( "exclude_test//"+ exclude + "_" + iteration+  ".bmp"));
          
         }
         catch (IOException e) {
           System.out.println(e.getMessage());
         }

         return 1;
   }

   
	private static boolean isPrime(int n) {
	    // fast even test.
	    if(n > 2 && (n & 1) == 0)
	       return false;
	    // only odd factors need to be tested up to n^0.5
	    for(int i = 3; i * i <= n; i += 2)
	        if (n % i == 0) 
	            return false;
	    return true;
	}

  public static void main(String[] args) {

      //Set values of N and T
      int N=512;
      int T=1000;
      Random rand = new Random ( System.currentTimeMillis() ) ;

      //Create List
      ArrayList<Integer> buffer = new ArrayList<Integer>() ;

      //Add random variables to list
      int value=0;
      int max = 63;
      int min = 80;
      int walk = 3;
      boolean up = true;
      int record = 0;
      for(int i=0;i<N*N;i++){
          //value = rand.nextInt(4);
    	  //value = 0;
    	  
    	  if(walk%2==0){
    		  walk = walk / 2;
    	  }
    	  else walk = ((walk *3) + 1)/2;
    	  
    	  if (walk == 1 && up){
    		  System.out.println("at 1");
    		  for(int f = 0; f < 50; f++)buffer.add(i);
    		  walk = i;
    		  up = false;
    	  }
    	  if (walk == 1 && !up){
    		  for(int f = 999999; f > 0; f = f /2)buffer.add(752);
    		  walk = (171 + i) / i+1;
    		  up = true;
    	  }
    	  
    	  /*
    	  if(up){
    		  walk = walk ;
    		  if(walk == max) up = false;
    	  }
    	  else{
    		  walk--;
    		  if(walk == min) up = true;
    	  }
    	  */
    	  
    	  if(walk == 4616 || walk < 200){
    		  	if(record <= 0) record = 4;
    		  	//System.out.println("I know this is happening");
    		  	if(record-- > 0)
    			 buffer.add(255);
    			 if(walk== 4616)buffer.add(5000);
    		  
    	  }
    	  
    	 // System.out.println("I know this is also happening");
    	  if(record-- > 0)
 			 buffer.add(i);
 			 
 			 if(buffer.size() > N*N)break;
      }

      //Sort data
      //Collections.sort(buffer);
      
      walk = 0;
      
      ArrayList <Integer> buffer2 = new ArrayList<Integer>();
      for(int i=1;i<=N*N;i++){
    	  buffer2.add(buffer.get(i));
    	  //if(i % (i * 7) == 0)buffer2.set(i, buffer.get(i)*5);
    	  
    	  if(up){
    		  walk++;
    		  System.out.println("max" + walk);
    		  if(walk >= i/5) up = false;
    	  }
    	  else{
    		  walk--;
    		  System.out.println("min" + walk);
    		  if(walk <= min) up = true;
    	  }
    		  buffer2.set(i-1, (buffer.get(i-1)*(walk/5))/2);
    	  
         //if(i%37 == 0) buffer.set(i, 0);
         // if(isPrime(i)) buffer.set(i, 255);
      }

      //Save image
      String filename = String.format("playing_with_image_buffer_4.jpg", N, T);
      //save_image(buffer2, N, filename);
   }
  
  private static int[] directionParse(int directions){
	  
  int direction1 = directions % 5;
	directions = directions / 5;
	int direction2 = directions % 5;
	directions = directions / 5;
	int direction3 = directions % 5;
	directions = directions / 5;
	int direction4 = directions % 5;	
  
	int[] returnArray = {direction1, direction2, direction3, direction4};
	
	return returnArray;
	
  }
  
  private static String wD(int direction){
	  
	  //System.out.println(direction);
	  
	  switch (direction){
	  
	  case (0):
	  	return "up_"; // was up
	  
	  case (1):
	  	return "right_"; // was right
	  
	  case (2):
	  	return "left_";  // was down 
	  
	  case (3):
	  	return "up_";  // was left
	  
	  case (4):
		  return "off_";
	  
	  
	  }
	  
	  System.out.println(direction);
	  System.exit(direction);
	 
	  
	  return "error!";
  }
}

