package ticket;


import java.awt.Font;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date;

import javax.print.Doc; 
import javax.print.DocFlavor; 
import javax.print.DocPrintJob; 
import javax.print.PrintService; 
import javax.print.PrintServiceLookup; 
import javax.print.SimpleDoc; 
import javax.swing.JOptionPane; 

public class Ticket{ 
	
	Date date=new Date(); 
	SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy"); 
	SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa"); 
	private PrintWriter pw;

	
	static ArrayList<String> CabezaLineas=new ArrayList<String>(); 
	static ArrayList<String> subCabezaLineas=new ArrayList<String>(); 
	static ArrayList<String> items=new ArrayList<String>(); 
	static ArrayList<String> totales=new ArrayList<String>(); 
	static ArrayList<String> LineasPie=new ArrayList<String>(); 
	public static void AddCabecera(String line){CabezaLineas.add(line);} 
	public static void AddSubCabecera(String line){subCabezaLineas.add(line);} 
	public static void AddItem(String cantidad,String item,String price){ 
		OrderItem newItem = new OrderItem(' '); 
		items.add(newItem.GeneraItem(cantidad,item, price)); 
	} 
	public static void AddTotal(String name,String price){ 
		OrderTotal newTotal = new OrderTotal(' '); 
		totales.add(newTotal.GeneraTotal(name, price)); 
	} 
	
	public void cabecera(String folio,String empleado){
		AddCabecera("\t\tBOUTIQUE MODAS MARS\t\t"); 
		AddCabecera(DarEspacio()); 
		AddCabecera(" EXPEDIDO EN:"); 
		AddCabecera("\n AV.MEXICO 178");
		AddCabecera(DarEspacio()); 
		AddCabecera(" COL. RAUL ROMERO"); 
		AddCabecera(DarEspacio()); 
		AddCabecera(DibujarLinea(48)); 
		AddSubCabecera(DarEspacio()); 
		AddSubCabecera(" FOLIO: "+folio); 
		AddSubCabecera(DarEspacio()); 
		AddSubCabecera(" LE ATENDIO: "+empleado); 
		AddSubCabecera(DarEspacio()); 
		AddSubCabecera(" Fecha: "+fecha.format(date) + "   Hora: " + hora.format(date)); 
		AddSubCabecera(DarEspacio()); 
		AddSubCabecera(DibujarLinea(48)); 
		AddSubCabecera(DarEspacio()); 
		AddItem("\tMODELO\t", "CANTIDAD\t", "PRECIO\t");
		AddItem("", "", DarEspacio());
		AddItem("", "", DarEspacio());


	}
	
	public void total(String total,String subtotal,String descuento,String recibido,String cambio){
		AddTotal("",DibujarLinea(47)); 
		AddTotal("",DarEspacio()); 
		AddTotal("\t\t\tTOTAL\t:","\t\t$"+total); 
		AddTotal("",DarEspacio()); 
		AddTotal("\t\t\t DESCUENTO","\t"+descuento+"%");
		AddTotal("",DarEspacio()); 
		AddTotal("",DarEspacio()); 
		AddTotal("\t\t\t SUBTOTAL:","\t$"+subtotal); 
		AddTotal("",DarEspacio()); 
		AddTotal("\t\t\t EFECTIVO","\t$"+recibido); 
		AddTotal("",DarEspacio()); 
		AddTotal("\t\t\t  CAMBIO:","\t$"+cambio); 
		AddTotal("",DarEspacio()); 
		AddTotal("",DarEspacio());
		
	}
	
	public  void Items(String venta [][]){
		for (String[] strings : venta) {
			AddItem("\t"+strings[0]+"\t", strings[4]+"\t",strings[5]+"\t");
			AddItem("","", DarEspacio());
		}
	}
	
	public void piePagina(){
		AddPieLinea(DibujarLinea(48)); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea("SALIDA LA MERCANCIA LA EMPRESA NO SE HACE RESPONSABLE POR ROBO O EXTRAVIO"); 
		AddPieLinea(DarEspacio());
		AddPieLinea(DarEspacio()); 
		AddPieLinea("***EN PRODUCTOS DE OFERTAS NO HAY CAMBIOS***"); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea(DarEspacio());
		AddPieLinea("******NO SE HACEN DEVOLUCIONES DE DINERO******"); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea("***PARA CAMBIOS ES NECESARIO PRESENTAR EL TICKET DENTRO DE LOS 7 DIAS POSTERIORES A SU COMPRA***"); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea("EN BOUTIQUE SIMAPJACO ES UN PLACER ATENDERLE\t"); 
		AddPieLinea(DarEspacio()); 
		AddPieLinea("\t GRACIAS POR SU PREFERENCIA .....\t"); 
		AddPieLinea(DarEspacio()); 
	} 

	public static void AddPieLinea(String line){LineasPie.add(line);} 
	public static String DibujarLinea(int valor){ 
		String raya="";for(int x=0;x<valor;x++){raya+="=";}return raya; 
	} 
	public static String DarEspacio(){return "\n";} 
	public static void ImprimirDocumento(){ 
		String cadena=""; 
		for(int cabecera=0;cabecera<CabezaLineas.size();cabecera++ ){cadena+=CabezaLineas.get(cabecera);} 
		for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){cadena+=subCabezaLineas.get(subcabecera);} 
		for(int ITEM=0;ITEM<items.size();ITEM++){cadena+=items.get (ITEM);} 
		for(int total=0;total<totales.size();total++){cadena+=totales.get(total);} 
		for(int pie=0;pie<LineasPie.size();pie++){cadena+=LineasPie.get(pie);} 

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE; 
		PrintService service = PrintServiceLookup.lookupDefaultPrintService(); 
		DocPrintJob pj = service.createPrintJob(); 
		byte[]bytes =cadena.getBytes(); 
		Doc doc = new SimpleDoc(bytes, flavor,null); 
		try{ 
			pj.print(doc,null); 
		}catch(Exception e){
			e.printStackTrace();
		} 
	} 
//	public void corta(){
//	char[] CORTAR_PAPEL=new char[]  {(char)27+(char)105}; // codigo q corta el papel
//	pw.write(CORTAR_PAPEL); // mando a la impresora
//}
//
//public  void cortar( ) {
//	try{
//		
//		char[] ESC_CUT_PAPER = new char[] { 0x1B, 'm'};
//			pw.write(ESC_CUT_PAPER);
//		
//
//	}catch(Exception e){
//		System.out.print(e);
//	}
//}
//
	
} 













