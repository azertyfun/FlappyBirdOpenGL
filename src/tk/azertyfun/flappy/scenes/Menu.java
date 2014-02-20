package tk.azertyfun.flappy.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Flappy;
import tk.azertyfun.flappy.State;
import tk.azertyfun.flappy.Util;

public class Menu implements Scene {
	protected Texture title;
	protected Texture hint;
	protected float birdDecal;
	protected float birdSpeed;
	
	public Menu() {
		title = Util.getTexture("title");
		hint = Util.getTexture("hint");
		birdDecal = 0;
		birdSpeed = 30;
	}
	
	@Override
	public void draw() {
		Flappy.background.draw();
		Flappy.ground.draw();
		Util.drawTexture(title, 45, 45, 256);
		Util.drawTexture(hint, 100, 200, 128); //The width is 39px
		
		//Drawing Flappy
		birdDecal += Util.getDelta() / birdSpeed;
		if(birdDecal >= 7 || birdDecal <= -7) {
			birdSpeed *= -1;
			if(birdDecal > 7)
				birdDecal = 7;
			if(birdDecal < -7)
				birdDecal = -7;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			Flappy.changeState(State.GAME);
		
		Flappy.bird.draw(100, 150 + birdDecal);
	}

	@Override
	public void reset() {
		// Nothing to do
	}
}
