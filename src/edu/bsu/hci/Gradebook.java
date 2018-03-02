package edu.bsu.hci;

import java.util.ArrayList;

class Gradebook {

    Gradebook() {
    }

    private int numberOfAssignments;
    private int numberOfAssignmentsCompleted;
    private ArrayList<Integer> completedAssignmentGrade = new ArrayList<>();
    private int numberOfPointsEarned;

    void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    void setNumberOfAssignmentsCompleted(int numberOfAssignmentsCompleted) {
        this.numberOfAssignmentsCompleted = numberOfAssignmentsCompleted;
    }

    int getNumberOfAssignmentsCompleted() {
        return this.numberOfAssignmentsCompleted;
    }

    private int getNumberOfAssignments() {
        return this.numberOfAssignments;
    }

    private int getNumberOfPointsEarned() {
        return this.numberOfPointsEarned;
    }

    String getShortTermGradeWarningMessage() {
        return "Be weary of the short-term grade. By nature of the triage " +
                "grading scale being out of 3 possible points, a student with less than majority assignments graded is likely" +
                " to result in having a very volatile score. If you are attempting to look at your long term performance, it is " +
                "highty suggested to not even look at the short-term score.";
    }

    private int calculateTotalPoints() {
        return (getNumberOfAssignments() * 3);
    }

    void setPointsEarned() {
        for (int assignents : completedAssignmentGrade) {
            this.numberOfPointsEarned = getNumberOfPointsEarned() + assignents;
        }
    }

    private int calculateAssumedPoints() {
        return (getNumberOfAssignments() - getNumberOfAssignmentsCompleted()) * 3;
    }

    double calculateLongTermGrade() {
        double num = calculateAssumedPoints() + getNumberOfPointsEarned();
        double den = calculateTotalPoints();
        return num / den * 100;
    }

    double calculateShortTermGrade() {
        double num = getNumberOfPointsEarned();
        double den = getNumberOfAssignmentsCompleted() * 3;
        return num / den * 100;
    }

    void addToCompletedAssignmentGrade(int grade) {
        this.completedAssignmentGrade.add(grade);
    }

    String reportAllAssinments() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getNumberOfAssignmentsCompleted(); i++) {
            output.append("Assignment ")
                    .append(i + 1)
                    .append(" => ")
                    .append(completedAssignmentGrade.get(i))
                    .append("/3\n");
        }
        return output.toString();
    }

}
