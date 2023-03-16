package test;

import entities.Financing;
import factory.FinancingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTest {

    @Test
    public void buildObjectWhenDataIsCorrect() {

        Double totalAmount = 10000.00;
        Double income = 2000.00;
        Integer months = 12;

        Financing financingTest = FinancingFactory.buildFinancing(totalAmount, income, months);
        Assertions.assertEquals(totalAmount, financingTest.getTotalAmount());
        Assertions.assertEquals(income, financingTest.getIncome());
        Assertions.assertEquals(months, financingTest.getMonths());
    }

    @Test
    public void objectNotConstructedWhenDataIsIncorrect() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            FinancingFactory.buildFinancing(200000.00, 5000.00, 20);
        });
    }

    @Test
    public void setTotalAmountWhenValuesIsCorrect() {

        Double newValue = 15000.00;
        Financing financing = FinancingFactory.buildFinancing(10000.00, 2000.00, 12);

        financing.setTotalAmount(newValue);

        Assertions.assertEquals(newValue, financing.getTotalAmount());
    }

    @Test
    public void doNotSetTotalAmountWhenValueIsIncorrect() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            Double newValue = 25000.00;
            Financing financing = FinancingFactory.buildFinancing(10000.00, 2000.00, 12);

            financing.setTotalAmount(newValue);
        });
    }

    @Test
    public void setIncomeWhenValueIsCorrect() {

        Double newIncome = 3000.00;
        Financing financing = FinancingFactory.buildFinancing(10000.00, 2000.00, 12);

        financing.setIncome(newIncome);

        Assertions.assertEquals(newIncome, financing.getIncome());
    }

    @Test
    public void doNotSetIncomeWhenValueIsIncorrect() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            Financing financing = FinancingFactory.buildFinancing(10000.00, 2000.00, 12);

            financing.setIncome(1000.00);
        });
    }

    @Test
    public void setMonthWhenValueIsCorrect() {
        Integer newMonth = 24;
        Financing financing = FinancingFactory.buildFinancing(10000.00, 20000.00,12);

        financing.setMonths(newMonth);

        Assertions.assertEquals(newMonth, financing.getMonths());
    }

    @Test
    public void doNotSetMonthWhenValueIsIncorrect() {
        Assertions.assertThrows( IllegalArgumentException.class, () -> {
            Financing financing = FinancingFactory.buildFinancing(10000.00, 1000.00, 12);
            financing.setMonths(5);
        });
    }

    @Test
    public void entry() {
        Double expected = 2000.00;
        Financing financing = FinancingFactory.buildFinancing(10000.00, 2000.00, 12);

        Assertions.assertEquals(expected, financing.entry());
    }

    @Test
    public void quotaIsCorrect() {
        Double expected = 1000.00;
        Financing financing = FinancingFactory.buildFinancing( 10000.00, 2000.00, 8);

        Assertions.assertEquals(expected, financing.quota());
    }
}
