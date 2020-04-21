package ie.tudublin;

import C18442284.CubeVisual;
import C18442284.Draw;
import C18442284.Magic;
import C18442284.MyVisual;
import C18442284.Fill;
import C18442284.Final;
import C18442284.spiral;


public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
		//processing.core.PApplet.runSketch( a, new Draw());
		processing.core.PApplet.runSketch( a, new Final());
		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}