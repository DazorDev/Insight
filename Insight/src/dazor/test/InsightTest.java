package dazor.test;

import dazor.api.Insight;
import dazor.test.testlistener.ScaleListener;
import dazor.test.testshader.MappingShader;

public class InsightTest {

	public static void main(String[] args) {
		Insight insight = Insight.create();
		insight.createWindow(500,500);
		insight.registerListener(new ScaleListener(insight));
		insight.addShader(new MappingShader());
		insight.render();
	}
}
