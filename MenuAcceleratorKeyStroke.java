import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuAcceleratorKeyStroke extends JFrame {
  public MenuAcceleratorKeyStroke() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar bar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menu.setMnemonic('f');
    bar.add(menu);

    JMenuItem exit = new JMenuItem("Exit");
    exit.setMnemonic('x');
    exit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.out.println("Exit performed");
        MenuAcceleratorKeyStroke.this.dispose();
        System.exit(0);
      }
    });
    menu.add(exit);

    menu = new JMenu("Edit");
    menu.setMnemonic('e');
    bar.add(menu);

    EditListener l = new EditListener();
    JMenuItem mi;
    mi = menu.add(new JMenuItem("Cut", 't'));
    mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
    mi.addActionListener(l);
    mi = menu.add(new JMenuItem("Copy", 'c'));
    mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
    mi.addActionListener(l);
    mi = menu.add(new JMenuItem("Paste", 'p'));
    mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK));
    mi.addActionListener(l);

    setJMenuBar(bar);
    getContentPane().add(new JLabel("A placeholder"));

    pack();
    setSize(300, 300);
    setVisible(true);
  }

  private class EditListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.out.println(e.getActionCommand());
    }
  }

  public static void main(String arg[]) {
    new MenuAcceleratorKeyStroke();
  }
}
