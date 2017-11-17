package ameba;

import java.util.List;

import httpclient.GetImage;
import httpclient.JavaNetHttpClient;

public class SaveImages {
	private static SaveImages singleton = new SaveImages();
	
	private SaveImages() {
	}
	
	public static SaveImages getInstance() {
		return singleton;
	}

    public void excecute(String initialUrl, String path, Integer num) {
        String url = initialUrl;
        Integer i = 0;
        while(i<num && !url.equals("")) {
            // nextUrl
            url = fetchImages(url, path);
            System.out.println("===== NEXT URL " + url + "=====");
            i++;
        }
        System.out.println("===== END !!=====");
    }

    private String fetchImages(String url, String path) {
        List<String> list = JavaNetHttpClient.executeGet(url);
        String str = "";
        GetImage getImages = GetImage.getInstance();
        for(int i=0; i<list.size(); i++) {
            if (list.get(i).endsWith("jpg")) {
            	getImages.fetch(list.get(i), path);
            } else {
                str = list.get(i);
            }
        }
        return str;
    }
}
