/*
* DreamNarae (Root) Open Source
* Colorful Harmony Team- Angeloid Team, inc
* Copyright 2009-2013 Angeloid Team, inc Licensed under the Apache License
* Version 2.0 (the "License"); you may not use this file except in compliance with the License. 
*
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software distributed 
*
* Under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*
* See the License for the specific language governing permissions and limitations under the License.
*/
package angeloid.dreamnarae;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.content.Context;

public class Prev_ZipTest {
	private static String BASE_FOLDER_prev;

	public static Context localContext_prev;
	public static boolean isDownloadInProgress_prev;
	public static boolean isLowOnMemory_prev;

	public static void startUnzipping_prev(Context cnxt) {
		localContext_prev = cnxt;
		Prev_ZipTest.BASE_FOLDER_prev = Prev_ZipTest.localContext_prev.getFilesDir().getPath();
		Prev_ZipTest.isLowOnMemory_prev = false;
		new UnzipThread_prev().start();
	}

	private static class UnzipThread_prev extends Thread {
		@Override
		public void run() {
			Prev_ZipTest.isDownloadInProgress_prev = true;
			URLConnection urlConnection_prev;
			try {

				URL finalUrl_prev = new URL(
						"http://dreamnaraepatchdownload.googlecode.com/files/Prev%201.4%202013.02.08.zip");
				urlConnection_prev = finalUrl_prev.openConnection();

				ZipInputStream zipInputStream_prev = new ZipInputStream(
						urlConnection_prev.getInputStream());

				for (ZipEntry zipEntry_prev = zipInputStream_prev.getNextEntry(); zipEntry_prev != null; zipEntry_prev = zipInputStream_prev
						.getNextEntry()) {
				

					String innerFileName = BASE_FOLDER_prev + File.separator
							+ zipEntry_prev.getName();
					File innerFile_prev = new File(innerFileName);

					if (innerFile_prev.exists()) {
						innerFile_prev.delete();
					}

					if (zipEntry_prev.isDirectory()) {
						innerFile_prev.mkdirs();
					} else {
						FileOutputStream outputStream_prev = new FileOutputStream(
								innerFileName);
						final int BUFFER_SIZE_prev = 2048;

						BufferedOutputStream bufferedOutputStream_prev = new BufferedOutputStream(
								outputStream_prev, BUFFER_SIZE_prev);

						int count = 0;
						byte[] buffer = new byte[BUFFER_SIZE_prev];
						while ((count = zipInputStream_prev.read(buffer, 0,
								BUFFER_SIZE_prev)) != -1) {
							bufferedOutputStream_prev.write(buffer, 0, count);
						}

						bufferedOutputStream_prev.flush();
						bufferedOutputStream_prev.close();
					}

					zipInputStream_prev.closeEntry();
				}

				zipInputStream_prev.close();
			} catch (IOException e) {

				if (e.getMessage().equalsIgnoreCase("No space left on device")) {
					Prev_ZipTest.isLowOnMemory_prev = true;
				}
				e.printStackTrace();
			}
			Prev_ZipTest.isDownloadInProgress_prev = false;
		}
	};
}