package src.ameba;

public class Main {
	public static void main(String args[]) {
		String url = args[0];
		String path = args[1];
		String str = "https://stat.ameba.jp/user_images/20171113/22/angerme-ss-shin/36/a7/j/o0480036014069909018.jpg?caw=800\" border=\"0\" width=\"270\" height=\"202\" alt=\"{F44DF270-5430-45EA-8435-A1B1052CB382}\"></a></div></div><div>団長室田さんからのムチャぶりで</div><div>竹内さんへのバースデーソングを歌ったんです笑</div><div>即興ソングは恥じらいを捨ててやらなきゃいけないですね?</div><div>次ある時は笑いを空へ飛ばして頑張ります！！</div><div><br></div><div>団長室田さんはほんとに凄いな?と思いました！！</div><div><br></div><div><br></div><div><br></div><div><br></div><div>お知らせ?</div><div>1月2日から始まる</div><div>Hello! Project 20th Anniversary!! Hello! Project 2018 WINTER?～PERFECT SCORE～ ～FULL SCORE～</div><div>のブログ先行受付中です！！</div><div><br></div><div>受付期間は11/16( 木）23：59までです！！</div><div><br></div><div>1/3(水)18：30?1/14(日)中野サンプラザ・大阪・名古屋・福岡・仙台・札幌・広島の公演が受付対象です！！</div><div><br></div><div><a href=\"http://eplus.jp/blog/helloproject2018/\">http://eplus.jp/blog/helloproject2018/</a><br></div><div><br></div><div><br></div><div>2017/12.31~2018/1.1</div><div>「Hello! Project 20th Anniversary!! Hello! Project COUNTDOWN PARTY 2017 ～ GOOD BYE &amp; HELLO ! ～」</div><div>私は2部も参加します?！</div><div>いぇーーーーい！！</div><div>年越しハローーーー！！って嬉しいびっくり！！！</div><div>18歳やき上國料さんも！！</div><div><br></div><div>楽しい年越しや??</div><div><br></div><div>????????????????????????????????????????????????????????????</div><div>今日アンジュルムみんなで撮影やったがやけど</div><div>面白くて楽しくて自然と腹筋が鍛えれました笑</div><div><br></div><div>みなさんといると楽しいです！</div><div>笑い貯めしたい?</div><div><br></div><div><div id=\"110E0207-1DC7-4A47-91AB-48486D85DD74\" style=\"text-align: left;\"><a id=\"i14069909024\" class=\"detailOn\" href=\"//ameblo.jp/angerme-ss-shin/image-12328153556-14069909024.html\">";
		// 記事数。デフォルトは十分大きい値。
		Integer num =  10000000;
		if(args.length > 2) {
			// 引数指定された場合は、それを設定
			num = Integer.parseInt(args[2]);			
		}
		SaveImages.saveImages(url, path, num);
	}
}
