package timer;

import java.awt.Color;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author alx.rogatko
 */
public class Timer {
    static int daySecFinal = returnSec("20:00:00");// 72000
    static int nightSecFinal = returnSec("8:00:00");// 28800
    static final int DAY = 86400;

    public static void start() throws InterruptedException {

        window();

    }

    public static String returnHMSofSec(int sec) {
        int h = sec / 3600;
        int m = (sec % 3600) / 60;
        int s = (sec % 3600) % 60;
        String dops = "";
        String dopm = "";
        String doph = "";
        if (h < 10)
            doph = "0";
        if (m < 10)
            dopm = "0";
        if (s < 10)
            dops = "0";
        return doph + h + " : " + m + dopm + " : " + dops + s;
    }

    public static int count() {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String currentTime = dateFormat.format(date);
        int currentSec = returnSec(currentTime);

        int a;

        if (currentSec < nightSecFinal || currentSec > daySecFinal) {
            a = differ(currentSec, daySecFinal);
        } else {
            a = differ(currentSec, nightSecFinal);

        }

        return a;

    }

    static int returnSec(String time) {
        String[] timeArray = time.split(":");
        return Integer.parseInt(timeArray[0]) * 3600 + Integer.parseInt(timeArray[1]) * 60 + Integer.parseInt(timeArray[2]);
    }

    static int differ(int currentSec, int workSec) {

        if (currentSec < daySecFinal)
            return daySecFinal - currentSec;// ?
        if (currentSec > nightSecFinal) {

            return DAY - currentSec + daySecFinal;
        }
        return nightSecFinal - currentSec;

    }

    static void window() throws InterruptedException {
        JFrame fr = new JFrame("Таймер смены");
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(400, 200);

        JButton btnC = new JButton("CANCEL");
        Container c = new Container();

        c.add(btnC);
        fr.add(c);
        JLabel lab = new JLabel("     Осталось до конца смены \n  " + count());

        fr.add(lab);

        fr.setVisible(true);
        while (true) {
            if (count() % 60 == 0) {
                fr.getContentPane().setBackground(Color.BLACK);
                lab.setForeground(Color.WHITE);
            } else {

                fr.getContentPane().setBackground(Color.WHITE);
                lab.setForeground(Color.BLACK);
            }
            Thread.sleep(100);
            lab.setText("     Осталось до конца смены \n  " + count() + " секунд  или  " + returnHMSofSec(count()));
        }

    }

}
