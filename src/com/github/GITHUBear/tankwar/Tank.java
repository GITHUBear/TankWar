package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Tank{
	private int directions;
	private Image dirImages;
	protected int speed=5;
	public int local_x;
	public int local_y;
	public boolean isMove;
	protected TankwarFrame t;
	
	public Tank(String path, int location_x, int location_y,int directions,TankwarFrame t) {
		this.dirImages=GameUtil.getImage(path);
		this.local_x=location_x;
		this.local_y=location_y;
		this.directions=directions;
		this.isMove=false;
		this.t=t;
	}
	
	protected void setImage(){
		switch(this.directions){
			case 1:
				this.dirImages=GameUtil.getImage("Images/tankU0.png");
				break;
			case 2:
				this.dirImages=GameUtil.getImage("Images/tankR0.png");
				break;
			case 3:
				this.dirImages=GameUtil.getImage("Images/tankD0.png");
				break;
			case 4:
				this.dirImages=GameUtil.getImage("Images/tankL0.png");
				break;
		}
	}
	
	public void setDir(int directions){
		this.directions=directions;
		setImage();
	}
	
	public int getDir(){
		return directions;
	}
	
	public void setSpeed(int speed){
		this.speed=speed;
	}
	
	public void drawPic(Graphics g){
		g.drawImage(dirImages,local_x,local_y,null);
	}
	
	protected boolean notCrash(){
		Rectangle tankRect=getRect();
		for(int i=0;i<t.gameMap.listBrick.size();i++){
			if(tankRect.intersects(t.gameMap.listBrick.get(i).getRect())){
				return false;
			}
		}
		return true;
	}
	
	
	public void move(){
			if(isMove&&notCrash()){
				switch(directions){
				case 1:
					if(local_y-speed>25)
						local_y-=speed;
					break;
				case 2:
					if(local_x+speed<450)
						local_x+=speed;
					break;
				case 3:
					if(local_y+speed<450)
						local_y+=speed;
					break;
				case 4:
					if(local_x-speed>0)
						local_x-=speed;
					break;
				}
			}
	}
	
	public Rectangle getRect(){
		switch(directions){
		case 1:
			return new Rectangle(this.local_x,this.local_y-speed,50,50);
		case 2:
			return new Rectangle(this.local_x+speed,this.local_y,50,50);
		case 3:
			return new Rectangle(this.local_x,this.local_y+speed,50,50);
		case 4:
			return new Rectangle(this.local_x-speed,this.local_y,50,50);
		default:
			return null;
		}
	}
		
	
}
