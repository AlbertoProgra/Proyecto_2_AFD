import java.io.*; //Incluyo la clase File
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Vector;
import java.util.Scanner;

public class AfdMachine{

	public static String[] separarPorComas(String valor){
		return valor.split(",");
	}

	public static String funcion_i(Vector vect, String cuerda){
		Vector<String> vect2 = vect;
		String posicion_simbolo_columna = "";
		String patron_finales = "[";
		String func1 = "RECHAZADA";
		String[] alfabeto = separarPorComas(vect2.elementAt(1));
		String[] estadosFinales = separarPorComas(vect2.elementAt(0));
		for (int m=0;m<estadosFinales.length;m++) {
			if (m<estadosFinales.length-1) {
				patron_finales += estadosFinales[m];
			} else {
				patron_finales += estadosFinales[m] + "]";
			}
		}
		for (int i=0;i<cuerda.length();i++) {
			for (int j=0;j<alfabeto.length;j++) {
				if(cuerda.substring(i , i+1).equals(alfabeto[j])){ posicion_simbolo_columna+= Integer.toString(i);}
			}
		}
		if (posicion_simbolo_columna.length() == cuerda.length()) {
			int i = 3;
			String[] e3;
			for (int j=0;j<posicion_simbolo_columna.length();j++) {
					e3 = separarPorComas(vect2.elementAt(i));
						for (int k=0;k<e3.length;k++) {
							if (posicion_simbolo_columna.charAt(j) == k) {
								i += Integer.parseInt(e3[k]);
								i = i-1;
								if (i == 0) {
									//System.out.println("no-entro");
									func1 = func1;
								} else if (Pattern.matches(patron_finales,Integer.toString(i))) {
									//System.out.println("entro");
									 func1 = "ACEPTADA";
								}			
							}
						}
				}			
		} else {
			//func1 = "La cuerda leida no contiene ningun simbolo que pertenezca al alfabeto de entrada";
			//return func1;
		}
		return func1;
	}

	public static String funcion_b(String afd, String archivo){
		String func2 ="";//Aca va la funcion cuando elegis -b (bach)
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

		//Llamada a funciones
		if (flag != 0) {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String cuerda = bf.readLine();
			if (Pattern.matches(".+\\.afd",args[0])) {
				File archivo_afd = new File(name_afd); //Si el nombre del archivo conicide con el patron se abre el archivo
				Scanner s = null;

				try { //Se lee el contenido del archivo.afd
					s = new Scanner(archivo_afd);
					int cont_lineas=0; 
					Vector<String> vect = new Vector<String>();//Se crea un vector con tamaño = a 10 por default, para que almacene cada linea de la matriz ingresada

					//Ciclo que recorre todas la lineas de la matriz y cada linea son guardas en vectores
					while (s.hasNextLine()) {
						vect.add (s.nextLine());
						cont_lineas++; //Cuenta las lineas de la matriz
					}


					if (flag == 1) {
						if (cuerda.length() != 0) {
							respuesta = funcion_i(vect , cuerda);
							System.out.println(respuesta);
						} //Se termina el programa debido a que cuerda == a 0
					} else {
					//respuesta = funcion_b(args[0] , archivo);
					}



				} catch (Exception ex) {
					System.out.println("Mensaje: " + ex.getMessage());
				} finally {
					//Se Cierra el fichero tanto si la lectura ha sido correcta o no
					try {
						if (s != null)
						s.close();
					} catch (Exception ex2) {
						System.out.println("Mensaje 2: " + ex2.getMessage());
					}
				}

				
			} else {
				System.out.println("[Parametro incorrecto en posicion 0] \n");
			}
		}
	//System.out.println("\n" + name_afd + "\n" + op_1 + "\n"+ name_arch_cuerd);
	}
}