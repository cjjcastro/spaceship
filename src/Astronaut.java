
import java.util.Random;


public class Astronaut extends Sprite {
    public Astronaut(int x, int y){
        super(x, y);
        
        loadImage("images/Astronaut.gif");
    }
    
    public void move() {
        Random geradorX = new Random();
        Random geradorY = new Random();
        
        x = geradorX.nextInt(450);
        y = geradorY.nextInt(450);
    }
    public void sumir() {
        
        x = -50;
        y = -50;
    }
}
