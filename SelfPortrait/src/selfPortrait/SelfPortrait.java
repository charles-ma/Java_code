package selfPortrait;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.net.URI;
import javax.swing.JFrame;


/**
 * @author Put your name here
 */
public class SelfPortrait extends JFrame {      
    /**
     * JFrame is serializable and you have to indicate the serialVersionUID in case of future complexity.
     */
    private static final long serialVersionUID = -8005944585994851327L;
    Color infoColor = Color.BLUE;
    
    public static void main(String[] args) {
        new SelfPortrait().run();
    }

    private void run() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
        myMouseListener myListener = new myMouseListener(this); 
        this.addMouseListener(myListener);
    }
    
    public void paint(Graphics g) {
    	//background
        Color myBgColor = new Color(176, 196, 222);
        g.setColor(myBgColor);
        int gridWidth = 1;
        for(int i = 0; i <= 300; i++) {
            if(i % 2 == 0) {
                g.fillRect(0, i * gridWidth, 300, gridWidth);
                g.fillRect(i * gridWidth, 0, gridWidth, 300);
            }else {
                //other colors
            }
        }
        //g.fillRect(0, 0, 300, 30);
        //name
        drawInfo(g);
        //face
        g.setColor(Color.ORANGE);
        g.fillOval(100, 95, 100, 120);
        //ears
        g.setColor(Color.ORANGE);
        g.fillOval(95, 137, 12, 30);
        g.fillOval(193, 137, 12, 30);
        g.setColor(Color.BLACK);
        g.drawLine(100, 150, 100, 155);
        g.drawLine(198, 150, 198, 155);
        //eye brows
        g.setColor(Color.BLACK);
        g.drawArc(105, 143, 60, 60, 65, 50);
        g.drawArc(135, 143, 60, 60, 65, 50);
        //eyes
        g.setColor(Color.WHITE);
        g.fillOval(124, 150, 20, 8);
        g.fillOval(156, 150, 20, 8);
        g.setColor(Color.BLACK);
        g.fillOval(128, 150, 8, 8);
        g.fillOval(160, 150, 8, 8);
        //glasses
        g.setColor(Color.BLACK);
        int a[] = {120, 147, 147, 120, 120};
        int b[] = {148, 148, 160, 160, 148};
        g.drawPolygon(a, b, 5);
        int c[] = {180, 153, 153, 180, 180};
        int d[] = {148, 148, 160, 160, 148};
        g.drawPolygon(c, d, 5);
        g.drawLine(147, 152, 153, 152);
        g.drawLine(120, 152, 110, 144);
        g.drawLine(180, 152, 190, 144);
        //hair
        g.setColor(Color.BLACK);
        g.fillArc(100, 85, 100, 98, 0, 180);
        int e[] = {101, 101, 110};
        int f[] = {134, 148, 134};
        g.fillPolygon(e, f, 3);
        int h[] = {200, 200, 192};
        int i[] = {134, 150, 134};
        g.fillPolygon(h, i, 3);
        //nose210
        g.setColor(Color.BLACK);
        g.drawLine(150, 165, 150, 185);
        //g.drawLine(150, 165, 155, 180);
        //mouth
        g.setColor(Color.BLACK);
        g.drawArc(140, 185, 20, 10, 235, 70);
    }
    
    public void drawInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 15));
        g.drawString("Charles(Chao) Ma", 80, 260);
        g.setColor(infoColor);
        g.setFont(new Font("Dialog", Font.BOLD, 15));
        g.drawString("Click anywhere for a surprise!", 30, 60);
    }
    
    public void changeColor() {
        if(infoColor == Color.BLUE) {
            infoColor = Color.RED;
        }else {
            infoColor = Color.BLUE;
        }
        repaint(30, 45, 270, 20);
    }
}

class myMouseListener extends MouseAdapter {
    SelfPortrait f = null;
    public myMouseListener(SelfPortrait f) {
        this.f = f;
    }
    
    public void mouseClicked(MouseEvent e) {
        Desktop desktop = Desktop.getDesktop();
        URI uri = null;
        try{uri = new URI("http://www.seas.upenn.edu/~machao");}
        catch(Exception ex) {
            System.out.println("Couldn't find the URL!");
        }
        try{desktop.browse(uri);}
        catch(Exception ex) {
            System.out.println("Couldn't open the URL!");
        }
    }
    
    public void mouseEntered(MouseEvent e) {
        f.changeColor();
    }
    
    public void mouseExited(MouseEvent e) {
        f.changeColor();
    }
}