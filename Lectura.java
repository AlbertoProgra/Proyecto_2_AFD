import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Lectura {
  public int estado;

	public Lectura(){
   this.estado = 1;
	}

	public int dameEstado(){
		return estado;
	}

	public void cambiaEstado(char caracter){
    

	}

	private static String[] separarcomas(String valor) {
		return valor.split(",");
	}



//---------------------------------------------------------MAIN----------------------------------------------
	public static void main(String[] args) {

		Lectura ADF = new Lectura();
		String cadena = "oscar";

		for(int i=0; i<cadena.length(); i++){
			System.out.println(cadena.charAt(i));
			ADF.cambiaEstado(cadena.charAt(i));
		}

//-------------------------------------------------------MANEJO DE ARCHIVOS--------------------------------		

		// Fichero del que queremos leer
		File fichero = new File("afd.afd");
		Scanner s = null;

		try {
			// Leemos el contenido del fichero

			s = new Scanner(fichero);

			// Leemos linea a linea el fichero
			String linea = null;
			int cont=0;

			//Se crea vector con tamaño x, para que almacene cada linea de la matriz ingresada
			Vector<String> v = new Vector<String>();

			//Ciclo que recorre todas la lineas de la matriz y cada linea son guardas en vectores
			while (s.hasNextLine()) {
				v.add (s.nextLine()); 	// Guardamos la linea en un vector
				cont++; //Cuenta las lineas de la matriz
			}


			for(int i=0; i<v.size(); i++){
				System.out.println(v.elementAt(i));
			}

 //Estados que siempre van a tener el mismo indice
 String[] EstadoFinal = separarcomas(v.elementAt(0));
 String[] Alfabeto = separarcomas(v.elementAt(1));
 String[] E0 = separarcomas(v.elementAt(2));
 String[] E1 = separarcomas(v.elementAt(3));

//Prueba de como imprime el contenido de los String
 for(int i=0; i< EstadoFinal.length;i++){
 	System.out.println(EstadoFinal[i]);
 }
 for(int i=0; i< Alfabeto.length;i++){
 	System.out.println(Alfabeto[i]);
 }



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