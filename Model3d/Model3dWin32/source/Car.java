import processing.core.*;
import processing.serial.*;
//import static processing.core.PApplet;


public class Car extends PApplet{
  //public static PShape body;
  protected int top,bot,left,right,lvl;
  private int w,h,d;
  private int mult, offset, offsetz, calZ, zvalue; // graphical accuracy data
  private int rotleft, rotright; // rotation direction of the wheels
  private float multz;
  private Coord fl,fr,rr,rl;
  private Wheel FL,FR,RR,RL;
  private Block Chassis;
  private int width, height;
  private Serial myPort;
  public static PApplet P;
  
  public Car(int w, int h, int d) {
    this.w=w; this.h=h; this.d=d;
    top=-h/2; bot=h/2; left=-w/2; right=w/2; lvl=d/2;
    fl=new Coord(left, top+1, -lvl); // tiny fixes +1 / -1 for the edges
    fr=new Coord(right, top+1, -lvl);
    rr=new Coord(right, bot-1, -lvl);
    rl=new Coord(left, bot-1, -lvl);
    
  }
  
  public void setPlane(int a, int b){
    this.width=a;
    this.height=b;
  }
  public void setCom(String str, int baud) {
    myPort = new Serial(P, str, baud);
  }
  public void setOff(float f) {
    FL.setOff(f);
    FR.setOff(-f);
    RR.setOff(-f);
    RL.setOff(f);
  }
  public void trimMO(int mult, int offset, float multz, int offsetz, int rot) {
    this.mult = mult;
    this.offset = offset;
    this.multz = multz;
    this.offsetz = offsetz;
    rotleft = rot;
    rotright = -rot;
  }
  public void calibrate() {
    calZ = zvalue - offsetz;
  }
  public void getData() {
    int[] Buffer = new int[32];
    int c;
    int idx=0;
    while (myPort.available() > 0) {
      c = myPort.read();
      //println(c);
      //if(c == 0)
        //break;
      if(idx>=32) {
        println("data overflow");
        myPort.clear();
        break;
      }
      Buffer[idx] = c;
      idx++;
    }
    //for(int i=0; Buffer[i] != 0 ; i++) {
    //  print((byte)Buffer[i]);
    //  print(" ");
    //}
    //println("");
    //float pi2 = (float)6.283185307;
    float toRad = (float)0.0174532925199/mult; // (2pi/360) / mult
    float toRadZ = (float)0.0174532925199/multz; // (2pi/360) / mult
    
    
    if(Buffer[0] != 0) {
      FL.setRot((float)rotleft*(((0xFF & Buffer[0])-offset)*toRad));
      FR.setRot((float)rotright*(((0xFF & Buffer[2])-offset)*toRad));
      RR.setRot((float)rotright*(((0xFF & Buffer[3])-offset)*toRad));
      RL.setRot((float)rotleft*(((0xFF & Buffer[1])-offset)*toRad));
      
      zvalue = Buffer[6];
      //System.out.println(Buffer[6]);
      
      Camera.setMain((float)(((0xFF & Buffer[4])-127)*toRad),
      (float)(((0xFF & Buffer[6])-offsetz-calZ)*toRadZ),
      (float)(((0xFF & Buffer[5])-127)*toRad));
    }
    
    //println(FL.getRot());
  }
  
  public void createWheels(int a, int b, int c) { // wheel dimensions
    FL=new Wheel(fl, new Coord(-a, b, c));
    FR=new Wheel(fr, new Coord(a, b, c));
    RR=new Wheel(rr, new Coord(a, -b, c));
    RL=new Wheel(rl, new Coord(-a, -b, c));
    Wheel.setColor(255,40,40);
    //Wheel.setColor(0,0,0);
    //FL.setRot((float)1/2);
  }
  public void createBlock() {
    Chassis = new Block((int)((4f/5)*top), (int)((4f/5)*bot), (int)((1f/3)*right), (int)((4f/5)*right), (int)((1f/3)*left), (int)((4f/5)*left),
      (int)((2f/3)*bot), 3*lvl, lvl);
  }
  
  public void drawCar(PApplet P) {
    PShape body,center1,center2;
    PShape[] W = new PShape[4];
    P.lights();
    body = P.createShape(0);
    P.translate(width/2, height/2, 0);
    center1 = P.createShape(BOX, w, h, d);
    //center1 = P.createShape(BOX, 300, 100, 10);
    body.addChild(center1);
 
    center2 = P.createShape();
    Chassis.drawBlock(center2);
    body.addChild(center2);
    
    getData();
    
    W[0]=P.createShape();
    FL.drawWheel(W[0]);
    body.addChild(W[0]);
    
    W[1]=P.createShape();
    FR.drawWheel(W[1]);
    body.addChild(W[1]);
    
    W[2]=P.createShape();
    RR.drawWheel(W[2]);
    body.addChild(W[2]);
    
    W[3]=P.createShape();
    RL.drawWheel(W[3]);
    body.addChild(W[3]);
    
    Camera.rotMain(body);
    Camera.rotCam(body);
    
    center1.setFill(P.color(0,155,255));
    center2.setFill(P.color(126,207,255));

    P.shape(body);
    
  }
  
  public void buildCar(){
    System.out.println("hurray!");
    
    createWheels(w/2, h/3, d/3);
    createBlock();
  }
  
}