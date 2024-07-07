package Lists.History;

public class HistoryProductNode {
    public HistoryProduct data;
    public HistoryProductNode next;

    public HistoryProductNode(HistoryProduct data) {
        this.data = data;
        this.next = null;
    }
}

