package by.test.tree;

/**
 * Created by Vasilina on 31.03.2015.
 */
public class Heights {
  int maxHeight = Integer.MIN_VALUE;
  int minHeight = Integer.MAX_VALUE;
  int numberOfLeafAnalyzed = 0;

  public int getMaxHeight() {
    return maxHeight;
  }

  public void setMaxHeight(int maxHeight) {
    this.maxHeight = maxHeight;
  }

  public int getMinHeight() {
    return minHeight;
  }

  public void setMinHeight(int minHeight) {
    this.minHeight = minHeight;
  }

  public int getNumberOfLeafAnalyzed() {
    return numberOfLeafAnalyzed;
  }

  public void setNumberOfLeafAnalyzed(int numberOfLeafAnalyzed) {
    this.numberOfLeafAnalyzed = numberOfLeafAnalyzed;
  }
}

