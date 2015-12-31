package com.axlan.time3d;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Test3D extends DefaultScreen {

	

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public Environment environment;
	public PerspectiveCamera cam;
	public CameraInputController camController;
	public ModelBatch modelBatch;
	public Model model;
	public Vector<ModelInstance> instances;
	
	float lastUpdate;
	
	private void generateCubes()
	{
		instances.clear();

        for(int i=0;i< 1000;i++) {
        	ModelInstance instance = new ModelInstance(model);
        	Matrix4 transform = new Matrix4();
        	float x,y,z;
        	float spacing = 100;
        	x=(float) (Math.random()*spacing);
        	y=(float) (Math.random()*spacing);
        	z=(float) (Math.random()*spacing);
            transform.setToTranslation(new Vector3(x,y,z));
            instance.transform = transform;
            float r,g,b;
            r=(float) Math.random();
            g=(float) Math.random();
            b=(float) Math.random();
            
            instance.materials.first().set(ColorAttribute.createDiffuse(new Color(r,g,b,1f)));
        	instances.add(instance);
        }
	}
	
	public Test3D(Game game) {
		super(game);

		instances = new Vector<ModelInstance>();
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
		
		modelBatch = new ModelBatch();
		
		cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(10f, 10f, 10f);
		cam.lookAt(0,0,0);
		cam.near = 1f;
		cam.far = 300f;
		cam.update();
		
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position | Usage.Normal);
        
        generateCubes();
        
        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
        
        lastUpdate=0;
	}

	@Override
	public void render(float delta) {
		camController.update();
		
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
 
        modelBatch.begin(cam);
        for(ModelInstance instance:instances) {
        	modelBatch.render(instance, environment);
        }
        modelBatch.end();
        lastUpdate+=delta;
        if(lastUpdate>1) {
        	lastUpdate-=1;
        	generateCubes();
        }
	}
	
	@Override
	public void dispose() {
		modelBatch.dispose();
		model.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
