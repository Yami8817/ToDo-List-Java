import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ToDoList {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> taskList = new ArrayList<>(Arrays.asList("ITP Activity"));
    private static ArrayList<String> deadlineList = new ArrayList<>(Arrays.asList("10-29-2024"));
    private static ArrayList<Boolean> taskStatus = new ArrayList<>(Arrays.asList(false));

    public static void mainMenu() {
        System.out.println("\nWelcome to My ToDo List");
        System.out.println("\tMain Menu:");
        System.out.println("[1] Add Task");
        System.out.println("[2] View Task");
        System.out.println("[3] Delete Task");
        System.out.println("[4] Mark Task as Completed");
        System.out.println("[5] Exit");
        System.out.print(": ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                viewTask();
                break;
            case 3:
                delTask();
                break;
            case 4:
                markTask();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
                mainMenu();
                break;
        }
    }

    public static void addTask() {
        System.out.println("\nWelcome to My ToDo List");
        System.out.println("\tAdd Task:");
        System.out.print("Enter Task Name: ");
        String todo = scanner.nextLine();
        System.out.print("Set deadline date (MM-DD): ");
        String date = scanner.nextLine();
        date = formatDate(date);

        taskList.add(todo);
        deadlineList.add(date);
        taskStatus.add(false);

        System.out.println("\nTask Added Successfully!");
        mainMenu();
    }

    public static String formatDate(String date) {
        LocalDate today = LocalDate.now();
        String currentYear = String.valueOf(today.getYear());

        if (date.matches("\\d{2}-\\d{2}")) {
            date = date + "-" + currentYear;
        } else if (!date.matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("Invalid date format. Please use MM-DD or MM-DD-YYYY.");
            addTask();
        }
        return date;
    }

    public static void viewTask() {
        if (taskList.isEmpty()) {
            System.out.println("\nNo tasks to display.");
            mainMenu();
            return;
        }

        System.out.println("\nWelcome to My ToDo List");
        System.out.println("\tView Task:");

        System.out.println("==============================================================================================");
        System.out.printf("| %-5s | %-35s | %-25s |\n", "Mark", "To Do:", "Deadline");
        System.out.println("==============================================================================================");

        for (int i = 0; i < taskList.size(); i++) {
            String task = taskList.get(i);
            String deadline = deadlineList.get(i);
            String mark = taskStatus.get(i) ? "[/]" : "[]";

            System.out.printf("| %-5s | %-35s | %-25s |\n", mark, (i + 1) + ". " + task, deadline);
        }
        System.out.println("==============================================================================================");

        mainMenu();
    }

    public static void delTask() {
        System.out.println("\nWelcome to My ToDo List");
        System.out.println("\tDelete Task:");

        if (taskList.isEmpty()) {
            System.out.println("No tasks available to delete.");
            mainMenu();
            return;
        }

        System.out.print("Enter task number to delete: ");
        int taskNo = scanner.nextInt();

        if (taskNo > 0 && taskNo <= taskList.size()) {
            taskList.remove(taskNo - 1);
            deadlineList.remove(taskNo - 1);
            taskStatus.remove(taskNo - 1);
            System.out.println("Task successfully deleted.");
        } else {
            System.out.println("Invalid task number.");
        }

        mainMenu();
    }

    public static void markTask() {
        System.out.println("\nWelcome to My ToDo List");
        System.out.println("\tMark Task:");

        if (taskList.isEmpty()) {
            System.out.println("No tasks available to mark.");
            mainMenu();
            return;
        }

        System.out.print("Enter task number to mark as completed: ");
        int taskNo = scanner.nextInt();

        if (taskNo > 0 && taskNo <= taskList.size()) {
            taskStatus.set(taskNo - 1, true);
            System.out.println("Task successfully marked as completed.");
        } else {
            System.out.println("Invalid task number.");
        }

        mainMenu();
    }

    public static void main(String[] args) {
        mainMenu();
    }
    
}
