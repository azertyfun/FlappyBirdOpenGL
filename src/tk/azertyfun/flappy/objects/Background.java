package tk.azertyfun.flappy.objects;

import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Util;

public class Background {
	protected Texture background;
	
	public Background() {
		background = Util.getTexture("background");
	}
	
	public void draw() {
		Util.drawTexture(background, 0, 0, 512);
	}
}
