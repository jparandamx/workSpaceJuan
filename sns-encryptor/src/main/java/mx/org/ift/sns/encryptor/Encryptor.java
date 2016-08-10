/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.sns.encryptor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author jparanda
 */
public class Encryptor {

    public static final String csvSplitBy=",";
    private Map<String, String> userPassMap;
    private Map<String,String> newUserPassMap;
    private FileWriter fileWriter;
    /**
     * This method received the all user data for encryptor
     * @param args
     */
	public static void main(String[] args) {

		Encryptor e = new Encryptor();
		System.out.println("<---Inicia el proceso de encriptar passwords--->");

		if (args.length>0) {

			Boolean resp = e.executeProcess(args[0].toString());
			if (resp) {
				System.out
						.println("<---Finaliza el proceso de encriptar passwords con exito--->");
			} else {
				System.out
						.println("<---Finaliza el proceso de encriptar passwords con error--->");
			}

		}else{
			System.out
			.println("<---Falta archivo con datos para encriptar--->");

			
		}
		

	}
    
    
	public Boolean executeProcess(String fileName) {
		Boolean resp=Boolean.TRUE;
		try {
			
			readCsvFile(fileName);
			encryptPassword(userPassMap);
			printPasswordEncryptor();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resp=Boolean.FALSE;
		}
		return resp;

	}
    /**
     * This method read the csv file a put the data in a Map
     * @param filePath
     */
    public void readCsvFile(String fileName) {
        BufferedReader br = null;
        String line = "";
        int index=1;
        String [] reg=null;
        userPassMap=new HashMap<String,String>();
        try {
            
            File file= new File(fileName);
            FileReader fileReader=new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                    reg = line.split(csvSplitBy);
                    //Add the user and pass to the HashMap
                    if(!reg[1].isEmpty()){
                    	//System.out.println("Index: "+index+ " registro leido :"+reg[0]+"/"+reg[1]);
                        userPassMap.put(reg[0], reg[1]);
                        index++;
                    }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
			System.out.println("Error en el registros "+ " Key :"+reg[0]+" Index :" +index++);
			e.printStackTrace();
		} 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //System.out.println("<---Finalizó la lectura del archivo--->");
       
    }
    
    private void encryptPassword(Map<String,String> userPawssHashMap){
       
    	newUserPassMap=new HashMap<>();
        //loop the Map
        userPawssHashMap.entrySet().stream().forEach((Map.Entry<String, String> reg) -> {
            try {
                MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
                byte[] hexByte = sha1.digest((reg.getValue()).getBytes());
                String s = Base64.encodeBase64String(hexByte);
                newUserPassMap.put(reg.getKey(),"{SHA-1}" + s);
            }catch (Exception e){
            }
        });      
        
        
    }
    
    /**
     * Este metodo genera el archivo con los inserts 
     * requeridos
     */
	private void printPasswordEncryptor() throws Exception {
		
		String os=System.getProperty("os.name");
		
		if(os.startsWith("Windows")){
			this.fileWriter = new FileWriter("c:/tmp/passworsForInserts.csv");
		}
		else{
			this.fileWriter = new FileWriter("/tmp/user_pass_sns/passworsForInserts.csv");
		}
		
		
		for (Map.Entry<String, String> reg : newUserPassMap.entrySet()) {
			//System.out.println("Username :" + reg.getKey() + "," + "password :" + reg.getValue());
			this.fileWriter.append("insert into temp_user_and_pass(username,password) values ");
			this.fileWriter.append("(").append("'").append(reg.getKey()).append("'").append(csvSplitBy).append("'").append(reg.getValue()).append("'").append(");");

		}
		this.fileWriter.flush();
		this.fileWriter.close();

	}

}


