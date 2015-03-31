package by.test.tree;

/**
 * Created by Vasilina on 31.03.2015.
 */
public interface Visitor<T> {
  void visit(T currentValue, T parentValue);

}
