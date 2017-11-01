import processing.core.*;

public class Wheel {
  
  private Coord A, B;
  private float rot;
  private PApplet P;
  private float off;
  private static Coord c; //colour
  
  public Wheel(Coord A, Coord B) {
    this.A = A;
    this.B = B;
    this.P = Car.P;
  }
  
  public void setRot(float r) {
    this.rot = r;
  }
  public float getRot() {
    return rot;
  }
  public void setOff(float f) {
    off = f;
  }
  public static void setColor(int cr, int cg, int cb) {
    c = new Coord(cr,cg,cb);
  }
  
  public void drawWheel(PShape W) {
    P.pushMatrix();
    W.rotateY(rot+off);
    W.translate(A.x, A.y, A.z);
    W.setFill(P.color(c.x, c.y, c.z));
    
    //System.out.println(A.x);
    W.beginShape(P.QUADS);
    // comments are only for FL wheel !!!
    //top
    W.vertex(0,0,0);
    W.vertex(B.x,0,0);
    W.vertex(B.x,B.y,0);
    W.vertex(0,B.y,0);
    
    //bottom
    W.vertex(0,0,B.z);
    W.vertex(B.x,0,B.z);
    W.vertex(B.x,B.y,B.z);
    W.vertex(0,B.y,B.z);
    
    //front
    W.vertex(0,0,0);
    W.vertex(B.x,0,0);
    W.vertex(B.x,0,B.z);
    W.vertex(0,0,B.z);
    
    //rear
    W.vertex(0,B.y,0);
    W.vertex(B.x,B.y,0);
    W.vertex(B.x,B.y,B.z);
    W.vertex(0,B.y,B.z);
    
    //right
    W.vertex(0,0,0);
    W.vertex(0,B.y,0);
    W.vertex(0,B.y,B.z);
    W.vertex(0,0,B.z);
    
    //left
    W.vertex(B.x,0,0);
    W.vertex(B.x,B.y,0);
    W.vertex(B.x,B.y,B.z);
    W.vertex(B.x,0,B.z);
    
    W.endShape();
    P.popMatrix();
  }
  
}