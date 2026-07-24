CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        log_id,
        transaction_id,
        account_id,
        amount,
        transaction_type,
        action_timestamp
    ) VALUES (
        audit_log_seq.NEXTVAL,
        :NEW.transaction_id,
        :NEW.account_id,
        :NEW.amount,
        :NEW.transaction_type,
        SYSDATE
    );
END;
/