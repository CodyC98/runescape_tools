import java.awt.image.MultiResolutionImage;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Robot;

public class ImageTaker{

	private static Robot bot;

	ImageTaker() {
		try {
			bot = new Robot();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage getImage() {
		BufferedImage image = null;
		try {
			Image temp = new Robot().createMultiResolutionScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize())).getResolutionVariant(3840, 2160);
			image = toBufferedImage(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	private static BufferedImage toBufferedImage(Image image) {
		if(image instanceof BufferedImage) {
			return (BufferedImage) image;
		}

		BufferedImage bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D draw = bimage.createGraphics();
		draw.drawImage(image, 0, 0, null);
		draw.dispose();

		return bimage;
	}
}