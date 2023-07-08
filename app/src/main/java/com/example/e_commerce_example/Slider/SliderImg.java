package com.example.e_commerce_example.Slider;

public class SliderImg
{
    private String slidername;

    private String imagename;

    public String getSlidername ()
    {
        return slidername;
    }

    public void setSlidername (String slidername)
    {
        this.slidername = slidername;
    }

    public String getImagename ()
    {
        return imagename;
    }

    public void setImagename (String imagename)
    {
        this.imagename = imagename;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [slidername = "+slidername+", imagename = "+imagename+"]";
    }
}