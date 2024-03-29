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
  void update(){
    z = z - speed; 
    if ( z < 1){
      z = width; 
      x = random(-width, width);
      y = random(-height, height);
      pz = z;
    }
  }
  
  void show(){
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
