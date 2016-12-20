package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame{
	private Image offScreenImage=null;
	private PaintThread paintThread=new PaintThread();
	public void launch(){
		this.setBackground(Color.BLACK);
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		this.setLocation(100, 100);
		this.setVisible(true);
		this.setTitle("TankWar");
		paintThread.start();
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	public void update(Graphics g){
		if(offScreenImage==null)
			offScreenImage=this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		Graphics gOff=offScreenImage.getGraphics();
		gOff.fillRect(0, 0, Constant.GAME_HEIGHT,Constant.GAME_WIDTH );
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, this);
	}
	
	class PaintThread extends Thread{

		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
}
