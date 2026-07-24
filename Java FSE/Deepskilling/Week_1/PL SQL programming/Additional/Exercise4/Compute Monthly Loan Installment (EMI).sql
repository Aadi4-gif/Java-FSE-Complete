CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount IN NUMBER,
    p_annual_rate IN NUMBER,
    p_years       IN NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
    v_installment  NUMBER;
BEGIN
    IF p_loan_amount <= 0 OR p_annual_rate < 0 OR p_years <= 0 THEN
        RETURN 0;
    END IF;

    IF p_annual_rate = 0 THEN
        RETURN p_loan_amount / (p_years * 12);
    END IF;

    v_monthly_rate := (p_annual_rate / 100) / 12;
    v_total_months := p_years * 12;

    v_installment := p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_total_months)) / 
                     (POWER(1 + v_monthly_rate, v_total_months) - 1);

    RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/