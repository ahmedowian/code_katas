package ahmedowian.code_katas.trees;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TreeNodeTest
{

    @Test
    public void testEquals()
    {
        TreeNode a = new TreeNode("A", 0);
        TreeNode b = new TreeNode("A", 4);
        assertEquals(a, b);
    }

}
