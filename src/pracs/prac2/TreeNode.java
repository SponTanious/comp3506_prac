package pracs.prac2;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T>  {

    T state;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T state) {
        this.state = state;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

}