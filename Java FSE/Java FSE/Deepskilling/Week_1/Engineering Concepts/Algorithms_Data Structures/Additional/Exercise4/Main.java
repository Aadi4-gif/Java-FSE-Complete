public class Main {
    public static void main(String[] args) {
        System.out.println("=== Corporate Employee Management Registry ===\n");

        EmployeeManagement system = new EmployeeManagement(5);

        system.addEmployee(new Employee("E001", "Alice Smith", "Software Engineer", 95000));
        system.addEmployee(new Employee("E002", "Bob Jones", "Project Manager", 105000));
        system.addEmployee(new Employee("E003", "Charlie Brown", "QA Analyst", 75000));

        System.out.println("\n--- Current Employee Directory ---");
        system.traverseEmployees();

        System.out.println("\n--- Searching for Employee E002 ---");
        Employee found = system.searchEmployee("E002");
        System.out.println(found != null ? "Found Match -> " + found : "Employee not found.");

        System.out.println("\n--- Removing Employee E002 ---");
        system.deleteEmployee("E002");

        System.out.println("\n--- Final Employee Directory ---");
        system.traverseEmployees();
    }
}