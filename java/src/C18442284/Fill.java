package C18442284;

import ie.tudublin.Visual;

public class Fill extends Visual
{	
	public void settings()
	{
		size(1024, 500);
	}

	public void setup() 
	{
       
        colorMode(HSB);
        noCursor();

        setFrameSize(256);

        startMinim();
        loadAudio("Visuals.mp3");
		
	}

	
	public void keyPressed()
	{
		if (key == ' ')
		{
            
            getAudioPlayer().cue(0);
            getAudioPlayer().play(); 
			
		}
	}	

    float smoothedBoxSize = 0;

	public void draw()
	{	
		
	
	noStroke();
    background(255);
    
    calculateAverageAmplitude();

		float x=0;
		while(x<width){
			float y=0;
			while(y<height){
				fill(random(0,150));
				ellipse(x+25, y+25, 50, 50);
				y=y+50;
			}
			x=x+50;
        }
        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);  
	}
}
