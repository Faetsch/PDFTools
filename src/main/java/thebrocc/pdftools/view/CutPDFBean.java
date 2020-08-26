package thebrocc.pdftools.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;


@Named("cutPDFBean") @RequestScoped
public class CutPDFBean
{
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
	
	public void test()
	{
		
		URL resource = getClass().getResource("/Formblatt_5.pdf");
		File f = null;
		try 
		{
			f = new File(resource.toURI());
		}
		catch (URISyntaxException e) 
		{
			e.printStackTrace();
		}
		
		setPdfFile(f.getAbsolutePath());
		splitPdfIntoSinglePages();
		System.out.println("Amount of files: " + getSplitPdfs().size());
	}
	
	public PDDocument getPdfFile()
	{
		return pdfFile;
	}

	

}
