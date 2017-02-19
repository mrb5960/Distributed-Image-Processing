// Author: Mandar Badave

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Reducer{
	public static void main(String [] args){
		new Thread(new ReduceServer(5656)).start();;
	}
}

class ReduceServer implements Runnable{
	ServerSocket reducerSocket ;
	public ReduceServer(int port) {
		try {
			this.reducerSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		//	File output = new File("E://DSProject");
		String finalFolder ="C://DSProject";
		Path outputPath = Paths.get("C://DSProject");
		if(!Files.exists(outputPath)){
			try {
				Files.createDirectory(outputPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int counter =0;
		while(true){
			try {
				//		System.out.println("new socket wait");
				Socket tempSocket = reducerSocket.accept();
				BufferedImage bimg = ImageIO.read(ImageIO.createImageInputStream(tempSocket.getInputStream()));
				System.out.println("read image");
				String fileName = finalFolder+"//"+(++counter)+".png";
				System.out.println("Received : " +fileName);	
				ImageIO.write(bimg,"png",new File(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

} 