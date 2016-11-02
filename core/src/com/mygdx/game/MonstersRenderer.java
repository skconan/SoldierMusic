package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class MonstersRenderer {
	private Monsters monster;
	private SpriteBatch batch;
	private Texture[][] monsterImg2D ;
	private Texture[] monsterImg = new Texture[10];
	private Texture[] monsterImgAction = new Texture[10];
	private int[][] monsterSize ;
	private float[][] monsterMove;
	private Random rand;
	private int count = 0;
	private String fileMonsters[] = {"monster06.fw.png","monster06.fw.png"};
	private String fileMonstersAction[] = {"monster06-1.fw.png"};
	private Point point = new Point();
	
	public MonstersRenderer(SpriteBatch batch, Monsters monsters){
		rand = new Random();
		this.monster = monsters;
		this.batch = batch;
		monsterImg2D = new Texture[monster.getHeight()][monster.getWidth()];
		monsterSize = new int[monster.getHeight()][monster.getWidth()];
		monsterMove = new float[monster.getHeight()][monster.getWidth()];
		for(int i=0; i<2; i++){
			monsterImg[i] = new Texture(fileMonsters[i]);
		}
		for(int i=0; i<1; i++){
			monsterImgAction[i] = new Texture(fileMonstersAction[i]);
		}
		
	}
	
	public void render(float delta) {
		Vector2 pt = point.getPositionMouse();
		batch.begin();
		System.out.println(count);
		for(int r = 0; r < monster.getHeight(); r++) {
			 for(int c = 0; c < monster.getWidth(); c++) {		
				 if(count%100 == 0){ 
				 	if(monsterMove[r][c] >= 0){
				 		monsterMove[r][c] = -((float)(rand.nextInt(50)-1)/200); 
					}else{
						monsterMove[r][c] = ((float)rand.nextInt(50)+1)/200;
					}
				 	System.out.println(monsterMove[r][c]);
				}
				if(monster.hasMonsterAt(r, c)){
					if(monsterMove[r][c] < 0){
						monsterImg2D[r][c] = monsterImg[0];
					}else{
						monsterImg2D[r][c] = monsterImgAction[0];
					}
					if(monster.createdMonsterAt(r, c)){
						
						monsterSize[r][c] = monster.getSize();
					}
					batch.draw(monsterImg2D[r][c], Math.abs(pt.x)*monsterMove[r][c]+(c*50+monsterSize[r][c]/2), (7-r)*50-monsterSize[r][c]/2, monsterSize[r][c], monsterSize[r][c]);
				}
			 }
		}
		count++;
		
        batch.end();
    }
}
