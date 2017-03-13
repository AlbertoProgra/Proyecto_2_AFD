import java.io.File;
import java.util.Scanner;
import java.util.Vector;
import java.util.HashMap;
import java.io.*;


public class AfdMachine{
  int estado;
  Vector<String> v;  
  //String cadena;
  String[] Alfabeto;
  //String[] cadenatemp;
  int cont;
  int cont1;
  int cont_estados;
  int cont_estados2;
  int cadenatam;
  int es;

//Constructor
public AfdMachine(Vector<String> v, String[] Alfabeto){
   estado = 1;
   cont = 0;
   cont_estados=0;
   cont_estados2=0;
   //cadenatemp = "";
   this.v=v;
   //this.cadena=cadena;
   this.Alfabeto=Alfabeto;
   new Vector<String>();
} 

//Metodo que devuelve el estado
public int dameEstado(){
   return estado;
}

//Metodo que devuelve el conteo de coincidencias
public int dameConteo(){
   return cont;
}

//Metodo que verifica las coincidencias de acuerdo al estado
public void cambiaEstado(String cadena, int cadenatamanio){

      cadenatam=cadenatamanio;
		//System.out.println(cadena+"-recibe");

		String[] AL = v.elementAt(1).split(",");
	
		for(int i=3; i<v.size();i++){
            String[] temp = v.elementAt(i).split(",");
           // System.out.println(v.elementAt(i));
               for(int j=0; j<temp.length;j++){
            // System.out.println(cadena+";"+AL[j]+":"+estado+":"+cont);	
               // System.out.println(AL[j]);  
             
           if(cadena.equals(AL[j]) && temp[j].equals(String.valueOf(estado)))
             {
              // System.out.println(cadena+";"+AL[j]+":"+estado+":"+cont+"aqui");
                              
          estado=estado-1;
           
            //    System.out.println("Estado:"+estado);

              cont1++;
             
           System.out.println(cadena+";"+AL[j]+":"+estado+"----"+cont1);
              
              es = dameEstado()+1;
             }


         else if(cadena.equals(AL[j]) && temp[j].equals(String.valueOf(estado+1))){  
                estado++;
        //  System.out.println(cadena+";"+AL[j]+":"+estado);
          	
             	cont++;
      System.out.println(cadena+";"+AL[j]+":"+estado+"----"+cont);
            // System.out.println("Conteo palabras:"+cont);
             }
  
               	   
               }//Fin de for de temp

     
		} //Fin for de v.size()
    

	}  //Fin cambia estado
//}//while


//Metodo separa comas
private static String[] separarcomas(String valor) {
		return valor.split(",");
}

//Metodo que verifica si la cadena tiene los elementos del alfabeto
public boolean Verificacadena(String cadena, String [] alfabeto) {
   int cont = 0;
      for(int x = 0 ; x < cadena.length(); x++){
        String l = ""+cadena.charAt(x);
           for(int y = 0 ; y < alfabeto.length ; y++){
              if(l .equals(alfabeto[y])){
                 cont++;
        }
      }
    }
    
    if(cont == cadena.length()){
      return true;
    }else{
      return false;
    }
    
  }

public String  resultado(){
	String resul = "";
	String[] EstadosFinales = v.elementAt(0).split(",");
	for(int i=0; i<EstadosFinales.length;i++){
		System.out.println("Estadosfinales "+EstadosFinales[i]);
 System.out.println("Estados "+dameEstado());
 System.out.println("conteo "+cont);
   System.out.println("conteo2 "+cont1);
    System.out.println("estadosestring "+es);

         if((dameEstado())==Integer.parseInt(EstadosFinales[i])){
             cont_estados++;
         }else if (es==Integer.parseInt(EstadosFinales[i]))
         {
             
             cont_estados2++;
             cont_estados  = -1;

        //     System.out.println("es"+es);
         }

	} //Fin de for



	if(cont_estados>0 && cont==cadenatam){
		resul = "ACEPTADA";
    estado=1;
    cont=0;
    cont1=0;


   //contestados=0;

	}else if(es==4 && cont==cadenatam){
		resul =  "ACEPTADA";
    estado=1;
    cont=0;
    cont1=0;

  // contestados=0;
	}else {
    resul =  "RECHAZADA";
    estado=1;
    cont=0;
    cont1=0;

  }



   return resul;
} //fin de Funcion



//---------------------------------------------------------MAIN----------------------------------------------
	public static void main(String[] args) {



//-------------------------------------------------------MANEJO DE ARCHIVOS--------------------------------		

// Fichero del que queremos leer
File fichero = new File("afd/variables.afd");
File ficherocadena = new File("afd/tests/variables.txt");
Scanner s = null;
Scanner c = null;
try {
// Leemos el contenido del fichero

s = new Scanner(fichero);
c = new Scanner(ficherocadena);

// Leemos linea a linea el fichero
String linea = null;
int cont=0;

//Se crea vector con tamaño x, para que almacene cada linea de la matriz ingresada
Vector<String> v = new Vector<String>();
Vector<String> vcadena = new Vector<String>();


//Ciclo que recorre todas la lineas de la matriz y cada linea son guardas en vectores
while (s.hasNextLine()) {
	v.add (s.nextLine()); 	// Guardamos la linea en un vector
	cont++; //Cuenta las lineas de la matriz
}




//-----------------------------------------------------------------------
 //Estados que siempre van a tener el mismo indice
 String[] EstadoFinal = separarcomas(v.elementAt(0));
 String[] Alfabeto = separarcomas(v.elementAt(1));
// String[] E0 = separarcomas(v.elementAt(2));
//-----------------------------------------------------------------------
String[] AL = v.elementAt(1).split(",");

String Estados = new String(); 


for(int i=0;i<vcadena.size(); i++){
//System.out.println(vcadena.elementAt(i));

}







	//String cadena = "abc";

	AfdMachine ADF = new AfdMachine(v,  Alfabeto);


//--------------------------------------------------LLAMA FUNCION QUE VERIFICA LA CADENA CON ALFABETO-----


while (c.hasNextLine()) {

   String l = "";
   String cadena =c.nextLine();   
 // System.out.println(cadena);

for(int i=0;i<cadena.length();i++){
//System.out.println(cadena);
  l = ""+cadena.charAt(i);
  //System.out.println(l+"-envio");
  
 ADF.cambiaEstado(l, cadena.length());

}
System.out.println(ADF.resultado());

}










/*for(int i=0;i<cad.length;i++){
System.out.println(cad[j]);
	String l = " "+ cad[j].charAt(i);
	System.out.println(l+"-envio2");
//  ADF.cambiaEstado(l);
  
}*/



//System.out.println(ADF.dameEstado());
//System.out.println(ADF.dameConteo());




	





//----------------------------------------------------------------------------------------------------------------------------
		} catch (Exception ex) {
			System.out.println("Mensaje: " + ex.getMessage());
		} finally {
			// Cerramos el fichero tanto si la lectura ha sido correcta o no
			try {
				if (s != null)
					s.close();
			} catch (Exception ex2) {
				System.out.println("Mensaje 2: " + ex2.getMessage());
			}
		}

//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		



	}
}