package dazor.framework.math;

public class Mat2f {

	float[][] matrix = new float[2][2];
	
	public Mat2f() {
		
	}
	
	public Mat2f(float firstFirst, float secondFirst, float firstSecond, float secondSecond) {
		matrix[0][0] = firstSecond;
		matrix[1][0] = firstSecond;
		matrix[0][1] = firstSecond;
		matrix[1][1] = firstSecond;
	}
	
	public Mat2f(float[][] mat) {
		for(int i=0; i!=2; i++) {
			for(int j=0; j!=2; j++) {
				matrix[i][j] = mat[i][j];
			}
		}
	}
	
	
	public Mat2f(Vec2f z, float f, float x) {
		matrix[0][0] = z.x;
		matrix[1][0] = z.y;
		matrix[0][1] = f;
		matrix[1][1] = x;
	}

	public float dotProduct(Mat2f inputMatrix) {
		float sum = 0;
		for(int i = 0; i!=2; i++) {
			for(int j = 0; j!=2; j++) {
				sum += matrix[i][j] * inputMatrix.matrix[i][j];
			}
		}
		return sum;
	}
	
	public Vec2f multiply(Vec2f inputVector) {
		return multiply(inputVector, null);
	}
	public Vec2f multiply(Vec2f inputVector, Vec2f outputVector) {
		if(outputVector == null) {
			outputVector = new Vec2f();
		}
		for(int i=0; i!=2; i++) {
			float vectorValue = 0;
			for(int j=0; j!=2; j++) {
				vectorValue += matrix[j][i] * inputVector.getVector()[i];
			}
			outputVector.vec[i] = vectorValue;
		}
		return outputVector;
	}
}
