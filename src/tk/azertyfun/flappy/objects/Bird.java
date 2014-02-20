package tk.azertyfun.flappy.objects;

import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Util;

public class Bird {
	protected Texture[] bird = {null, null, null, null};
	protected int frame;
	protected long lastFrameChange;
	
	public Bird() {
		bird[0] = Util.getTexture("flappy_0");
		bird[1] = Util.getTexture("flappy_1");
		bird[2] = Util.getTexture("flappy_2");
		bird[3] = bird[1];
		frame = 0;
		lastFrameChange = Util.getTime();
	}
	
	public void draw(float x, float y) {
		int delta = (int) (Util.getTime() - lastFrameChange);
		if(delta >= 100) {
			frame++;
			lastFrameChange += 100;
		}
		if(frame == 4)
			frame = 0;
		Util.drawTexture(bird[frame], x, y, 64); //Bird itself is 34px large
	}
}
