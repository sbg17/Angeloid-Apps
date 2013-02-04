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

public class SPiCa_ZipTest {
	private static String BASE_FOLDER_spica;

	public static Context localContext_spica;
	public static boolean isDownloadInProgress_spica;
	public static boolean isLowOnMemory_spica;

	public static void startUnzipping_spica(Context cnxt) {
		localContext_spica = cnxt;
		SPiCa_ZipTest.BASE_FOLDER_spica = SPiCa_ZipTest.localContext_spica
				.getFilesDir().getPath();
		SPiCa_ZipTest.isLowOnMemory_spica = false;
		new UnzipThread_spica().start();
	}

	private static class UnzipThread_spica extends Thread {
		@Override
		public void run() {
			SPiCa_ZipTest.isDownloadInProgress_spica = true;
			URLConnection urlConnection_spica;
			try {

				URL finalUrl_spica = new URL(
						"http://dreamnaraepatchdownload.googlecode.com/files/%5BC%5DSPiCa%20Source%204.8.zip");
				urlConnection_spica = finalUrl_spica.openConnection();

				ZipInputStream zipInputStream_spica = new ZipInputStream(
						urlConnection_spica.getInputStream());

				for (ZipEntry zipEntry_spica = zipInputStream_spica
						.getNextEntry(); zipEntry_spica != null; zipEntry_spica = zipInputStream_spica
						.getNextEntry()) {

					String innerFileName = BASE_FOLDER_spica + File.separator
							+ zipEntry_spica.getName();
					File innerFile_spica = new File(innerFileName);

					if (innerFile_spica.exists()) {
						innerFile_spica.delete();
					}

					if (zipEntry_spica.isDirectory()) {
						innerFile_spica.mkdirs();
					} else {
						FileOutputStream outputStream_spica = new FileOutputStream(
								innerFileName);
						final int BUFFER_SIZE_spica = 2048;

						BufferedOutputStream bufferedOutputStream_spica = new BufferedOutputStream(
								outputStream_spica, BUFFER_SIZE_spica);

						int count = 0;
						byte[] buffer = new byte[BUFFER_SIZE_spica];
						while ((count = zipInputStream_spica.read(buffer, 0,
								BUFFER_SIZE_spica)) != -1) {
							bufferedOutputStream_spica.write(buffer, 0, count);
						}

						bufferedOutputStream_spica.flush();
						bufferedOutputStream_spica.close();
					}

					zipInputStream_spica.closeEntry();
				}

				zipInputStream_spica.close();
			} catch (IOException e) {
				if (e.getMessage().equalsIgnoreCase("No space left on device")) {
					SPiCa_ZipTest.isLowOnMemory_spica = true;
				}
				e.printStackTrace();
			}
			SPiCa_ZipTest.isDownloadInProgress_spica = false;
		}
	};
}