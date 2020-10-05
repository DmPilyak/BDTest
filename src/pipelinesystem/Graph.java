package pipelinesystem;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> nodes = new HashSet<>();

    public void addNode(Node nodeA) {
    	for (Node node : nodes) {
			if(node.getName().equals(nodeA.getName())) {
				return;
			}
		}
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
    
    public Node getNodeByName(String name) {
    	for (Node node : nodes) {
			if(node.getName().equals(name)) {
				return node;
			}
		}
    	return null;
    }
}
