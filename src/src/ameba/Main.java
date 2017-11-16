package src.ameba;

public class Main {
	/**
	 * 実行メソッド
	 * @param args
	 * 第1引数: URL（必須）
	 * 第2引数: 画像の保存path（必須）
	 * 第3引数: 保存記事数（任意）
	 */
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1];
		// 記事数。デフォルトは十分大きい値。
		Integer num =  10000000;
		if(args.length > 2) {
			// 引数指定された場合は、それを設定
			num = Integer.parseInt(args[2]);			
		}
		SaveImages.saveImages(url, path, num);
	}
}
