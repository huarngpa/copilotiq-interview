package com.huarngpa.copilotiq.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Balanced binary tree solution. The tree will be laid out in a max-heap fashion such that each
 * node is greater than or equal to the values in the children of that node. The underlying data
 * structure of this heap is an ArrayList, which provides O(1) access to nodes.
 */
public class Heap<T extends Comparable<T>> implements TreeLike<T> {

  protected int size = 0;
  protected final List<T> store = new ArrayList<>();

  public Heap() {}

  /** Gets the internal storage of the heap. */
  public List<T> getStore() {
    return store;
  }

  /**
   * Finds the parent of the provided index.
   *
   * @param index
   * @return
   */
  protected int parent(int index) {
    return index / 2;
  }

  /**
   * Returns the left child of the index/node in the tree.
   *
   * @param index
   * @return
   */
  protected int left(int index) {
    return index * 2;
  }

  /**
   * Returns the right child of the index/node in the tree.
   *
   * @param index
   * @return
   */
  protected int right(int index) {
    return index * 2 + 1;
  }

  /**
   * Swaps the values between index i and j.
   *
   * @param i
   * @param j
   */
  protected void swap(int i, int j) {
    T temp = store.get(i);
    store.set(i, store.get(j));
    store.set(j, temp);
  }

  /**
   * Bubble a node up the tree if it has a value greater than its parent.
   *
   * @param index
   */
  protected void bubbleUp(int index) {
    int i = index;
    Function<Integer, Boolean> canBubbleUp =
        (idx) -> {
          T parent = store.get(parent(idx));
          T current = store.get(idx);
          return parent.compareTo(current) < 0;
        };
    while (canBubbleUp.apply(i)) {
      swap(parent(i), i);
      i = parent(i);
    }
  }

  /**
   * Insert a value into the heap.
   *
   * @param val
   */
  @Override
  public void insert(T val) {
    int idx = size++;
    store.add(val);
    bubbleUp(idx);
    postInsert(val);
  }

  /**
   * Post-insertion hook for overriding insertion behavior.
   *
   * @param val
   */
  protected void postInsert(T val) {}

  @Override
  public String toString() {
    return "Heap=" + store.subList(0, size) + " size=" + size;
  }
}
