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

public class Miracle_ZipTest {
	private static String BASE_FOLDER_miracle;

	public static Context localContext_miracle;
	public static boolean isDownloadInProgress_miracle;
	public static boolean isLowOnMemory_miracle;

	public static void startUnzipping_miracle(Context cnxt) {
		localContext_miracle = cnxt;
		Miracle_ZipTest.BASE_FOLDER_miracle = Miracle_ZipTest.localContext_miracle.getFilesDir().getPath();
		Miracle_ZipTest.isLowOnMemory_miracle = false;
		new UnzipThread_miracle().start();
	}

	private static class UnzipThread_miracle extends Thread {
		@Override
		public void run() {
			Miracle_ZipTest.isDownloadInProgress_miracle = true;
			URLConnection urlConnection_miracle;
			try {

				URL finalUrl_miracle = new URL(
						"http://dreamnaraepatchdownload.googlecode.com/files/Miracle%202.2%2B%202013.02.08.zip");
				urlConnection_miracle = finalUrl_miracle.openConnection();

				ZipInputStream zipInputStream_miracle = new ZipInputStream(
						urlConnection_miracle.getInputStream());

				for (ZipEntry zipEntry_miracle = zipInputStream_miracle.getNextEntry(); zipEntry_miracle != null; zipEntry_miracle = zipInputStream_miracle
						.getNextEntry()) {
				

					String innerFileName = BASE_FOLDER_miracle + File.separator
							+ zipEntry_miracle.getName();
					File innerFile_miracle = new File(innerFileName);

					if (innerFile_miracle.exists()) {
						innerFile_miracle.delete();
					}

					if (zipEntry_miracle.isDirectory()) {
						innerFile_miracle.mkdirs();
					} else {
						FileOutputStream outputStream_miracle = new FileOutputStream(
								innerFileName);
						final int BUFFER_SIZE_miracle = 2048;

						BufferedOutputStream bufferedOutputStream_miracle = new BufferedOutputStream(
								outputStream_miracle, BUFFER_SIZE_miracle);

						int count = 0;
						byte[] buffer = new byte[BUFFER_SIZE_miracle];
						while ((count = zipInputStream_miracle.read(buffer, 0,
								BUFFER_SIZE_miracle)) != -1) {
							bufferedOutputStream_miracle.write(buffer, 0, count);
						}

						bufferedOutputStream_miracle.flush();
						bufferedOutputStream_miracle.close();
					}

					zipInputStream_miracle.closeEntry();
				}

				zipInputStream_miracle.close();
			} catch (IOException e) {

				if (e.getMessage().equalsIgnoreCase("No space left on device")) {
					Miracle_ZipTest.isLowOnMemory_miracle = true;
				}
				e.printStackTrace();
			}
			Miracle_ZipTest.isDownloadInProgress_miracle = false;
		}
	};
}