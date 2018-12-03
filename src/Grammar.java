import java.util.ArrayList;
import java.util.Arrays;

public class Grammar {
    private String leftOperand;
    private ArrayList<String> rightOperands = new ArrayList<>();

    public Grammar(String leftInput, String rightInput) {
        this.leftOperand = leftInput;
        String[] inputs = rightInput.split("\\|");
        System.out.println(Arrays.toString(inputs));

        for (String operand : inputs) {
            rightOperands.add(operand);
        }
    }

    public String reduce(String parsingStack) {
        for (String operand : rightOperands) {
            if (operand.equals(parsingStack)) {
                return leftOperand;
            }
        }
        return parsingStack;
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
