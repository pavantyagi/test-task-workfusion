package by.test;

import by.test.tree.Leaf;
import by.test.tree.LeafImpl;
import by.test.tree.Visitor;

import java.util.HashMap;
import java.util.Map;

/**
 * todo: Nice: will be unit test and api functions
 * only for sorted arr, if we dont know array is sorted should use BalancedTreeImpl
 *
 * @see by.test.BalancedTreeImpl constructor, this is swing application to view tree result
 * Created by Vasilina on 01.04.2015.
 */
public class SortedArrayToTree {
  private Leaf<Integer> root;

  public static void main(String[] args) {
    int[] arr = {1, 4, 9, 10, 11, 50, 55, 57, 60, 70, 77, 79};
    SortedArrayToTree sortedArrayToTree = new SortedArrayToTree();
    sortedArrayToTree.createTreeFromSortedArr(arr);
    sortedArrayToTree.visit(new Visitor<Integer>() {
      @Override
      public void visit(Integer currentValue, Integer parentValue) {

      }
    });
  }

  public void visit(Visitor<Integer> visitor) {
    visit(root, visitor);
  }

  public void createTreeFromSortedArr(int[] arr) {
    Map<Integer, Leaf<Integer>> leaves = new HashMap<Integer, Leaf<Integer>>();
    for (int i = 0; i < arr.length / 2; i++) {
      int indexForCalc = i + 1;
      Leaf<Integer> current;
      if (i == 0) {
        current = createCurrent(arr[i], null);
        leaves.put(i, current);
      } else {
        current = leaves.get(i);
      }
      int leftIndex = indexForCalc * 2 - 1;
      Leaf<Integer> left = createCurrent(arr[leftIndex], current);
      current.setLeft(left);
      leaves.put(leftIndex, left);

      int rightIndex = indexForCalc * 2;
      if (rightIndex <= arr.length - 1) {
        Leaf<Integer> right = createCurrent(arr[rightIndex], current);
        current.setRight(right);
        leaves.put(rightIndex, right);
      }

    }
    this.root = leaves.get(0);
  }

  public void visit(Leaf<Integer> leaf, Visitor<Integer> visitor) {
    //if (leaf.getLeft() == null && leaf.getRight() == null) {
    visitor.visit(leaf.getValue(), leaf.getParent() != null ? leaf.getParent().getValue() : null);
    //}
    if (leaf.getLeft() != null) {
      visit(leaf.getLeft(), visitor);
    }
    if (leaf.getRight() != null) {
      visit(leaf.getRight(), visitor);
    }

  }


  private Leaf<Integer> createCurrent(Integer value, Leaf<Integer> parent) {
    Leaf<Integer> current = new LeafImpl<Integer>();
    current.setValue(value);
    current.setParent(parent);
    return current;
  }

  public Leaf<Integer> getRoot() {
    return root;
  }
}
