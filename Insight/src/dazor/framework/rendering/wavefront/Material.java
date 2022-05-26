package dazor.framework.rendering.wavefront;

import dazor.framework.math.Vec3f;

/**
 * Basic implementation of a Material importet through a .mtl file
 * @author Daniel Banic
 */
public class Material {

	public Material() {
		this("test",null,null,null,null,null,null,-1,null);
	}
	
	public Material(String newmtl) {
		this(newmtl,null,null,null,null,null,null,-1,null);
	}
	
	public Material(String newmtl, String file) {
		this(newmtl,null,null,null,null,null,null,-1,file);
	}
	
	public Material(String newmtl, Vec3f Ka, Vec3f Kd, Vec3f Ks, Float Ns, Float Ni, Float d, int illum, String map_kd) {
		if(Ka == null) {
			Ka = new Vec3f(0.2,0.2,0.2);
		}
		if(Kd == null) {
			Kd = new Vec3f(0.8,0.8,0.8);
		}
		if(Ks == null) {
			Ks = new Vec3f(1.0,1.0,1.0);
		}
		if(Ns == null) {
			Ns = 1f;
		}
		if(Ni == null) {
			Ni = 1f;
		}
		if(d == null) {
			d = 1f;
		}
		if(illum == -1) {
			illum = 0;
		}
		
		this.newmtl = newmtl;
		this.Ka = Ka;
		this.Kd = Kd;
		this.Ks = Ks;
		this.Ns = Ns;
		this.Ni = Ni;
		this.d = d;
		this.Tr = 1.0f - d;
		this.illum = illum;
		this.map_kd = map_kd;
		
	}
	
	/**
	 * The name of the Material
	 */
	String newmtl;
	
	/**
	 * This Vec3f specifies the ambient color for the light scattered around the scene
	 * It consists of 3 Floats that range between 0 and 1 for the RGB representation
	 */
	Vec3f Ka;
	
	/**
	 * This Vec3f specifies the diffuse color, which is going to be layered on top of the actual Texture / color
	 * The Values of this Vec3f consist of 3 Floats that range from 0 to 1 for the RGB representation
	 */
	Vec3f Kd;
	
	/**
	 * This Vec3f specifies the specular color, which makes a surface shiny
	 * The Values of this Vec3f consist of 3 Floats that range from 0 to 1 for the RGB representation
	 */
	Vec3f Ks;
	
	/**
	 * This float defines the focus of specular highlights in the material. 
	 * The values of this float range from 0 to 1000, with a high value resulting in a tight, concentrated highlight.
	 */
	float Ns;
	
	/**
	 * This float defines the optical density in the current material.
	 * The values of this float range from 0.001 to 10, in which a value of 1 results in light passing throug the object without bending
	 */
	float Ni;
	
	/**
	 * This float defines how strongly this Material dissolves into the background
	 * The values of this float range from 0 to 1, in which a value of 0 means the Material is translucent and a Value of 1 means the Material is opaque
	 */
	float d;
	
	/**
	 * This float is the inverted value of {@link #d}. This Value will be ignored if d is declared
	 */
	float Tr;
	
	/**
	 * This Vec3f is the Transmission Filter and will block certain RGB values when declared
	 * If it´s not declared the default a new Vec3f(1,1,1) 
	 */
	Vec3f Tf;
	
	/**
	 * This int specifies which Illumination model should be used for the Material. The available Illumination models range from 0 - 2
	 * 
	 * Only one I am able to implement for now
	 * illum 0: a constant color illumination model, using the Kd for the material
	 * 
	 * Potential shit
	 * illum 1: a diffuse illumination model using Lambertian shading, taking into account Ka, Kd, the intensity and position of each light source and the angle at which it strikes the surface.
     * illum 2: a diffuse and specular illumination model using Lambertian shading and Blinn's interpretation of Phong's specular illumination model, taking into account Ka, Kd, Ks, and the intensity and position of each light source and the angle at which it strikes the surface.
	 */
	int illum;
	
	/**
	 * This string defines the color texture file which will be applied to the {@link #Kd} reflectivly. During rendering values of {@link #map_kd} will be multiplied with {@link #Kd} to get the resulting RGB values
	 */
	String map_kd;
		
	@Override
	public String toString() {
		String tempString = "";
		tempString += "newmtl " +newmtl+"\n";
		if(Ka != null) {
			tempString += "Ka "  +Ka.getX()+" "+Ka.getY()+" "+Ka.getZ()+"\n";	
		}
		if(Kd != null) {
			tempString += "Kd "  +Kd.getX()+" "+Kd.getY()+" "+Kd.getZ()+"\n";	
		}
		if(Ks != null) {
			tempString += "Ks "  +Ks.getX()+" "+Ks.getY()+" "+Ks.getZ()+"\n";
		}
		tempString += "Ns "  +Ns+"\n";
		tempString += "Ni "  +Ni+"\n";
		tempString += "d "   +d+"\n";
		tempString += "illum "+illum+"\n";
		if(map_kd != null) {
			tempString += "map_kd "+map_kd;
		}
		return tempString;
	}

}
