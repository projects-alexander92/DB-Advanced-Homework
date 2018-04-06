package app.enitties.jsonDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExportPhotographerToJson
{
    @Expose
    @SerializedName("FirstName")
    private String firstName;

    @Expose
    @SerializedName("LastName")
    private String lastName;

    @Expose
    @SerializedName("phone")
    private String phone;

    public ExportPhotographerToJson()
    {
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }
}
