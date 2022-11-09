
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.util.Stack;

/**
 *
 * @author yisus
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private String lastDataType = "";
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<Token> program;
    private ArrayList<Token> errores;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;
    private int codinter = 0;
    File f;
    FileWriter w;
    BufferedWriter bw;
    PrintWriter wr;
    String[][] codeIntermedio = new String[999][4];
    int iniciofor = 0;
    int inicioopera = 0;
    int finfor = 0;
    boolean entrofor = false;
    boolean esor = false;
    boolean primeror = true;

    /*
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compilador Equipo 12";
        setLocationRelativeTo(null);
        setTitle(title);
        directorio = new Directory(this, jtpCode, title, ".mgm");
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode);
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();
            colorAnalysis();
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart();
        });
        tokens = new ArrayList<>();
        program = new ArrayList<>();
        errores = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblErrors = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblErrors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Token", "Lexema", "Renglón", "Descripción"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblErrors.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblErrors);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                                .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        clearFields();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            clearFields();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            clearFields();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick();
        if (codeHasBeenCompiled) {
            if (!errors.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE);
            } else {
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";");
                System.out.println(codeBlock);
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec();
                System.out.println(blocksOfCode);

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    private void compile() {
        clearFields();
        lexicalAnalysis();
        fillTableTokens();
        syntacticAnalysis();
        semanticAnalysis();
        printConsole();
        printCodeIntermedio();
        codeHasBeenCompiled = true;
    }

    private void printCodeIntermedio() {
        try {
            f = new File("codigo_intermedio.txt");
            w = new FileWriter(f);
            bw = new BufferedWriter(w);
            wr = new PrintWriter(bw);

            wr.write("Código intermedio\n");
            primeror = true;
            for (int i = 0; i < codinter; i++) {
                wr.append("\n");
                for (int j = 0; j < codeIntermedio[i].length; j++) {
                    if (codeIntermedio[i][j].equals("FALSE ")) {
                        if (!esor || (esor && !primeror)) {
                            codeIntermedio[i][j + 1] = finfor + "";
                            System.out.println("Entre a editar false");
                        } else if (esor && primeror) {
                            primeror = false;
                        }
                    } else if (codeIntermedio[i][j].equals("TRUE ")) {
                        if (esor) {
                            System.out.println("inicioopera: " + inicioopera);
                            codeIntermedio[i][j + 1] = inicioopera + "";
                        }
                    }
                    wr.append(codeIntermedio[i][j]);
                }
            }

            wr.close();
            bw.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha sucedido un error " + e);
        }
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                program.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors);

        /* Mostrar gramáticas */
        gramatica.show();
    }

    private void semanticAnalysis() {
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("color.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));
    }

    private void fillTableTokens() {
        // Recorrido 1
        program.forEach(token -> {
            String lexema = token.getLexeme();
            String tipo = token.getLexicalComp();
            if (tipo.equals("IDENTIFICADOR")) {
                tipo = lastDataType;
            } else if (tipo.equals("PUNTO_COMA") || tipo.equals("OP_ASIG")) {
                lastDataType = "";
            } else if (tipo.equals("TIPO_DATO")) {
                lastDataType = lexema;
            }

            if (!tipo.equals("I-Var") && !tipo.equals("S-Var") && !tipo.equals("Ch-Var") && !tipo.equals("ERROR")) {
                tipo = "";
            }

            boolean repetido = false;
            // No se repiten los tokens
            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).getLexeme().equals(token.getLexeme())) {
                    repetido = true;
                }
            }
            if (!repetido) {
                String newTipo = tipo;
                if (tipo.equals("")) {
                    newTipo = "null";
                }
                Token newToken = new Token(lexema, newTipo, token.getLine(), token.getColumn());
                tokens.add(newToken);
                Object[] data = new Object[]{lexema, tipo}; //, "[" + token.getLine() + ", " + token.getColumn() + "]"
                Functions.addRowDataInTable(tblTokens, data);
            }
        });

        // Recorrido 2
        Token[][] lineas = new Token[9999][999];
        Token[] lineaAux = new Token[999];
        int linea = 0;
        int counter = 0;
        boolean esperar = false;
        // Armado de lineas
        for (int i = 0; i < program.size(); i++) {
            boolean bien = false;
            if (program.get(i).getLexeme().equals(";")) {
                linea++;
                counter = 0;
            } else if (program.get(i).getLexeme().equals("}")) {
                if (esperar) {
                    linea++;
                    counter = 0;
                    for (int j = 0; lineaAux[j] != null; j++) {
                        lineas[linea][j] = lineaAux[j];
                    }
                    esperar = false;
                    lineaAux = new Token[999];
                }
                linea++;
                counter = 0;
                lineas[linea][counter] = program.get(i);
                counter++;
            } else if (program.get(i).getLexeme().equals(")")) {
                for (int j = 0; lineas[linea][j] != null; j++) {
                    if (lineas[linea][j].equals("(")) {
                        bien = true;
                        break;
                    }
                }
                if (bien) {
                    lineas[linea][counter] = program.get(i);
                    counter++;
                    bien = false;
                } else {
                    for (int j = 0; lineas[linea][j] != null; j++) {
                        lineaAux[j] = lineas[linea][j];
                    }
                    esperar = true;
                    lineas[linea] = new Token[999];
                    counter = 0;
                    lineas[linea][counter] = program.get(i);
                    counter++;
                }
            } else {
                lineas[linea][counter] = program.get(i);
                counter++;
            }
        }
        for (int i = 0; i < linea; i++) {
            String tipo_val = "";
            for (int j = 0; lineas[i][j] != null; j++) {
                if (lineas[i][j].getLexeme().equals("=")) {
                    for (int a = 0; a < tokens.size(); a++) {
                        if (j != 0 && lineas[i][j - 1].getLexeme().equals(tokens.get(a).getLexeme())) {
                            tipo_val = tokens.get(a).getLexicalComp();
                            if (tokens.get(a).getLexicalComp().equals("null")) {
                                errores.add(lineas[i][j - 1]);
                                Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j - 1].getLexeme(), lineas[i][j - 1].getLine(), "Variable indefinida"};
                                Functions.addRowDataInTable(tblErrors, data);
                            }
                        }
                    }
                } else if (j != 0 && (lineas[i][j - 1].getLexeme().equals("=") || lineas[i][j - 1].getLexicalComp().equals("OP_ARIT")) && lineas[i][j].getLexicalComp().equals("IDENTIFICADOR")) {
                    for (int a = 0; a < tokens.size(); a++) {
                        if (lineas[i][j].getLexeme().equals(tokens.get(a).getLexeme())) {
                            String tipo_actual = tokens.get(a).getLexicalComp();
                            // I-Var reglas
                            if (tipo_val.equals("I-Var")) {
                                if (!tipo_actual.equals("I-Var")) {
                                    if (tipo_actual.equals("null")) {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Variable indefinida"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    } else {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Incompatibilidad de tipos, I-Var"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    }
                                }
                                // S-Var reglas
                            } else if (tipo_val.equals("S-Var")) {
                                if (!tipo_actual.equals("S-Var") && !tipo_actual.equals("I-Var")) {
                                    if (tipo_actual.equals("null")) {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Variable indefinida"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    } else {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Incompatibilidad de tipos, S-Var"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    }

                                }
                                // Ch-Var reglas
                            } else if (tipo_val.equals("Ch-Var")) {
                                if (!tipo_actual.equals("Ch-Var")) {
                                    if (tipo_actual.equals("null")) {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Variable indefinida"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    } else {
                                        errores.add(lineas[i][j]);
                                        Object[] data = new Object[]{"ErrSem" + errores.size(), lineas[i][j].getLexeme(), lineas[i][j].getLine(), "Incompatibilidad de tipos, Ch-Var"};
                                        Functions.addRowDataInTable(tblErrors, data);
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }

        // Tercer recorrido
        for (int i = 0; i < linea; i++) {
            for (int j = 0; lineas[i][j] != null; j++) {
                if (lineas[i][j].getLexeme().equals("||")) {
                    esor = true;
                    System.out.println("Es OR");
                }
            }
            for (int j = 0; lineas[i][j] != null; j++) {
                if (lineas[i][j].getLexicalComp().equals("S-Var") || lineas[i][j].getLexicalComp().equals("Ch-Var") || lineas[i][j].getLexicalComp().equals("I-Var")) {
                    break;
                } else if (j != 0 && (lineas[i][j].getLexeme().equals("=") || lineas[i][j].getLexeme().equals("<") || lineas[i][j].getLexeme().equals(">") || lineas[i][j].getLexeme().equals("<=") || lineas[i][j].getLexeme().equals(">=") || lineas[i][j].getLexeme().equals("==") || lineas[i][j].getLexeme().equals("!="))) {
                    String operacion = "";

                    for (int x = j - 1; lineas[i][x] != null; x++) {
                        operacion += " " + lineas[i][x].getLexeme();
                    }
                    convertirATriplos(operacion);
                    break;
                } else if (lineas[i][j].getLexeme().equals("}")) {

                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t \t";
                    codeIntermedio[codinter - 1][2] = iniciofor + " ";
                    codeIntermedio[codinter - 1][3] = "JMP";

                    System.out.println(codinter + "\t " + iniciofor + " " + "JMP");
                    iniciofor = 0;
                    finfor = codinter + 1;
                } else if (lineas[i][j].getLexeme().equals("12for12")) {
                    entrofor = true;
                    finfor = 0;
                } else if (lineas[i][j].getLexeme().equals("{")) {
                    inicioopera = codinter + 1;
                }
            }
        }
    }

    private void convertirATriplos(String op) {
        String[] arrayOp = op.split(" ");
        boolean init = false;
        boolean init2 = false;
        boolean T1 = false;
        boolean paren = false;
        int parente = 0;
        for (int i = 0; i < arrayOp.length; i++) {
            if (arrayOp[i].equals("(")) {
                String[] subArr = new String[999];
                int n = 0;
                for (int j = i + 1; j < arrayOp.length; j++) {
                    if (arrayOp[j].equals(")")) {
                        break;
                    }
                    subArr[n] = arrayOp[j];
                    n++;
                }
                T1 = imprimirTriplo(subArr, init, init2, T1);
                paren = true;
                parente++;
            }
        }

        String[] auxArr = new String[999];
        boolean ignorar = false;
        String opera = "";
        int b = 0;

        if (parente % 2 == 0) {
            paren = T1;
        } else {
            paren = !T1;
        }
        init2 = false;
        for (int x = 0; x < arrayOp.length; x++) {
            if (arrayOp[x].equals("(")) {
                ignorar = true;
                if (paren) {
                    auxArr[b] = "T2";
                    opera += " T2";
                    paren = false;

                } else {
                    auxArr[b] = "T1";
                    opera += " T1";
                    paren = true;
                }
                b++;
            }

            if (!ignorar) {
                auxArr[b] = arrayOp[x];
                opera += " " + arrayOp[x];
                b++;
            }

            if (arrayOp[x].equals(")")) {
                ignorar = false;
            }

        }
        imprimirTriplo(auxArr, init, init2, T1);
    }

    private boolean imprimirTriplo(String[] subArr, boolean init, boolean init2, boolean T1) {
        for (int j = 0; subArr[j] != null; j++) {
            if (subArr[j].equals("*") || subArr[j].equals("/")) {
                if (subArr[j + 1].equals("T1") || T1) {
                    if (!init2) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T2 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T2 " + subArr[j - 1] + " =");
                        init2 = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T2 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T2 " + subArr[j + 1] + " " + subArr[j]);
                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T2";
                            b++;
                            init = false;
                        }
                    }
                    subArr = auxArr;
                } else {
                    if (!init && !subArr[j - 1].equals("T1")) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T1 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T1 " + subArr[j - 1] + " =");
                        init = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T1 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T1 " + subArr[j + 1] + " " + subArr[j]);

                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T1";
                            b++;
                        }
                    }
                    subArr = auxArr;
                }
                T1 = !T1;
            }
        }
        for (int j = 0; subArr[j] != null; j++) {
            if (subArr[j].equals("%")) {
                if (subArr[j + 1].equals("T1") || T1) {
                    if (!init2) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T2 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T2 " + subArr[j - 1] + " =");
                        init2 = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T2 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T2 " + subArr[j + 1] + " " + subArr[j]);
                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T2";
                            b++;
                            init = false;
                        }
                    }
                    subArr = auxArr;
                } else {
                    if (!init && !subArr[j - 1].equals("T1")) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T1 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T1 " + subArr[j - 1] + " =");
                        init = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T1 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T1 " + subArr[j + 1] + " " + subArr[j]);

                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T1";
                            b++;
                        }
                    }
                    subArr = auxArr;
                }
                T1 = !T1;
            }
        }
        for (int j = 0; subArr[j] != null; j++) {
            if (subArr[j].equals("+") || subArr[j].equals("-")) {
                if (subArr[j + 1].equals("T1") || T1) {
                    if (!init2) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T2 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T2 " + subArr[j - 1] + " =");
                        init2 = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T2 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T2 " + subArr[j + 1] + " " + subArr[j]);
                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T2";
                            b++;
                            init = false;
                        }
                    }
                    subArr = auxArr;
                } else {
                    if (!init && !subArr[j - 1].equals("T1")) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T1 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T1 " + subArr[j - 1] + " =");
                        init = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T1 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T1 " + subArr[j + 1] + " " + subArr[j]);

                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T1";
                            b++;
                        }
                    }
                    subArr = auxArr;
                }
                T1 = !T1;
            }
        }
        for (int j = 0; subArr[j] != null; j++) {
            if (j != 0 && subArr[j].equals("=")) {
                if (!init && !subArr[j - 1].equals("T1")) {
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T1 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " =";

                    System.out.println(codinter + "\t T1 " + subArr[j + 1] + " =");
                    init = true;
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t " + subArr[j - 1];
                    codeIntermedio[codinter - 1][2] = " T1 ";
                    codeIntermedio[codinter - 1][3] = subArr[j];

                    System.out.println(codinter + "\t " + subArr[j - 1] + " T1 " + subArr[j]);
                } else {
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t " + subArr[j - 1];
                    codeIntermedio[codinter - 1][2] = " " + subArr[j + 1] + " ";
                    codeIntermedio[codinter - 1][3] = subArr[j];

                    System.out.println(codinter + "\t " + subArr[j - 1] + " " + subArr[j + 1] + " " + subArr[j]);
                }
            }
        }
        for (int j = 0; subArr[j] != null; j++) {
            if (subArr[j].equals("<") || subArr[j].equals(">") || subArr[j].equals("<=") || subArr[j].equals(">=") || subArr[j].equals("==") || subArr[j].equals("!=")) {
                if (entrofor) {
                    iniciofor = codinter + 1;
                    entrofor = false;
                }
                if (subArr[j + 1].equals("T1") || T1) {
                    if (!init2) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T2 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T2 " + subArr[j - 1] + " =");
                        init2 = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T2 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T2 " + subArr[j + 1] + " " + subArr[j]);
                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T2";
                            b++;
                            init = false;
                        }
                    }
                    subArr = auxArr;
                } else {
                    if (!init && !subArr[j - 1].equals("T1")) {
                        codinter++;

                        codeIntermedio[codinter - 1][0] = codinter + "";
                        codeIntermedio[codinter - 1][1] = "\t T1 ";
                        codeIntermedio[codinter - 1][2] = subArr[j - 1];
                        codeIntermedio[codinter - 1][3] = " =";

                        System.out.println(codinter + "\t T1 " + subArr[j - 1] + " =");
                        init = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t T1 ";
                    codeIntermedio[codinter - 1][2] = subArr[j + 1];
                    codeIntermedio[codinter - 1][3] = " " + subArr[j];

                    System.out.println(codinter + "\t T1 " + subArr[j + 1] + " " + subArr[j]);

                    String[] auxArr = new String[999];
                    int b = 0;
                    for (int x = 0; subArr[x] != null; x++) {
                        if (subArr[x] != subArr[j - 1] && subArr[x] != subArr[j] && subArr[x] != subArr[j + 1]) {
                            auxArr[b] = subArr[x];
                            b++;
                        } else if (subArr[x].equals(subArr[j])) {
                            auxArr[b] = "T1";
                            b++;
                        }
                    }
                    subArr = auxArr;
                }
                T1 = !T1;

                if (esor && primeror) {
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t \t";
                    codeIntermedio[codinter - 1][2] = "TRUE ";
                    codeIntermedio[codinter - 1][3] = inicioopera + "";

                    System.out.println(codinter + "\t \t" + "TRUE " + "x");
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t \t";
                    codeIntermedio[codinter - 1][2] = "FALSE ";
                    codeIntermedio[codinter - 1][3] = (codinter + 1) + "";

                    System.out.println(codinter + "\t \t" + "FALSE " + "x");
                    primeror = false;
                } else {
                    if (esor) {
                        primeror = true;
                    }
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t \t";
                    codeIntermedio[codinter - 1][2] = "TRUE ";
                    codeIntermedio[codinter - 1][3] = (codinter + 2) + "";

                    System.out.println(codinter + "\t \t" + "TRUE " + "x");
                    codinter++;

                    codeIntermedio[codinter - 1][0] = codinter + "";
                    codeIntermedio[codinter - 1][1] = "\t \t";
                    codeIntermedio[codinter - 1][2] = "FALSE ";
                    codeIntermedio[codinter - 1][3] = "x";

                    System.out.println(codinter + "\t \t" + "FALSE " + "x");
                }
            }
        }
        return T1;
    }

    private void printConsole() {
        int sizeErrors = errors.size();
        if (sizeErrors > 0) {
            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {
                String strError = String.valueOf(error);
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");
        } else {
            jtaOutputConsole.setText("Compilación terminada...");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens);
        Functions.clearDataInTable(tblErrors);
        jtaOutputConsole.setText("");
        program.clear();
        tokens.clear();
        errors.clear();
        errores.clear();
        identProd.clear();
        identificadores.clear();
        codeHasBeenCompiled = false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Compilador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblErrors;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
