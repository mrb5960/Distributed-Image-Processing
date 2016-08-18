import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import static java.nio.file.StandardCopyOption.*;

public class NameNode {

	public static void main(String []args) throws IOException{
		PackUnpack p = new PackUnpack("E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Images");
		p.divide();
		
		File folder = new File("E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Container");
		File[] listOfFiles = folder.listFiles();
		String [] nodeIP = new String[9];
		
		nodeIP[0]="glados.cs.rit.edu";
		nodeIP[1]="kansas.cs.rit.edu";
		nodeIP[2]="rhea.cs.rit.edu";
		nodeIP[3]="comet.cs.rit.edu";
		nodeIP[4]="arizona.cs.rit.edu";
		nodeIP[5]="newyork.cs.rit.edu";
		nodeIP[6]="delaware.cs.rit.edu";
		nodeIP[7]="idaho.cs.rit.edu";
		nodeIP[8]="buddy.cs.rit.edu";
		
		String folderStr = "E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Container\\";//block1";
	//	for (File file : listOfFiles) {
		File file;// = listOfFiles[0]
			int i=0;
			i++;
			int ipChoose = i%9;
		Long start=	System.currentTimeMillis();
		int portId = 6000;
			 Mapper map1 ;
			 
			 file = listOfFiles[0];
		map1= new Mapper(nodeIP[0], portId+(0),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		file = listOfFiles[1];
		map1= new Mapper(nodeIP[1], portId+(1),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		file = listOfFiles[2];
		map1= new Mapper(nodeIP[2], portId+(2),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=3;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=4;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=5;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=6;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=7;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
		ipChoose=8;
		file = listOfFiles[ipChoose];
		map1= new Mapper(nodeIP[ipChoose], portId+(ipChoose),folderStr+ file.getName());//folderStr);//
		new Thread(map1).start();
	//	}
		Long stop =System.currentTimeMillis();
		// System.out.println("Time taken ="+(stop-start));
	}
	
}

/*
 * Class to divide content of sindle source into differnt blocks
 */
 class PackUnpack{
	
	/*
	 * Create 16MB blocks 
	 */
 	final int blockSize = 1024*1024*16; 
	String inputFile;
	String zipFile;
	
	public PackUnpack(String fileName) {
		this.inputFile = fileName;
	}
	
	/*
	 * function to divide and return number of blocks creaeted
	 */
	long divide() throws IOException{
		
		long numberOfImages = Files.list(Paths.get(inputFile)).count();
		
		java.io.File temp = new java.io.File("E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Images\\1.png");
		long imageSize=temp.length();
		
		long totalSize = imageSize * numberOfImages;
		System.out.println("Total size of images : "+totalSize);
		long numOfBlocks = totalSize/blockSize;
		System.out.println("Number of blocks : "+numOfBlocks);
		int blockCount = (int) (numberOfImages / numOfBlocks);
		int currentBlock = 0;
		for(int i=0;i<numberOfImages;i++ ){
			if(i%blockCount==0)currentBlock++;
			/*
			 * Get each file and and move it to a block folder
			 */
			Path source = Paths.get("E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Images\\"+(i+1)+".png");
    		
    		String blockFolder = "E:\\Mars\\rahul\\workspace\\MapReduce\\src\\Container\\block"+(currentBlock);
			Path target = Paths.get(blockFolder+ "\\"+(i+1)+".png");
    		
			/*
			 * Check if destination filder already exists
			 */
			if (!Files.exists(Paths.get(blockFolder)))
    		    Files.createDirectories(Paths.get(blockFolder));
			Files.copy(source, target, REPLACE_EXISTING);
		}
	 		
		return numOfBlocks;
	}
	void packer(){
		
	}
	
}