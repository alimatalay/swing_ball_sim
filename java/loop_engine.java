import javax.swing.Timer;

// loop engine class, uses timer to trigger every millisecond set for the TICK_MS value 
 
public class loop_engine {

    public int tick_ms = 8;
    public double time_step = tick_ms / 1000.0;   // seconds of game time per tick

    public Timer timer;
 
    public loop_engine(physics_engine physics, render_panel renderer) {
        timer = new Timer(tick_ms, e -> {
            physics.step(time_step);               // lambda function gets triggered every millisecond set for the tick_ms and updates/renders object every time
            renderer.repaint();
        });
    }
 
    public void start(){
        timer.start();
    }

    public void stop(){
        timer.stop();
    }
}
