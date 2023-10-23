package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import graphics.math.Vector;

public class AntiprismGeometry extends Geometry
{
    // start with hexagonal antiprism,
    //  then generalize to n-sided polygon antiprism
    public AntiprismGeometry(int numOfSides)
    {
        // calculate vertices
        
        ArrayList<Vector> topVertices = new ArrayList<Vector>();
        ArrayList<Vector> bottomVertices = new ArrayList<Vector>();
        
        double baseAngle = 2 * Math.PI / numOfSides;
        
        for (int i = 0; i < numOfSides; i++)
        {
            // top vertices
            Vector tv = new Vector( Math.cos(i*baseAngle), 
                                    1, 
                                    Math.sin(i*baseAngle) );
            topVertices.add( tv );
                                    
            // bottom vertices are offset by half the baseAngle
            Vector bv = new Vector( Math.cos(i*baseAngle + baseAngle/2), 
                                    -1, 
                                    Math.sin(i*baseAngle + baseAngle/2) );
            bottomVertices.add( bv );
        }
        
        // group into triangles
        
        ArrayList<Vector> triangleVertices = new ArrayList<Vector>();
        ArrayList<Vector> vertexColors = new ArrayList<Vector>();
        
        Vector topPolygonCenter = new Vector(0,1,0);
        
        Vector red = new Vector(1,0,0);
        Vector green = new Vector(0,1,0);
        Vector blue = new Vector(0,0,1);
        
        // -- top polygon
        for (int i = 0; i < numOfSides; i++)
        {
            triangleVertices.add( topPolygonCenter );
            triangleVertices.add( topVertices.get(i) );
            // the mod (%) turns numOfSides into 0, needed for last iteration of for loop.
            triangleVertices.add( topVertices.get((i+1)%numOfSides) );
            
            vertexColors.add( red );
            vertexColors.add( green );
            vertexColors.add( blue );
        }
        
        Vector bottomPolygonCenter = new Vector(0,-1,0);
        // -- bottom polygon
        for (int i = 0; i < numOfSides; i++)
        {
            triangleVertices.add( bottomPolygonCenter );
            triangleVertices.add( bottomVertices.get(i) );
            // the mod (%) turns numOfSides into 0, needed for last iteration of for loop.
            triangleVertices.add( bottomVertices.get((i+1)%numOfSides) );
            
            vertexColors.add( red );
            vertexColors.add( green );
            vertexColors.add( blue );
        }
        
        // -- side triangles
        //  loop over number of sides of polygon, generate in pairs
        Vector magenta = new Vector(1,0,1);
        Vector yellow  = new Vector(1,1,0);
        Vector cyan    = new Vector(0,1,1);
        
        for (int i = 0; i < numOfSides; i++)
        {
            triangleVertices.add( topVertices.get(i) );
            triangleVertices.add( topVertices.get((i+1)%numOfSides) );
            triangleVertices.add( bottomVertices.get(i) );
            
            triangleVertices.add( bottomVertices.get(i) );
            triangleVertices.add( bottomVertices.get((i+1)%numOfSides) );
            triangleVertices.add( topVertices.get((i+1)%numOfSides) );
            
            vertexColors.add( magenta );
            vertexColors.add( yellow );
            vertexColors.add( cyan );
            
            vertexColors.add( magenta );
            vertexColors.add( yellow );
            vertexColors.add( cyan );
        }
        
        
        // create attributes
        float[] positionData = Vector.flattenList(triangleVertices);
        float[] colorData    = Vector.flattenList(vertexColors);
        
        addAttribute("vec3", "vertexPosition", positionData);
        addAttribute("vec3", "vertexColor", colorData);
        
        // set vertex count: 2 * 3 * num sides of polygon (top and bottom)
        //                   + 2 * 3 * num sides of polygon (side triangles)
        vertexCount = 12 * numOfSides;
    }
}