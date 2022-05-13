package dazor.test;

import javax.swing.JPanel;

import dazor.api.Insight;

public class TestButMoreRobust {
	
	public static void main(String[] args) {
		Insight insight = Insight.create();
		insight.createWindow();
		insight.createWindow(20,20);
		insight.getWindow().add(new JPanel());
	}
}
