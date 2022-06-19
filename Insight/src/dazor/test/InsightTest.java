package dazor.test;

import dazor.api.Insight;
import dazor.test.testshader.EyeCancerShader;
import dazor.test.testshader.OnlyGreenChannelShader;

public class InsightTest {

	public static void main(String[] args) {
		Insight i = Insight.create();
		i.createWindow(400,400);
		i.addShader(new EyeCancerShader());
		i.addShader(new OnlyGreenChannelShader());
		i.render();
	}
}
