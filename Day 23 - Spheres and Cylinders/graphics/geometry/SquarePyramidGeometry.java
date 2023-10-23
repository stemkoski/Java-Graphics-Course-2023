package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import graphics.math.Vector;

public class SquarePyramidGeometry extends Geometry
{
    public SquarePyramidGeometry()
    {
        Vector P0 = new Vector(0, 0.5, 0);
        Vector P1 = new Vector(-0.5, -0.5, 0.5);
        Vector P2 = new Vector(0.5, -0.5, 0.5);
        Vector P3 = new Vector(0.5, -0.5, -0.5);
        Vector P4 = new Vector(-0.5, -0.5, -0.5);
        
        Vector C0 = new Vector(1,0,0);
        Vector C1 = new Vector(1,0.5,0);
        Vector C2 = new Vector(1,1,0);
        Vector C3 = new Vector(0,1,0);
        Vector C4 = new Vector(1,1,1);
        
        List positionList = Arrays.asList(P0,P4,P3, P0,P2,P3, P0,P1,P2, P0,P1,P4,
           P1,P2,P4, P1,P2,P3);
           
        float[] positionData = Vector.flattenList(positionList);
        
        addAttribute("vec3", "vertexPosition", positionData);
        
        List colorList = Arrays.asList(C0,C4,C3, C0,C2,C3, C0,C1,C2, C0,C1,C4,
           C1,C2,C4, C1,C2,C3);
           
        float[] colorData = Vector.flattenList(colorList);
        
        addAttribute("vec3", "vertexColor", colorData);

        vertexCount = 18;
    }
    
}