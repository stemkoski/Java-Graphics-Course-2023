package graphics.material;

import static org.lwjgl.opengl.GL40.*;

public class SurfaceMaterial extends BasicMaterial
{
    public SurfaceMaterial()
    {
        drawStyle = GL_TRIANGLES;
        
        // make all polygons double-sided
        glDisable(GL_CULL_FACE);
        
        // use filled-in triangles; 
        //    alternative is wireframe: GL_LINE
        glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
    }
}