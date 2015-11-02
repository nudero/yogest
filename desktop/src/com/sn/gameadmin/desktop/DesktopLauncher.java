package com.sn.gameadmin.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sn.gameadmin.GameAdmin;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "";
		config.width = 200;
		config.height = 20;
		new LwjglApplication(new GameAdmin(), config);
	}
}
