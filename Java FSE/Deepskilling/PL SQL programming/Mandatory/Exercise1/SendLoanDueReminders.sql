DECLARE
    CURSOR c_due_loans IS
        SELECT c.customer_name, l.loan_id, l.end_date
        FROM loans l
        JOIN customers c ON l.customer_id = c.customer_id
        WHERE l.end_date BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR r_loan IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || r_loan.customer_name || 
                             ', your loan (ID: ' || r_loan.loan_id || 
                             ') is due on ' || TO_CHAR(r_loan.end_date, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/