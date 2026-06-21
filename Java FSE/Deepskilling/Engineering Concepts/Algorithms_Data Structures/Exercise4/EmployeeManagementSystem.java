class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "ID: " + employeeId + " | Name: " + name + " | Position: " + position + " | Salary: $" + salary;
    }
}

class EmployeeManagement {
    private Employee[] records;
    private int size;

    public EmployeeManagement(int capacity) {
        this.records = new Employee[capacity];
        this.size = 0;
    }

    public void addEmployee(Employee emp) {
        if (size >= records.length) {
            System.out.println("Error: Cannot add employee. Management array is completely full.");
            return;
        }
        records[size] = emp;
        size++;
        System.out.println("Added: " + emp.getName());
    }

    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (records[i].getEmployeeId().equalsIgnoreCase(employeeId)) {
                return records[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        if (size == 0) {
            System.out.println("No employee records found.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("  " + records[i]);
        }
    }

    public void deleteEmployee(String employeeId) {
        int targetIndex = -1;
        for (int i = 0; i < size; i++) {
            if (records[i].getEmployeeId().equalsIgnoreCase(employeeId)) {
                targetIndex = i;
                break;
            }
        }

        if (targetIndex == -1) {
            System.out.println("Error: Employee ID " + employeeId + " not found.");
            return;
        }

        String removedName = records[targetIndex].getName();
        
      
        for (int i = targetIndex; i < size - 1; i++) {
            records[i] = records[i + 1];
        }
        
        records[size - 1] = null; 
        size--;
        System.out.println("Deleted: " + removedName);
    }
}
