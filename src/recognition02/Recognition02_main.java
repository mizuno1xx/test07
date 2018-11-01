package recognition02;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition02_main {

	public static void main(String[] args) 
	{
		Recognition02_lib rec_lib = new Recognition02_lib();
		DetectedFaces result = rec_lib.getRecognition("img\\S__144556041.jpg");
		rec_lib.getJson(result);
	}
}
