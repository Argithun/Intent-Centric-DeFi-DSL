package optimize.dependency;

import ast.Node;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    private HashMap<Node.TriggerStatement, GraphNode> triggerStatementToNode;

    public DependencyGraph(ArrayList<GraphNode> roots, ArrayList<GraphNode> allNodes,
                           HashMap<Node.TriggerStatement, GraphNode> triggerStatementToNode) {
        this.roots = roots;
        this.allNodes = allNodes;
        this.triggerStatementToNode = triggerStatementToNode;
    }

    public ArrayList<GraphNode> getRoots() {
        return roots;
    }

    public ArrayList<GraphNode> getAllNodes() {
        return allNodes;
    }

    public HashMap<Node.TriggerStatement, GraphNode> getTriggerStatementToNode() {
        return triggerStatementToNode;
    }


    private static void calHeightOfSingleNode(DependencyGraph.GraphNode graphNode, int height,
                                             HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> heightToNodes) {
        heightToNodes.computeIfAbsent(height, k -> new ArrayList<>());
        ArrayList<DependencyGraph.GraphNode> graphNodesOnHeight = heightToNodes.get(height);

        if (graphNodesOnHeight.contains(graphNode)) {
            return;
        }
        graphNodesOnHeight.add(graphNode);

        if (graphNode.getDependOn() == null) {
            return;
        }
        for (DependencyGraph.GraphNode fathers : graphNode.getDependOn()) {
            calHeightOfSingleNode(fathers, height + 1, heightToNodes);
        }
    }

    public static HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> calHeightOfNodes(
            ArrayList<DependencyGraph.GraphNode> allNodes) {
        HashMap<Integer, ArrayList<DependencyGraph.GraphNode>> heightToNodes = new HashMap<>();
        ArrayList<DependencyGraph.GraphNode> zeroHeightNode = new ArrayList<>();

        for (DependencyGraph.GraphNode graphNode : allNodes) {
            if (graphNode.getDominateOver() == null || graphNode.getDominateOver().size() == 0) {
                zeroHeightNode.add(graphNode);
            }
        }
        for (DependencyGraph.GraphNode graphNode : zeroHeightNode) {
            calHeightOfSingleNode(graphNode, 0, heightToNodes);
        }

        return heightToNodes;
    }

    private static void helpGatherSonOfSingleNode(GraphNode graphNode, HashSet<GraphNode> sons) {
        sons.add(graphNode);
        for (GraphNode son : graphNode.getDominateOver()) {
            helpGatherSonOfSingleNode(son, sons);
        }
    }

    public static HashSet<GraphNode> gatherSonOfSingleNode(GraphNode graphNode) {
        HashSet<GraphNode> sons = new HashSet<>();
        for (GraphNode son : graphNode.getDominateOver()) {
            helpGatherSonOfSingleNode(son, sons);
        }
        return sons;
    }

    public static HashSet<GraphNode> gatherSonOfNodes(ArrayList<GraphNode> graphNodes) {
        HashSet<GraphNode> totalSons = new HashSet<>();
        for (GraphNode graphNode : graphNodes) {
            totalSons.addAll(gatherSonOfSingleNode(graphNode));
        }
        return totalSons;
    }

}
