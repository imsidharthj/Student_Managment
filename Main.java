import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    private List<Map<String, String>> listOfDictionaries = new ArrayList<>();

    public void add_student() {
        Scanner scanner = new Scanner(System.in);

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

        Map<String, String> student = new HashMap<>();
        student.put("name", name);
        student.put("ID", id);
        student.put("course", courseEnrollment);
        student.put("grade", grade);
        student.put("task", task);

        listOfDictionaries.add(student);

        // Optional: Save to CSV after adding each student
        saveToCSV("students.csv");
    }

    public void saveToCSV(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            // Write header
            writer.append("Name,ID,Course,Grade,Task\n");

            // Write each student as a row
            for (Map<String, String> student : listOfDictionaries) {
                writer.append(student.get("name"))
                      .append(',')
                      .append(student.get("ID"))
                      .append(',')
                      .append(student.get("course"))
                      .append(',')
                      .append(student.get("grade"))
                      .append(',')
                      .append(student.get("task"))
                      .append('\n');
            }

            System.out.println("Data saved to CSV file successfully.");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file.");
            e.printStackTrace();
        }
    }
}
