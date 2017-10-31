
public class Missil extends Sprite {
	
	public Missil(int x, int y) {
		super(x, y);
		
		initMissil();
	}
	
	public void initMissil() {
		loadImage("images/missile.png");
	}
	
	public void setX(int x) {
                this.x = x;
        }

        public void setY(int y) {
                this.y = y;
        }
    
        public void move() {
                y-=4;
        }
	
}
