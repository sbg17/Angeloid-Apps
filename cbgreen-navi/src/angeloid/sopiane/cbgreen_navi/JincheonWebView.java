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

public class JincheonWebView extends Activity {

	// 진천 카테고리 보여주는 웹뷰
	
	// Pubilc
		WebView jincheon;
		private ProgressBar jincheon_progress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jincheon_webview);
		jincheon = (WebView) findViewById(R.id.jincheon_web);
		jincheon.setWebViewClient(new myWebClient());
		jincheon_progress = (ProgressBar) findViewById(R.id.jincheon_pb);

		// Set Settings... Plugin ..etc
		// some warning is wrong, just go on away.
		jincheon.getSettings().setPluginsEnabled(true);
		jincheon.getSettings().setJavaScriptEnabled(true);
		jincheon.getSettings().setBuiltInZoomControls(true);
		jincheon.getSettings().setLoadWithOverviewMode(true);
		jincheon.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
		jincheon.getSettings().setSupportMultipleWindows(true);
		jincheon.getSettings().setSupportZoom(true);
		jincheon.getSettings().setLoadsImagesAutomatically(true);
		jincheon.getSettings().setUseWideViewPort(true);
		jincheon.getSettings().setLayoutAlgorithm(
				WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

		// Load Url
		if (savedInstanceState == null) {
			// 진천 카테고리 접속
			jincheon.loadUrl("http://green-navi.tistory.com/m/post/list?categoryId=525722");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// Save the state of the WebView
		jincheon.saveState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		// Restore the state of the WebView
		jincheon.restoreState(savedInstanceState);
	}

	public class myWebClient extends WebViewClient

	{
		public void onProgressChanged(WebView view, int newProgress) {
			jincheon_progress.setProgress(newProgress);

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
			jincheon_progress.setVisibility(View.INVISIBLE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			jincheon_progress.setVisibility(View.VISIBLE);

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
			if ((keyCode == KeyEvent.KEYCODE_DPAD_LEFT) && jincheon.canGoBack()) {
				jincheon.goBack();
				return true;
			} else if ((keyCode == KeyEvent.KEYCODE_DPAD_RIGHT)
					&& jincheon.canGoForward()) {
				jincheon.goForward();
				return true;
			}
			return false;
		}

		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			// webpage error
			super.onReceivedError(view, errorCode, description, failingUrl);
			Toast.makeText(JincheonWebView.this, "로딩오류" + description,
					Toast.LENGTH_SHORT).show();
		}

	}

	// To handle "Back" key press event for WebView to go back to previous
	// screen.
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && jincheon.canGoBack()) {
			jincheon.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}