package com.ms001.bank.constant;
public enum LoanTerm {
    SHORT_TERM(6),
    MEDIUM_TERM(12),
    LONG_TERM(36);

    private final int months;

    LoanTerm(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }
}
