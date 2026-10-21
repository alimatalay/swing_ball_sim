import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Font;

public class render_panel extends JPanel{

    private object_manager objects;

    public render_panel(object_manager objects, int width, int height){
        this.objects = objects;
        setPreferredSize(new Dimension(width, height));     //initialized with same size as JFrame on main
        setBackground(new Color(25, 25, 35));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        objects.get_plane().draw(g2);        //calls draw method in object_plane object

        for (object_ball b : objects.get_ball_list()){      //calls draw method in object_ball objects
            b.draw(g2);
        }

        g2.setColor(new Color(200, 200, 210));

        Font font = new Font("Arial", Font.BOLD, 14);       //set the font for printing strings
        g2.setFont(font);

        g2.drawString("Ball count: " + objects.get_ball_list().size(), 10, 20);     //prints ball count on the top
        g2.drawString("Press C to clear.", 10, this.getHeight() - 10);      // prints button info on bottom
    }
}
