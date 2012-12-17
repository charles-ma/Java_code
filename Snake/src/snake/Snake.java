package snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Charles(Chao) Ma
 * @author Karan Srivastava
 * @version Dec 5, 2012
 * 
 * Controller class
 */
@SuppressWarnings("serial")
public class Snake extends JFrame{
 
    private enum controlScheme{
        ABSOLUTE, RELATIVE;
    }
    
    private enum gameDifficulty{
        EASY, MEDIUM, HARD;
    }
    
    //component panels:
    JPanel menuPanel = new JPanel();
    //menu bar elements:
    JMenuBar menuBar = new JMenuBar();
    JMenu game = new JMenu("Game");
    JMenuItem start = new JMenuItem("Start!");
    JMenu controls = new JMenu("Control Scheme");
    JMenuItem absolute = new JMenuItem("Absolute");
    JMenuItem relative = new JMenuItem("Relative");
    JMenu gameSize = new JMenu("Game Size");
    JMenuItem smallSize = new JMenuItem("Small");
    JMenuItem mediumSize = new JMenuItem("Medium");
    JMenuItem largeSize = new JMenuItem("Large");
    JMenu difficultyLevel = new JMenu("Difficulty");
    JMenuItem easy = new JMenuItem("Easy");
    JMenuItem medium = new JMenuItem("Medium");
    JMenuItem hard = new JMenuItem("Hard");
    JMenuItem stop = new JMenuItem("Stop Current Game");
    JMenuItem quit = new JMenuItem("Quit Snake");
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    int rows = 100;
    int cols = 100;
    int speed = 8;
    
    controlScheme control = controlScheme.ABSOLUTE;
    gameDifficulty difficulty = gameDifficulty.EASY;
    
    SnakeModel model = new SnakeModel(rows, cols, 5);
    SnakeView view;
    SnakeThread snakeThread;
    
    public static void main(String[] args) {
        new Snake().run();
    }
        
    /**
     * run the game
     */
    private void run() {
        //this.model = new SnakeModel(rows, cols, 5);
        view = new SnakeView(this);
        
        setupWindow();
        addComponents();
        attachActionListeners();
        attachKeyListeners();
    }
    
    /**
     * Create window
     */
    private void setupWindow() {
        setSize(800, 880);
        this.setLocation((this.screenSize.width - 800) / 2, (this.screenSize.height - 800) / 2);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * Define layout and add components
     */
    private void addComponents() {
        //add components to the window
        menuPanel.setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.NORTH);
        add(view, BorderLayout.CENTER);

        //add the menu bar to the menu panel:
        menuPanel.add(menuBar);
        
        //add menu items to the menu bar
        menuBar.add(game);
        
        game.add(start);
        
        //add menu items to the game menu
        game.add(controls);
        game.add(gameSize);
        game.add(difficultyLevel);
        game.add(stop);
        game.add(quit);

        stop.setEnabled(false);
        
        //add menu items to the controls menu
        controls.add(absolute);
        controls.add(relative);

        //add menu items to the size menu
        gameSize.add(smallSize);
        gameSize.add(mediumSize);
        gameSize.add(largeSize);
        
        //add menu items to the difficulty menu
        difficultyLevel.add(easy);
        difficultyLevel.add(medium);
        difficultyLevel.add(hard);
    }
    
    /**
     * add action listeners to menu items
     */
    private void attachActionListeners() {
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                begin();
                stop.setEnabled(true);
            }
        });  
        
        absolute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                control = controlScheme.ABSOLUTE;
            }
        });  
        
        relative.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                control = controlScheme.RELATIVE;
            }
        });  

        smallSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                model.setMaxRow(25);
                model.setMaxCol(25);
            }
        });  
        
        mediumSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                model.setMaxRow(50);
                model.setMaxCol(50);
            }
        });  
        
        largeSize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                model.setMaxRow(100);
                model.setMaxCol(100);
            }
        });  
        
        easy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                speed = 8;
            }
        });  
        
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                speed = 12;
            }
        });  
        
        hard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                speed = 16;
            }
        });  

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gameOver();
            }
        });  
        
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                model.stop();
                int score = model.getScore();
                JOptionPane.showMessageDialog(Snake.this, "Your final score is " + score + "\nFlies eaten: " + model.fliesEaten(), "Score: " + score, JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });  
    }
    
    /**
     * add key listeners for input
     */
    private void attachKeyListeners() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP) {
                    if(control == controlScheme.ABSOLUTE) {
                        System.out.println("up");
                        model.turnAbUp();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if(control == controlScheme.ABSOLUTE) {
                        System.out.println("down");
                        model.turnAbDown();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if(control == controlScheme.ABSOLUTE) {
                        System.out.println("left");
                        model.turnAbLeft();
                    }else {
                        model.turnReLeft();
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if(control == controlScheme.ABSOLUTE) {
                        System.out.println("right");
                        model.turnAbRight();
                    }else {
                        model.turnReRight();
                    }
                }
            }
        });
    }
    
    
    /**
     * reset the game. show dialog box with final score and flies eaten
     */
    public void gameOver() {
        stop.setEnabled(false);
        model.stop();
        int score = model.getScore();
        int fliesEaten = model.fliesEaten();
        //JOptionPane.showMessageDialog(Snake.this, "Your final score is " + score + "\nFlies eaten: " + fliesEaten, "Game Over", JOptionPane.INFORMATION_MESSAGE); 
        ScoreWin s = new ScoreWin(score);
        model = new SnakeModel(rows, cols, 5);
        view.setModel(model);
        view.repaint();      
    }
    
    /**
     * start the game
     */
    public void begin() { 
        //model = new SnakeModel(rows, cols, 5);
        model.setSpeed(speed);
        model.addObserver(view);
        view.setModel(model);

        snakeThread = new SnakeThread();
        snakeThread.setDaemon(true);
        snakeThread.start();
    }
    
    /**
     * @author Charles(Chao) Ma
     * @author Karan Srivastava
     * @version Dec 5, 2012
     * 
     * Class to start the model on a new thread
     */
    class SnakeThread extends Thread{
        public SnakeThread() {
            super();            
        } 
        
        @Override
        public void run() {
            model.start();
        }
    }
    
    class ScoreWin extends JFrame {
        Random random = new Random();
        int score = 0;
        /**
         * Constructor of the scoring window
         */
        public ScoreWin(int score) {
            this.setSize(200, 100);
            this.setLocation((screenSize.width - 200) / 2, (screenSize.height - 100) / 2);
            this.score = score;
            this.setTitle("Congratulations!");
            setVisible(true);
            setResizable(false);
            LittleThread t = new LittleThread(this);
            t.start();
        }
        
        @Override
        public void paint(Graphics g) {
            for(int i = 0; i < 200; i += 10) {
                for(int j = 0; j < 100; j += 10) {
                    if(random.nextInt(50) < 25) {
                        g.setColor(Color.white);
                    }else {
                        g.setColor(Color.yellow);
                    }
                    g.fillRect(i, j, 10, 10);
                }
            }
            g.setColor(Color.black);
            g.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 15));
            g.drawString("Your score is " + this.score, 40, 50);
        }
        
        class LittleThread extends Thread{
            Timer timer = new Timer();
            ScoreWin s;
            public LittleThread(ScoreWin s) {
                this.s = s;
            }
            
            @Override
            public void run() {
                timer.schedule(new TimerTask() {
                    public void run() {
                        System.out.println("s");
                        s.repaint();
                    }
                }, 0, 1000);
            }
        }
    }
    
}