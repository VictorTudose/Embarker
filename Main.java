
package tema1;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException
	{
		Embark EM = new Embark("input.txt","output.txt");
		EM.exec_File();
	}
}