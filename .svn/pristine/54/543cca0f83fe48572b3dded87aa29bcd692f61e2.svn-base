package com.sn.gameadmin;

import net.sf.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sn.gameadmin.net.AdminCmd;
import com.sn.gameadmin.net.AdminNet;
import com.sn.gameadmin.net.Message;

public class LoginScreen implements Screen {
	
	Stage stage;
	Skin skin;
	TextButton btnConnect;
	TextField tfName;
	TextField tfPwd;
	GameAdmin admin;
	
	public LoginScreen(GameAdmin admin) {
		this.admin = admin;
		stage = new Stage(new FitViewport(640, 480));
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		Table table = new Table();
//		table.debug();
		stage.addActor(table);
		table.setFillParent(true);
		table.defaults().height(60).width(300).pad(20);
		
		tfName = new TextField("", skin);
		table.add(tfName);
		tfName.setAlignment(Align.center);
		tfName.setMessageText("ID");
		tfName.addListener(new InputListener() {
			
		});
		
		table.row();
		tfPwd = new TextField("", skin);
		table.add(tfPwd);
		tfPwd.setAlignment(Align.center);
		tfPwd.setMessageText("password");
		
		table.row();
		btnConnect = new TextButton("connect", skin);
		table.add(btnConnect);
		btnConnect.addListener(new ClickListener() {
			@Override
			public void clicked (InputEvent event, float x, float y) {
				connect();
			}
		});
		
		MsgCenter.getInstance().clearMsgs();
	}
	
	private void popErrorMsg(String msg) {
		Dialog dialog = new Dialog("error", skin, "dialog") {
		    public void result(Object obj) {
		        if (obj.equals(true)) {
		        	btnConnect.setTouchable(Touchable.enabled);
		        }
		    }
		};
		dialog.text(msg);
		dialog.button("OK", true);
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
	}
	
	private void connect() {
		btnConnect.setTouchable(Touchable.disabled);
		
		String name = tfName.getText();
		String pwd = tfPwd.getText();
		if (name.isEmpty() || pwd.isEmpty()) {
			popErrorMsg("id and pwd cannot be empty");
			return;
		}
		
		boolean connected = AdminNet.getInstance().connect();
		if (!connected) {
			popErrorMsg("fail to connect server");
			return;
		}
		
		// aadminn 0508
		AdminNet.getInstance().send(AdminCmd.C_LOGIN, "name", name, "password", pwd);
	}
	
	private void doLogin(Message msg) {
		JSONObject body = msg.getJsonBody();
		boolean success = false;
		if (body.containsKey("success")) {
			success = body.getBoolean("success");
		}
		
		if (success) {
			admin.setScreen(new AdminScreen(admin));
		}
		else {
			popErrorMsg(body.containsKey("reason")?body.getString("reason"):"fail to connect server");
		}
	}
	
	private void doMessage() {
		Message msg = MsgCenter.getInstance().popMsg();
		if (msg == null) {
			return;
		}
		switch (msg.cmd)
		{
		case AdminCmd.S_LOGIN:
			doLogin(msg);
			break;
		}
	}
	
	private void doLogic() {
		doMessage();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
		
		doLogic();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
