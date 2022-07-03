package com.huarngpa.copilotiq.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LevelHeapTest {

  private static Stream<Arguments> treeLevelSource() {
    return Stream.of(
        Arguments.of(0, new LevelHeap.TreeLevel(0, 0, 1)),
        Arguments.of(2, new LevelHeap.TreeLevel(1, 1, 3)),
        Arguments.of(3, new LevelHeap.TreeLevel(2, 3, 7)),
        Arguments.of(4, new LevelHeap.TreeLevel(2, 3, 7)));
  }

  @ParameterizedTest
  @MethodSource("treeLevelSource")
  public void test_treeLevel(Integer index, LevelHeap.TreeLevel expected) {
    LevelHeap<LevelNode<Integer>> heap = new LevelHeap<>();
    assertEquals(expected, heap.treeLevel(index));
  }

  @Test
  public void test_sameLevelLink() {
    LevelHeap<LevelNode<Integer>> heap = new LevelHeap<>();
    heap.insert(new LevelNode(Integer.valueOf(1)));
    heap.insert(new LevelNode(Integer.valueOf(2)));
    heap.insert(new LevelNode(Integer.valueOf(3)));
    List<Integer> vals =
        heap.getStore().stream()
            .map(e -> (Object) e.val)
            .map(e -> (Integer) e)
            .collect(Collectors.toList());
    assertEquals(Arrays.asList(3, 2, 1), vals);
    // Test same-level TreeNode(2)
    assertNull(heap.getStore().get(1).sameLevelLeft);
    assertEquals(Integer.valueOf(1), (Integer) (Object) heap.getStore().get(1).sameLevelRight.val);
    // Test same-level TreeNode(1)
    assertEquals(Integer.valueOf(2), (Integer) (Object) heap.getStore().get(2).sameLevelLeft.val);
    assertNull(heap.getStore().get(2).sameLevelRight);
  }
}
