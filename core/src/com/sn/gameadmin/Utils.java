package com.sn.gameadmin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String getCurTimeStr() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
}
