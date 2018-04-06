package app.enitties.jsonDto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CameraFromJsonDto
{
    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private boolean isFullFrame;

    @Expose
    private int minISO = 100;

    @Expose
    private int maxISO;

    @Expose
    @SerializedName("MaxShutterSpeed")
    private int maxShutterSpeed;

    @Expose
    private String maxVideoResolution;

    @Expose
    private int maxFrameRate;

    @Expose
    private String type;

    public CameraFromJsonDto()
    {
    }

    public String getMake()
    {
        return this.make;
    }

    public void setMake(String make)
    {
        this.make = make;
    }

    public String getModel()
    {
        return this.model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public boolean isFullFrame()
    {
        return this.isFullFrame;
    }

    public void setFullFrame(boolean fullFrame)
    {
        isFullFrame = fullFrame;
    }

    public int getMinISO()
    {
        return this.minISO;
    }

    public void setMinISO(int minISO)
    {
        this.minISO = minISO;
    }

    public int getMaxISO()
    {
        return this.maxISO;
    }

    public void setMaxISO(int maxISO)
    {
        this.maxISO = maxISO;
    }

    public int getMaxShutterSpeed()
    {
        return this.maxShutterSpeed;
    }

    public void setMaxShutterSpeed(int maxShutterSpeed)
    {
        this.maxShutterSpeed = maxShutterSpeed;
    }

    public String getMaxVideoResolution()
    {
        return this.maxVideoResolution;
    }

    public void setMaxVideoResolution(String maxVideoResolution)
    {
        this.maxVideoResolution = maxVideoResolution;
    }

    public int getMaxFrameRate()
    {
        return this.maxFrameRate;
    }

    public void setMaxFrameRate(int maxFrameRate)
    {
        this.maxFrameRate = maxFrameRate;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
