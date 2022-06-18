package dazor.test;

import dazor.api.Insight;
import dazor.test.testshader.TestShader;
import dazor.test.testshader.UsingBufferShader;

public class InsightTest {

	public static void main(String[] args) {
		
		Insight i = Insight.create();
		
		i.createWindow(200,200);
		i.addShader(new TestShader());
		i.addShader(new UsingBufferShader());
		i.render();
	}
}
