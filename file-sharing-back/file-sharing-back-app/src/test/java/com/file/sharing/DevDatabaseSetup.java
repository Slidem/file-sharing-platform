package com.file.sharing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.assertj.core.util.Lists;
import org.springframework.data.util.Pair;

/*
 * CLEANUP DISK!!!
 * 
 * DB CLEANUP 
    truncate table public.directory cascade;
	ALTER SEQUENCE seq_id_item RESTART WITH 100
	ALTER SEQUENCE seq_id_user RESTART WITH 100
	
	truncate table public.item_action cascade;
	
	
	
	//clean start
	DROP SCHEMA public CASCADE;
	CREATE SCHEMA public;
 * 
 */
public class DevDatabaseSetup {
	//THIS MUST BE CHANGED BEFORE RUN
	private static final String AUTHORISATION = "bearer "
			+ "";
	private static final String AUTH_HEADER = "Authorization";
	private static final String AUTH_URL = "http://localhost:9081/file-sharing-auth/oauth/authorize";
	private static final String TOKEN_URL = "http://localhost:9081/file-sharing-auth/oauth/token";

	private static final List<Pair<String,String>> URL_LIST = Lists.newArrayList(
			Pair.of("POST", "http://localhost:8080/file-sharing-back/createDirectory?directoryName=dir_1"),
			Pair.of("POST", "http://localhost:8080/file-sharing-back/createDirectory?directoryName=dir_1_1&parentId=100"),
			Pair.of("POST", "http://localhost:8080/file-sharing-back/createDirectory?directoryName=dir_1_2&parentId=100"),
			Pair.of("PATCH", "http://localhost:8080/file-sharing-back/renameDirectory?fileId=102&newName=dir_1_2_renamed")
	);
	
	public static void main(String args[]) throws IOException, InterruptedException {
		allowMethods("PATCH");
		for(Pair<String,String> pair : URL_LIST) {
			executeGet(pair.getSecond(), pair.getFirst());
			Thread.sleep(1000);
		}
	}

	public static void executeGet(String url, String reqMethod) throws IOException {
		//request
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestProperty(AUTH_HEADER, AUTHORISATION);
		con.setDoOutput(true);
		con.setRequestMethod(reqMethod);
		
		//response
		System.out.println("REQUEST: " + url);
		System.out.println("RESPONSE: " + con.getResponseCode());
//		StringBuffer content = getResponseContent(con);
//		System.out.println(content);
		System.out.println();
	}

	public static StringBuffer getResponseContent(HttpURLConnection con) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		return content;
	}
	
	// using PATCH workaround
    private static void allowMethods(String... methods) {
        try {
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/*static field*/, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }	
}
