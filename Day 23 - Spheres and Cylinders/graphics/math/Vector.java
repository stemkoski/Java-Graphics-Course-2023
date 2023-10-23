package graphics.math;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

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

    // resize values array (larger or smaller)
    public void resize(int newSize)
    {
        double[] newValues = new double[newSize];
        int smaller = Math.min(values.length, newValues.length);
        for (int i = 0; i < smaller; i++)
            newValues[i] = values[i];
        values = newValues;
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


    public static double dot(Vector v, Vector w)
    {
        double c = 0;
        for (int i = 0; i < v.values.length; i++)
            c += v.values[i] * w.values[i];
        return c;
    }
    
    // if this vector contains RGB data, convert it to a vector containing HSV data
    //   for simpler calculations (like hue adjustment)
    public Vector toHSV()
    {
        int r = (int)(255 * values[0]);
        int g = (int)(255 * values[1]);
        int b = (int)(255 * values[2]);

        float[] hsv = new float[3];
        hsv = Color.RGBtoHSB(r,g,b,hsv);
        
        return new Vector( hsv[0], hsv[1], hsv[2] );
    }
    
    // after performing HSV vector calculations, typically need to convert back to an RGB vector for
    //   use by GPU
    public Vector toRGB()
    {
        float h = (float)values[0];
        float s = (float)values[1];
        float v = (float)values[2];
    
        int colorInt = Color.HSBtoRGB(h, s, v);
        Color color = new Color(colorInt);
        
        return new Vector( color.getRed() / 255.0, color.getGreen() / 255.0, color.getBlue() / 255.0 );
        
    }

    public static List<Vector> unflattenList(float[] flatArray, int vecSize)
    {
        List<Vector> vecList = new ArrayList<Vector>();
        double[] tempData = new double[vecSize];
        for (int i = 0; i < flatArray.length; i += vecSize)
        {
            for (int j = 0; j < vecSize; j++)
                tempData[j] = flatArray[i + j];

            vecList.add( new Vector(tempData) );
        }
        return vecList;
    }
}