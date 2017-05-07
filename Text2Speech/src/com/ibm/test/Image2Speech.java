package com.ibm.test;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier.VisualClass;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.CustomTranslation;



public class Image2Speech {

	 

	public static void main(String[] args) throws URISyntaxException, IOException {
		 
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setApiKey("28304063cbd926ff6461e4a288401cc55478f319");

		System.out.println("Classify an image");
		BufferedImage image = ImageIO.read(new URL(args[0]));
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		File file = new File("downloaded.jpg");
	    ImageIO.write(image, "jpg", file);
	    byte[] jpgByteArray = b.toByteArray();
	    StringBuilder sb = new StringBuilder();
	    for (byte by : jpgByteArray)
	    sb.append(Integer.toBinaryString(by & 0xFF));
		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(file).build();
		VisualClassification result = service.classify(options).execute();
		List<ImageClassification> ImageCl=result.getImages();
		ImageClassification imageClassification =ImageCl .get(0);
		List<VisualClassifier> visual=imageClassification.getClassifiers();
		VisualClassifier visualClassifier =visual.get(0);
		List<VisualClass> cc=visualClassifier.getClasses();
		VisualClass visualClass =cc.get(0);
		System.out.println(result);
		System.out.println("Name---"+visualClass.getName());
		 TextToSpeech ttservice = new TextToSpeech();
		 ttservice.setUsernameAndPassword("57f68cf8-6a51-4968-a6d4-41ea7eb308d5", "KKxerZK1MelT");
		 CustomTranslation ct = new CustomTranslation();
		 ct.getPartOfSpeech();
		 t2S(visualClass.getName());
 
		      }
	
	public static void t2S(String text) throws URISyntaxException, IOException{
		
		Desktop desktop = java.awt.Desktop.getDesktop();
		 String Text = text;
		 Text = Text.replace(" ", "%20");
		 System.out.println("TEXT" + Text);
		 String url = "https://stream.watsonplatform.net/text-to-speech/api/v1/synthesize?voice=en-US_LisaVoice&text="+Text;
		 System.out.println(url);
		  URI oURL = new URI(url);
		  desktop.browse(oURL);
	}
		    
		    
	}
	 
	 

