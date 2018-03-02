package edu.bsu.hci;

class GradebookParser {

    GradebookParser() {
    }

    int parseNumberOnly(String input) {
        return Integer.parseInt(input.replaceAll("\\D+", ""));
    }


}
