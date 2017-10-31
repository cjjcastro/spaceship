
public class AlienHard extends Sprite{
        int timer=0;
	
	public AlienHard(int x, int y){
		super(x,y);
		
		initAlien();
	}
	
	public void initAlien() {
		
		alienHard();
		
	}
	
	public void alienEasy() {
		loadImage("images/alien_EASY.png");
	}
	
	public void alienMedium() {
		loadImage("images/alien_MEDIUM.png");
	}
	
	public void alienHard() {
		loadImage("images/alien_HARD.png");
	}
	
	public void move() {
		/*if( y == 500){
			y = 0;
		}*/
		y+=4;
                if (timer < 15) x-=1;
        	else if (timer < 30) x+=1;
        	else timer=0;
        	
        	timer++;

	}
}
