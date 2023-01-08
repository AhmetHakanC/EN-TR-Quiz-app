package english;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class English_quiz_game extends JFrame {
    //Database'den çekilen kelimeler bu listelere eklenecek
    //Bu koda göre ingilizce kelimeler ile türkçelerin indexleri hizalı olmalıdır
    static String[] ingilizce = {"apple", "banana", "orange", "strawberry", "watermelon"};
    static String[] turkce = {"elma", "muz", "portakal", "çilek", "karpuz"};

    static String[] ingilizce_kolay = {"apple", "banana", "orange", "strawberry", "watermelon"};
    static String[] turkce_kolay = {"elma", "muz", "portakal", "çilek", "karpuz"};
    static String[] ingilizce_orta = {"car", "bus", "train", "plane", "ship"};
    static String[] turkce_orta = {"araba", "otobüs", "tren", "uçak", "gemi"};
    static String[] ingilizce_zor = {"computer", "laptop", "smartphone", "tablet", "television"};
    static String[] turkce_zor = {"bilgisayar", "dizüstü bilgisayar", "akıllı telefon", "tablet", "televizyon"};

    static int puan = 0;
    static int sure = 60;
    static int dogru_sayisi = 0;
    static int zorluk = 4;
    static String word;
    static String cevap;
    static String a;
    static String b;
    static String c;


    public static String engWord() {
        if (zorluk == 1){
            return ingilizce_kolay[(int)(Math.random() * ingilizce_kolay.length)];
        }else if (zorluk == 2){
            return ingilizce_orta[(int)(Math.random() * ingilizce_orta.length)];
        }else if (zorluk == 3){
            return ingilizce_zor[(int)(Math.random() * ingilizce_zor.length)];
        }else {
            return ingilizce_kolay[(int)(Math.random() * ingilizce_kolay.length)];
        }
    }

    public static String anlam(String kelime) {
        int index = 0;

        if (zorluk == 1){
            for(int i = 0; i < ingilizce_kolay.length; i++){
                if(kelime.equals(ingilizce_kolay[i])){
                    index = i;
                    break;
                }
            }
            return turkce_kolay[index];
        }else if (zorluk == 2){
            for(int i = 0; i < ingilizce_orta.length; i++){
                if(kelime.equals(ingilizce_orta[i])){
                    index = i;
                    break;
                }
            }
            return turkce_orta[index];
        }else if (zorluk == 3){
            for(int i = 0; i < ingilizce_zor.length; i++){
                if(kelime.equals(ingilizce_zor[i])){
                    index = i;
                    break;
                }
            }
            return turkce_zor[index];
        }else {
            for(int i = 0; i < ingilizce_kolay.length; i++){
                if(kelime.equals(ingilizce_kolay[i])){
                    index = i;
                    break;
                }
            }
            return turkce_kolay[index];
        }
    }

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    English_quiz_game frame = new English_quiz_game();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public English_quiz_game() {
        JButton btn_a = new JButton(a);
        JButton btn_b = new JButton(b);
        JButton btn_c = new JButton(c);
        JButton btn_kolay = new JButton("KOLAY");
        btn_kolay.setForeground(new Color(50, 205, 50));
        JButton btn_orta = new JButton("ORTA");
        btn_orta.setForeground(new Color(255, 215, 0));
        JButton btn_zor = new JButton("ZOR");
        btn_zor.setForeground(new Color(220, 20, 60));
        JButton btn_tekrar = new JButton("Tekrar dene");

        JLabel lbl_word = new JLabel(word);
        JLabel lbl_str_sure = new JLabel("SÜRE:");
        JLabel lbl_sure = new JLabel("0");

        btn_a.setEnabled(false);
        btn_b.setEnabled(false);
        btn_c.setEnabled(false);

        game();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_zorluksecim = new JPanel();
        panel_zorluksecim.setBackground(Color.DARK_GRAY);
        panel_zorluksecim.setBounds(10, 174, 204, 280);
        contentPane.add(panel_zorluksecim);
        panel_zorluksecim.setLayout(null);

        btn_zor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zorluk = 3;
                panel_zorluksecim.setVisible(false);
                btn_a.setEnabled(true);
                btn_b.setEnabled(true);
                btn_c.setEnabled(true);
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        lbl_sure.setText(String.valueOf(sure));
                        sure--;
                        if (sure < 0) {
                            timer.cancel();
                            lbl_sure.setText("Süre doldu!");
                            btn_tekrar.setEnabled(true);
                            btn_a.setEnabled(false);
                            btn_b.setEnabled(false);
                            btn_c.setEnabled(false);
                        }
                    }
                }, 0, 1000);

            }
        });
        btn_zor.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_zor.setBounds(40, 216, 125, 35);
        panel_zorluksecim.add(btn_zor);

        btn_orta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zorluk = 2;
                panel_zorluksecim.setVisible(false);
                btn_a.setEnabled(true);
                btn_b.setEnabled(true);
                btn_c.setEnabled(true);
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        lbl_sure.setText(String.valueOf(sure));
                        sure--;
                        if (sure < 0) {
                            timer.cancel();
                            lbl_sure.setText("Süre doldu!");
                            btn_tekrar.setEnabled(true);
                            btn_a.setEnabled(false);
                            btn_b.setEnabled(false);
                            btn_c.setEnabled(false);
                        }
                    }
                }, 0, 1000);
            }
        });
        btn_orta.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_orta.setBounds(40, 143, 125, 35);
        panel_zorluksecim.add(btn_orta);

        btn_kolay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zorluk = 1;
                panel_zorluksecim.setVisible(false);
                btn_a.setEnabled(true);
                btn_b.setEnabled(true);
                btn_c.setEnabled(true);
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);

                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        lbl_sure.setText(String.valueOf(sure));
                        sure--;
                        if (sure < 0) {
                            timer.cancel();
                            lbl_sure.setText("Süre doldu!");
                            btn_tekrar.setEnabled(true);
                            btn_a.setEnabled(false);
                            btn_b.setEnabled(false);
                            btn_c.setEnabled(false);
                        }
                    }
                }, 0, 1000);
            }
        });
        btn_kolay.setFont(new Font("Tahoma", Font.BOLD, 15));
        btn_kolay.setBounds(40, 71, 125, 35);
        panel_zorluksecim.add(btn_kolay);

        JLabel lbl_str_zorluk = new JLabel("Zorluk seçin");
        lbl_str_zorluk.setForeground(Color.WHITE);
        lbl_str_zorluk.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_str_zorluk.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_str_zorluk.setBounds(10, 11, 187, 49);
        panel_zorluksecim.add(lbl_str_zorluk);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(10, 58, 187, 2);
        panel_zorluksecim.add(separator_1);

        JLabel lbl_str_puan = new JLabel("PUAN:");
        lbl_str_puan.setForeground(Color.WHITE);
        lbl_str_puan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_str_puan.setBounds(10, 79, 56, 50);
        contentPane.add(lbl_str_puan);

        JLabel lbl_puan = new JLabel(String.valueOf(puan));
        lbl_puan.setForeground(Color.WHITE);
        lbl_puan.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_puan.setBounds(76, 79, 100, 50);
        contentPane.add(lbl_puan);

        lbl_str_sure.setForeground(Color.WHITE);
        lbl_str_sure.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_str_sure.setBounds(605, 11, 56, 50);
        contentPane.add(lbl_str_sure);

        lbl_sure.setForeground(Color.WHITE);
        lbl_sure.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_sure.setBounds(671, 11, 103, 50);
        contentPane.add(lbl_sure);

        lbl_word.setForeground(Color.WHITE);
        lbl_word.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_word.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_word.setBounds(269, 161, 225, 50);
        contentPane.add(lbl_word);

        JLabel lbl_str_dogruks = new JLabel("Doğru bilinen kelime sayısı:");
        lbl_str_dogruks.setForeground(Color.WHITE);
        lbl_str_dogruks.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_str_dogruks.setBounds(10, 13, 246, 50);
        contentPane.add(lbl_str_dogruks);

        JLabel lbl_dogruks = new JLabel("0");
        lbl_dogruks.setForeground(Color.WHITE);
        lbl_dogruks.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lbl_dogruks.setBounds(269, 15, 100, 50);
        contentPane.add(lbl_dogruks);

        btn_a.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btn_a.getText() == cevap) {
                    puan += 10;
                    dogru_sayisi += 1;
                } else {
                    puan -= 2;
                }
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);
                lbl_puan.setText(String.valueOf(puan));
                lbl_dogruks.setText(String.valueOf(dogru_sayisi));
            }
        });
        btn_a.setForeground(new Color(0, 0, 0));
        btn_a.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btn_a.setBounds(269, 235, 225, 50);
        contentPane.add(btn_a);

        btn_b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btn_b.getText() == cevap) {
                    puan += 10;
                    dogru_sayisi += 1;
                } else {
                    puan -= 2;
                }
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);
                lbl_puan.setText(String.valueOf(puan));
                lbl_dogruks.setText(String.valueOf(dogru_sayisi));
            }
        });
        btn_b.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btn_b.setBounds(269, 313, 225, 50);
        contentPane.add(btn_b);

        btn_c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (btn_c.getText() == cevap) {
                    puan += 10;
                    dogru_sayisi += 1;
                } else {
                    puan -= 2;
                }
                game();
                lbl_word.setText(word);
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);
                lbl_puan.setText(String.valueOf(puan));
                lbl_dogruks.setText(String.valueOf(dogru_sayisi));
            }
        });
        btn_c.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btn_c.setBounds(269, 390, 225, 50);
        contentPane.add(btn_c);

        btn_tekrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                game();
                puan = 0;
                lbl_puan.setText(String.valueOf(puan));
                sure = 60;
                lbl_sure.setText(String.valueOf(sure));
                dogru_sayisi = 0;
                lbl_dogruks.setText(String.valueOf(dogru_sayisi));
                btn_a.setText(a);
                btn_b.setText(b);
                btn_c.setText(c);
                lbl_word.setText(word);
                btn_tekrar.setEnabled(false);
                panel_zorluksecim.setVisible(true);

            }
        });
        btn_tekrar.setEnabled(false);
        btn_tekrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btn_tekrar.setBounds(10, 500, 225, 50);
        contentPane.add(btn_tekrar);

        JSeparator separator = new JSeparator();
        separator.setBounds(10, 127, 764, 2);
        contentPane.add(separator);
    }
    void game() {
        word = engWord();
        cevap = anlam(word);

        int randomAns = (int)(Math.random() * 3) + 1;
        if (zorluk == 1){
            if (randomAns == 1){
                a = cevap;
                while (true){
                    b = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    c = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (b == cevap || c == cevap) {
                        continue;
                    }else if (b == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            } else if (randomAns == 2) {
                b = cevap;
                while (true){
                    a = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    c = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (a == cevap || c == cevap) {
                        continue;
                    }else if (a == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            }else {
                c = cevap;
                while (true){
                    a = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    b = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (a == cevap || b == cevap) {
                        continue;
                    }else if (a == b) {
                        continue;
                    }else {
                        break;
                    }
                }
            }
        } else if (zorluk == 2) {
            if (randomAns == 1){
                a = cevap;
                while (true){
                    b = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    c = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    if (b == cevap || c == cevap) {
                        continue;
                    }else if (b == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            } else if (randomAns == 2) {
                b = cevap;
                while (true){
                    a = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    c = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    if (a == cevap || c == cevap) {
                        continue;
                    }else if (a == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            }else {
                c = cevap;
                while (true){
                    a = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    b = turkce_orta[(int)(Math.random() * turkce_orta.length)];
                    if (a == cevap || b == cevap) {
                        continue;
                    }else if (a == b) {
                        continue;
                    }else {
                        break;
                    }
                }
            }
        } else if (zorluk == 3) {
            if (randomAns == 1){
                a = cevap;
                while (true){
                    b = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    c = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    if (b == cevap || c == cevap) {
                        continue;
                    }else if (b == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            } else if (randomAns == 2) {
                b = cevap;
                while (true){
                    a = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    c = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    if (a == cevap || c == cevap) {
                        continue;
                    }else if (a == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            }else {
                c = cevap;
                while (true){
                    a = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    b = turkce_zor[(int)(Math.random() * turkce_zor.length)];
                    if (a == cevap || b == cevap) {
                        continue;
                    }else if (a == b) {
                        continue;
                    }else {
                        break;
                    }
                }
            }
        }else {
            if (randomAns == 1){
                a = cevap;
                while (true){
                    b = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    c = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (b == cevap || c == cevap) {
                        continue;
                    }else if (b == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            } else if (randomAns == 2) {
                b = cevap;
                while (true){
                    a = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    c = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (a == cevap || c == cevap) {
                        continue;
                    }else if (a == c) {
                        continue;
                    }else {
                        break;
                    }
                }
            }else {
                c = cevap;
                while (true){
                    a = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    b = turkce_kolay[(int)(Math.random() * turkce_kolay.length)];
                    if (a == cevap || b == cevap) {
                        continue;
                    }else if (a == b) {
                        continue;
                    }else {
                        break;
                    }
                }
            }
        }
    }
}
