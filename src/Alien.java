
public class Alien extends Sprite{
    
        int timer=0;
	
	public Alien(int x, int y){
		super(x,y);
		

		
		initAlien();
	}
	
	public void initAlien() {
		
		alienEasy();
		
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
		y+=1;
                if (timer < 50) x-=1;
        	else if (timer < 100) x+=1;
        	else timer=0;
        	
        	timer++;

	}
	
	
}
