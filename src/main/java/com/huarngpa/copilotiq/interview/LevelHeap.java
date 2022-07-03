package com.huarngpa.copilotiq.interview;

public class LevelHeap<T extends Comparable<T>> extends Heap<LevelNode<T>> {

  /** Data class for tree level metadata. */
  protected static class TreeLevel {
    int level;
    int leftmost;
    int rightmost;

    public TreeLevel(int level, int leftmost, int rightmost) {
      this.level = level;
      this.leftmost = leftmost;
      this.rightmost = rightmost;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof TreeLevel other)) {
        return false;
      }
      return level == other.level && leftmost == other.leftmost && rightmost == other.rightmost;
    }

    @Override
    public String toString() {
      return String.format(
          "TreeLevel(level=%d, leftmost=%d, rightmost=%d)", level, leftmost, rightmost);
    }
  }

  @Override
  protected void postInsert(LevelNode<T> ignored) {
    for (int index = 0; index < store.size(); index++) {
      TreeLevel treeLevel = treeLevel(index);
      LevelNode<T> node = store.get(index);
      if (index == 0) {
        node.sameLevelLeft = null;
        node.sameLevelRight = null;
        continue;
      }
      node.sameLevelLeft = index > treeLevel.leftmost ? store.get(index - 1) : null;
      node.sameLevelRight =
          index < treeLevel.rightmost - 1 && index + 1 < store.size() ? store.get(index + 1) : null;
    }
  }

  /**
   * Determines the tree level of a given index.
   *
   * @param index
   */
  protected TreeLevel treeLevel(int index) {
    if (index == 0) {
      return new TreeLevel(0, 0, 1);
    }
    int pow = 0;
    int bound = 0;
    while (index >= bound) {
      bound += Math.pow(2, pow);
      pow++;
    }
    return new TreeLevel(pow - 1, (int) (bound - Math.pow(2, pow - 1)), bound);
  }
}
