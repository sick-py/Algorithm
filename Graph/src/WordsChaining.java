public class WordsChaining {
    /***
     * Given a list of words, figure out whether the given words can form a circular chain. Assume that a single word can never form a chain.
     *
     * Two words can be chained together if the first word’s last letter is equal to the second word’s first letter.
     *
     * Note: Assume that all the words are lower case.
     *
     * A better solution is to do a linear pass of the word list and construct a graph that represents the start and end characters of each word. We need to add an edge from the starting character of a word to its ending character for each word in the list. If there exists a cycle in this graph containing all the nodes, then a chain can be formed.
     *
     * To form a circular chain connecting all the words, the graph must be connected so that if we start traversing it from any vertex, it should end at the same vertex. This ensures that all vertices are visited, and all edges are traversed exactly once, thus forming a cycle that represents the chain of words.
     *
     * It leads us to the concept of an Euler circuit in a graph. An Euler circuit is a circuit that uses every edge of a graph exactly once. It starts and ends at the same vertex. Therefore, we can find that a chain exists for a list of words if an Euler circuit exists for the graph created as described above.
     *
     * To find the Euler circuit of the graph, we must ensure that these two conditions are satisfied:
     *
     * The in-degree of every vertex is equal to its out-degree.
     * A cycle exists connecting all the vertices of the graph that starts and ends at the same vertex.
     * Below is a pseudo-code walkthrough of our approach:
     *
     * Create Graph:
     *     While not end-of-list
     *         Read a word
     *         Create a node with the start character of the word (if not already created)
     *         Create a node with the end character of the word (if not already created)
     *         Add an edge from the start node to the end node by adding the end node to
     *             the adjacency list of the start node
     *         Save the start node as an in vertex of the end node
     * Can Chain Words:
     *     current_node = first node
     *     if the out degree of every node is equal to its in degree
     *         Call Can Chain Words for current_node
     *         Visit the node
     *         If all nodes have been visited and starting node is adjacent to the current node,
     *             return TRUE
     *         Otherwise,
     *             For all adjacent nodes of the current node
     *             if(adjacent node has not been visited yet)
     *                 current_node = adjacent node
     *                 Call can_chain_words() recursively for the current_node
     *                 if it returns true, return TRUE; otherwise, return FALSE
     *             return FALSE
     */

}
