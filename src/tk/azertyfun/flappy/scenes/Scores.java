package tk.azertyfun.flappy.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Flappy;
import tk.azertyfun.flappy.State;
import tk.azertyfun.flappy.Util;

public class Scores implements Scene {
	
	protected Texture popup_scores; //226*116px
	protected Texture gameOver; //188*38px
	protected Texture[] medals = {null, null, null, null};
	
	public Scores() {
		popup_scores = Util.getTexture("popup_scores");
		gameOver = Util.getTexture("gameOver");
		medals[0] = Util.getTexture("medal_bronze");
		medals[1] = Util.getTexture("medal_silver");
		medals[2] = Util.getTexture("medal_gold");
		medals[3] = Util.getTexture("medal_platinium");
	}
	
	@Override
	public void draw() {
		Flappy.background.draw();
		Flappy.ground.draw();
		
		Util.drawTexture(gameOver, 45, 50, 256);
		Util.drawTexture(popup_scores, 28, 150, 256);
		Util.drawNumber(Flappy.score, 196, 184, true); //160 + 28, 34 + 150
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
			Flappy.changeState(State.MENU);
	}

	@Override
	public void reset() {
		// Nothing to do right now
	}

}
