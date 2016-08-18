
public class DataNode {
	public static void main(String [] args){
		if(args.length<1){
			System.out.println("Specify port number");
			return;
		}
		int port = Integer.parseInt(args[0]);

		DataNodeReceiver dnr = new DataNodeReceiver(port);
		new Thread(dnr).start();
	}
}
