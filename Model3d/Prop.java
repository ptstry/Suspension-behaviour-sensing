import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//////////////////////////////////////////////////////////////////////////////////////////////
//////// Remember to copy the properties file to data directory of the project!!!!!  /////////
//////////////////////////////////////////////////////////////////////////////////////////////
/*
Processing looks to directory of Processing.exe for FileInputStream while debugging
After app release, default directory changes to the one with Model3d.exe
For debugging copy "data/config.properties" to "your_processing_directory/data/config.properties"
*/

public class Prop {
  
  public static int CAR_WIDTH, CAR_HEIGHT, CAR_DEPTH, WIDTH, HEIGHT;
  public static String MODE, PORT;
  
  public static void setProperties(Car car) {
    Properties prop = new Properties();
    InputStream input = null;
    
    try {
      input = new FileInputStream("data/config.properties");
      
      // load a properties file
      prop.load(input);
      
      int mult = Integer.parseInt(prop.getProperty("MULT"));
      int off = Integer.parseInt(prop.getProperty("OFFSET"));
      float multz = Float.parseFloat(prop.getProperty("MULTZ"));
      int offz = Integer.parseInt(prop.getProperty("OFFSETZ"));
      int rot = Integer.parseInt(prop.getProperty("ROTATION"));
      
      car.trimMO(mult,off,multz,offz,rot); // mult, offset, multz, offsetz
      
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
  
  public static void setSpace() {
    Properties prop = new Properties();
    InputStream input = null;
    
    try {
      input = new FileInputStream("data/config.properties");
      
      // load a properties file
      prop.load(input);
      
      CAR_WIDTH = Integer.parseInt(prop.getProperty("CAR_WIDTH"));
      CAR_HEIGHT = Integer.parseInt(prop.getProperty("CAR_HEIGHT"));
      CAR_DEPTH = Integer.parseInt(prop.getProperty("CAR_DEPTH"));
      WIDTH = Integer.parseInt(prop.getProperty("WIDTH"));
      HEIGHT = Integer.parseInt(prop.getProperty("HEIGHT"));
      MODE = prop.getProperty("MODE");
      PORT = prop.getProperty("PORT");
      
      //car.trimMO(mult,off,multz,offz); // mult, offset, multz, offsetz
      
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }
  
}