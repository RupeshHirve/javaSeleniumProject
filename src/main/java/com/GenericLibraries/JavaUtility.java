package com.GenericLibraries;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

public class JavaUtility 
{
	
	public int getRandomNum()
	{
		Random rand=new Random();
		int randnum = rand.nextInt(1000);
		return randnum;
	}

	public String getsystemDate()
	{
		Date d=new Date();
		String systemDateAndTime = d.toString();
		return systemDateAndTime;
	}

	public String getSystemDateInFormat()
	{
		Date d=new Date();
		String systemDate = d.toString();
		//System.out.println(systemDate);
		String[] arr = systemDate.split(" ");
		String DD = arr[2];
		String YYYY = arr[5];
		int MM = d.getMonth()+1;
		String finalformat = YYYY+"-"+MM+"-"+DD;
		return finalformat;
	}

	public void pressEnterKey() throws AWTException
	{
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
	}

	public void readFile(String user, String fileName,String fileType) throws Throwable
	{
			/*File file=new File("C:\\Users\\"+user+"\\Downloads\\"+fileName+".pdf");   
			Scanner sc = new Scanner(file);     //file to be scanned  
			while (sc.hasNextLine())        //returns true if and only if scanner has another token  
				System.out.println(sc.nextLine());*/
			String encoding = "TIS-620";
			File file = new File("C:\\Users\\"+user+"\\Downloads\\"+fileName+fileType);

			if (file.exists()) {
			   /* try {
			    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			        String line = null;
			        while ((line = br.readLine()) != null) {
			            System.out.println(line);
			        }
			        br.close();
			    } catch (Exception e) {
			        System.out.println("No texts present in the file");;
			    }*/
				try { 
					InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
				    int data = isr.read();
				    while (data != -1) {
				        System.out.print((char) data);
				        data = isr.read();
				    }
				    isr.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			else {
			    System.out.println("file doesn't exist");
			}

	}

	public void readFile(String path, String fileName) throws Throwable
	{
			String encoding = "TIS-620";
			File file = new File(path+fileName);

			if (file.exists()) {
				try { 
					InputStreamReader isr = new InputStreamReader(new FileInputStream(file), encoding);
				    int data = isr.read();
				    while (data != -1) {
				        System.out.print((char) data);
				        data = isr.read();
				    }
				    isr.close();
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
			else {
			    System.out.println("file doesn't exist");
			}

	}
	
	public void openFile(String user, String fileName, String fileType) throws Throwable
	{
		try
		{
			File file = new File("C:\\Users\\"+user+"\\Downloads\\"+fileName+fileType);   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}
	public void openFile(String path, String fileName) throws Throwable
	{
		try
		{
			File file = new File(path+fileName);   
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())         //checks file exists or not  
				desktop.open(file);              //opens the specified file  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

	public void closeFile(String user, String fileName,String fileType) throws Throwable
	{
		FileInputStream fos = null; 
		try 
		{
			// create new file output stream
			fos = new FileInputStream("C:\\Users\\"+user+"\\Downloads\\"+fileName+fileType);

			// close stream
			fos.close();
			
		} 
		catch(Exception ex)
		{
			// if any error occurs
			System.out.print("IOException: File output stream is closed");
		} 
		finally
		{
			// releases all system resources from the streams
			if(fos!=null)
				fos.close();
		}
	}
	
	public void closeFile(String path, String fileName) throws Throwable
	{
		FileInputStream fos = null; 
		try 
		{
			// create new file output stream
			fos = new FileInputStream(path+fileName);

			// close stream
			fos.close();
			
		} 
		catch(Exception ex)
		{
			// if any error occurs
			System.out.print("IOException: File output stream is closed");
		} 
		finally
		{
			// releases all system resources from the streams
			if(fos!=null)
				fos.close();
		}
	}
	
	public static final String makeScreenshot(JFrame argFrame, String name) {
	    Rectangle rec = argFrame.getBounds();
	    BufferedImage bufferedImage = new BufferedImage(rec.width, rec.height, BufferedImage.TYPE_INT_ARGB);
	    argFrame.paint(bufferedImage.getGraphics());
	    String destfile = System.getProperty("user.dir")+"./screenshot/"+name+".png";
		File finaldest=new File(destfile);
	    
	    try {
	        // Create temp file
	        File temp = File.createTempFile("screenshot", ".png");

	        // Use the ImageIO API to write the bufferedImage to a temporary file
	       // ImageIO.write(bufferedImage, "png", temp);
			FileUtils.copyFile(temp, finaldest);
	
	        // Delete temp file when program exits
	        temp.deleteOnExit();
	        
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
	    }
	    return destfile;
	}
}
