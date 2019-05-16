package metadataExtractor;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class test extends JFrame{

	private JTextField filename = new JTextField(), dir = new JTextField();

	private JButton open = new JButton("Open"), quit = new JButton("Quit");
	
	public static String fileName = "";
	public  BufferedReader br;
	
	
	public test() {
		  
	    JPanel p = new JPanel();
	    open.addActionListener(new OpenL());
	    p.add(open);
	    //quit.addActionListener(new QuitL());
	    quit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	filename.setText("You pressed Quit");
		    	System.exit(1);
		        //call another method in the same class which will close this Jframe		        
		    }
		});
	    p.add(quit);
	    Container cp = getContentPane();
	    cp.add(p, BorderLayout.SOUTH);
	    dir.setEditable(false);
	    filename.setEditable(false);
	    p = new JPanel();
	    p.setLayout(new GridLayout(2, 1));
	    p.add(filename);
	    p.add(dir);
	    cp.add(p, BorderLayout.NORTH);   
	  }
	  
	  class OpenL implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		    	fileName = "";
		    	
		    	JFileChooser c = new JFileChooser();
		    	File dFile = new File("C:\\Users\\Ahmet\\Dropbox\\Format\\Computer Engineering\\4-2\\Multimedia Systems");
		    	c.setCurrentDirectory(dFile);
		    	// Demonstrate "Open" dialog:
		    	int rVal = c.showOpenDialog(test.this);
		    	
		    	if (rVal == JFileChooser.APPROVE_OPTION) {
		    		filename.setText(c.getSelectedFile().getPath().toString());
		    		//System.out.println(filename.getText());
		    		dir.setText(c.getCurrentDirectory().toString());
		    		//fileName = c.getCurrentDirectory().toString();
		    		setVisible(false);
		    		
		    		
		    		
		    		fileName = filename.getText();
		    		System.out.println(fileName);
		    		String [] tokens = fileName.split(Pattern.quote("."));
		    		int leng = tokens.length;
		    		String token = tokens[leng-1].toLowerCase();
		    		
		    		try {
			    		br = new BufferedReader(new FileReader(fileName));
		  		      	StringBuilder sb = new StringBuilder();
		  		      	String line = br.readLine();
		  		      	
		  		      	while (line != null) {
		  		      		sb.append(line);
		  		      		sb.append(System.lineSeparator());
		  		      		line = br.readLine();
		  		      		
		  		      	}
		  		      	System.out.println();
		  		      	String everything = sb.toString();
		  		      	String hex = "";
		  		      	switch(token) {
		  		      	case "gif":
		  		      		System.out.println("ITS A GIF!");
		  		      		
		  		      		Integer width = 0;
			  		      	for (int i = 6; i < 8; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								
								width = (int) (width + getHex(hex.toCharArray()[0])* Math.pow(16, (i-6)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-6)*2+1));
							}
			  		      	Integer height = 0;
			  		      	for (int i = 8; i < 10; i++) {
			  		      		
			  		      		Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
			  		      		hex = Integer.toHexString(ascii);
			  		      		if (hex.length() == 1) {
			  		      			hex = "0".concat(hex);
			  		      		}
			  		      		hex = swap(hex);
			  		      		
			  		      		height = (int) (height + getHex(hex.toCharArray()[0])* Math.pow(16, (i-8)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-8)*2+1));
			  		      	}
				  		    System.out.println("Image resolution: " + width.toString() + " x " + height.toString());
				  		    
				  		    String backgroundColour = "";
			  		      	for (int i = 11; i < 12; i++) {
			  		      		
			  		      		Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
			  		      		hex = Integer.toHexString(ascii);
			  		      		if (hex.length() == 1) {
			  		      			hex = "0".concat(hex);
			  		      		}
			  		      		//hex = swap(hex);
			  		      		backgroundColour = backgroundColour.concat(hex);
			  		      		//backgroundColour = (int) (backgroundColour + getHex(hex.toCharArray()[0])* Math.pow(16, (i-11)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-11)*2+1));
			  		      	}
			  		      	System.out.println("Background colour: " + backgroundColour);
				  		    
			  		      	byte[] allBytes = everything.getBytes();
			  		      	
			  		      	Integer fs = allBytes.length;
			  		      	Double numberOfImages = Double.valueOf( fs * 8)/ (width * height * 3 * 8) ;
			  		      	System.out.println("Number of images: " + numberOfImages);
			  		      	
			  		      	
				  		    
			  		      	
		  		      		break;
		  		      	case "jpg":
		  		      		System.out.println("ITS JPG!");
		  		      		
		  		      		byte[] everth = everything.getBytes();
		  		      		
		  		      		Double fileSize = ( (Double.valueOf(everth.length) / 1024) );
				  		    System.out.println("File size: " + fileSize + " KB");
				  		    
				  		    String littleBig = "";
			  		      	for (int i = 11; i < 13; i++) {
			  		      		
			  		      		Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
			  		      		
			  		      		hex = Integer.toHexString(ascii);
			  		      		if (hex.length() == 1) {
			  		      			hex = "0".concat(hex);
			  		      		}
			  		      		
			  		      		littleBig = littleBig.concat(hex);
			  		      	}
			  		      	System.out.println("Little//Big: " + littleBig);
				  		    
			  		      	String appSegment= "";
			  		      	for (int i = 2; i < 4; i++) {
			  		      		
			  		      		Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
			  		      		hex = Integer.toHexString(ascii);
			  		      		if (hex.length() == 1) {
			  		      			hex = "0".concat(hex);
			  		      		}
			  		      		//hex = swap(hex);
			  		      		appSegment = appSegment.concat(hex);
			  		      	}
			  		      	appSegment = appSegment.toUpperCase();
			  		      	if (appSegment.equals("FFED")) {
			  		      		System.out.println("The image is changed.");
				  		    
							}else {
								System.out.println("The image is not changed.");
							}
			  		      	
		  		      		break;
		  		      	case "wav":
		  		      		
		  		      		Integer chunkSize = 0;
		  		      		hex = "";
		  		      		
		  		      		System.out.println("ITS A WAV!");
		  		      		for (int i = 4; i < 8; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								
								
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								
								chunkSize = (int) (chunkSize + getHex(hex.toCharArray()[0])* Math.pow(16, (i-4)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-4)*2+1));
							}
		  		      		
		  		      		System.out.println("Chunk size is: " + chunkSize.toString());
		  		      		
		  		      		Integer formatValue = 0;
			  		      	for (int i = 20; i < 22; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								//System.out.println(hex);
								formatValue = (int) (formatValue + getHex(hex.toCharArray()[0])* Math.pow(16, (i-20)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-20)*2+1));
							}
		  		      		System.out.println("Format is: " + formatValue.toString());
		  		      		
			  		      	Integer channelNum = 0;
			  		      	for (int i = 22; i < 24; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								//System.out.println(hex);
								channelNum = (int) (channelNum + getHex(hex.toCharArray()[0])* Math.pow(16, (i-22)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-22)*2+1));
							}
		  		      		System.out.println("Number of channels: " + channelNum.toString());
		  		      		
		  		      		
			  		      	Integer samplingRate = 0;
			  		      	for (int i = 24; i < 28; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								//System.out.println(hex);
								samplingRate = (int) (samplingRate + getHex(hex.toCharArray()[0])* Math.pow(16, (i-24)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-24)*2+1));
							}
		  		      		System.out.println("Sampling Rate is: " + samplingRate.toString());
		  		      		

			  		      	Integer bps = 0;
			  		      	for (int i = 34; i < 36; i++) {
		  		      			
								Integer ascii = (int) (everything.substring(i, i+1)).toCharArray()[0];
								hex = Integer.toHexString(ascii);
								if (hex.length() == 1) {
									hex = "0".concat(hex);
								}
								hex = swap(hex);
								//System.out.println(hex);
								bps = (int) (bps + getHex(hex.toCharArray()[0])* Math.pow(16, (i-34)*2) + getHex(hex.toCharArray()[1])* Math.pow(16, (i-34)*2+1));
							}
		  		      		System.out.println("Bits per sample is: " + bps.toString());
		  		      		
	  		      			
	  		      			break;
		  		      	default:
		  		      		System.out.println("Not a required file!");	
		  		      	}
		  		      	
		  		      	System.exit(0);
		  		      	//finishing whole process
		  		  	} catch (IOException e1) {
						
						e1.printStackTrace();
					} finally {
		    			try {
							br.close();
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
		  		  	}
		    		}
		    	if (rVal == JFileChooser.CANCEL_OPTION) {
		    		filename.setText("You pressed cancel");
		    		dir.setText("");
		    	}
		    }
	  }
	  
	  String swap(String str) {
		  
		  char[] carr = str.toCharArray();
		  char tmp = carr[0];
		  carr[0] = carr[1];
		  carr[1] = tmp;
		  return new String(carr);
	  }
	  
	  int getHex(char charr) {
		  switch(charr) {
		  	case '0': 
		  		return 0;
		  	case '1':
		  		return 1;
		  	case '2':
		  		return 2;
		  	case '3':
		  		return 3;
		  	case '4':
		  		return 4;
		  	case '5':
		  		return 5;
		  	case '6':
		  		return 6;
		  	case '7':
		  		return 7;
		  	case '8':
		  		return 8;
		  	case '9':
		  		return 9;
		  	case 'a':
		  		return 10;
		  	case 'b':
		  		return 11;
		  	case 'c':
		  		return 12;
		  	case 'd': 
		  		return 13;
		  	case 'e':
		  		return 14;
		  	case 'f':
		  		return 15;
		  	default:
		  		System.out.println("error!");
		  }  
		return 0;
	  }
	  
	  public static void run(JFrame frame, int width, int height) {
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  frame.setLocationRelativeTo(null);
		  frame.setLocation(frame.getLocation().x-300, frame.getLocation().y-250);
		  frame.setSize(width, height);
		  frame.setVisible(true);
	  }
	
	  public static void main(String[] args) throws IOException {
		  
		  System.out.println("Select the file ");
		  run(new test(), 640, 480);
		  
	}

}
