package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	Animation bot,  mUp , mDown , mLeft , mRight; //states of animation 
	Image worldMap;  //background
	boolean quit = false; //for game loop
	int[] duration = {200,200 }; //how long the animation will last 200ms,200ms
	float botPosX = 0;
	float botPosY = 0;
	float shiftX = botPosX + 320; //640 / 2
	float shiftY = botPosY + 160; //320 / 2
	
	
	public Play(int state){
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//worldMap = new Image("res/Sprites/background.png");
		worldMap = new Image("res/Sprites/grass.png");
		Image[] walkUp = {new Image("res/Sprites/back.png"),new Image("res/Sprites/back.png")}; // when bot is walking up show back.png
		Image[] walkDown = {new Image("res/Sprites/front.png"),new Image("res/Sprites/front.png")}; // when bot is walking up show back.png
		Image[] walkLeft = {new Image("res/Sprites/left.png"),new Image("res/Sprites/left.png")}; // when bot is walking up show back.png
		Image[] walkRight = {new Image("res/Sprites/right.png"),new Image("res/Sprites/right.png")}; // when bot is walking up show back.png
		
		mUp = new Animation(walkUp,duration,false); // params are series of images,duration,auto-update
		mDown = new Animation(walkDown,duration,false);
		mLeft = new Animation(walkLeft,duration,false);
		mRight = new Animation(walkRight,duration,false);
		bot = mDown; //Set the bot as moving down and then animate according to events
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		//Draw text/pics/stuff on the screen with Graphics g object as a paint brush  
		g.drawString("Get ready to Play!", 50, 50);
		worldMap.draw(botPosX,botPosY);
		bot.draw(shiftX,shiftY);
		
		if(quit==true){
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if(quit==false){
				g.clear();
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		//helps to give the illusion of an exception
		Input input = gc.getInput(); // get input from the user either mouse or keyboard its stored in the class Input
		
		if(input.isKeyDown(Input.KEY_UP)){
			bot = mUp;
			botPosY += delta *.1f; //increase the y pos of bot
			if(botPosY>160){       //collision detection
				botPosY -= delta *.1f; 
			}
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			bot = mDown;
			botPosY -= delta *.1f; //decrease the y pos of bot
			if(botPosY<-200){       //collision detection
				botPosY += delta *.1f; 
			}
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			bot = mLeft;
			botPosX += delta *.1f; //increase the x pos of bot
			if(botPosX>324){       //collision detection
				botPosX -= delta *.1f; 
			}
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			bot = mRight;
			botPosX -= delta *.1f; //decrease the x pos of bot
			if(botPosX<-280){       //collision detection
				botPosX += delta *.1f; 
			}
		}
		
		//escape
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			quit=true;
		}
		
		//when menu is up
		
		//resume
		if(input.isKeyDown(Input.KEY_R)){
			quit=false;
		}
		
		//quit
		if(input.isKeyDown(Input.KEY_M)){
			sbg.enterState(0);
		}
		
		//quit
		if(input.isKeyDown(Input.KEY_Q)){
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 1;
	}
	
}
