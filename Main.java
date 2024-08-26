import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.*;

public class Main {

    public void addStudent(String fileName) {
        Scanner scanner = new Scanner(System.in);
        List<Map<String, String>> students = new ArrayList<>();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Course Enrollment: ");
        String courseEnrollment = scanner.nextLine();

        System.out.print("Grade: ");
        String grade = scanner.nextLine();

        System.out.print("Tasks: ");
        String task = scanner.nextLine();
        scanner.close();

        Map<String, String> student = new HashMap<>();
        student.put("Name", name);
        student.put("ID", id);
        student.put("Course Enrollment", courseEnrollment);
        student.put("Grade", grade);
        student.put("Tasks", task);

        students.add(student);

        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (Map<String, String> studentData : students) {
                writer.append(String.join(",", studentData.values()));
                writer.append('\n');
            }
            System.out.println("Student added to CSV file successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }

    public void findAndEditStudent(String fileName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        scanner.close();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            boolean found = false;
    
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
    
                if (fields[1].equals(studentID)) {
                    System.out.println("Student found: ");
                    System.out.println("Name: " + fields[0]);
                    System.out.println("ID: " + fields[1]);
                    System.out.println("Course: " + fields[2]);
                    System.out.println("Grade: " + fields[3]);
                    System.out.println("Task: " + fields[4]);
    
                    System.out.print("Do you want to edit? (yes/no): ");
                    String choice = scanner.nextLine();
    
                    if (choice.equalsIgnoreCase("yes")) {
                        System.out.println("Enter new details (press enter to skip):");
    
                        System.out.print("Name: ");
                        String name = scanner.nextLine();
                        if (!name.isEmpty()) {
                            fields[0] = name;
                        }
    
                        System.out.print("Course Enrollment: ");
                        String courseEnrollment = scanner.nextLine();
                        if (!courseEnrollment.isEmpty()) {
                            fields[2] = courseEnrollment;
                        }
    
                        System.out.print("Grade: ");
                        String grade = scanner.nextLine();
                        if (!grade.isEmpty()) {
                            fields[3] = grade;
                        }
    
                        System.out.print("Tasks: ");
                        String task = scanner.nextLine();
                        if (!task.isEmpty()) {
                            fields[4] = task;
                        }
    
                        line = String.join(",", fields);
                    }
                    found = true;
                }
                lines.add(line);
            }
    
            if (!found) {
                System.out.println("Student with ID " + studentID + " not found.");
                return;
            }
    
            try (FileWriter writer = new FileWriter(fileName)) {
                for (String lineToWrite : lines) {
                    writer.append(lineToWrite);
                    writer.append('\n');
                }
                System.out.println("Student data updated successfully.");
    
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the CSV file.");
                e.printStackTrace();
            }
    
        } catch (IOException e) {
            System.out.println("An error occurred while reading the CSV file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main sms = new Main();
        sms.addStudent("students.csv");
        sms.findAndEditStudent("students.csv");
    }
}