package by.test.tree;

/**
 * Created by Vasilina on 30.03.2015.
 */
public class BalancedLeafImpl<T> implements BalancedLeaf<T> {
  private Leaf<T> left;
  private Leaf<T> right;
  private Leaf<T> parent;
  private T value;
  private int height;

  public Leaf<T> getLeft() {
    return left;
  }

  public void setLeft(Leaf<T> left) {
    if (left != null) {
      left.setParent(this);
    }
    this.left = left;
  }

  public Leaf<T> getRight() {
    return right;
  }

  public void setRight(Leaf<T> right) {
    if (right != null) {
      right.setParent(this);
    }
    this.right = right;
  }

  public Leaf<T> getParent() {
    return parent;
  }

  public void setParent(Leaf<T> parent) {
    this.parent = parent;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public T getValue() {
    return value;
  }

  public static int getHeight(BalancedLeaf leaf) {
    return leaf == null ? 0 : leaf.getHeight();
  }

  public static int getBalanceFactor(BalancedLeaf leaf) {
    return BalancedLeafImpl.getHeight((BalancedLeaf) leaf.getRight()) - BalancedLeafImpl.getHeight((BalancedLeaf) leaf.getLeft());

  }

  public void fixHeight() {
    setHeight((BalancedLeafImpl.getHeight((BalancedLeaf) left) > BalancedLeafImpl.getHeight((BalancedLeaf) right) ? BalancedLeafImpl.getHeight((BalancedLeaf) right) : BalancedLeafImpl.getHeight((BalancedLeaf) right))+1);
  }



  public void setHeight(int height) {
    this.height = height;
  }

  public int getHeight() {
    return height;
  }


}
