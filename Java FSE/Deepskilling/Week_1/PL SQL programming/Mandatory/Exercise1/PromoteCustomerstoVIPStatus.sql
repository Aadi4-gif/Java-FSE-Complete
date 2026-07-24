DECLARE
    CURSOR c_vip_candidates IS
        SELECT customer_id, balance 
        FROM customers
        WHERE balance > 10000;
BEGIN
    FOR r_cust IN c_vip_candidates LOOP
        UPDATE customers
        SET IsVIP = 'TRUE'
        WHERE customer_id = r_cust.customer_id;
    END LOOP;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating VIP status: ' || SQLERRM);
END;
/