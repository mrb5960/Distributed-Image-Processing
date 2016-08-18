import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class DataNodeReceiver implements Runnable{
	private ServerSocket dnSocket;
	Socket server;
	public DataNodeReceiver(int port) {
		
		try {
			// creates a server socket
			this.dnSocket = new ServerSocket(port);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		
		while(true)
		{ 
			try
			{
				System.out.println("block");
				server = dnSocket.accept();
				// reads the images sent from the client
				BufferedImage IMG = ImageIO.read(ImageIO.createImageInputStream(server.getInputStream()));

				// converts the image to gray scale 
				this.convert(IMG);

				// creating a client socket to send the image to the reducer
				Socket sendForRed = new Socket("129.21.80.78", 5656);
				// writes the image to the output stream
				ImageIO.write(IMG,"PNG",sendForRed.getOutputStream());
				sendForRed.close();
				System.out.println("done");                
			}
			catch(SocketTimeoutException st)
			{
				System.out.println("time out...");
				break;
			}
			catch(IOException e)
			{
				e.printStackTrace();
				break;
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
	}

	// method that converts normal image to a gray scale image
	void convert(BufferedImage IMG) {
		int w, h, resultant;
		Color c1, c2;
		w = IMG.getWidth();
		h = IMG.getHeight();
		// traverses through every pixel of the image
		for(int i = 0; i < h; i++){
			for(int j = 0; j < w; j++){
				// gets the pixel at the value of w and h
				// value of the pixel is passed as an input to the the Color 
				c1 = new Color(IMG.getRGB(j, i));
				// red, blue and green colors are extracted separately and converted into gray scale
				resultant = (int)(c1.getRed() * 0.21) + (int)(c1.getGreen() * 0.72) + (int)(c1.getBlue() * 0.07);
				c2 = new Color(resultant, resultant, resultant);
				// the modified color is applied to the pixel
				IMG.setRGB(j, i, c2.getRGB());
			}
		}
	}

}
