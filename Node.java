import java.io.Serial;
import java.io.Serializable;

/**
 * This node class saves the attribute and its value (True, False or English/Dutch) i.e.
 * each node represents a node in a decision tree
 */
class Node implements Serializable {
    @Serial
    private static final long serialVersionUID = 7473635044717921869L;
    int attribute;
    String value;

    public Node(int attribute, String value) {
        this.attribute = attribute;
        this.value = value;
    }
}
