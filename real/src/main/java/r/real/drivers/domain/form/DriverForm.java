package r.real.drivers.domain.form;

import r.real.common.domain.valueObjects.Currency;

import java.math.BigDecimal;

public class DriverForm {
    public String name;
    public String teamName;
    public String nationality;
    public Integer racingNumber;
    public BigDecimal salary;
    public Currency salaryCurrency;
    public Integer championshipPoints;
}