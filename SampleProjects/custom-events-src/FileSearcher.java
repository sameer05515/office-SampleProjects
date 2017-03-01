import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

	public static void main(String[] args) {
		String res = new FileSearcher()
				.startSearch("C:/CUSTOM INSTALLATIONS/apache-tomcat-8.0.35-windows-x64/apache-tomcat-8.0.35/webapps/AngularJS-animations-book-master");

		System.out.println(res);

	}

	private static String base = "C:/CUSTOM INSTALLATIONS/apache-tomcat-8.0.35-windows-x64/apache-tomcat-8.0.35/webapps/AngularJS-animations-book-master";

	List<String> fileList = new ArrayList<String>();

	public String startSearch(String fileName) {
		StringBuffer sb = new StringBuffer();
		if (fileName != null) {
			fileList = new ArrayList<String>();
			serarch(fileList, fileName);

			for (int i = 0; i < fileList.size(); i++) {
				if (i == 0) {
					sb.append("[");
				}

				sb.append(fileList.get(i));

				if (i == fileList.size() - 1) {
					sb.append("]");
				} else {
					sb.append(" , \n");
				}
			}
		}

		return sb.toString();

	}

	private void serarch(List<String> fileList, String fileName) {

		File f = new File(fileName);
		File[] children = f.listFiles(new MyFileFilter());
		for (File child : children) {
			if (child.isDirectory()) {
				serarch(fileList, child.getAbsolutePath());
			}
			if (child.isFile()) {

				fileList.add(child.getAbsolutePath().trim().replace("\\", "/")
						.substring(base.length() + 1));
			}
		}

	}

	private static class MyFileFilter implements FileFilter {

		@Override
		public boolean accept(File file) {

			if (file == null) {
				return false;
			}
			if (!file.exists()) {
				return false;
			}
			if (file.isDirectory()) {
				return true;
			}

			String fileNameee = (file.getName() != null) ? file.getName()
					.trim().toLowerCase() : null;

			String[] allowedExtentions = { ".html", ".htm" };

			for (String exts : allowedExtentions) {

				if (fileNameee != null && fileNameee.endsWith(exts)) {
					return true;
				}
			}

			return false;
		}

	}

}
