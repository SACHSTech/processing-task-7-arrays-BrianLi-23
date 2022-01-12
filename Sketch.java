import processing.core.PApplet;

public class Sketch extends PApplet {

  public float[] SnowY = new float[60];
  public float[] SnowPile = new float[600];
  public float[] MouseX = new float[30];
  public float[] MouseY = new float[30];
  public int indexPosition = 0;

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    // put your size call here
    size(600, 600);
  }

  /**
   * Called once at the beginning of execution. Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < SnowY.length; i++) {
      SnowY[i] = random(height);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(1, 0, 1);

    // Shifting each value of MouseX and MouseY to the right in array
    for (int i = MouseX.length - 1; i > 0; i--) {
      MouseX[i] = MouseX[i - 1];
      MouseY[i] = MouseY[i - 1];
    }

    // Adding the new values to the beginning of the array
    MouseX[0] = mouseX;
    MouseY[0] = mouseY;

    // Drawing the trail (circles)
    for (int i = 0; i < MouseX.length; i++) {
      tint(255, 128);
      ellipse(MouseX[i], MouseY[i], 30 - i, 30 - i);
    }

    // For loop for snow fall
    for (int i = 0; i < SnowY.length; i++) {
      int SnowX = width * i / SnowY.length;
      noStroke();
      ellipse(SnowX, SnowY[i], 5, 5);
      SnowY[i] += 5;

      // Checking if the key UP or DOWN is pressed and increasing or decreasing
      // snowfall speed accordingly
      if (keyPressed) {
        if (keyCode == UP) {
          SnowY[i] += 2;
        }
        if (keyCode == DOWN) {
          SnowY[i] -= 2;
        }
      }

      if (SnowY[i] > height) {
        SnowY[i] = 0;
        SnowPile[SnowX] += 1;
      }

      // Using a for loop and iterating through the snow pile array, draw the height
      // of snow pile.
      for (int SnowHeight = 0; SnowHeight < 600; SnowHeight++) {
        fill(255, 255, 255);
        rect(SnowHeight, height - SnowPile[SnowHeight], 10, height);
      }

    }
  }

  // define other methods down here.
}