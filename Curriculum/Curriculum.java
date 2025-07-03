package graficas.Curriculum;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Curriculum extends JFrame {

    // Componentes de la interfaz
    private JTextField textField1; // Teléfono
    private JTextField textField2; // Nombres
    private JTextField textField4; // Apellidos
    private JTextField textField6; // Email
    private JRadioButton radioButton1; // Masculino
    private JRadioButton radioButton2; // Femenino
    private JRadioButton radioButton3; // Otro
    private JCheckBox checkBox1; // Leer
    private JCheckBox checkBox2; // Viajar
    private JCheckBox checkBox3; // Deportes
    private JCheckBox checkBox4; // Música
    private JTextArea textArea1; // Experiencia laboral
    private JComboBox<String> comboBox1; // Nivel de instrucción
    private JTable table1; // Formación académica
    private JButton guardarButton;
    private JButton limpiarButton;
    private JButton salirButton;
    private JButton agregarFormacionButton;

    // Componentes adicionales
    private ButtonGroup generoGroup;
    private DefaultTableModel tableModel;

    public Curriculum() {
        initComponents();
        setupEventHandlers();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Hoja de Vida");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Inicializar todos los componentes
        textField1 = new JTextField(20); // Teléfono
        textField2 = new JTextField(20); // Nombres
        textField4 = new JTextField(20); // Apellidos
        textField6 = new JTextField(20); // Email

        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();

        checkBox1 = new JCheckBox();
        checkBox2 = new JCheckBox();
        checkBox3 = new JCheckBox();
        checkBox4 = new JCheckBox();

        textArea1 = new JTextArea();
        comboBox1 = new JComboBox<>();
        table1 = new JTable();

        guardarButton = new JButton();
        limpiarButton = new JButton();
        salirButton = new JButton();
        agregarFormacionButton = new JButton();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 5; gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel("Hoja de vida"), gbc);


        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField2, gbc);


        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField4, gbc);


        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField6, gbc);


        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Telefono:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField1, gbc);

        generoGroup = new ButtonGroup();


        radioButton1.setText("Masculino");
        radioButton2.setText("Femenino");
        radioButton3.setText("Otro");
        generoGroup.add(radioButton1);
        generoGroup.add(radioButton2);
        generoGroup.add(radioButton3);


        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Genero:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 1;
        add(radioButton1, gbc);
        gbc.gridx = 2;
        add(radioButton2, gbc);
        gbc.gridx = 3;
        add(radioButton3, gbc);

        checkBox1.setText("Leer");
        checkBox2.setText("Viajar");
        checkBox3.setText("Deportes");
        checkBox4.setText("Música");

        // Hobbies
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 1;
        add(new JLabel("Hobbies:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 1;
        add(checkBox1, gbc);
        gbc.gridx = 2;
        add(checkBox2, gbc);
        gbc.gridx = 3;
        add(checkBox3, gbc);
        gbc.gridx = 4;
        add(checkBox4, gbc);

        textArea1.setRows(4);
        textArea1.setColumns(30);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea1);

        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 1;
        add(new JLabel("Experiencia Laboral"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        String[] nivelesEducacion = {
                "Seleccionar...",
                "Educación Básica",
                "Bachiller",
                "Técnico",
                "Tecnólogo",
                "Tercer Nivel (Licenciatura/Ingeniería)",
                "Cuarto Nivel (Maestría)",
                "Doctorado (PhD)"
        };
        comboBox1.setModel(new DefaultComboBoxModel<>(nivelesEducacion));

        // Nivel de instrucción
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Nivel de Instruccion"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 4; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(comboBox1, gbc);

        // Configurar JTable para formación académica
        String[] columnNames = {"Institución", "Título/Curso", "Año", "Duración"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(table1);
        tableScrollPane.setPreferredSize(new Dimension(600, 100));

        gbc.gridx = 0; gbc.gridy = 9; gbc.gridwidth = 5; gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        add(tableScrollPane, gbc);

        gbc.gridx = 2; gbc.gridy = 10; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0; gbc.weighty = 0; gbc.anchor = GridBagConstraints.CENTER;
        agregarFormacionButton.setText("Agregar Formacion");
        add(agregarFormacionButton, gbc);

        guardarButton.setText("Guardar");
        limpiarButton.setText("Limpiar");
        salirButton.setText("Salir");

        gbc.gridy = 12;
        gbc.gridx = 1;
        add(guardarButton, gbc);
        gbc.gridx = 2;
        add(limpiarButton, gbc);
        gbc.gridx = 3;
        add(salirButton, gbc);
    }

    private void setupEventHandlers() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion = JOptionPane.showConfirmDialog(
                        Curriculum.this,
                        "¿Está seguro que desea salir?",
                        "Confirmar salida",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        agregarFormacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarFormacion();
            }
        });
    }

    private void guardarDatos() {

        if (!validarCampos()) {
            return;
        }

        try {
            FileWriter writer = new FileWriter("C:\\Users\\USER\\Downloads\\hoja_de_vida1.txt");

            writer.write("==============================================\n");
            writer.write("            HOJA DE VIDA\n");
            writer.write("==============================================\n\n");

            writer.write("DATOS PERSONALES:\n");
            writer.write("- Nombres: " + textField2.getText().trim() + "\n");
            writer.write("- Apellidos: " + textField4.getText().trim() + "\n");
            writer.write("- Email: " + textField6.getText().trim() + "\n");
            writer.write("- Teléfono: " + textField1.getText().trim() + "\n");

            String genero = "";
            if (radioButton1.isSelected()) genero = "Masculino";
            else if (radioButton2.isSelected()) genero = "Femenino";
            else if (radioButton3.isSelected()) genero = "Otro";
            writer.write("- Género: " + genero + "\n\n");

            writer.write("HOBBIES E INTERESES:\n");
            ArrayList<String> hobbies = new ArrayList<>();
            if (checkBox1.isSelected()) hobbies.add("Leer");
            if (checkBox2.isSelected()) hobbies.add("Viajar");
            if (checkBox3.isSelected()) hobbies.add("Deportes");
            if (checkBox4.isSelected()) hobbies.add("Música");

            if (hobbies.isEmpty()) {
                writer.write("- No se han seleccionado hobbies\n\n");
            } else {
                for (String hobby : hobbies) {
                    writer.write("- " + hobby + "\n");
                }
                writer.write("\n");
            }

            writer.write("NIVEL DE INSTRUCCIÓN:\n");
            String nivelInstruccion = (String) comboBox1.getSelectedItem();
            if (nivelInstruccion.equals("Seleccionar...")) {
                writer.write("- No especificado\n\n");
            } else {
                writer.write("- " + nivelInstruccion + "\n\n");
            }

            // Experiencia laboral
            writer.write("EXPERIENCIA LABORAL:\n");
            String experiencia = textArea1.getText().trim();
            if (experiencia.isEmpty()) {
                writer.write("- No se ha especificado experiencia laboral\n\n");
            } else {
                writer.write(experiencia + "\n\n");
            }

            writer.write("FORMACIÓN ACADÉMICA:\n");
            if (tableModel.getRowCount() == 0) {
                writer.write("- No se ha registrado formación académica\n\n");
            } else {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    writer.write("- Institución: " + tableModel.getValueAt(i, 0) + "\n");
                    writer.write("  Título/Curso: " + tableModel.getValueAt(i, 1) + "\n");
                    writer.write("  Año: " + tableModel.getValueAt(i, 2) + "\n");
                    writer.write("  Duración: " + tableModel.getValueAt(i, 3) + "\n\n");
                }
            }

            writer.write("==============================================\n");
            writer.write("Documento generado automáticamente\n");
            writer.write("Fecha: " + java.time.LocalDateTime.now().format(
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
            ) + "\n");
            writer.write("==============================================\n");

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "La hoja de vida se ha guardado exitosamente en 'hoja_de_vida.txt'",
                    "Guardado exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar el archivo: " + ex.getMessage(),
                    "Error de guardado",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private boolean validarCampos() {
        if (textField2.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese los nombres",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            textField2.requestFocus();
            return false;
        }

        if (textField4.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese los apellidos",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            textField4.requestFocus();
            return false;
        }

        String email = textField6.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el email",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            textField6.requestFocus();
            return false;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un email válido",
                    "Formato incorrecto", JOptionPane.WARNING_MESSAGE);
            textField6.requestFocus();
            return false;
        }

        if (textField1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el teléfono",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            textField1.requestFocus();
            return false;
        }

        if (!radioButton1.isSelected() && !radioButton2.isSelected() && !radioButton3.isSelected()) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione el género",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void limpiarCampos() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea limpiar todos los campos?",
                "Confirmar limpieza",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            textField1.setText("");
            textField2.setText("");
            textField4.setText("");
            textField6.setText("");
            textArea1.setText("");

            // Limpiar selecciones
            generoGroup.clearSelection();
            checkBox1.setSelected(false);
            checkBox2.setSelected(false);
            checkBox3.setSelected(false);
            checkBox4.setSelected(false);

            // Resetear ComboBox
            comboBox1.setSelectedIndex(0);

            // Limpiar tabla
            tableModel.setRowCount(0);

            // Enfocar primer campo
            textField2.requestFocus();
        }
    }

    private void agregarFormacion() {
        JDialog dialog = new JDialog(this, "Agregar Formación Académica", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos del diálogo
        JTextField institucionField = new JTextField(20);
        JTextField tituloField = new JTextField(20);
        JTextField añoField = new JTextField(20);
        JTextField duracionField = new JTextField(20);

        // Agregar componentes al diálogo
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        dialog.add(new JLabel("Institución:"), gbc);
        gbc.gridx = 1;
        dialog.add(institucionField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        dialog.add(new JLabel("Título/Curso:"), gbc);
        gbc.gridx = 1;
        dialog.add(tituloField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        dialog.add(new JLabel("Año:"), gbc);
        gbc.gridx = 1;
        dialog.add(añoField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        dialog.add(new JLabel("Duración:"), gbc);
        gbc.gridx = 1;
        dialog.add(duracionField, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton agregarBtn = new JButton("Agregar");
        JButton cancelarBtn = new JButton("Cancelar");

        agregarBtn.addActionListener(e -> {
            if (institucionField.getText().trim().isEmpty() ||
                    tituloField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog,
                        "Por favor complete al menos la institución y el título/curso",
                        "Campos requeridos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Object[] rowData = {
                    institucionField.getText().trim(),
                    tituloField.getText().trim(),
                    añoField.getText().trim().isEmpty() ? "No especificado" : añoField.getText().trim(),
                    duracionField.getText().trim().isEmpty() ? "No especificado" : duracionField.getText().trim()
            };
            tableModel.addRow(rowData);
            dialog.dispose();
        });

        cancelarBtn.addActionListener(e -> dialog.dispose());

        buttonPanel.add(agregarBtn);
        buttonPanel.add(cancelarBtn);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(buttonPanel, gbc);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Curriculum().setVisible(true);
        });
    }
}