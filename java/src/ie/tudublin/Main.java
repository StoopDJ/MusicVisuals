package ie.tudublin;

import C18442284.CubeVisual;
import C18442284.Magic;
import C18442284.MyVisual;
import C18442284.Fill;


public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
		processing.core.PApplet.runSketch( a, new CubeVisual());
		
	}

	public static void main(String[] args)
	{
		Main main = new Main();
		main.startUI();			
	}
}