DECLARE
    CURSOR c_customers IS
        SELECT customer_id, age, interest_rate 
        FROM customers
        WHERE age > 60;
BEGIN
    FOR r_cust IN c_customers LOOP
        UPDATE customers 
        SET interest_rate = interest_rate - 1
        WHERE customer_id = r_cust.customer_id;
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating interest rates: ' || SQLERRM);
END;
/