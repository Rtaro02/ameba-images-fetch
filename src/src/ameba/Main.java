package src.ameba;

public class Main {
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1];
		String str = "https://stat.ameba.jp/user_images/20171113/22/angerme-ss-shin/36/a7/j/o0480036014069909018.jpg?caw=800\" border=\"0\" width=\"270\" height=\"202\" alt=\"{F44DF270-5430-45EA-8435-A1B1052CB382}\"></a></div></div><div>�c�����c���񂩂�̃��`���Ԃ��</div><div>�|������ւ̃o�[�X�f�[�\���O���̂�����ł���</div><div>�����\���O�͒p���炢���̂ĂĂ��Ȃ��Ⴂ���Ȃ��ł���?</div><div>�����鎞�͏΂�����֔�΂��Ċ撣��܂��I�I</div><div><br></div><div>�c�����c����͂ق�Ƃɐ�����?�Ǝv���܂����I�I</div><div><br></div><div><br></div><div><br></div><div><br></div><div>���m�点?</div><div>1��2������n�܂�</div><div>Hello! Project 20th Anniversary!! Hello! Project 2018 WINTER?�`PERFECT SCORE�` �`FULL SCORE�`</div><div>�̃u���O��s��t���ł��I�I</div><div><br></div><div>��t���Ԃ�11/16( �؁j23�F59�܂łł��I�I</div><div><br></div><div>1/3(��)18�F30?1/14(��)����T���v���U�E���E���É��E�����E���E�D�y�E�L���̌�������t�Ώۂł��I�I</div><div><br></div><div><a href=\"http://eplus.jp/blog/helloproject2018/\">http://eplus.jp/blog/helloproject2018/</a><br></div><div><br></div><div><br></div><div>2017/12.31~2018/1.1</div><div>�uHello! Project 20th Anniversary!! Hello! Project COUNTDOWN PARTY 2017 �` GOOD BYE &amp; HELLO ! �`�v</div><div>����2�����Q�����܂�?�I</div><div>�����[�[�[�[���I�I</div><div>�N�z���n���[�[�[�[�I�I���Ċ������т�����I�I�I</div><div>18�΂₫�㚠��������I�I</div><div><br></div><div>�y�����N�z����??</div><div><br></div><div>????????????????????????????????????????????????????????????</div><div>�����A���W�������݂�ȂŎB�e��������₯��</div><div>�ʔ����Ċy�����Ď��R�ƕ��؂��b����܂�����</div><div><br></div><div>�݂Ȃ���Ƃ���Ɗy�����ł��I</div><div>�΂����߂�����?</div><div><br></div><div><div id=\"110E0207-1DC7-4A47-91AB-48486D85DD74\" style=\"text-align: left;\"><a id=\"i14069909024\" class=\"detailOn\" href=\"//ameblo.jp/angerme-ss-shin/image-12328153556-14069909024.html\">";
		// �L�����B�f�t�H���g�͏\���傫���l�B
		Integer num =  10000000;
		if(args.length > 2) {
			// �����w�肳�ꂽ�ꍇ�́A�����ݒ�
			num = Integer.parseInt(args[2]);			
		}
		SaveImages.saveImages(url, path, num);
	}
}
