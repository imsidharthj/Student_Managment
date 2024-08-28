import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
                    System.out.print("Do you want to edit or delete? (edit/delete): ");
                    String choice = scanner.nextLine();
    
                    if (choice.equalsIgnoreCase("edit")) {
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
                    } else if (choice.equalsIgnoreCase("delete")) {
                        System.out.println("Student deleted successfully.");
                        continue; // skip adding this line to the list
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

    public void sortStudents(String fileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        Collections.sort(lines, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String[] fields1 = s1.split(",");
                String[] fields2 = s2.split(",");

                int nameCompare = fields1[0].compareTo(fields2[0]);
                if (nameCompare != 0) {
                    return nameCompare;
                }

                int idCompare = fields1[1].compareTo(fields2[1]);
                if (idCompare != 0) {
                    return idCompare;
                }

                return fields1[2].compareTo(fields2[2]);
            }
        });

        for (String lineToPrint : lines) {
            System.out.println(lineToPrint);
        }

    } catch (IOException e) {
        System.out.println("An error occurred while reading the CSV file.");
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    Main sms = new Main();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("Student Management System");
        System.out.println("1. Add Student");
        System.out.println("2. Find/Edit Student");
        System.out.println("3. List Students");
        System.out.println("4. Exit");

        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        switch (option) {
            case 1:
                sms.addStudent("students.csv");
                break;
            case 2:
                sms.findAndEditStudent("students.csv");
                break;
            case 3:
                sms.sortStudents("students.csv");
                break;
            case 4:
                System.out.println("Exiting...");
                return;
            default:
                System.out.println("Invalid option. Please choose again.");
        }
        scanner.close();
    }
}
}