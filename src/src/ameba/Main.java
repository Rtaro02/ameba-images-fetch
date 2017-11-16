package src.ameba;

import java.util.List;

import src.httpclient.GetImage;
import src.httpclient.JavaNetHttpClient;

public class Main {
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1]; 
		List<String> list = JavaNetHttpClient.executeGet(url);
		for(int i=0; i<list.size(); i++) {
			GetImage.fetch(list.get(i), path);
		}
	}
}
