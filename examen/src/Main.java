import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncuestaApp extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTextField[] respuestas;
    private String[] preguntas = {
            "¿Cuál es tu nombre?",
            "¿Qué edad tienes?",
            "¿Cuál es tu comida favorita?",
            "¿Cuál es tu deporte favorito?"
    };

    public EncuestaApp() {
        setTitle("Encuesta");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        respuestas = new JTextField[preguntas.length];

        // Panel de selección de encuesta
        JPanel seleccionPanel = new JPanel();
        seleccionPanel.setLayout(new BorderLayout());

        JLabel seleccionLabel = new JLabel("Selecciona una encuesta para comenzar", SwingConstants.CENTER);
        seleccionPanel.add(seleccionLabel, BorderLayout.NORTH);

        JButton empezarBtn = new JButton("Comenzar Encuesta");
        seleccionPanel.add(empezarBtn, BorderLayout.SOUTH);

        // Panel de preguntas
        JPanel preguntasPanel = new JPanel();
        preguntasPanel.setLayout(new GridLayout(preguntas.length + 1, 2)); // Una fila por cada pregunta + botón final
        for (int i = 0; i < preguntas.length; i++) {
            JLabel preguntaLabel = new JLabel(preguntas[i]);
            respuestas[i] = new JTextField(20);
            preguntasPanel.add(preguntaLabel);
            preguntasPanel.add(respuestas[i]);
        }

        JButton finalizarBtn = new JButton("Finalizar");
        preguntasPanel.add(new JLabel("")); // Espacio vacío
        preguntasPanel.add(finalizarBtn);

        // Panel de agradecimiento
        JPanel agradecimientoPanel = new JPanel(new BorderLayout());
        JLabel mensajeFinalLabel = new JLabel("¡Gracias por completar la encuesta!", SwingConstants.CENTER);
        agradecimientoPanel.add(mensajeFinalLabel, BorderLayout.CENTER);

        // Agregar los paneles al CardLayout
        mainPanel.add(seleccionPanel, "Seleccion");
        mainPanel.add(preguntasPanel, "Preguntas");
        mainPanel.add(agradecimientoPanel, "Agradecimiento");

        // Acción del botón "Comenzar Encuesta"
        empezarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "Preguntas");
            }
        });

        // Acción del botón "Finalizar"
        finalizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRespuestas();
                cardLayout.show(mainPanel, "Agradecimiento");
            }
        });

        add(mainPanel);
        cardLayout.show(mainPanel, "Seleccion");
    }

    private void mostrarRespuestas() {
        System.out.println("Respuestas del usuario:");
        for (int i = 0; i < respuestas.length; i++) {
            System.out.println(preguntas[i] + ": " + respuestas[i].getText());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EncuestaApp().setVisible(true);
            }
        });
    }
}
