package ahmedowian.code_katas.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NodeManagerAddingTest extends NodeManagerTestBase
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    
    /**
     * Sets up the tree to look like this:
     *          A
     *         /\ \
     *        B  I L
     *       / \  \ \
     *      C   D  J C2
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
        nodeA.addChildren(nodeB, nodeI, nodeL);
        nodeB.addChildren(nodeC, nodeD);
        nodeD.addChildren(nodeE, nodeF, nodeG, nodeH);
        nodeI.addChildren(nodeJ);
        nodeJ.addChildren(nodeK);
        nodeL.addChildren(nodeC2);
        nodeC2.addChildren(nodeM);
    }
    
    @Test
    public void addA_AtNegativeNumberThrowsException()
    {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("non-negative");
        
        TreeNode node = new TreeNode("A", 10);
        mgr.add(node, -1);
    }
    
    @Test
    public void addA_At0ChangesA()
    {
        TreeNode node = new TreeNode("A", 10);
        mgr.add(node, 0);
        int originalNodeValue = nodeMap.get(nodeA);
        int expectedValue = originalNodeValue + 10;
        assertEquals(expectedValue, nodeA.getValue());
        assertNothingElseChanged(nodeA);
    }

    @Test
    public void addB_At1ChangesB()
    {
        TreeNode node = new TreeNode("B", 10);
        mgr.add(node, 1);
        int originalNodeValue = nodeMap.get(nodeB);
        int expectedValue = originalNodeValue + 10;
        assertEquals(expectedValue, nodeB.getValue());
        assertNothingElseChanged(nodeB);
    }

    @Test
    public void addC_At2ChangesCAndC2()
    {
        TreeNode node = new TreeNode("C", 10);
        mgr.add(node, 2);
        int originalNodeValue = nodeMap.get(nodeC);
        int expectedValue = originalNodeValue + 10;
        assertEquals(expectedValue, nodeC.getValue());
        
        expectedValue = NODE_C2_ORIGINAL_VALUE + 10;
        assertEquals(expectedValue, nodeC2.getValue());
        assertNothingElseChanged(true, nodeC);
    }
    
    @Test
    public void addG_At3ChangesG()
    {
        TreeNode node = new TreeNode("G", 10);
        mgr.add(node, 3);
        int originalNodeValue = nodeMap.get(nodeG);
        int expectedValue = originalNodeValue + 10;
        assertEquals(expectedValue, nodeG.getValue());
        assertNothingElseChanged(nodeG);
    }
    
    @Test
    public void addSomethingAt4ChangesNothing()
    {
        TreeNode node = new TreeNode("G", 10);
        mgr.add(node, 4);
        assertNothingElseChanged();
    }
    
    @Test
    public void addX_At0ChangesNothing()
    {
        TreeNode node = new TreeNode("X", 10);
        mgr.add(node, 0);
        assertNothingElseChanged();
    }
    
    @Test
    public void addX_At1ChangesNothing()
    {
        TreeNode node = new TreeNode("X", 10);
        mgr.add(node, 1);
        assertNothingElseChanged();
    }
    
    @Test
    public void addX_At2ChangesNothing()
    {
        TreeNode node = new TreeNode("X", 10);
        mgr.add(node, 2);
        assertNothingElseChanged();
    }
    
    @Test
    public void addX_At3ChangesNothing()
    {
        TreeNode node = new TreeNode("X", 10);
        mgr.add(node, 3);
        assertNothingElseChanged();
    }
    
    @Test
    public void getNodesAt0GetsA()
    {
        Collection<TreeNode> nodes = mgr.getNodesAtTreeLevel(0);
        assertEquals(1, nodes.size());
        assertTrue(nodes.contains(nodeA));
    }
    
    @Test
    public void getNodesAt1GetsBIL()
    {
        Collection<TreeNode> nodes = mgr.getNodesAtTreeLevel(1);
        assertEquals(3, nodes.size());
        assertTrue(nodes.contains(nodeB));
        assertTrue(nodes.contains(nodeI));
        assertTrue(nodes.contains(nodeL));
    }
    
    @Test
    public void getNodesAt2GetsCDJandC2()
    {
        Collection<TreeNode> nodes = mgr.getNodesAtTreeLevel(2);
        assertEquals(4, nodes.size());
        assertTrue(nodes.contains(nodeD));
        assertTrue(nodes.contains(nodeJ));

        /* These two checks do the same thing, so perform them explicitly
         *  assertTrue(nodes.contains(nodeC));
         *  assertTrue(nodes.contains(nodeC2));
         */
        
        // Checks for C and C2 explicitly
        ArrayList<TreeNode> the2Cs = new ArrayList<>(2);
        for (TreeNode node : nodes)
        {
            // nodeC and nodeC2 are "equal"
            if (node.equals(nodeC))
            {
                the2Cs.add(node);
            }
        }
        
        assertEquals(2, the2Cs.size());
        assertNotSame(the2Cs.get(0), the2Cs.get(1));
        assertNotSame(the2Cs.get(0).getValue(), the2Cs.get(1).getValue());
    }
    
    @Test
    public void getNodesAt3GetsEFGHKM()
    {
        Collection<TreeNode> nodes = mgr.getNodesAtTreeLevel(3);
        assertEquals(6, nodes.size());
        assertTrue(nodes.contains(nodeE));
        assertTrue(nodes.contains(nodeF));
        assertTrue(nodes.contains(nodeG));
        assertTrue(nodes.contains(nodeH));
        assertTrue(nodes.contains(nodeK));
        assertTrue(nodes.contains(nodeM));
    }
    
    @Test
    public void getNodesAt4GetsNothing()
    {
        Collection<TreeNode> nodes = mgr.getNodesAtTreeLevel(4);
        assertTrue(nodes.isEmpty());
    }
}
