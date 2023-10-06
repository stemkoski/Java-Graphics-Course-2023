package graphics.material;

import static org.lwjgl.opengl.GL40.*;

import java.util.HashMap;
import graphics.core.OpenGLUtils;
import graphics.core.Uniform;

public class Material
{

    public int programRef;

    public int drawStyle;

    // Store Uniform objects,
    //   indexed by name of associated variable in shader.
    public HashMap<String, Uniform> uniforms;

    public Material(String vertexShaderFileName, String fragmentShaderFileName)
    {
        programRef = OpenGLUtils.initFromFiles( 
            vertexShaderFileName, fragmentShaderFileName );

        drawStyle = GL_TRIANGLES;

        uniforms = new HashMap<String, Uniform>();

        // Each shader typically contains these uniforms;
        //   values will be set during render process from Mesh/Camera.
        // Additional uniforms added by extending classes.
        addUniform("mat4", "modelMatrix", null);
        addUniform("mat4", "viewMatrix", null);
        addUniform("mat4", "projectionMatrix", null);
    }

    public void addUniform(String dataType, String variableName, Object data)
    {
        uniforms.put(variableName, new Uniform(dataType, data));
    }

    // initialize all uniform variable references
    public void locateUniforms()
    {
        for (String variableName : uniforms.keySet()) 
        {
            Uniform uniform = uniforms.get(variableName);
            uniform.locateVariable(programRef, variableName);
        }
    }
}