import graphics.core.*;
import graphics.math.*;

import static org.lwjgl.opengl.GL40.*;

import java.util.ArrayList;

public class Test_Animate1 extends Base
{
    public int programRef;
    public int vaoRef;

    public Uniform<Vector> 
    translation, baseColor;

    public double dx, dy;

    public static void main(String[] args)
    {
        Base test = new Test_Animate1();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {       
        // load code, send to GPU, and compile; store program reference
        programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/uniform-translation.vert", 
            "graphics/shaders/uniform-color.frag" );

        // set up vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = {
                0.0f,  0.2f, 0.0f,
                0.2f, -0.2f, 0.0f,
                -0.2f, -0.2f, 0.0f  };
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        positionAttribute.associateVariable( programRef, "position" );

        // set up uniforms
        translation = new Uniform<Vector>("vec3", new Vector(0.0f, 0.0f, 0.0f) );
        translation.locateVariable( programRef, "translation" );

        Vector c = new Vector(1,0,0);
        
        baseColor = new Uniform<Vector>("vec3", c );
        baseColor.locateVariable( programRef, "baseColor" );

        dx = 0.0;
        dy = 0.0;

        // render settings (optional)
        glPointSize(20);
        glLineWidth(10);
    }

    

    public void update()
    {
        glClear(GL_COLOR_BUFFER_BIT);

        // select program to use when rendering
        glUseProgram( programRef );
        glBindVertexArray(vaoRef);

        // constant motion
        dx = 0.01;
        // dy = 0.015;

        // random motion
        // dx = 0.01 * (Math.random() - 0.5);
        // dy = 0.01 * (Math.random() - 0.5);

        // semi-random motion
        /*
        double r = Math.random();
        double speed = 0.005;
        if (0.00 < r && r < 0.05)
            dx = -2 * speed;
        if (0.05 < r && r < 0.10)
            dx = -1 * speed;
        if (0.10 < r && r < 0.15)
            dx = 1 * speed;
        if (0.15 < r && r < 0.20)
            dy = 2 * speed;
        if (0.20 < r && r < 0.25)
            dy = -2 * speed;
        if (0.25 < r && r < 0.30)
            dy = -1 * speed;
        if (0.30 < r && r < 0.35)
            dy = 1 * speed;
        if (0.35 < r && r < 0.40)
            dy = 2 * speed;
        */
       
        // update the uniforms
        translation.data.values[0] += dx;
        translation.data.values[1] += dy;

        // wrap around the screen
        if (translation.data.values[0] > 1.2)
            translation.data.values[0] = -1.2;

        if (translation.data.values[0] < -1.2)
            translation.data.values[0] = 1.2;

        if (translation.data.values[1] > 1.2)
            translation.data.values[1] = -1.2;

        if (translation.data.values[1] < -1.2)
            translation.data.values[1] = 1.2;

        Vector hsv = baseColor.data.toHSV();
        
        hsv.values[0] += 0.005;
        if (hsv.values[0] > 1)
            hsv.values[0] = 0; 
        
        baseColor.data = hsv.toRGB();
        
        // draw the triangle
        translation.uploadData();
        baseColor.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );

    }
    
}
