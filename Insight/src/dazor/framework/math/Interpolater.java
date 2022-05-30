package dazor.framework.math;

public class Interpolater {

	public static float linearInterpolate(float startingPosition, float endPosition, int numberOfSteps) {
		float growth = startingPosition / endPosition;
		return growth * numberOfSteps;
	}
	
	public static Vec2f linearInterpolate(Vec2f startingPosition, Vec2f endPosition, int numberOfSteps) {
		float deltaX = startingPosition.getX() - endPosition.getX();
		float deltaY = startingPosition.getY() - endPosition.getY();
		float growth = deltaX / deltaY;
		
		Vec2f output = new Vec2f(startingPosition);
		output.addLocal(new Vec2f(numberOfSteps, growth*numberOfSteps));
		return output;
	}
	
}
