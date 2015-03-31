package by.test;

import by.test.tree.BalancedLeaf;
import by.test.tree.BalancedLeafImpl;
import by.test.tree.Visitor;

/**
 * Created by Vasilina on 30.03.2015.
 */
public class BalancedTreeImpl<T extends Comparable> {
  public BalancedLeaf<T> rootLeaf;

  public static void main(String[] args) {
    BalancedTreeImpl<Integer> integerTree = new BalancedTreeImpl<Integer>();
    int[] arr = {1, 4, 9, 10, 11, 50};
    for (int value : arr) {
      integerTree.addLeaf(value);
    }
//    BalancedLeaf<Integer> leaf = integerTree.findLeaf(integerTree.getRootLeaf(), 19);
    //integerTree.removeLeaf(3);
 //   System.out.println(leaf == null ? "Not found" : leaf.getValue());
    integerTree.visit(new Visitor<Integer>() {
      @Override
      public void visit(Integer currentValue, Integer parentValue) {
        System.out.println(currentValue + " visited parent : " + parentValue);

      }
    });
    System.out.println("all");
  }

  public void visit(Visitor<T> visitor) {
    visit(rootLeaf, visitor);
  }

  public void visit(BalancedLeaf<T> leaf, Visitor<T> visitor) {
    //if (leaf.getLeft() == null && leaf.getRight() == null) {
    visitor.visit(leaf.getValue(), leaf.getParent() != null ? leaf.getParent().getValue() : null);
    //}
    if (leaf.getLeft() != null) {
      visit((BalancedLeaf<T>) leaf.getLeft(), visitor);
    }
    if (leaf.getRight() != null) {
      visit((BalancedLeaf<T>) leaf.getRight(), visitor);
    }

  }


  public BalancedLeaf<T> addLeaf(T value) {
    return addLeaf(rootLeaf, value);
  }

  private BalancedLeaf<T> addLeaf(BalancedLeaf<T> parent, T value) {
    if (rootLeaf == null) {
      rootLeaf = createLeaf(parent, value);
      return rootLeaf;
    }
    if (parent == null) {
      return createLeaf(null, value);
    }
    if (parent.getValue().compareTo(value) > 0) {
        //Leaf<T> childLeaf = createLeaf(value);
        //childLeaf.setParent(leaf);
        parent.setLeft(addLeaf((BalancedLeaf<T>) parent.getLeft(), value));
        //return balance(leaf);
    } else {
        //Leaf<T> childLeaf = createLeaf(value);
        //childLeaf.setParent(leaf);
        parent.setRight(addLeaf((BalancedLeaf<T>) parent.getRight(), value));
        //return balance(leaf);
    }
    return balance(parent);
    //return leaf;
  }

  private BalancedLeafImpl<T> createLeaf(BalancedLeaf<T> parent, T value) {
    BalancedLeafImpl<T> childLeaf = new BalancedLeafImpl<T>();
    childLeaf.setParent(parent);
    childLeaf.setValue(value);
    childLeaf.setHeight(1);
    return childLeaf;
  }

  private BalancedLeaf<T> findLeaf(BalancedLeaf<T> leaf, T value) {
    if (leaf.getValue().compareTo(value) == 0) {
      return leaf;
    } else if (leaf.getValue().compareTo(value) > 0) {
      if (leaf.getLeft() == null) {
        return null;
      } else {
        return findLeaf((BalancedLeaf<T>) leaf.getLeft(), value);
      }
    } else {
      if (leaf.getRight() == null) {
        return null;
      } else {
        return findLeaf((BalancedLeaf<T>) leaf.getRight(), value);
      }
    }
  }

  public void removeLeaf(T value) {
    BalancedLeaf<T> leaf = findLeaf(rootLeaf, value);
    if (leaf != null) {
      if (leaf.getLeft() != null) {
        leaf.getParent().setLeft(leaf.getLeft());
      }
      if (leaf.getRight() != null) {
        getNearestRightForInsert((BalancedLeaf<T>) leaf.getParent()).setLeft(leaf.getRight());
      }
      balance(leaf);
    }
  }

  public BalancedLeaf<T> getNearestRightForInsert(BalancedLeaf<T> parent) {
    if (parent.getRight() == null) {
      return parent;
    } else {
      return getNearestRightForInsert((BalancedLeaf<T>) parent.getRight());
    }
  }

  public BalancedLeaf<T> balance(BalancedLeaf<T> leaf) {
    leaf.fixHeight();
    if (BalancedLeafImpl.getBalanceFactor(leaf) == 2) {
      if (BalancedLeafImpl.getBalanceFactor((BalancedLeaf) leaf.getRight()) < 0) {
        leaf.setRight(rotateRight((BalancedLeaf<T>) leaf.getRight()));
      }
      return rotateLeft(leaf);
    }
    if (BalancedLeafImpl.getBalanceFactor(leaf) == -2) {
      if (BalancedLeafImpl.getBalanceFactor((BalancedLeaf) leaf.getLeft()) > 0) {
        leaf.setLeft(rotateLeft((BalancedLeaf<T>) leaf.getLeft()));
      }
      return rotateRight(leaf);
    }
    return leaf;
  }

  public BalancedLeaf<T> getRootLeaf() {
    return rootLeaf;
  }

  public BalancedLeaf<T> rotateRight(BalancedLeaf<T> rotate) {
    BalancedLeaf<T> prevLeft = (BalancedLeaf<T>) rotate.getLeft();
    if (rotate.getParent() == null) {
      rootLeaf = prevLeft;
    }
    if (prevLeft != null) {
      rotate.setLeft(prevLeft.getRight());
      prevLeft.setRight(rotate);
      prevLeft.fixHeight();
    }
    rotate.fixHeight();
    return prevLeft;
  }



  public BalancedLeaf<T> rotateLeft(BalancedLeaf<T> rotate) {
    BalancedLeaf<T> prevRight = (BalancedLeaf<T>) rotate.getRight();
    if (rotate.getParent() == null) {
      rootLeaf = prevRight;
      prevRight.setParent(null);
    }
    if (prevRight != null) {
      rotate.setRight(prevRight.getLeft());
      prevRight.setLeft(rotate);
      prevRight.fixHeight();
    }
    rotate.fixHeight();
    return prevRight;
  }

}
