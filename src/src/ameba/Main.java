package src.ameba;

public class Main {
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1];
		// �L�����B�f�t�H���g�͏\���傫���l�B
		Integer num =  10000000;
		if(args.length > 2) {
			// �����w�肳�ꂽ�ꍇ�́A�����ݒ�
			num = Integer.parseInt(args[2]);			
		}
		SaveImages.saveImages(url, path, num);
	}
}
