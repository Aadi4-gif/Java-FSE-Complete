CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id   IN NUMBER,
    p_customer_name IN VARCHAR2,
    p_balance       IN NUMBER
) IS
BEGIN
    INSERT INTO customers (customer_id, customer_name, balance)
    VALUES (p_customer_id, p_customer_name, p_balance);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer ' || p_customer_name || ' added successfully.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: A customer with ID ' || p_customer_id || ' already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Failed to add customer due to error: ' || SQLERRM);
END;
/