package test;

import javax.swing.UIManager;

public class UIStyles {
	public static void main(String[] args) {
		UIManager.LookAndFeelInfo[] info = UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo tem:info) {
			System.out.println(tem.getClassName());
		}
	}

}
