package graphics.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import graphics.math.Vector;
import graphics.math.Surface;

public class SurfaceGeometry extends Geometry
{
	public SurfaceGeometry( Surface.Function function,
		double uStart, double uEnd, int uResolution, 
    	double vStart, double vEnd, int vResolution  )
	{
		Surface surface = new Surface(function);

		Vector[][] positions = surface.getPoints(
			uStart, uEnd, uResolution, vStart, vEnd, vResolution);

		List<Vector> quadColors = Arrays.asList(
			new Vector(1, 0.3, 0.3), new Vector(0.3, 1, 0.3), new Vector(0.3, 0.3, 1),
			new Vector(0.3, 1, 1), new Vector(1, 0.3, 1), new Vector(1, 1, 0.3) );

		ArrayList<Vector> positionList = new ArrayList<Vector>();
		ArrayList<Vector> colorList    = new ArrayList<Vector>();

		for (int uIndex=0; uIndex<uResolution; uIndex++)
		{
			for (int vIndex=0; vIndex<vResolution; vIndex++)
			{
                // position coordinates
                Vector pA = positions[uIndex+0][vIndex+0];
                Vector pB = positions[uIndex+1][vIndex+0];
                Vector pD = positions[uIndex+0][vIndex+1];
                Vector pC = positions[uIndex+1][vIndex+1];
                positionList.addAll( Arrays.asList(pA,pB,pC, pA,pC,pD) );

                colorList.addAll(quadColors);
            }
		}

		float[] positionData = Vector.flattenList(positionList);
		float[] colorData = Vector.flattenList(colorList);
		
		addAttribute("vec3", "vertexPosition", positionData);
        addAttribute("vec3", "vertexColor", colorData);
		vertexCount = uResolution * vResolution * 6;
	}
}
