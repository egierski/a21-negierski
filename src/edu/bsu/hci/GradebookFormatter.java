package edu.bsu.hci;

public class GradebookFormatter {

    String reportAllAssinments(Gradebook g) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < g.getNumberOfAssignmentsCompleted(); i++) {
            output.append("Assignment ")
                    .append(i + 1)
                    .append(" => ")
                    .append(g.getListOfCompleteAssignmentGrade().get(i))
                    .append("/3\n");
        }
        for (int i = g.getNumberOfAssignmentsCompleted(); i < g.getNumberOfAssignments(); i++)
            output.append("Assignment ")
                    .append(i + 1)
                    .append(" => --/3\n");
        return output.toString();
    }

}
