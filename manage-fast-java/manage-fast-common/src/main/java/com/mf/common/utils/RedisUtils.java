package com.mf.common.utils;

public class RedisUtils {

	private static final String userRedisKey = "mf_user#";
	private static final String tokenRedisKey = "mf_token#";

	public static String genUserRedisKey(Long userId) {
		return userRedisKey + userId;
	}

	public static String genTokenRedisKey(String token) {
		return tokenRedisKey + token;
	}
}
