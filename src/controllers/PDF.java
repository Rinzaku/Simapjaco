package controllers;

import instancias.*;
import models.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.*;

public class PDF {

	private static  Document document;
	private static String FILE = "";
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	//	  public static void main(String[] args) {
	//	    try {
	//	      Document document = new Document();
	//	      PdfWriter.getInstance(document, new FileOutputStream(FILE));
	//	      document.open();
	//	      addMetaData(document);
	//	      addTitlePage(document);
	//	      addContent(document);
	//	      document.close();
	//	    } catch (Exception e) {
	//	      e.printStackTrace();
	//	    }
	//	  }

	public PDF(String file_name) throws FileNotFoundException, DocumentException{
		FILE = file_name+".pdf";
		//		  System.out.println(FILE);
		document = new Document(PageSize.LETTER);
		PdfWriter.getInstance(document, new FileOutputStream(FILE));
		document.open();
	}

	// iText allows to add metadata to the PDF which can be viewed in your Adobe
	// Reader
	// under File -> Properties
	public void addMetaData(String titulo, String subtitulo,String autor) {
		document.addTitle(titulo);
		document.addSubject(subtitulo);
		document.addKeywords("PDF, Reporte, Reportes, Ventas, Inventario");
		document.addAuthor(autor);
		document.addCreator(autor);
	}

	public void addCabecera() throws DocumentException{
		Font f = new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK);
		String cabecera = "BOUTIQUE SIMAPJACO\nAV.MEXICO 178, COL. RAÚL ROMERO";
		Paragraph paragraph = new Paragraph(cabecera, f);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		addEmptyLine(paragraph, 2);
		document.add(paragraph);
	}
	
	public void addTitlePage(String tipo_reporte, String fecha, String autor, String descripcion)
			throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		Paragraph p = new Paragraph(tipo_reporte,new Font(FontFamily.HELVETICA, 17, Font.BOLD, GrayColor.BLACK));
		p.setAlignment(Element.ALIGN_CENTER);
		preface.add(p);

		addEmptyLine(preface, 1);
		p = new Paragraph("Reporte generado por: " +autor+ ",\ncon fecha : " + fecha,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		preface.add(p);

		addEmptyLine(preface, 1);
		p = new Paragraph(descripcion,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		preface.add(p);

		addEmptyLine(preface,1);

		document.add(preface);
	}

	public void addContent_Inventario(ArrayList<Modelo> productos) throws DocumentException {
		
		createTableInventario(productos);

	}

	public void addContent_Ventas_dia(ArrayList<Ventas> ventas, String cuenta) throws DocumentException {
		Paragraph paragraph = null;
		String msj = "";
		double totalVenta = 0;
		for (Ventas v : ventas) {
			totalVenta += v.getTotal_venta(); 
			createTableVentas(v);
			paragraph = new Paragraph();
			addEmptyLine(paragraph, 2);
			document.add(paragraph);
		}
		double cuenta_dia = cuenta.length() == 0 ? 0.0 : Double.parseDouble(cuenta);
		msj = "Subtotal de venta : $"+(totalVenta + cuenta_dia);
		paragraph = new Paragraph(msj,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		msj = "Cuenta del dia de hoy : $"+cuenta;
		paragraph = new Paragraph(msj,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
		msj = "Total de venta para el dia de hoy : $" + totalVenta;
		paragraph = new Paragraph(msj,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
	}
	
	public void addContent_Ventas_mes(ArrayList<Ventas> ventas) throws DocumentException {
		double total_venta_mes = 0;
		String msj = "";
		Paragraph paragraph = null;
		for (Ventas v : ventas) {
			total_venta_mes += v.getTotal_venta();
			createTableVentas(v);
			paragraph = new Paragraph();
			addEmptyLine(paragraph, 2);
			document.add(paragraph);
		}
		msj = "Total de ventas para este mes : $" + total_venta_mes;
		paragraph = new Paragraph(msj,new Font(FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.BLACK));
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		document.add(paragraph);
	}
	public void createTableInventario(ArrayList<Modelo> productos)
			throws DocumentException {
		float[] columWidths = {2,2,4,2,2,1.5f,2,2};
		PdfPTable table = new PdfPTable(columWidths);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, GrayColor.BLACK);
        Font ft = new Font(FontFamily.HELVETICA, 10, Font.BOLDITALIC, GrayColor.BLACK);
        
		table.addCell(new Phrase("Modelo",ft));
		table.addCell(new Phrase("Producto",ft));
		table.addCell(new Phrase("Descripción",ft));
		table.addCell(new Phrase("Talla",ft));
		table.addCell(new Phrase("Color",ft));
		table.addCell(new Phrase("Cant.",ft));
		table.addCell(new Phrase("Precio",ft));
		table.addCell(new Phrase("Estado",ft));
		table.setHeaderRows(1);
		table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		for (Modelo m : productos) {
			Ropa r = new Ropa_model().find_ropa(m.getId_ropa());
			table.addCell(new Phrase(m.getModelo(),f));
			table.addCell(new Phrase(r.getPrenda(),f));
			table.addCell(new Phrase(r.getDescricion(),f));
			table.addCell(new Phrase(new Talla_model().find_talla(m.getId_talla()).getTalla(),f));
			table.addCell(new Phrase(new Color_model().find_color(m.getId_color()).getColor(),f));
			table.addCell(new Phrase(""+m.getExistencias(),f));
			table.addCell(new Phrase(""+r.getPrecio(),f));
			table.addCell(new Phrase(m.getEstado(),f));
		}
		
		document.add(table);
        
	}

	public void createTableVentas(Ventas venta)
			throws DocumentException {
		
		Paragraph paragraph = new Paragraph("Venta", catFont);
		addEmptyLine(paragraph, 1);
		document.add(paragraph);
		float[] columWidths = {1.5f,3,1.5f,2,1.5f,2,1.5f,3,3};
		PdfPTable table = new PdfPTable(columWidths);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, GrayColor.BLACK);
        Font ft = new Font(FontFamily.HELVETICA, 10, Font.BOLDITALIC, GrayColor.BLACK);
        
		table.addCell(new Phrase("No. Venta",ft));
		table.addCell(new Phrase("Empleado",ft));
		table.addCell(new Phrase("Arts.",ft));
		table.addCell(new Phrase("SubTotal",ft));
		table.addCell(new Phrase("Desc.",ft));
		table.addCell(new Phrase("Total",ft));
		table.addCell(new Phrase("Abono",ft));
		table.addCell(new Phrase("Estado",ft));
		table.addCell(new Phrase("Fecha",ft));
		
		table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
		table.addCell(new Phrase(""+venta.getId_venta(),f));
		table.addCell(new Phrase(new Empleados_model().find_empleado(venta.getNo_empleado()).getNombre()+" "+new Empleados_model().find_empleado(venta.getNo_empleado()).getApellidos(),f));
		table.addCell(new Phrase(""+venta.getNo_articulos(),f));
		table.addCell(new Phrase(""+venta.getSub_total(),f));
		table.addCell(new Phrase(""+venta.getDescuento(),f));
		table.addCell(new Phrase(""+venta.getTotal_venta(),f));
		table.addCell(new Phrase(""+venta.getAbono(),f));
		table.addCell(new Phrase(venta.getEstado(),f));
		table.addCell(new Phrase(venta.getFecha(),f));

		document.add(table);
		
		createTableDetalleVenta(new Detalle_Venta_model().find_detalle_venta(venta.getId_venta()));

	}
	
	public void createTableDetalleVenta(ArrayList<Detalle_Venta> detalles)
			throws DocumentException {
		Paragraph paragraph = new Paragraph("Detalle de Venta", catFont);
		addEmptyLine(paragraph, 1);
		document.add(paragraph);
		
		float[] columWidths = {1.5f,3,2f,2,1.5f,2,2f,3};
		PdfPTable table = new PdfPTable(columWidths);
		table.setWidthPercentage(100);
		table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.75f));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        
        Font f = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, GrayColor.BLACK);
        Font ft = new Font(FontFamily.HELVETICA, 10, Font.BOLDITALIC, GrayColor.BLACK);
        
		table.addCell(new Phrase("No. Venta",ft));
		table.addCell(new Phrase("Modelo",ft));
		table.addCell(new Phrase("Color",ft));
		table.addCell(new Phrase("Talla",ft));
		table.addCell(new Phrase("Arts.",ft));
		table.addCell(new Phrase("Precio Unitario",ft));
		table.addCell(new Phrase("Precio Total",ft));
		table.addCell(new Phrase("Estado",ft));
		
		table.setHeaderRows(1);
        table.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
        for (Detalle_Venta dv : detalles) {
        	Modelo m = new Modelo_model().find_modelo(dv.getId_modelo());
        	table.addCell(new Phrase(""+dv.getId_venta(),f));
    		table.addCell(new Phrase(m.getModelo(),f));
    		table.addCell(new Phrase(new Color_model().find_color(m.getId_color()).getColor(),f));
    		table.addCell(new Phrase(new Talla_model().find_talla(m.getId_talla()).getTalla(),f));
    		table.addCell(new Phrase(""+dv.getCantidad_articulos(),f));
    		table.addCell(new Phrase(""+dv.getPrecio_unitario(),f));
    		table.addCell(new Phrase(""+(dv.getCantidad_articulos()*dv.getPrecio_unitario()),f));
    		table.addCell(new Phrase(dv.getEstado(),f));
		}
	
		document.add(table);

	}
	
	public void cerrar_doc(){
		document.close();
	}
	public void createList(Section subCatPart) {
		List list = new List(true, false, 10);
		list.add(new ListItem("First point"));
		list.add(new ListItem("Second point"));
		list.add(new ListItem("Third point"));
		subCatPart.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
