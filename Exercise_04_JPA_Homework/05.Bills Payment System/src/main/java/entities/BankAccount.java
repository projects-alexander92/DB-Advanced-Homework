package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "bank_account")
public class BankAccount extends BillingDetail
{
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "swift_core")
    private String swiftCode;

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getSwiftCode()
    {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode)
    {
        this.swiftCode = swiftCode;
    }
}
