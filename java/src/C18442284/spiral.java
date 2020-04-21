package C18442284;

import ie.tudublin.Visual;

public class spiral extends Visual
{
    public void settings()
        {
            size(1024, 500);
            //fullScreen();
        }

        
    public void setup()
    {
        
        cx = width / 2;
        cy = height / 2;
    }

    float cx, cy;
    public void draw()
    {
        background(0);
        stroke(255);
        strokeWeight(5);
        int points = 6;
        float radius = 10;
        float thetaInc = TWO_PI / (float) points;
        noFill();
        beginShape();
        for (int i = 0 ; i <= points * 10 ; i ++)
        {
            float theta = thetaInc * i;
            float x = cx + sin(theta) * radius;
            float y = cy - cos(theta) * radius;
            vertex(x, y);
            radius += 2;
        }
        endShape();
    }
}