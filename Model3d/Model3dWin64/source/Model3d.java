import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Model3d extends PApplet {

//import processing.core.*;
//import java.util.*;


Car F1 = null;


public void setup() {
  Prop.setSpace();
  F1 = new Car(Prop.CAR_WIDTH, Prop.CAR_HEIGHT, Prop.CAR_DEPTH); // car main body
  
  
  surface.setSize(Prop.WIDTH, Prop.HEIGHT);
  
  background(100);
  Car.P = this;
  F1.buildCar();
  F1.setPlane(width,height);
  Camera.setPlane(width,height);
  F1.setCom(Prop.PORT,115200);
  //F1.trimMO(6,140,2.8333333,127); // mult, offset, multz, offsetz
  Prop.setProperties(F1);
}

public void draw() {
  //delay(5);
  background(0);
  //F1.getData();
  F1.drawCar(this);
  //System.out.println(mouseX);
  //if(mousePressed) {
  //  float fov = PI/3.0; 
  //  float cameraZ = (height/2.0) / tan(fov/2.0); 
  //  perspective(fov, float(width)/float(height), cameraZ/2.0, cameraZ*2.0); 
  //} else {
  //  ortho(-width/2, width/2, -height/2, height/2);
  //}
  //Car.body.rotate(20);
}

public void keyPressed() {
  if(key == 'c'){
    F1.calibrate(); // calibrates Z axis of animation
  }
}
  public void settings() {  size(1333, 768, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Model3d" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
