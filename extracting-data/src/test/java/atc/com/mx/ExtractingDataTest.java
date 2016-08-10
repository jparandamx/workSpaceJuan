package atc.com.mx;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ExtractingDataTest 
 
{
	@Test
    public void testReadTheCsvFileFromExternalPath(){
    	
    	ExtractingData en=new ExtractingData();
        
        Assert.assertTrue(en.executeProcess("/home/jparanda/atc/centroCosto.csv"
        									,"/home/jparanda/atc/dataAtc.csv"
        									,"/home/jparanda/atc/datosTelcel.csv"));
    }
	
	@Ignore
	@Test
    public void testReadCentroCostoFile(){
    	
		ExtractingData en=new ExtractingData();
        System.out.println("<---Inicia la lectura del archivo dentro de costo-->");
        en.readCsvCentroCosto("/home/jparanda/atc/centroCosto.csv");
        System.out.println("<---finaliza la lectura del archivo dentro de costo-->");
    }
	
	@Ignore
	@Test
    public void testReadAtcFile(){
    	
		ExtractingData en=new ExtractingData();
        System.out.println("<---Inicia la lectura del archivo ATC-->");
        en.readCsvAtcFile("/home/jparanda/atc/dataAtc.csv");
        System.out.println("<---finaliza la lectura del archivo ATC-->");
    }
	
	@Ignore
	@Test
    public void testReadTelcelFile(){
    	
		ExtractingData en=new ExtractingData();
        System.out.println("<---Inicia la lectura del archivo Telcel-->");
        en.readCsvAtcFile("/home/jparanda/atc/datosTelcel.csv");
        System.out.println("<---finaliza la lectura del archivo Telcel-->");
    }
	
}
