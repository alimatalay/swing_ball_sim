import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){  // Used in Oracle examples as well, makes sure GUI gets rendered right away and does not get blocked by other threads.
            public void run(){

                int width = 640, height = 720;

                object_manager objects = new object_manager(width, height);
                physics_engine physics = new physics_engine(objects);       //physics engine and render panel class works on the same object list
                render_panel renderer = new render_panel(objects, width, height);

                new input_manager(objects, renderer);

                JFrame frame = new JFrame("ball sim");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(renderer);
                frame.pack();
                frame.setVisible(true);
                renderer.requestFocusInWindow(); // keeps the window on top. if not used C key might not work

                loop_engine loop = new loop_engine(physics, renderer);
                loop.start();
            }
        });
    }
}
