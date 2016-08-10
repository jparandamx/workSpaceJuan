package atc.com.mx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class ExtractingData 
{
	public static final String csvSplitBy=",";
    private Map<String, String> mapAtcData;
    private Map<String, String> mapRhData;
    private Map<String,String> mapTelcelData;
    private Map<String,String> mapMerch;
    private FileWriter fileWriter;
    /**
     * This method received the all user data for encryptor
     * @param args
     */
	public static void main(String[] args) {

		ExtractingData e = new ExtractingData();
		System.out.println("<---Inicia el proceso de extraccion de datos--->");

		if (args.length>0) {

			Boolean resp = e.executeProcess(args[0].toString(),args[1].toString(),args[2].toString());
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
    
    
	public Boolean executeProcess(String fileCentroCosto, String fileNameAtc,String fileNameTelcel) {
		Boolean resp=Boolean.TRUE;
		try {
			
			readCsvCentroCosto(fileCentroCosto);
			readCsvAtcFile(fileNameAtc);
			readCsvTelcelFile(fileNameTelcel);
			matchFiles();
			generarOutFileCsv();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			resp=Boolean.FALSE;
		}
		return resp;

	}
	
	
	public void readCsvCentroCosto(String fileName){
		
		BufferedReader br = null;
        String line = "";
        int index=1;
        String [] reg=null;
        mapRhData=new HashMap<String,String>();
        try {
            
            File file= new File(fileName);
            FileReader fileReader=new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                    StringBuffer data=new StringBuffer();
            		reg = line.split(csvSplitBy);
                    //reg[0] contiene el numero de telefono
                    data.append(reg[1]).append(csvSplitBy).append(reg[2]).append(csvSplitBy).append(reg[3]);
                    mapRhData.put(reg[0], data.toString());
              }
            //Only for test
            System.out.println("<<----Datos Centro Costo File-->");
            for (Map.Entry<String, String> register : mapRhData.entrySet()) {
    			System.out.println("Telefono :" + register.getKey() + "," + "data :" + register.getValue());
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
	
	
	
	
    /**
     * This method read the csv file a put the data in a Map
     * @param filePath
     */
    public void readCsvAtcFile(String fileName) {
        BufferedReader br = null;
        String line = "";
        int index=1;
        String [] reg=null;
        mapAtcData=new HashMap<String,String>();
        try {
            
            File file= new File(fileName);
            FileReader fileReader=new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                    StringBuffer data=new StringBuffer();
            		reg = line.split(csvSplitBy);
                    //reg[0] contiene el numero de telefono
                    data.append(reg[1]).append(csvSplitBy)//MODELO	 OBSERVACIONES	STATUS	Fecha de entrega
                    .append(reg[2]).append(csvSplitBy)//IMEI
                    .append(reg[3]).append(csvSplitBy)//OBSERVACIONES
                    .append(reg[4]).append(csvSplitBy)//STATUS
                    .append(reg[5]);//Fecha de entrega
                    mapAtcData.put(reg[0], data.toString());
              }
            //Only for test
            System.out.println("<<----Datos ATC File-->");
            for (Map.Entry<String, String> register : mapAtcData.entrySet()) {
    			System.out.println("Telefono :" + register.getKey() + "," + "data :" + register.getValue());
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
    
    
    public void readCsvTelcelFile(String fileName) {
    	BufferedReader br = null;
        String line = "";
        int index=1;
        String [] reg=null;
        mapTelcelData=new HashMap<String,String>();
        try {
            
            File file= new File(fileName);
            FileReader fileReader=new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                    StringBuffer data=new StringBuffer();
            		reg = line.split(csvSplitBy);
                    //reg[0] contiene el numero de telefono
                    data.append(reg[1]).append(csvSplitBy)
                    .append(reg[2]).append(csvSplitBy)
                    .append(reg[3]).append(csvSplitBy)
                    .append(reg[4]).append(csvSplitBy)
                    .append(reg[5]).append(csvSplitBy)
                    .append(reg[6]);
                    mapTelcelData.put(reg[0], data.toString());
              }
            //Only for test
            System.out.println("<<----Datos Telcel File-->");
            for (Map.Entry<String, String> register : mapTelcelData.entrySet()) {
    			System.out.println("Telefono :" + register.getKey() + "," + "data :" + register.getValue());
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
    
    public void readFechaCompraFile(){
    	
    }
    
    
    public void matchFiles(){
    	
    	mapMerch=new HashMap<String,String>();
    	for (Map.Entry<String, String> reg : mapRhData.entrySet()) {
    	
    	if(mapAtcData.containsKey(reg.getKey()))
    	{
    		if(mapTelcelData.containsKey(reg.getKey()))
    		{
    			
    			StringBuffer data=new StringBuffer();
    			
    			data.append(mapRhData.get(reg.getKey())).append(csvSplitBy)
    			.append(mapTelcelData.get(reg.getKey())).append(csvSplitBy)
    			.append(mapAtcData.get(reg.getKey()));
    			
    			mapMerch.put(reg.getKey(),data.toString());
    			
    		}else
    		{
    			
    			System.out.println("El numero: "+reg.getKey()+ " Existe en ATC file pero no existe en MATC file");
    		}
    		
    	}
    	else
    	{
    		System.out.println("El numero: "+reg.getKey()+ " No existe en ATC file");
    	}
			
    	
    	}
    	 //Only for test
        for (Map.Entry<String, String> register : mapMerch.entrySet()) {
			System.out.println(register.getKey()+","+register.getValue());
		}
}
        
    /**
     * Este metodo genera el archivo de salida
     * requeridos
     */
	private void generarOutFileCsv() {
		
		String os=System.getProperty("os.name");
		int i=0;
		
		try{
			if(os.startsWith("Windows")){
				this.fileWriter = new FileWriter("c:/atc/excelComparativo.csv");
			}
			else{
				this.fileWriter = new FileWriter("/home/jparanda/atc/excelComparativo.csv");
			}
			
			this.fileWriter.append("TELEFONO").append(csvSplitBy).
			append("NOMBRE DE USUARIO").append(csvSplitBy).
			append("DEPARTAMENTO").append(csvSplitBy).
			append("C.C").append(csvSplitBy).
			append("SIM").append(csvSplitBy).
			append("IMEI TELCEL").append(csvSplitBy).
			append("PLAN").append(csvSplitBy).
			append("Monto Renta").append(csvSplitBy).
			append("Fecha Inicio plan").append(csvSplitBy).
			append("Fecha terminacion plan").append(csvSplitBy).
			append("MODELO").append(csvSplitBy).
			append("IMEI ATC").append(csvSplitBy).
			append("OBSERVACIONES").append(csvSplitBy).
			append("STATUS").append(csvSplitBy).
			append("FECHA ENTREGA").append(csvSplitBy).append("\n");
			
			

			for (Map.Entry<String, String> reg : mapMerch.entrySet()) {
				//System.out.println("Username :" + reg.getKey() + "," + "password :" + reg.getValue());
				StringBuffer writer=new StringBuffer();
				
				String[] datos=reg.getValue().split(csvSplitBy);
					
					this.fileWriter.append(reg.getKey()).append(csvSplitBy)
					.append(datos[0]).append(csvSplitBy)
					.append(datos[1]).append(csvSplitBy)
					.append(datos[2]).append(csvSplitBy)
					.append(datos[3]).append(csvSplitBy)
					.append(datos[4]).append(csvSplitBy)
					.append(datos[5]).append(csvSplitBy)
					.append(datos[6]).append(csvSplitBy)
					.append(datos[7]).append(csvSplitBy)
					.append(datos[8]).append(csvSplitBy)
					.append(datos[9]).append(csvSplitBy)
					.append(datos[10]).append(csvSplitBy)
					.append(datos[11]).append(csvSplitBy)
					.append(datos[12]).append(csvSplitBy)
					.append(datos[13]).append(csvSplitBy)
					.append("\n");
					i++;
					
				
			}
			this.fileWriter.flush();
			this.fileWriter.close();
		}catch (Exception e){
			
			System.out.println("Se presento error en el registro :"+i );
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
