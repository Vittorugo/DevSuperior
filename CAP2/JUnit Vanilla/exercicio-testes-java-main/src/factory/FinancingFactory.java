package factory;

import entities.Financing;

public class FinancingFactory {

    public static Financing buildFinancing(Double totalAmount, Double income, Integer months) {
        return new Financing(totalAmount, income, months);
    }
}
