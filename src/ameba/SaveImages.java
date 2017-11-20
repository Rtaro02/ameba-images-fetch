package ameba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public void excecute(String initialUrl, String path, Integer upperLimit) {
        String url = JavaNetHttpClient.fetchInitialURL(initialUrl);
        System.out.println("===== Initial URL is " + url + " =====");
        String lastURL = readURL(path, initialUrl);
        // URLを書き込む
        writeURL(url, path, initialUrl);
        Integer i = 0;
        while(i<upperLimit && isNotEndUrl(url) && isPriviousFetchURL(url, lastURL)) {
            // nextUrl
            url = fetchImages(url, path);
            System.out.println("===== NEXT URL " + url + " =====");
            // 初回のみURL保持
            i++;
        }
        System.out.println("===== END !! =====");
    }
    
    /**
     * URLが空文字列の場合は最終ページなので、終了する
     * @param url
     * @return
     */
    private boolean isNotEndUrl(String url) {
    	return !url.equals("");
    }
    
    /**
     * 前回のURLまでたどり着いた場合は終了する
     * @param url
     * @return
     */
    private boolean isPriviousFetchURL(String url, String lastURL) {
    	return !url.equals(lastURL);
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
    
    /**
     * 前回読んだurlを読み込む
     * @param url
     * @return
     */
    private String readURL(String path, String init) {
    	File file = new File(path + "/" + init.replaceAll("^.*/([^/]+)/[^/]*$", "$1"));
    	String lastURL = "";
    	try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			lastURL = br.readLine();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			// ファイルがない場合は、空文字列を返却する。
			lastURL = "";
		} catch (IOException e) {
			e.printStackTrace();
			// ファイルがない場合は、空文字列を返却する。
			lastURL = "";
		}
    	System.out.println("===== Last URL is " + lastURL + " =====");
    	return lastURL;
    }
    
    /**
     * urlを書き込む
     * @param url
     * @return
     */
    private void writeURL(String url, String path, String init) {
    	File file = new File(path + "/" + init.replaceAll("^.*/([^/]+)/[^/]*$", "$1"));
    	PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(url);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			// 失敗したらなにもしない
		}
    	System.out.println("===== " + url + " is writed!! =====");
    }
}
