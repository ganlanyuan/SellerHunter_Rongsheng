import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Created by linwilliam on 6/14/17.
 */
public class HunterInterface {
    private static String urlPostfix = ".com";
    private static int positive = 90;
    private static int totalRating = 100;
    public static void main(String[] args) {
        HunterInterface ui = new HunterInterface();
        ui.startUp();
    }

    public static void startUp(){
        JFrame frame = new JFrame("Layout Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container panel = frame.getContentPane();
        GroupLayout groupLayout = new GroupLayout(panel);
        frame.setLayout(groupLayout);
        frame.setSize(800, 600);

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
        JSlider positiveS = new JSlider(0, 100, 90);
        positiveS.setSize(80, 30);
        JTextField ratingT = new JTextField("100");
        ratingT.setSize(80, 30);
        JButton button = new JButton("Submit");
        button.setSize(60, 40);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String country = countryC.getSelectedItem().toString();
                switch (country) {
                    case "US":
                        urlPostfix = ".com";
                        break;
                    case "UK":
                        urlPostfix = ".co.uk";
                        break;
                    case "CA":
                        urlPostfix = ".ca";
                        break;
                }

                positive = positiveS.getValue();

                try {
                    int rating = Integer.parseInt(ratingT.getText());
                    if (rating > 0) {
                        totalRating = rating;
                    }
                } finally {
                    // close frame
                    frame.dispose();

                    // start searching pages
                    AmazonSellerHunter hunter = new AmazonSellerHunter(urlPostfix, positive, totalRating);
                    hunter.searchPages();
                }
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
