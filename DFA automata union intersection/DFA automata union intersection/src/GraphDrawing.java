
import static javax.swing.JOptionPane.showMessageDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.QuadCurve2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.*;
import java.util.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author vento
 */
public class GraphDrawing extends JFrame implements MouseListener, MouseMotionListener, KeyListener {

    HashMap<String, Backup> all = new HashMap<>();
    //Data of graph
    ArrayList<Vertex> Vertexs = new ArrayList<>();
    ArrayList<Edge_> Edge_s = new ArrayList<>();
    ArrayList<Integer> max = new ArrayList<>();
    Object selected = null;
    TempEdge TempEdge = null; //TempEdge edge

    //UI 
    Canvas c;
    String mode = "Vertex"; //Vertex,Edge_

    //set font 
    Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 24);
    int maxall = 0;
    //find size monitor
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JFrame frameHelp = new JFrame("Help");
    JFrame frameHelp1 = new JFrame("Help");
    ArrayList<JCheckBox> ll = new ArrayList<>();
    JPanel boxSave = new JPanel();
    JPanel boxOpen = new JPanel();
    JPanel boxHelp = new JPanel();
    JPanel boxHelp1 = new JPanel();
    JButton put = new JButton();
    JButton remove = new JButton();
    JButton uninon = new JButton();
    JButton intersection = new JButton();
    JButton saveButt = new JButton();
    JButton clear = new JButton();
    JButton show = new JButton();
    JButton show2 = new JButton();
    JButton add = new JButton();
    JRadioButton sr = new JRadioButton();
    JButton delete = new JButton();
    JButton in = new JButton();
    Backup backup;
    JButton openButt = new JButton();
    JButton helpButt = new JButton();
    JComboBox cb = new JComboBox();
    JComboBox cb2 = new JComboBox();
    String oto = "DFA";
    JFileChooser pathSave = new JFileChooser();
    JFileChooser pathOpen = new JFileChooser();
    int i = 1;
    JLabel helpString = new JLabel();
    JLabel helpString1 = new JLabel();
    int maxst = 0;
    //-----###e
    JPanel menubar = new JPanel();
    int shift = 50;

    //////////////////////////////// Backup ////////////////////////////////
    class Backup {

        ArrayList<Vertex> VertexsBackup;
        ArrayList<Edge_> Edge_sBackup;
        String from = "";
        ArrayList<Integer> max = new ArrayList<>();
        int count;
        int maxst = 0;
        int maxall = 0;

        public Backup() {
            count = ll.size();
            this.VertexsBackup = (ArrayList<Vertex>) Vertexs.clone();
            this.Edge_sBackup = (ArrayList<Edge_>) Edge_s.clone();
        }

    }

    GraphDrawing() {

        super("canvas");
        // create a empty canvas 
        c = new Canvas() {
            @Override
            public void paint(Graphics g) {
            }
        };
        c.setBackground(Color.white);

        // add mouse listener 
        c.addMouseListener(this);
        c.addMouseMotionListener(this);

        // add keyboard listener 
        c.addKeyListener(this);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });
        ArrayList<String> x = new ArrayList();

        cb.setFont(sanSerifFont);
        cb2.setFont(sanSerifFont);
        clear.setFont(sanSerifFont);
        saveButt.setFont(sanSerifFont);
        openButt.setFont(sanSerifFont);
        helpButt.setFont(sanSerifFont);
        delete.setFont(sanSerifFont);
        add.setFont(sanSerifFont);
        in.setFont(sanSerifFont);
        show.setFont(sanSerifFont);
        show2.setFont(sanSerifFont);
        put.setFont(sanSerifFont);
        saveButt.setFont(sanSerifFont);
        remove.setFont(sanSerifFont);
        uninon.setFont(sanSerifFont);
        intersection.setFont(sanSerifFont);
        saveButt.setFont(sanSerifFont);
        saveButt.setFont(sanSerifFont);
        helpString.setFont(sanSerifFont);
        helpString1.setFont(sanSerifFont);
        boxSave.setBackground(Color.white);
        boxOpen.setBackground(Color.white);

        boxHelp.setBackground(Color.white);
        frameHelp.add(boxHelp);
        boxHelp1.setBackground(Color.white);
        frameHelp1.add(boxHelp1);

        cb.setBounds((screenSize.width - getWidth()) - 400 + shift, 150, 300, 23);
        getContentPane().add(cb);
        cb2.setBounds((screenSize.width - getWidth()) - 400 + shift, 300, 300, 23);
        getContentPane().add(cb2);

        getContentPane().add(saveButt);
        put.setText("put");
        put.setBounds((screenSize.width - getWidth()) - 400 + shift, 250, 150, 23);
        getContentPane().add(put);
        remove.setText("remove");
        remove.setBounds((screenSize.width - getWidth()) - 400 + shift, 350, 300, 23);
        getContentPane().add(remove);
        uninon.setText("union");
        uninon.setBounds((screenSize.width - getWidth()) - 400 + shift, 400, 150, 23);
        getContentPane().add(uninon);
        intersection.setText("intersection");
        intersection.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 400, 150, 23);
        getContentPane().add(intersection);
        intersection.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 400, 150, 23);
        getContentPane().add(intersection);
        show2.setText("show");
        show2.setBounds((screenSize.width - getWidth()) - 400 + shift, 450, 300, 23);
        getContentPane().add(show2);
        clear.setText("clear");
        clear.setBounds((screenSize.width - getWidth()) - 400 + shift, 500, 300, 23);
        getContentPane().add(clear);
        sr.setText("ทำการตัดสถานะกับดัก");
        sr.setBounds((screenSize.width - getWidth()) - 400 + shift, 550, 300, 23);
        getContentPane().add(sr);
        delete.setText("delete");
        delete.setBounds((screenSize.width - getWidth()) - 400 + shift + 150, 250, 150, 23);
        getContentPane().add(delete);
        show.setText("show");
        show.setBounds((screenSize.width - getWidth()) - 400 + shift, 200, 150, 23);
        getContentPane().add(show);
        add.setText("add");
        add.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 200, 150, 23);
        getContentPane().add(add);

        getContentPane().add(saveButt);
        saveButt.setText("save");
        saveButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 100, 150, 23);
        getContentPane().add(saveButt);
        saveButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (no) {

                    boolean x = true;
                    for (Edge_ Edge_1 : Edge_s) {
                        if (Edge_1.weight.equals("-")) {
                            x = false;
                            break;
                        }
                    }
                    if (x && backup == null) {
                        Backup backup = new Backup();
                        selected = null;
                        Vertexs = new ArrayList<>();
                        Edge_s = new ArrayList<>();
                        clear();
                        if (backup.VertexsBackup.size() != 0) {
                            cb.addItem("DFA" + String.valueOf(i));
                            backup.from = "DFA" + String.valueOf(i);
                            all.put("DFA" + String.valueOf(i), backup);
                            i++;
                        }
                    }
                    if (x && !(backup == null)) {
                        if (backup.VertexsBackup.size() != 0) {
                            cb.addItem("DFA" + String.valueOf(i));

                            all.put("DFA" + String.valueOf(i), backup);
                            i++;
                        }
                    }
                    
                        if (!x) {
                            showMessageDialog(null, "กรุณาใสอักขระ");
                        }
                    
                }
            }
        });

        show2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String datatb = backup.from;
                String[] str1 = datatb.split("-");
                int re = 0;
                int count = 0;
                max = backup.max;
                maxst = backup.maxst;
                maxall = backup.maxall;
                String[][] datareal = new String[maxst][maxall + 2];
                String[] columnreal = new String[maxall + 2];
                columnreal[0] = "Status";
                columnreal[maxall + 1] = "Queue";
                for (int num = 0; num < maxall; num++) {
                    columnreal[num + 1] = "" + (char) (97 + num);
                }
                for (int i = 0; i < datareal.length; i++) {
                    for (int j = 0; j < datareal[0].length; j++) {
                        if (count == str1.length) {
                            break;
                        }
                        String check = str1[count];
                        for (int l = 0; l < check.length() - 1; l++) {
                            if (check.charAt(l) == '[' && check.charAt(l + 1) == '[') {
                                check = check.substring(l + 1);
                                str1[count] = check;
                            }
                            if (check.charAt(l) == ']' && check.charAt(l + 1) == ']') {
                                check = check.substring(0, l + 1);
                                str1[count] = check;
                            }

                        }

                        if (maxall - max.get(i + re) > 0 && max.get(i + re) + 1 == j) {

                            datareal[i][j] = "-";
                        } else {

                            datareal[i][j] = str1[count];
                            if (str1[count].indexOf("[]") != -1) {

                                ++i;
                                re--;
                            }
                            count++;

                        }
                    }
                }
                System.out.println("columnreal[j]");
                for (int j = 0; j < columnreal.length; j++) {
                    System.out.println(columnreal[j]);
                }
                for (int i = 0; i < datareal.length; i++) {
                    for (int j = 0; j < datareal[0].length; j++) {
                        System.out.print(datareal[i][j] + "#");
                    }
                    System.out.println("end");
                }
                JFrame f = new JFrame();

                JTable jt = new JTable(datareal, columnreal);
                jt.setBounds(30, 40, 200, 300);
                TableColumnModel modelcolonne = jt.getColumnModel();
                TableModel model = jt.getModel();
                int total = modelcolonne.getColumnCount();
                for (int i = 0; i < total; i++) {
                    int taille = 0;
                    int total2 = model.getRowCount();
                    for (int j = 0; j < total2; j++) {
                        if (model.getValueAt(j, i) != null) {
                            int taille2 = model.getValueAt(j, i).toString().length() * 7;
                            if (taille2 > taille) {
                                taille = taille2;
                            }
                        }
                        modelcolonne.getColumn(i).setPreferredWidth(taille);

                    }
                }
                JScrollPane sp = new JScrollPane(jt);
                f.add(sp);
                f.setSize(600, 400);

                f.setVisible(true);

            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backup = null;
                no = true;
                selected = null;
                Vertexs = new ArrayList<>();
                Edge_s = new ArrayList<>();
                clear();
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                all.remove(cb.getSelectedItem());
                cb.removeItem(cb.getSelectedItem());
            }
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cb2.removeItem(cb2.getSelectedItem());
            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                no = false;
                if (all.size() != 0) {
                    clear();
                    selected = null;
                    Vertexs = null;
                    Edge_s = null;
                    Backup backupp = all.get(cb.getSelectedItem());
                    show(backupp);
                    backup = backupp;

                    draw();

                }
            }
        });

        uninon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                backup = new Backup();
                if (ca) {
                    list();
                    ca = false;
                }
                jFrame.setVisible(false);
                joon();
                int kkk = 0;
                maxst = 0;
                maxall = kkk;
                max = new ArrayList<>();
                String data = "";
                String kk = "";
                String jj = "";

                backup = all.get((String) cb2.getItemAt(0));
                maxst += cb2.getItemCount() - 1;

                for (int j = 1; j < cb2.getItemCount(); j++) {

                    try {
                        kkk = chaeck(backup, all.get((String) cb2.getItemAt(j)));
                        cartesian(backup, all.get((String) cb2.getItemAt(j)));
                    } catch (Exception r) {
                        //  Block of code to handle errors
                    }
                    // print();

                    createAutomata(true);
                    backup = new Backup();
                    Vertexs = backup.VertexsBackup;
                    Edge_s = backup.Edge_sBackup;
                    maxst += st.size();
                    for (int k = 0; k < st.size(); k++) {
                        System.out.print(" st.size() " + st.size() + "st.size()");
                        String str = "";
                        String[] str1 = st.get(k).toString().split(",");
                        for (int l = 0; l < str1.length; l++) {
                            if (l % 2 == 1) {
                                str1[l] = " , r" + str1[l].substring(2);
                            }

                            str += str1[l];

                        }
                        data += str + "-";
                        System.out.print(" | " + str + " ");
                        kk += " | " + str + " ";
                        if (k < cha.size()) {
                            str = "";
                            str1 = cha.get(k).toString().split(",");
                            for (int l = 0; l < str1.length; l++) {
                                if (l % 2 == 1) {
                                    str1[l] = " , r" + str1[l].substring(2) + "-";
                                }
                                str += str1[l];
                                data += str1[l];
                            }
                            System.out.print(" | " + str + " | ");
                            kk += " | " + str + " | ";
                        }
                        if (k < qqqqq.size()) {
                            System.out.println(qqqqq.get(k).toString() + "  ");
                            kk += qqqqq.get(k).toString() + "  \n";
                            data += qqqqq.get(k).toString() + "-";
                        }
                        max.add(kkk);
                        System.out.println("/////////////////////--" + kkk);
                    }
                    kk += "\n\n";
                    System.out.println(data);
                    maxall = Math.max(kkk, maxall);
                }

                draw();
                System.out.println(kkk);

                if (sr.isSelected()) {
                    removetrap(kkk);
                }
                jj += " |   Staus   | ";
                for (int num = 0; num < kkk; num++) {
                    jj += "      " + ((char) (97 + num)) + "      ";
                }
                jj += "  |                    Queue    \n";
                System.out.print(jj);
                System.out.println(kk);
                // backup.from = jj+kk;
                backup.from = data;
                backup.max = (ArrayList<Integer>) max.clone();
                backup.maxall = maxall;
                backup.maxst = maxst;
            }
        });
        intersection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backup = new Backup();
                if (ca) {
                    list();
                    ca = false;
                }
                jFrame.setVisible(false);
                joon();
                int kkk = 0;
                maxst = 0;
                maxall = kkk;
                max = new ArrayList<>();
                String data = "";
                String kk = "";
                String jj = "";

                backup = all.get((String) cb2.getItemAt(0));
                maxst += cb2.getItemCount() - 1;

                for (int j = 1; j < cb2.getItemCount(); j++) {

                    try {
                        kkk = chaeck(backup, all.get((String) cb2.getItemAt(j)));
                        cartesian(backup, all.get((String) cb2.getItemAt(j)));
                    } catch (Exception r) {
                        //  Block of code to handle errors
                    }
                    // print();

                    createAutomata(false);
                    backup = new Backup();
                    Vertexs = backup.VertexsBackup;
                    Edge_s = backup.Edge_sBackup;
                    maxst += st.size();
                    for (int k = 0; k < st.size(); k++) {
                        System.out.print(" st.size() " + st.size() + "st.size()");
                        String str = "";
                        String[] str1 = st.get(k).toString().split(",");
                        for (int l = 0; l < str1.length; l++) {
                            if (l % 2 == 1) {
                                str1[l] = " , r" + str1[l].substring(2);
                            }

                            str += str1[l];

                        }
                        data += str + "-";
                        System.out.print(" | " + str + " ");
                        kk += " | " + str + " ";
                        if (k < cha.size()) {
                            str = "";
                            str1 = cha.get(k).toString().split(",");
                            for (int l = 0; l < str1.length; l++) {
                                if (l % 2 == 1) {
                                    str1[l] = " , r" + str1[l].substring(2) + "-";
                                }
                                str += str1[l];
                                data += str1[l];
                            }
                            System.out.print(" | " + str + " | ");
                            kk += " | " + str + " | ";
                        }
                        if (k < qqqqq.size()) {
                            System.out.println(qqqqq.get(k).toString() + "  ");
                            kk += qqqqq.get(k).toString() + "  \n";
                            data += qqqqq.get(k).toString() + "-";
                        }
                        max.add(kkk);
                        System.out.println("/////////////////////--" + kkk);
                    }
                    kk += "\n\n";
                    System.out.println(data);
                    maxall = Math.max(kkk, maxall);
                }

                draw();
                System.out.println(kkk);

                if (sr.isSelected()) {
                    removetrap(kkk);
                }
                jj += " |   Staus   | ";
                for (int num = 0; num < kkk; num++) {
                    jj += "      " + ((char) (97 + num)) + "      ";
                }
                jj += "  |                    Queue    \n";
                System.out.print(jj);
                System.out.println(kk);
                // backup.from = jj+kk;
                backup.from = data;
                backup.max = (ArrayList<Integer>) max.clone();
                backup.maxall = maxall;
                backup.maxst = maxst;
            }
        });

        put.addActionListener(new ActionListener() {
            boolean v = true;

            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println();
                for (int j = 0; j < cb2.getItemCount(); j++) {

                    if (cb2.getItemAt(j).equals(cb.getSelectedItem())) {
                        v = false;
                        break;
                    }
                }
                if (v) {
                    cb2.addItem(cb.getSelectedItem());
                }
                v = true;
            }
        });

        openButt.setText("open");
        openButt.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 100, 150, 23);
        getContentPane().add(openButt);
        openButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                no = false;
                try {
                    openButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        helpButt.setText("Help");
        helpButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 40, 300, 23);
        getContentPane().add(helpButt);
        helpButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpButtAction(e);
            }
        });

        //-----###e     
        menubar.setBackground(Color.cyan);
        menubar.setBounds((screenSize.width - getWidth()) - 400, 0, 400, (screenSize.height - getHeight()));
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        setBounds(0, 0, (screenSize.width - getWidth()), (screenSize.height - getHeight()));
        //setUndecorated(true);
        //setVisible(true);
        add(c);
        add(menubar);
        //   list();
        //jFrame.setVisible( false);
        // setSize(1500, 1000);
        show();
    }

    //
    int chaeckk(Backup x, Backup y) {
        int maxaf = maxaf(x, y);

        no = true;

        return maxaf;

    }

    int chaeck(Backup x, Backup y) {
        int maxaf = maxaf(x, y);
        System.out.println("1");
        thecharr(x, maxaf);
        System.out.println("2");
        thecharr(y, maxaf);
        no = true;

        return maxaf;

    }

    int maxaf(Backup x, Backup y) {
        int max = Integer.MIN_VALUE;
        for (Vertex Vertex1 : x.VertexsBackup) {
            for (Edge_ Edge_1 : x.Edge_sBackup) {
                if (Edge_1.vertexA.equals(Vertex1) && Edge_1.weight.length() != 0) {
                    String[] str = Edge_1.weight.split(",");
                    for (String string : str) {
                        //    System.out.println(string);
                        max = Math.max(max, string.charAt(0) - 96);
                    }
                }
            }
        }
        for (Vertex Vertex1 : y.VertexsBackup) {
            for (Edge_ Edge_1 : y.Edge_sBackup) {
                if (Edge_1.vertexA.equals(Vertex1) && Edge_1.weight.length() != 0) {
                    String[] str = Edge_1.weight.split(",");
                    for (String string : str) {
                        //   System.out.println(string+" "+((int) (string.charAt(0)-96)));
                        //   System.out.println("Max : "+max);
                        max = Math.max(max, string.charAt(0) - 96);
                    }
                }
            }
        }

        //System.out.println("asdasdasd     "+   max);
        return max;
    }

    void removetrap(int maxaf) {
        ArrayList<Vertex> Vertexss = new ArrayList<>();
        for (int j = 0; j < Vertexs.size(); j++) {
            Vertex Vertex = Vertexs.get(j);
            for (int k = 0; k < Edge_s.size(); k++) {
                Edge_ Edge_1 = Edge_s.get(k);
                if (Edge_1.vertexA.equals(Vertex) && Edge_1.vertexB.equals(Vertex) && Edge_1.weight.length() != 0) {
                    System.out.println(Vertex.name);
                    String[] str = Edge_1.weight.split(",");

                    System.out.println(str.length + "   " + maxaf + " " + Vertex.ap);
                    for (String string : str) {
                        System.out.println(string + ",");
                    }
                    if (str.length == maxaf) {
                        if (!Vertex.ap) {

                            Vertexss.add(Vertex);

                            draw();
                        }
                    }
                }
            }

        }
        ArrayList<Edge_> Edge_ss = new ArrayList<>();
        for (Vertex Vertexs1 : Vertexss) {
            System.out.println(Vertexs1.name);
            for (int j = 0; j < Edge_s.size(); j++) {
                if (Edge_s.get(j).vertexB.equals(Vertexs1)) {
                    Edge_ss.add(Edge_s.get(j));
                }
            }
            Vertexs.remove(Vertexs1);

        }
        for (Edge_ Edge_s1 : Edge_ss) {
            Edge_s.remove(Edge_s1);
            draw();

        }

    }

    void thecharr(Backup backup, int maxaf) {
        System.out.println("max" + maxaf);
        boolean max[] = new boolean[maxaf];
        System.out.println("maxxxx" + max.length);
        for (int j = 0; j < backup.VertexsBackup.size(); j++) {

            Vertex Vertex = backup.VertexsBackup.get(j);
            for (int k = 0; k < backup.Edge_sBackup.size(); k++) {
                Edge_ Edge_1 = backup.Edge_sBackup.get(k);

                if (Edge_1.vertexA.equals(Vertex) && Edge_1.weight.length() != 0) {

                    System.out.println(Edge_1.weight);
                    String[] str = Edge_1.weight.split(",");
                    for (String string : str) {

                        max[string.charAt(0) - 97] = true;

                    }

                }
            }
            boolean f = false;
            for (boolean b : max) {
                if (!b) {
                    f = !b;
                }
            }
            System.out.println(f);
            if (f) {
                String str = "";
                String str2 = "";
                for (int l = 0; l < max.length; l++) {
                    str2 += (char) (97 + l) + ",";
                    if (!max[l]) {
                        str += (char) (97 + l) + ",";
                    }
                }
                str = str.substring(0, str.length() - 1);
                str2 = str2.substring(0, str2.length() - 1);
                System.out.print(str2 + "  ");
                System.out.println(str);
                Vertexs = backup.VertexsBackup;
                Edge_s = backup.Edge_sBackup;
                for (Edge_ e : Edge_s) {
                    if (e.vertexA != null) {
                        int id = e.vertexA.id;
                        for (Vertex v : Vertexs) {
                            if (v.id == id) {
                                e.vertexA = v;
                                break;
                            }
                        }
                    }
                    if (e.vertexB != null) {
                        int id = e.vertexB.id;
                        for (Vertex v : Vertexs) {
                            if (v.id == id) {
                                e.vertexB = v;
                                break;
                            }
                        }
                    }
                }

                Vertex TempVertex = new Vertex(0, 0);
                Vertexs.add(TempVertex);

                draw();

                Edge_ E = new Edge_(Vertex, TempVertex);
                E.weight = str;
                Edge_s.add(E);

                draw();

                E = new Edge_(TempVertex, TempVertex);
                E.weight = str2;
                Edge_s.add(E);

                draw();

            }
            max = new boolean[maxaf];

        }
        draw();
    }

    void helpButtAction(ActionEvent e) {
        String help = "<html>";
        help += "Double click for create Vertex<br>";
        help += "Click on Vertex then type for rename<br>";
        help += "Click on Vertex or center of edge then it is blue you can edit etc move , rename , delete <br>";
        help += "Click on Vertex then press delete for remove Vertex<br>";
        help += "Press and hold spacebar with drag mouse for create edge<br>";
        help += "Click on character on edge then type for rename<br>";
        help += "Click on character on edge then drag mouse for move edge<br>";
        help += "Click on character on edge then press delete for remove edge<br>";
        help += "Press Button save for save Graph on canvas to json file<br>";
        help += "Press Button open for open Graph json file to canvas<br>";

        helpString.setText(help);
        boxHelp.add(helpString);
        boxHelp.setAutoscrolls(true);
        frameHelp.setBounds(screenSize.width / 2 - 500, screenSize.height / 2 - 200, 1000, 400);
        frameHelp.setVisible(true);
        String help1 = "<html>";

        help1 += "CTRL+E ทำการเรียกหน้าจอที่ใช้สำหรับการใส่อักขระ<br>";
        help1 += "CTRL+R ทำการลดขนาดของ automata<br>";
        help1 += "CTRL+T ทำการเพิ่มขนาดของ automata<br>";
        help1 += "CTRL+C ทำการเปลี่ยนสถานะที่เลือกให้เป็นสถานะยอมรับ<br>";
        help1 += "CTRL+N ทำการสุ่มตำแหน่งของสถานะที่อยูบนหน้าจอ<br>";
        help1 += "CTRL+K ช่วยในการจัดรูป<br>";
        help1 += "CTRL+L ช่วยในการจัดรูป<br>";
        help1 += "สามารถใช้สัญลักษณ์ลูกศร บนแป้นพิมพ์คอมพิวเตอร์ ในการขยับ Automata<br>";
        
        helpString1.setText(help1);
        boxHelp1.add(helpString1);
        boxHelp1.setAutoscrolls(true);
        frameHelp1.setBounds(screenSize.width / 2 - 500, screenSize.height / 2 - 200, 1000, 350);
        frameHelp1.setVisible(true);
    }

    void saveButtAction(ActionEvent e) throws IOException {
        pathSave.setBounds(60, 120, 75, 450);
        boxSave.add(pathSave);

        int ret = pathSave.showDialog(null, "save");
        String path = "";

        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathSave.getSelectedFile();
            path = filePath.getPath();
            save(path);
        }
    }

    void openButtAction(ActionEvent e) throws IOException {
        pathOpen.setBounds(60, 120, 750, 450);
        boxOpen.add(pathOpen);

        int ret = pathOpen.showDialog(null, "open");
        String path = "";

        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathOpen.getSelectedFile();
            path = filePath.getPath();
            open(path);

        }
    }

    public void save(String path) throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter(path);

        Set set1 = all.entrySet();

        for (int j = 0; j < cb.getItemCount(); j++) {

            Backup backup = (Backup) all.get(cb.getItemAt(j));

            writer.write(gson.toJson(backup) + "\n");

        }
        writer.close();
    }
    boolean no = true;

    public void show(Backup backup) {

        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;

        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            }
        }
    }

    public void open(String path) throws FileNotFoundException, IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        String line;
        while ((line = bufferedReader.readLine()) != null) {

            Backup backup = gson.fromJson(line, Backup.class);
            all.put(oto + String.valueOf(i), backup);
            cb.addItem(oto + String.valueOf(i));

            i++;

            Vertexs = backup.VertexsBackup;
            Edge_s = backup.Edge_sBackup;

            //bind object reference
            for (Edge_ e : Edge_s) {
                if (e.vertexA != null) {
                    int id = e.vertexA.id;
                    for (Vertex v : Vertexs) {
                        if (v.id == id) {
                            e.vertexA = v;
                            break;
                        }
                    }
                }
                if (e.vertexB != null) {
                    int id = e.vertexB.id;
                    for (Vertex v : Vertexs) {
                        if (v.id == id) {
                            e.vertexB = v;
                            break;
                        }
                    }
                }
            }
            draw();
        }
    }

    public void open(Backup backup) throws FileNotFoundException {

        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;

        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            }
        }

    }

    //set canvas is white
    public void clear() {
        Graphics2D g = (Graphics2D) c.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void selected(int x, int y) {
        Object obj = null;
        for (Vertex s : Vertexs) {
            if (s.inCircle(x, y)) {
                s.isSelect = true;
                obj = s;
                break;
            }
        }
        if (obj == null) {
            for (Edge_ t : Edge_s) {
                if (t.inLine(x, y)) {
                    t.isSelect = true;
                    obj = t;
                    break;
                }
            }
        }
        if (obj == null) {
            if (selected == null) {
                return;
            } else {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    s.isSelect = false;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.isSelect = false;
                }
                selected = null;
            }
        } else {
            if (selected == null) {
                selected = obj;
            } else {
                if (obj == selected) {
                    return;
                } else {
                    if (selected instanceof Vertex) {
                        Vertex s = (Vertex) selected;
                        s.isSelect = false;
                    } else {
                        Edge_ t = (Edge_) selected;
                        t.isSelect = false;
                    }
                    selected = obj;
                }
            }
        }
    }

    double sizep = 1.02, sizen = 0.98;
    int x;
    double kk = 24;
    int y;
    int x1, y1;
    int min = Integer.MAX_VALUE;
    boolean mem = true;

    Vertex min(Vertex s) {
        int min = Integer.MAX_VALUE;
        Vertex sss = null;
        for (Vertex ss : Vertexs) {
            if (!ss.equals(s)) {
                min = (int) Math.min(Math.sqrt(Math.pow(ss.x - s.x, 2) + Math.pow(ss.y - s.y, 2)), min);
                sss = ss;
            }
        }
        return sss;
    }

    int min(Vertex s, boolean x) {
        int min = Integer.MAX_VALUE;

        for (Vertex ss : Vertexs) {
            if (!ss.equals(s)) {
                min = (int) Math.min(Math.sqrt(Math.pow(ss.x - s.x, 2) + Math.pow(ss.y - s.y, 2)), min);

            }
        }
        return min;
    }

    void outrun(Vertex s1, Vertex s2) {
        int x, y;

        x = (s1.x + s2.x) / 2;
        y = (s1.y + s2.y) / 2;

        if (s1.x < x) {
            s1.x *= sizen * sizen;
        } else if (s1.x > x) {
            s1.x /= (sizen * sizen);
        }
        if (s1.y < y) {
            s1.y *= (sizen * sizen);
        } else if (s1.y > y) {
            s1.y /= (sizen * sizen);

        }
    }
    int k = 97;
    int o = 0;
    JScrollPane jScrollPane;
    JPanel listOfFiles;
    JButton p = new JButton("+");
    JPanel contentPane;

    void joon() {
        int kkk = 0;
        for (int j = 1; j < cb2.getItemCount(); j++) {

            kkk = Math.max(chaeckk(backup, all.get((String) cb2.getItemAt(j))), kkk);
        }

        Backup backup = all.get(cb.getSelectedItem());
        if (ll.size() != kkk) {
            int x = ll.size() - kkk;
            if (x > 0) {
                downdown();
            } else {
                upup();

            }
            joon();

        }

    }

    void upup() {
        if (o < 26) {

            o++;
            JCheckBox h = new JCheckBox("" + (char) k, true);

            ll.add(h);
            listOfFiles.add(h);
            k++;

            jFrame.setVisible(false);
        }
    }

    void downdown() {
        if (o != 0) {
            ll.remove(ll.size() - 1);
            k--;
            o--;
            listOfFiles.remove(o);
            jFrame.setVisible(false);
        }
    }
    JFrame jFrame;
    JButton n = new JButton("-");

    /**
     *
     */
    @Override
    public void list() {
        jFrame = new JFrame("Checkable list");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //do something
                putchar();
                draw();

                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jFrame.setVisible(false);
            }
        });

        contentPane = new JPanel();
        listOfFiles = new JPanel();
        listOfFiles.setLayout(new BoxLayout(listOfFiles, BoxLayout.Y_AXIS));

        JLabel lbl = new JLabel("inside the scroll");

        JLabel lbl1 = new JLabel("Other stuff");

        contentPane.add(lbl1);
        contentPane.add(p);
        contentPane.add(n);
        jScrollPane = new JScrollPane(listOfFiles);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(50, 250));
        contentPane.add(jScrollPane);
        jFrame.add(contentPane);

        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (o < 26) {

                    o++;
                    JCheckBox h = new JCheckBox("" + (char) k);
                    ll.add(h);
                    listOfFiles.add(h);
                    k++;
                    jFrame.setVisible(true);
                }
            }
        });

        n.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (o != 0) {
                    ll.remove(ll.size() - 1);
                    k--;
                    o--;
                    listOfFiles.remove(o);
                    jFrame.setVisible(false);
                    jFrame.setVisible(true);
                }
            }
        });

        jFrame.setSize(250, 300);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
    }

    HashMap<Vertex, ArrayList<Vertex>> nq = new HashMap<>();
    Backup alll = new Backup();

    public void print() {
        System.out.println();
        for (int j = 0; j < st.size(); j++) {
            String str = "";
            String[] str1 = st.get(j).toString().split(",");
            for (int l = 0; l < str1.length; l++) {
                if (l % 2 == 1) {
                    str1[l] = " , r" + str1[l].substring(2);
                }
                str += str1[l];
            }
            System.out.print(" | " + str + " ");
            if (j < cha.size()) {
                str = "";
                str1 = cha.get(j).toString().split(",");
                for (int l = 0; l < str1.length; l++) {
                    if (l % 2 == 1) {
                        str1[l] = " , r" + str1[l].substring(2);
                    }
                    str += str1[l];
                }
                System.out.print(" | " + str + " | ");
            }

            if (j < qqqqq.size()) {
                System.out.println(qqqqq.get(j).toString() + " | ");
            }
        }
    }
    Queue<ArrayList<Vertex>> q = new LinkedList<>();
    Set<ArrayList<Vertex>> ck = new HashSet<>();
    ArrayList<ArrayList<Vertex>> otoo = new ArrayList<>();
    ArrayList<ArrayList<ArrayList<Vertex>>> deq = new ArrayList<>();
    HashMap<ArrayList<Vertex>, ArrayList<ArrayList<Vertex>>> run = new HashMap<>();
    ArrayList<ArrayList<ArrayList<Vertex>>> cha = new ArrayList<>();
    ArrayList<ArrayList<Vertex>> st = new ArrayList<>();
    ArrayList<Vertex> ver = new ArrayList<>();
    ArrayList<Edge_> edg = new ArrayList<>();

    void createAutomata(boolean tt) {

        ver = new ArrayList<>();
        edg = new ArrayList<>();
        nq = new HashMap<>();

        for (ArrayList<Vertex> arrayList : otoo) {
            Vertex m = new Vertex(0, 0);
            ver.add(m);
            nq.put(m, arrayList);
            System.out.println("/////////////////////////////////");
            System.out.println(arrayList.toString());
        }
        System.out.println(nq.size());

        for (Map.Entry<ArrayList<Vertex>, ArrayList<ArrayList<Vertex>>> entry : run.entrySet()) {
            ArrayList<Vertex> key = entry.getKey();
            ArrayList<ArrayList<Vertex>> value = entry.getValue();
            System.out.println(key + "  " + value.toString());
        }
        for (Map.Entry<ArrayList<Vertex>, ArrayList<ArrayList<Vertex>>> entry : run.entrySet()) {
            ArrayList<Vertex> key = entry.getKey();
            ArrayList<ArrayList<Vertex>> value = entry.getValue();
            Vertex keys = mapping(key);
            keys.ap = false;
            for (int j = 0; j < value.size(); j++) {
                if (!value.get(j).contains(null)) {
                    Vertex keye = mapping(value.get(j));
                    keye.ap = false;
                    if (tt) {
                        if (key.get(0).ap || key.get(1).ap) {
                            keys.ap = true;
                        }

                        if (value.get(j).get(0).ap || value.get(j).get(1).ap) {
                            keye.ap = true;
                        }
                    } else {
                        if (key.get(0).ap && key.get(1).ap) {
                            keys.ap = true;
                        }
                        if (value.get(j).get(0).ap && value.get(j).get(1).ap) {
                            keye.ap = true;
                        }
                    }
                    Edge_ e = new Edge_(keys, keye);

                    Edge_ b = inline(e);
                    System.out.println(b.vertexA + " " + b.vertexB + "   " + b.vertexA.ap + "  " + b.vertexB.ap);
                    System.out.println(e.vertexA + " " + e.vertexB + "   " + e.vertexA.ap + "  " + e.vertexB.ap);
                    b.weight += (char) (97 + j) + ",";

                    if (!edg.contains(b)) {
                        edg.add(b);
                    }
                }

            }
        }
        //    System.out.println(edg.size());

        for (Edge_ edge_ : edg) {
            edge_.weight = edge_.weight.substring(1, edge_.weight.length() - 1);
            System.out.println(edge_.vertexA.name + "  " + edge_.vertexB.name + "  " + edge_.weight);
            System.out.println(edge_.vertexA.name + "  " + edge_.vertexA.ap + " " + edge_.vertexB.name + "  " + edge_.vertexB.ap);

        }

        alll.Edge_sBackup = (ArrayList<Edge_>) edg.clone();
        alll.VertexsBackup = (ArrayList<Vertex>) ver.clone();
        Vertex.idGen = 0;
        Vertexs = ver;
        Edge_s = edg;

        autops();
        for (int j = 0; j < 20; j++) {
            nec2();
            up();
            up();
            drop();
            drop();
        }
        for (Edge_ e : edg) {
            e.x_center += 50;
        }
        backup = new Backup();
        draw();
    }

    Edge_ inline(Edge_ e) {
        for (Edge_ edge_ : edg) {
            if (edge_.vertexA.equals(e.vertexA) && edge_.vertexB.equals(e.vertexB)) {
                return edge_;
            }
        }
        return e;
    }

    Vertex mapping(ArrayList<Vertex> value) {
        for (Map.Entry<Vertex, ArrayList<Vertex>> entry1 : nq.entrySet()) {
            Vertex key1 = entry1.getKey();
            ArrayList<Vertex> value1 = entry1.getValue();
            if (value.equals(value1)) {
                return key1;
            }
        }
        return null;
    }

    void cartesian(Backup x2, Backup x1) {
        run = new HashMap<>();
        q = new LinkedList<>();
        ck = new HashSet<>();
        deq = new ArrayList<>();
        cha = new ArrayList<>();
        st = new ArrayList<>();
        otoo = new ArrayList<>();
        qqqqq = new ArrayList<>();
        int max = Math.max(x1.count, x2.count);

        ArrayList<Vertex> c = new ArrayList<>();
        c.add(x1.VertexsBackup.get(0));
        c.add(x2.VertexsBackup.get(0));
        q.add(c);
        ck.add(c);
        otoo.add(c);

        c = q.remove();
        st.add(c);

        re(c.get(0), c.get(1), x1, x2, max);

    }

    void re(Vertex ver1, Vertex ver2, Backup x1, Backup x2, int max) {
        ArrayList<Vertex> c = new ArrayList<>();
        ArrayList<Vertex> s = new ArrayList<>();
        s.add(ver1);
        s.add(ver2);
        ArrayList<ArrayList<Vertex>> c1 = new ArrayList<>();
        ArrayList<ArrayList<Vertex>> c2 = new ArrayList<>();
        ArrayList<ArrayList<Vertex>> c3 = new ArrayList<>();
        for (int j = 0; j < max; j++) {
            c = new ArrayList<>();
            print();
            Vertex v1 = strat(ver1, x1, "" + ((char) (97 + j)));
            Vertex v2 = strat(ver2, x2, "" + ((char) (97 + j)));

            c.add(v1);
            c.add(v2);

            // System.out.println("" + ((char) (97 + j)));
            c2.add(c);

            c3.add(c);

            if (!ck.contains(c)) {
                if (v1 != null && v2 != null) {
                    q.add(c);
                    if (!ck.contains(c)) {
                        otoo.add(c);

                    }
                    ck.add(c);

                    c1.add(c);
                }
            }
        }
        run.put(s, c3);
        String[] st2 = q.toString().split(",");
        String str = "";
        for (int j = 0; j < st2.length; j++) {
            if (j % 2 == 1) {
                st2[j] = " , r" + st2[j].substring(2);
            }
            str += st2[j];
        }
        qqqqq.add(str);
        deq.add(c1);
        cha.add(c2);
        c = q.remove();
        st.add(c);
        re(c.get(0), c.get(1), x1, x2, max);
    }

    ArrayList<String> qqqqq = new ArrayList<>();

    Vertex strat(Vertex v, Backup x, String e) {

        for (Edge_ edge_ : x.Edge_sBackup) {
            if (edge_.vertexA.equals(v)) {
                String[] str = edge_.weight.split(",");
                for (String string : str) {

                    if (string.equals(e)) {
                        return edge_.vertexB;
                    }
                }
            }
        }
        return null;
    }

    boolean fullchar(Vertex Vertex) {
        int count = 0;
        for (Edge_ Edge_1 : Edge_s) {
            if (Edge_1.vertexA.equals(Vertex) && Edge_1.weight.length() != 0) {
                count += Edge_1.weight.split(",").length;

            }
        }
        if (count == ll.size()) {
            return true;
        }
        return false;
    }

    void clean() {
        for (JCheckBox jCheckBox : ll) {
            jCheckBox.setSelected(false);
        }

    }

    boolean thechar(Vertex Vertex, String t) {
        for (Edge_ Edge_1 : Edge_s) {
            if (Edge_1.vertexA.equals(Vertex) && Edge_1.weight.length() != 0) {
                String[] str = Edge_1.weight.split(",");
                for (String string : str) {
                    if (t.equals(string)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void putchar() {

        if (selected instanceof Edge_) {
            Edge_ t = (Edge_) selected;

            t.weight = "";
            Vertex Vertex = t.vertexA;
            for (JCheckBox jCheckBox : ll) {
                for (Vertex Vertex1 : Vertexs) {

                }

                if (jCheckBox.isSelected() && thechar(Vertex, jCheckBox.getText())) {
                    t.weight += jCheckBox.getText() + ",";
                }

            }
            t.weight = t.weight.substring(0, t.weight.length() - 1);
            if (t.weight.length() == 0) {

                t.weight = "-";
            }
        }

    }

    void showputchar() {
        if (selected instanceof Edge_) {
            Edge_ t = (Edge_) selected;

            t.weight = "";
            Vertex Vertex = t.vertexA;
            for (JCheckBox jCheckBox : ll) {
                for (Vertex Vertex1 : Vertexs) {

                }

                if (jCheckBox.isSelected() && thechar(Vertex, jCheckBox.getText())) {
                    t.weight += jCheckBox.getText() + ",";
                }

            }
            t.weight = t.weight.substring(0, t.weight.length() - 1);
        }
    }

    void nnn(Vertex s) {
        draw();
        if (mem) {
            x1 = (int) getcenterx();
            y1 = (int) getcentery();
            mem = false;
        }

        if (s.x > x) {
            s.x *= sizen;
        } else if (s.x < x) {
            s.x /= sizen;
        }
        if (s.y > y) {
            s.y *= sizen;
        } else if (s.y < y) {
            s.y /= sizen;
        }

        s.x += (x1 - x);
        s.y += (y1 - y);
        draw();
    }
    ArrayList<Boolean> jj = new ArrayList<>();

    void nec() {
        int i = 0;
        for (Vertex s : Vertexs) {
            if (min(s, true) <= s.r * 4) {
                outrun(s, min(s));
            }

            if (min(s, true) >= s.r * 4) {
                nnn(s);

                nnn(s);
            }

        }

    }

    void nec2() {
        int i = 0;
        for (Vertex s : Vertexs) {

            if (min(s, true) >= s.r * 4) {
                nnn(s);

            }

        }

    }
    Random r = new Random();

    void randomxy(Vertex s) {
        s.x = r.nextInt(1080) + 100;
        s.y = r.nextInt(780) + 150;
        if (min(s, true) < s.r * 4) {
            randomxy(s);
        }
    }

    void autops() {
        for (Vertex s : Vertexs) {
            randomxy(s);
        }
        // nec();
        drop();
        up();

    }

    void drawArrow(Graphics g1, Vertex vertexA, int x1, int y1, int x_center, int y_center, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = (x2 - x1), dy = (y2 - y1);

        double angle = Math.atan2(y_center - y1, x_center - x1);
        int len = (int) Math.sqrt((dx * dx) + (dy * dy));
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw  horizontal arrow starting in (0, 0)
        //  g.drawLine(0, 0, len, 0);
        int ARR_SIZE = (int) (vertexA.r / 3.6);
        if (vertexA.ap) {
            len -= vertexA.r + 6;
        } else {
            len -= vertexA.r;
        }
        g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

    void downcv() {
        for (Edge_ e : Edge_s) {
            for (Edge_ Edge_1 : Edge_s) {

                if (Edge_1.vertexA.equals(e.vertexA) && Edge_1.vertexB.equals(e.vertexB) && !Edge_1.equals(e)) {
                    Edge_s.remove(e);
                }
                if ((Edge_1.vertexA.equals(Edge_1.vertexB) || e.vertexA.equals(e.vertexB))) {

                } else if (Edge_1.vertexA.equals(e.vertexB) && Edge_1.vertexB.equals(e.vertexA)) {
                    if (Edge_1.x_center == e.x_center && Edge_1.y_center == e.y_center) {
                        int centerx = (e.vertexA.x + e.vertexB.x) / 2;
                        int centery = (e.vertexA.y + e.vertexB.y) / 2;
                        double l = Math.sqrt(Math.pow((e.vertexA.x - centerx), 2) + Math.pow((e.vertexA.y - centery), 2));
                        e.y_center += (e.vertexA.x - centerx) / l * e.vertexA.r * 2;
                        e.x_center -= (e.vertexA.y - centery) / l * e.vertexA.r * 2;
                        //  System.out.println("x"+(e.vertexA.y-centery)+" l="+l);
                        //         System.out.println("y"+(e.vertexA.x-centerx)/l*e.vertexA.r*2);
                        Edge_1.y_center += (Edge_1.vertexA.x - centerx) / l * e.vertexA.r * 2;
                        Edge_1.x_center -= (Edge_1.vertexA.y - centery) / l * e.vertexA.r * 2;
                    }
                }

            }
        }
    }
    BufferedImage grid = null;

    public void draw() {

        min = Integer.MAX_VALUE;

        downcv();
        Graphics2D g = (Graphics2D) c.getGraphics();
        g.setFont(sanSerifFont);
        Vertex ss;
        if (grid == null) {
            grid = (BufferedImage) createImage(c.getWidth(), c.getHeight());
        }

        Graphics2D g2 = grid.createGraphics();

        g2.setColor(Color.white);
        g2.setFont(sanSerifFont);
        g2.fillRect(0, 0, getWidth(), getHeight());
        for (Vertex s : Vertexs) {

            min = min(s, true);

        }

        for (Edge_ t : Edge_s) {
            t.color = Color.BLACK;

            t.draw(g2);

        }
        if (TempEdge != null) {
            TempEdge.line(g2);
        }
        int xx = 0, yy = 0;
        int m = 0;
        for (Vertex s : Vertexs) {

            s.id = m;
            s.name = "q" + String.valueOf(m);
            m++;
            xx += s.x;
            yy += s.y;
            s.draw(g2);
            if (s.id == 0) {
                g.setStroke(new BasicStroke(2));

                g.draw(new QuadCurve2D.Float(s.x - s.r * 3, s.y, (s.x * 2 - s.r * 3) / 2, (s.y * 2) / 2, s.x, s.y));
                drawArrow(g, s, s.x - s.r * 3, s.y, (s.x * 2 - s.r * 3) / 2, (s.y * 2) / 2, s.x, s.y);
                g2.setStroke(new BasicStroke(2));

                g2.draw(new QuadCurve2D.Float(s.x - s.r * 3, s.y, (s.x * 2 - s.r * 3) / 2, (s.y * 2) / 2, s.x, s.y));
                drawArrow(g2, s, s.x - s.r * 3, s.y, (s.x * 2 - s.r * 3) / 2, (s.y * 2) / 2, s.x, s.y);
            }
            s.r = Vertexs.get(0).r;
            s.shift = Vertexs.get(0).shift;

            s.draw(g2);
        }
        g.drawImage(grid, null, 0, 0);
        x = xx / Vertexs.size();
        y = yy / Vertexs.size();
    }

    double getcenterx() {
        int xx = 0;

        for (Vertex s : Vertexs) {
            xx += s.x;
        }
        return xx / Vertexs.size();

    }

    double getcentery() {
        int yy = 0;

        for (Vertex s : Vertexs) {
            yy += s.y;
        }
        return yy / Vertexs.size();

    }
    HashMap<Vertex, Set<Vertex>> qq = new HashMap<>();
////////////////////////////////  UI EVENT  ////////////////////////////////
    // 3.1 mouse listener and mouse motion listener mehods 
    // keyboard listener and keyboard motion listener mehods 

    void drop() {

        if (mem) {
            x1 = (int) getcenterx();
            y1 = (int) getcentery();
            mem = false;
        }

        draw();
        //ctrl + R 
        Graphics2D g = (Graphics2D) c.getGraphics();
        sanSerifFont = new Font("SanSerif", Font.PLAIN, (int) kk);

        g.setFont(sanSerifFont);
        boolean v = true;
        if (Vertexs.get(0).r >= 17) {
            v = false;
            kk *= sizen;

            for (Edge_ t : Edge_s) {
                t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
                t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
            }
            for (Vertex s : Vertexs) {

                s.r--;
                s.shift = s.r - 6;

                s.x *= sizen;
                s.x += (x1 - x);
                s.y *= sizen;
                s.y += (y1 - y);
            }
            draw();

        }
    }

    void up() {

        if (mem) {
            x1 = (int) getcenterx();
            y1 = (int) getcentery();
            mem = false;
        }
        System.out.println();
        Graphics2D g = (Graphics2D) c.getGraphics();
        sanSerifFont = new Font("SanSerif", Font.PLAIN, (int) kk);
        kk *= sizep;
        g.setFont(sanSerifFont);

        for (Edge_ t : Edge_s) {
            t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
            t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
        }
        for (Vertex s : Vertexs) {

            s.r++;
            s.shift = s.r - 6;

            s.x /= sizen;
            s.x -= (x - x1);
            s.y /= sizen;
            s.y -= (y - y1);
            s.draw(g);
        }
    }
    boolean ca = true;

    @Override
    public void keyTyped(KeyEvent ke) {

        if ((int) ke.getKeyChar() == 19) {
            try {
                //ctrl + S
                save("backup.json");
            } catch (IOException ex) {

            }
        } else if ((int) ke.getKeyChar() == 15) {
            try {
                //ctrl + O 
                open("backup.json");
            } catch (IOException ex) {

            }
        } else if ((int) ke.getKeyChar() == 14) {
            //ctrl + N 
            autops();
        } else if ((int) ke.getKeyChar() == 12) {
            //ctrl + L

            nec();
            up();
            up();
            drop();
            drop();
        } else if ((int) ke.getKeyChar() == 18) {

            drop();

        } else if ((int) ke.getKeyChar() == 3) {
            if (selected instanceof Vertex) {
                Vertex s = (Vertex) selected;
                s.setap();
            }
        } else if ((int) ke.getKeyChar() == 20) {

            //ctrl + T 
            up();

        } else if ((int) ke.getKeyChar() == 5) {

            //ctrl + T 
            if (ca) {
                list();
                ca = false;
            }
            jFrame.setVisible(true);

        } else if ((int) ke.getKeyChar() == 9) {
        } else if ((int) ke.getKeyChar() == 11) {
            //ctrl + K
            nec2();
            up();
            up();
            drop();
            drop();
        }

        if (selected instanceof Vertex) {
            Vertex s = (Vertex) selected;

            int status = (int) ke.getKeyChar();
            if (status == 8) { //delete

                if (s.name.length() > 1) {
                    s.name = s.name.substring(0, s.name.length() - 1).trim();
                } else {
                    s.name = "".trim();
                }
            } else if (status == 127) { // space
                ArrayList<Edge_> TempEdge = new ArrayList<>();
                for (Edge_ t : Edge_s) {
                    if (t.vertexA == selected || t.vertexB == selected) {
                        TempEdge.add(t);
                    }
                }
                for (Edge_ t : TempEdge) {
                    Edge_s.remove(t);
                }
                Vertexs.remove(selected);
                selected = null;

            } else {
                s.name += ke.getKeyChar();
                s.name = s.name.trim();
            }

        } else if (selected instanceof Edge_) {

            Edge_ t = (Edge_) selected;
            int status = (int) ke.getKeyChar();
            if (status == 8) {
                if (t.weight.length() > 1) {
                    t.weight = t.weight.substring(0, t.weight.length() - 1).trim();
                } else {
                    t.weight = "".trim();
                }
            } else if (status == 127) {
                Edge_s.remove(selected);
                selected = null;

            } else {
                if (ke.getKeyChar() == ' ') {
                    return;
                }
                // t.weight += ke.getKeyChar();
                //    t.weight = t.weight.trim();
            }

        }
        draw();
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                for (Vertex s : Vertexs) {
                    s.y -= 10;
                }
                for (Edge_ t : Edge_s) {
                    t.y_center -= 10;
                }
                break;
            case KeyEvent.VK_DOWN:
                for (Vertex s : Vertexs) {
                    s.y += 10;
                }
                for (Edge_ t : Edge_s) {
                    t.y_center += 10;
                }
                break;
            case KeyEvent.VK_LEFT:
                for (Vertex s : Vertexs) {
                    s.x -= 10;
                }
                for (Edge_ t : Edge_s) {
                    t.x_center -= 10;
                }
                break;
            case KeyEvent.VK_RIGHT:
                for (Vertex s : Vertexs) {
                    s.x += 10;
                }
                for (Edge_ t : Edge_s) {
                    t.x_center += 10;
                }
                break;
        }

        draw();
        if ((int) ke.getKeyChar() == 32) {
            //press space bar
            mode = "Edge_";
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //release space bar
        mode = "Vertex";
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        selected(x, y);
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            if (!Vertexs.contains(selected)) {
                Vertex TempVertex = new Vertex(x, y);
                Vertexs.add(TempVertex);
            }
        }
        draw();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            if (selected != null) {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    for (Edge_ t : Edge_s) {
                        if (t.vertexA == s || t.vertexB == s) {
                            int difx = x - s.x;
                            int dify = y - s.y;
                            if (t.vertexA != t.vertexB) {
                                if (t.vertexA != null) {
                                    t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
                                    t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
                                }
                            } else {
                                t.x_center += difx;
                                t.y_center += dify;
                            }

                        }
                    }
                    s.x = x;
                    s.y = y;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.x_center = x;
                    t.y_center = y;
                }
            }

        } else if (mode.equals("Edge_")) {
            try {
                Vertex Vertex = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        Vertex = s;
                    }
                }

                if (Vertex != null) {
                    if (Vertex != TempEdge.vertexA) {
                        double angle = Math.atan2(TempEdge.vertexA.y - Vertex.y, TempEdge.vertexA.x - Vertex.x);
                        double dx = Math.cos(angle);
                        double dy = Math.sin(angle);
                        TempEdge.x1 = Vertex.x + (int) (Vertex.r * dx);
                        TempEdge.y1 = Vertex.y + (int) (Vertex.r * dy);
                    } else {
                        TempEdge.x1 = x;
                        TempEdge.y1 = y;
                    }
                } else {
                    TempEdge.x1 = x;
                    TempEdge.y1 = y;
                }
            } catch (Exception ex) {

            }
        }
        draw();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            TempEdge = null;
        } else if (mode.equals("Edge_")) {
            try {
                TempEdge.x1 = x;
                TempEdge.y1 = y;
                Vertex vertexB = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        TempEdge.x1 = s.x;
                        TempEdge.y1 = s.y;
                        vertexB = s;
                        Edge_ edge = new Edge_(TempEdge.vertexA, vertexB);

                        if (s != TempEdge.vertexA) {
                            edge.x_center = (TempEdge.vertexA.x + s.x) / 2;
                            edge.y_center = (TempEdge.vertexA.y + s.y) / 2;
                        } else {
                            double angle = Math.atan2(y - TempEdge.vertexA.y, x - TempEdge.vertexA.x);
                            double dx = Math.cos(angle);
                            double dy = Math.sin(angle);
                            int rc = 3 * TempEdge.vertexA.r;
                            edge.x_center = TempEdge.vertexA.x + (int) (dx * rc);
                            edge.y_center = TempEdge.vertexA.y + (int) (dy * rc);

                        }

                        Edge_s.add(edge);
                        break;
                    }
                }
                TempEdge = null;
            } catch (Exception ex) {
            }
        }
        draw();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Edge_")) {
            TempEdge = new TempEdge(x, y);
            for (Vertex s : Vertexs) {
                if (s.inCircle(x, y)) {
                    TempEdge.setA(s);
                    break;
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }
}
