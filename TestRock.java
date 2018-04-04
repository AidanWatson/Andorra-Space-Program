import ecs100.*;
import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JColorChooser;
/**
 * Write a description of class TestRock here.
 *
 * @author Aidan Watson
 */
public class TestRock implements OrbitalBody
{
    // kinematic fields
    double x;
    double y;
    double Vx;
    double Vy;
    double mass;
    private double radius = 30;
    private double elasticity;//larger numbers are less elastic, can not go below one
    //int moveCount=0;

    /**constructor for TestRock objects*/
    public TestRock(double x1,double y1,double Vx1, double Vy1){
        x=x1;
        y=y1;
        Vx=Vx1;
        Vy=Vy1;
        mass=15;
        elasticity=5;
        
        //ID=1;
    }

    public TestRock(double x1,double y1,double Vx1, double Vy1, double m){
    x=x1;
    y=y1;
    Vx=Vx1;
    Vy=Vy1;
    mass=m;
    elasticity=5;
    //ID=num;
    }
   

    public void applyForce(double Fx, double Fy)//applys a force of size in the direction 
    {
        double Fx1=Fx;
        double Fy1=Fy;
       /** if(Fx<1){
         Fx1=(Fx/(Math.abs(Fx)))*(Math.log(Math.abs(Fx))/Math.log(10));}
        if(Fy<1){
         Fy1=(Fy/(Math.abs(Fy)))*(Math.log(Math.abs(Fy))/Math.log(10));}
         */
        Vx=Vx+(Fx1/mass);
        Vy=Vy+(Fy1/mass);
        if(Math.abs(Vy)>10){Vy=(Math.abs(Vy)/Vy)*10;}
        if(Math.abs(Vx)>10){Vx=(Math.abs(Vx)/Vx)*10;}
          // UI.printf("%i  ", ID);
           //UI.println("Vx "+Vx+" Vy "+Vy +" X "+x+" Y "+y);
          // UI.println("");
    }

    public void move(){
       // boolean separation = SpaceProgramMain.GetSep();
        //moveCount++; 
        //UI.println("move count: "+moveCount);
       
         x=x+Vx;
        y=y+Vy;
        if(x-radius/2<0){x=radius/2; Vx=-1*(Vx/elasticity);}
        if(y-radius/2<0){y=radius/2; Vy=-1*(Vy/elasticity);}
        if(x+radius/2>1050){x=1050-radius/2; Vx=-1*(Vx/elasticity);}
        if(y+radius/2>800){y=800-radius/2; Vy=-1*(Vy/elasticity);}
    
}

    public boolean touching(double x, double y, double radius){
        return false;
    }

    public void redraw(){
        UI.setColor(Color.black);
        UI.fillOval(x-radius/2,y-radius/2,radius*2,radius*2);
    }

    public double returnX(){
        return x;
    }

    public double returnY(){
        return y;
    }

    public double returnM(){
        return mass;
    }
     public double returnVx(){
        return Vx;
    }
     public double returnVy(){
        return Vy;
    }
   

}
