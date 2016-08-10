/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.ift.sns.encryptor.test;

import mx.org.ift.sns.encryptor.Encryptor;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author jparanda
 */
public class EncryptorTest {
    
	@Ignore
    @Test
    public void testReadTheCsvFileIntoClassPath(){
        
        Encryptor en=new Encryptor();
        ClassLoader classLoader = getClass().getClassLoader();
        String file=classLoader.getResource("testFiles/export_datos_usuario_pawss.csv").getFile();
        Assert.assertTrue(en.executeProcess(file));
    }
    
    
    @Test
    public void testReadTheCsvFileFromExternalPath(){
    	
    	Encryptor en=new Encryptor();
        
        Assert.assertTrue(en.executeProcess("/home/jparanda/workSpaceJuan/export_datos_usuario_pawss.csv"));
    }
        
    
    
    
}
