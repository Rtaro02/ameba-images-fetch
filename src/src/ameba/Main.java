package src.ameba;

import java.util.List;

import src.httpclient.GetImage;
import src.httpclient.JavaNetHttpClient;

public class Main {
	public static void main(String args[]) {
		List<String> list = JavaNetHttpClient.executeGet("https://ameblo.jp/c-ute-official/entry-12283798139.html");
		for(int i=0; i<list.size(); i++) {
			GetImage.fetch(list.get(i));
		}
	}
}
