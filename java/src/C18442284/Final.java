package C18442284;

import ddf.minim.AudioPlayer;
import ddf.minim.analysis.*;
import ddf.minim.*;
import ie.tudublin.Visual;


public class Final extends Visual 
{

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
    float scoreDecreaseRate =25;

    // Cubes that appear in space
    int nbCube;
    Cube[] cubes;

    // Lines that appear on the sides
    int nbMurs = 500;
    Mur[] murs;
    

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
    public void setup()
    {   
        minim = new Minim(this);

        song = minim.loadFile("Visuals.mp3");

        fft = new FFT(song.bufferSize(), song.sampleRate());


        nbCube = (int)(fft.specSize()*specHi);
        cubes = new Cube[nbCube];

        murs = new Mur[nbMurs];

        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);  
    }

    public void draw()
    {

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

    }
}