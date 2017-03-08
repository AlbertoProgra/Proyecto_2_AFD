import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AfdMachine{
	
	static String name_afd;
	static String op_1;
	static String name_arch_cuerd;

	public static String funcion_i(String afd, String cuerda){
		String func1 =""; //Aca va la funcion cuando elegis -i (interactive)
		return func1;
	}

	public static String funcion_b(String afd, String archivo){
		String func2 ="";//Aca va la funcion cuando elegis -b (bach)
		return func2;
	}

	//Clase principal
	public static void main(String[] args) throws Exception {
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
			if (flag == 1) {
				if (cuerda.length() != 0) {
					respuesta = funcion_i(args[0] , cuerda);
				} //Se termina el programa debido a que cuerda == a 0
			} else {
				respuesta = funcion_b(args[0] , archivo);
			}
		}
	System.out.println(name_afd + "\n" + op_1 + "\n"+ name_arch_cuerd);
	}
}