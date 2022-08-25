package tank;

public class Tank {
    private int x;//横坐标
    private int y;//纵坐标
    private int direction = 0;//方向 上右下左 0 1 2 3
    private int type = 0;//0为玩家，-1为AI
    private int SPEED = 5;

    public void moveUp(){
        if(y - this.SPEED >= 0 ){//判断是否到达边界--用于限制坦克移动
            y -= this.SPEED;
        }
    }
    public void moveDown(){
        if(y + this.SPEED <= JPanelTK.WINDOW_HEIGHT - 80){//判断是否到达边界--用于限制坦克移动(-60为坦克自身长度)
            y += this.SPEED;
            System.out.println(y);
        }
    }
    public void moveRight(){
        if(x + this.SPEED <= JPanelTK.WINDOW_WIDTH - 60){//判断是否到达边界--用于限制坦克移动(-80变换方向时保证坦克在框内)
            x += this.SPEED;
        }
    }
    public void moveLeft(){
        if(x - this.SPEED >= 0 ){//判断是否到达边界--用于限制坦克移动
            x -= this.SPEED;
        }
    }

    public Tank(int x, int y, int direction, int type) {
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

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }
}
