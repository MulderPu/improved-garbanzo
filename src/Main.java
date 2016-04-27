import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 3/28/2016.
 */
public class Main implements Serializable {
    static Scanner input = new Scanner(System.in);
    static int userInput;
    static  ArrayList<Student> studentList = new ArrayList<>();
    static  ArrayList<Unit> unitList = new ArrayList<>();
    static  ArrayList<Class> classList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList = new ArrayList<>();
    static  ArrayList<Assessment> assessmentList2 = new ArrayList<>();
    static ArrayList<Submission> submissionsList = new ArrayList<>();
    static boolean loop = true;

    public static void main(String[] args) {
        homeMenu();
    }

    private static void homeMenu(){
        while(userInput != 5) {
            printHeader("HOME >>");
            System.out.println("1) Student Menu");
            System.out.println("2) Unit Menu");
            System.out.println("3) Class Menu");
            System.out.println("4) Assessment Menu");
            System.out.println("5) Exit Application");
            System.out.println();
            System.out.println("Select number: ");

            try{
                userInput = input.nextInt();
            }catch(Exception e)
            {
                input.nextLine();
                System.out.println(e);
            }

            switch(userInput) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    unitMenu();
                    break;
                case 3:
                    classMenu();
                    break;
                case 4:
                    assessmentMenu();
                    break;
                case 5:
                    //Program Exit Point
                    break;
                default:
                    System.out.println("Invalid Input.");
                    System.out.println("Please re-enter a correct format.");
            }
        }
        System.out.println("Bye Bye");
    }

    private static void assessmentMenu() {
        while(userInput != 0)
        {
            printHeader("HOME >> ASSESSMENT MENU");
            System.out.println("1) Create Assessment");
            System.out.println("2) View Assessment");
            System.out.println("3) Edit Assessment");
            System.out.println("4) Delete Assessment");
            System.out.println("0) Back");
            System.out.println();
            System.out.println("Select number: ");

            try{
                userInput = input.nextInt();
                input.nextLine();
            }catch(Exception e)
            {
                input.nextLine();
                System.out.println(e);
            }

            switch(userInput)
            {
                case 1:
                    createAssessment();
                    break;
                case 2:
                    viewAssessment();
                    break;
                case 3:
                    editAssessment();
                    break;
                case 4:
                    deleteAssessment();
                    break;
                case 0:
                    //Back Point
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private static void deleteAssessment() {
        //read assessment file and store in arraylist
        readAssessmentFile();

        while (loop){
            System.out.println("Enter assessment name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < assessmentList.size(); i++) {
                if (assessmentList.get(i).getName().equals(inputName)) {
                    assessmentList.get(i).printAssessmentDetails();
                }
            }

            System.out.println("Assessment detail will not show if the assessment does not exist.");
            System.out.println("Confirm delete assessment? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("Enter assessment name again: ");
                String assessmentName = input.nextLine();
                System.out.println("Enter assessment weight : ");
                String assessmentWeight = input.nextLine();

                for (int i = 0; i < assessmentList.size(); i++) {
                    if (assessmentList.get(i).getName().equals(assessmentName) && assessmentList.get(i).getWeight().equals(assessmentWeight)) {
                        assessmentList.remove(i);
                        System.out.println("Assessment had been removed.");
                    }
                }
            }

            //re-write assessment file for the changes made
            writeAssessmentFile();

            System.out.println("Do you want to continue delete assessment? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void editAssessment() {
        //read assessment file and store in arraylist
        readAssessmentFile();

        while(loop) {
            System.out.println("Enter assessment name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < assessmentList.size(); i++) {
                if (assessmentList.get(i).getName().equals(inputName)) {
                    assessmentList.get(i).printAssessmentDetails();
                }
            }

            System.out.println("Assessment detail will not show if the assessment does not exist.");
            System.out.println("Confirm edit this assessment? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                int cont = 0;
                while (cont != 3) {
                    System.out.println("============================================================");
                    System.out.println("1) Edit Name");
                    System.out.println("2) Edit Weight");
                    System.out.println("3) Back");
                    System.out.println();
                    System.out.println("Select number: ");

                    try {
                        cont = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println(e);
                    }

                    switch (cont) {
                        case 1:
                            System.out.println("============================================================");
                            System.out.println("Old Name: ");
                            String oldName = input.nextLine();
                            System.out.println("New Name: ");
                            String newName = input.nextLine();
                            for (int i = 0; i < assessmentList.size(); i++) {
                                if (assessmentList.get(i).getName().equals(oldName)) {
                                    assessmentList.get(i).setName(newName);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("============================================================");
                            System.out.println("Old Weight: ");
                            String oldWeight = input.nextLine();
                            System.out.println("New Weight: ");
                            String newWeight = input.nextLine();
                            for (int i = 0; i < assessmentList.size(); i++) {
                                if (assessmentList.get(i).getWeight().equals(oldWeight)) {
                                    assessmentList.get(i).setWeight(newWeight);
                                }
                            }
                            break;
                        case 3:
                            //Back Point
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            System.out.println("Assessment had been updated.");
            //re-write assessment file for the changes
            writeAssessmentFile();

            System.out.println("Do you want to continue edit assessment? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void viewAssessment() {
        //read file and store in arraylist
        readAssessmentFile();

        while(loop){
            System.out.println("============================================================");
            System.out.println("Lists of assessment");
            System.out.println("============================================================");

            //loop for assessment list
            for (int i = 0; i < assessmentList.size(); i++) {
                System.out.println(i + "." + assessmentList.get(i).getName());
            }

            //show specific assessment details
            System.out.println("Enter assessment name for more info: ['exit' to back]");
            String inputName = input.nextLine();

            if (inputName.equals("exit")) {
                break;
            } else {
                System.out.println("============================================================");
                System.out.println("Assessment info");
                System.out.println("============================================================");
                for (int i = 0; i < assessmentList.size(); i++) {
                    if (assessmentList.get(i).getName().equals(inputName)) {
                        assessmentList.get(i).printAssessmentDetails();
                    }
                }
                pressEnterKeyToContinue();
            }
        }
    }

    private static void createAssessment() {

        readAssessmentFile();

        while(loop) {
            System.out.println("Do you wish to create an assessment? [y/n]");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                int cont = 0;
                while (cont != 6) {
                    System.out.println("============================================================");
                    System.out.println("1) Create Lab Exercise");
                    System.out.println("2) Create Assignment");
                    System.out.println("3) Create Test");
                    System.out.println("4) Create Quiz");
                    System.out.println("5) Create Final Exam");
                    System.out.println("6) Back");
                    System.out.println();
                    System.out.println("Select number: ");

                    try {
                        cont = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println(e);
                    }

                    switch (cont) {
                        case 1:
                            System.out.println("============================================================");
                            System.out.println("Please enter the lab exercise information:");
                            System.out.println("Name :");
                            String inputName = input.nextLine();
                            System.out.println("Weight :");
                            String inputWeight = input.nextLine();

                            //create lab, add to arraylist
                            Lab newLab = new Lab(inputName, inputWeight);

                            if (!inputName.equals("") || !inputWeight.equals("")) {
                                assessmentList.add(newLab);
                            }
                            break;
                        case 2:
                            System.out.println("============================================================");
                            System.out.println("Please enter the assignment information:");
                            System.out.println("Name :");
                            String inputAssignmentName = input.nextLine();
                            System.out.println("Weight :");
                            String inputAssignmentWeight = input.nextLine();

                            //create assignment, add to arraylist
                            Assignment newAssignment = new Assignment(inputAssignmentName, inputAssignmentWeight);

                            if (!inputAssignmentName.equals("") || !inputAssignmentWeight.equals("")) {
                                assessmentList.add(newAssignment);
                            }
                            break;
                        case 3:
                            System.out.println("============================================================");
                            System.out.println("Please enter the test information:");
                            System.out.println("Name :");
                            String inputTestName = input.nextLine();
                            System.out.println("Weight :");
                            String inputTestWeight = input.nextLine();

                            //create test, add to arraylist
                            Test newTest = new Test(inputTestName, inputTestWeight);

                            if (!inputTestName.equals("") || !inputTestWeight.equals("")) {
                                assessmentList.add(newTest);
                            }
                            break;
                        case 4:
                            System.out.println("============================================================");
                            System.out.println("Please enter the quiz information:");
                            System.out.println("Name :");
                            String inputQuizName = input.nextLine();
                            System.out.println("Weight :");
                            String inputQuizWeight = input.nextLine();

                            //create quiz, add to arraylist
                            Quiz newQuiz = new Quiz(inputQuizName, inputQuizWeight);

                            if (!inputQuizName.equals("") || !inputQuizWeight.equals("")) {
                                assessmentList.add(newQuiz);
                            }
                            break;
                        case 5:
                            System.out.println("============================================================");
                            System.out.println("Please enter the final exam information:");
                            System.out.println("Name :");
                            String inputFinalName = input.nextLine();
                            System.out.println("Weight :");
                            String inputFinalWeight = input.nextLine();

                            //create final, add to arraylist
                            Final newFinal = new Final(inputFinalName, inputFinalWeight);

                            if (!inputFinalName.equals("") || !inputFinalWeight.equals("")) {
                                assessmentList.add(newFinal);
                            }
                            break;
                        case 6:
                            //Back Point
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }

            //store arraylist in txt file
            writeAssessmentFile();

            System.out.println("Assessment had been created.");
            System.out.println("Do you want to continue create assessment? [y/n]");
            String result = input.next();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void classMenu() {
        while(userInput != 0)
        {
            printHeader("HOME >> CLASS MENU");
            System.out.println("1) Create Class");
            System.out.println("2) View Class");
            System.out.println("3) Edit Class");
            System.out.println("4) Delete Class");
            System.out.println("5) Add Unit to Class");
            System.out.println("6) Add Student to Class");
            System.out.println("7) Remove Student from Class");
            System.out.println("8) Add Assessment to Class");
            System.out.println("9) Assign Mark to Student's Assessment");
            System.out.println("10) View Student's Assessment Mark");
            System.out.println("11) View Summary Report");
            System.out.println("12) View Student Individual Report");
            System.out.println("0) Back");
            System.out.println();
            System.out.println("Select number: ");

            try{
                userInput = input.nextInt();
                input.nextLine();
            }catch(Exception e)
            {
                input.nextLine();
                System.out.println(e);
            }

            switch(userInput)
            {
                case 1:
                    createClass();
                    break;
                case 2:
                    viewClass();
                    break;
                case 3:
                    editClass();
                    break;
                case 4:
                    deleteClass();
                    break;
                case 5:
                    addUnitToClass();
                    break;
                case 6:
                    addStudToClass();
                    break;
                case 7:
                    removeStudFromClass();
                    break;
                case 8:
                    addAssessmentToClass();
                    break;
                case 9:
                    assignMark();
                    break;
                case 10:
                    ViewStudMark();
                    break;
                case 11:
                    ViewSummaryReport();
                    break;
                case 12:
                    ViewStudReport();
                    break;
                case 0:
                    //Back Point
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private static void ViewStudReport() {
        readClassFile();

        while (loop) {
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                    assessmentList = classList.get(i).getAssessmentList();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm view student individual report from this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                double totalmark = 0;
                System.out.println("Enter student name: ");
                String inputStudName = input.nextLine();

                System.out.println("============================================================");
                System.out.println("Student Individual Report");
                System.out.println("============================================================");



                for(int i =0; i < assessmentList.size() ; i++){
                    submissionsList = assessmentList.get(i).getSubmissionsList();
                    System.out.println(assessmentList.get(i).getName());
                    for(int k =0; k < submissionsList.size(); k++){
                        if(submissionsList.get(k).getName().equals(inputStudName)) {
                            System.out.println(submissionsList.get(k).getMark());
                            totalmark += submissionsList.get(k).getMark();
                        }
                    }
                }
                System.out.println("\n" + "Total mark = " + totalmark);

                if(totalmark < 50){
                    System.out.println("Grade: N" );
                }
                else if(totalmark >=50 && totalmark < 60){
                    System.out.println("Grade: P");
                }
                else if(totalmark >=60 && totalmark<70){
                    System.out.println("Grade: C");
                }
                else if(totalmark >=70 && totalmark<80){
                    System.out.println("Grade: D");
                }
                else {
                    System.out.println("Grade: HD");
                }
            }

            pressEnterKeyToContinue();
            System.out.println("Do you want to continue view student's individual report? [y/n]");
            String result = input.next();
            input.nextLine();

            if (result.equals("n")) {
                break;
            } else {
                loop = true;
            }
        }
    }

    private static void ViewSummaryReport() {
        readClassFile();

        while (loop) {
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();
            System.out.println("Confirm view summary report of this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                double totalMark = 0;
                System.out.println("============================================================");
                System.out.println("Unit Summary Report");
                System.out.println("============================================================");
                for (int i = 0; i < classList.size(); i++) {
                    if (classList.get(i).getName().equals(inputName)) {
                        assessmentList = classList.get(i).getAssessmentList();
                        classList.get(i).printSummaryReport();

                        for(int j =0; j < assessmentList.size() ; j++){
                            submissionsList = assessmentList.get(j).getSubmissionsList();
                            System.out.println("\n"+assessmentList.get(j).getName());
                            for(int k =0; k < submissionsList.size();k++) {
                                System.out.println(k + ". " + submissionsList.get(k).getId() + "\t" + submissionsList.get(k).getName() + "\t" + " Mark: " + submissionsList.get(k).getMark());
                            }
                        }

                        System.out.println("\n"+"Total Number of candidates presenting: "+ submissionsList.size());
                    }
                }

            }

            pressEnterKeyToContinue();
            System.out.println("Do you want to continue view student mark? [y/n]");
            String result = input.next();
            input.nextLine();

            if (result.equals("n")) {
                break;
            } else {
                loop = true;
            }
        }
    }

    private static void ViewStudMark() {
        readClassFile();

        while (loop) {
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                    assessmentList = classList.get(i).getAssessmentList();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm view student mark from this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                System.out.println("Enter assessment name you want to view student's mark?");
                String inputAss = input.nextLine();

                for(int i =0; i < assessmentList.size() ; i++){
                    if(assessmentList.get(i).getName().equals(inputAss)){
                        submissionsList = assessmentList.get(i).getSubmissionsList();
                        for(int j =0; j < submissionsList.size();j++) {
                            System.out.println("Student :" + submissionsList.get(j).getName() + "\t" + " Mark: " + submissionsList.get(j).getMark());
                        }
                    }
                }

            }

            pressEnterKeyToContinue();
            System.out.println("Do you want to continue view student mark? [y/n]");
            String result = input.next();
            input.nextLine();

            if (result.equals("n")) {
                break;
            } else {
                loop = true;
            }
        }
    }

    private static void assignMark() {

        readClassFile();

        while (loop) {
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                    assessmentList = classList.get(i).getAssessmentList();
                    break;
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm assign student's mark from this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                System.out.println("============================================================");
                System.out.println("Lists of Assessment Weight");
                System.out.println("============================================================");

                //loop assessment list
                for (int i = 0; i < assessmentList.size(); i++) {
                    System.out.println(i + "." + assessmentList.get(i).getName() +"\t \t \t" + "Weight:" +  assessmentList.get(i).getWeight());
                }

                System.out.println("Enter assessment name you want to assign mark to the student?");
                String inputAss = input.nextLine();
                System.out.println("Name of the student: ");
                String inputStudName = input.nextLine();
                System.out.println("Mark of the student: ");
                double inputStudMark = input.nextInt();
                input.nextLine();

                for(int i =0; i < assessmentList.size() ; i++){
                    if(assessmentList.get(i).getName().equals(inputAss)){
                        submissionsList = assessmentList.get(i).getSubmissionsList();
                        for(int k =0; k < submissionsList.size(); k++){
                            if(submissionsList.get(k).getName().equals(inputStudName) ){
                                submissionsList.get(k).setMark(inputStudMark);
                                break;
                            }
                        }
                    }
                }

            }

            //store arraylist in txt file
            writeClassFile();

            System.out.println("Mark had been assigned.");
            System.out.println("Do you want to continue assign mark to student? [y/n]");
            String result = input.next();
            input.nextLine();

            if (result.equals("n")) {
                break;
            } else {
                loop = true;
            }
        }
    }

    private static void addAssessmentToClass() {

        readClassFile();
        readAssessmentFile();

        while(loop){
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm add assessment to this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("Enter assessment name you wish to add:");
                String inputAssessment = input.nextLine();

                for (int i = 0; i < assessmentList.size(); i++) {
                    if(assessmentList.get(i).getName().equals(inputAssessment)){
                        for (int j = 0; j < classList.size(); j++) {
                            if (classList.get(j).getName().equals(inputName)) {
                                classList.get(j).setAssessment(assessmentList.get(i));
                            }
                        }
                    }
                }

                for (int i = 0; i < classList.size(); i++) {
                    studentList = classList.get(i).getStudentList();
                    assessmentList2 = classList.get(i).getAssessmentList();
                }

                for(int i =0; i < assessmentList2.size() ; i++){
                    if(assessmentList2.get(i).getName().equals(inputAssessment)){
                        for(int j = 0; j < studentList.size(); j++){
                            String studName = studentList.get(j).getName();
                            String studId = studentList.get(j).getId();

                            Submission newSub = new Submission(studName, studId);
                            assessmentList2.get(i).setSubmission(newSub);
                        }
                    }
                }
            }

            System.out.println("Class had been updated.");
            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to continue add assessment? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void removeStudFromClass() {
        readClassFile();

        while(loop){
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm remove student from this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                System.out.println("Enter student name you wish to remove:");
                String inputStud = input.nextLine();

                for (int i = 0; i < classList.size(); i++) {
                    classList.get(i).removeStudent(inputStud);
                    assessmentList2 = classList.get(i).getAssessmentList();
                    for(int j = 0; j < assessmentList2.size(); j++){
                        assessmentList2.get(j).removeSubmission(inputStud);
                    }
                    System.out.println("Student had been removed.");
                }
            }

            System.out.println("Class had been updated.");
            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to continue remove student from class? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void addStudToClass(){
        readClassFile();
        readStudentFile();

        while(loop){
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm add student to this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")) {
                System.out.println("============================================================");
                System.out.println("Lists of students");
                System.out.println("============================================================");
                for (int i = 0; i < studentList.size(); i++) {
                    System.out.println(i + "." + studentList.get(i).getName());
                }

                System.out.println("Enter student name you wish to add:");
                String inputStud = input.nextLine();

                for (int i = 0; i < studentList.size(); i++) {
                    if(studentList.get(i).getName().equals(inputStud)){
                        for (int j = 0; j < classList.size(); j++) {
                            if (classList.get(j).getName().equals(inputName)) {
                                classList.get(j).enrollStudent(studentList.get(i));
                                System.out.println("Student had been added into the class.");
                            }
                        }
                    }
                }
            }

            System.out.println("Class had been updated.");
            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to continue add student to class? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void addUnitToClass() {
        readClassFile();
        readUnitFile();

        while(loop){
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm add unit to this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("============================================================");
                System.out.println("Lists of units");
                System.out.println("============================================================");

                //loop for unit list
                for (int i = 0; i < unitList.size(); i++) {
                    System.out.println(i + "." + unitList.get(i).getName());
                }

                System.out.println("Enter unit name you wish to add:");
                String inputUnit = input.nextLine();

                for (int i = 0; i < unitList.size(); i++) {
                    if(unitList.get(i).getName().equals(inputUnit)){
                        for (int j = 0; j < classList.size(); j++) {
                            if (classList.get(j).getName().equals(inputName)) {
                                classList.get(j).setUnit(unitList.get(i));
                            }
                        }
                    }
                }
            }

            System.out.println("Class had been updated.");
            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to exit? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("y")){
                break;
            }
            else{
                loop=true;
            }
        }

    }

    private static void deleteClass() {
        //read class file and store in arraylist
        readClassFile();

        while (loop){
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm delete class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("Enter class name again: ");
                String className = input.nextLine();
                System.out.println("Enter class code : ");
                String classCode = input.nextLine();

                for (int i = 0; i < classList.size(); i++) {
                    if (classList.get(i).getName().equals(className) && classList.get(i).getCode().equals(classCode)) {
                        classList.remove(i);
                        System.out.println("Class had been removed.");
                    }
                }
            }

            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to continue delete class? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void editClass() {
        //read class file and store in arraylist
        readClassFile();

        while(loop) {
            System.out.println("Enter class name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < classList.size(); i++) {
                if (classList.get(i).getName().equals(inputName)) {
                    classList.get(i).printClassDetails();
                }
            }

            System.out.println("Class detail will not show if the class is not exist.");
            System.out.println("Confirm edit this class? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                int cont = 0;
                while (cont != 5) {
                    System.out.println("============================================================");
                    System.out.println("1) Edit Name");
                    System.out.println("2) Edit Code");
                    System.out.println("3) Edit Semester");
                    System.out.println("4) Edit Year");
                    System.out.println("5) Back");
                    System.out.println();
                    System.out.println("Select number: ");

                    try {
                        cont = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println(e);
                    }

                    switch (cont) {
                        case 1:
                            System.out.println("============================================================");
                            System.out.println("Old Name: ");
                            String oldName = input.nextLine();
                            System.out.println("New Name: ");
                            String newName = input.nextLine();
                            for (int i = 0; i < classList.size(); i++) {
                                if (classList.get(i).getName().equals(oldName)) {
                                    classList.get(i).setName(newName);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("============================================================");
                            System.out.println("Old Code: ");
                            String oldCode = input.nextLine();
                            System.out.println("New Code: ");
                            String newCode = input.nextLine();
                            for (int i = 0; i < classList.size(); i++) {
                                if (classList.get(i).getCode().equals(oldCode)) {
                                    classList.get(i).setCode(newCode);
                                }
                            }
                            break;
                        case 3:
                            System.out.println("============================================================");
                            System.out.println("Old Semester: ");
                            String oldSem = input.nextLine();
                            System.out.println("New Semester: ");
                            String newSem = input.nextLine();
                            for (int i = 0; i < classList.size(); i++) {
                                if (classList.get(i).getSem().equals(oldSem)) {
                                    classList.get(i).setSem(newSem);
                                }
                            }
                            break;
                        case 4:
                            System.out.println("============================================================");
                            System.out.println("Old Year: ");
                            String oldYear = input.nextLine();
                            System.out.println("New Year: ");
                            String newYear = input.nextLine();
                            for (int i = 0; i < classList.size(); i++) {
                                if (classList.get(i).getYear().equals(oldYear)) {
                                    classList.get(i).setYear(newYear);
                                }
                            }
                            break;
                        case 5:
                            //Back Point
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            System.out.println("Class had been updated.");
            //re-write class file for the changes made
            writeClassFile();

            System.out.println("Do you want to continue edit class? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void viewClass() {
        //read file and store in arraylist
        readClassFile();

        while(loop){
            System.out.println("============================================================");
            System.out.println("Lists of classes");
            System.out.println("============================================================");

            //loop for class list
            for (int i = 0; i < classList.size(); i++) {
                System.out.println(i + "." + classList.get(i).getName());
            }

            //show specific class details
            System.out.println("Enter class name for more info: ['exit' to back]");
            String inputName = input.nextLine();

            if (inputName.equals("exit")) {
                break;
            } else {
                System.out.println("============================================================");
                System.out.println("Class info");
                System.out.println("============================================================");
                for (int i = 0; i < classList.size(); i++) {
                    if (classList.get(i).getName().equals(inputName)) {
                        classList.get(i).printClassDetails();
                    }
                }
                pressEnterKeyToContinue();
            }
        }
    }

    private static void createClass() {
        //read file and store in arraylist
        readClassFile();

        while(loop) {
            System.out.println("Do you wish to create a class? [y/n]");
            String ans = input.next();
            input.nextLine();

            if(ans.equals("n")){
                break;
            }

            System.out.println("Please enter the class information:");
            System.out.println("Name :");
            String inputName = input.nextLine();
            System.out.println("Code :");
            String inputCode = input.nextLine();
            System.out.println("Sem :");
            String inputSem = input.nextLine();
            System.out.println("Year :");
            String inputYear = input.nextLine();

            //create class, add to arraylist
            Class newClass = new Class(inputName, inputCode, inputSem, inputYear);

            if (!inputName.equals("") || !inputCode.equals("")  || !inputSem.equals("")  || !inputYear.equals("")) {
                classList.add(newClass);
            }

            //store arraylist in txt file
            writeClassFile();

            System.out.println("Class had been created.");
            System.out.println("Do you want to continue create class? [y/n]");
            String result = input.next();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void unitMenu() {
        while(userInput != 0)
        {
            printHeader("HOME >> UNIT MENU");
            System.out.println("1) Create Unit");
            System.out.println("2) View Unit");
            System.out.println("3) Edit Unit");
            System.out.println("4) Delete Unit");
            System.out.println("0) Back");
            System.out.println();
            System.out.println("Select number: ");

            try{
                userInput = input.nextInt();
                input.nextLine();
            }catch(Exception e)
            {
                input.nextLine();
                System.out.println(e);
            }

            switch(userInput)
            {
                case 1:
                    createUnit();
                    break;
                case 2:
                    viewUnit();
                    break;
                case 3:
                    editUnit();
                    break;
                case 4:
                    deleteUnit();
                    break;
                case 0:
                    //Back Point
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private static void deleteUnit() {
        //read unit file and store in arraylist
        readUnitFile();

        while (loop){
            System.out.println("Enter unit name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < unitList.size(); i++) {
                if (unitList.get(i).getName().equals(inputName)) {
                    unitList.get(i).printUnitDetails();
                }
            }

            System.out.println("Unit detail will not show if the unit is not exist.");
            System.out.println("Confirm delete unit? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("Enter unit name again: ");
                String unitName = input.nextLine();
                System.out.println("Enter unit code : ");
                String unitCode = input.nextLine();

                for (int i = 0; i < unitList.size(); i++) {
                    if (unitList.get(i).getName().equals(unitName) && unitList.get(i).getCode().equals(unitCode)) {
                        unitList.remove(i);
                        System.out.println("Unit had been removed.");
                    }
                }
            }

            //re-write unit file for the changes made
            writeUnitFile();

            System.out.println("Do you want to continue delete unit? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void editUnit() {
        //read unit file and store in arraylist
        readUnitFile();

        while(loop) {
            System.out.println("Enter unit name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < unitList.size(); i++) {
                if (unitList.get(i).getName().equals(inputName)) {
                    unitList.get(i).printUnitDetails();
                }
            }

            System.out.println("Unit detail will not show if the unit is not exist.");
            System.out.println("Confirm edit this unit? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                int cont = 0;
                while (cont != 3) {
                    System.out.println("============================================================");
                    System.out.println("1) Edit Name");
                    System.out.println("2) Edit Code");
                    System.out.println("3) Back");
                    System.out.println();
                    System.out.println("Select number: ");

                    try {
                        cont = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println(e);
                    }

                    switch (cont) {
                        case 1:
                            System.out.println("============================================================");
                            System.out.println("Old Name: ");
                            String oldName = input.nextLine();
                            System.out.println("New Name: ");
                            String newName = input.nextLine();
                            for (int i = 0; i < unitList.size(); i++) {
                                if (unitList.get(i).getName().equals(oldName)) {
                                    unitList.get(i).setName(newName);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("============================================================");
                            System.out.println("Old Code: ");
                            String oldCode = input.nextLine();
                            System.out.println("New Code: ");
                            String newCode = input.nextLine();
                            for (int i = 0; i < unitList.size(); i++) {
                                if (unitList.get(i).getCode().equals(oldCode)) {
                                    unitList.get(i).setCode(newCode);
                                }
                            }
                            break;
                        case 3:
                            //Back Point
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            System.out.println("Unit had been updated.");
            //re-write unit file for the changes made
            writeUnitFile();

            System.out.println("Do you want to continue edit unit? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void viewUnit() {
        //read file and store in arraylist
        readUnitFile();

        while(loop){
            System.out.println("============================================================");
            System.out.println("Lists of units");
            System.out.println("============================================================");

            //loop for unit list
            for (int i = 0; i < unitList.size(); i++) {
                System.out.println(i + "." + unitList.get(i).getName());
            }

            //show specific unit details
            System.out.println("Enter unit name for more info: ['exit' to back]");
            String inputName = input.nextLine();

            if (inputName.equals("exit")) {
                break;
            } else {

                System.out.println("============================================================");
                System.out.println("Unit info");
                System.out.println("============================================================");
                for (int i = 0; i < unitList.size(); i++) {
                    if (unitList.get(i).getName().equals(inputName)) {
                        unitList.get(i).printUnitDetails();
                    }
                }
                pressEnterKeyToContinue();
            }
        }
    }

    private static void createUnit() {
        //read file and store in arraylist
        readUnitFile();

        while(loop) {
            System.out.println("Do you wish to create a unit? [y/n]");
            String ans = input.next();
            input.nextLine();

            if(ans.equals("n")){
                break;
            }

            System.out.println("Please enter the unit information:");
            System.out.println("Name :");
            String inputName = input.nextLine();
            System.out.println("Code :");
            String inputCode = input.nextLine();

            //create unit, add to arraylist
            Unit newUnit = new Unit(inputName, inputCode);

            if (!inputName.equals("") || !inputCode.equals("")) {
                unitList.add(newUnit);
            }

            //store arraylist in txt file
            writeUnitFile();

            System.out.println("Unit had been created.");
            System.out.println("Do you want to continue create unit? [y/n]");
            String result = input.next();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void studentMenu() {
        while(userInput != 0)
        {
            printHeader("HOME >> STUDENT MENU");
            System.out.println("1) Create Student");
            System.out.println("2) View Student");
            System.out.println("3) Edit Student");
            System.out.println("4) Delete Student");
            System.out.println("0) Back");
            System.out.println();
            System.out.println("Select number: ");

            try{
                userInput = input.nextInt();
                input.nextLine();
            }catch(Exception e)
            {
                input.nextLine();
                System.out.println(e);
            }

            switch(userInput)
            {
                case 1:
                    createStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    editStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 0:
                    //Back Point
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    private static void deleteStudent() {
        //read student file and store in arraylist
        readStudentFile();

        while (loop){
            System.out.println("Enter student name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getName().equals(inputName)) {
                    studentList.get(i).printStudentDetails();
                }
            }

            System.out.println("Student detail will not show if the student is not exist.");
            System.out.println("Confirm delete? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                System.out.println("Enter student name again: ");
                String StudName = input.nextLine();
                System.out.println("Enter student Id : ");
                String StudId = input.nextLine();

                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getName().equals(StudName) && studentList.get(i).getId().equals(StudId)) {
                        studentList.remove(i);
                        System.out.println("Student had been removed.");
                    }
                }
            }

            //re-write student file for the changes made
            writeStudentFile();

            System.out.println("Do you want to continue delete student? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void editStudent() {
        //read student file and store in arraylist
        readStudentFile();

        while(loop) {
            System.out.println("Enter student name: ");
            String inputName = input.nextLine();

            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getName().equals(inputName)) {
                    studentList.get(i).printStudentDetails();
                }
            }

            System.out.println("Student detail will not show if the student is not exist.");
            System.out.println("Confirm edit this student? [y/n] ");
            String ans = input.next();
            input.nextLine();

            if (ans.equals("y")){
                int cont = 0;
                while (cont != 4) {
                    System.out.println("============================================================");
                    System.out.println("1) Edit Name");
                    System.out.println("2) Edit ID");
                    System.out.println("3) Edit Program Enrolled");
                    System.out.println("4) Back");
                    System.out.println();
                    System.out.println("Select number: ");

                    try {
                        cont = input.nextInt();
                        input.nextLine();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println(e);
                    }

                    switch (cont) {
                        case 1:
                            System.out.println("============================================================");
                            System.out.println("Old Name: ");
                            String oldName = input.nextLine();
                            System.out.println("New Name: ");
                            String newName = input.nextLine();
                            for (int i = 0; i < studentList.size(); i++) {
                                if (studentList.get(i).getName().equals(oldName)) {
                                    studentList.get(i).setName(newName);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("============================================================");
                            System.out.println("Old ID: ");
                            String oldId = input.nextLine();
                            System.out.println("New ID: ");
                            String newId = input.nextLine();
                            for (int i = 0; i < studentList.size(); i++) {
                                if (studentList.get(i).getId().equals(oldId)) {
                                    studentList.get(i).setId(newId);
                                }
                            }
                            break;
                        case 3:
                            System.out.println("============================================================");
                            System.out.println("Old Program: ");
                            String oldProgram = input.nextLine();
                            System.out.println("New Program: ");
                            String newProgram = input.nextLine();
                            for (int i = 0; i < studentList.size(); i++) {
                                if (studentList.get(i).getProgram().equals(oldProgram)) {
                                    studentList.get(i).setProgram(newProgram);
                                }
                            }
                            break;
                        case 4:
                            //Back Point
                            break;
                        default:
                            System.out.println("Invalid Input");
                    }
                }
            }
            System.out.println("Student had been updated.");
            //re-write student file for the changes made
            writeStudentFile();

            System.out.println("Do you want to continue edit student? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    private static void viewStudent() {
        //read file and store in arraylist
        readStudentFile();

        while(loop){
            System.out.println("============================================================");
            System.out.println("Lists of students");
            System.out.println("============================================================");

            //loop for student list
            for (int i = 0; i < studentList.size(); i++) {
                System.out.println(i + "." + studentList.get(i).getName());
            }

            //show specific student details
            System.out.println("Enter student name for more info: ['exit' to back]");
            String inputName = input.nextLine();

            if (inputName.equals("exit")) {
                break;
            } else {

                System.out.println("============================================================");
                System.out.println("Student info");
                System.out.println("============================================================");
                for (int i = 0; i < studentList.size(); i++) {
                    if (studentList.get(i).getName().equals(inputName)) {
                        studentList.get(i).printStudentDetails();
                    }
                }
                pressEnterKeyToContinue();
            }
        }
    }

    private static void createStudent() {

        //read file and store in arraylist
        readStudentFile();

        while(loop) {
            System.out.println("Do you wish to create a student? [y/n]");
            String ans = input.next();
            input.nextLine();

            if(ans.equals("n")){
                break;
            }

            System.out.println("Please enter the student information:");
            System.out.println("Name :");
            String inputName = input.nextLine();
            System.out.println("ID :");
            String inputId = input.nextLine();
            System.out.println("Program Enrolled: ");
            String inputProgram = input.nextLine();

            //create student, add to arraylist
            Student newStudent = new Student(inputName, inputId, inputProgram);

            if (!inputName.equals("") || !inputId.equals("") || !inputProgram.equals("")) {
                studentList.add(newStudent);
            }

            //store arraylist in txt file
            writeStudentFile();

            System.out.println("Student had been created.");
            System.out.println("Do you want to continue create student? [y/n]");
            String result = input.next();
            input.nextLine();

            if(result.equals("n")){
                break;
            }
            else{
                loop=true;
            }
        }
    }

    //print header of console application
    private static void printHeader(String title) {
        System.out.println("============================================================");
        System.out.println("Student Management Application");
        System.out.println("============================================================");
        System.out.println(title);
        System.out.println();
    }

    //press enter to continue, PAUSE
    private static void pressEnterKeyToContinue()
    {
        System.out.println("Press enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    //read student file
    private static void readStudentFile(){
        try {
            FileInputStream fis = new FileInputStream("student.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            studentList = (ArrayList) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //write student file
    private static void writeStudentFile(){
        try {
            FileOutputStream fos = new FileOutputStream("student.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(studentList);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //read unit file
    private static void readUnitFile(){
        try {
            FileInputStream fis2 = new FileInputStream("unit.txt");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            unitList = (ArrayList) ois2.readObject();
            ois2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //write unit file
    private static void writeUnitFile(){
        try {
            FileOutputStream fos2 = new FileOutputStream("unit.txt");
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(unitList);
            oos2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //read class file
    private static void readClassFile(){
        try {
            FileInputStream fis3 = new FileInputStream("class.txt");
            ObjectInputStream ois3 = new ObjectInputStream(fis3);
            classList = (ArrayList) ois3.readObject();
            ois3.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //write class file
    private static void writeClassFile(){
        try {
            FileOutputStream fos3 = new FileOutputStream("class.txt");
            ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
            oos3.writeObject(classList);
            oos3.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //read class file
    private static void readAssessmentFile(){
        try {
            FileInputStream fis4 = new FileInputStream("assessment.txt");
            ObjectInputStream ois4 = new ObjectInputStream(fis4);
            assessmentList = (ArrayList) ois4.readObject();
            ois4.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //write class file
    private static void writeAssessmentFile(){
        try {
            FileOutputStream fos4 = new FileOutputStream("assessment.txt");
            ObjectOutputStream oos4 = new ObjectOutputStream(fos4);
            oos4.writeObject(assessmentList);
            oos4.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
