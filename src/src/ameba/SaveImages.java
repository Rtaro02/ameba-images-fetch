package src.ameba;

import java.util.List;

import src.httpclient.GetImage;
import src.httpclient.JavaNetHttpClient;

public class SaveImages {

	public static void saveImages(String initialUrl, String path, Integer num) {
		String url = initialUrl;
		for(int i=0; i < num; i++) {
			// nextUrl
			url = fetchImages(url, path);
			System.out.println("===== NEXT URL " + url + "=====");
		}
	}
	
	private static String fetchImages(String url, String path) {
		List<String> list = JavaNetHttpClient.executeGet(url);
		for(int i=1; i<list.size(); i++) {
			GetImage.fetch(list.get(i), path);
		}
		return list.get(0);
	} 
}
