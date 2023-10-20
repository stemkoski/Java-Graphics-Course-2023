package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import graphics.math.Vector;

public class SGeometry extends Geometry
{
    public SGeometry()
    {
        Vector P0 = new Vector(0.5, 1, 0);
        Vector P1 = new Vector(0.5, 0.6, 0);
        Vector P2 = new Vector(0.5, 0.3, 0);
        Vector P3 = new Vector(0.5, -1, 0);
        Vector P4 = new Vector(0, 0.6, 0);
        Vector P5 = new Vector(0, 0.3, 0);
        Vector P6 = new Vector(0, -0.3, 0);
        Vector P7 = new Vector(0, -0.6, 0);
        Vector P8 = new Vector(-0.5, 1, 0);
        Vector P9 = new Vector(-0.5, -0.3, 0);
        Vector P10 = new Vector(-0.5, -0.6, 0);
        Vector P11 = new Vector(-0.5, -1, 0);
        
        Vector C0 = new Vector(1, 0, 0);
        Vector C8 = new Vector(1, 0, 0);
        Vector C1 = new Vector(1, 0.5, 0);
        Vector C4 = new Vector(1, 0.5, 0);
        Vector C5 = new Vector(1, 1, 0);
        Vector C2 = new Vector(1, 1, 0);
        Vector C6 = new Vector(0, 0.6, 0);
        Vector C9 = new Vector(0, 0.6, 0);
        Vector C10 = new Vector(0, 0, 1);
        Vector C7  = new Vector(0, 0, 1);
        Vector C11 = new Vector(0.5, 0, 1);
        Vector C3  = new Vector(0.5, 0, 1);
        
        List positionList = Arrays.asList(P0,P1,P4, P0,P4,P8, P4,P8,P9, P4,P5,P9,
          P2,P5,P9, P2,P6,P9, P2,P3,P6, P3,P6,P7, P3,P7,P11, P7,P10,P11);
        
        float[] positionData = Vector.flattenList(positionList);
        
        addAttribute("vec3", "vertexPosition", positionData);
        
        List colorList = Arrays.asList(C0,C1,C4, C0,C4,C8, C4,C8,C9, C4,C5,C9,
          C2,C5,C9, C2,C6,C9, C2,C3,C6, C3,C6,C7, C3,C7,C11, C7,C10,C11);
        
        float[] colorData = Vector.flattenList(colorList);
        
        addAttribute("vec3", "vertexColor", colorData);
        
        vertexCount = 30;
        
    }
    
    
}