package com.clonelol.config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionCheck {
	public static String profileiconVersion = null;
	public static String summonerVersion = null;
	public static String championVersion = null;
	public static String mapVersion = null;
	public static String languageVersion = null;
	public static String stickerVersion = null;
	public static String itemVersion = null;
	
	public static void checkVersion() {
		VersionCheckDto versionCheckDto = null;
		BufferedReader br = null;
		try{            
			String urlstr = "https://ddragon.leagueoflegends.com/realms/kr.json";
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 여기에 문자열을 받아와라.
			String result = "";
			String line;
			while((line = br.readLine()) != null) {
				result = result + line;
			}

			Gson gson = new Gson();
			versionCheckDto = gson.fromJson(result, VersionCheckDto.class);
			itemVersion 		= versionCheckDto.getN().getItem();
			summonerVersion 	= versionCheckDto.getN().getSummoner();
			championVersion 	= versionCheckDto.getN().getChampion();
			profileiconVersion 	= versionCheckDto.getN().getProfileicon();
			mapVersion 			= versionCheckDto.getN().getMap();
			languageVersion 	= versionCheckDto.getN().getLanguage();
			stickerVersion 		= versionCheckDto.getN().getSticker();

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
