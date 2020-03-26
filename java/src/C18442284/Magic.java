package C18442284;

import ie.tudublin.Visual;


public class Magic extends Visual
{

    int Csize =300;
    float angle, angle1, angle2;

	public void settings()
	{
        size(1024, 500);
	}

	public void setup() 
	{      
        setFrameSize(256);
        colorMode(HSB);
        //noCursor();
        startMinim();
        loadAudio("Visuals.mp3");
        rectMode(CENTER);
        //noStroke();
        background(255);
        angle1 = random(PI);
        angle2 = random(PI);
    
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
        //stroke(map(getSmoothedAmplitude(), 0, 1, 0, 255), 255, 255);

        angle1 += 0.0013;
        angle2 += 0.0002;
        angle = angle1 + angle2;

        translate(width/2, height/2);
        rotate(angle2-angle1);

       

        float boxSize = 50 + (getAmplitude() * 300);//map(average, 0, 1, 100, 400); 
        smoothedBoxSize = lerp(smoothedBoxSize, boxSize, 0.2f);

        for(int j=0; j<2; j++)
        {
            pushMatrix();
            for(int i=0; i<70; i++)
            {
                rotate((-1+2*j)*angle/2);
                scale((float) 0.95);
                float a = (float) (i * PI / 40. + angle1 * 2 * i);
                fill(255 + 140 *sin(a), 355-140 * sin(a + TAU/3), 255 + 140 * sin(a - TAU/3));
                float dia = 30 + 20 * sin(3*angle2) + 25 * sin((float) (i * HALF_PI / 40.));
                f(dia,i);         
            }
            popMatrix();
        }
    }

   
    public void f(float r, int i)
    {
        float rx = 15 * cos(TAU * 1/70), ry =15 * sin(TAU * 1/70);
        if(random(1) <0.5 * (1 + cos(1)))rect (Csize +rx,0 +ry,r,r);
        else 
        if(random(1) <0.5 * (1 + cos(1)))rect (-Csize +rx,0 +ry,r,r);
        else 
        if(random(1) <0.5 * (1 + cos(1)))rect (0 +rx,Csize +ry,r,r);
        else 
        if(random(1) <0.5 * (1 + cos(1)))rect (0 +rx,-Csize +ry,r,r);
        

    }
    public void mousePressed()
    {
        noLoop();
    }
}

