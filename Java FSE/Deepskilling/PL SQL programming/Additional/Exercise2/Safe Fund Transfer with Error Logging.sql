CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) IS
    v_balance NUMBER;
    insufficient_funds EXCEPTION;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be greater than zero.');
    END IF;

    SELECT balance INTO v_balance 
    FROM accounts 
    WHERE account_id = p_from_account 
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE insufficient_funds;
    END IF;

    UPDATE accounts 
    SET balance = balance - p_amount 
    WHERE account_id = p_from_account;

    UPDATE accounts 
    SET balance = balance + p_amount 
    WHERE account_id = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer successful.');

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Insufficient funds in account ' || p_from_account);
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transaction failed due to system error: ' || SQLERRM);
END;
/