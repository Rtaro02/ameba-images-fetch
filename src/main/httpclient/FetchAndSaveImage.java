package main.httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

public class FetchAndSaveImage {
	private static FetchAndSaveImage singleton = new FetchAndSaveImage();
	
	private FetchAndSaveImage() {
	}
	
	public static FetchAndSaveImage getInstance() {
		return singleton;
	}

	/**
	 * 画像を保存する
	 */
    public void fetch(String imageUrl, String path){
        System.out.println("===== GET IMAGE START =====");
        try {
            URI uri =new URI(imageUrl);
            URL url=uri.toURL();
            System.out.println("===== Connect " + imageUrl + " =====");
            URLConnection urlcon =url.openConnection();
            InputStream fileIS =urlcon.getInputStream();
            File saveFile = new File(path + "/" + imageUrl.replaceAll("^.*/([^/]+)$", "$1"));
            FileOutputStream fileOS = new FileOutputStream(saveFile);
            int c;
            while((c =fileIS.read()) != -1) fileOS.write((byte) c);
            fileOS.close();
            fileIS.close();

        } catch (URISyntaxException e) {
           System.err.println(e);
        } catch (MalformedURLException e) {
           System.err.println(e);
        } catch (IOException e) {
           System.err.println(e);
        }
        System.out.println("===== GET IMAGE End =====");
    }
}
