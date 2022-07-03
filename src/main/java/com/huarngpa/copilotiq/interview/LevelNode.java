package com.huarngpa.copilotiq.interview;

/**
 * Special tree node that provides access to adjacent left/right nodes at the same level of the
 * tree.
 */
public class LevelNode<T extends Comparable<T>> implements Comparable<LevelNode<T>> {
  T val;
  LevelNode<T> sameLevelLeft;
  LevelNode<T> sameLevelRight;

  public LevelNode(T val) {
    this.val = val;
  }

  @Override
  public int compareTo(LevelNode other) {
    return val.compareTo((T) other.val);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof LevelNode<?>)) {
      return false;
    }
    return val.equals(((LevelNode<?>) obj).val);
  }

  @Override
  public String toString() {
    return String.format("LevelNode(val=%s)", val);
  }
}
