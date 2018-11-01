package recognition02;

public class Recognition02_main {

	public static void main(String[] args) 
	{
		Recognition02_lib rec_lib = new Recognition02_lib();
		rec_lib.getRecognition("img\\S__144556041.jpg");
		rec_lib.getJson();
	}
}
