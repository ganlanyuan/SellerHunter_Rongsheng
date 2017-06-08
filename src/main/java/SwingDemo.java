import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;

/**
 * Created by designer01 on 6/8/17.
 */
public class SwingDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame();

        JButton b1 = new JButton();
        b1.setText("Search");
        b1.setBounds(100, 100, 120, 40);
        b1.setBackground(Color.red);
        b1.setOpaque(true);

        JButton b2 = new JButton("click");
        b2.setBounds(240, 100, 120, 40);
        b2.setBackground(Color.blue);
        b2.setOpaque(true);

        f.add(b1);
        f.add(b2);
        f.setSize(800, 800);
        f.setLayout(null);
        f.setVisible(true);
    }
}

class Simple {
    JFrame f;

    public Simple() {
        f = new JFrame();

        JButton b = new JButton("Submit");
        b.setBounds(50, 100, 80, 40);

        f.add(b);
        f.setSize(600, 600);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new Simple();
    }
}

class Simple2 extends JFrame {
    public Simple2() {

        JButton b = new JButton("Submit");
        b.setBounds(50, 100, 80, 40);
        b.setBackground(Color.BLUE);
        b.setOpaque(true);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
            }
        });
        b.setVisible(true);

        add(b);
        setSize(600, 600);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Simple2();
    }
}

class TextFieldDemo {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.setSize(800, 600);
        j.setLayout(null);

        JTextField t = new JTextField(90);
        t.setBounds(100, 100, 500, 300);
        t.setAlignmentY(0);
        t.setBackground(Color.BLUE);
        t.setOpaque(true);
        t.setBorder(BorderFactory.createLineBorder(Color.black));

        j.add(t);
        j.setVisible(true);
    }
}

class ComboboxDemo {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.setSize(600, 400);
        j.setLayout(null);

        String[] colors = {"red", "blue", "yellow", "black"};
        final JComboBox co = new JComboBox(colors);
        co.setBounds(100, 100, 100, 40);
        co.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(co.getSelectedItem());
            }
        });

        j.add(co);
        j.setVisible(true);
    }
}

class LableDemo {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.setSize(600, 400);
        j.setLayout(null);

        JLabel l = new JLabel();
        l.setText("Color: ");
        l.setBounds(100, 100, 200, 40);

        j.add(l);
        j.setVisible(true);
    }
}

class LayoutDemo {
    public static void main(String[] args) {
        JFrame j = new JFrame("Layout Demo");
        Container c = j.getContentPane();
        GroupLayout layout = new GroupLayout(c);
        j.setLayout(layout);
        j.setSize(600, 400);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        JLabel countryL = new JLabel("国家：");
        countryL.setSize(80, 30);

        String[] countryList = {"US", "UK", "CA"};
        JComboBox countryC = new JComboBox(countryList);
        countryC.setSize(80, 30);

        JLabel positiveL = new JLabel("好评率：");
        positiveL.setSize(80, 30);

        JSlider positiveS = new JSlider(0, 100, 50);
        positiveS.setSize(80, 30);

        JLabel ratingL = new JLabel("最少rating数：");
        ratingL.setSize(80, 30);

        JTextField ratingT = new JTextField();
        ratingT.setSize(80, 30);

        JTextField t = new JTextField(10);
        JButton b = new JButton("Submit");
        b.setSize(60, 40);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                .addGroup()
        );
        j.pack();
        j.setVisible(true);
    }
}