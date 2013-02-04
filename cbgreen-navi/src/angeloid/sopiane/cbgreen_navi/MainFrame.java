package angeloid.sopiane.cbgreen_navi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainFrame extends Activity {

	// ���� �������� ǥ���ϴ� ��Ƽ��Ƽ
	// �� ��ư���� �̵���
	// ����
	
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
		
		// ��ư ���̾ƿ� ����
		
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

		// ���� ������ ����
		goesan_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, GoesanWebView.class));
			}
		}); // ������ ����

		// �ܾ� ������ ����
		danyang_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, DanyangWebView.class));
			}
		}); // ������ ����

		// ��õ ������ ����
		okcheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, OkcheonWebView.class));
			}
		}); // ������ ����

		// ��õ ������ ����
		jincheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, JincheonWebView.class));
			}
		}); // ������ ����

		// ���������� ����
		boeun_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, BoeunWebView.class));
			}
		}); // ������ ����

		// ���� ������ ����
		eumseong_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, EumseongWebView.class));
			}
		}); // ������ ����

		// ��õ ������ ����
		jecheon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, JecheonWebView.class));
			}
		}); // ������ ����

		// ���� ������ ����
		chungju_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, ChungjuWebView.class));
			}
		}); // ������ ����

		// û�� ������ ����
		cheongwon_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, CheongwonWebView.class));
			}
		}); // ������ ����

		// û�� ������ ����
		cheongju_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, CheongjuWebView.class));
			}
		}); // ������ ����

		// ���� ������ ����
		youngdong_b.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(MainFrame.this, YoungdongWebView.class));
			}
		}); // ������ ����
		
		

	}

}
