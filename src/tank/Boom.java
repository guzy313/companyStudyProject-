package tank;

/**
 * @author Gzy
 * @version 1.0
 */
public class Boom {
    private int x;
    private int y;
    private boolean alive;
    private int lifes;

    public Boom(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.lifes = 9;
    }

    public void lifesDown(){
        if(this.lifes > 0){
            this.lifes --;
        }else{
            this.alive = false;
        }
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

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
