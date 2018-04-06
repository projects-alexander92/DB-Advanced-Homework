package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "credit_cards")
public class CreditCard extends BillingDetail
{
    @Column(name = "card_tpe")
    private String cardType;
    @Column(name = "expiration_date")
    private Date expirationDate;

    public String getCardType()
    {
        return cardType;
    }

    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationMonth)
    {
        this.expirationDate = expirationMonth;
    }
}
