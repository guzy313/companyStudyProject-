package my.tank.entity;

public class Hero {
    private int x;//横坐标
    private int y;//纵坐标
    private int direction = 0;//方向 上右下左 0 1 2 3
    private int type = 0;//0为玩家，-1为AI
    private final int SPEED = 5;

    public void moveUp(){
        y -= SPEED;
    }
    public void moveDown(){
        y += SPEED;
    }
    public void moveRight(){
        x += SPEED;
    }
    public void moveLeft(){
        x -= SPEED;
    }

    public Hero(int x, int y, int direction, int type) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
