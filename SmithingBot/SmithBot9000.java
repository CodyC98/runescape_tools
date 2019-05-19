import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.util.Random;

public class SmithBot9000 {

	//Points for dragging from first slot
	private static Point first = new Point(2400, 1055);
	private static Point next;

	//Points for in-game object pixel coordinates
	private static Point Anvil_loc = new Point(1215, 725);
	private static Point Forge_loc = new Point(1275, 660);
	private static Point MasterworkTab_loc = new Point(1070, 670);
	private static Point PartiallyFoldedAlloyBar_loc = new Point(1195, 490);
	private static Point MasterworkPlate_loc = new Point(1245, 490);
	private static Point CurvedMasterworkPlate_loc = new Point(1300, 490);
	private static Point UntemperedMasterworkArmourPiece_loc = new Point(1145, 540);
	private static Point MasterworkArmourPiece_loc = new Point(1195, 540);

	//Objects and variables for checking for ceremonial prompt
	private static ImageTaker camera = new ImageTaker();
	private static ImageComparator compare = new ImageComparator();
	private static BufferedImage prompt;
	private static BufferedImage screen;
	private static Point begin = new Point(1500,1450);
	private static Point end = new Point(2000, 1650);
	private static Point secondSlot = new Point(2440 , 1055);

	//Variables to determine starting conditions
	private static int progress = 0;
	private static int completed = 0;
	private static boolean started = true; //assume started, will change if empty state is selected, or 0 progress is made through selected state

	//Other objects/variables
	private static Random rand = new Random();
	private static Robot controller;
	private static Scanner input = new Scanner(System.in);
	private static int progressPerCycle = 1190;	//can change depending on level
	private static boolean mousePressed = false;
	private static boolean spacePressed = false;

	public static void main(String[] args) {
		try {
			controller = new Robot();
			prompt = ImageIO.read(new File("CeremonialSmithing.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		//obtaining initial state
		System.out.print("Select the initial piece to begin or continue:\n");
		System.out.print("(0) : Empty\n(1) : Partially Folded Alloy Bar\n(2) : Masterwork Plate\n");
		System.out.print("(3) : Curved Masterwork Plate\n(4) : Untempered Masterwork Armour Piece\n(5) : Masterwork Armour Piece\n");
		int initialState = input.nextInt();	

		//obtaining initial progress
		if(initialState == 1){
			System.out.print("Enter the current number of folds:\n");
			progress = input.nextInt();
		} else if (initialState == 0) {
			started = false;
		} else {
			System.out.print("Enter the current progress:\n");
			progress = input.nextInt();
		}
		if(progress == 0) {
			started = false;
		}

		//obtaining number of pieces completed
		System.out.print("Enter number of completed pieces (0 - 14) :\n");
		completed = input.nextInt();

		int a = completed/4;
		int b = completed%4;

		System.out.print("Starting...\n");

		try {
			TimeUnit.SECONDS.sleep(2); //gives time to click into runescape window
		} catch(Exception e) {
			e.printStackTrace();
		}

		for(int i = a; i < 4; i++) {
			for(int j = b; j < 4; j++) {
				next = new Point(2520 - 40*j, 1270 - 35*i);
				if(initialState == 0) {
					Empty();	
				} else if(initialState == 1) {
					PartiallyFoldedAlloyBar();
				} else if(initialState == 2) {
					MasterworkPlate();
				} else if(initialState == 3) {
					CurvedMasterworkPlate();
				} else if(initialState == 4) {
					UntemperedMasterworkArmourPiece();
				} else if(initialState == 5) {
					MasterworkArmourPiece();
				}
				initialState = 0;
				if(i == 3 && j == 1) {
					break;
				}
			}
		}
	}

	private static void Empty() {
		try {
			controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
			Click();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
			Click();
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

			if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

			controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
			Click();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
			Click();
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

			controller.mouseMove((int)PartiallyFoldedAlloyBar_loc.getX(), (int)PartiallyFoldedAlloyBar_loc.getY());
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
			Click();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
			Click();
			TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

			Space();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
			Space();

			TimeUnit.SECONDS.sleep(75);
			progress += 60;
			PartiallyFoldedAlloyBar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void PartiallyFoldedAlloyBar() {
		try{
			while(progress < 1001) {
				controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

				if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

				controller.mouseMove((int)PartiallyFoldedAlloyBar_loc.getX(), (int)PartiallyFoldedAlloyBar_loc.getY());
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(rand.nextInt(3) + 1);

				Space();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Space();

				TimeUnit.SECONDS.sleep(75);
				progress += 60;
			}
			progress = 0;
			started = false;
			MasterworkPlate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private static void MasterworkPlate() {
		try {
			if(!started){
				controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				controller.mouseMove((int)MasterworkPlate_loc.getX(), (int)MasterworkPlate_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				Space();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Space();
				TimeUnit.SECONDS.sleep(rand.nextInt(6) + 5);
			}

			while(progress < 10000){
				controller.mouseMove((int)Forge_loc.getX(), (int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(5);

				controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(90);	//more than 0 heat but less than 3/4 -- minimise loss due to timing
				progress += progressPerCycle;
			}
			progress = 0;
			started = false;
			CurvedMasterworkPlate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void CurvedMasterworkPlate() {
		try {
			if(!started) {
				controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				controller.mouseMove((int)CurvedMasterworkPlate_loc.getX(), (int)CurvedMasterworkPlate_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				Space();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Space();
				TimeUnit.SECONDS.sleep(rand.nextInt(6) + 5);
			}

			while(progress < 10000){
				controller.mouseMove((int)Forge_loc.getX(), (int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(5);

				controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(90);	//more than 0 heat but less than 3/4 -- minimise loss due to timing
				progress += progressPerCycle;
			}
			progress = 0;
			started = false;
			UntemperedMasterworkArmourPiece();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void UntemperedMasterworkArmourPiece() {
		try {
			if(!started) {
				controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				controller.mouseMove((int)UntemperedMasterworkArmourPiece_loc.getX(), (int)UntemperedMasterworkArmourPiece_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				Space();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Space();
				TimeUnit.SECONDS.sleep(rand.nextInt(6) + 5);
			}
			
			while(progress < 10000){
				controller.mouseMove((int)Forge_loc.getX(), (int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(5);

				controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(90);	//more than 0 heat but less than 3/4 -- minimise loss due to timing
				progress += progressPerCycle;
			}
			progress = 0;
			started = false;
			MasterworkArmourPiece();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void MasterworkArmourPiece() {
		try {
			if(!started) {
				controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				if(ceremonialPrompt()) {
					controller.mouseMove((int)Forge_loc.getX(),(int)Forge_loc.getY());
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
					Click();
					TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				}

				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				controller.mouseMove((int)MasterworkArmourPiece_loc.getX(), (int)MasterworkArmourPiece_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);

				Space();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Space();
				TimeUnit.SECONDS.sleep(rand.nextInt(6) + 5);
			}
			
			while(progress < 1000){
				controller.mouseMove((int)Forge_loc.getX(), (int)Forge_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(5);

				controller.mouseMove((int)Anvil_loc.getX(), (int)Anvil_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(31) + 20);
				Click();
				TimeUnit.SECONDS.sleep(90);	//more than 0 heat but less than 3/4 -- minimise loss due to timing
				progress += progressPerCycle;
			}
			progress = 0;
			started = false;
			Drag();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Drag() {
		try {
			controller.mouseMove((int)first.getX(), (int)first.getY());
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
			Click();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
			controller.mouseMove((int)next.getX(), (int)next.getY());
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
			Click();
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void Click() {
		if(mousePressed) {
			controller.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			mousePressed = false;
		} else {
			controller.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			mousePressed = true;
		}
	}

	private static void Space() {
		if(spacePressed) {
			controller.keyRelease(KeyEvent.VK_SPACE);
			spacePressed = false;
		} else {
			controller.keyPress(KeyEvent.VK_SPACE);
			spacePressed = true;
		}
	}

	private static boolean ceremonialPrompt() {
		screen = camera.getImage();
		if(compare.isOnScreen(screen, prompt, begin, end)) {
			try {
				controller.mouseMove((int)secondSlot.getX(), (int)secondSlot.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				controller.mouseMove((int)MasterworkTab_loc.getX(), (int)MasterworkTab_loc.getY());
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(501) + 250);
				Click();
				return true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static int Rand(int min, int max) {
		int range = max - min;
		int random = 17%range;//get random number
		random += min;
		return random;
	}

}