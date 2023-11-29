import static org.lwjgl.glfw.GLFW.*;

import graphics.core.*;
import graphics.math.*;
import graphics.geometry.*;
import graphics.material.*;
import graphics.extras.*;
import graphics.light.*;

public class Test_Lights extends Base
{
    public Renderer renderer;
    public Scene scene;
    public Camera camera;
    public Mesh mesh;
    public MovementRig rig;

    public DirectionalLight directionalLight;
    public PointLight pointLight;

    public static void main(String[] args)
    {
        Base test = new Test_Lights();
        test.setWindowSize(800, 800);
        test.run();
    }

    public void initialize()
    {
        renderer = new Renderer();
        renderer.setClearColor(new Vector(0.4,0.4,0.4,1));

        scene    = new Scene();
        camera   = new Camera();
        
        rig = new MovementRig();
        rig.attach( camera );
        rig.setPosition( new Vector(0.5, 1, 4) );
        scene.add( rig );

        Light ambient = new AmbientLight( new Vector(0.1, 0.1, 0.1) ); 
        scene.add( ambient );

        directionalLight = new DirectionalLight( 
            new Vector(0.8, 0.8, 0.8), new Vector(-1, -1, -2) );
        scene.add( directionalLight );

        pointLight = new PointLight(
            new Vector(0.9, 0, 0), new Vector(1, 1, 0.8) );
        scene.add( pointLight );


        Geometry sphereGeometry = new SphereGeometry();

        Material flatMaterial = new FlatMaterial(null);
        flatMaterial.uniforms.get("baseColor").data = new Vector(0.6, 0.2, 0.2);
        Mesh sphere1 = new Mesh(sphereGeometry, flatMaterial);
        sphere1.setPosition( new Vector(-2.2, 0, 0) );
        scene.add( sphere1 );

        Material lambertMaterial = new LambertMaterial( new Texture("images/grid.png") );
        Mesh sphere2 = new Mesh(sphereGeometry, lambertMaterial);
        sphere2.setPosition( new Vector(0, 0, 0) );
        scene.add( sphere2 );

        Material phongMaterial = new PhongMaterial(null);
        phongMaterial.uniforms.get("baseColor").data = new Vector(0.5, 0.5, 1);
        Mesh sphere3 = new Mesh(sphereGeometry, phongMaterial);
        sphere3.setPosition( new Vector(2.2, 0, 0) );
        scene.add( sphere3 );

    }

    public void update()
    {
        rig.update(input, deltaTime);
        renderer.render(scene, camera);
    }


}

