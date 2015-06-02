package utilities;

import java.awt.Dimension;

public class Constants {
	public static final int FRAME_HEIGHT = 768;
	public static final int FRAME_WIDTH = 980;
	public static final Dimension FRAME_SIZE = new Dimension(
			Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);	
	// sleep time between two frames in milliseconds
		public static final int DELAY = 20;
		// sleep time between two frames in seconds
		public static final double DT = DELAY / 1000.0;
}