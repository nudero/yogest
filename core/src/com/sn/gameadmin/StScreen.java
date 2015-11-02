package com.sn.gameadmin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class StScreen implements Screen {
	Stage stage;
	int st = 0;
	
	boolean tftf = false;
	float stime = 0;
	BitmapFont font;
	BitmapFont font1;
	BitmapFont font2;
	BitmapFont tfont;
	SpriteBatch batch;
	Skin skin;
	TextField textf;
	Label label_left = null;
	Label label_right = null;
	
	int st1 = 0;
	int st2 = 0;
	
	
//	600198
//	601766
	
	boolean szconnect = false;
	boolean zxconnect = false;
	boolean connect = false;
	
	boolean connect1 = false;
	boolean connect2 = false;
	
	HashMap<Integer, Boolean> connects = new HashMap<Integer, Boolean>();
	ArrayList<Integer> sts = new ArrayList<Integer>();
	
	public StScreen(GameAdmin admin) {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		font = new BitmapFont();
		font.setColor(new Color(0.02f, 0.02f, 0.02f, 1));
		font2 = new BitmapFont();
		font2.setColor(new Color(0.05f, 0.05f, 0.05f, 1));
		font1 = new BitmapFont();
		font1.setColor(new Color(0.2f, 0.2f, 0.2f, 1));
		batch = new SpriteBatch();
		
//		Table table = new Table();
//		stage.addActor(table);
//		table.setFillParent(true);
//		table.defaults().height(60).width(300).pad(20);
//		
//		textf = new TextArea("", skin);
//		table.add(textf);
		
		for (int i = 0; i < 4; i++) {
			sts.add(0);
		}
		
//		table.row();
//		TextButton btnConnect = new TextButton("connect", skin);
//		table.add(btnConnect);
//		btnConnect.addListener(new ClickListener() {
//			@Override
//			public void clicked (InputEvent event, float x, float y) {
//				szconnect();
//			}
//		});
		
	}
	
	private void parseCheats() {
		String tline = "";
		File f = new File("./weblog");
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
			BufferedReader reader = new BufferedReader(isr);
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("csum")) {
					tline = line;
				}
			}
			reader.close();
			isr.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		if (tline.isEmpty()) {
			popMsg(0+"");
		}
		else {
			int index = tline.indexOf("{");
			String substr = tline.substring(index);
			JSONObject json = JSONObject.fromObject(substr);
			int rank = 0;
			String ranks = "[";
			if (json.containsKey("csum")) {
				JSONArray arr = json.getJSONArray("csum");
				for (int i = 0; i < arr.size(); i++) {
					if (arr.getInt(i) == 202434156) {
						rank = i+1;
					}
					ranks = ranks + arr.getInt(i) + ",";
				}
				ranks = ranks + "] " + rank;
			}
			popMsg(ranks);
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	void stf1() {
		String ttid = textf.getText();
		if (ttid.isEmpty()) {
			ttid = "sh601519"; // 300104 sz300188
		}
		
		if (connect1) {
			return;
		}
		connect1 = true;
		
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://hq.sinajs.cn/list="+ttid).build();
		httpRequest.setTimeOut(5000);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				connect1 = false;
				String result = httpResponse.getResultAsString();
				String[] results = result.split(",");
				try {
					float c = Float.parseFloat(results[3]);
					float o = Float.parseFloat(results[2]);
					float p = (c-o)*100.0f/o;
					st1 = (int)(Float.parseFloat(String.format("%.2f", p)) * 100);
//					popMsg(String.format("%.2f", p));
				}
				catch (Exception e) {
//					popMsg("error");
				}
			}

			@Override
			public void failed(Throwable t) {
				connect1 = false;
				System.out.println(t.getMessage());
			}
			
			@Override
			public void cancelled() {
				connect1 = false;
			}
		});
	}
	
	void stf(int index, String stid) {
		if (connects.containsKey(index)) {
			return;
		}
		connects.put(index, true);
		
		final int theIndex = index;
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://hq.sinajs.cn/list="+stid).build();  
		httpRequest.setTimeOut(5000);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				connects.remove(theIndex);
				String result = httpResponse.getResultAsString();
				String[] results = result.split(",");
				try {
					float c = Float.parseFloat(results[3]);
					float o = Float.parseFloat(results[2]);
					float p = (c-o)*100.0f/o;
					int st = (int)(Float.parseFloat(String.format("%.2f", p)) * 100);
					sts.set(theIndex, st);
				}
				catch (Exception e) {
				}
			}

			@Override
			public void failed(Throwable t) {
				connects.remove(theIndex);
				System.out.println(t.getMessage());
			}
			
			@Override
			public void cancelled() {
				connects.remove(theIndex);
			}
		});
	}
	
	void stf2() {
		if (connect2) {
			return;
		}
		connect2 = true;
		
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://hq.sinajs.cn/list=sh600575").build(); // sh601519  
		httpRequest.setTimeOut(5000);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				connect2 = false;
				String result = httpResponse.getResultAsString();
				String[] results = result.split(",");
				try {
					float c = Float.parseFloat(results[3]);
					float o = Float.parseFloat(results[2]);
					float p = (c-o)*100.0f/o;
					st2 = (int)(Float.parseFloat(String.format("%.2f", p)) * 100);
//					popMsg(String.format("%.2f", p));
				}
				catch (Exception e) {
//					popMsg("error");
				}
			}

			@Override
			public void failed(Throwable t) {
				connect2 = false;
				System.out.println(t.getMessage());
			}
			
			@Override
			public void cancelled() {
				connect2 = false;
			}
		});
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
		
		stime += delta;
		if (stime > 3.0f) {
			stime = 0;
			connect();
//			stf1();
//			stf2();
			
			stf(0, "sd601519");
			stf(1, "sz300104");
			stf(2, "sz300223");
			stf(3, "sz300331");
		}
		
		batch.begin();
		
		
		
		
		
		
		
		
		
		
		if (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT)) {
			tftf = !tftf;
		}
		
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			tfont = font1;
		}
		else {
			if (tftf) {
				tfont = font;
			}
			else {
				tfont = font2;
			}
		}
		tfont.draw(batch, st+"", 10, 15);
		for (int i = 0; i < sts.size(); i++) {
			tfont.draw(batch, sts.get(i)+"", 50+40*i, 15);
		}
		batch.end();
	}
	
	private void popMsg(String msg) {
		Dialog dialog = new Dialog("info", skin, "dialog");
		dialog.text(msg);
		dialog.button("OK", true);
		dialog.key(Keys.ENTER, true);
		dialog.show(stage);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	void szconnect() {
		if (szconnect) {
			return;
		}
		szconnect = true;
		
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://hq.sinajs.cn/list=sh000001").build();
		httpRequest.setTimeOut(5000);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				szconnect = false;
				String result = httpResponse.getResultAsString();
				String[] results = result.split(",");
				popMsg(results[3]);
			}

			@Override
			public void failed(Throwable t) {
				szconnect = false;
				System.out.println(t.getMessage());
			}
			
			@Override
			public void cancelled() {
				szconnect = false;
			}
		});
	}
	
	void connect() {
		if (connect) {
			return;
		}
		connect = true;
		
		HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
		HttpRequest httpRequest = requestBuilder.newRequest().method(HttpMethods.GET).url("http://hq.sinajs.cn/list=sh000001").build();
		httpRequest.setTimeOut(5000);
		Gdx.net.sendHttpRequest(httpRequest, new HttpResponseListener() {

			@Override
			public void handleHttpResponse(HttpResponse httpResponse) {
				connect = false;
				try {
					String result = httpResponse.getResultAsString();
					String[] results = result.split(",");
//					st = (int) (Float.parseFloat(results[3]) * 1000);
					st = (int) (Float.parseFloat(results[3])) % 1000;
				}
				catch (Exception e) {
					
				}
			}

			@Override
			public void failed(Throwable t) {
				connect = false;
				System.out.println(t.getMessage());
			}
			
			@Override
			public void cancelled() {
				connect = false;
			}
		});
		
//		"http://hq.sinajs.cn/list=sz150172,sh000001"
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
		// TODO Auto-generated method stub
		
	}

}
