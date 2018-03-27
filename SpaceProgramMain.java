import ecs100.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JColorChooser;
/**
 * Write a description of class SpaceProgramMain here.
 *
 * @author AidanWatson
 */
public class SpaceProgramMain
{
    // instance variables - replace the example below with your own
    double tInterval=0.2;
    private double G0 = 1;
    private double g=10;

    boolean gameRunning=false;
    boolean gamePaused=true;
    boolean gfloor=false;//decides whether bodies are attracted to each other only or to ground.
    ArrayList<OrbitalBody> OBodies = new ArrayList<OrbitalBody>();
    public SpaceProgramMain (){
        UI.addButton("Start test", this::runTestGame);
        UI.addButton("Pause game", this::changePause);
    }

    public void changePause(){//pauses or unpauses game
        if(gamePaused==true){
            gamePaused=false;
        }
        else{gamePaused=true;}

    }

    public void runTestGame(){
        boolean gameRunning=true;
        gfloor=true;
        TestRock t1 = new TestRock(UI.askDouble("x"),UI.askDouble("y"),UI.askDouble("Vx"),UI.askDouble("Vy"));
        OBodies.add(t1);
        StartSimulation();
    }

    public void StartSimulation(){//runs main simulation process
        while(gameRunning==true){
            while(gamePaused==false){//checks game is running and unpaused
                evaluateForces();
                for(OrbitalBody b:OBodies){
                    b.move();
                }
                drawAll();
                UI.sleep(1000*tInterval);
            }
        }

    }

    public void evaluateForces(){//applies force between all active orbital bodies
        for(OrbitalBody b1:OBodies){
            for(OrbitalBody b2:OBodies){
             double bx1=b1.returnX();
             double by1=b1.returnY();
             double bx2=b1.returnX();
             double by2=b1.returnY();
             double dx = (bx2-bx1);
             double dy = (by2-by1);
             double dTotal = Math.hypot(dx, dy);//total distance vector between objects
             double Ftotal = (b1.returnM()*b2.returnM()*G0)/(Math.pow(dTotal,2));//newtons Fg equation
             double Fx=(Ftotal*(dx/dTotal));
             double Fy=(Ftotal*(dx/dTotal));
                            
            }
            
        }//all forces are applied upon the first object b1
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void drawAll()
    {
        for(OrbitalBody b:OBodies){
            b.redraw();
        }

    }
}
