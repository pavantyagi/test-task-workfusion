package by.test.tree;

/**
 * Created by Vasilina on 31.03.2015.
 */
public interface Leaf<T> {
  Leaf<T> getRight();

  void setRight(Leaf<T> right);

  Leaf<T> getLeft();

  void setLeft(Leaf<T> left);

  Leaf<T> getParent();

  void setParent(Leaf<T> parent);

  T getValue();

  void setValue(T value);

}
