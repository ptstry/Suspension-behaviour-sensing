import processing.core.*;

public class Camera {
  private static int X,Y;
  private static float rotX, rotY, baseX, baseY;
  private static PApplet P = Car.P;
  private static int width, height;
  private static boolean lock;
  
  public static void rotCam(PShape body) {
    int mX, mY;
    //body.rotateX(rot);
    //rot+=0.01;
    //System.out.println(X);
    mX = P.mouseX;
    mY = P.mouseY;
    if(P.mousePressed){
      
      
      //mX -= width/2;
      //mY -= height/2;
      rotY += (float)(mX-X)/1000;
      rotX += (float)(mY-Y)/1000;
      
      lock = false;
      body.rotateX(rotX + baseX);
      body.rotateY(rotY + baseY);
    } 
    else {
      if(!lock){
        baseX += rotX;
        baseY += rotY;
        rotX = 0;
        rotY = 0;
        lock = true;
      }
      body.rotateX(baseX);
      body.rotateY(baseY);
    }
    X = mX;
    Y = mY;
    
  }
  
  private static float bX, bY, bZ;
  
  public static void setMain(float p, float y, float r) {
    bX = r;
    bY = p;
    bZ = y;
  }
  
  public static void rotMain(PShape body) {
    //P.pushMatrix();
    body.rotateX(bX);
   // P.popMatrix();
   // P.pushMatrix();
    body.rotateY(bY);
   // P.popMatrix();
    body.rotateZ(bZ);
  }
  
  public static void setPlane(int w, int h) {
    width = w;
    height = h;
  }
  
}