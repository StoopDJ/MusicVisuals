# Music Visualiser Project

Name: Amir Akbari

Student Number: C18442284

# Description of the assignment
Aim of this assignemt was something beautiful while listening to music. so i have created my own processing sketches, I really liked this module so from the start of semaster I was working on this and created lots of sketches and for this assignment I had to choose best ones which goes and matches with the music in the background.
I have chosen 6 classes for this assignment and I used FFT funtion for my music whcih would be react to the music. I have put up all of this classes together in one big master file and made each one of them in a loop using keyboard so while im running the programe I can swap between each loop at any time I needed.
there is 6 loops in my Final file but i only used 5 of them i think the other one wasnt matching the scene so i didnt use it at all.

# Instructions
my own code is in a new branch which is called "FINAL" there is another branch called "PROJECT" that was only my practice one and it is not finished.
to view my codes please change the branch to "FINAL", I have created a folder with my student number C18442284 which all my codes are in it. I have couple of files there for this assignmetn but again the main file which would contains all the loops together is the Final.java
simply run the Final.java and use the keyboard (#1-#6) u can loop through all 6 functions in any order you want, i have created a video of my order which I think would be the best match.

# How it works
I have 6 diagram or basically 6 loops 

1. loop1: is the best one  i think but i have so many codes for it so i wont be able to post it here because there is too much, but basically i would draw cubes in this sloop using my CUBE class and reatcs to the music and then i have draw the wave line on each side of screen top left, top right, bottom left and bottom right and these would react to the music aswell. for code please look at Final.java

2. loop 2: it is a simple program which i draw a rect and using an angle to rotate it.
This is code:

```Java
  public void loop2()
    {
        // scale(0.5f* map(mouseY, 0, height,0, 10));
        background(0);
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
```
3. loop 3: this program again pne simle one i only draw ellipse and fill it randomly
This is Code:
```Java
    public void loop3()
    {
        noStroke();
        background(255);

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

    }
```
4. loop 4: its another simple one, just draws random lines.

```Java
  public void loop4()
    {
       // background(0);
        fill(0, 10);
        noStroke();
        rect(0, 0, width, height);
        
        stroke(random(255), 255, 255);
        float x = random(width);
        line(x, 0, x, height);
       
    }
```
5. loop 5: this one it too simple i did as practice i have it in my Final.java but i have used it in my video i think it would match the music and background so thats why i havent use it i had another idea before to use it but i changed my mind.
```Java
    public void loop5()
    {
         
        loadPixels();

        for(int i = 0; i < pixels.length; i++) {
          pixels[i] = color(random(255));
        }
        
        updatePixels();
    }
```    
6. loop 6: another simple idea, i saw a video on youtube i tired to do that but i couldnt i got this instead but i was happy at the start of it its not cool bur=t it gets cool and nice.it is simple enough it basically a line keeps getting bigger and goes around using Cos and Sin.

```Java
 public void loop6 ()
    {
          
        colorMode(HSB,30);
        stroke(255,0,255);
        smooth();
        strokeWeight(3);
		

        lights();
        
        stroke(co, 80, 80, 20);
  

        float x0 = map(sin(a), -1, 1, 20, width - 20);
        float y0 = map(cos(a), -1, 1, 20, height - 20);
        
        float x1 = map(sin(b), -1, 1, 20, width - 20);
        float y1 = map(cos(b), -1, 1, 20, height - 20);
        
        line(x0, y0, x1, y1);
        
        a = (float) (a + 0.071);
        b = (float) (b + 0.07);
        
        co = co + 1;
        if (co > 100) {
          co = 0;
        
        }
    }
```
# What I am most proud of in the assignment
Im so proud of this assignemt, I made what was looking for one of the most thing im proud of it is the loop1 which i have worked on that for nearly 2 months.
the Cube sketch which I called I new dimensions, I think I did a good job there and that is the one Im most proud of it.

```
This is a youtube video

[![YouTube](http://img.youtube.com/vi/QMWbjcXvcZo/mq3.jpg)](https://www.youtube.com/watch?v=QMWbjcXvcZo&t=8s)




