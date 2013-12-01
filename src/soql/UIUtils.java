package soql;

import java.awt.Dimension;
import java.awt.Window;

public class UIUtils {

	public static void center(Window window){
		Dimension size = window.getToolkit().getScreenSize();
		Dimension windowSize = window.getSize();
		window.setLocation((size.width-windowSize.width)/2, (size.height-windowSize.height)/2);
	}
}
