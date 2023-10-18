import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL40.*;

import graphics.core.*;
import graphics.math.*;

/* will use for testing Matrix class */
public class Test_MatrixUniform extends Base
{
    public int programRef, vaoRef;
    
    public Uniform<Matrix> 
        modelMatrixUniform, 
        viewMatrixUniform, 
        projectionMatrixUniform;
        
    public float moveSpeed, turnSpeed;

    public static void main(String[] args)
    {
        Base test = new Test_MatrixUniform();
        // using square window size to prevent distortion
        test.setWindowSize(640, 640);
        test.run();
    }

    public void initialize()
    {
        // load code, send to GPU, and compile; store program reference
         programRef = OpenGLUtils.initFromFiles(
            "graphics/shaders/model-matrix.vert", 
            "graphics/shaders/yellow.frag" );

        // specify color used when clearing the screen
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);

        // setup vertex array object
        vaoRef = glGenVertexArrays();
        glBindVertexArray(vaoRef);

        float[] positionData = {
                  0.0f,  0.2f, 0.0f,
                  0.1f, -0.2f, 0.0f,
                 -0.1f, -0.2f, 0.0f  };
        Attribute positionAttribute = new Attribute( "vec3", positionData );
        positionAttribute.associateVariable( programRef, "position" );

        // set up uniforms
        Matrix modelMatrix = Matrix.makeIdentity();
        modelMatrixUniform = new Uniform<Matrix>("mat4", modelMatrix);
        modelMatrixUniform.locateVariable( programRef, "modelMatrix" );

        // note: positive z-axis points towards viewer 
        Matrix cameraMatrix = Matrix.makeTranslation(0, 0, 1);
        Matrix viewMatrix = cameraMatrix.inverse();
        viewMatrixUniform = new Uniform<Matrix>("mat4", viewMatrix);
        viewMatrixUniform.locateVariable( programRef, "viewMatrix" );

        Matrix projectionMatrix = Matrix.makePerspective();
        projectionMatrixUniform = new Uniform<Matrix>("mat4", projectionMatrix);
        projectionMatrixUniform.locateVariable( programRef, "projectionMatrix" );

        // movement speed, units per second
        moveSpeed = 0.5f;
        // rotation speed, radians per second
        turnSpeed = (float)Math.toRadians(90);

    }

    public void update()
    {
        // update data
        float moveAmount = moveSpeed * deltaTime;
        float turnAmount = turnSpeed * deltaTime;

        // global translation
        if (input.isKeyPressed(GLFW_KEY_W))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(0, moveAmount, 0) );

        if (input.isKeyPressed(GLFW_KEY_S))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(0, -moveAmount, 0));

        if (input.isKeyPressed(GLFW_KEY_A))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(-moveAmount, 0, 0));
      
        if (input.isKeyPressed(GLFW_KEY_D))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(moveAmount, 0, 0));

        if (input.isKeyPressed(GLFW_KEY_Z))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(0, 0, moveAmount));
        
        if (input.isKeyPressed(GLFW_KEY_X))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeTranslation(0, 0, -moveAmount));

        // global rotation
        if (input.isKeyPressed(GLFW_KEY_Q))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeRotationZ(turnAmount));

        if (input.isKeyPressed(GLFW_KEY_E))
            modelMatrixUniform.data.leftMultiply( 
                Matrix.makeRotationZ(-turnAmount));

        // local translation
        if (input.isKeyPressed(GLFW_KEY_I))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeTranslation(0, moveAmount, 0) );

        if (input.isKeyPressed(GLFW_KEY_K))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeTranslation(0, -moveAmount, 0) );

        if (input.isKeyPressed(GLFW_KEY_J))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeTranslation(-moveAmount, 0, 0) );

        if (input.isKeyPressed(GLFW_KEY_L))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeTranslation(moveAmount, 0, 0) );

        // local rotation
        if (input.isKeyPressed(GLFW_KEY_U))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeRotationZ(turnAmount) );

        if (input.isKeyPressed(GLFW_KEY_O))
            modelMatrixUniform.data.rightMultiply( 
                Matrix.makeRotationZ(-turnAmount) );

        // render scene

        // reset color buffer with specified color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glUseProgram( programRef );
        glBindVertexArray( vaoRef );
        modelMatrixUniform.uploadData();
        viewMatrixUniform.uploadData();
        projectionMatrixUniform.uploadData();
        glDrawArrays( GL_TRIANGLES, 0, 3 );
    }

}
