package ahmedowian.code_katas.trees;

import java.util.HashSet;
import java.util.Set;

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
    
        Set<TreeNode> nodes = getNodesAtLevelToModify(treeLevelToModify, 0, root);
        for (TreeNode tempNode : nodes)
        {
            if (tempNode.equals(node))
            {
                tempNode.setValue(tempNode.getValue() + node.getValue());
            }
        }
    }
    
    protected Set<TreeNode> getNodesAtLevelToModify(int treeLevelToModify, int level, TreeNode node)
    {
        final int NEXT_LEVEL = level + 1;
        
        if (treeLevelToModify > NEXT_LEVEL)
        {
            // Traverse the children to get the nodes to modify
            Set<TreeNode> nodesToModify = new HashSet<>();
            for (TreeNode child : node.getChildren())
            {
                nodesToModify.addAll(getNodesAtLevelToModify(treeLevelToModify, NEXT_LEVEL, child));
            }
            return nodesToModify;
        }
        else if (treeLevelToModify == NEXT_LEVEL)
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
