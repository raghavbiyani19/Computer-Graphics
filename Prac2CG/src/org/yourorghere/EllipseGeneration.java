package org.yourorghere;

import java.util.ArrayList;
import java.util.Scanner;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class Ellipse implements GLEventListener {
 private GLU glu;
 int a;
 int b;
ArrayList<Integer> x;
ArrayList<Integer> y;
ArrayList<Integer> x2;
ArrayList<Integer> y2;
 public Ellipse()
 {
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
    glu.gluOrtho2D(-640/2.0, 640/2.0, -480/2.0, 480/2.0);//for making origin at center
}
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    Scanner in = new Scanner(System.in);
    System.out.println("Enter a:");
    a=in.nextInt();
     System.out.println("Enter b:");
    b=in.nextInt();
    /*System.out.println("Enter origin x coordinate");
    int ox=in.nextInt();
    System.out.println("Enter origin y coordinate:");
    int oy=in.nextInt();
    glu.gluOrtho2D(-oy,oy,-ox,ox);*/
    draw_Ellipse(gl);
   }

public void draw_Ellipse(GL gl)
{
    int xi=0,yi=b;
    int di=(int)a*a+b*b-(2*b*a*a);
    //float limit=R/(float)sqrt(2); for one octant
    int limit=0;//for full quadrant in clockwise direction 
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
             d1=(2*di)-(2*yi*a*a)-(a*a);
             if(d1<=0)
             {
                 //call mh
                 xi=xi+1;
                 di=di+(2*xi*b*b)+(b*b);
             }
             else
             {
                 //call md
                 xi=xi+1;
                 yi=yi-1;
                  di=di+(2*xi*b*b)-(2*yi*a*a)+(a*a)+(b*b);
             }
         }
         else if(di>0)
         {
              d2=(2*di)+(2*xi*b*b)-(b*b);
             if(d2<=0)
             {
                 //call md
                 xi=xi+1;
                 yi=yi-1;
                  di=di+(2*xi*b*b)-(2*yi*a*a)+(a*a)+(b*b);
             }
             else
             {
                 //call mv
                 yi=yi-1;
                 di=di-(2*yi*a*a)+(a*a);
             }
             
         }
         else
         {
             //call md
                 xi=xi+1;
                 yi=yi-1;
                 di=di+(2*xi*b*b)-(2*yi*a*a)+(a*a)+(b*b);
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

public class EllipseGeneration {
    public static void main(String[] args) {
            GLCapabilities capabilities = new GLCapabilities();
            // The canva
            final GLCanvas glcanvas = new GLCanvas(capabilities);
            Ellipse e=new Ellipse();
             glcanvas.addGLEventListener(e);
            glcanvas.setSize(400, 400);
            //creating frame
            final JFrame frame = new JFrame("Ellipse");
            //adding canvas to frame
            frame.add(glcanvas);
            frame.setSize(640, 480);
            frame.setVisible(true);
}
}
