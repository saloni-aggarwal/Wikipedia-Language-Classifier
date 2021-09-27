import java.util.ArrayList;
import java.util.Arrays;

public class DecisionTree {

    /**
     * This function builds a decision tree model for the given set of data
     * @param examples          Examples using which decision tree is to be made
     * @param attributes        Attributes or features used for decision tree
     * @param parentExample     parent examples
     */
    public static void decisionTreeLearning(ArrayList<String[]> examples, ArrayList<Integer> attributes,
                                            ArrayList<String[]> parentExample) {
        Node node;
        //If there are no examples left then make the node of the result
        if (examples == null) {
            String result = pluralityValueClass.pluralityValue(parentExample);
            node = new Node(-1,result);
            Lab2.decisionTree.add(node);
        }

        /*
        If all the examples are either dutch or english as labels then return the prediction
        and make a node of the result
         */
        else if (Classifications.classification(examples) != null) {
            String result = Classifications.classification(examples);
            node = new Node(-1,result);
            Lab2.decisionTree.add(node);
        }

        /*
        If there are no attributes left then make the node of the result by finding
        plurality value of the examples
         */
        else if (!DecisionTreeMethods.isAttributeEmpty(attributes)) {
            String result = pluralityValueClass.pluralityValue(examples);
            node = new Node(-1,result);
            Lab2.decisionTree.add(node);
        }

        else {
            //To store I.G. of every attribute and initially assigned as -1.0
            double[] gains = new double[attributes.size()];
            Arrays.fill(gains, -1.0);
            //To store maximum gain
            double max = -1.0;
            //Store index of attribute whose gain is maximum
            int index = -1;
            //If there is only one attribute left then select that as index
            if(attributes.size() == 1)
                index = attributes.get(0);
            else {
                //For every attribute remaining
                for (Integer attribute : attributes) {
                    //Store the column values of the attribute
                    String[] oneAttribute = new String[examples.size()];
                    int k = 0;
                    for (String[] example : examples) {
                        oneAttribute[k] = example[attribute];
                        k++;
                    }
                    //Storing information gain
                    gains[attribute] = DecisionTreeMethods.importance(oneAttribute, examples);
                }

                //Finding maximum gain and its index
                for (int i = 0; i < gains.length; i++)
                    if (gains[i] > max) {
                        max = gains[i];
                        index = i;
                    }
            }
            //Removing attribute once it is selected
            attributes.remove(attributes.indexOf(index));

            //Storing left and right child accordingly
            ArrayList<String[]> examplesFalse = new ArrayList<>();
            ArrayList<String[]> examplesTrue = new ArrayList<>();
            for (String[] example : examples) {
                if (example[index].equals("false"))
                    examplesFalse.add(example);
                else
                    examplesTrue.add(example);
            }

            //Storing attributes for both the child
            ArrayList<Integer> childAttributes = new ArrayList<>();
            ArrayList<Integer> childAttributes2 = new ArrayList<>();
            childAttributes.addAll(attributes);
            childAttributes2.addAll(attributes);

            //Creating nodes for left and right child and calling decision tree recursively
            node = new Node(index, "false");
            Lab2.decisionTree.add(node);
            decisionTreeLearning(examplesFalse, childAttributes2, examples);
            node = new Node(index, "true");
            Lab2.decisionTree.add(node);
            decisionTreeLearning(examplesTrue, childAttributes, examples);
        }
    }
}
