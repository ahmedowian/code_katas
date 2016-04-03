package ahmedowian.code_katas.trees;

import java.util.HashSet;
import java.util.Set;

/**
 * A node of a tree with a String key and int value.
 * A given TreeNode can also have one or more children TreeNodes. The order of the children doesn’t matter, 
 * but a parent TreeNode can have only one child with a given key.
 *
 * @author Ahmed Owian
 */
public class TreeNode
{
    private String key;
    private int value;
    private Set<TreeNode> children;
    
    public TreeNode(String key, int value)
    {
        this.key = key;
        this.value = value;
        this.children = new HashSet<>();
    }
    
    public String getKey()
    {
        return this.key;
    }
    
    public int getValue()
    {
        return this.value;
    }
    
    public void setValue(int newValue)
    {
        this.value = newValue;
    }
    
    public Set<TreeNode> getChildren()
    {
        return this.children;
    }
    
    public TreeNode addChildren(TreeNode... children)
    {
        for (TreeNode child : children)
        {
            this.children.add(child);
        }
        return this;
    }
    
    /**
     * TreeNodes are considered equivalent when their keys are equivalent.
     * @param that
     * @return true if the keys are equal
     */
    public boolean equals(TreeNode that)
    {
        return this.key == that.key;
    }
    
    /**
     * TreeNodes with the same key should hash to the same value.
     */
    public int hashCode()
    {
        return this.key.hashCode();
    }
}
