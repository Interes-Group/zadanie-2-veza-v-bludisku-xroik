package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MyMaze {
    int numberOfWins;
    int cols,rows;
    int size;
    int[][] maze;
    Tile startTile; // start
    Tile endTile; // finish
    Tile currentTile; // player
    Tile previousTile; // previous player location (for mouse click)
    Tile currentMouseTile;

    // 0 -> white, 1->black, 2->green, 3->red, 4->pink, 5->blue
    public MyMaze(int rows,int cols,int size){
        this.rows = rows;
        this.cols = cols;
        this.size = size;
        maze = new int[rows][cols];
        startTile = new Tile();
        endTile = new Tile();
        currentTile = new Tile();
        previousTile = new Tile();
        currentMouseTile = new Tile();
        numberOfWins = 0;
    }
    public int[][] getMaze(){
        return this.maze;
    }
    public void generateMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = 1;
            }
        }
        Random rand = new Random();
        int r = rand.nextInt(rows);
        while (r % 2 == 0) {
            r = rand.nextInt(rows);
        }
        // Generate random c
        int c = rand.nextInt(cols);
        while (c % 2 == 0) {
            c = rand.nextInt(cols);
        }
        startTile = new Tile(r,c);
        maze[r][c] = 0;
        recursion(r,c);
        setStartTile(startTile.getX(),startTile.getY());
        endTile = setEndTile();
        maze[endTile.getX()][endTile.getY()] = 3;
    }
    private Tile setEndTile(){
        Random rand = new Random();
        int x,y;
        do {
            x = rand.nextInt(rows);
            y = rand.nextInt(cols);
        }while(maze[x][y] != 0);
        return new Tile(x,y);
    }
    private void setStartTile(int r,int c){
        maze[r][c] = 2;
        currentTile = new Tile(r,c);
    }
    private void recursion(int r, int c) {
        Integer[] randDirs = generateRandomDirections();
        //int[] randDirs = generateRandomDirections();
        // Examine each direction
        for (Integer randDir : randDirs) {

            switch (randDir) {
                case 1: // Up
                    //　Whether 2 cells up is out or not
                    if (r - 2 <= 0)
                        continue;
                    if (maze[r - 2][c] != 0) {
                        maze[r - 2][c] = 0;
                        maze[r - 1][c] = 0;
                        recursion(r - 2, c);
                    }
                    break;
                case 2: // Right
                    // Whether 2 cells to the right is out or not
                    if (c + 2 >= rows - 1)
                        continue;
                    if (maze[r][c + 2] != 0) {
                        maze[r][c + 2] = 0;
                        maze[r][c + 1] = 0;
                        recursion(r, c + 2);
                    }
                    break;
                case 3: // Down
                    // Whether 2 cells down is out or not
                    if (r + 2 >= cols - 1)
                        continue;
                    if (maze[r + 2][c] != 0) {
                        maze[r + 2][c] = 0;
                        maze[r + 1][c] = 0;
                        recursion(r + 2, c);
                    }
                    break;
                case 4: // Left
                    // Whether 2 cells to the left is out or not
                    if (c - 2 <= 0)
                        continue;
                    if (maze[r][c - 2] != 0) {
                        maze[r][c - 2] = 0;
                        maze[r][c - 1] = 0;
                        recursion(r, c - 2);
                    }
                    break;
            }
        }
    }

    private Integer[] generateRandomDirections(){
        ArrayList<Integer> randoms = new ArrayList<>();
        for(int i=0;i<4;i++){
            randoms.add(i+1);
        }
        Collections.shuffle(randoms);
        return randoms.toArray(new Integer[4]);
    }

    public void moveUp(){
        if(currentTile.getY()>0){
            if(maze[currentTile.getX()][currentTile.getY()-1] == 0){
                maze[currentTile.getX()][currentTile.getY()-1] = 2;
                maze[currentTile.getX()][currentTile.getY()] = 0;
                currentTile.decreaseY();
            }
            else if(maze[currentTile.getX()][currentTile.getY()-1]==3){
                numberOfWins++;
                generateMaze();

            }
        }
    }

    public void moveDown(){

        if(currentTile.getY()<cols){
            if(maze[currentTile.getX()][currentTile.getY()+1] == 0){
                maze[currentTile.getX()][currentTile.getY()+1] = 2;
                maze[currentTile.getX()][currentTile.getY()] = 0;
                currentTile.increaseY();
            }
            else if(maze[currentTile.getX()][currentTile.getY()+1]==3){
                numberOfWins++;
                generateMaze();
            }
        }
    }
    public void moveRight(){
        if(currentTile.getX()<rows){
            if(maze[currentTile.getX()+1][currentTile.getY()]==0){
                maze[currentTile.getX()+1][currentTile.getY()]=2;
                maze[currentTile.getX()][currentTile.getY()]=0;
                currentTile.increaseX();
            }
            else if(maze[currentTile.getX()+1][currentTile.getY()]==3){
                numberOfWins++;
                generateMaze();

            }
        }
    }
    public  void moveLeft(){
        if(currentTile.getX() > 0){
            if(maze[currentTile.getX()-1][currentTile.getY()] == 0){
                maze[currentTile.getX()-1][currentTile.getY()] = 2;
                maze[currentTile.getX()][currentTile.getY()] = 0;
                currentTile.decreaseX();
            }
            else if(maze[currentTile.getX()-1][currentTile.getY()]==3){
                numberOfWins++;
                generateMaze();

            }
        }
    }
    public boolean findPink(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(maze[i][j]==4){
                    return true;
                }
            }
        }
        return false;
    }
    public void markCrossNeighbours(int x,int y){
        int startPointX = x, startPointY = y;
        while(maze[x+1][y] == 0){
            maze[x+1][y] = 4;
            x++;
        }
        x = startPointX;
        while(maze[x-1][y] == 0){
            maze[x-1][y] = 4;
            x--;
        }
        x = startPointX;
        while(maze[x][y+1] == 0){
            maze[x][y+1] = 4;
            y++;
        }
        y = startPointY;
        while(maze[x][y-1] == 0){
            maze[x][y-1]=4;
            y--;
        }
    }
    public void unmarkCrossNeighbours(int x,int y){
        int startPointX = x, startPointY = y;
        while(maze[x+1][y] == 4 || maze[x+1][y] == 5){
            maze[x+1][y] = 0;
            x++;
        }
        x = startPointX;
        while(maze[x-1][y] == 4|| maze[x-1][y] == 5){
            maze[x-1][y] = 0;
            x--;
        }
        x = startPointX;
        while(maze[x][y+1] == 4|| maze[x][y+1] == 5){
            maze[x][y+1] = 0;
            y++;
        }
        y = startPointY;
        while(maze[x][y-1] == 4|| maze[x][y-1] == 5){
            maze[x][y-1]=0;
            y--;
        }
        y= startPointY;
        if(maze[x][y] == 4|| maze[x][y] == 5){
            maze[x][y] = 0;
        }
    }
    public boolean checkPinkNeighbours(int x, int y){
        return (maze[x - 1][y] == 4 || maze[x + 1][y] == 4 || maze[x][y - 1] == 4 || maze[x][y + 1] == 4) ||
                (maze[x - 1][y] == 5 || maze[x + 1][y] == 5 || maze[x][y - 1] == 5 || maze[x][y + 1] == 5);
    }
    public void mouseClickOnTile(int x, int y){
        int currentTileX = x/size;
        int currentTileY = y/size;
        if(currentTileX<rows && currentTileY<cols) {
            if(!findPink()) {//IndexOutOfBonds exception
                if (maze[currentTileX][currentTileY] == 2) {
                    maze[currentTileX][currentTileY] = 5;
                    markCrossNeighbours(currentTileX, currentTileY);
                    previousTile.setX(currentTileX);
                    previousTile.setY(currentTileY);
                    currentMouseTile.setX(currentTileX);
                    currentMouseTile.setY(currentTileY);
                }
            }
            else{
                if(maze[currentTileX][currentTileY]==4 || maze[currentTileX][currentTileY]==5){
                    unmarkCrossNeighbours(previousTile.getX(),previousTile.getY());
                    maze[currentTileX][currentTileY]=2;
                    currentTile.setX(currentTileX);
                    currentTile.setY(currentTileY);
                }
                else if(maze[currentTileX][currentTileY]==3){
                    if(checkPinkNeighbours(currentTileX,currentTileY)){
                        numberOfWins ++;
                        generateMaze();
                    }
                }
            }
        }
    }
    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public String getLabelText(){
        return "Number of wins: "+ numberOfWins;
    }

    public boolean mouseMoveDetectPink(int currentX, int currentY) {
        if(currentX/size<rows && currentY/size < cols) {
            if (maze[currentX / size][currentY / size] == 4) {
                if(currentX/size!=currentMouseTile.getX() || currentY/size != currentMouseTile.getY()) {
                    maze[currentMouseTile.getX()][currentMouseTile.getY()] = 4;
                    currentMouseTile.setX(currentX/size);
                    currentMouseTile.setY(currentY/size);
                }
                maze[currentMouseTile.getX()][currentMouseTile.getY()] = 5;
                return true;
            }
        }
                return false;
    }
}
