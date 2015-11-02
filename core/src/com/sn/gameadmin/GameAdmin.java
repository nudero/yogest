package com.sn.gameadmin;

import net.sf.json.JSONObject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class GameAdmin extends Game {
	@Override
	public void create () {
		load();
		setScreen(new StScreen(this));
	}
	
	public static void load() {
		FileHandle f = Gdx.files.local("admin.cfg");
		if (!f.exists()) {
			Const.ADMIN_IP = "127.0.0.1";
		}
		String filestr = f.readString();
//			String filestr = new String(filedata, Charset.forName("UTF-8"));
		JSONObject json = JSONObject.fromObject(filestr);
		
		Const.ADMIN_IP = json.getString("ip");
	}
}
