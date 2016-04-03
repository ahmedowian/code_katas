package ahmedowian.code_katas.trees;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ahmedowian.code_katas.trees.NodeManager;
import ahmedowian.code_katas.trees.TreeNode;

public class NodeManagerTest
{
    NodeManager mgr;
    TreeNode nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL;
    
    @Before
    public void setUp()
    {
        /*
         * The tree looks like this, labeled in depth-first traversal order:
         *          A
         *         /\ \
         *        B  I L
         *       / \  \
         *      C   D  J
         *        //\\  \
         *       EF  GH  K
         */
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
    }
    
    @Test
    public void getnodesAt0GetsA()
    {
        Set<TreeNode> nodes = mgr.getNodesAtLevelToModify(0, 0, nodeA);
        assertEquals(1, nodes.size());
        assertTrue(nodes.contains(nodeA));
    }
    
    @Test
    public void getnodesAt1GetsBIL()
    {
        Set<TreeNode> nodes = mgr.getNodesAtLevelToModify(1, 0, nodeA);
        assertEquals(3, nodes.size());
        assertTrue(nodes.contains(nodeB));
        assertTrue(nodes.contains(nodeI));
        assertTrue(nodes.contains(nodeL));
    }
    
    @Test
    public void getnodesAt2GetsCDJ()
    {
        Set<TreeNode> nodes = mgr.getNodesAtLevelToModify(2, 0, nodeA);
        assertEquals(3, nodes.size());
        assertTrue(nodes.contains(nodeC));
        assertTrue(nodes.contains(nodeD));
        assertTrue(nodes.contains(nodeJ));
    }
    
    @Test
    public void getnodesAt3GetsEFGHK()
    {
        Set<TreeNode> nodes = mgr.getNodesAtLevelToModify(3, 0, nodeA);
        assertEquals(5, nodes.size());
        assertTrue(nodes.contains(nodeE));
        assertTrue(nodes.contains(nodeF));
        assertTrue(nodes.contains(nodeG));
        assertTrue(nodes.contains(nodeH));
        assertTrue(nodes.contains(nodeK));
    }
}
