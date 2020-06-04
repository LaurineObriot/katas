import math._
import scala.util._

/**
* CodinGame planet is being attacked by slimy insectoid aliens.
**/

// Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

object Player extends App {

  // game loop
  while(true) {
	  val enemy1 = readLine // name of enemy 1
	  val dist1 = readInt // distance to enemy 1
	  val enemy2 = readLine // name of enemy 2
	  val dist2 = readInt // distance to enemy 2

	  if (dist1 < dist2) {
		  println(enemy1);
	  } else {
		  println(enemy2);
	  }
  }
}
