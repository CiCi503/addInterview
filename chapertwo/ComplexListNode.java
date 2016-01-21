package chapertwo;

public class ComplexListNode {
    public int value;
    public ComplexListNode next;
    public ComplexListNode sibling;
    
    
    public ComplexListNode() {
        super();
    }

    public ComplexListNode(int value) {
        super();
        this.value = value;
    }

    @Override
    public String toString() {
        return "ComplexListNode [value=" + value + "]";
    }
}
