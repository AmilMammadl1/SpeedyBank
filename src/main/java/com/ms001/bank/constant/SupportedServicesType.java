package com.ms001.bank.constant;
public enum SupportedServicesType {
    CASH_WITHDRAWAL("Cash Withdrawal"),
    CASH_DEPOSIT("Cash Deposit"),
    BALANCE_INQUIRY("Balance Inquiry"),
    ACCOUNT_TRANSFER("Account Transfer"),
    MINI_STATEMENT("Mini Statement"),
    PIN_CHANGE("PIN Change"),
    BILL_PAYMENTS("Bill Payments"),
    MOBILE_RECHARGE("Mobile Recharge"),
    CHEQUE_DEPOSIT("Cheque Deposit"),
    CARDLESS_TRANSACTIONS("Cardless Transactions"),
    CURRENCY_EXCHANGE("Currency Exchange"),
    RECEIPT_PRINTING("Receipt Printing"),
    LANGUAGE_SELECTION("Language Selection"),
    ACCESSIBILITY_FEATURES("Accessibility Features"),
    EMERGENCY_CASH("Emergency Cash");

    private final String description;

    SupportedServicesType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

