import java.awt.image.BufferedImage;
import java.awt.Point;

public class ImageComparator {
	
		public static boolean isOnScreen(BufferedImage search, BufferedImage find, Point begin, Point end){
		for(int x = (int)begin.getX(); x < (int)end.getX(); x++){	//search between 1800 and 2050 x-coordinates
	    	for(int y = (int)begin.getY(); y < (int)end.getY(); y++){	//search between 110 and 175 y-coordinates

	    		boolean invalid = false;
	    		int k = x,l = y;
	    		for(int a = 0; a < find.getWidth(); a++){
	    			l = y;
	    			for(int b = 0; b < find.getHeight(); b++){
	    				if(find.getRGB(a, b) != search.getRGB(k, l)){
	    					invalid = true;
	    					break;
	    				}
	    				else{
	    						l++;
	    				}
	    			}
	    			if(invalid){
	    				break;
	    			}else{
	    					k++;
	    			}
	    				
	    		}
	    		
	    		if(!invalid){
	    			return true;
	    		}
		    }
		}
		return false; //If no image match is found
		
	}

}