public class Main {
    public static void main(String[] args) {
        System.out.println("=== Task Management System Registry ===\n");

        TaskManagement manager = new TaskManagement();

        manager.addTask(new Task("T001", "Database Migration", "Pending"));
        manager.addTask(new Task("T002", "API Integration", "In Progress"));
        manager.addTask(new Task("T003", "UI Code Review", "Completed"));

        System.out.println("\n--- Current Task List ---");
        manager.traverseTasks();

        System.out.println("\n--- Searching for Task T002 ---");
        Task found = manager.searchTask("T002");
        System.out.println(found != null ? "Found Match -> " + found : "Task not found.");

        System.out.println("\n--- Deleting Task T002 ---");
        manager.deleteTask("T002");

        System.out.println("\n--- Final Task List ---");
        manager.traverseTasks();
    }
}