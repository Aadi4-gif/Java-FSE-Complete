CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id   IN NUMBER,
        p_customer_name IN VARCHAR2,
        p_dob           IN DATE,
        p_balance       IN NUMBER
    );
    
    PROCEDURE UpdateCustomerDetails(
        p_customer_id   IN NUMBER,
        p_new_name      IN VARCHAR2
    );
    
    FUNCTION GetCustomerBalance(
        p_customer_id   IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(
        p_customer_id   IN NUMBER,
        p_customer_name IN VARCHAR2,
        p_dob           IN DATE,
        p_balance       IN NUMBER
    ) IS
    BEGIN
        INSERT INTO customers (customer_id, customer_name, date_of_birth, balance, IsVIP, LastModified)
        VALUES (p_customer_id, p_customer_name, p_dob, p_balance, 'FALSE', SYSDATE);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_customer_id   IN NUMBER,
        p_new_name      IN VARCHAR2
    ) IS
    BEGIN
        UPDATE customers
        SET customer_name = p_new_name
        WHERE customer_id = p_customer_id;
        
        IF SQL%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('Warning: No customer found with ID ' || p_customer_id);
        ELSE
            COMMIT;
        END IF;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_customer_id   IN NUMBER
    ) RETURN NUMBER IS
        v_balance NUMBER := 0;
    BEGIN
        SELECT balance INTO v_balance
        FROM customers
        WHERE customer_id = p_customer_id;
        
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;
END CustomerManagement;
/