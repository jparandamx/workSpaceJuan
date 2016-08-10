package mx.org.ift.sns.load.templates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoadTemplates 
{
	static File folder;
	private static final String url="jdbc:oracle:thin:@10.34.144.114:1521:sns";
	private static final String username="sns_qa";
	private static final String password="sns_qa_test";
	private static final String csvSplitBy=",";
	private List<TemplateVO> templateVO=new ArrayList<TemplateVO>();
	private Boolean response=Boolean.TRUE;

	public static void main( String[] args ) throws Exception
    {

		
		
		LoadTemplates load=new LoadTemplates();
		load.executeProcess();
		
    }
	
	private void executeProcess() throws Exception {
		readCsvFile();
		loadToDataBase(this.templateVO);
		if(response){
			System.out.println("<--El proceso de carga de Plantillas termino con Exito :)---->");
		}else{
			System.out.println("<--El proceso de carga de Plantillas no fue exitoso :(---->");
		}

	}
	
	public void readCsvFile() {
        
		ClassLoader classLoader = getClass().getClassLoader();
        String filename=classLoader.getResource("mx/org/ift/sns/load/templates/asignacion_plantillas.csv").getFile();
        
		BufferedReader br = null;
        String line = "";
        int index=1;
        String [] reg=null;
        
        try {
            
            File file= new File(filename);
            FileReader fileReader=new FileReader(file);
            br = new BufferedReader(fileReader);
            while ((line = br.readLine()) != null) {
                    reg = line.split(csvSplitBy);
                    TemplateVO vo=new TemplateVO();
                    vo.setId_tipo_solicitud(reg[0].toString());
                    vo.setId_tipo_destinatario(reg[1].toString());
                    vo.setDescripcion(reg[2].toString());
                    vo.setPlantilla(searchFile(reg[3]));
                    templateVO.add(vo);
                    
            }
        } 
        catch (FileNotFoundException e) 
        {
        	System.out.println("<---El archivo asignacion_plantillas.csv  no existe en la ruta indicada");
        	this.response=Boolean.FALSE;
         } 
        catch (IOException e) 
        {
        	  System.out.println("<---Ha ocurrido un error leyendo el archivo asignacion_plantillas.csv");
        	  this.response=Boolean.FALSE;
        }
        catch (Exception e) {
			System.out.println("<---Ha ocurrido un error, es posible que la ruta /tmp/plantilla_sns/ no exista");
			this.response=Boolean.FALSE;
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

       
       
    }
	
	private InputStream searchFile(String nameFile){
		
		String os=System.getProperty("os.name");
		
		if(os.startsWith("Windows")){
			folder = new File("c:/tmp/plantillas_sns/");
		}
		else{
			folder = new File("/tmp/plantillas_sns/");
		}
		
		File[] listOfFiles = folder.listFiles();
		InputStream inputStream=null;
		
		for (File filereading : listOfFiles) {
			if (filereading.isFile()) {
				//System.out.println(file.getName());
				if(filereading.getName().equals(nameFile))
				{
					try {
						inputStream = new FileInputStream(new File(filereading.getAbsolutePath()));
					} catch (FileNotFoundException e) {
						System.out.println("<---Ha ocurrido un error ubicando un template en /tmp/plantilla_sns/");
						this.response=Boolean.FALSE;
						e.printStackTrace();
					}
					break;
				}
				
		       
			}else{
				System.out.println("<---El archivo "+nameFile+" No existe en la ruta /tmp/plantillas_sns/");
			}
		}
		return inputStream;
		
	}
	
	
	
	private void loadToDataBase(List<TemplateVO> plantillasData){
		
		
		
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, username, password);
		    conn.setAutoCommit(false);
		    
		    for (TemplateVO templateVO : plantillasData) 
		    {
		    	String sql = "INSERT INTO temp_plantilla (plantilla, id_tipo_solicitud,id_tipo_destinatario,descripcion) VALUES (?, ?, ?,?)";
			    PreparedStatement stmt = conn.prepareStatement(sql);
			    stmt.setBlob(1, templateVO.getPlantilla());
			    stmt.setString(2,templateVO.getId_tipo_solicitud());
			    stmt.setString(3, templateVO.getId_tipo_destinatario());
			    stmt.setString(4, templateVO.getDescripcion());
			    
			    stmt.execute();

			    conn.commit();
				
			}
		    
		    conn.close();
		    
		    
		}catch (Exception e){
			this.response=Boolean.FALSE;
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				this.response=Boolean.FALSE;
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	public List<TemplateVO> getTemplateVO() {
		return templateVO;
	}

	public void setTemplateVO(List<TemplateVO> templateVO) {
		this.templateVO = templateVO;
	}
	
	

}
