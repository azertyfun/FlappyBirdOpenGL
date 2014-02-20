package tk.azertyfun.flappy.objects;

import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Util;

public class Ground {
	protected Texture ground;
	protected int decal;
	
	public Ground() {
		ground = Util.getTexture("ground");
		decal = 0;
	}
	
	public void draw() {
		decal += 1 * Util.getDelta() / 8;
		if(decal >= 28)
			decal = 0;
		Util.drawTexture(ground, -decal, 402, 512); //Original is 308*110px
	}
}
