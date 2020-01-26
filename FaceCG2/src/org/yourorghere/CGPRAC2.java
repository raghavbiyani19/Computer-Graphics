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
int a;
 int b;
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
      draw_Circle(gl,100,40,40);
      draw_Circle(gl,20,0,70);
      draw_Circle(gl,20,70,70);
      draw_Ellipse(gl,35,25,20,30);
      draw_Ellipse(gl,35,-30,50,10);
    }
    public void draw_Circle(GL gl,int R,int h,int k)
    {
        int xi=0,yi=R,di=2*(1-R);
        int limit=0; 
        int d1,d2;
        while(yi>=limit)
        {
        //x.add(xi);
       // y.add(yi);
        gl. glBegin(GL.GL_POINTS);
        gl.glVertex2i(xi+h,yi+k);
        gl.glVertex2i(-xi+h, -yi+k);
        gl.glVertex2i(xi+h, -yi+k);
        gl.glVertex2i(-xi+h, yi+k);
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
       // reflection_y(gl);
       // reflection_x(gl);
    }
    
    
    
    public void draw_Ellipse(GL gl,int h,int k,int a,int b)
{
    int xi=0,yi=b;
    int di=(int)a*a+b*b-(2*b*a*a);
    //float limit=R/(float)sqrt(2); for one octant
    int limit=0;//for full quadrant in clockwise direction 
    int d1,d2;
    while(yi>=limit)
    {
       // x.add(xi);
        //y.add(yi);
         gl. glBegin(GL.GL_POINTS);
         //gl.glVertex2i(xi,yi);
         gl.glVertex2i(xi+h,yi+k);
        gl.glVertex2i(-xi+h, -yi+k);
        gl.glVertex2i(xi+h, -yi+k);
        gl.glVertex2i(-xi+h, yi+k);
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
   
}


public void reshape(GLAutoDrawable drawable, int x, int y, int width,int height) {
    }
public void displayChanged(GLAutoDrawable drawable,boolean modeChanged, boolean deviceChanged) {
    }
public void dispose(GLAutoDrawable arg0) {
    }
}
public class CGPRAC2 {
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities();
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Circle c = new Circle();
        glcanvas.addGLEventListener(c);
        glcanvas.setSize(400, 400);
        final JFrame frame = new JFrame("Basic frame");
        frame.add(glcanvas);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
