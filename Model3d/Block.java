import processing.core.*;

public class Block {
  private Coord fl,fr,rr,rl,mid;
  private int lvl;
  private PApplet P;
  
  public Block(int top, int bot, int fleft, int left, int fright, int right, int mid, int peak, int lvl) {
    this.fl=new Coord(fleft, top, 0);
    this.fr=new Coord(fright, top, 0);
    this.rr=new Coord(right, bot, 0);
    this.rl=new Coord(left, bot, 0);
    this.mid=new Coord(0, mid, peak);
    this.lvl=lvl;
    this.P=Car.P;
    System.out.println(fleft);
  }
  
  public void drawBlock(PShape B) {
    P.pushMatrix();
    B.translate(0,0,lvl);
    B.beginShape(P.TRIANGLES);
    
    //front
    B.vertex(fl.x, fl.y, fl.z);
    B.vertex(fr.x, fr.y, fr.z);
    B.vertex(mid.x, mid.y, mid.z);
    
    //rear
    B.vertex(rl.x, rl.y, rl.z);
    B.vertex(rr.x, rr.y, rr.z);
    B.vertex(mid.x, mid.y, mid.z);
    
    //left
    B.vertex(fl.x, fl.y, fl.z);
    B.vertex(rl.x, rl.y, rl.z);
    B.vertex(mid.x, mid.y, mid.z);
    
    //right
    B.vertex(fr.x, fr.y, fr.z);
    B.vertex(rr.x, rr.y, rr.z);
    B.vertex(mid.x, mid.y, mid.z);
    
    //System.out.println(fl.y);
    
    B.endShape();
    P.popMatrix();
  }
  
}