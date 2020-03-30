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

    float specLow = (float) 0.03;
    float specMid = (float) 0.125;
    float specHi = (float) 0.20;

    float scoreLow = 0;
    float scoreMid = 0;
    float scoreHi = 0;

    float oldScoreLow = scoreLow;
    float oldScoreMid = scoreMid;
    float oldScoreHi = scoreHi;


    float scoreDecreaseRate =25;

    int nbCube;
    Cube[] cubes;

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
}