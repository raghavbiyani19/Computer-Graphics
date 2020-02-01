package org.yourorghere;
import java.util.*;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {
    private GLU glu;
    ArrayList<Integer> xvertices = new ArrayList<Integer>();
    ArrayList<Integer> yvertices = new ArrayList<Integer>();
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 640, 480);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(0, 640, 0, 480);
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glPointSize(3);
        drawLine(gl);
    }
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
    private void Bresenham(GL gl, int x1, int y1, int x2, int y2) {
        gl.glBegin(GL.GL_POINTS);
        int x = x1, y = y1;
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int change = 0;
        int s1 = (int) Math.signum(x2 - x1);
        int s2 = (int) Math.signum(y2 - y1);
        if (dy > dx) {
            int temp = dx;
            dx = dy;
            dy = temp;
            change = 1;
        } else {
            change = 0;
        }
        int e = (2 * dy) - dx;
        for (int i = 0; i <= dx; i++) {
            gl.glVertex2i(x, y);
            xvertices.add(x);
            yvertices.add(y);
            while (e > 0) {
                if (change == 1) {
                    x = x + s1;
                } else {
                    y = y + s2;
                }
                e -= 2 * dx;
            }
            if (change == 1) {
                y = y + s2;
            } else {
                x = x + s1;
            }
            e += 2 * dy;
        }
        gl.glEnd();
    }
    public void BresenhamThicc(GL gl, int x1, int y1, int x2, int y2, int thickness) {
        for (int i = 0; i < thickness; i++) {
            if (x1 == x2) {
                Bresenham(gl, x1 + i, y1, x2 + i, y2);
            } else if (y2 == y1) {
                Bresenham(gl, x1, y1 + i, x2, y2 + i);
            } else {
                Bresenham(gl, x1 + i, y1 + i, x2 + i, y2 + i);
            }
        }
    }
    private void drawLine(GL gl) {
        /*Bresenham(gl, 200, 200,240, 200);
        Bresenham(gl, 240, 200, 240, 240);
        Bresenham(gl, 240, 240, 200, 240);
        Bresenham(gl, 200,240, 200, 200);*/
        
        Bresenham(gl, 100,100, 100, 200);
        Bresenham(gl, 100,100, 200, 200);
        Bresenham(gl, 100,200, 200, 100);
        Bresenham(gl, 200,100, 200, 200);

        //seedFill(gl, 225, 225);
        seedFill(gl, 110, 150);

    }
    void seedFill(GL gl, int x, int y) {
        boolean flag = false;
        for (int i = 0; i < xvertices.size(); i++) {
            if (xvertices.get(i) == x && yvertices.get(i) == y) {
                flag=true;
            }
        }
        if (!flag) {
            gl.glColor3f(50, 100, 150);
            gl.glBegin(GL.GL_POINTS);
            xvertices.add(x);
            yvertices.add(y);
            System.out.println("X: "+x+" Y: "+y);
            gl.glVertex2i(x, y);
            gl.glEnd();
            seedFill(gl, x + 1, y);
            seedFill(gl, x - 1, y);
            seedFill(gl, x, y + 1);
            seedFill(gl, x, y - 1);
        }
    }
    
    int savex;
    public void dispose(GLAutoDrawable arg0) {
    }
}
public class CGPRAC3SeedFill {
    public static void main(String args[]) {
        GLCapabilities capabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame("Basic frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}