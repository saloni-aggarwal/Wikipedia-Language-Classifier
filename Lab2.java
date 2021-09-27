import java.io.*;
import java.util.ArrayList;

/**
 * This class creates a trained model either using decision tree or adaboost algorithm
 */
public class Lab2 {
    //Stores the nodes of decision tree
    public static ArrayList<Node> decisionTree = new ArrayList<>();
    //Stores the nodes of stumps created in adaboost
    public static ArrayList<AdaBoostNode> adaBoostTree = new ArrayList<>();
    public static ArrayList<AdaBoostNode> adaBoostTreeHelper = new ArrayList<>();
    //Stores left and right child examples for each stump
    public static ArrayList<String[]> examplesLeft;
    public static ArrayList<String[]> examplesRight;
    //Stores what left and right child have predicted
    public static String leftPredict;
    public static String rightPredict;
    //Stores attribute of each stump
    public static int adaBoostIndex;
    //Stores value of english or dutch
    public static int English = 1;
    public static int Dutch = -1;


    /**
     * This function takes the input from user and predicts the output according
     * to the user
     *
     * @param args      Command line arguments
     */
    public static void input(String[] args) throws IOException, ClassNotFoundException {
        //This stores if user wants to train or predict the data
        String action = args[0];
        //If user wants to train the model
        if (action.equals("train")) {
            ArrayList<Integer> attributes = new ArrayList<>();
            ArrayList<String[]> examples;
            //Storing number of attributes
            for (int i = 0; i < 6; i++)
                attributes.add(i);
            //Making dataset
            examples = ExampleSet.makingExampleSet(args[1]);

            //If the algorithm is decision tree
            if (args[3].equals("dt")) {
                DecisionTree.decisionTreeLearning(examples, attributes, examples);
                writingInFile.writeDecisionTreeInFile(args[2]);
                //If the algorithm is adaboost
            } else if (args[3].equals("ada")) {
                AdaBoost.adaBoost(examples, attributes, attributes.size());
                writingInFile.writeAdaBoostInFile(args[2]);
            }
            //If user wants to predict data
        } else if (action.equals("predict")) {
            ArrayList<String> outputArray = new ArrayList<>();
            ArrayList<String[]> examples;
            examples = ExampleSetForPrediction.makingExamplesForPrediction(args[2]);

            //Deserializing the hypothesis file
            File file1 = new File(args[1]);
            FileInputStream fileName = new FileInputStream(file1);
            ObjectInputStream in = new ObjectInputStream(fileName);

            //Storing it in a variable of Object type
            Object tree;
            //Reading file
            tree = in.readObject();
            //If it is an instance of Adaboost then predict output using adaboost
            if (tree instanceof AdaBoostNode) {
                for (String[] input : examples) {
                    String output = AdaBoostPrediction.predictingAdaBoost(input, 6, tree);
                    outputArray.add(output);
                }
            }
            //If the fileName is hypothesis1.txt then predict using adaboost
            else if(args[1].equals("hypothesis1.txt")){
                for (String[] input : examples) {
                    String output = AdaBoostPrediction.predictingAdaBoost(input, 6, tree);
                    outputArray.add(output);
                }
            }
            //Else predict using decision tree
            else {
                for (String[] input : examples) {
                    String output = DecisionTreePrediction.prediction(input, tree);
                    outputArray.add(output);
                }
            }
            //Displaying output
            for (String s : outputArray) System.out.println(s);
        }
        else
            System.out.println("Wrong input");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        input(args);
    }
}
