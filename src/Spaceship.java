import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Spaceship extends Sprite {
    
    private static final int MAX_SPEED_X = 2;
    private static final int MAX_SPEED_Y = 1;
   
    private int speed_x;
    private int speed_y;
    private int life;
    private int bonus;
    protected boolean inGame;
    
    //protected Audio soundShot;
    protected Missil missil;
    protected List <Alien> aliens;
    protected List <AlienMedium> aliensMedium;
    protected List <AlienHard> aliensHard;
    protected List<Missil> misseis;
    protected Astronaut astronaut;

    public Spaceship(int x, int y) {
        super(x, y);
        
        life = 10;
        bonus = 0;
        initSpaceShip();
        inGame = false;
        
        missil = new Missil(0, 0);
        misseis = new ArrayList<>();
        aliens = new ArrayList<>();
        aliensMedium = new ArrayList<>();
        aliensHard = new ArrayList<>();
        astronaut = new Astronaut(350, 350);
    }

    private void initSpaceShip() {
        
        noThrust();
        
    }
    
    private void noThrust(){
        loadImage("images/SpaceshipWhiteNoT.gif");
    	//loadImage("images/spaceship.png");
    }
    
    private void thrust(){
        loadImage("images/SpaceshipWhiteWT.gif");
    	//loadImage("images/spaceship_thrust.png");
    }    

    public void move() {
        
        // Limits the movement of the spaceship to the side edges.
        if((speed_x < 0 && x <= 0) || (speed_x > 0 && x + width >= Game.getWidth())){
            speed_x = 0;
        }
        
        // Moves the spaceship on the horizontal axis
        x += speed_x;
        
        // Limits the movement of the spaceship to the vertical edges.
        if((speed_y < 0 && y <= 0) || (speed_y > 0 && y + height >= Game.getHeight())){
            speed_y = 0;
        }

        // Moves the spaceship on the verical axis
        y += speed_y;
        
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        // Set speed to move to the left
        if (key == KeyEvent.VK_LEFT) { 
            speed_x = -1 * MAX_SPEED_X;
        }

        // Set speed to move to the right
        if (key == KeyEvent.VK_RIGHT) {
            speed_x = MAX_SPEED_X;
        }
        
        // Set speed to move to up and set thrust effect
        if (key == KeyEvent.VK_UP) {
            speed_y = -1 * MAX_SPEED_Y;
            thrust();
        }
        
        // Set speed to move to down
        if (key == KeyEvent.VK_DOWN) {
            speed_y = MAX_SPEED_Y;
        }
        
        // Set missil 
        if (key == KeyEvent.VK_SPACE) {
        	
                this.misseis.add(new Missil(x+((image.getWidth(null))/2)-(missil.image.getWidth(null)/2), y));
            
                //soundShot = new Audio("sounds/tiro.wav");
            
        }
        if (key == KeyEvent.VK_ENTER){
            inGame = true;
        }
        
    }
    
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            speed_x = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            speed_y = 0;
            noThrust();
        }
    }
    
    public int getLife(){
        return life;
    }
    public void setLife(int valor) {
        life = life - valor;
    }
    
    public int getBonus(){
        return bonus;
    }
    public void setBonus(int valor) {
        bonus = bonus + valor;
    }
    
}