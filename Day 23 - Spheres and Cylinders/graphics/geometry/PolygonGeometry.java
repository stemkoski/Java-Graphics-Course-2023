package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import graphics.math.Vector;

public class PolygonGeometry extends Geometry
{
    // default
    public PolygonGeometry()
    {
        // call this class' contructor with parameter 6.
        this(6);    
    }
    
    public PolygonGeometry(int numberOfSides)
    {
        ArrayList<Vector> positionList = new ArrayList<Vector>();
        ArrayList<Vector> colorList = new ArrayList<Vector>();
                
        double A = 2 * Math.PI / numberOfSides;
        Vector CA = new Vector(1,0,0);
        Vector CB = new Vector(0,1,0);
        Vector CC = new Vector(0,0,1);
        
        for (int i = 0; i < numberOfSides; i++)
        {
            Vector PA = new Vector(0,0,0);
            Vector PB = new Vector( Math.cos(i*A), Math.sin(i*A), 0 );
            Vector PC = new Vector( Math.cos((i+1)*A), Math.sin((i+1)*A), 0 );
            positionList.add(PA);
            positionList.add(PB);
            positionList.add(PC);
            
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