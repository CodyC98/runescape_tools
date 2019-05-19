import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;
import java.awt.Point;

public class StanceSelector {

	//variables for images to look for
	private static BufferedImage bow;
	private static BufferedImage crane;
	private static BufferedImage lotus;
	private static BufferedImage ward;

	//Objects for obtaining and searching images
	private static ImageTaker camera;
	private static ImageComparator compare;

	//Points for search area in screen capture image
	private static Point start = new Point(1800, 110);
	private static Point finish = new Point(2050, 175);

	private static Robot keyboard;
	private static int currentPressed = KeyEvent.VK_0;
	private static int count = 0;

	public static void main(String[] args) {
		camera = new ImageTaker();
		compare = new ImageComparator();
		try {
	        bow = ImageIO.read(new File("Stances/Bow.png"));
	       	crane = ImageIO.read(new File("Stances/Crane.png"));
	        lotus = ImageIO.read(new File("Stances/Lotus.png"));
	        ward = ImageIO.read(new File("Stances/Ward.png"));
			keyboard = new Robot();
		} catch(Exception e) {
			e.printStackTrace();
		}
		while(true) {
			Check();
			if(count > 10) {
				break;
			} else {
				try {
					TimeUnit.SECONDS.sleep((long)0.5);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static int Check(){
		BufferedImage image = null;
		image = camera.getImage();
	    if(compare.isOnScreen(image, bow, start, finish)) {
	    	pushButton(KeyEvent.VK_2);
	    	count = 0;
	    } else if(compare.isOnScreen(image, crane, start, finish)) {
	    	pushButton(KeyEvent.VK_1);
	    	count = 0;
	    } else if(compare.isOnScreen(image, lotus, start, finish)) {
	    	pushButton(KeyEvent.VK_3);
	    	count = 0;
	    } else if(compare.isOnScreen(image, ward, start, finish)) {
	    	pushButton(KeyEvent.VK_4);
	    	count = 0;
	    } else {
	    	System.out.print("Nothing Found (" + count + ")\n");	//terminal will count to 10 then exit due to inactivity if no matches are found
	    	count++;
	    }
	    return count;
	}

	//num is the keycode for the button in question
	private static void pushButton(int num) {
		if(currentPressed == num) {
			return;
		} else {
			keyboard.keyRelease(currentPressed);
			keyboard.keyPress(num);
			currentPressed = num;
		}
	}

}
