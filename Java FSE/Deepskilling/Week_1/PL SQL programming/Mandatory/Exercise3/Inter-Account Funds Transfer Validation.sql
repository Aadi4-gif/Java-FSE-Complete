CREATE OR REPLACE PROCEDURE TransferFunds (
    p_source_acc IN NUMBER,
    p_dest_acc   IN NUMBER,
    p_amount     IN NUMBER
) IS
    v_source_balance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be greater than zero.');
    END IF;

    SELECT balance INTO v_source_balance 
    FROM accounts 
    WHERE account_id = p_source_acc 
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE accounts 
    SET balance = balance - p_amount 
    WHERE account_id = p_source_acc;

    UPDATE accounts 
    SET balance = balance + p_amount 
    WHERE account_id = p_dest_acc;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Successfully transferred $' || p_amount || ' from Account ' || p_source_acc || ' to Account ' || p_dest_acc);

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer Aborted: Account ' || p_source_acc || ' has insufficient funds.');
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer Aborted: Invalid source or destination account ID.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer Aborted: Unexpected processing error: ' || SQLERRM);
END;
/