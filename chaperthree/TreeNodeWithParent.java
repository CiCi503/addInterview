package chaperthree;

public class TreeNodeWithParent {
    public int value;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
    public TreeNodeWithParent parent;
    
    public TreeNodeWithParent() {}

    public TreeNodeWithParent(int value) {
        this.value = value;
    }
}
