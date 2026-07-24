CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_emp_id     IN NUMBER,
        p_name       IN VARCHAR2,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER,
        p_department IN VARCHAR2
    );
    
    PROCEDURE UpdateEmployeeDetails(
        p_emp_id     IN NUMBER,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER
    );
    
    FUNCTION CalculateAnnualSalary(
        p_emp_id     IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/
CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_emp_id     IN NUMBER,
        p_name       IN VARCHAR2,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER,
        p_department IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO employees (employee_id, name, position, salary, department)
        VALUES (p_emp_id, p_name, p_position, p_salary, p_department);
        COMMIT;
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_emp_id     IN NUMBER,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER
    ) IS
    BEGIN
        UPDATE employees
        SET position = p_position,
            salary = p_salary
        WHERE employee_id = p_emp_id;
        COMMIT;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_emp_id     IN NUMBER
    ) RETURN NUMBER IS
        v_monthly_salary NUMBER := 0;
    BEGIN
        SELECT salary INTO v_monthly_salary
        FROM employees
        WHERE employee_id = p_emp_id;
        
        RETURN v_monthly_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
    END CalculateAnnualSalary;
END EmployeeManagement;
/