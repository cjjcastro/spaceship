import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Map extends JPanel implements ActionListener {

    private final int SPACESHIP_X = 220;
    private final int SPACESHIP_Y = 430;
    private int ALIEN_X = 25;
    private int ALIEN_Y = 0;
    private int ALIEN_MEDIUM_X = 25;
    private int ALIEN_MEDIUM_Y = 0;
    private int ALIEN_HARD_X = 25;
    private int ALIEN_HARD_Y = 0;
    private final Timer timer_map;
    
    private final Image background;
    //private final Spaceship spaceship;
    private Spaceship spaceship;
    //private final Alien alien;

    public Map() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);
        ImageIcon image = new ImageIcon("images/space.jpg");
        
        this.background = image.getImage();

        spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y);
        //alien = new Alien(ALIEN_X, ALIEN_Y);
        inicializarAliens();

        timer_map = new Timer(Game.getDelay(), this);
        timer_map.start();
                            
    }
    
    private int contadorAliens=0;
    public void inicializarAliens() {
                ALIEN_X = 25;
                ALIEN_Y = 0;
                ALIEN_MEDIUM_X = 25;
                ALIEN_MEDIUM_Y = 0;
                ALIEN_HARD_X = 25;
                ALIEN_HARD_Y = 0;
                for (int j = 0; j < spaceship.aliens.size(); j++){
                    spaceship.aliens.remove(j);
                }
                for (int j = 0; j < spaceship.aliensMedium.size(); j++){
                    spaceship.aliensMedium.remove(j);
                }
                for (int j = 0; j < spaceship.aliensHard.size(); j++){
                    spaceship.aliensHard.remove(j);
                }
                
		for (int i = 0; i < 20; i++) {
			spaceship.aliens.add(new Alien( ALIEN_X, ALIEN_Y));
                        //ALIEN_Y = ALIEN_Y + 5;
                        //ALIEN_X = ALIEN_X + 20;
                        Random geradorX = new Random();
                        Random geradorY = new Random();
        
                        ALIEN_X = geradorX.nextInt(450);
                        ALIEN_Y = geradorY.nextInt(50);
                }
                if(contadorAliens >= 5){
                    for (int i = 0; i < 20; i++) {
			spaceship.aliensMedium.add(new AlienMedium( ALIEN_MEDIUM_X, ALIEN_MEDIUM_Y));
                        Random geradorX = new Random();
                        Random geradorY = new Random();
        
                        ALIEN_MEDIUM_X = geradorX.nextInt(450);
                        ALIEN_MEDIUM_Y = geradorY.nextInt(50);
                    }
                }
                if(contadorAliens >= 10){
                    for (int i = 0; i < 20; i++) {
			spaceship.aliensHard.add(new AlienHard( ALIEN_HARD_X, ALIEN_HARD_Y));
                        Random geradorX = new Random();
                        Random geradorY = new Random();
        
                        ALIEN_HARD_X = geradorX.nextInt(450);
                        ALIEN_HARD_Y = geradorY.nextInt(50);
                    }
                }
                spaceship.astronaut.move();
                contadorAliens++;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(this.background, 0, 0, null);
        
        if(spaceship.inGame){
            //Condição de derrota
            if(spaceship.getLife()<=0){
                drawGameOver(g);
            }
            //Condição de vitória
            if(spaceship.getBonus() == 20){
                dranMissionAccomplished(g);
            }

            draw(g);
            drawLife(g);
        } else {
            menuStart(g);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    
    private void draw(Graphics g) {
        
        // Draw missil
    	for(int i=0;i<spaceship.misseis.size();i++){
            g.drawImage(spaceship.misseis.get(i).getImage(), spaceship.misseis.get(i).getX(), spaceship.misseis.get(i).getY(), this);
        }
        
        // Draw alien
        for(int i=0;i<spaceship.aliens.size();i++){
            g.drawImage(spaceship.aliens.get(i).getImage(), spaceship.aliens.get(i).getX(), spaceship.aliens.get(i).getY(), this);
        }
        for(int i=0;i<spaceship.aliensMedium.size();i++){
            g.drawImage(spaceship.aliensMedium.get(i).getImage(), spaceship.aliensMedium.get(i).getX(), spaceship.aliensMedium.get(i).getY(), this);
        }
        for(int i=0;i<spaceship.aliensHard.size();i++){
            g.drawImage(spaceship.aliensHard.get(i).getImage(), spaceship.aliensHard.get(i).getX(), spaceship.aliensHard.get(i).getY(), this);
        }
        
        // Draw astronaut
        g.drawImage(spaceship.astronaut.getImage(), spaceship.astronaut.getX(), spaceship.astronaut.getY(), this);
        
        // Draw spaceship
        g.drawImage(spaceship.getImage(), spaceship.getX(), spaceship.getY(), this);        
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(spaceship.inGame){
            updateSpaceship();
            updateAlien();
            updateMissil();
            colisor();

            repaint();
        }
    }
    
    public void colisor() {
        Rectangle formaNave = spaceship.getBounds();
        Rectangle formaAlien;
        Rectangle formaAlienMedium;
        Rectangle formaAlienHard;
	Rectangle formaMissil;
        Rectangle formaAstronaut = spaceship.astronaut.getBounds();
        
        if(formaNave.intersects(formaAstronaut)){
            spaceship.setBonus(1);
            if(spaceship.aliens.size() < 19){
                spaceship.astronaut.sumir();
            } else{
                spaceship.astronaut.move();
            }
        }
        
        //Colisor misseis com aliens
        for (int i = 0; i < spaceship.misseis.size(); i++) {
		Missil tempMissil = spaceship.misseis.get(i);
		formaMissil = tempMissil.getBounds();

                    for (int j = 0; j < spaceship.aliens.size(); j++) {
			Alien tempAlien = spaceship.aliens.get(j);
			formaAlien = tempAlien.getBounds();

			if (formaMissil.intersects(formaAlien)) {
				try{
                                    spaceship.aliens.get(j).setVisible(false);
                                    spaceship.aliens.remove(j);
                                    spaceship.misseis.get(i).setVisible(false);
                                    spaceship.misseis.remove(i);
                                }
                                catch (Exception e){
                                    
                                }
			}
                    }
                }
	
         //Colisor misseis com aliensMedium
        for (int i = 0; i < spaceship.misseis.size(); i++) {
		Missil tempMissil = spaceship.misseis.get(i);
		formaMissil = tempMissil.getBounds();

                    
                    
                    for (int j = 0; j < spaceship.aliensMedium.size(); j++) {
			AlienMedium tempAlienMedium = spaceship.aliensMedium.get(j);
			formaAlienMedium = tempAlienMedium.getBounds();

			if (formaMissil.intersects(formaAlienMedium)) {
				try{
                                    spaceship.aliensMedium.get(j).setVisible(false);
                                    spaceship.aliensMedium.remove(j);
                                    spaceship.misseis.get(i).setVisible(false);
                                    spaceship.misseis.remove(i);
                                }
                                catch (Exception e){
                                    
                                }
			}
                    }
	}
         //Colisor misseis com aliensHard
        for (int i = 0; i < spaceship.misseis.size(); i++) {
		Missil tempMissil = spaceship.misseis.get(i);
		formaMissil = tempMissil.getBounds();
                    
                for (int j = 0; j < spaceship.aliensHard.size(); j++) {
                    AlienHard tempAlienHard = spaceship.aliensHard.get(j);
                    formaAlienHard = tempAlienHard.getBounds();

                    if (formaMissil.intersects(formaAlienHard)) {
			try{
                                spaceship.aliensHard.get(j).setVisible(false);
                                spaceship.aliensHard.remove(j);
				spaceship.misseis.get(i).setVisible(false);
                                spaceship.misseis.remove(i);
                           }
                           catch (Exception e){
                                    
                           }
			}
                    }
	}
        
        for (int i = 0; i < spaceship.aliens.size(); i++) {

		Alien tempAlien = spaceship.aliens.get(i);
		formaAlien = tempAlien.getBounds();

		if (formaNave.intersects(formaAlien)) {
			tempAlien.setVisible(false);
                        spaceship.aliens.get(i).setVisible(false);
                        spaceship.aliens.remove(i);
			spaceship.setLife(0b1);
		}
	}
        for (int i = 0; i < spaceship.aliensMedium.size(); i++) {

		AlienMedium tempAlienMedium = spaceship.aliensMedium.get(i);
		formaAlien = tempAlienMedium.getBounds();

		if (formaNave.intersects(formaAlien)) {
			tempAlienMedium.setVisible(false);
                        spaceship.aliensMedium.get(i).setVisible(false);
                        spaceship.aliensMedium.remove(i);
			spaceship.setLife(0b1);
		}
	}
        for (int i = 0; i < spaceship.aliensHard.size(); i++) {

		AlienHard tempAlienHard = spaceship.aliensHard.get(i);
		formaAlien = tempAlienHard.getBounds();

		if (formaNave.intersects(formaAlien)) {
			tempAlienHard.setVisible(false);
                        spaceship.aliensHard.get(i).setVisible(false);
                        spaceship.aliensHard.remove(i);
			spaceship.setLife(0b1);
		}
	}
        for(int i = 0; i<spaceship.aliens.size() ; i++){
            if(spaceship.aliens.get(i).getY() > 499){
                spaceship.aliens.remove(i);
            }
        }
        
        if(spaceship.aliens.isEmpty()){
                inicializarAliens();
        }
        for (int i = 0; i < spaceship.misseis.size(); i++){
            if(spaceship.misseis.get(i).getY() <= 0){
                spaceship.misseis.remove(i);
            }
        }
        
    }
    
    private void dranMissionAccomplished(Graphics g) {

        String message = "MISSION ACCOMPLISHED";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
        timer_map.stop();
    }
    
    private void drawLife(Graphics g) {

        String messageLife = "LIFE: " + spaceship.getLife();
        String messageBonus = "ASTRONAUT: " + spaceship.getBonus();
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(messageLife, 10, 20);
        g.drawString(messageBonus, 10, 35);
    }
    
    private void drawGameOver(Graphics g) {

        String message = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
        timer_map.stop(); 
    }
    
    private void updateSpaceship() {
        spaceship.move();
    }
    
    private void updateAlien() {
        
        for(int i=0;i<spaceship.aliens.size();i++){
            spaceship.aliens.get(i).move();
        }
        for(int i=0;i<spaceship.aliensMedium.size();i++){
            spaceship.aliensMedium.get(i).move();
        }
        for(int i=0;i<spaceship.aliensHard.size();i++){
            spaceship.aliensHard.get(i).move();
        }
    }
    
    private void updateMissil() {
        
        for(int i=0;i<spaceship.misseis.size();i++){
            spaceship.misseis.get(i).move();
        }
    }

    private class KeyListerner extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e) {
            spaceship.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            spaceship.keyReleased(e);
        }

        
    }
    
    public void menuStart(Graphics g){
        int width;
        int height;
        ImageIcon ii = new ImageIcon("images/start.png");
        Image image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        g.drawImage(image, 150, 350, this);
        g.drawImage(spaceship.astronaut.getImage(), 200, 200, this);
        
        String message = "Press Enter";
        Font font = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metric = getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(message, (Game.getWidth() - metric.stringWidth(message)) / 2, Game.getHeight() / 2);
    }
    
    public void restartMap() {
        
        addKeyListener(new KeyListerner());
        
        setFocusable(true);
        setDoubleBuffered(true);

        spaceship = new Spaceship(SPACESHIP_X, SPACESHIP_Y);
        
        contadorAliens = 0;
        inicializarAliens();

        timer_map.start();
                            
    }
}