package angeloid.sopiane.cbgreen_navi;

import android.app.Activity;

import android.graphics.Bitmap;

import android.os.Bundle;
import android.os.Message;

import android.view.KeyEvent;
import android.view.View;

import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.ProgressBar;
import android.widget.Toast;

public class ChungjuWebView extends Activity {

	// û�� ī�װ� �����ִ� ����
	
	// Pubilc
		WebView chungju;
		private ProgressBar chungju_progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chungju_webview);
		chungju = (WebView) findViewById(R.id.chungju_web);
		chungju.setWebViewClient(new myWebClient());
		chungju_progress = (ProgressBar) findViewById(R.id.chungju_pb);

		// Set Settings... Plugin ..etc
		// some warning is wrong, just go on away.
		chungju.getSettings().setPluginsEnabled(true);
		chungju.getSettings().setJavaScriptEnabled(true);
		chungju.getSettings().setBuiltInZoomControls(true);
		chungju.getSettings().setLoadWithOverviewMode(true);
		chungju.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		chungju.getSettings().setSupportMultipleWindows(true);
		chungju.getSettings().setSupportZoom(true);
		chungju.getSettings().setLoadsImagesAutomatically(true);
		chungju.getSettings().setUseWideViewPort(true);
		chungju.getSettings().setLayoutAlgorithm(
				WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		// Load Url
		if (savedInstanceState == null) {
			// ���� ī�װ� ����
			chungju.loadUrl("http://green-navi.tistory.com/m/post/list?categoryId=522424");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the state of the WebView
		chungju.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore the state of the WebView
		chungju.restoreState(savedInstanceState);
	}

	public class myWebClient extends WebViewClient

	{
		public void onProgressChanged(WebView view, int newProgress) {
			chungju_progress.setProgress(newProgress);

		}

		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;

		}

		@Override
		public void onLoadResource(WebView view, String url) {

			super.onLoadResource(view, url);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			chungju_progress.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			chungju_progress.setVisibility(View.VISIBLE);

		}

		@Override
		public void doUpdateVisitedHistory(WebView view, String url,
				boolean isReload) {
			super.doUpdateVisitedHistory(view, url, isReload);
		}

		@Override
		public void onFormResubmission(WebView view, Message dontResend,
				Message resend) {
			super.onFormResubmission(view, dontResend, resend);
		}

		@Override
		public void onReceivedHttpAuthRequest(WebView view,
				HttpAuthHandler handler, String host, String realm) {
			super.onReceivedHttpAuthRequest(view, handler, host, realm);
		}

		@Override
		public void onScaleChanged(WebView view, float oldScale, float newScale) {
			super.onScaleChanged(view, oldScale, newScale);
		}

		@Override
		public void onTooManyRedirects(WebView view, Message cancelMsg,
				Message continueMsg) {
			super.onTooManyRedirects(view, cancelMsg, continueMsg);
		}

		@Override
		public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
			super.onUnhandledKeyEvent(view, event);
		}

		@Override
		public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
			int keyCode = event.getKeyCode();
			if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT) && chungju.canGoBack()) {
				chungju.goBack();
				return true;
			} else if ((keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
					&& chungju.canGoForward()) {
				chungju.goForward();
				return true;
			}
			return false;
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// webpage error
			super.onReceivedError(view, errorCode, description, failingUrl);
			Toast.makeText(ChungjuWebView.this, "�ε�����" + description,
					Toast.LENGTH_SHORT).show();
		}

	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && chungju.canGoBack()) {
			chungju.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}