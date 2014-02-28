package com.github.bentorfs.ai.algorithms.search.minimax;

import java.util.Collection;

public interface MiniMaxNode {

   public Collection<MiniMaxNode> getPossibleMoves();

   public boolean isEndNode();

   public double getDesirability();

}
