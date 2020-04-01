package C18442284;


import ie.tudublin.Visual;

public class Draw extends Visual
{	
    float a = 0;
    float b = 0;
    float co = 0;
    
	public void settings()
	{
        size(1024, 500);
        //fullScreen();
	}

	public void setup() 
	{
        startMinim();
        loadAudio("Visuals.mp3");
		 
		//noFill();
		//smooth();
        colorMode(HSB,30);
        stroke(255,0,255);
        background(0);
        smooth();
        strokeWeight(3);
		
	}

	
	public void keyPressed()
	{
		if (key == ' ')
		{
            getAudioPlayer().cue(0);
            getAudioPlayer().play();

			
		}
	}	


	public void draw()
	{   

        calculateAverageAmplitude();
        float smoothedBoxSize = 0;
        lights();
        
        stroke(co, 80, 80, 20);
  
        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);


        float x0 = map(sin(a), -1, 1, 20, width - 20);
        float y0 = map(cos(a), -1, 1, 20, height - 20);
        
        float x1 = map(sin(b), -1, 1, 20, width - 20);
        float y1 = map(cos(b), -1, 1, 20, height - 20);

   

        
        
        //background(100);
        //stroke(0);
        //point(x0, y0);
        //point(x1, y1);
        
        line(x0, y0, x1, y1);
        
        a = (float) (a + 0.071);
        b = (float) (b + 0.07);
        
        co = co + 1;
        if (co > 100) {
          co = 0;
        
        }
    }
}
