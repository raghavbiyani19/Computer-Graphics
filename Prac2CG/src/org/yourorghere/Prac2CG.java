package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import java.util.*;

class Circle implements GLEventListener {
private GLU glu;
ArrayList<Integer> x;
ArrayList<Integer> y;
ArrayList<Integer> x2;
ArrayList<Integer> y2;
    public Circle(){
       x=new  ArrayList<Integer>();
       y=new  ArrayList<Integer>();
       x2=new  ArrayList<Integer>();
       y2=new  ArrayList<Integer>();
    }
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0, 0, 640, 480);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
       glu.gluOrtho2D(-640/2.0, 640/2.0, -480/2.0, 480/2.0);//for making origin ata center
    }
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
        int R1=70;
        int R2=70;

      draw_Circle(gl,R1);
    }
    public void draw_Circle(GL gl,int R)
    {
        int xi=0,yi=R,di=2*(1-R);
        int limit=0; 
        int d1,d2;
        while(yi>=limit)
        {
        x.add(xi);
        y.add(yi);
        gl. glBegin(GL.GL_POINTS);
        gl.glVertex2i(xi,yi);
        gl.glEnd();
        if(di<0)
        {
            d1=(2*di)-(2*xi)-1;
            if(d1<=0)
            {
                //call mh
                xi=xi+1;
                di=di+(2*xi)+1;
            }
            else
            {
                //call md
                xi=xi+1;
                yi=yi-1;
                di=di+(2*xi)-(2*yi)+2;
            }
        }
        else if(di>0)
        {
             d2=(2*di)+(2*yi)-1;
            if(d2<=0)
            {
                //call md
                xi=xi+1;
                yi=yi-1;
                di=di+(2*xi)-(2*yi)+2;
            }
            else
            {
                //call mv
                yi=yi-1;
                di=di-(2*yi)+1;
            }
        }
        else
        {
            //call md
                xi=xi+1;
                yi=yi-1;
                di=di+(2*xi)-(2*yi)+2;
        }
        }
        reflection_y(gl);
        reflection_x(gl);
    }
public void reflection_y(GL gl)
{
    for(int i=0;i<x.size();i++)
    {
        gl. glBegin(GL.GL_POINTS);
        gl.glVertex2i(-x.get(i),y.get(i));
        gl.glEnd();
        x2.add(-x.get(i));
        y2.add(y.get(i));
    }
}
public void reflection_x(GL gl)
{
    for(int i=0;i<x.size();i++)
    {
         gl. glBegin(GL.GL_POINTS);
         gl.glVertex2i(x.get(i),-y.get(i));
         gl.glEnd();
         gl. glBegin(GL.GL_POINTS);
         gl.glVertex2i(x2.get(i),-y2.get(i));
         gl.glEnd();
    }
}
public void reshape(GLAutoDrawable drawable, int x, int y, int width,int height) {
    }
public void displayChanged(GLAutoDrawable drawable,boolean modeChanged, boolean deviceChanged) {
    }
public void dispose(GLAutoDrawable arg0) {
    }
}
public class Prac2CG {
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Circle c = new Circle();
        glcanvas.addGLEventListener(c);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame("Basic frame");
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}