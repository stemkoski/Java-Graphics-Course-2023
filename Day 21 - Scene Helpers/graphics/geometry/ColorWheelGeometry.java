package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import graphics.math.Vector;

public class ColorWheelGeometry extends Geometry
{
    // default
    public ColorWheelGeometry()
    {
        // call this class' contructor with parameter 6.
        this(6);    
    }
    
    public ColorWheelGeometry(int numberOfSides)
    {
        ArrayList<Vector> positionList = new ArrayList<Vector>();
        ArrayList<Vector> colorList = new ArrayList<Vector>();
                
        double A = 2 * Math.PI / numberOfSides;
        
        
        for (int i = 0; i < numberOfSides; i++)
        {
            Vector PA = new Vector(0,0,0);
            Vector PB = new Vector( Math.cos(i*A), Math.sin(i*A), 0 );
            Vector PC = new Vector( Math.cos((i+1)*A), Math.sin((i+1)*A), 0 );
            positionList.add(PA);
            positionList.add(PB);
            positionList.add(PC);
            
            Vector CA = new Vector(1,1,1);
            
            // hue should be between 0 and 1
            double baseColorAngle = A / (2 * Math.PI);
            double hueB = baseColorAngle * i;
            double hueC = baseColorAngle * (i + 1);
            // vectors with HSV data need to be converted to RGB data
            Vector CB = new Vector( hueB, 1, 1 ).toRGB();
            Vector CC = new Vector( hueC, 1, 1 ).toRGB();

            colorList.add(CA);
            colorList.add(CB);
            colorList.add(CC);
        }
        
        float[] positionData = Vector.flattenList(positionList);
        
        addAttribute("vec3", "vertexPosition", positionData);
        
        float[] colorData = Vector.flattenList(colorList);
        
        addAttribute("vec3", "vertexColor", colorData);
        
        vertexCount = numberOfSides * 3;
    }
    
    
    
    
    
    
    
    
}