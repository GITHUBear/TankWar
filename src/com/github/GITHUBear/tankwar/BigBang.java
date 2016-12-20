package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class BigBang {
	
	private List<Image> list = new ArrayList<Image>();
	private int showNum;
	public boolean isFinish;
	private TankwarFrame t;
	private Brick whichBrick;
	private EnemyTank et;
	private int x;
	private int y;
	
	public BigBang(TankwarFrame t,Brick b){
		for(int i=0;i<=8;i++)
			list.add(GameUtil.getImage("Images/"+i+".gif"));
		this.whichBrick=b;
		this.x=t.temp.local_x;
		this.y=t.temp.local_y;
		this.showNum=0;
		this.isFinish=false;
		this.t=t;
	}
	
	public BigBang(TankwarFrame t,EnemyTank et){
		for(int i=0;i<=8;i++)
			list.add(GameUtil.getImage("Images/"+i+".gif"));
		this.et=et;
		this.x=t.et.local_x;
		this.y=t.et.local_y;
		this.showNum=0;
		this.isFinish=false;
		this.t=t;
	}
	
	public void show(Graphics g){
		if(showNum==9){
			if(whichBrick!=null){
				t.gameMap.listBrick.remove(whichBrick);
			}
			if(et!=null){
				t.eTList.remove(et);
			}
			showNum=0;
			isFinish=true;
		}
		if(!isFinish){
			g.drawImage(list.get(showNum),x,y, null);
			showNum++;
		}
	}
	
}
