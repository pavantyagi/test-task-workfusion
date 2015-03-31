package by.test.tree;

/**
 * Created by Vasilina on 31.03.2015.
 */
public interface BalancedLeaf<T> extends Leaf<T> {
  
  int getHeight();

  void fixHeight();

/*
  BalancedLeaf<T> getRight();

  void setRight(BalancedLeaf<T> right);

  BalancedLeaf<T> getLeft();

  void setLeft(BalancedLeaf<T> left);

  BalancedLeaf<T> getParent();

  void setParent(BalancedLeaf<T> parent);

  T getValue();

  void setValue(T value);
*/


}
