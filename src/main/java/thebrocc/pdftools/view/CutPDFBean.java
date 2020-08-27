package thebrocc.pdftools.view;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;


@Named("cutPDFBean") @SessionScoped
public class CutPDFBean implements Serializable
{

	private static final long serialVersionUID = 4562575047410592125L;
	private PDDocument pdfFile;
	private List<PDDocument> splitPdfs;
	
	public void setPdfFile(String path)
	{
		File f = new File(path);
		try 
		{
			pdfFile = PDDocument.load(f);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void setSplitPdfs(List<PDDocument> splitPdfs)
	{
		this.splitPdfs = splitPdfs;
	}
	
	public List<PDDocument> getSplitPdfs()
	{
		return this.splitPdfs;
	}
	
	public void splitPdfIntoSinglePages()
	{
		Splitter splitter = new Splitter();
		try 
		{
			List<PDDocument> splitPdfs = splitter.split(getPdfFile());
			setSplitPdfs(splitPdfs);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public String test()
	{
		System.out.println("cutting...");
		URL resource = getClass().getResource("/Formblatt_5.pdf");
		System.out.println(resource.getFile());
		File f = null;
		f = new File(resource.getFile());
		
		setPdfFile(f.getAbsolutePath());
		splitPdfIntoSinglePages();
		System.out.println("Amount of files: " + getSplitPdfs().size());
		return "yehaw";
	}
	
	public PDDocument getPdfFile()
	{
		return pdfFile;
	}

	

}
