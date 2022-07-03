# CopilotIQ

## Question 1

Define and draw how a binary tree for the given input sequence looks like: Input Integers: 9, 8, 7, 6, 5, 4, 3, 2, 1.

```
                9
               /
              8
             /
            7
           /
          6
         /
        5
       /
      4
     /
    3
   /
  2
 /
1
```

## Question 2

Define the tree data structure, with the tree node values being integers.

See explanation in Question 3.

## Question 3

Define an algorithm in language of your choice that can create a more balanced tree over the given input defined in #1.

From the questions/requirements:

1. Define a tree data structure (from #2).
2. Define an algorithm that can create a more balanced tree (from #3).
3. There are red/black (or similar) that can rebalance, but we want something simpler (from #3).

I propose that we use a "Max-Heap" data structure to solve the balanced tree problem. The underlying data structure that
the max-heap uses is an array-list. When inserting into the tree we add a new node/leaf such that we create a complete
tree. We then "bubble up" the node so that it maintains a property where children nodes are all less than their parents.

```java
public class Heap<T extends Comparable<T>> implements TreeLike<T> {
    public void insert(T val) {
        int idx = size++;
        store.add(val);
        bubbleUp(idx);
    }

    private void bubbleUp(int index) {
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
}
```

## Question 4

Extend the data structure so you can navigate bi-directionally on each level of the tree.

We accomplish this using inheritance on the existing solution. But we create an extension point by which we can add this
behavior:

```java
public class Heap<T extends Comparable<T>> implements TreeLike<T> {
    @Override
    public void insert(T val) {
        int idx = size++;
        store.add(val);
        bubbleUp(idx);
        postInsert(val); // this
    }

    // to be overridden
    protected void postInsert(T val) {
    }
}
```

Further, we modify the node (data class) to contain a pointer to the adjacent node traversal across the same level on
the tree. See `LevelNode`:

```java
public class LevelNode<T extends Comparable<T>> implements Comparable<LevelNode<T>> {
    T val;
    LevelNode<T> sameLevelLeft;
    LevelNode<T> sameLevelRight;
}
```
