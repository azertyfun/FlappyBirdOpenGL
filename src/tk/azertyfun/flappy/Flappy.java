package tk.azertyfun.flappy;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import tk.azertyfun.flappy.objects.Background;
import tk.azertyfun.flappy.objects.Bird;
import tk.azertyfun.flappy.objects.Ground;
import tk.azertyfun.flappy.scenes.Game;
import tk.azertyfun.flappy.scenes.Menu;
import tk.azertyfun.flappy.scenes.Scores;

public class Flappy {
	
	//Scenes
	private static Menu menu;
	private static Game game;
	private static Scores scores;
	
	//Objects
	public static Background background;
	public static Ground ground;
	public static Bird bird;
	
	//Time stuff
	public static long lastFrame;
	public static long lastStateChange; //Don't want to change state too fast because of spacebar already down...
	
	public static int score;
	private static State state;
	
	public static void main(String[] args) {
		try {
			Display.setDisplayMode(new DisplayMode(282, 512));
			Display.setTitle("Flappy bird - Java - OpenGL 1.1");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		state = State.MENU;
		lastStateChange = Util.getTime();
		
		//Enabling textures and transparency
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		//Initializing objects
		background = new Background();
		ground = new Ground();
		bird = new Bird();
		
		//Initializing time
		lastFrame = Util.getTime();
		
		//Initializing scenes
		menu = new Menu();
		game = new Game();
		scores = new Scores();
		
		while(!Display.isCloseRequested()) {
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, 282, 512, 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			glClear(GL_COLOR_BUFFER_BIT);
			
			switch(state) {
				case MENU:
					menu.draw();
					break;
				case GAME:
					game.draw();
					break;
				case SCORES:
					scores.draw();
					break;
			}
			
			//Time stuff
			lastFrame = Util.getTime();
			
			Display.update();
			Display.sync(9999999);
		}
		
		Display.destroy();
	}
	
	@SuppressWarnings("incomplete-switch")
	public static void changeState(State state) {
		if(Util.getTime() - lastStateChange <= 500)
			return;
		else
			lastStateChange = Util.getTime();
		switch(state) {
		case GAME:
			game.reset();
			break;
		}
		Flappy.state = state;
	}
}
