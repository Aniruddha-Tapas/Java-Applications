package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{
	
	Image playNow;
	Image exitGame;
	public String mousepos = "Mouse position";
	
	public Menu(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		playNow = new Image("res/Sprites/play.png");
		exitGame = new Image("res/Sprites/exit.png");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//Draw text/pics/stuff on the screen with Graphics g object as a paint brush  
		g.drawString("Welcome to BlasterCity!", 100, 50);
		//g.drawString(mousepos, 100, 100);
		playNow.draw(100,120);
		exitGame.draw(300,120);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//helps to give the illusion of an exception
		Input input = gc.getInput(); // get input from the user either mouse or keyboard its stored in the class Input
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mousepos = xpos + ","  + ypos ;
		
		//play button
		if((xpos>100)&&(xpos<203) && (ypos>140)&&(ypos<240)){
			//whenever mouse cursor is inside the play button
			if(input.isMouseButtonDown(0)){ //isMouseButtonDown(0) 0 -> left click, 1 -> right click
				sbg.enterState(1);
				
			}
		}
		//exit button
		if((xpos>300)&&(xpos<403) && (ypos>140)&&(ypos<240)){
			//whenever mouse cursor is inside the exit button
			if(input.isMouseButtonDown(0)){ //isMouseButtonDown(0) 0 -> left click, 1 -> right click
				System.exit(0);
				 
					
			}
		}
		
	}

	@Override
	public int getID() {
		return 0;
	}
	
}
