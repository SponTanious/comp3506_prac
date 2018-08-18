package pracs.prac3;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> implements Comparable<TreeNode<T>> {

    T state;
    TreeNode<T> parent;
    List<TreeNode<T>> children;
    int cCost;
    int hCost;

    public TreeNode(T state, int hCost) {
        this.state = state;
        this.parent = null;
        this.children = new ArrayList<>();
        this.cCost = 0;
        this.hCost = hCost;
    }

    public TreeNode<T> addChild(T child, int hCost) {
        TreeNode<T> childNode = new TreeNode(child, hCost);
        childNode.parent = this;
        childNode.cCost = this.cCost + 1;
        this.children.add(childNode);
        return childNode;
    }

    @Override
    public int compareTo(TreeNode<T> o1){
        int o1Cost = this.cCost + this.hCost;
        int o2Cost = o1.cCost + o1.hCost;

        return Integer.compare(o1Cost, o2Cost);
    }

}