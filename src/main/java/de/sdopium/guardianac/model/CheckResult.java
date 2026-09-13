package de.sdopium.guardianac.model;

public record CheckResult(boolean failed, double weight, String detail) {
    public static CheckResult pass() {
        return new CheckResult(false, 0, "");
    }

    public static CheckResult fail(double weight, String detail) {
        return new CheckResult(true, weight, detail);
    }
}
