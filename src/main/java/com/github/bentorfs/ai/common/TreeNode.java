package com.github.bentorfs.ai.common;

import java.util.Collection;

public interface TreeNode {

   /**
    * Returns all child nodes that are accessible from this node
    */
   public abstract Collection<TreeNode> getChildNodes();

   /**
    * Returns whether this tree node represents a solution
    */
   public abstract boolean isSolutionNode();

   /**
    * Returns the numerical value associated with this tree node
    */
   public abstract double getValue();

}
