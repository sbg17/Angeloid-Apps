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

public class CheongwonWebView extends Activity {

	// 청원 카테고리 보여주는 웹뷰
	
	// Pubilc
		WebView cheongwon;
		private ProgressBar cheongwon_progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cheongwon_webview);
		cheongwon = (WebView) findViewById(R.id.cheongwon_web);
		cheongwon.setWebViewClient(new myWebClient());
		cheongwon_progress = (ProgressBar) findViewById(R.id.cheongwon_pb);

		// Set Settings... Plugin ..etc
		// some warning is wrong, just go on away.
		cheongwon.getSettings().setPluginsEnabled(true);
		cheongwon.getSettings().setJavaScriptEnabled(true);
		cheongwon.getSettings().setBuiltInZoomControls(true);
		cheongwon.getSettings().setLoadWithOverviewMode(true);
		cheongwon.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		cheongwon.getSettings().setSupportMultipleWindows(true);
		cheongwon.getSettings().setSupportZoom(true);
		cheongwon.getSettings().setLoadsImagesAutomatically(true);
		cheongwon.getSettings().setUseWideViewPort(true);
		cheongwon.getSettings().setLayoutAlgorithm(
				WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		// Load Url
		if (savedInstanceState == null) {
			// 청원 카테고리 접속
			cheongwon.loadUrl("http://green-navi.tistory.com/m/post/list?categoryId=525723");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the state of the WebView
		cheongwon.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore the state of the WebView
		cheongwon.restoreState(savedInstanceState);
	}

	public class myWebClient extends WebViewClient

	{
		public void onProgressChanged(WebView view, int newProgress) {
			cheongwon_progress.setProgress(newProgress);

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
			cheongwon_progress.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			cheongwon_progress.setVisibility(View.VISIBLE);

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
			if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT) && cheongwon.canGoBack()) {
				cheongwon.goBack();
				return true;
			} else if ((keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
					&& cheongwon.canGoForward()) {
				cheongwon.goForward();
				return true;
			}
			return false;
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// webpage error
			super.onReceivedError(view, errorCode, description, failingUrl);
			Toast.makeText(CheongwonWebView.this, "로딩오류" + description,
					Toast.LENGTH_SHORT).show();
		}

	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && cheongwon.canGoBack()) {
			cheongwon.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}