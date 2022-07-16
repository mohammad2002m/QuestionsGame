import javax.swing.*;

import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.event.*;
import java.io.*;
import java.io.IOException;
import java.io.File;


public class MainWindow extends JFrame {
    public static int n = 10;
    public static int cur = -1;
    public static String excel_path = System.getProperty("user.dir");
    public static int ans[] = new int[n];
    public static int ind[] = new int[n];
    public static int sel[] = new int[n];
    public static JPanel panel1 , panel2 , panel3;
    public static JLabel label1 , label2 , label3 , label4 , label5;
    public static JButton btn1 , btn2 , btn3 , btn4 , btn5 , btn6 , btn7;
    public static JTextArea text1 , text2 , text3 , text4 , text5;
    public static JRadioButton radioButton1 , radioButton2, radioButton3 , radioButton4;
    public static JRadioButton radioButton5 , radioButton6, radioButton7 , radioButton8;
    public static ButtonGroup G , G2;

    public static int get_blank_index(){
        try {
            XSSFWorkbook Work = new XSSFWorkbook("contest.xlsx");
            XSSFSheet sheet = Work.getSheet("Sheet1");
            for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++){
                if (sheet.getRow(i).getCell(0).toString() == ""){
                    return i;
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        assert(false);
        return -1;
    }
    public static void view(int x){
        XSSFWorkbook Work;
        try {
            Work = new XSSFWorkbook("contest.xlsx");
            XSSFSheet sheet = Work.getSheet("Sheet1");
            label1.setText(sheet.getRow(ind[cur]).getCell(0).toString());
            label2.setText(sheet.getRow(ind[cur]).getCell(1).toString());
            label3.setText(sheet.getRow(ind[cur]).getCell(2).toString());
            label4.setText(sheet.getRow(ind[cur]).getCell(3).toString());
            label5.setText(sheet.getRow(ind[cur]).getCell(4).toString());
            

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (x == n - 1){
            btn4.setText("إنهاء");
        } else {
            btn4.setText("التالي");
        }

        if (x == 0){
            btn3.setText("أخرج");
        } else {
            btn3.setText("السابق");
        }

        if (sel[cur] == -1){
            G.clearSelection();
        } else if (sel[cur] == 0){
            radioButton1.setSelected(true);
        } else if (sel[cur] == 1){
            radioButton2.setSelected(true);
        } else if (sel[cur] == 2){
            radioButton3.setSelected(true);
        } else if (sel[cur] == 3){
            radioButton4.setSelected(true);
        }

    }

    public static void generate(){
        if (cur == -1){
            assert(false);
        }
        for (int i = 0; i < n; i++) ind[i] = -1;
        Random random = new Random();
        
        int cnt = 0;
        while (cnt != n){
            int x = random.nextInt(get_blank_index() - 1) + 1;
            boolean found = false;
            for (int j = 0; j < n; j++){
                if (ind[j] == x){
                    found = true;
                    break;
                }
            }
            
            if (!found){
                ind[cnt] = x;
                cnt++;
            }
        }

        XSSFWorkbook Work;
        try {
            Work = new XSSFWorkbook("contest.xlsx");
            XSSFSheet sheet = Work.getSheet("Sheet1");
            for (int i = 0; i < n; i++){
                ans[i] = (int) sheet.getRow(ind[i]).getCell(6).getNumericCellValue();
            }
        } catch (IOException e){
            System.out.println("Exception Happened");
        }

    }
    public static void main(String[] args) throws Exception {
        MainWindow M = new MainWindow();
    }

    MainWindow(){

        //
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        panel1.setVisible(false);
        panel3.setVisible(false);
        panel2.setVisible(true);
    
        // make panel2

        btn1 = new JButton();
        btn2 = new JButton();
        btn7 = new JButton();

        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn7);

        btn1.setBounds(50 , 50 , 200 , 50);
        btn2.setBounds(50 , 150 , 200 , 50);
        btn7.setBounds(50 , 250 , 200 , 50);

        btn1.setVisible(true);
        btn2.setVisible(true);
        btn7.setVisible(true);

        panel2.setBounds(0 , 0 , 600 , 400);
        panel2.setLayout(null);


        // end panel2

        // make panel1

        label1 = new JLabel();

        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();

        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();

        btn3 = new JButton();
        btn4 = new JButton();

        G = new ButtonGroup();

        G.add(radioButton1);
        G.add(radioButton2);
        G.add(radioButton3);
        G.add(radioButton4);

        panel1.add(radioButton1);
        panel1.add(radioButton2);
        panel1.add(radioButton3);
        panel1.add(radioButton4);

        panel1.add(btn3);
        panel1.add(btn4);

        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);

        radioButton1.setBounds(50 + 480 , 95 , 20 , 20);
        radioButton2.setBounds(50 + 480 , 145 , 20 , 20);
        radioButton3.setBounds(50 + 480 , 195 , 20 , 20);
        radioButton4.setBounds(50 + 480 , 245 , 20 , 20);

        label1.setBounds(80 , 40 , 500  , 50);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label2.setBounds(80 + 370 , 80 , 100  , 50);
        label3.setBounds(80 + 370 , 130 , 100 , 50);
        label4.setBounds(80 + 370 , 180 , 100 , 50);
        label5.setBounds(80 + 370 , 230 , 100 , 50);

        btn3.setBounds(140 + 330 , 300 , 80 , 40);
        btn4.setBounds(50 + 330 , 300 , 80 , 40);


        panel1.setBounds(0 , 0 , 600 , 400);
        panel1.setLayout(null);

        // end panel1

        // make panel3 
        btn5 = new JButton();
        btn6 = new JButton();

        text1 = new JTextArea();
        text2 = new JTextArea();
        text3 = new JTextArea();
        text4 = new JTextArea();
        text5 = new JTextArea();
        
        G2 = new ButtonGroup();

        radioButton5 = new JRadioButton();
        radioButton6 = new JRadioButton();
        radioButton7 = new JRadioButton();
        radioButton8 = new JRadioButton();

        G2.add(radioButton5);
        G2.add(radioButton6);
        G2.add(radioButton7);
        G2.add(radioButton8);

        text1.setBounds(50 , 50 , 150 , 50);

        text2.setBounds(50 , 110 , 150 , 50);
        radioButton5.setBounds(210 , 110 , 150 , 50);
        text3.setBounds(50 , 170 , 150 , 50);
        radioButton6.setBounds(210 , 170 , 150 , 50);
        text4.setBounds(50 , 230 , 150 , 50);
        radioButton7.setBounds(210 , 230 , 150 , 50);
        text5.setBounds(50 , 290 , 150 , 50);
        radioButton8.setBounds(210 , 290 , 150 , 50);

        btn5.setBounds(470 , 300 , 100 , 50);
        btn6.setBounds(360 , 300 , 100 , 50);


        panel3.add(text1);
        panel3.add(text2);
        panel3.add(text3);
        panel3.add(text4);
        panel3.add(text5);

        panel3.add(btn5);
        panel3.add(btn6);

        panel3.add(radioButton5);
        panel3.add(radioButton6);
        panel3.add(radioButton7);
        panel3.add(radioButton8);

        panel3.setBounds(0 , 0 , 600 , 400);
        panel3.setLayout(null);

        // end panel3
        add(panel1);
        add(panel2);
        add(panel3);

        
        int index = get_blank_index();
        System.out.println(index);

        for (int i = 0; i < n; i++) sel[i] = -1;

       btn1.setText("أضف سؤال");
       btn2.setText("ابدأ الإجابة");
       btn3.setText("السابق");
       btn4.setText("التالي");
       btn5.setText("أضف سؤال");
       btn6.setText("خروج");
       btn7.setText("خروج");
       
       btn6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");

                panel3.setVisible(false);
                panel2.setVisible(true);
                panel1.setVisible(false);
            }
        });

        btn7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        btn5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (text1.getText().trim().equals("") == true || text2.getText().trim().equals("") == true || text3.getText().trim().equals("") == true || text4.getText().trim().equals("") == true|| text5.getText().trim().equals("") == true
                    || (radioButton5.isSelected() == false && radioButton6.isSelected() == false && radioButton7.isSelected() == false && radioButton8.isSelected() == false)){

                    JOptionPane.showMessageDialog(null , "لا يمكن إضافة سؤال ويوجد مساحة فارغة أو الإجابة الصحيحة غير محددة");
                } else {
                        int result = JOptionPane.showConfirmDialog(null, "هل أنت متأكد من إضافة السؤال لا يمكن حذفه");
                        if (result == JOptionPane.YES_OPTION){
                            JOptionPane.showMessageDialog(null, "تمت إضافة السؤال بنجاح");
                            int temp_ans = 0;

                            if (radioButton5.isSelected() == true) temp_ans = 0;
                            if (radioButton6.isSelected() == true) temp_ans = 1;
                            if (radioButton7.isSelected() == true) temp_ans = 2;
                            if (radioButton8.isSelected() == true) temp_ans = 3;

                        try {
                            int index = get_blank_index();
                            
                            FileInputStream excel_file= new FileInputStream(new File("contest.xlsx"));
                            XSSFWorkbook wb = new XSSFWorkbook(excel_file);
                            XSSFSheet sheet2 = wb.getSheet("Sheet1");

                            sheet2.getRow(index).getCell(0).setCellValue(text1.getText());
                            sheet2.getRow(index).getCell(1).setCellValue(text2.getText());
                            sheet2.getRow(index).getCell(2).setCellValue(text3.getText());
                            sheet2.getRow(index).getCell(3).setCellValue(text4.getText());
                            sheet2.getRow(index).getCell(4).setCellValue(text5.getText());
                            sheet2.getRow(index).getCell(5).setCellValue(sheet2.getRow(index).getCell(temp_ans + 1).getStringCellValue());
                            sheet2.getRow(index).getCell(6).setCellValue(Integer.toString(temp_ans));

                            excel_file.close();
                            FileOutputStream output_file =new FileOutputStream(new File("contest.xlsx")); 
                            wb.write(output_file);
                            output_file.close();  



                        } catch (IOException e2){

                        }

                        }
                }
            }
        });


        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                text1.setText("نص السؤال");
                text2.setText("الخيار الأول");
                text3.setText("الخيار الثاني");
                text4.setText("الخيار الثالث");
                text5.setText("الخيار الرابع");

                panel3.setVisible(true);
                panel2.setVisible(false);
                panel1.setVisible(false);
            }
        });

        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                panel2.setVisible(false);
                panel1.setVisible(true);
                System.out.println(cur);
                generate();
                view(++cur);
                for (int i = 0; i < n; i++){
                    System.out.print(ans[i] + " ");
                }
                System.out.println();
            }
        });

        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cur == 0){
                    for (int i = 0; i < n; i++) ans[i] = -1;
                    for (int i = 0; i < n; i++) sel[i] = -1;
                    panel2.setVisible(true);
                    panel1.setVisible(false);
                    cur = -1;
                } else {
                    view(--cur);
                }
                
            }
        });


        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cur == n - 1){
                    int score = 0;
                    for (int i = 0; i < n; i++){
                        if (ans[i] == sel[i]){
                            score++;
                        }
                    }
                    for (int i = 0; i < n; i++) ans[i] = -1;
                    for (int i = 0; i < n; i++) sel[i] = -1;
                    JOptionPane.showMessageDialog(null, "حصلت على" + " " + score + " " + "من" + " " + n);
                    cur = -1;
                    panel2.setVisible(true);
                    panel1.setVisible(false);
                } else {
                    view(++cur);
                }
                
            }
        });

        radioButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sel[cur] = 0;
            }
        });

        radioButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sel[cur] = 1;
            }
        });

        radioButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sel[cur] = 2;
            }
        });

        radioButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sel[cur] = 3;
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0 , 0 , 600 , 400);
        setLayout(null);
        setVisible(true);

    }
}