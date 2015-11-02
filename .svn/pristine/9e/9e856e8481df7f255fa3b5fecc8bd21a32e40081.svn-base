package com.sn.gameadmin;

import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sn.gameadmin.net.AdminCmd;
import com.sn.gameadmin.net.AdminNet;
import com.sn.gameadmin.net.Message;

public class AdminScreen implements Screen {
	
	enum ButtonKinds {
		broadcast, stop, playerNum, kickPlayer, checkPlayer, resetTable
	}
	
	private static final String NOTIRY_DDA = "因系统更新，需要在3分钟后停服维护，维护时间预计为5分钟";
	
	GameAdmin admin;
	Skin skin;
	Stage stage;
	
	ScrollPane scrollPane;
	List<String> console;
	HashMap<String, TextButton> buttons = new HashMap<String, TextButton>();
	String[] buttonNames = {"broadcast", "stop", "playerNum", "kickPlayer", "checkPlayer", "openid"};
	TextButton curActiveButton = null;
	TextField textInput = null;
	int cheatOpenId = 202434156;
	
	public AdminScreen(GameAdmin admin) {
		MsgCenter.getInstance().clearMsgs();
		
		this.admin = admin;
		stage = new Stage(new FitViewport(1024, 768));
		Gdx.input.setInputProcessor(stage);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		Table table = new Table();
		stage.addActor(table);
		table.setFillParent(true);
		table.defaults().height(40).width(800).pad(10);
		
		textInput = new TextField("", skin);
		textInput.setMessageText("click here");
		textInput.setAlignment(Align.center);
		textInput.setTextFieldListener(new TextFieldListener() {
			public void keyTyped (TextField textField, char key) {
//				activateButtons();
			}
		});
		table.add(textInput).width(800).height(40).center().colspan(3);
		table.row();
		
		for (int i = 0; i < buttonNames.length; i++) {
			if (i%3 == 0) {
				table.row();
			}
			
			String buttoName = buttonNames[i];
			TextButton bt = new TextButton(buttoName, skin);
			table.add(bt).center().width(200);
			bt.addListener(new ClickListener() { 
				@Override
				public void clicked (InputEvent event, float x, float y) {
					doButtons(event);
//					AdminNet.getInstance().send(AdminCmd.C_BROADCAST_SERVER, "msg", "�װ��ĸ�λ��ң�ȫ����ݽ�������ʱά����Ԥ��ά��ʱ��3���ӣ�����");
				}
			});
			
			if (i != 3) {
//				bt.setTouchable(Touchable.disabled);
			}
			buttons.put(buttoName, bt);
		}
		
		
//		TextButton bt = new TextButton(buttonNames[0], skin);
//		table.add(bt).left();
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				
//				AdminNet.getInstance().send(AdminCmd.C_BROADCAST_SERVER, "msg", "�װ��ĸ�λ��ң�ȫ����ݽ�������ʱά����Ԥ��ά��ʱ��3���ӣ�����");
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
//		buttons.put(buttonNames[0], value)
//		
//		bt = new TextButton("stop", skin);
//		table.add(bt);
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				AdminNet.getInstance().send(AdminCmd.C_NORMAL_STOP_SERVER, "subcmd", "normal_stop_server");
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
//
//		bt = new TextButton("playerNum", skin);
//		table.add(bt).right();
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				AdminNet.getInstance().send(AdminCmd.C_GET_ALL_PLAYER_NUM);
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
//		
//		
//		table.row();
//		bt = new TextButton("kickPlayer", skin);
//		table.add(bt).left();
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				AdminNet.getInstance().send(AdminCmd.C_BROADCAST_SERVER, "msg", "�װ��ĸ�λ��ң�ȫ����ݽ�������ʱά����Ԥ��ά��ʱ��3���ӣ�����");
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
//		
//		bt = new TextButton("checkPlayer", skin);
//		table.add(bt);
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				AdminNet.getInstance().send(AdminCmd.C_NORMAL_STOP_SERVER, "subcmd", "normal_stop_server");
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
//
//
//		bt = new TextButton("resetTable", skin);
//		table.add(bt).right();
//		bt.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				AdminNet.getInstance().send(AdminCmd.C_GET_ALL_PLAYER_NUM);
//			}
//		});
//		bt.setTouchable(Touchable.disabled);
		
		table.row();
		console = new List<String>(skin);
		scrollPane = new ScrollPane(console, skin);
		scrollPane.setFlickScroll(false);
		table.add(scrollPane).colspan(3).width(800).height(500);

		print("admin login scucess");
	}
	
//	void prints() {
//		String[] listEntries = {"This is a list entry1", "And another one1", "The meaning of life1", "Is hard to come by1",
//				"This is a list entry2", "And another one2", "The meaning of life2", "Is hard to come by2", "This is a list entry3",
//				"And another one3", "The meaning of life3", "Is hard to come by3", "This is a list entry4", "And another one4",
//				"The meaning of life4", "Is hard to come by4", "This is a list entry5", "And another one5", "The meaning of life5",
//				"Is hard to come by5"};
//		
//		console.setItems(listEntries);
//		scrollPane.invalidate();
//		scrollPane.validate();
//		scrollPane.setScrollPercentY(1.0f);
//	}
	
	void activateButtons() {
		String text = textInput.getText();
		if (!buttons.containsKey(text)) {
			return;
		}
		
		textInput.setText("");
		if (curActiveButton != null) {
			curActiveButton.setTouchable(Touchable.disabled);
		}
		curActiveButton = buttons.get(text);
		curActiveButton.setTouchable(Touchable.enabled);
		
		if (text.equals(buttonNames[ButtonKinds.broadcast.ordinal()])) {
			textInput.setText(NOTIRY_DDA);
		}
	}
	
	private void popErrorMsg(String msg) {
		Dialog dialog = new Dialog("error", skin, "dialog");
		dialog.text(msg);
		dialog.button("OK", true);
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
	}
	
	void doBroadcast() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			text = NOTIRY_DDA;
//			popErrorMsg("broadcast content cannot be empty");
		}
		AdminNet.getInstance().send(AdminCmd.C_BROADCAST_SERVER, "msg", text);
	}
	
	void doStop() {
		AdminNet.getInstance().send(AdminCmd.C_NORMAL_STOP_SERVER, "subcmd", "normal_stop_server");
	}
	
	void doPlayerNum() {
		AdminNet.getInstance().send(AdminCmd.C_GET_ALL_PLAYER_NUM);
	}
	
	void doKickPlayer() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			popErrorMsg("player id cannot be empty");
		}
		else {
			try {
				int openid = Integer.parseInt(text);
				AdminNet.getInstance().send(AdminCmd.C_KICK_OUT_PLAYER, "id", openid);
			}
			catch (NumberFormatException e) {
				popErrorMsg("invalide player id");
			}
		}
	}
	
	void doCheckPlayer() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			popErrorMsg("player id cannot be empty");
		}
		else {
			try {
				int openid = Integer.parseInt(text);
				AdminNet.getInstance().send(AdminCmd.C_CHECK_PLAYER_STATUS, "id", openid);
			}
			catch (NumberFormatException e) {
				popErrorMsg("invalide player id");
			}
		}
	}
	
	void doCheckTable() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			popErrorMsg("roomid and tableid cannot be empty");
		}
		else {
			try {
				String[] texts = text.split(",");
				
				popErrorMsg("roomid and tableid cannot be empty");
				
				int openid = Integer.parseInt(text);
				AdminNet.getInstance().send(AdminCmd.C_CHECK_PLAYER_STATUS, "id", openid);
			}
			catch (NumberFormatException e) {
				popErrorMsg("invalide player id");
			}
		}
	}
	
	void doOpenId() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			popErrorMsg("openid cannot be empty");
		}
		else {
			try {
				String[] texts = text.split(",");
				if (texts.length == 1) {
					int openid = Integer.parseInt(texts[0]);
					AdminNet.getInstance().send(AdminCmd.C_ADD_CHEAT_OPENID, "openid", openid, "type", 1);
					cheatOpenId = openid;
				}
				else if (texts.length > 1) {
					int openid = Integer.parseInt(texts[0]);
					int top = Integer.parseInt(texts[1]);
					AdminNet.getInstance().send(AdminCmd.C_ADD_CHEAT_OPENID, "openid", openid, "type", 1, "top", top);
					cheatOpenId = openid;
				}
			}
			catch (NumberFormatException e) {
				popErrorMsg("invalide player id");
			}
		}
	}
	
	void doTopOpenId() {
		String text = textInput.getText();
		if (text.isEmpty()) {
			popErrorMsg("openid cannot be empty");
		}
		else {
			try {
				int openid = Integer.parseInt(text);
				AdminNet.getInstance().send(AdminCmd.C_ADD_CHEAT_OPENID, "openid", openid, "type", 1, "top", 1);
				cheatOpenId = openid;
			}
			catch (NumberFormatException e) {
				popErrorMsg("invalide player id");
			}
		}
	}
	
	void doButtons(InputEvent event) {
		TextButton btn = (TextButton)(event.getListenerActor());
		String btnName = btn.getText().toString();
		if (btnName.equals(buttonNames[ButtonKinds.broadcast.ordinal()])) {
			doBroadcast();
		}
		else if (btnName.equals(buttonNames[ButtonKinds.stop.ordinal()])) {
			doStop();
		}
		else if (btnName.equals(buttonNames[ButtonKinds.playerNum.ordinal()])) {
			doPlayerNum();
		}
		else if (btnName.equals(buttonNames[ButtonKinds.kickPlayer.ordinal()])) {
			doKickPlayer();
		}
		else if (btnName.equals(buttonNames[ButtonKinds.checkPlayer.ordinal()])) {
			doCheckPlayer();
		}
		else if (btnName.equals(buttonNames[ButtonKinds.resetTable.ordinal()])) {
//			doCheckTable();
			doOpenId();
		}
//		btn.setTouchable(Touchable.disabled);
	}
	
	void print (String message) {
		message = "["+Utils.getCurTimeStr()+"] " + message;
		
		String[] lines = console.getItems().toArray(String.class);
		String[] newLines = new String[lines.length + 1];
		newLines[0] = message;
		System.arraycopy(lines, 0, newLines, 1, lines.length);
//		newLines[newLines.length - 1] = message;
		console.setItems(newLines);
		console.setSelectedIndex(0);
//		scrollPane.invalidate();
//		scrollPane.validate();
//		scrollPane.setScrollPercentY(1.0f);
	}
	
	@Override
	public void show() {
		
	}

	private void doGetAllPlayerNum(Message msg) {
		JSONObject body = msg.getJsonBody();
		int num = -1;
		if (body.containsKey("num")) {
			num = body.getInt("num");
		}
		print("all online players num is: "+num);
	}
	
	private void doCommonResp(Message msg) {
		JSONObject body = msg.getJsonBody();
		if (body.getBoolean("success")) {
			print("command send success");
		}
		else {
			popErrorMsg("command send failed, reason: "+(body.containsKey("reason")?body.getString("reason"):" unknown"));
		}
	}
	
//	private void doBroadcastServer(Message msg) {
//		
//	}
//	
//	private void doNormalStopServer(Message msg) {
//		
//	}
	
	private void doCheckPlayerStatus(Message msg) {
		JSONObject body = msg.getJsonBody();
		if (body.containsKey("status")) {
			print(body.getString("status"));
		}
		else {
			popErrorMsg("fail to get player status, reason: "+ (body.containsKey("reason")?body.getString("reason"):" unknown"));
		}
	}
	
	private void doChecks(Message msg) {
		JSONObject body = msg.getJsonBody();
		if (!body.containsKey("csum") || !body.containsKey("openid")) {
			return;
		}
		int openid = body.getInt("openid");
		if (openid != cheatOpenId) {
			return;
		}
		
		JSONArray csum = body.getJSONArray("csum");
		int rank = 0;
		for (int i = 0; i < csum.size(); i++) {
			if (csum.getInt(i) == cheatOpenId) {
				rank = i+1;
			}
		}
		String ranks = rank + body.getJSONArray("csum").toString();

		print(ranks);
	}

	
	private void doMessage() {
//		print("中文");
		
		Message msg = MsgCenter.getInstance().popMsg();
		if (msg == null) {
			return;
		}
		
		
		switch (msg.cmd)
		{
		case AdminCmd.S_GET_ALL_PLAYER_NUM:
			doGetAllPlayerNum(msg);
			break;
		case AdminCmd.S_BROADCAST_SERVER:
			doCommonResp(msg);
			break;
		case AdminCmd.S_NORMAL_STOP_SERVER:
			doCommonResp(msg);
			break;
		case AdminCmd.S_KICK_OUT_PLAYER:
			doCommonResp(msg);
			break;
		case AdminCmd.S_CHECK_PLAYER_STATUS:
			doCheckPlayerStatus(msg);
			break;
		case AdminCmd.S_CHEATS:
			doChecks(msg);
			break;
		}
	}
	
	private void doLogic() {
		doMessage();
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
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}

}
