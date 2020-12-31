package test;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.net.URL;
 
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
 
public class JLabelDemo {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Demo");
		try {
			URL picURL = JLabelDemo.class.getResource("backImg.jpg");
			String picPath = JLabelDemo.class.getResource("/studentSys02/src/images/pexels-elina-krima-3377414.jpg")
					.getFile();// 将getFile改为getPath亦可
			String picPathone = JLabelDemo.class.getResource("/studentSys02/src/images/pexels-elina-krima-3377414.jpg")
					.getPath();
			final Image image = ImageIO.read(new File(picPath));
 
			JDesktopPane DESKTOP_PANE = new JDesktopPane() {
				public void paint(Graphics g) {
					// 改为protected void paintComponent(Graphics g) { 亦可
					g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
				};
			};
			frame.getContentPane().add(DESKTOP_PANE);
		} catch (Exception e) {
		}
		System.out.println(new File(".").getAbsolutePath());
		System.out.println(new File(".").getPath());
		frame.setSize(800, 600);
		frame.setVisible(true);
	}
}