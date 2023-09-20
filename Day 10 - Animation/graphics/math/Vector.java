package graphics.math;
import java.util.List;
import java.awt.Color;

public class Vector
{
    public double[] values;

    // initialize zero vector with given size
    public Vector(int size)
    {
        values = new double[size];
    }

    // initialize with contents
    public Vector(double... v)
    {
        values = new double[v.length];
        for (int i = 0; i < v.length; i++)
            values[i] = v[i];
    }

    public String toString()
    {
        String s = "[";
        for (int i = 0; i < values.length; i++)
            s += String.format("%6.2f", values[i]);
        s += "]";
        return s;
    }

    // add values (v1, v2, v3, ...) to the components of this vector
    public void addValues(double... v)
    {
        for (int i = 0; i < v.length; i++)
            values[i] += v[i];
    }

    // add components of other vector to this vector
    public void addVector(Vector v)
    {
        for (int i = 0; i < v.values.length; i++)
            values[i] += v.values[i];
    }

    // multiply components of this vector by a scalar value s
    public void multiplyScalar(double s)
    {
        for (int i = 0; i < values.length; i++)
            values[i] *= s;
    }

    // convert a list of vectors to a list of floats (for GPU processing)
    public static float[] flattenList(List<Vector> vecList)
    { 
        int listSize = vecList.size();
        int vecSize  = vecList.get(0).values.length;
        float[] flattened = new float[listSize * vecSize];
        for (int vecNumber = 0; vecNumber < listSize; vecNumber++)
        {  
            Vector v = vecList.get(vecNumber);
            for (int i = 0; i < vecSize; i++)
                flattened[vecNumber * vecSize + i] = (float)v.values[i];
        }
        return flattened;
    }

    // if this vector contains RGB data, convert it to a vector containing HSV data
    //   for simpler calculations (like hue adjustment)
    public Vector toHSV()
    {
        int r = (int)(255 * values[0]);
        int g = (int)(255 * values[1]);
        int b = (int)(255 * values[2]);

        float[] hsv = new float[3];
        Color.RGBtoHSB(r,g,b,hsv);
        
        return new Vector( hsv[0] / 255.0, hsv[1] / 255.0, hsv[2] / 255.0 );
    }
    
    // after performing HSV vector calculations, typically need to convert back to an RGB vector for
    //   use by GPU
    public Vector toRGB()
    {
        int h = (int)(255 * values[0]);
        int s = (int)(255 * values[1]);
        int v = (int)(255 * values[2]);
    
        int colorInt = Color.HSBtoRGB(h, s, v);
        Color color = new Color(colorInt);
        
        return new Vector( color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0 );
        
    }
}