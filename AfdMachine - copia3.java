import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;
import java.util.Scanner;

public class AfdMachine{

	public static String[] separarPorComas(String valor){
		return valor.split(",");
	}

	public static String funcion_i(Vector<String> vect, String cuerda){
		String posicion_simbolo_columna = "";
		String patron_finales = "[";
		String func1 = "RECHAZADA";
		String[] alfabeto = separarPorComas(vect.elementAt(1));
		String[] estadosFinales = separarPorComas(vect.elementAt(0));

		for (int m=0;m<=estadosFinales.length;m++) { //Creo patron de estados finales
			if (m<estadosFinales.length) {
				patron_finales += estadosFinales[m];
			} else {
				patron_finales += "]";
			}
		}

		for (int i=0;i<cuerda.length();i++) { //Obtengo posicion de cada caracter de la cuerda en el alfabeto de entrada
			for (int j=0;j<alfabeto.length;j++) {
				if(cuerda.substring(i , i+1).equals(alfabeto[j])) { posicion_simbolo_columna+= Integer.toString(j);} 
			}
		}

		if (posicion_simbolo_columna.length() == cuerda.length()) {
			int i = 3;
			String[] e3;//Estado 3 de la matriz

			for (int j=0;j<posicion_simbolo_columna.length();j++) {
					e3 = separarPorComas(vect.elementAt(i));
					i = 3; //Seteo de variable
						
						for (int k=0;k<e3.length;k++) {
							
							if (Integer.parseInt(posicion_simbolo_columna.substring(j , j+1)) == k) {
								i += Integer.parseInt(e3[k]);
								i = i-1;//Me da el estado a donde me dirijo consumiendo el simbolo
								if (i < 3) {//Verifico si no es el estado de error
									func1 = "RECHAZADA";
								} else if (Pattern.matches(patron_finales,Integer.toString(i))) {								
									 func1 = "ACEPTADA";
								}

								k = e3.length; //Seteo de varible
							}
						}
				}			
		}
		return func1;
	}

	public static String funcion_b(String afd, String archivo){
		String func2 ="RECHAZADA";
		return func2;
	}

	//Clase principal
	public static void main(String[] args) throws Exception {
		String name_afd = "";
		String op_1 = "";
		String name_arch_cuerd = "";
		int flag = 0;
		String respuesta ="";

		//Manejo de errores
		if ((args.length) != 0) {
			if (args.length == 2 && args[1].equals("-i")) {
				name_afd = args[0];
				op_1 = args[1];
				flag = 1;
			} else if (args.length == 3 && args[1].equals("-b") && Pattern.matches(".+\\.txt",args[2])) {
				name_afd = args[0];
				op_1 = args[1];
				name_arch_cuerd = args[2];
				flag = 2;
			} else {
				System.out.println("[Parametros incorrectos] \n");
			}
		} else {
			System.out.println("[No se ingreso ningun Parametro] \n");
		}

		//Lectura y almacenado de la cuerda y la matriz de transiciones
		if (flag != 0) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String cuerda = bf.readLine();
			if (Pattern.matches(".+\\.afd",args[0])) {
				File archivo_afd = new File(name_afd); //Si el nombre del archivo conicide con el patron se abre el archivo
				Scanner s = null;

				//Clausula de manejo de errores de lectura para la clase File
				try {
					s = new Scanner(archivo_afd);
					Vector<String> vect = new Vector<String>();//Se crea un vector con tamaño = 10 por default

					//Ciclo que recorre todas la lineas de la matriz y cada linea son guardas en vectores
					while (s.hasNextLine()) {
						vect.add (s.nextLine());
					}

					//Llamada a funciones
					if (flag == 1) {
						if (cuerda.length() != 0) {
							respuesta = funcion_i(vect , cuerda);
							System.out.println(respuesta);
						} //Se termina el programa debido a que cuerda = 0
					} else {
					//respuesta = funcion_b(args[0] , archivo);
					}

				} catch (Exception ex) {
					System.out.println("Mensaje 1: " + ex.getMessage());
				} finally {
					//Se Cierra el fichero tanto si la lectura ha sido correcta o no
					try {
						if (s != null) //No necesariamente tiene el archivo de Lectura; puede tener el error de generado de no haberlo leido 
						s.close();
					} catch (Exception ex2) {
						System.out.println("Mensaje 2: " + ex2.getMessage());
					}
				}
		
			} else {
				System.out.println("[Parametro incorrecto en posicion 0] \n");
			}
		} //Flag = 0 
	}
}