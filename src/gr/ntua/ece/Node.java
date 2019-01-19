package gr.ntua.ece;

public class Node extends Point {
    private String name;
    private long nodeId;
    private long lineId;

    public Node(double x, double y, String name, long nodeId, long lineId) {
        super(x, y);
        this.name = name;
        this.nodeId = nodeId;
        this.lineId = lineId;
    }

    public Node(String position, String name, long nodeId, long lineId) {
        super(position);
        this.name = name;
        this.nodeId = nodeId;
        this.lineId = lineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNodeId() {
        return nodeId;
    }

    public void setNodeId(long nodeId) {
        this.nodeId = nodeId;
    }

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }
}
