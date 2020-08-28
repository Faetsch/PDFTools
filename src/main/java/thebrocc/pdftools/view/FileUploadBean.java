package thebrocc.pdftools.view;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@Named
@SessionScoped
public class FileUploadBean implements Serializable{

	private static final long serialVersionUID = -5126069359308979588L;
	private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
    	System.out.println("yo what the fuck");
        this.file = file;
    }
 
 
    public void upload() {
        System.out.println("Uploaded file: " + file.getFileName());
        if (file != null) {
            FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
     
    public void handleFileUpload(FileUploadEvent event) {
    	System.out.println("handling uploading of file:...");
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
