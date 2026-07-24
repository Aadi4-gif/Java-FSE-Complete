CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_init_balance IN NUMBER
    );
    
    PROCEDURE CloseAccount(
        p_account_id   IN NUMBER
    );
    
    FUNCTION GetTotalBalance(
        p_customer_id  IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id   IN NUMBER,
        p_customer_id  IN NUMBER,
        p_account_type IN VARCHAR2,
        p_init_balance IN NUMBER
    ) IS
    BEGIN
        INSERT INTO accounts (account_id, customer_id, account_type, balance)
        VALUES (p_account_id, p_customer_id, p_account_type, p_init_balance);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id   IN NUMBER
    ) IS
    BEGIN
        DELETE FROM accounts
        WHERE account_id = p_account_id;
        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_customer_id  IN NUMBER
    ) RETURN NUMBER IS
        v_total_balance NUMBER := 0;
    BEGIN
        SELECT SUM(balance) INTO v_total_balance
        FROM accounts
        WHERE customer_id = p_customer_id;
        
        RETURN NVL(v_total_balance, 0);
    END GetTotalBalance;
END AccountOperations;
/