CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER IS
BEGIN
    IF p_dob IS NULL THEN
        RETURN NULL;
    END IF;
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END CalculateAge;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount IN NUMBER,
    p_annual_rate IN NUMBER,
    p_years       IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
BEGIN
    IF p_loan_amount <= 0 OR p_annual_rate < 0 OR p_years <= 0 THEN
        RETURN 0;
    END IF;
    IF p_annual_rate = 0 THEN
        RETURN p_loan_amount / (p_years * 12);
    END IF;

    v_monthly_rate := (p_annual_rate / 100) / 12;
    v_total_months := p_years * 12;

    RETURN ROUND(p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_total_months)) / 
                 (POWER(1 + v_monthly_rate, v_total_months) - 1), 2);
END CalculateMonthlyInstallment;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN VARCHAR2 IS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'FALSE';
END HasSufficientBalance;
/