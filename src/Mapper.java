import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Mapper implements Runnable{
	String folderName;
	String hostName;
	int portId;
	public Mapper(String hostName,int portId,String folderName ) {
		this.hostName = hostName;
		this.portId = portId;
		this.folderName = folderName;
	}
	@Override
	public void run() {
		//long numberOfImages = Files.list(Paths.get(folderName)).count();
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    
		
		try {	
			Image image = ImageIO.read(file);
			
			BufferedImage bimg= (BufferedImage) image;


		
			Socket client = new Socket(this.hostName, this.portId);
			
			ImageIO.write(bimg,"PNG",client.getOutputStream());
            client.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
