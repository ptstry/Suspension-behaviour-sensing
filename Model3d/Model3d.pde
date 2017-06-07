//import processing.core.*;
//import java.util.*;
import processing.serial.*;

Car F1 = new Car(700, 1400, 150);

void setup() {
  size(3000, 1700, P3D);
  background(100);
  Car.P = this;
  F1.buildCar();
  F1.setPlane(width,height);
  Camera.setPlane(width,height);
  F1.setCom("COM6",115200);
  F1.setOff(0.477465);
  //F1.setOff(0.63662);
}

void draw() {
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