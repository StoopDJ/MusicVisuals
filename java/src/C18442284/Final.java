package C18442284;

import ddf.minim.AudioPlayer;
import ddf.minim.analysis.*;
import ddf.minim.*;
import ie.tudublin.Visual;


public class Final extends Visual 
{

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
        colorMode(HSB);
        noCursor();
        
        setFrameSize(256);

        startMinim();
        loadAudio("Visuals.mp3");
        //getAp().play();
        //startListening(); 
        
    }
}