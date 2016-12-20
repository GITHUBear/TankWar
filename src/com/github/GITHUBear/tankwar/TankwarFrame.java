package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankwarFrame extends MyFrame {
	
	//private boolean showBang=false;
	public Tank tank = new Tank("Images/tankU0.png", 100, 300, 1, this);
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	public map gameMap=new map();
	public List<BigBang> bigBangs=new ArrayList<BigBang>();
	public Brick temp=null;
	public EnemyTank et=null;
	public List<EnemyTank> eTList=new ArrayList<EnemyTank>();
	
	public void paint(Graphics g) {
		for(int i=0;i<gameMap.listBrick.size();i++)
			gameMap.listBrick.get(i).drawBrick(g);
		tank.drawPic(g);
		tank.move();
		for(int i=0;i<eTList.size();i++){
			eTList.get(i).drawPic(g);
			eTList.get(i).move();
		}
		//System.out.println(tank.local_x+" "+tank.local_y);
		//System.out.println(etank.local_x+" "+etank.local_y);
		if (!bullets.isEmpty()) {
			for(int i=0;i<bullets.size();i++){
				bullets.get(i).drawBullet(g);
				bullets.get(i).move();
				if((temp=bullets.get(i).ifDestroy())!=null){
					//showBang=true;
					bigBangs.add(new BigBang(this,temp));
					//bb.isFinish=false;
				}
				if((et=bullets.get(i).ifeTankDestroy())!=null){
					bigBangs.add(new BigBang(this,et));
				}
			}
		}
		//if(showBang){
			for(int i=0;i<bigBangs.size();i++){
				if(!bigBangs.get(i).isFinish)
					bigBangs.get(i).show(g);
				else{
					bigBangs.remove(i);
					i--;
				}
			}
			//bb.show(g);
		//}
		//if(bb.isFinish){
			//showBang=false;
		//}
	}

	public void launch() {
		super.launch();
		gameIni();
		this.addKeyListener(new keyMonitor());
	}

	public void gameIni(){
		gameMap.mapIni();
		eTList.add(new EnemyTank("Images/tankD.png",350,100,3,this));
		eTList.add(new EnemyTank("Images/tankL.png",200,200,4,this));
	}
	
	public static void main(String[] args) {
		TankwarFrame tankwarFrame = new TankwarFrame();
		tankwarFrame.launch();
	}

	class keyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			System.out.println(arg0.getKeyCode()+"");
			switch (arg0.getKeyCode()) {
			case 87:
				tank.isMove = true;
				tank.setDir(1);
				break;
			case 68:
				tank.isMove = true;
				tank.setDir(2);
				break;
			case 83:
				tank.isMove = true;
				tank.setDir(3);
				break;
			case 65:
				tank.isMove = true;
				tank.setDir(4);
				break;
			default:
				break;
			}
			if (arg0.getKeyCode() == 32 && !Bullet.isShot) {
				Bullet bullet = new Bullet(tank, TankwarFrame.this);
				bullets.add(bullet);
				Bullet.isShot = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			tank.isMove = false;
			Bullet.isShot = false;
		}

	}

}
