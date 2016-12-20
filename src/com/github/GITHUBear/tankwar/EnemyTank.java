package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class EnemyTank {

	private int target_x;
	private int target_y;
	public int local_x;
	public int local_y;
	public int direction;
	private Image eImage;
	private int speed = 5;
	private TankwarFrame t;
	private int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private List<Point> openedList = new ArrayList<Point>();
	private List<Point> closedList = new ArrayList<Point>();
	private Stack<Point> guide = new Stack<Point>();
	private int shootNum=15;

	class Point {
		int x;
		int y;
		int F;
		int G;
		int H;
		Point fatherPoint;

		public Point(int x, int y, Point p) {
			this.x = x;
			this.y = y;
			this.fatherPoint = p;
			iniFGH();
		}

		public boolean equal(int a, int b) {
			if (this.x == a && this.y == b)
				return true;
			else
				return false;
		}

		public void iniFGH() {
			if (fatherPoint == null) {
				this.G = 0;
			} else
				this.G = fatherPoint.G + 10;
			this.H = Math.abs(x - EnemyTank.this.target_x) + Math.abs(y - EnemyTank.this.target_y);
			this.F = this.G + this.H;
		}

		public void setFatherPoint(Point p) {
			this.fatherPoint = p;
			iniFGH();
		}

		public void setG(int G) {
			this.G = G;
			this.F = this.G + this.H;
		}

	}

	public EnemyTank(String path, int location_x, int location_y,int direction, TankwarFrame t) {
		this.eImage = GameUtil.getImage(path);
		this.t = t;
		this.target_x = t.tank.local_x;
		this.target_y = t.tank.local_y;
		this.local_x = location_x;
		this.local_y = location_y;
		this.direction=direction;
	}

	public void drawPic(Graphics g) {
		g.drawImage(eImage, local_x, local_y, null);
	}

	public void move() {
		Point target = null;
		target_x = t.tank.local_x;
		target_y = t.tank.local_y;
		if (guide.isEmpty()) {
			openedList.clear();
			closedList.clear();
			openedList.add(new Point(this.local_x,this.local_y,null));
			CalcuAct();
			target = closedList.get(checkInClosed(target_x, target_y));
			do {
				guide.push(target);
				target = target.fatherPoint;
			} while (target != null);
		}
		if (!guide.isEmpty()) {
			target = guide.pop();
			setImage(local_x, local_y, target);
			this.local_x = target.x;
			this.local_y = target.y;
			shoot();
		}
	}

	private void shoot(){
		if(Math.abs(local_y-target_y)==0){
			if(local_x>=target_x){
				eImage=GameUtil.getImage("Images/tankL.png");
				direction=4;
				if(shootNum==10){
					t.bullets.add(new Bullet(this,this.t));
				}
				shootNum--;
				if(shootNum==0)
					shootNum=10;
			}else{
				eImage=GameUtil.getImage("Images/tankR.png");
				direction=2;
				if(shootNum==10){
					t.bullets.add(new Bullet(this,this.t));
				}
				shootNum--;
				if(shootNum==0)
					shootNum=10;
			}
			return;
		}
		if(Math.abs(local_x-target_x)==0){
			if(local_y>=target_y){
				eImage=GameUtil.getImage("Images/tankU.png");
				direction=1;
				if(shootNum==10){
					t.bullets.add(new Bullet(this,this.t));
				}
				shootNum--;
				if(shootNum==0)
					shootNum=10;
			}else{
				eImage=GameUtil.getImage("Images/tankD.png");
				direction=3;
				if(shootNum==10){
					t.bullets.add(new Bullet(this,this.t));
				}
				shootNum--;
				if(shootNum==0)
					shootNum=10;
			}
			return;
		}
	}
	
	private void setImage(int x, int y, Point next) {
		if (next.x > x) {
			eImage = GameUtil.getImage("Images/tankR.png");
			direction=2;
			return;
		}
		if (next.x < x) {
			eImage = GameUtil.getImage("Images/tankL.png");
			direction=4;
			return;
		}
		if (next.y > y) {
			eImage = GameUtil.getImage("Images/tankD.png");
			direction=3;
			return;
		}
		if (next.y < y) {
			eImage = GameUtil.getImage("Images/tankU.png");
			direction=1;
			return;
		}
	}

	private int checkInClosed(int a, int b) {
		for (int i = 0; i < closedList.size(); i++) {
			if (closedList.get(i).equal(a, b))
				return i;
		}
		return -1;
	}

	private int checkInOpened(int a, int b) {
		for (int i = 0; i < openedList.size(); i++) {
			if (openedList.get(i).equal(a, b))
				return i;
		}
		return -1;
	}

	private void CalcuAct() {
		Point p = null;
		int a = 0;
		while (checkInClosed(target_x, target_y) == -1) {
			p = openedList.get(0);
			closedList.add(p);
			openedList.remove(p);
			for (int i = 0; i < 4; i++) {
				if (notCrash(new Rectangle(p.x + dir[i][0] * speed, p.y + dir[i][1] * speed, 50, 50))
						&& checkInClosed(p.x + dir[i][0] * speed, p.y + dir[i][1] * speed) == -1
						&& p.x + dir[i][0] * speed > 0 && p.x + dir[i][0] * speed < 450 && p.y + dir[i][1] * speed > 25
						&& p.y + dir[i][1] * speed < 450) {
					if ((a = checkInOpened(p.x + dir[i][0] * speed, p.y + dir[i][1] * speed)) == -1) {
						openedList.add(new Point(p.x + dir[i][0] * speed, p.y + dir[i][1] * speed, p));
					} else {
						if (p.G + 10 < openedList.get(a).G) {
							openedList.get(a).setFatherPoint(p);
						}
					}
				}
			}
			Collections.sort(openedList, new Comparator<Point>() {

				@Override
				public int compare(Point arg0, Point arg1) {
					return arg0.F - arg1.F;
				}

			});
		}
	}

	private boolean notCrash(Rectangle rec) {
		for (int i = 0; i < t.gameMap.listBrick.size(); i++) {
			if (rec.intersects(t.gameMap.listBrick.get(i).getRect())) {
				return false;
			}
		}
		return true;
	}

}
