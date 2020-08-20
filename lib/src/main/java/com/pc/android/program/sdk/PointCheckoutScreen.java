package com.pc.android.program.sdk;

public enum PointCheckoutScreen {
    MAIN_SCREEN("MainScreen"),
    MERCHANT_SCREEN("MerchantScreen"),
    TRANSACTION_SCREEN("TransactionScreen"),
    PROMOTION_SCREEN("PromotionScreen"),
    QUICK_PAY_SCREEN("QuickPayScreen");

    private String route;

    PointCheckoutScreen(String route) {
        this.route = route;
    }

    public String getRoute() {
        return this.route;
    }
}