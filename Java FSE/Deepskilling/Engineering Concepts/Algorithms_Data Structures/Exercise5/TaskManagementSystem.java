class Task {
    private String taskId;
    private String taskName;
    private String status;

    public Task(String taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Task ID: " + taskId + " | Name: " + taskName + " | Status: " + status;
    }
}

class TaskManagement {
    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    private Node head = null;

    // Add a task to the end of the list
    public void addTask(Task newTask) {
        Node newNode = new Node(newTask);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Added Task: " + newTask.getTaskName());
    }

    // Search for a task by ID
    public Task searchTask(String taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId().equalsIgnoreCase(taskId)) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        if (head == null) {
            System.out.println("The task list is currently empty.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.task);
            current = current.next;
        }
    }

    // Delete a task by ID
    public void deleteTask(String taskId) {
        if (head == null) {
            System.out.println("Error: List is empty. Cannot delete.");
            return;
        }

        // Case 1: The head node itself needs to be removed
        if (head.task.getTaskId().equalsIgnoreCase(taskId)) {
            System.out.println("Deleted Task: " + head.task.getTaskName());
            head = head.next;
            return;
        }

        // Case 2: Search for the node to delete while keeping track of the previous node
        Node current = head;
        Node previous = null;

        while (current != null && !current.task.getTaskId().equalsIgnoreCase(taskId)) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Error: Task ID " + taskId + " not found.");
            return;
        }

        // Unlink the node from the chain
        System.out.println("Deleted Task: " + current.task.getTaskName());
        previous.next = current.next;
    }
}