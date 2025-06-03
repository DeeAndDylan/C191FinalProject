package cs191;

import javax.swing.*;
import java.awt.*;

/**
 * Lead Author(s):
 * 
 * @author Dawit Eshete
 * @author Dylan Hancock
 *
 * Version Date: 6/2/2025
 * 
 * Responsibilities of Class: To add a background to the GUI.
 */
public class Background extends JPanel // Background is-a JPanel
{
	private Image backgroundImage; // Background has-a Image

	/**
	 * Purpose: Constructor that sets the background image to the variable and also sets the
	 * layout for the background
	 * 
	 * @param backgroundImage
	 */
	public Background(Image backgroundImage) 
    {
		this.backgroundImage = backgroundImage;
		setLayout(new BorderLayout());
	}

	/**
	 * Purpose: Protected override method that paints the background image onto the GUI. 
	 */
	@Override
	protected void paintComponent(Graphics g) 
    {
		super.paintComponent(g);
		if (backgroundImage != null) 
        {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
