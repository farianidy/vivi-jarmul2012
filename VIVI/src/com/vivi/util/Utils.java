package com.vivi.util;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
	
	public static void closeStreamQuietly(InputStream is) {
		try {
			if (is != null)
				is.close();
		}
		catch (IOException e) {
			// ignore exception
		}
	}
}
