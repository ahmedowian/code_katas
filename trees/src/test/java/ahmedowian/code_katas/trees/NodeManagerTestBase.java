package ahmedowian.code_katas.trees;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map.Entry;

import org.junit.Before;

public abstract class NodeManagerTestBase
{
    NodeManager mgr;
    TreeNode nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG, nodeH, nodeI, nodeJ, nodeK, nodeL, nodeC2, nodeM;
    final int NODE_C2_ORIGINAL_VALUE = 22;
    HashMap<TreeNode, Integer> nodeMap;

    @Before
    public void setUp()
    {
        nodeA = new TreeNode("A", 8);
        mgr = new NodeManager(nodeA);
        
        nodeB = new TreeNode("B", 4);
        nodeC = new TreeNode("C", 2);
        nodeD = new TreeNode("D", 0);
        nodeE = new TreeNode("E", 5);
        nodeF = new TreeNode("F", 9);
        nodeG = new TreeNode("G", 7);
        nodeH = new TreeNode("H", 6);
        nodeI = new TreeNode("I", 34);
        nodeJ = new TreeNode("J", 20);
        nodeK = new TreeNode("K", 47);
        nodeL = new TreeNode("L", 45);
        nodeM = new TreeNode("M", 42);
        nodeC2 = new TreeNode("C", NODE_C2_ORIGINAL_VALUE);

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

    protected void assertNothingElseChanged(TreeNode... exceptNodes)
    {
        boolean c2Changed = false;
        assertNothingElseChanged(c2Changed, exceptNodes);
    }

    protected void assertNothingElseChanged(boolean c2Changed, TreeNode... exceptNodes)
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
}
