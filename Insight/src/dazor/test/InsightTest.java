package dazor.test;

import dazor.api.Insight;
import dazor.test.testlistener.ScaleListener;
import dazor.test.testshader.TestShader;

public class InsightTest {

	public static void main(String[] args) {
		Insight insight = Insight.create();
		insight.createWindow(200,200);
		insight.registerListener(new ScaleListener(insight));
//		insight.addShader(new MappingShader());
//		insight.addShader(new ColorBlendingTest());
		insight.addShader(new TestShader());
		insight.render();
	}
}
