package optimize.dependency;

import ast.Node;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

public class DependencyGraph {

    public static class GraphNode {
        private Node.TriggerStatement self;
        private ArrayList<GraphNode> dependOn;
        private ArrayList<GraphNode> dominateOver;

        // wallet + token name : token amount -----------------
        private HashMap<String, BigInteger> assetIncrease;
        private HashMap<String, BigInteger> assetDecrease;
        private HashMap<String, BigInteger> estimatedAsset;
        //-----------------------------------------------------

        public GraphNode(Node.TriggerStatement self, ArrayList<GraphNode> dependOn,
                         ArrayList<GraphNode> dominateOver) {
            this.self = self;
            this.dependOn = dependOn;
            this.dominateOver = dominateOver;
            assetIncrease = new HashMap<>();
            assetDecrease = new HashMap<>();
            estimatedAsset = new HashMap<>();
        }

        public Node.TriggerStatement getSelf() {
            return self;
        }

        public ArrayList<GraphNode> getDependOn() {
            return dependOn;
        }

        public ArrayList<GraphNode> getDominateOver() {
            return dominateOver;
        }

        public HashMap<String, BigInteger> getAssetIncrease() {
            return assetIncrease;
        }

        public HashMap<String, BigInteger> getAssetDecrease() {
            return assetDecrease;
        }

        public HashMap<String, BigInteger> getEstimatedAsset() {
            return estimatedAsset;
        }

        public void setEstimatedAsset(HashMap<String, BigInteger> estimatedAsset) {
            this.estimatedAsset = estimatedAsset;
        }
    }

    private ArrayList<GraphNode> roots;
    private ArrayList<GraphNode> allNodes;

    public DependencyGraph(ArrayList<GraphNode> roots, ArrayList<GraphNode> allNodes) {
        this.roots = roots;
        this.allNodes = allNodes;
    }

    public ArrayList<GraphNode> getRoots() {
        return roots;
    }

    public ArrayList<GraphNode> getAllNodes() {
        return allNodes;
    }

}
