package C18442284;

import ddf.minim.AudioPlayer;
import ddf.minim.analysis.*;
import ddf.minim.*;
import ie.tudublin.Visual;
import javafx.scene.paint.Color;

public class Final extends Visual {

    Minim minim;
    AudioPlayer song;
    FFT fft;

    // Variables that define the "areas" of the spectrum
    float specLow = (float) 0.03;
    float specMid = (float) 0.125;
    float specHi = (float) 0.20;

    // There is therefore 64% of the possible spectrum which will not be used.
    // These values ​​are generally too high for the human ear anyway.

    // Score values ​​for each zone
    float scoreLow = 0;
    float scoreMid = 0;
    float scoreHi = 0;

    // Previous values, to soften the reduction
    float oldScoreLow = scoreLow;
    float oldScoreMid = scoreMid;
    float oldScoreHi = scoreHi;

    // Softening value
    float scoreDecreaseRate = 25;

    // Cubes that appear in space
    int nbCubes;
    Cube[] cubes;

    // Lines that appear on the sides
    int nbWalls = 500;
    Wall [] walls;

    public void settings() {
        // size(800, 800, P3D);
        fullScreen(P3D, SPAN);
    }

    public void keyPressed() {
        if (key == ' ') {
            getAudioPlayer().cue(0);
            getAudioPlayer().play();

        }

    }

    public void setup() {

        colorMode(HSB);
        noCursor();

        setFrameSize(256);
        
        minim = new Minim(this);

        song = minim.loadFile("Visuals.mp3");

        fft = new FFT(song.bufferSize(), song.sampleRate());

        nbCubes = (int) (fft.specSize() * specHi);
        cubes = new Cube[nbCubes];

        walls= new Wall[nbWalls];


        // Create all objects
        // Create the cube objects
        for ( int i =  0 ; i < nbCubes; i ++ )
        {
            cubes [i] =  new  Cube ();
        }
        
        // Create the wall objects
        // Left walls
        for ( int i =  0 ; i < nbWalls; i += 4 ) 
        {
            walls [i] =  new  Wall ( 0 , height / 2 , 10 , height );
        }
          // Straight walls
        for ( int i =  1 ; i < nbWalls; i += 4 ) 
        {
            walls [i] =  new  Wall ( width , height / 2 , 10 , height );
        }
         // Low walls
        for ( int i =  2 ; i < nbWalls; i += 4 )
        {
            walls [i] =  new  Wall ( width / 2 , height , width , 10 );
        }

          // High walls
        for ( int i =  3 ; i < nbWalls; i += 4 ) {
            walls [i] =  new  Wall ( width / 2 , 0 , width , 10 );
        }
        
        // Black background
       // background ( 0 );
        
        // Start the song
        song . play ( 0 );
    }
  


    public void draw() {

    }

    // Class for cubes floating in space
    class Cube
    {
        // Z position of "spawn" and maximum Z position
        float startingZ =  - 10000 ;
        float maxZ =  1000 ;
        
        // Position values
        float x, y, z;
        float rotX, rotY, rotZ;
        float sumRotX, sumRotY, sumRotZ;

        // Builder
        Cube ()
        {
            // Make the cube appear at a random location
            x =  random ( 0 , width );
            y =  random ( 0 , height );
            z =  random (startingZ, maxZ);
            
            // Give the cube a random rotation
            rotX =  random ( 0 , 1 );
            rotY =  random ( 0 , 1 );
            rotZ =  random ( 0 , 1 );
        }
        void  display ( float  scoreLow , float  scoreMid , float  scoreHi , float  intensity , float  scoreGlobal ) {
            // Color selection, opacity determined by intensity (volume of the strip)
            int displayColor = color (scoreLow * 0.67 , scoreMid * 0.67 , scoreHi * 0.67 , intensity * 5 );
            fill (displayColor, 255 );
            
            // Color lines, they disappear with the individual intensity of the cube
            int strokeColor =  color ( 255 , 150 - ( 20 * intensity));
            stroke (strokeColor);
            strokeWeight ( 1  + (scoreGlobal / 300 ));
            // Creation of a transformation matrix to perform rotations, enlargements
            pushMatrix ();
            

            // Displacement
            translate (x, y, z);
            
            // Calculate the rotation as a function of the intensity for the cube
            sumRotX += intensity * (rotX / 1000 );
            sumRotY += intensity * (rotY / 1000 );
            sumRotZ += intensity * (rotZ / 1000 );
            
            // Apply rotation
            rotateX (sumRotX);
            rotateY (sumRotY);
            rotateZ (sumRotZ);
            
            // Creation of the box, variable size depending on the intensity for the cube
            box ( 100 + (intensity / 2 ));
            
            // Apply the matrix
            popMatrix ();
            
            // Move Z
            z += ( 1 + (intensity / 5 ) + ( pow ((scoreGlobal / 150 ), 2 )));
            
            // Replace the box at the back when it is no longer visible
            if (z >= maxZ) {
            x =  random ( 0 , width );
            y =  random ( 0 , height );
            z = startingZ;
            }
        }
    }

    class Wall
    {

        // Minimum and maximum position Z
        float startingZ =  - 10000 ;
        float maxZ =  50 ;
        
        // Position values
        float x, y, z;
        float sizeX, sizeY;

        //constructor for wall class
        public Wall(int i, int j, int k, int height) {
        }

        // Builder
        void Wall(float x, float y, float sizeX, float sizeY)
        {
            // Make the line appear at the specified location
            this . x = x;
            this . y = y;
            // Random depth
            this . z =  random (startingZ, maxZ);  
            
            // We determine the size because the walls on the floors have a different size than those on the sides
            this . sizeX = sizeX;
            this . sizeY = sizeY;
        }
         // Display function
        void  display ( float  scoreLow , float  scoreMid , float  scoreHi , float  intensity , float  scoreGlobal ) {
            // Color determined by low, medium and high tones
            // Opacity determined by the overall volume
            int displayColor =  color (scoreLow * 0.67 , scoreMid * 0.67 , scoreHi * 0.67 , scoreGlobal);
            
            // Make the lines disappear in the distance to give an illusion of fog
            fill (displayColor, ((scoreGlobal - 5 ) / 1000 ) * ( 255 + (z / 25 )));
            noStroke ();
            
            // First band, the one that moves according to the force
            // Transformation matrix
            pushMatrix ();
            
            // Displacement
            translate (x, y, z);
            
            // Enlargement
            if (intensity >  100 ) intensity =  100 ;
            scale (sizeX * (intensity / 100 ), sizeY * (intensity / 100 ), 20 );
            
            // Creation of the "box"
            box ( 1 );
            popMatrix ();
            
            // Second strip, the one that is always the same size
            displayColor =  color (scoreLow * 0.5 , scoreMid * 0.5 , scoreHi * 0.5 , scoreGlobal);
            fill (displayColor, (scoreGlobal / 5000 ) * ( 255 + (z / 25 )));
            // Transformation matrix
            pushMatrix ();
            
            // Displacement
            translate (x, y, z);
            
            // Enlargement
            scale (sizeX, sizeY, 10 );
            
            // Creation of the "box"
            box ( 1 );
            popMatrix ();
            
            // Move Z
            z += ( pow ((scoreGlobal / 150 ), 2 ));
            if (z >= maxZ) {
            z = startingZ;  
            }
        }
    }
   

	public int color(double d, double e, double f, float g) {
		return 0;
	}
}