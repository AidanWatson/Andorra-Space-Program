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
    double tInterval=0.005;
    private double G0 = 75;
    private double DRatio = 15;
    private double FRatio =0.0025;//relationships between force and distance to smooth out the user experience.
    private double G1=10;
    private double nPower=1.3;
    boolean referenceLine=false;
    boolean gameRunning=false;
    boolean gamePaused=true;
    boolean gfloor=false;//decides whether bodies are attracted to each other only or to ground.
    ArrayList<OrbitalBody> OBodies = new ArrayList<OrbitalBody>();
    public SpaceProgramMain (){
        UI.addButton("Start test", this::runTestGame);
        UI.addButton("Pause game", this::changePause);
        UI.addButton("Reference line",this::ReferenceLineToggle);
        UI.addButton("New TestRock",this::addTestRock);
        UI.addButton("Reset bodies", this::resetGame);
        UI.addButton("Tinker tool", this::adjustConstants);
    }

    public void adjustConstants(){
        UI.println("adjust constants by:");
        G0=G0+(UI.askDouble("G0:"));
        DRatio=DRatio+(UI.askDouble("Distance modifier:"));
        FRatio=FRatio+(UI.askDouble("Force modifier:"));
        nPower=nPower+(UI.askDouble("Power of exponential relationship:"));
        tInterval=tInterval+(UI.askDouble("resolution speed:"));
    }

    public void resetGame(){
        int i=0;
        for(OrbitalBody b:OBodies){
            i++;
        }
        int j=0;
        while(j<=i){
            OBodies.remove(0);
            j++;
        }
        UI.println(OBodies.size());
        ArrayList<OrbitalBody> OBodies = new ArrayList<OrbitalBody>();
        UI.println("game reset");
    }

    public void changePause(){//pauses or unpauses game
        if(gamePaused==true){
            gamePaused=false;
            runSimulation();
        }
        else{gamePaused=true;}

    }

    public void runTestGame(){
        gameRunning=true;
        gfloor=false;
        TestRock t1 = new TestRock(100,100,2,1);
        UI.println("rock created");
        OBodies.add(t1);
        TestRock t2 = new TestRock(400,400,1,0);
        UI.println("rock created");
        OBodies.add(t2);
        //UI.println("added");
        UI.println(OBodies.size());
        runSimulation();

    }

    public void ReferenceLineToggle(){
        if(referenceLine==true){
            referenceLine=false;
        }
        else{referenceLine=true;}

    }

    public void ReferenceLine(){//draws line for a distance reference
        if(referenceLine==true){
            UI.setColor(Color.black);
            UI.drawLine(10, 300, 610, 300);
            UI.drawLine(10,300,10,310);
            UI.drawLine(110,300,110,310);
            UI.drawLine(210,300,210,310);
            UI.drawLine(310,300,310,310);
            UI.drawLine(410,300,410,310);
            UI.drawLine(510,300,510,310);
            UI.drawLine(610,300,610,310);
            UI.drawString("(10,310)",10, 325);
            UI.drawString("(110)",110,325);

        }
    }

    /**
     * runs main simulation process
     * checks forces between bodies, alters velocities, and moves bodies, before redrawing
     */
    public void runSimulation(){

        UI.println("paused status:"+gamePaused);
        while(gameRunning==true){

            while(gamePaused==false){//checks game is running and unpaused
                drawAll();

                evaluateForces();

                for(OrbitalBody b:OBodies){
                    b.move();
                }
                UI.sleep(1000*tInterval);
                UI.clearGraphics();
                drawAll();
                UI.sleep(1000*tInterval);

            }
        }

    }

    /**
     * applies force between all active orbital bodies
     */
    public void evaluateForces(){
        int index=0;
        for(OrbitalBody b1:OBodies){

            for(int i=index; i<=(OBodies.size()-1); i++){
                /** if(index==1&&i==0){UI.printf("b2");}
                else if(index==0&&i==1){UI.printf("b1");}*/
                if(index!=i&&i<=(OBodies.size()-1)){
                    OrbitalBody b2=OBodies.get(i);
                    double bx1=b1.returnX();
                    double by1=b1.returnY();
                    double bx2=b2.returnX();
                    double by2=b2.returnY();
                    //UI.println("bx1 "+bx1+" by1 "+by1);
                    //UI.println("bx2 "+bx2+" by2 "+by2);
                    double dx = (bx2-bx1)/DRatio;
                    double dy = (by2-by1)/DRatio;
                    if(Math.abs(dx)>0.2){dx=(Math.abs(dx)/dx)*0.2;}
                    if(Math.abs(dy)<0.2){dy=(Math.abs(dy)/dy)*0.2;}
                    double dTotal = Math.hypot(dx, dy);//total distance vector between objects
                    double Ftotal = FRatio*(b1.returnM()*b2.returnM()*G0)/(Math.pow(dTotal,nPower));//newtons Fg equation
                    double Fx=(Ftotal*(dx/dTotal));
                    double Fy=(Ftotal*(dx/dTotal));
                    // UI.println("Fx "+Fx+" Fy "+Fy);
                    // UI.println("dx "+dx+" dy "+dy+" dTotal "+dTotal);
                    b1.applyForce(Fx, Fy); 
                    b2.applyForce(-1*Fx,-1*Fy);
                    //UI.println("force applied");

                }
                else{
                    // UI.println("na");
                }
            }
            index++;

        }//all forces are applied upon the first object b1

    }

    /**
     * draws all bodies in their new locations
     * also draws reference line
     */
    public void drawAll()
    {
        for(OrbitalBody b:OBodies){
            b.redraw();
        }
        //UI.println("drawn");
        ReferenceLine();

    }

    /**
     * Adds a new testrock with initial position, velocity and mass asked from user
     */
    public void addTestRock(){
        TestRock t1 = new TestRock(UI.askDouble("x"),UI.askDouble("y"),UI.askDouble("Vx"),UI.askDouble("Vy"));
        OBodies.add(t1);
        UI.println("new rock added");

    }
}
