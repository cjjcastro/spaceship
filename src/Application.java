import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class Application extends JFrame {
    
    JMenuBar barra = new JMenuBar();
    JMenu menu1 = new JMenu("Options");
    JMenuItem item2 = new JMenuItem("Restart");
    JMenuItem item3 = new JMenuItem("Credits");
    JMenuItem item1 = new JMenuItem("Exit");
    Map mapa;
    
    
    public Application() {
        
        setJMenuBar(barra);
        barra.add(menu1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.add(item1);
        item1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        item2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mapa.restartMap();
            }
        });
        item3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"Desenvolvido por Cleber Júnior \ncontato: cjj.castro3@gmail.com \n\nVersão 1.0 - 2017","Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        //add(new Map());
        mapa = new Map();
        add(mapa);

        setSize(Game.getWidth(), Game.getHeight());

        setTitle("Save The Astronaut");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application app = new Application();
                app.setVisible(true);
            }
        });
    }
}