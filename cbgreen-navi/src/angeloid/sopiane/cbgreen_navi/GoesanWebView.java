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

public class GoesanWebView extends Activity {

	// 괴산 카테고리 보여주는 웹뷰
	
	// Pubilc
		WebView goesan;
		private ProgressBar goesan_progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goesan_webview);
		goesan = (WebView) findViewById(R.id.goesan_web);
		goesan.setWebViewClient(new myWebClient());
		goesan_progress = (ProgressBar) findViewById(R.id.goesan_pb);

		// Set Settings... Plugin ..etc
		// some warning is wrong, just go on away.
		goesan.getSettings().setPluginsEnabled(true);
		goesan.getSettings().setJavaScriptEnabled(true);
		goesan.getSettings().setBuiltInZoomControls(true);
		goesan.getSettings().setLoadWithOverviewMode(true);
		goesan.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		goesan.getSettings().setSupportMultipleWindows(true);
		goesan.getSettings().setSupportZoom(true);
		goesan.getSettings().setLoadsImagesAutomatically(true);
		goesan.getSettings().setUseWideViewPort(true);
		goesan.getSettings().setLayoutAlgorithm(
				WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		// Load Url
		if (savedInstanceState == null) {
			// 괴산 카테고리 접속
			goesan.loadUrl("http://green-navi.tistory.com/m/post/list?categoryId=525715");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the state of the WebView
		goesan.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore the state of the WebView
		goesan.restoreState(savedInstanceState);
	}

	public class myWebClient extends WebViewClient

	{
		public void onProgressChanged(WebView view, int newProgress) {
			goesan_progress.setProgress(newProgress);

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
			goesan_progress.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			goesan_progress.setVisibility(View.VISIBLE);

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
			if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT) && goesan.canGoBack()) {
				goesan.goBack();
				return true;
			} else if ((keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
					&& goesan.canGoForward()) {
				goesan.goForward();
				return true;
			}
			return false;
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// webpage error
			super.onReceivedError(view, errorCode, description, failingUrl);
			Toast.makeText(GoesanWebView.this, "로딩오류" + description,
					Toast.LENGTH_SHORT).show();
		}

	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && goesan.canGoBack()) {
			goesan.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}