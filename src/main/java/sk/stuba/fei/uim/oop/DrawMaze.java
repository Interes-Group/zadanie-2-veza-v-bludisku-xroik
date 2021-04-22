package sk.stuba.fei.uim.oop;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawMaze extends Canvas {
    int[][] maze;
    int x,y;
    int size;
    public DrawMaze(MyMaze maze) {
            this.maze = maze.getMaze();
            this.x = this.maze[0].length;
            this.y = this.maze[1].length;
            this.size = maze.size;
        }
    @Override
    public void paint(Graphics g){
        var image = new BufferedImage(1000,1000,BufferedImage.TYPE_INT_RGB);
        var ramGraphics = image.getGraphics();
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(maze[i][j] == 1){
                    ramGraphics.setColor(Color.BLACK);
                }
                else if(maze[i][j] == 0){
                    ramGraphics.setColor(Color.WHITE);
                }
                else if(maze[i][j] == 2){
                    ramGraphics.setColor(Color.green);
                }
                else if(maze[i][j] == 3){
                    ramGraphics.setColor(Color.RED);
                }
                else if(maze[i][j] == 4){
                    ramGraphics.setColor(Color.pink);
                }
                else if(maze[i][j] == 5){
                    ramGraphics.setColor(Color.blue);
                }
                ramGraphics.fillRect(i*size,j*size,size,size);
            }
        }
        g.drawImage(image,0,0,null);
    }
}
