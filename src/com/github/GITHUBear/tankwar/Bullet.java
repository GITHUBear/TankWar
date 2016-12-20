package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Bullet {
	private int directions;
	private Image dirImage;
	private int speed = 10;
	private int local_x;
	private int local_y;
	public static boolean isShot = false;
	private TankwarFrame t;

	public Bullet(Tank tank, TankwarFrame t) {
		switch (tank.getDir()) {
		case 1:
			this.directions = 1;
			this.dirImage = GameUtil.getImage("Images/missileU_f.gif");
			this.local_x = tank.local_x + 19;
			this.local_y = tank.local_y - 12;
			this.t = t;
			break;
		case 2:
			this.directions = 2;
			this.dirImage = GameUtil.getImage("Images/missileR_f.gif");
			this.local_x = tank.local_x + 50;
			this.local_y = tank.local_y + 19;
			this.t = t;
			break;
		case 3:
			this.directions = 3;
			this.dirImage = GameUtil.getImage("Images/missileD_f.gif");
			this.local_x = tank.local_x + 19;
			this.local_y = tank.local_y + 50;
			this.t = t;
			break;
		case 4:
			this.directions = 4;
			this.dirImage = GameUtil.getImage("Images/missileL_f.gif");
			this.local_x = tank.local_x - 12;
			this.local_y = tank.local_y + 19;
			this.t = t;
			break;
		}
	}
	
	public Bullet(EnemyTank etank, TankwarFrame t) {
		switch (etank.direction) {
		case 1:
			this.directions = 1;
			this.dirImage = GameUtil.getImage("Images/missileU_f.gif");
			this.local_x = etank.local_x + 19;
			this.local_y = etank.local_y - 12;
			this.t = t;
			break;
		case 2:
			this.directions = 2;
			this.dirImage = GameUtil.getImage("Images/missileR_f.gif");
			this.local_x = etank.local_x + 50;
			this.local_y = etank.local_y + 19;
			this.t = t;
			break;
		case 3:
			this.directions = 3;
			this.dirImage = GameUtil.getImage("Images/missileD_f.gif");
			this.local_x = etank.local_x + 19;
			this.local_y = etank.local_y + 50;
			this.t = t;
			break;
		case 4:
			this.directions = 4;
			this.dirImage = GameUtil.getImage("Images/missileL_f.gif");
			this.local_x = etank.local_x - 12;
			this.local_y = etank.local_y + 19;
			this.t = t;
			break;
		}
	}

	public void move() {
		switch (directions) {
		case 1:
			local_y -= speed;
			break;
		case 2:
			local_x += speed;
			break;
		case 3:
			local_y += speed;
			break;
		case 4:
			local_x -= speed;
			break;
		}
	}

	public EnemyTank ifeTankDestroy(){
		if (TankwarFrame.bullets.size() > 0){
			for (int i = 0; i < t.eTList.size(); i++) {
				if(this.getRect().intersects(new Rectangle(t.eTList.get(i).local_x,t.eTList.get(i).local_y,50,50))){
					TankwarFrame.bullets.remove(0);
					return t.eTList.get(i);
				}
			}
		}
		return null;
	}
	
	public Brick ifDestroy() {
		if (TankwarFrame.bullets.size() > 0) {
			for (int i = 0; i < t.gameMap.listBrick.size(); i++) {
				if (this.local_x > 520 || this.local_x < -20 || this.local_y < -20 || this.local_y > 500) {
					TankwarFrame.bullets.remove(0);
					return null;
				}
				if(this.getRect().intersects(t.gameMap.listBrick.get(i).getRect())){
					TankwarFrame.bullets.remove(0);
					return t.gameMap.listBrick.get(i);
				}
			}
		}
		return null;
	}
	
	
	
	public void drawBullet(Graphics g) {
		g.drawImage(dirImage, local_x, local_y, null);
	}

	public Rectangle getRect() {
		return new Rectangle(this.local_x, this.local_y, 12, 12);
	}

}
