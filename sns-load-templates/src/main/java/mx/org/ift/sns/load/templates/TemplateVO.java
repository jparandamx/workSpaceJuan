package mx.org.ift.sns.load.templates;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;

public class TemplateVO {
	
	private InputStream plantilla;
	
	private String id_tipo_solicitud;
	
	private String id_tipo_destinatario;
	
	private String descripcion;
	
	public TemplateVO(){
		
	}

	public InputStream getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(InputStream plantilla) {
		this.plantilla = plantilla;
	}

	public String getId_tipo_solicitud() {
		return id_tipo_solicitud;
	}

	public void setId_tipo_solicitud(String id_tipo_solicitud) {
		this.id_tipo_solicitud = id_tipo_solicitud;
	}

	public String getId_tipo_destinatario() {
		return id_tipo_destinatario;
	}

	public void setId_tipo_destinatario(String id_tipo_destinatario) {
		this.id_tipo_destinatario = id_tipo_destinatario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
