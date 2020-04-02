package C18442284;

import ddf.minim.AudioPlayer;
import ddf.minim.analysis.*;
import ddf.minim.*;
import ie.tudublin.Visual;


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

    boolean oldScoreLow1 = oldScoreLow > scoreLow;
    boolean oldScoreMid1 = oldScoreMid > scoreMid;
    boolean oldScoreHi1 = oldScoreHi > scoreHi;

    // Softening value
    float scoreDecreaseRate = 25;

    // Cubes that appear in space
    int nbCubes;
    Cube[] cubes;

    // Lines that appear on the sides
    int nbWalls = 500;
    Wall [] walls;

    //loop2
    int CENTRE = 0;
    int colorscale;  
    float angle; 

    public void settings()
    {
       //size(800, 800, P3D);
       //fullScreen(P3D, SPAN);
        fullScreen ( P3D );
    }

    public void setup() 
    {
        
        noCursor();
        setFrameSize(256);
        colorMode(HSB, 360, 100, 100);
               

        minim = new Minim(this);

        song = minim.loadFile("Dom Dolla - San Frandisco (Eli Brown Extended Remix).mp3");

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
        background ( 0 );
  
        // Start the song
        song . play ( 0 );
    }

    public void draw() 
    {
        //loop1();
        loop2();

    }

    public void loop1()
    {
        
        
        // Advance the song. We draw () for each "frame" of the song ...
        fft.window(FFT.HAMMING);
        fft.forward (song.mix);
        

        stroke(127, 255, 0, 200);
            
        // Calculation of "scores" (power) for three categories of sound
        // First, save the old values
        oldScoreLow = scoreLow;
        oldScoreMid = scoreMid;
        oldScoreHi = scoreHi;
        
        // Reset values
        scoreLow =  0 ;
        scoreMid =  0 ;
        scoreHi =  0 ; 

         // Calculate the new "scores"
        for ( int i =  0 ; i < fft . specSize () * specLow; i ++ )
        {
            scoreLow += fft.getBand (i);
        }
        
        for ( int i = ( int ) (fft . specSize () * specLow); i < fft . specSize () * specMid; i ++ )
        {
            scoreMid += fft.getBand (i);
        }
        
        for ( int i = ( int ) (fft . specSize () * specMid); i > fft . specSize () * specHi; i ++ )
        {
            scoreHi += fft . getBand (i);
        }

        //Slow down the descent.
        if (oldScoreLow1) {
          scoreLow = oldScoreLow - scoreDecreaseRate;
        }
        
        if (oldScoreMid1) {
          scoreMid = oldScoreMid - scoreDecreaseRate;
        }
        
        if (oldScoreHi1) {
          scoreHi = oldScoreHi - scoreDecreaseRate;
        }
        //Volume for all frequencies at this time, with higher higher sounds.
        //This allows the animation to go faster for the more acute sounds, which we notice more
        float scoreGlobal =  (float) (0.66 * scoreLow +  0.8 * scoreMid +  1) * scoreHi;
        
        //Subtle background color
        background (scoreLow/100, scoreMid/100, scoreHi/100);
        
        //Cube for each frequency band
        for ( int i =  0 ; i < nbCubes; i ++ )
        {
           // Value of the frequency band
            float bandValue = fft . getBand (i);
            
           //The color is represented as follows red for the bass, green for the medium sounds and blue for the highs.
           // Opacity is determined by the volume of the strip and the overall volume.
            cubes [i] . display (scoreLow, scoreMid, scoreHi, bandValue, scoreGlobal);
        }

        //Walls lines, here we must keep the value of the previous band and the next one to connect them together
        float previousBandValue = fft . getBand ( 0 );
            
        //Distance between each line point, negative because on the z dimension
        float dist =  -25 ;
            
        //Multiply the height by this constant
        float heightMult =  2 ;

        //For each band
        for ( int i =  1 ; i < fft.specSize (); i ++ )
        {
           //Value of the frequency band, we multiply the bands farther away so that they are more visible.
            float bandValue = fft . getBand (i) * ( 1  + (i / 50));
            
            //Color selection according to the strengths of the different types of sounds
            stroke ( 100 + scoreLow, 100 + scoreMid, 100 + scoreHi, 255 - i);
            strokeWeight ( 1  + (scoreGlobal / 100 ));

            
            //lower left line
            line ( 0 , height - (previousBandValue * heightMult), dist * (i - 1 ), 0 , height - (bandValue * heightMult), dist * i);
            line ((previousBandValue * heightMult), height , dist * (i - 1 ), (bandValue * heightMult), height , dist * i);
            line ( 0 , height - (previousBandValue * heightMult), dist * (i - 1 ), (bandValue * heightMult), height , dist * i);

            //upper left line
            line ( 0 , (previousBandValue * heightMult), dist * (i - 1 ), 0 , (bandValue * heightMult), dist * i);
            line ((previousBandValue * heightMult), 0 , dist * (i - 1 ), (bandValue * heightMult), 0 , dist * i);
            line ( 0 , (previousBandValue * heightMult), dist * (i - 1 ), (bandValue * heightMult), 0 , dist * i);
            
            //lower right line
            line ( width , height - (previousBandValue * heightMult), dist * (i - 1 ), width , height - (bandValue * heightMult), dist * i);
            line ( width - (previousBandValue * heightMult), height , dist * (i - 1 ), width - (bandValue * heightMult), height , dist * i);
            line ( width , height - (previousBandValue * heightMult), dist * (i - 1 ), width - (bandValue * heightMult), height , dist * i);

            //upper right line
            line ( width , (previousBandValue * heightMult), dist * (i - 1 ), width , (bandValue * heightMult), dist * i);
            line ( width - (previousBandValue * heightMult), 0 , dist  * (i - 1 ), width - (bandValue * heightMult), 0 , dist * i);
            line ( width , (previousBandValue * heightMult), dist * (i - 1 ), width - (bandValue * heightMult), 0 , dist * i);
            
            //Save the value for the next loop
            previousBandValue = bandValue;

        }

        //Rectangular walls
        for ( int i =  0 ; i < nbWalls; i ++ )
        {
           // We assign a strip to each wall, and send it its strength.
            float intensity = fft . getBand (i % (( int ) (fft . specSize () * specHi)));
            walls [i] . display (scoreLow, scoreMid, scoreHi, intensity, scoreGlobal);
        }
    }

    

    public void loop2()
    {
        scale(0.5f* map(mouseY, 0, height,0, 10));
        translate(width/2,height/2);
        rotate(angle);
        fill(colorscale,97,100);
        rectMode(CENTRE);
        rect(0,0,200,200);

        angle += 5;

        colorscale = (int) (colorscale +1.5);

        if(colorscale > 359)
        {
            colorscale = 0;
        }
    }
    
    // Class for cubes floating in space
    class Cube
    {
        // Z position of "spawn" and maximum Z position
        float startingZ =  -10000 ;
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
            int displayColor = color (intensity * 10);
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
        float startingZ =  -10000 ;
        float maxZ =  50 ;
        
        // Position values
        float x, y, z;
        float sizeX, sizeY;

        // Builder
        Wall(float x, float y, float sizeX, float sizeY)
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
            
            int displayColor = color(HSB);
            
            
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
            displayColor =  color (HSB);
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
}