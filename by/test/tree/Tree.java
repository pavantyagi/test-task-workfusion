package by.test.tree;

/**
 * Created by Vasilina on 01.04.2015.
 */
public interface Tree<T, L extends Leaf<T>> {

  L addLeaf(T value);

  void visit(Visitor<T> visitor);

  L getRootLeaf();
}
