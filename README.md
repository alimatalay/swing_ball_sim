Simple java/swing app with physics calculations and renderer for 2D objects.

Operating System: Linux Mint Cinnamon 22.3

Editors: VS Code & Vim

Language/Runtime: Java/openjdk 21.0.12.1 

1. Clone the Repository

git clone https://github.com/alimatalay/swing_ball_sim.git
cd swing_ball_sim

2. Build the Docker Image

docker build -t swing-ball-sim .

3. Grant X11 Display Permissions (Linux Only)

xhost +local:docker

4. Run the Simulation

docker run --rm -it -e DISPLAY=$DISPLAY -v /tmp/.X11-unix:/tmp/.X11-unix swing-ball-sim

