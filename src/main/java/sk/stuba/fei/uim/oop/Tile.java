package sk.stuba.fei.uim.oop;

public class Tile { // represents tile with 2 coords
    private int x,y;
    public Tile(){
        this.x = -1;
        this.y = -1;
    }
    public Tile(int x,int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void decreaseY(){
        this.y--;
    }
    public void increaseY(){
        this.y++;
    }
    public void decreaseX(){
        this.x--;
    }
    public void increaseX(){
        this.x++;
    }
}
