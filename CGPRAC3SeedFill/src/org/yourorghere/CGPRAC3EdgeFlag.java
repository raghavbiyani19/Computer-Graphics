package org.yourorghere;

import static java.lang.Math.abs;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

class Point{
    int x;
    int y;
    Point(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
       @Override
    public boolean equals(Object o) { 
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Point)) { 
            return false; 
        }
        // typecast o to Complex so that we can compare data members  
        Point p = (Point) o; 
        // Compare the data members and return accordingly  
        return Integer.compare(x, p.x) == 0
                && Integer.compare(y, p.y) == 0; 
    } 
}

 class EdgeFiller implements GLEventListener {
ArrayList<Point> BoundaryValues=new ArrayList<Point>();
 private GLU glu;
int xmin,xmax,ymin,ymax;
 void getminmax()
 {
        xmin=BoundaryValues.get(0).x;
        xmax=BoundaryValues.get(0).x;
        ymin=BoundaryValues.get(0).y;
        ymax=BoundaryValues.get(0).y;
     for(int i=0;i<BoundaryValues.size();i++)
     {
         if(xmin>BoundaryValues.get(i).x)
             xmin=BoundaryValues.get(i).x;
         if(xmax<BoundaryValues.get(i).x)
             xmax=BoundaryValues.get(i).x;
         if(ymin>BoundaryValues.get(i).y)
             ymin=BoundaryValues.get(i).y;
         if(ymax<BoundaryValues.get(i).y)
             ymax=BoundaryValues.get(i).y;
     }
  }

 public EdgeFiller()
 {
     
 }

 public void edge(GL gl)
 {   
     boolean inside=false;
     for(int i=ymin;i<=ymax;i++)
     {
         inside = false;
         for(int j=xmin;j<=xmax;j++)
         {
           if(BoundaryValues.contains(new Point(j,i)))
           {
               inside= !inside;
           }
           if(inside==true)
           {
            System.out.println("Pixel colored: ("+j+","+i+")");
            gl.glColor3f(0.5f, 0.5f, 0.5f);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2i(j,i);
            gl.glEnd();
           }
           else
           {
            gl.glColor3f(0.0f, 0.0f, 0.0f);
            gl.glBegin(GL.GL_POINTS);
            gl.glVertex2i(j,i);
            gl.glEnd();
               
           }
         }
     }
 }
 
public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();
    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    gl.glViewport(0, 0, 640, 480);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0, 640, 0, 480);
}

 @Override
        public void display(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
              
//                 BresenhamLine(gl, 100,100, 100, 200);
//                 BresenhamLine(gl, 100,100, 200, 200);
//                 BresenhamLine(gl, 100,200, 200, 100);
//                 BresenhamLine(gl, 200,100, 200, 200);
                 
                 BresenhamLine(gl, 100,100, 200, 100);
                 BresenhamLine(gl, 100,100, 150, 200);
                 BresenhamLine(gl, 150,200, 200, 100);
                 
                 
                getminmax();
                edge(gl);
           }
       
        public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                int height) {
        }

        public void displayChanged(GLAutoDrawable drawable,
                boolean modeChanged, boolean deviceChanged) {
        }
        public int sign(float x)
            {
            if(x>0)
            return 1;
            else if(x<0)
            return -1;
            else
            return 0;
            }

       
     void BresenhamLine(GL gl,int x1,int y1,int x2,int y2){
    int x,y,dx,dy,temp,e;
    int s1,s2,interchange=0;
    x=x1;
    y=y1;
    dx=abs(x2-x1);
    dy=abs(y2-y1);
    s1=sign(x2-x1);
    s2=sign(y2-y1);
    //interchange dx and dy
    if(dy>dx){
    temp=dx;
    dx=dy;
    dy=temp;
    interchange=1;
    }
    else
    {
    interchange=0;
    }
    e=(2*dy)-dx;
    for(int i=1;i<=dx;i++)
    {
    gl. glBegin(GL.GL_POINTS);
    gl.glVertex2i(x,y);
    gl.glEnd();
    BoundaryValues.add(new Point(x,y));
     
    while(e>0)
    {
    if(interchange==1){
    x=x+s1;
    }
    else
    {
    y=y+s2;
    }
    e=e-(2*dx);
    }
    if(interchange==1){
    y=y+s2;
    }
    else
    {
    x=x+s1;
    }
    e=e+(2*dy);

    }
    }
     
public void dispose(GLAutoDrawable arg0) {
}
}
public class CGPRAC3EdgeFlag {
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities();
            final GLCanvas glcanvas = new GLCanvas(capabilities);
             EdgeFiller p = new EdgeFiller();
            glcanvas.addGLEventListener(p);
            glcanvas.setSize(400, 400);
            final JFrame frame = new JFrame("Edge Fill Algorithm");
            frame.add(glcanvas);
            frame.setSize(640, 480);
            frame.setVisible(true);
            }
}

