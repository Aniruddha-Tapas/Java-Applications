package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame{  //extends StateBasedGame to make a StateBasedGame
	
	public static final String gamename = "Blaster City!";
	public static final int menu = 0;
	public static final int play = 1;
	
	public Game(String gamename){
		super(gamename);  //Add title
		this.addState(new Menu(menu));  //set states
		this.addState(new Play(play));
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//Game container is responsible for game loop, FPS, game attributes
		//initStatesList has all states in the game
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this); //initialized two states
		this.enterState(menu); //which is the first screen to be shown(launcher)
	
		
	}
	
	
	public static void main(String[] args) {
		//create the main window
		AppGameContainer appgc; //window where the game is
		try{
			appgc = new AppGameContainer(new Game(gamename)); //create a window to hold the game
			appgc.setDisplayMode(640, 360, false); //game size , full screen = ? -> false
			appgc.start();
		}catch(SlickException e){ //Slicks custom error handling
			e.printStackTrace();
		}
		
	}



}
