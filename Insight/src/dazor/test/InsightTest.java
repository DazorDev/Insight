package dazor.test;

import dazor.api.Insight;

public class InsightTest {

	public static void main(String[] args) {
		
		Insight i = Insight.create();
		
		i.createWindow(500,500);
		i.render();
	}
}
