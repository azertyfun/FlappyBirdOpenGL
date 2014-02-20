package tk.azertyfun.flappy.scenes;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import tk.azertyfun.flappy.Flappy;
import tk.azertyfun.flappy.State;
import tk.azertyfun.flappy.Util;

public class Game implements Scene {
	
	protected Texture pipe_up; //26*135px
	protected Texture pipe_down; //26*121px
	protected float[][] pipes;
	
	protected float y;
	protected float dy;
	protected double delta;
	protected long tLastPipe;
	protected int lastPipe;
	
	public Game() {
		pipe_up = Util.getTexture("pipe_up");
		pipe_down = Util.getTexture("pipe_down");
		dy = 0;
		y = 0;
		pipes = new float[4][2];
	}
	
	@Override
	public void draw() {
		Flappy.background.draw();
		
		delta = Util.getDelta();
		
		if(Util.getTime() - tLastPipe >= 1700) {
			int pipeLength = Util.randomize(50, 200);
			pipes[lastPipe][0] = 282;
			pipes[lastPipe][1] = 0 - pipeLength;
			pipes[lastPipe + 1][0] = 282;
			pipes[lastPipe + 1][1] =  370 - pipeLength;
			
			if(lastPipe == 0)
				lastPipe = 2;
			else
				lastPipe = 0;
			
			tLastPipe = Util.getTime();
			Flappy.score++;
		}
		
		for(int i = 0; i < 4; i++) {
			pipes[i][0] -= (float) delta / 8.0f;
			if(i % 2 != 0)
				Util.drawTexture(pipe_down, pipes[i][0], pipes[i][1], 512);
			else
				Util.drawTexture(pipe_up, pipes[i][0], pipes[i][1], 512);
		}
		
		Flappy.ground.draw();
		Flappy.bird.draw(100, y);
		
		//Drawing score
		int digits = 0;
		int score = Flappy.score;
		while(score > 0) {
			digits++;
			score /= 10;
		}
		Util.drawNumber(Flappy.score, 141 - (digits * 16), 100, true);
		
		dy += delta / 40;
		y += dy * delta / 40;
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && y >= 4.5f)
			dy = -9f;
		
		//Checking hitboxes and stuff
		
		if(y >= 378) //Ground is at 402, minus bird size (24 px)
			Flappy.changeState(State.SCORES);
		
		for(int i = 0; i < 4; i += 2) {
			if(pipes[i][0] <= 134 && pipes[i][0] + 26 >= 100) //If pipe x pos <= right side of bird and right side of the pipe >= bird pos
				if(y <= (pipes[i][1] + 270) || y + 24 >= pipes[i + 1][1]) //If bird head is above upper pipe or bird's legs under lower pipe
					Flappy.changeState(State.SCORES);
		}
	}

	@Override
	public void reset() {
		y = 0;
		dy = 0;
		Flappy.score = -1;
		
		tLastPipe = Util.getTime();
		lastPipe = 0;
		for(int i = 0; i < 4; i++) {
			pipes[i][0] = -500;
			pipes[i][1] = -500;
		}
	}
	
}
