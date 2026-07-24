CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON transactions
FOR EACH ROW
DECLARE
    v_current_balance NUMBER;
BEGIN
    IF :NEW.amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Transaction amount must be strictly greater than zero.');
    END IF;

    IF :NEW.transaction_type = 'Withdrawal' THEN
        SELECT balance INTO v_current_balance
        FROM accounts
        WHERE account_id = :NEW.account_id
        FOR UPDATE;

        IF v_current_balance < :NEW.amount THEN
            RAISE_APPLICATION_ERROR(-20011, 'Transaction Aborted: Insufficient balance for withdrawal.');
        END IF;
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20012, 'Transaction Aborted: Target account ID does not exist.');
END;
/