package tk.azertyfun.flappy;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.lwjgl.Sys;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Util {
	public static Texture numbers_l[] = {getTexture("numbers_l/0"), getTexture("numbers_l/1"), getTexture("numbers_l/2"), getTexture("numbers_l/3"), getTexture("numbers_l/4"), getTexture("numbers_l/5"), getTexture("numbers_l/6"), getTexture("numbers_l/7"), getTexture("numbers_l/8"), getTexture("numbers_l/9")};
	public static Texture numbers_h[] = {getTexture("numbers_h/0"), getTexture("numbers_h/1"), getTexture("numbers_h/2"), getTexture("numbers_h/3"), getTexture("numbers_h/4"), getTexture("numbers_h/5"), getTexture("numbers_h/6"), getTexture("numbers_h/7"), getTexture("numbers_h/8"), getTexture("numbers_h/9")};
	public static Texture getTexture(String path) {
		try {
			Texture text = TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + path + ".png")));
			System.out.println("Loaded texture " + path + ". ID : " + text.getTextureID());
			return text;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void drawTexture(Texture text, float x, float y, float size) {
		glBindTexture(GL_TEXTURE_2D, text.getTextureID());
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			
			glTexCoord2f(1, 0);
			glVertex2f(x + size, y);
			
			glTexCoord2f(1, 1);
			glVertex2f(x + size, y + size);
			
			glTexCoord2f(0, 1);
			glVertex2f(x, y + size);
		glEnd(); 
		
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	public static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public static double getDelta() {
		long currentTime = getTime();
		double delta = (double) currentTime - (double) Flappy.lastFrame;
		return delta;
	}
	
	public static void drawNumber(int number, float x, float y, boolean large) {
		//Transforming into single digits
		List<Integer> digits = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		
		if(number == 0)
			digits.add(0);
		
		while (number > 0) {
			stack.push(number % 10);
			number /= 10;
		}
		
		while (!stack.isEmpty()) {
			digits.add(stack.pop());
		}
		
		int mult = large ? 16 : 14;
		
		for(int i = 0; i < digits.size(); i++)
			drawTexture(large ? numbers_h[digits.get(i)] : numbers_l[digits.get(i)], i*mult + x, y, large ? (float) 32 : (float) 16);
	}
	
	public static int randomize(int lower, int higher) {
		return (int) (Math.random() * (higher-lower)) + lower;
	}
}
