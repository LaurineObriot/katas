<?php
/**
* CodinGame planet is being attacked by slimy insectoid aliens.
**/

// Most simple puzzle : you have to shoot down all enemies coming at you before they reach you. Each turn you have the choice between two enemies, so you just have to compare the distance that separates each one from you, and shoot the closest one.

// game loop
while (TRUE)
{
  fscanf(STDIN, "%s",
	  $enemy1 // name of enemy 1
  );
  fscanf(STDIN, "%d",
	  $dist1 // distance to enemy 1
  );
  fscanf(STDIN, "%s",
	  $enemy2 // name of enemy 2
  );
  fscanf(STDIN, "%d",
	  $dist2 // distance to enemy 2
  );

  if ($dist1 < $dist2) {
	  echo ($enemy1."\n");
  } else {
	  echo ($enemy2."\n");
  };
}
?>
