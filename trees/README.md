# code_katas


You are being asked to implement a class which is used to manage a tree of "TreeNodes".

1. Implement the "TreeNode" class. This class should have a String "key" along with an integer "value". TreeNodes are considered equivalent when their keys are equivalent. A given TreeNode can also have one or more children TreeNodes. The order of the children doesn’t matter, but a parent TreeNode can have only one child with a given key.

2. Implement a class called "NodeManager" which contains a single TreeNode, which is the root of a tree. This class should have two methods:

add(TreeNode node, int treeLevelToModify) – this method will update all nodes in the tree which equal the TreeNode by adding the value of the passed node to the value of the existing node. It will only modify nodes which are at the specified treeLevelToModify (0 refers to the root).

remove(TreeNode node, int treeLevelToModify) – this method will remove all nodes in the tree at the specified level whose value equals the value of node.

Questions: What about its children?

Note that a given NodeManager might be utilized by multiple threads.
