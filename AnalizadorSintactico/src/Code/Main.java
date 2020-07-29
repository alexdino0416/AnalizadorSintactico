package Code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.internal_error;

public class Main {
	
	public static void main(String[] args) throws internal_error, IOException, Exception {
		String route = "C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/Lexer.flex";
		String route2 = "C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/LexerCup.flex";
		String[] routeS = {"-parser", "Sintax", "C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/Sintax.cup"};
		generateLexer(route, route2, routeS);
	}
	public static void generateLexer(String route, String route2, String[] routeS) throws internal_error, IOException, Exception {
		File file = new File(route);
		JFlex.Main.generate(file);
		file = new File(route2);
		JFlex.Main.generate(file);
		java_cup.Main.main(routeS);
		
		//Verificar si los archivos ya fueron creados
		Path routeSym = Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/sym.java");
		if(Files.exists(routeSym)) {
			Files.delete(routeSym);
		}
		
		Path routeSim = Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/Sintax.java");
		if(Files.exists(routeSim)) {
			Files.delete(routeSim);
		}	
		
		//Moving cup files into project directory
		Files.move(
				Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/sym.java"),
				Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/sym.java")
		);
		Files.move(
				Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/Sintax.java"),
				Paths.get("C:/Users/leona/P2-Compiladores/AnalizadorSintactico/src/Code/Sintax.java")
		);
	}
}
