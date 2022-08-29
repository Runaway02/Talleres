package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static java.awt.Font.PLAIN;

public class Calculadora extends JFrame {

	JLabel display;
	int numBotones = 24;
	JButton botones[] = new JButton[numBotones];
	String textoBotones[] = { "%", "√", "x²", "1/x", "CE", "C", "Borrar", "/", "7", "8", "9", "X", "4", "5", "6", "-",
			"1", "2", "3", "+", "±", "0", ".", "=" };
	int xBotones[] = { 0, 86, 172, 258, 0, 86, 172, 258, 0, 86, 172, 258, 0, 86, 172, 258, 0, 86, 172, 258, 0, 86, 172,
			258 };
	int yBotones[] = { 90, 90, 90, 90, 130, 130, 130, 130, 175, 175, 175, 175, 220, 220, 220, 220, 265, 265, 265, 265,
			310, 310, 310, 310 };
	int numerosBotones[] = { 21, 16, 17, 18, 12, 13, 14, 8, 9, 10 };
	int operacionesBotones[] = { 20, 19, 15, 11, 7, 3, 2, 1, 0 };
	int anchoBoton = 89;
	int altoBoton = 46;
	boolean nuevoNumero = true;
	boolean puntoDecimal = false;
	double operando1 = 0;
	double operando2 = 0;
	double resultado = 0;
	String operacion = "";

	public Calculadora() {
		initDisplay();
		initBotones();
		initPantalla();
		eventosNumeros();
		eventoDecimal();
		eventosOperaciones(); // Eventos asociados a los botones de operaciones (+,-,*,/) de la calculadora
		eventoResultado(); // Eventos asociados al botón resultado de la calculadora
		eventoLimpiar(); // Eventos asociados al botón de limpiar "C" de la calculadora
		eventoBorrar();

	}

	private void initDisplay() {
		display = new JLabel("0");
		display.setBounds(0, 0, 342, 93); // Posición y dimensiones
		display.setOpaque(true); // Para poder darle un color de fondo
		display.setBackground(Color.BLACK); // Color de fondo
		display.setForeground(Color.GREEN); // Color de fuente
		display.setBorder(new LineBorder(Color.DARK_GRAY)); // Borde
		display.setFont(new Font("MONOSPACED", PLAIN, 24)); // Fuente
		display.setHorizontalAlignment(SwingConstants.RIGHT); // Alineamiento horizontal derecha
		getContentPane().add(display); // Añado el JLabel al JFrame
	}

	private void initBotones() {
		for (int i = 0; i < numBotones; i++) {
			botones[i] = new JButton(textoBotones[i]); // Inicializo JButton
			int size = 16; // EL botón de Resultado tendrá un tamaño de fuente menor que todos los demás
			int ancho = anchoBoton; // EL botón de Resultado será más ancho que todos los demás
			botones[i].setBounds(xBotones[i], yBotones[i], ancho, altoBoton); // Posición y dimensiones
			botones[i].setFont(new Font("MONOSPACED", PLAIN, size)); // Fuente
			botones[i].setOpaque(true); // Para poder darle un color de fondo
			botones[i].setFocusPainted(false); // Para que no salga una recuadro azul cuando tenga el foco
			botones[i].setBackground(Color.DARK_GRAY); // Color de fondo
			botones[i].setForeground(Color.WHITE); // Color de fuente
			botones[i].setBorder(new LineBorder(Color.DARK_GRAY)); // Borde
			getContentPane().add(botones[i]); // Añado el JButton al JFrame
		}
	}

	private void initPantalla() {
		getContentPane().setLayout(null); // Layout absoluto
		setTitle("Calculadora"); // Título del JFrame
		setBounds(100, 100, 358, 390); // Dimensiones del JFrame
		setResizable(false); // No redimensionable
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar proceso al cerrar ventana
		getContentPane().setBackground(Color.BLACK);
		setVisible(true); // Mostrar JFrame
	}

	private void eventosNumeros() {
		for (int i = 0; i < 10; i++) {
			int numBoton = numerosBotones[i];
			botones[numBoton].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Si es un nuevo número y no es 0, sustituyo el valor del display
					if (nuevoNumero) {
						if (!textoBotones[numBoton].equals("0")) {
							display.setText(textoBotones[numBoton]);
							nuevoNumero = false; // Ya no es un nuevo número
						}
					}
					// Si no, lo añado a los dígitos que ya hubiera
					else {
						display.setText(display.getText() + textoBotones[numBoton]);
					}
				}
			});
		}

	}

	private void eventoDecimal() {
		botones[22].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Si todavía no he añadido el punto decimal al número actual
				if (!puntoDecimal) {
					display.setText(display.getText() + textoBotones[22]);
					puntoDecimal = true; // Ya no puedo añadir el punto decimal en este número
					nuevoNumero = false; // Por si empiezo el número con punto decimal (por ejemplo, .537)
				}
			}
		});
	}

	private void eventosOperaciones() {

		for (int numBoton : operacionesBotones) { // Es la versión optimizada de for (int i = 0; i <
													// operacionesBotones.length; i++){
			botones[numBoton].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// Si no tenía ninguna operación pendiente de realizar
					if (operacion.equals("")) {
						// Asocio la operación del botón a la variable
						operacion = textoBotones[numBoton];
						// Asigno a operando2 el valor del display (como double)
						operando2 = Double.parseDouble(display.getText());
						// Reseteo para poder introducir otro número y otro decimal
						nuevoNumero = true;
						puntoDecimal = false;
						// Si tenía alguna pendiente, calculo el resultado de la anterior y luego me
						// guardo la actual
					} else {
						operando2 = resultado(); // Se almacena en operando2 para poder encadenar operaciones
													// posteriores
						operacion = textoBotones[numBoton];
					}
					// SOUT para comprobar que estoy guardando los valores adecuados
					System.out.println(operando2 + " " + operacion + " " + operando1);

				}
			});
		}

	}

	private double resultado() {

		// recojo el valor del display
		operando1 = Double.parseDouble(display.getText());

		// Selecciono y realizo operación
		switch (operacion) {

		case "+":
			resultado = operando2 + operando1;
			break;
		case "-":
			resultado = operando2 - operando1;
			break;
		case "X":
			resultado = operando2 * operando1;
			break;
		case "/":
			resultado = operando2 / operando1;
			break;
		case "x²":
			resultado = operando2 * operando2;
			break;
		case "√":
		    resultado = Math.sqrt(operando2);
		    break;
		case "1/x":
		    resultado = 1/operando2;
		    break;

		}

		// Formateo y muestro en el display
		Locale localeActual = Locale.GERMAN;
		DecimalFormatSymbols simbolos = new DecimalFormatSymbols(localeActual);
		simbolos.setDecimalSeparator('.');
		DecimalFormat formatoResultado = new DecimalFormat("#.######", simbolos);
		display.setText(String.valueOf(formatoResultado.format(resultado)));

		// Limpio variables para poder continuar
		limpiar();

		// Devuelvo el valor del resultado
		return resultado;

	}

	// Resetea los valores de la calculadora para poder continuar haciendo
	// operaciones
	private void limpiar() {

		operando1 = operando2 = 0;
		operacion = "";
		nuevoNumero = true;
		puntoDecimal = false;
	}

	private void eventoResultado() {

		botones[23].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Al pulsar el botón de resultado, directamente lo calculo y reseteo la
				// calculadora,
				// sin necesidad de almacenar el resultado para futuras operaciones
				resultado();

			}
		});

	}

	private void eventoLimpiar() {

		botones[5].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Al pulsar el botón de limpiar, se resetean el display y las variables de la
				// calculadora,
				display.setText("0");
				limpiar();
			}
		});
	}

	private void eventoBorrar() {

		botones[6].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Al pulsar el botón de limpiar, se resetean el display y las variables de la
				// calculadora,
				String cadena = display.getText();
				cadena = cadena.substring(0, cadena.length() - 1);
				display.setText(cadena);

			}
		});
	}

	public static void main(String[] args) {
		new Calculadora();
	}
}
