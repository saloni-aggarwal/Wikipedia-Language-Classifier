import java.io.Serial;
import java.io.Serializable;

/**
 * This class is the nodes for Adaboost stumps storing attribute of each stump
 * along with its amount of say and what value has it predicted
 */
class AdaBoostNode implements Serializable {
    @Serial
    private static final long serialVersionUID = 6266418448701948775L;
    String type;
    double amountOfSay;
    int attribute;
    String value;
    String prediction;

    public AdaBoostNode(double amountOfSay, int attribute, String value, String prediction) {
        this.type = "Adaboost";
        this.amountOfSay = amountOfSay;
        this.attribute = attribute;
        this.value = value;
        this.prediction = prediction;
    }
}
