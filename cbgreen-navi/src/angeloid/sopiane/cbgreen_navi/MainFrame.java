package angeloid.sopiane.cbgreen_navi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainFrame extends Activity {

	// 메인 프레임을 표시하는 액티비티
	// 각 버튼으로 이동함
	// 선언
	
	Button goesan_b;
	Button danyang_b;
	Button okcheon_b;
	Button jincheon_b;
	Button boeun_b;
	Button eumseong_b;
	Button jecheon_b;
	Button chungju_b;
	Button cheongwon_b;
	Button cheongju_b;
	Button youngdong_b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainframe_map);
		
		// 버튼 레이아웃 연결
		
		goesan_b = (Button) findViewById(R.id.goesan_b);
		danyang_b = (Button) findViewById(R.id.danyang_b);
		okcheon_b = (Button) findViewById(R.id.okcheon_b);
		jincheon_b = (Button) findViewById(R.id.jincheon_b);
		boeun_b = (Button) findViewById(R.id.boeun_b);
		eumseong_b = (Button) findViewById(R.id.eumseong_b);
		jecheon_b = (Button) findViewById(R.id.jecheon_b);
		chungju_b = (Button) findViewById(R.id.chungju_b);
		cheongwon_b = (Button) findViewById(R.id.cheongwon_b);
		cheongju_b = (Button) findViewById(R.id.cheongju_b);
		youngdong_b = (Button) findViewById(R.id.youngdong_b);

		// 괴산 리스너 시작
		goesan_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, GoesanWebView.class));
			}
		}); // 리스너 종료

		// 단양 리스너 시작
		danyang_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, DanyangWebView.class));
			}
		}); // 리스너 종료

		// 옥천 리스너 시작
		okcheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, OkcheonWebView.class));
			}
		}); // 리스너 종료

		// 진천 리스너 시작
		jincheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, JincheonWebView.class));
			}
		}); // 리스너 종료

		// 보은리스너 시작
		boeun_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, BoeunWebView.class));
			}
		}); // 리스너 종료

		// 음성 리스너 시작
		eumseong_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, EumseongWebView.class));
			}
		}); // 리스너 종료

		// 제천 리스너 시작
		jecheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, JecheonWebView.class));
			}
		}); // 리스너 종료

		// 충주 리스너 시작
		chungju_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, ChungjuWebView.class));
			}
		}); // 리스너 종료

		// 청원 리스너 시작
		cheongwon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, CheongwonWebView.class));
			}
		}); // 리스너 종료

		// 청주 리스너 시작
		cheongju_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, CheongjuWebView.class));
			}
		}); // 리스너 종료

		// 영동 리스너 시작
		youngdong_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, YoungdongWebView.class));
			}
		}); // 리스너 종료
		
		

	}

}
