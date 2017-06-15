import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.event.WindowEvent;

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
        JFrame frame = new JFrame("Layout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container panel = frame.getContentPane();
        GroupLayout groupLayout = new GroupLayout(panel);
        frame.setLayout(groupLayout);
        frame.setSize(600, 400);

        groupLayout.setAutoCreateContainerGaps(true);
        groupLayout.setAutoCreateGaps(true);

        JLabel countryL = new JLabel("选择国家：");
        countryL.setSize(120, 30);
        JLabel positiveL = new JLabel("最低好评率：");
        positiveL.setSize(120, 30);
        JLabel ratingL = new JLabel("最少rating数：");
        ratingL.setSize(120, 30);

        String[] countryList = {"US", "UK", "CA"};
        JComboBox countryC = new JComboBox(countryList);
        countryC.setSize(80, 30);
        JSlider positiveS = new JSlider(0, 100, 50);
        positiveS.setSize(80, 30);
        JTextField ratingT = new JTextField("20");
        ratingT.setSize(80, 30);
        JButton button = new JButton("Submit");
        button.setSize(60, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country = countryC.getSelectedItem().toString();
                int positive = positiveS.getValue();
                int rating = Integer.parseInt(ratingT.getText());
                System.out.println("country: " + country + "\npositive: " + positive + "\nrating: " + rating);
                System.exit(0);
            }
        });

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(countryL).addComponent(positiveL).addComponent(ratingL))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(countryC).addComponent(positiveS).addComponent(ratingT).addComponent(button)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(countryL).addComponent(countryC))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(positiveL).addComponent(positiveS))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(ratingL).addComponent(ratingT))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(button)));

        frame.pack();
        frame.setVisible(true);
    }
}