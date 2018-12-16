package com.macwargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class test
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI()
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600,600);

        final ProjectileShooter projectileShooter =
                new ProjectileShooter();
        ProjectileShooterPanel projectileShooterPanel =
                new ProjectileShooterPanel(projectileShooter);
        projectileShooter.setPaintingComponent(projectileShooterPanel);

        JPanel controlPanel = new JPanel(new GridLayout(1,0));

        controlPanel.add(new JLabel("Angle"));
        final JSlider angleSlider = new JSlider(0, 90, 45);
        controlPanel.add(angleSlider);

        controlPanel.add(new JLabel("Power"));
        final JSlider powerSlider = new JSlider(0, 100, 50);
        controlPanel.add(powerSlider);

        JButton shootButton = new JButton("Shoot");
        shootButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int angleDeg = angleSlider.getValue();
                int power = powerSlider.getValue();
                projectileShooter.setAngle(Math.toRadians(angleDeg));
                projectileShooter.setPower(power);
                projectileShooter.shoot();
            }
        });
        controlPanel.add(shootButton);

        f.getContentPane().setLayout(new BorderLayout());
        f.getContentPane().add(controlPanel, BorderLayout.NORTH);
        f.getContentPane().add(projectileShooterPanel, BorderLayout.CENTER);
        f.setVisible(true);
    }
}

class ProjectileShooter
{
    private double angleRad = Math.toRadians(45);
    private double power = 50;
    private Projectile projectile;
    private JComponent paintingComponent;

    void setPaintingComponent(JComponent paintingComponent)
    {
        this.paintingComponent = paintingComponent;
    }

    void setAngle(double angleRad)
    {
        this.angleRad = angleRad;
    }

    void setPower(double power)
    {
        this.power = power;
    }

    void shoot()
    {
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                executeShot();
            }
        });
        t.setDaemon(true);
        t.start();
    }

    private void executeShot()
    {
        if (projectile != null)
        {
            return;
        }
        projectile = new Projectile();

        Point2D velocity = AffineTransform.getRotateInstance(angleRad).transform(new Point2D.Double(1,0), null);
        velocity.setLocation(velocity.getX() * power * 0.5, velocity.getY() * power * 0.5);
        projectile.setVelocity(velocity);

        //System.out.println("Initial "+velocity);

        long prevTime = System.nanoTime();
        while (projectile.getPosition().getY() >= 0)
        {
            long currentTime = System.nanoTime();
            double dt = 3 * (currentTime - prevTime) / 1e8;
            projectile.performTimeStep(dt);

            prevTime = currentTime;
            paintingComponent.repaint();
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }

        projectile = null;
        paintingComponent.repaint();
    }

    Projectile getProjectile()
    {
        return projectile;
    }
}

class Projectile
{
    private final Point2D ACCELERATION = new Point2D.Double(0, -9.81 * 0.1);

    private final Point2D position = new Point2D.Double();
    private final Point2D velocity = new Point2D.Double();

    public Point2D getPosition()
    {
        return new Point2D.Double(position.getX(), position.getY());
    }
    public void setPosition(Point2D point)
    {
        position.setLocation(point);
    }

    public void setVelocity(Point2D point)
    {
        velocity.setLocation(point);
    }

    void performTimeStep(double dt)
    {
        scaleAddAssign(velocity, dt, ACCELERATION);
        scaleAddAssign(position, dt, velocity);

        //System.out.println("Now at "+position+" with "+velocity);
    }

    private static void scaleAddAssign(Point2D result, double factor, Point2D addend){
        double x = result.getX() + factor * addend.getX();
        double y = result.getY() + factor * addend.getY();
        result.setLocation(x, y);
    }

}

class ProjectileShooterPanel extends JPanel
{
    private final ProjectileShooter projectileShooter;

    public ProjectileShooterPanel(ProjectileShooter projectileShooter)
    {
        this.projectileShooter = projectileShooter;
    }

    @Override
    protected void paintComponent(Graphics gr)
    {
        super.paintComponent(gr);
        Graphics2D g = (Graphics2D)gr;

        Projectile projectile = projectileShooter.getProjectile();
        if (projectile != null)
        {
            g.setColor(Color.RED);
            Point2D position = projectile.getPosition();
            int x = (int)position.getX();
            int y = getHeight() - (int)position.getY();
            g.fillOval(x-01, y-10, 20, 20);
        }
    }
}