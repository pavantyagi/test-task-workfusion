package by.test;

import by.test.tree.Leaf;
import by.test.tree.OrdinaryTreeImpl;
import by.test.tree.Tree;
import by.test.tree.Visitor;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vasilina on 31.03.2015.
 */
public class TreeWithMinHeightUi extends JScrollPane
    implements TreeWillExpandListener, TreeExpansionListener {
  JTree tree;

  public static void main(String[] args) {
    TreeWithMinHeightUi treeWithMinHeight = new TreeWithMinHeightUi();

    JFrame frame = new JFrame("FrameDemo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(treeWithMinHeight, BorderLayout.CENTER);
    frame.pack();
    frame.setSize(500, 500);
    frame.setVisible(true);
  }

  public TreeWithMinHeightUi() {
    //here please uncomment one of line to see according result tree
//    TreeNode rootNode = createOrdinaryNodes();
    TreeNode rootNode = createBalancedTreeNodes();
//    TreeNode rootNode = createNodesSortedArr();
    tree =  new JTree(rootNode);
    for (int i = 0; i < tree.getRowCount(); i++) {
      tree.expandRow(i);
    }
    tree.addTreeExpansionListener(this);
    tree.addTreeWillExpandListener(this);

    setViewportView(tree);
  }

  private TreeNode createOrdinaryNodes() {
    OrdinaryTreeImpl<Integer> integerTree = new OrdinaryTreeImpl<Integer>();
    return getTreeNodeForTree(integerTree);
  }

  private TreeNode getTreeNodeForTree(Tree integerTree) {
    int[] arr = {1, 4, 9, 10, 11, 50, 55, 57,60, 70, 77, 79};
    for (int value : arr) {
      integerTree.addLeaf(value);
    }
    DefaultMutableTreeNode root;
    Leaf<Integer> rootLeaf = integerTree.getRootLeaf();
    root = new DefaultMutableTreeNode(rootLeaf.getValue());
    final HashMap<Integer, DefaultMutableTreeNode> treeNodes = new HashMap<Integer, DefaultMutableTreeNode>();
    treeNodes.put(rootLeaf.getValue(), root);
    integerTree.visit(new Visitor<Integer>() {
      @Override
      public void visit(Integer currentValue, Integer parentValue) {
        if (parentValue != null) {
          DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(currentValue);
          treeNodes.put(currentValue, defaultMutableTreeNode);
          treeNodes.get(parentValue).add(defaultMutableTreeNode);
        }

      }
    });
    return root;
  }

  private TreeNode createBalancedTreeNodes() {
    BalancedTreeImpl<Integer> integerTree = new BalancedTreeImpl<Integer>();
    return getTreeNodeForTree(integerTree);
  }


  private TreeNode createNodesSortedArr() {
    SortedArrayToTree integerTree = new SortedArrayToTree();
    int[] arr = {1, 4, 9, 10, 11, 50, 55, 57,60, 70, 77, 79};
    DefaultMutableTreeNode root;
    integerTree.createTreeFromSortedArr(arr);
    Leaf<Integer> rootLeaf = integerTree.getRoot();
    root = new DefaultMutableTreeNode(rootLeaf.getValue());
    final HashMap<Integer, DefaultMutableTreeNode> treeNodes = new HashMap<Integer, DefaultMutableTreeNode>();
    treeNodes.put(rootLeaf.getValue(), root);
    integerTree.visit(new Visitor<Integer>() {
      @Override
      public void visit(Integer currentValue, Integer parentValue) {
        if (parentValue != null) {
          DefaultMutableTreeNode defaultMutableTreeNode = new DefaultMutableTreeNode(currentValue);
          treeNodes.put(currentValue, defaultMutableTreeNode);
          treeNodes.get(parentValue).add(defaultMutableTreeNode);
        }

      }
    });
    return root;
  }


  @Override
  public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {

  }

  @Override
  public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

  }

  @Override
  public void treeExpanded(TreeExpansionEvent event) {
  }

  @Override
  public void treeCollapsed(TreeExpansionEvent event) {

  }
}
