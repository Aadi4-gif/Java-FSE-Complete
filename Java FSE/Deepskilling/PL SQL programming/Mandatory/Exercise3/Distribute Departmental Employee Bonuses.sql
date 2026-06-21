CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department  IN VARCHAR2,
    p_bonus_pct   IN NUMBER
) IS
BEGIN
    IF p_bonus_pct < 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Bonus percentage cannot be negative.');
    END IF;

    UPDATE employees
    SET salary = salary + (salary * (p_bonus_pct / 100))
    WHERE department = p_department;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus applied successfully to department: ' || p_department);

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonuses: ' || SQLERRM);
END;
/