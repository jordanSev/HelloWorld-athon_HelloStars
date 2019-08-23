import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class HelloWorld extends PApplet {

ArrayList<Object> ob; 
Star[] stars = new Star[1000]; 
Text t = new Text(); 
Object o, x, s; 
float a = 0; 
float speed; 


public void setup(){
 
   //canvas
  //Object 
  ob = new ArrayList<Object>(); 
  Object o = new Object(-60,-60,-60,20); 
  ob.add(o); 
  
  
  //loop to institiate stars
  for (int i = 0; i < stars.length; i++){
    stars[i] = new Star();
  }
}


public void draw(){ 
  background(0); //background
  stroke(255); //background
  noFill(); //object
  lights(); //object
  
  //Object rotation physics
  translate(width/2, height/2);
 
  
  speed = map(mouseX, 0, width, 0, 30);
  
  //making the stars
  for (int i = 0; i < stars.length; i++){
    stars[i].update(); 
    stars[i].show(); 
  }
  a += 0.01f; 
   t.show(); 
  
}
/*import processing.sound*; 
SoundFile file; 

String audioN = "star-wars-theme-song.mp3"; 
String path; 

void setup(){
 path = sketchPath(audioN); 
 file = new SoundFile(this, path); 
 file.play(); 
 
}

void draw(){
}
*/
class Object{
  
 //Location of hello world 2d/3d rendering
 PVector position; 
 float r; 
 
 //Making box for given coordinates of our object
 Object(float x, float y, float z, float q){
 position = new PVector(x,y,z); 
 r = q; 
 }
 
 
 
 /*
 *Generating new objects as an Array to the Object
 *Returning the Array of Cubes
 */
 public ArrayList<Object> generate(){ 
   ArrayList<Object> objects = new ArrayList<Object>(); 
     for (int x = -1; x < 2 ; x++){
     for (int y = -1; y < 2 ; y++){
       for (int z = -1; z < 2 ; z++){
         int sum = abs(x) + abs(y) + abs(z);
         float R = r/3;
         if (sum >1){
             Object c = new Object(position.x+x*R, position.y+y*R, position.z+z*R, R); 
             objects.add(c); 
           }
         }
       }
     }
   
   return objects; 
 }
 
 
 //Making the object appear
 public void show(){
   pushMatrix();
   
   translate(position.x, position.y, position.z);
   fill(255); 
   noStroke(); 
   box(r); 
   popMatrix(); 
   rotateX(a*0.4f);
  rotateY(a*0.4f); 
  rotateZ(a*0.4f);
 }
 
 
}
class Star {
  
 //Declaring variables for positions
 float x;
 float y;
 float z;
 float pz; 

//Giving Random x, y, and z values for the objects
  Star() {
     x = random(-width, width);
     y = random(-height, height);
     z = random(width); 
     pz = z;
  }
  
  
  //updating the stars that are in the background
  public void update(){
    z = z - speed; 
    if ( z < 1){
      z = width; 
      x = random(-width, width);
      y = random(-height, height);
      pz = z;
    }
  }
  
  public void show(){
   fill(random(255),random(255),random(255)); 
   noStroke(); 
   
   float sx = map(x / z, 0, 1, 0, width);
   float sy = map(y / z, 0, 1, 0, height);
   
   float r = map(z, 0, width, 16, 0); 
   ellipse(sx, sy, r, r);
   
   float px = map(x / pz, 0, 1, 0, width);
   float py = map(y / pz, 0, 1, 0, height);
   
   pz = z; 
   
   stroke(random(255),random(255),random(255)); 
   line(px, py, sx, sy);    
  }
  
}
class Text{
  
   float x; 
   float y; 
   float z; 
   float h; 
   float w; 
   String hello;
   
  Text(){
   hello = new String ("HELLO WORLD!");
   x = -10; 
   y = -80; 
   z = 0; 
   w = 150; 
   h = 150; 
  }

public void show(){
    //pushMatrix();
    //rotateY(map(mouseX,0,width,-PI,PI));
    //rotateX(map(mouseY,0,width,-PI,PI));
    fill(195); 
    textSize(32); 
    text(hello, x, y, w, h);
    //popMatrix(); 
}
}
  public void settings() {  size(1280, 720, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "HelloWorld" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
