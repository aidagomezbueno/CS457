import javax.swing.*;
import java.awt.*;

public class GradientCat1 {
    public static void main(String[] args) {
        GradientCat1 gd = new GradientCat1();
    }

    public GradientCat1(){
        JFrame frame = new JFrame("cat2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MyComponent());
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    public class MyComponent extends JComponent  {
        public void paint(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            Color s1 = Color.black;
            Color e = Color.red;
            GradientPaint gradient = new GradientPaint(10,10,s1,30,90,e,true);
            g2d.setPaint(gradient);
            g2d.drawArc(110,100,225,190,0,360);
            Color s2 = Color.orange;
            Color e1 = Color.red;
            GradientPaint gradient1 = new GradientPaint(10,200,s2,300,10,e1,true);
            g2d.setPaint(gradient1);
            g2d.fillArc(110,100,225,190,0,360);
            g2d.setColor(Color.black);
            g2d.drawArc(170,170,25,25,0,360);
            g2d.fillArc(170,170,25,25,0,360);
            g2d.drawArc(245,170,25,25,0,360);
            g2d.fillArc(245,170,25,25,0,360);
            g2d.drawArc(190,205,30,50,0,-80);
            g2d.drawArc(220,205,30,50,-100,-90);
            g2d.drawArc(212,220,17,17,0,360);
            g2d.fillArc(212,220,17,17,0,360);
            g2d.drawLine(175, 225, 220, 230);
            g2d.drawLine(175, 215, 220, 230);
            g2d.drawLine(175, 235, 220, 230);
            g2d.drawLine(220, 230, 265, 225);
            g2d.drawLine(220, 230, 265, 215);
            g2d.drawLine(220, 230, 265, 235);
            Color s3 = Color.orange;
            Color e2 = Color.red;
            GradientPaint gradient2 = new GradientPaint(40,200,s3,300,10,e2,true);
            g2d.setPaint(gradient2);
            g2d.drawArc(240,90,80,120,0,135);
            g2d.fillArc(240,90,80,120,0,135);
            g2d.drawArc(120,90,80,120,42,145);
            g2d.fillArc(120,90,80,120,42,145);
            g2d.setColor(Color.white);
            g2d.drawArc(250,100,60,100,0,135);
            g2d.fillArc(250,100,60,100,0,135);
            g2d.drawArc(130,100,60,100,42,145);
            g2d.fillArc(130,100,60,100,42,145);
            g2d.drawLine(220, 140, 225, 130);
            g2d.drawLine(225, 130, 220, 120);
            g2d.drawLine(220, 120, 225, 110);
            g2d.drawLine(225, 110, 220, 100);
            g2d.drawLine(215, 140, 220, 130);
            g2d.drawLine(220, 130, 215, 120);
            g2d.drawLine(215, 120, 220, 110);
            g2d.drawLine(220, 110, 215, 100);

            g2d.drawLine(200, 140, 205, 130);
            g2d.drawLine(205, 130, 200, 120);
            g2d.drawLine(200, 120, 205, 110);
            g2d.drawLine(205, 110, 200, 100);
            g2d.drawLine(195, 140, 200, 130);
            g2d.drawLine(200, 130, 195, 120);
            g2d.drawLine(195, 120, 200, 110);
            g2d.drawLine(200, 110, 195, 100);

            g2d.drawLine(240, 140, 245, 130);
            g2d.drawLine(245, 130, 240, 120);
            g2d.drawLine(240, 120, 245, 110);
            g2d.drawLine(245, 110, 240, 100);
            g2d.drawLine(235, 140, 240, 130);
            g2d.drawLine(240, 130, 235, 120);
            g2d.drawLine(235, 120, 240, 110);
            g2d.drawLine(240, 110, 235, 100);
        }
    }
}