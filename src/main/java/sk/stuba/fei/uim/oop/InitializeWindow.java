package sk.stuba.fei.uim.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InitializeWindow implements KeyListener, MouseListener,MouseMotionListener {

    private final MyMaze maze;
    private final DrawMaze drawMaze;
    private final JButton up;
    private final JButton left;
    private final JButton right;
    private final JButton down;
    private final JLabel labelText;


    public InitializeWindow(int sizeOfGrid, int sizeOfField) {

        JFrame appWindow = new JFrame();
        appWindow.setFocusable(true);

        JPanel p = new JPanel();
        appWindow.setSize(sizeOfField*sizeOfGrid + 100, sizeOfField*sizeOfGrid+100);
        appWindow.setLayout(new BorderLayout());
        appWindow.setLocationRelativeTo(null);
        maze = new MyMaze(sizeOfGrid, sizeOfGrid, sizeOfField);
        maze.generateMaze();
        drawMaze = new DrawMaze(maze);
        drawMaze.addKeyListener(this);
        drawMaze.addMouseListener(this);
        drawMaze.addMouseMotionListener(this);
        labelText = new JLabel("Number of wins: 0", JLabel.CENTER);
        up = new JButton("Up");
        up.addActionListener(e -> {
            maze.moveUp();
            labelText.setText(maze.getLabelText());
            drawMaze.repaint();
        });
        down = new JButton("Down");
        down.addActionListener(e -> {
            maze.moveDown();
            labelText.setText(maze.getLabelText());
            drawMaze.repaint();
        });
        left = new JButton("Left");
        left.addActionListener(e -> {
            maze.moveLeft();
            labelText.setText(maze.getLabelText());
            drawMaze.repaint();
        });
        right = new JButton("Right");
        right.addActionListener(e -> {
            maze.moveRight();
            labelText.setText(maze.getLabelText());
            drawMaze.repaint();
        });
        JButton reset = new JButton("Reset");
        reset.addActionListener(e -> {
            maze.generateMaze();
            maze.setNumberOfWins(0);
            labelText.setText(maze.getLabelText());
            drawMaze.repaint();
        });

        p.setLayout(new GridLayout(2, 3));
        p.add(labelText);
        p.add(up);
        p.add(reset);
        p.add(left);
        p.add(down);
        p.add(right);
        appWindow.add("North", p);
        appWindow.setDefaultCloseOperation(appWindow.EXIT_ON_CLOSE);
        appWindow.setVisible(true);


        appWindow.add(drawMaze);
        appWindow.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            down.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            up.doClick();
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            left.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent arg0) {
        int x = arg0.getX();
        int y = arg0.getY();
        maze.mouseClickOnTile(x,y);
        labelText.setText(maze.getLabelText());
        drawMaze.repaint();

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    public void mouseExited(MouseEvent arg0) {

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int currentX = e.getX();
        int currentY = e.getY();

         if(maze.mouseMoveDetectPink(currentX,currentY)){
             drawMaze.repaint();
         }
    }
}
