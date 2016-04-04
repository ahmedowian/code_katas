package ahmedowian.code_katas.trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class NodeManager
{
    private TreeNode root;
    
    public NodeManager(TreeNode root)
    {
        this.root = root;
    }

    /**
     * This method will update all nodes in the tree which equal the TreeNode by adding the value of the passed node 
     * to the value of the existing node. It will only modify nodes which are at the specified treeLevelToModify 
     * (0 refers to the root).
     * @param node
     * @param treeLevelToModify
     */
    public synchronized void add(TreeNode node, int treeLevelToModify) 
    {
        if (treeLevelToModify < 0)
        {
            throw new IllegalArgumentException("treeLevelToModify [" + treeLevelToModify + "] must be non-negative.");
        }
    
        Collection<TreeNode> nodes = getNodesAtTreeLevel(treeLevelToModify);
        for (TreeNode tempNode : nodes)
        {
            if (tempNode.equals(node))
            {
                tempNode.setValue(tempNode.getValue() + node.getValue());
            }
        }
    }
    
    protected Collection<TreeNode> getNodesAtTreeLevel(int treeLevel)
    {
        return getNodesAtTreeLevel(treeLevel, 0, root);
    }
    
    /**
     * Traverses the tree depth-first to get the nodes at the tree level specified
     * @param treeLevel the level of the tree to get the nodes
     * @param nodeLevel the current node level
     * @param node the current node
     * @return a collection of the nodes at the given tree level
     */
    protected Collection<TreeNode> getNodesAtTreeLevel(int treeLevel, int nodeLevel, TreeNode node)
    {
        final int NEXT_LEVEL = nodeLevel + 1;
        
        if (treeLevel > NEXT_LEVEL)
        {
            // Traverse the children to get the nodes to modify
            Collection<TreeNode> nodesAtTreeLevel = new ArrayList<>();
            for (TreeNode child : node.getChildren())
            {
                nodesAtTreeLevel.addAll(getNodesAtTreeLevel(treeLevel, NEXT_LEVEL, child));
            }
            return nodesAtTreeLevel;
        }
        else if (treeLevel == NEXT_LEVEL)
        {
            // Just return the children, since they are at the next level
            return node.getChildren();
        } 
        else
        {
            // Simple case where (treeLevelToModify == level).  Happens at root
            HashSet<TreeNode> set = new HashSet<>();
            set.add(node);
            return set;
        }
    }
    
    /**
     * This method will remove all nodes in the tree at the specified level whose value equals the value of node.
     * @param node
     * @param treeLevelToModify
     */
    public synchronized void remove(TreeNode node, int treeLevelToModify)
    {
        
    }
}
