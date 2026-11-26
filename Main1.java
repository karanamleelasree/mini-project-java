import java.util.Scanner;

class Student {
    private int studentId;
    private String name;
    private String department;
    private double subject1Marks;
    private double subject2Marks;
    private double subject3Marks;
    private boolean marksEntered;

    public Student(int studentId, String name, String department) {
        this.studentId = studentId;
        this.name = name;
        this.department = department;
        this.marksEntered = false;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public boolean hasMarks() {
        return marksEntered;
    }

    public void setMarks(double subject1, double subject2, double subject3) {
        this.subject1Marks = subject1;
        this.subject2Marks = subject2;
        this.subject3Marks = subject3;
        this.marksEntered = true;
    }

    public double calculateTotal() {
        return subject1Marks + subject2Marks + subject3Marks;
    }

    public double calculateAverage() {
        return calculateTotal() / 3.0;
    }

    public String calculateGrade() {
        double avg = calculateAverage();
        if (avg >= 90 && avg <= 100) {
            return "A+";
        } else if (avg >= 80) {
            return "A";
        } else if (avg >= 70) {
            return "B";
        } else if (avg >= 60) {
            return "C";
        } else if (avg >= 50) {
            return "D";
        } else {
            return "Fail";
        }
    }

    public String getResultStatus() {
        return calculateAverage() >= 50.0 ? "PASS" : "FAIL";
    }

    public void displayStudentReport() {
        System.out.println("\n===== STUDENT REPORT =====");
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);

        if (!marksEntered) {
            System.out.println("Marks      : Not entered yet");
            return;
        }

        System.out.println("Total Marks: " + formatNumber(calculateTotal()));
        System.out.println("Average    : " + calculateAverage());
        System.out.println("Grade      : " + calculateGrade());
        System.out.println("Result     : " + getResultStatus());
    }

    private String formatNumber(double value) {
        if (value == (long) value) {
            return Long.toString((long) value);
        }
        return Double.toString(value);
    }
}

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = null;
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display Student");
            System.out.println("3. Enter Marks (3 Subjects)");
            System.out.println("4. Calculate Total");
            System.out.println("5. Calculate Average");
            System.out.println("6. Calculate Grade");
            System.out.println("7. Pass/Fail Result");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-8): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Department: ");
                    String dept = scanner.nextLine();

                    student = new Student(id, name, dept);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    if (student == null) {
                        System.out.println("No student record found. Please add a student first.");
                    } else {
                        student.displayStudentReport();
                    }
                    break;

                case 3:
                    if (student == null) {
                        System.out.println("Please add a student first.");
                    } else {
                        System.out.print("Enter Subject 1 Marks: ");
                        double m1 = scanner.nextDouble();

                        System.out.print("Enter Subject 2 Marks: ");
                        double m2 = scanner.nextDouble();

                        System.out.print("Enter Subject 3 Marks: ");
                        double m3 = scanner.nextDouble();

                        student.setMarks(m1, m2, m3);
                        System.out.println("Marks recorded successfully.");
                    }
                    break;

                case 4:
                    if (student == null || !student.hasMarks()) {
                        System.out.println("Please add a student and enter marks first.");
                    } else {
                        System.out.println("Total Marks: " + student.calculateTotal());
                    }
                    break;

                case 5:
                    if (student == null || !student.hasMarks()) {
                        System.out.println("Please add a student and enter marks first.");
                    } else {
                        System.out.println("Average Marks: " + student.calculateAverage());
                    }
                    break;

                case 6:
                    if (student == null || !student.hasMarks()) {
                        System.out.println("Please add a student and enter marks first.");
                    } else {
                        System.out.println("Grade: " + student.calculateGrade());
                    }
                    break;

                case 7:
                    if (student == null || !student.hasMarks()) {
                        System.out.println("Please add a student and enter marks first.");
                    } else {
                        System.out.println("Result: " + student.getResultStatus());
                    }
                    break;

                case 8:
                    System.out.println("Exiting Student Management System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please select between 1 and 8.");
            }
        } while (choice != 8);

        scanner.close();
    }
}