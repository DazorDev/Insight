package dazor.framework.util;

import java.awt.Dimension;
import java.awt.Toolkit;

public class JFrameHelper {
	
	/** 
	 * Helper function to get the Size of the current screen
	 * @return
	 */
	public static Dimension getScreenSize() {
		return Toolkit.getDefaultToolkit().getScreenSize();
	}
	
}
