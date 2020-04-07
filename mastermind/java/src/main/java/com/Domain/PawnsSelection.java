
package com.domain;

import java.util.ArrayList;


public class PawnsSelection extends ArrayList<Pawn> {
  public boolean add(Pawn pawn) {
	  return this.size() < 4 && super.add(pawn);
  }
}
