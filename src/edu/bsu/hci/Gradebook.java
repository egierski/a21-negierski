package edu.bsu.hci;

import java.util.ArrayList;

class Gradebook {

    Gradebook() {
    }

    private int numberOfAssignments;
    private int numberOfAssignmentsCompleted;
    private ArrayList<Integer> listOfCompleteAssignmentGrade = new ArrayList<>();
    private int assignmentPointsEarned;

    void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    void setNumberOfAssignmentsCompleted(int numberOfAssignmentsCompleted) {
        this.numberOfAssignmentsCompleted = numberOfAssignmentsCompleted;
    }

    int getNumberOfAssignmentsCompleted() {
        return this.numberOfAssignmentsCompleted;
    }

    int getNumberOfAssignments() {
        return this.numberOfAssignments;
    }

    private int getAssignmentPointsEarned() {
        return this.assignmentPointsEarned;
    }

    ArrayList<Integer> getListOfCompleteAssignmentGrade() {
        return listOfCompleteAssignmentGrade;

    }

    String getShortTermGradeWarningMessage() {
        return "Be weary of the short-term grade. By nature of the triage " +
                "grading scale being out of 3 possible points, a student with less than majority assignments graded is likely" +
                " to result in having a very volatile score. If you are attempting to look at your long term performance, it is " +
                "highty suggested to not even look at the short-term score.";
    }

    String getLongTermGradeInformationMessage() {
        return "Long-Term grading feedback, should, be prefered when dealing with triage grading. Long-term grading takes" +
                "all the assignments throughout the semester into account when calculating the grade. The long term grade" +
                "indicates the best possible percent you can finish the class with; assuming that you score a 3/3 on the" +
                "remaining assignments. This is exceptionally useful in situations where the student if trying to determine" +
                "if it is still possible for him/her to pass the class.";
    }

    private int calculateTotalAvailablePoints() {
        return (getNumberOfAssignments() * 3);
    }

    void setAssignmentPointsEarned() {
        this.assignmentPointsEarned = 0;
        for (int assignents : listOfCompleteAssignmentGrade) {
            this.assignmentPointsEarned = getAssignmentPointsEarned() + assignents;
        }
    }

    private int calculateAssumedAssignmentPoints() {
        return (getNumberOfAssignments() - getNumberOfAssignmentsCompleted()) * 3;
    }

    double calculateLongTermGrade() {
        double num = calculateAssumedAssignmentPoints() + getAssignmentPointsEarned();
        double den = calculateTotalAvailablePoints();
        return num / den * 100;
    }

    double calculateShortTermGrade() {
        double num = getAssignmentPointsEarned();
        double den = getNumberOfAssignmentsCompleted() * 3;
        return num / den * 100;
    }

    void addToCompletedAssignmentGrade(int grade) {
        this.listOfCompleteAssignmentGrade.add(grade);
    }

}
