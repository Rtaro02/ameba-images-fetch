package src.ameba;

public class Main {
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1]; 
		Integer num =  Integer.parseInt(args[2]);
		SaveImages.saveImages(url, path, num);
	}
}
