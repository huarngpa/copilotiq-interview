package com.huarngpa.copilotiq.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class HeapTest {

  private static Stream<Arguments> heapInsertSource() {
    return Stream.of(
        Arguments.of(Arrays.asList(5, 4, 3, 2, 1), Arrays.asList(5, 4, 3, 2, 1)),
        Arguments.of(Arrays.asList(1, 4, 2, 1, 3), Arrays.asList(4, 3, 2, 1, 1)),
        Arguments.of(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(5, 4, 3, 2, 1)));
  }

  /**
   * Verifies that the max-heap properties are maintained when inserting into the heap.
   *
   * @param input
   * @param expected
   */
  @ParameterizedTest
  @MethodSource("heapInsertSource")
  public void test_heap_insert(List<Integer> input, List<Integer> expected) {
    // Setup
    Heap<Integer> heap = new Heap<>();
    input.forEach(heap::insert);

    // Execute and verify
    assertEquals(expected, heap.getStore());
  }
}
