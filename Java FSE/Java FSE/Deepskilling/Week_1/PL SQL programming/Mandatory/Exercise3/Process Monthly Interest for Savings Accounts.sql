CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
    CURSOR c_savings_accounts IS
        SELECT account_id, balance 
        FROM accounts 
        WHERE account_type = 'Savings'
        FOR UPDATE;
BEGIN
    FOR r_acc IN c_savings_accounts LOOP
        UPDATE accounts
        SET balance = balance + (balance * 0.01)
        WHERE account_id = r_acc.account_id;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processed successfully for all savings accounts.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing monthly interest: ' || SQLERRM);
END;
/