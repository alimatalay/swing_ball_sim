import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class input_manager extends MouseAdapter{

    private object_manager objects;

    public input_manager(object_manager objects, Component render_panel){
        this.objects = objects;
        render_panel.addMouseListener(this);
        render_panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_C) objects.clear_ball_list();     // listener , checks if 'C' key is pressed and clears list.
            }            
        });

        render_panel.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentResized(ComponentEvent e){
                Component c = e.getComponent();                 // listener for window resize, resizes plane(ground) on window resize.
                objects.get_plane().resize(c.getWidth(), c.getHeight());
            }
        });
    }

    @Override
    public void mousePressed(MouseEvent e){
        objects.addBall(e.getX(), e.getY());    // creates ball on cursor when any button on mouse clicked.
    }
}
