package com.github.GITHUBear.tankwar;
/*
 * @author GITHUBear
 */
import java.util.ArrayList;
import java.util.List;

public class map {
	public List<Brick> listBrick=new ArrayList<Brick>();
	public void mapIni(){
		listBrick.add(new Brick("Images/wall_brick.png",275,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",300,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",250,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",225,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",200,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",200,425,true));
		listBrick.add(new Brick("Images/wall_brick.png",200,450,true));
		listBrick.add(new Brick("Images/wall_brick.png",200,475,true));
		listBrick.add(new Brick("Images/wall_brick.png",300,425,true));
		listBrick.add(new Brick("Images/wall_brick.png",300,450,true));
		listBrick.add(new Brick("Images/wall_brick.png",300,475,true));
		listBrick.add(new Brick("Images/wall_brick.png",100,100,true));
		listBrick.add(new Brick("Images/wall_brick.png",100,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",125,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",375,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",400,400,true));
		listBrick.add(new Brick("Images/wall_brick.png",100,125,true));
		listBrick.add(new Brick("Images/wall_brick.png",100,150,true));
		listBrick.add(new Brick("Images/wall_brick.png",125,150,true));
		listBrick.add(new Brick("Images/wall_brick.png",150,150,true));
		listBrick.add(new Brick("Images/wall_brick.png",325,300,true));
		listBrick.add(new Brick("Images/wall_brick.png",300,300,true));
		listBrick.add(new Brick("Images/wall_brick.png",350,300,true));
		listBrick.add(new Brick("Images/wall_brick.png",375,300,true));
		listBrick.add(new Brick("Images/wall_brick.png",375,275,true));
		listBrick.add(new Brick("Images/wall_brick.png",375,250,true));
	}
}
