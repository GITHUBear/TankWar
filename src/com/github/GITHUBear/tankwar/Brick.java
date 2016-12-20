package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Brick {
	private Image image;
	public int local_x;
	public int local_y;
	public boolean isDestroy;
	
	public Brick(String path,int x,int y,boolean isDestroy){
		this.local_x=x;
		this.local_y=y;
		this.isDestroy=false;
		this.image=GameUtil.getImage(path);
	}
	
	public void drawBrick(Graphics g){
		g.drawImage(image, local_x, local_y, null);
	}
	
	public Rectangle getRect(){
		return new Rectangle(this.local_x,this.local_y,25,25);
	}
	
}
