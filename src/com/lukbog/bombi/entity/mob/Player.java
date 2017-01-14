package com.lukbog.bombi.entity.mob;

import com.lukbog.bombi.Screen;
import com.lukbog.bombi.entity.Bombs.Bombs;
import com.lukbog.bombi.entity.Explosion.Explosion;
import com.lukbog.bombi.graphics.Sprite;
import com.lukbog.bombi.input.Keyboard;
import com.lukbog.bombi.level.Level;

public class Player extends Mob
{
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0 ;
	private boolean walking = false;
	private int flip = 1;
	private int playerBombs = 1;
	
	public Player(int x, int y, Keyboard input, Level level)
	{
		this.input = input;
		this.level = level;
		sprite = Sprite.player_walking_side_1;
		this.x = x ;
		this.y = y ;
		this.input = input;
	}
	
	public void update()
	{
		int dx = 0, dy = 0;
		if (input.up) dy --;
		if (input.down) dy ++;
		if (input.right) dx ++;
		if (input.left) dx --;
		if (input.tnt) 
			{
				if (playerBombs > 0 && !checkSpot())
				{
					System.out.println("BombaTu");
					plant(this.x, this.y);
					playerBombs --;
			
				}
			}
		if (anim < 7500) anim++; else anim = 0;
		if (dx != 0 || dy != 0) 
			{
				move(dx, dy);
				walking = true;
			}
		else 
		{
			walking = false;
		}
		clear();
	}
	
	protected void plant(int x, int y)
	{
		Bombs b = new Bombs(x, y, level, 10);
		//Explosion b = new Explosion(x, y, 3, Sprite.explosion_1, level);
		level.addBomb(b);
		/*if (bombs.size() < 1)
		{	
			if (bombs.size() == 0)
			{
				//timer.start();
				Bombs b = new Bombs(x, y, level, 10);
				//Bombs b = new Explosion(x, y, dir);
				System.out.println("Dodalem nowa bombke po raz pierwszy");
				bombPlanted = true;
				bombs.add(b);
				level.add(b);
				
			}
			for (int i = 0; i < bombs.size() ; i++)
			{
					if (bombs.get(i).x== x && bombs.get(i).y == y) break;
			}}*/
	}
	private void clear() {
		for(int i = 0; i < Level.bomb.size(); i++) {
			if(Level.bomb.get(i).removed) {
				Level.bomb.remove(i);
				playerBombs++;
			}
			}
		for(int i = 0; i < Level.explosion.size(); i++) {
			if(Level.explosion.get(i).removed) {
				Level.explosion.remove(i);
			}
		}
		
		
	}
	private boolean checkSpot()
	//Do poprawy bo jak sie ruszasz to wywala ze juz nie koliduje a powinno.
	// X trzeba zmienic na x bomby ktora chcemy postawic a y na y bomby ktora chcemy postawic
	// A nie gracza tak jak teraz.
	{
		for (int i = 0; i < Level.bomb.size() ; i++)
		{
				if (Level.bomb.get(i).x == x && Level.bomb.get(i).y == y) return true;
		}
		return false;
	}
	
	public void render(Screen screen)
	{
		//int flip = 0;
		if(walking){
			if (dir == left) 
				{
					flip = 0;
					sprite = Sprite.player_walking_side_1;
					
					if (anim % 40 > 10 ) 
					{
						 sprite = Sprite.player_walking_side_2;
					} 
					if (anim % 40 > 20)
					{
						sprite = Sprite.player_walking_side_3;
					}
					if (anim % 40 > 30)
					{
						sprite = Sprite.player_walking_side_4;
					
					}
				}
			if (dir == up) 
			{
					sprite = Sprite.player_walking_up_1;
					
					if (anim % 20 > 10)
					{
						sprite = Sprite.player_walking_up_2;
					}
					else 
					{
						sprite = Sprite.player_walking_up_1;
					}
			}
			if (dir == down) 
			{
					sprite = Sprite.player_walking_down_1;
					
					if (anim % 20 > 10)
					{
						sprite = Sprite.player_walking_down_2;
					}
					else 
					{
						sprite = Sprite.player_walking_down_1;
					}
			}
			if (dir == right) 
			{
				flip = 1;
				sprite = Sprite.player_walking_side_1;
				if (anim % 40 > 10 ) 
				{
					 sprite = Sprite.player_walking_side_2;
				} 
				if (anim % 40 > 20)
				{
					sprite = Sprite.player_walking_side_3;
				}
				if (anim % 40 > 30)
				{
					sprite = Sprite.player_walking_side_4;
				}
			}
		}
		else{
			sprite = Sprite.player_idle_1;
			if (anim % 40 > 10 ) 
			{
				 sprite = Sprite.player_idle_2;
			} 
			if (anim % 40 > 20)
			{
				sprite = Sprite.player_idle_3;
			}
			if (anim % 40 > 30)
			{
				sprite = Sprite.player_idle_4;
			}
		}
			
		screen.renderPlayer(x, y, sprite, flip);
	}

	
} 
