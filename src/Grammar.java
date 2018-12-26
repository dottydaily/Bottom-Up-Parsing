import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Grammar {
    private String leftOperand;
    private ArrayList<String> rightOperands = new ArrayList<>();

    public Grammar(String leftInput, String rightInput) {
        this.leftOperand = leftInput;
        String[] inputs = rightInput.split("\\|");

        // add each operand in inputs into arrayList
        for (String operand : inputs) {
            rightOperands.add(operand);
        }
    }

    public String reduce(String parsingString, Stack<Character> inputStack) {

        // start with max len of parsingString
        for (int len = parsingString.length() ; len > 0 ; len--) {

            // checking with "len" length
            // move startIndex by 1 until this checking word reach the end of parsingString
            for (int startIndex = 0 ; startIndex <= parsingString.length()-len ; startIndex++) {

                // check with each operand in operand list
                for (String operand : rightOperands) {
                    if (operand.equals(parsingString.substring(startIndex, startIndex+len))) {
                        // print action if success reduce
                        System.out.printf("| $ %-15s | %15s $ | %-20s |\n", parsingString,
                                Main.printInputStack(inputStack), "Reduce "+leftOperand+" -> "+operand);
                        return parsingString.replace(
                                parsingString.substring(startIndex, startIndex+len),
                                leftOperand);
                    }
                }
            }
        }

        // extend the leftOperand if we have empty string in rightOperands
        // (this case will trigger if all of right operands can't reduce parsingString)
        if (hasEmptyString()) {
            System.out.printf("| $ %-15s | %15s $ | %-20s |\n", parsingString,
                    Main.printInputStack(inputStack), "Reduce "+leftOperand+" -> "+"ε");
            parsingString += leftOperand;
        }

        return parsingString;
    }

    public Boolean hasEmptyString() {
        for (String operand : rightOperands) {
            if (operand.equals("ε")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String fullgrammar = leftOperand + " -> ";
        for (int i = 0 ; i < rightOperands.size() ; i++) {
            fullgrammar += rightOperands.get(i);

            if (i < rightOperands.size()-1) {
                fullgrammar += " | ";
            }
        }
        return fullgrammar;
    }
}
