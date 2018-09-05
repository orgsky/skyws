package com.inca.skyws.tools;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.util.StringUtils;

public class StringCodeUtils {
	public static String CODE_TYPE = "utf-8";

	/**
	 * encode
	 * @param code
	 * @return
	 */
	public static String encode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		try {
			return URLEncoder.encode(code, CODE_TYPE);
		} catch (Exception e) {
			System.out.println("encode error:" + e);
		}
		return null;
	}

	/**
	 * decode
	 * @param code
	 * @return
	 */
	public static String decode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		try {
			return URLDecoder.decode(code, CODE_TYPE);
		} catch (Exception e) {
			System.out.println("decode error:" + e);
		}
		return null;
	}
}
