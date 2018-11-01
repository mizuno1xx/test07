package recognition02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions.Builder;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectFacesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;

public class Recognition02_lib 
{
	VisualRecognition service;
	IamOptions iamOptions;
	DetectedFaces result;
	int age_min;
	int age_max;
	double age_score;
	int gender;	
	
	Recognition02_lib(String flie)
	{
		service = new VisualRecognition("2018-03-19");
		iamOptions = new IamOptions.Builder()
		  .apiKey("1618101")
		  .build();
		service.setIamCredentials(iamOptions);


		DetectFacesOptions detectFacesOptions = null;
		try {
			detectFacesOptions = new DetectFacesOptions.Builder()
			  .imagesFile(new File(flie))
			  .build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		result = service.detectFaces(detectFacesOptions).execute();	
	}
	
	public void getGender()
	{
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readTree(String.valueOf(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		age_min = node.get("images").get(0).get("faces").get(0).get("age").get("min").asInt();
		age_max = node.get("images").get(0).get("faces").get(0).get("age").get("max").asInt();
		age_score = node.get("images").get(0).get("faces").get(0).get("age").get("score").doubleValue();

		if(node.get("images").get(0).get("faces").get(0).get("gender").get("gender").toString() == "MALE") {
			gender = 0;
		}else{
			gender = 1;
		}
		double gender_score = node.get("images").get(0).get("faces").get(0).get("gender").get("score").doubleValue();
		
		System.out.println(result);	
		System.out.println("age_min : " + age_min);
	}
}
