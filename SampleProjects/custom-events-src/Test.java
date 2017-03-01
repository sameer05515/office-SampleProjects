import java.io.File;
import java.io.FileFilter;

public class Test {

	public static void main(String[] args) {

		File f = new File(
				"C:/CUSTOM INSTALLATIONS/apache-tomcat-8.0.35-windows-x64/apache-tomcat-8.0.35/webapps/AngularJS-animations-book-master");
		File[] children=f.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				// TODO Auto-generated method stub
				if(pathname==null){
					return false;
				}
				if(pathname.isDirectory()){
					return true;
				}
				String name=pathname.getName();
				if(name!=null&&name.toLowerCase().endsWith(".html")){
					return true;
				}
				return false;
			}
		});
		
		for(File child:children){
			System.out.println("name"+child.getName());
			System.out.println("parent"+child.getAbsolutePath());
		}

	}
	
	

}
