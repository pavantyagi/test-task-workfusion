package by.test.tree;

/**
 * Created by Vasilina on 30.03.2015.
 */
public class OrdinaryTreeImpl<T extends Comparable> implements Tree<T, Leaf<T>> {
  public Leaf<T> rootLeaf;

  public static void main(String[] args) {
    OrdinaryTreeImpl<Integer> integerTree = new OrdinaryTreeImpl<Integer>();
    int[] arr = {1, 4, 9, 10, 11, 50};
    for (int value : arr) {
      integerTree.addLeaf(value);
    }
//    Leaf<Integer> leaf = integerTree.findLeaf(integerTree.getRootLeaf(), 19);
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

  public void visit(Leaf<T> leaf, Visitor<T> visitor) {
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

  public Leaf<T> addLeaf(T value) {
    Leaf<T> leaf = new LeafImpl<T>();
    leaf.setValue(value);
    if (rootLeaf == null) {
      rootLeaf = leaf;
    } else {
      addLeaf(rootLeaf, leaf);
    }
    return leaf;
  }

  private void addLeaf(Leaf<T> leaf, Leaf<T> childLeaf) {
    if (leaf.getValue().compareTo(childLeaf.getValue()) > 0) {
      if (leaf.getLeft() == null) {
        childLeaf.setParent(leaf);
        leaf.setLeft(childLeaf);
      } else {
        addLeaf(leaf.getLeft(), childLeaf);
      }
    } else {
      if (leaf.getRight() == null) {
        childLeaf.setParent(leaf);
        leaf.setRight(childLeaf);
      } else {
        addLeaf(leaf.getRight(), childLeaf);
      }
    }
  }

  private Leaf<T> findLeaf(Leaf<T> leaf, T value) {
    if (leaf.getValue().compareTo(value) == 0) {
      return leaf;
    }
    else if (leaf.getValue().compareTo(value) > 0) {
      if (leaf.getLeft() == null) {
        return null;
      } else {
        return findLeaf(leaf.getLeft(), value);
      }
    } else {
      if (leaf.getRight() == null) {
        return null;
      } else {
        return findLeaf(leaf.getRight(), value);
      }
    }
  }

  public void removeLeaf(T value) {
    Leaf<T> leaf = findLeaf(rootLeaf, value);
    if (leaf != null) {
      if (leaf.getLeft() != null) {
        leaf.getParent().setLeft(leaf.getLeft());
      }
      if (leaf.getRight() != null) {
        getNearestRightForInsert(leaf.getParent()).setLeft(leaf.getRight());
      }
    }
  }

  public Leaf<T> getNearestRightForInsert(Leaf<T> parent) {
    if (parent.getRight() == null) {
      return parent;
    } else {
      return getNearestRightForInsert(parent.getRight());
    }
  }

  public Leaf<T> getRootLeaf() {
    return rootLeaf;
  }

}
