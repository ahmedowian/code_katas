package ahmedowian.code_katas.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class NodeManagerAddingTest
{
    NodeManager mgr;
    TreeNode nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL, nodeC2, nodeM;
    final int NODE_C2_ORIGINAL_VALUE = 22;
    HashMap<TreeNode, Integer> nodeMap;
    

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
        nodeA = new TreeNode("A", 8);
        mgr = new NodeManager(nodeA);
        
        nodeB = new TreeNode("B", 4);
        nodeI = new TreeNode("I", 34);
        nodeL = new TreeNode("L", 45);
        nodeA.addChildren(nodeB, nodeI, nodeL);
        
        nodeC = new TreeNode("C", 2);
        nodeD = new TreeNode("D", 0);
        nodeB.addChildren(nodeC, nodeD);
        
        nodeE = new TreeNode("E", 5);
        nodeF = new TreeNode("F", 9);
        nodeG = new TreeNode("G", 7);
        nodeH = new TreeNode("H", 6);
        nodeD.addChildren(nodeE, nodeF, nodeG, nodeH);
        
        nodeJ = new TreeNode("J", 20);
        nodeI.addChildren(nodeJ);

        nodeK = new TreeNode("K", 47);
        nodeJ.addChildren(nodeK);
        
        nodeC2 = new TreeNode("C", NODE_C2_ORIGINAL_VALUE);
        nodeL.addChildren(nodeC2);
        
        nodeM = new TreeNode("M", 42);
        nodeC2.addChildren(nodeM);
        
        // Don't bother putting C2, because it will overwrite C in the nodeMap
        TreeNode[] nodeArray = {nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL, nodeM};
        rememberValues(nodeArray);
    }
    
    private void rememberValues(TreeNode[] nodeArray)
    {
        nodeMap = new HashMap<>();
        for (TreeNode node : nodeArray)
        {
            nodeMap.put(node, node.getValue());
        }
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
    
    private void assertNothingElseChanged(TreeNode... exceptNodes)
    {
        boolean c2Changed = false;
        assertNothingElseChanged(c2Changed, exceptNodes);
    }
    
    private void assertNothingElseChanged(boolean c2Changed, TreeNode... exceptNodes)
    {
        if (!c2Changed)
        {
            assertEquals(NODE_C2_ORIGINAL_VALUE, nodeC2.getValue());
        }
        
        for (Entry<TreeNode, Integer> entry : nodeMap.entrySet())
        {
            TreeNode node = entry.getKey();
            boolean checkThisNode = true;
            for (TreeNode exceptNode : exceptNodes)
            {
                if (node.equals(exceptNode))
                {
                    checkThisNode = false;
                    break;
                }
            }
            
            if (checkThisNode)
            {
                assertEquals(entry.getValue().intValue(), node.getValue());
            }
        }
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
