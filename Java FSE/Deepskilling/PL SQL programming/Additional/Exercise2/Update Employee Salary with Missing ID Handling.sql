CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_emp_id     IN NUMBER,
    p_percentage IN NUMBER
) IS
    v_dummy NUMBER;
BEGIN
    SELECT 1 INTO v_dummy 
    FROM employees 
    WHERE employee_id = p_emp_id;

    UPDATE employees 
    SET salary = salary + (salary * (p_percentage / 100))
    WHERE employee_id = p_emp_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated successfully for Employee ID: ' || p_emp_id);

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_emp_id || ' does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating salary: ' || SQLERRM);
END;
/