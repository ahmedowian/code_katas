package ahmedowian.code_katas.trees;

import org.junit.Before;
import org.junit.Test;

public class NodeManagerRemovingTest extends NodeManagerTestBase
{
    /**
     * Sets up the tree to look like this:
     *          A       -> Removing A on level 0 will cause there to be no root
     *         /\ \
     *        B  I C2   -> Removing B on level 1 will cause a conflict of C and C2!
     *       / \  \ \
     *      C   D  J L
     *        //\\  \  \
     *       EF  GH  K  M
     *       
     * By definition, a tree has no cycles, so we cannot have two parents of any one node.
     * However, we can have two different nodes with the same key, making them "equal".  Hence C and C2.
     */
    @Before
    public void setUp()
    {
        super.setUp();
        nodeA.addChildren(nodeB, nodeI, nodeC2);
        nodeB.addChildren(nodeC, nodeD);
        nodeD.addChildren(nodeE, nodeF, nodeG, nodeH);
        nodeI.addChildren(nodeJ);
        nodeJ.addChildren(nodeK);
        nodeC2.addChildren(nodeL);
        nodeL.addChildren(nodeM);
    }

    @Test
    public void removeA_At0()
    {
        TreeNode node = new TreeNode("A", 10);
        mgr.remove(node, 0);
        
        // What to expect here?
    }
}
