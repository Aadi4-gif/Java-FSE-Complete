CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    IF p_amount <= 0 THEN
        RETURN TRUE;
    END IF;

    SELECT balance INTO v_balance 
    FROM accounts 
    WHERE account_id = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN TRUE;
    END ELSE
        RETURN FALSE;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;
    WHEN OTHERS THEN
        RETURN FALSE;
END HasSufficientBalance;
/