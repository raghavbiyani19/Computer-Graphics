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


 
        /**
        * Take care of initialization here.
        */
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
            gl.glColor3f(0.5f, 0.5f, 1.0f);
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
       
       
       
public String computeStatus(Line l){
    String ans="";
    String oc1="",oc2="",ands="";
    if(l.y1>400)
        oc1+='1';
    else
        oc1+='0';
    if(l.y1<100)
        oc1+='1';
    else
        oc1+='0';
    if(l.x1>400)
        oc1+='1';
    else
        oc1+='0';
    if(l.x1<100)
        oc1+='1';
    else
        oc1+='0';
    //second endpt
    if(l.y2>400)
        oc2+='1';
    else
        oc2+='0';
    if(l.y2<100)
        oc2+='1';
    else
        oc2+='0';
    if(l.x2>400)
        oc2+='1';
    else
        oc2+='0';
    if(l.x2<100)
        oc2+='1';
    else
        oc2+='0';
   
    //ANDING
    for(int i=0;i<4;i++){
        if(oc1.charAt(i)=='1' && oc2.charAt(i)=='1')
            ands+='1';
        else
            ands+='0';
    }
    System.out.println("AND ====>"+ands);
    if(ands.equalsIgnoreCase("0000") && oc1.equalsIgnoreCase("0000") && oc2.equalsIgnoreCase("0000"))
        return "TV";
    else if(!ands.equalsIgnoreCase("0000"))
        return "TI";
    else
        return "PV";
   
}

 @Override
        public void display(GLAutoDrawable drawable) {
            GL gl = drawable.getGL();
            gl.glClear(GL.GL_COLOR_BUFFER_BIT);
            
            //window
            drawClipWindow(gl,100,100,400,100,400,400,100,400);
            lineToClip(gl,100,80,450,290);
            getminmax();
           }
       
       public int t,b,l,r;
       public float slope,c;
        public void drawClipWindow(GL gl,int x1,int y1,int x2,int y2,int x3,int y3,int x4,int y4){
            drawLineBresenham(gl,x1,y1,x2,y2);
            drawLineBresenham(gl,x2,y2,x3,y3);
            drawLineBresenham(gl,x3,y3,x4,y4);
            drawLineBresenham(gl,x4,y4,x1,y1);
            t=y3;
            b=y1;
            r=x2;
            l=x1;
        }
       
        public void lineToClip(GL gl,int x1,int y1,int x2,int y2){
          ArrayList<Integer> out1=new ArrayList<Integer>();
          ArrayList<Integer> out2=new ArrayList<Integer>();

          out1=getOutcode(x1,y1);
          out2=getOutcode(x2,y2);
         
          Point p1=new Point(x1,y1);
          Point p2=new Point(x2,y2);       
          Line l=new Line(p1,p2);
          String hit=computeStatus(l);
          slope=l.slope;
          System.out.println(""+hit);
          Point f1=null,f2=null;
            if(!hit.equals("TI") && !hit.equals("TV"))
            {f1=fixIt(gl,p1,out1);
            f2=fixIt(gl,p2,out2);
            drawFinalLine(gl,f1.x,f1.y,f2.x,f2.y);
            }
        }
        
        public Point fixIt(GL gl,Point p,ArrayList<Integer> out){
            int fx=p.x;
            int fy=p.y;
           
            if(out.get(0)==1){
                fy=t;
                float a=(t-c)/slope;
                fx=(int)a;
               
            }
            if(out.get(1)==1){
                fy=b;
                   float a=(b-c)/slope;
                fx=(int)a;
               
               
            }
            if(out.get(2)==1){
                fx=r;
                float b=slope*r+c;
                fy=(int)b;
            }

            if(out.get(3)==1){
                fx=l;
                float b=slope*l+c;
                fy=(int)b;

            }
           
           
       
            return new Point(fx,fy);
        }
   
       
        public ArrayList<Integer> getOutcode(int x,int y){
            ArrayList<Integer> out=new ArrayList<Integer>();
            if(y>t)
                out.add(1);
            else
               out.add(0);
            if(y<b)
                out.add(1);
            else
                out.add(0);
            if(x>r)
                out.add(1);
            else
                out.add(0);
            if(x<l)
                out.add(1);
            else
                out.add(0);
           
            System.out.println("Outcode==>\t"+out);
           
            return out;
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

       
       
void drawFinalLine(GL gl,int x1,int y1,int x2,int y2){
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
//            gl.glColor3f(0.2f, 0.5f, 0.8f);
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

       
       
       
       
void drawLineBresenham(GL gl,int x1,int y1,int x2,int y2){
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
class Line{
    int x1,x2,y1,y2;    public float slope,c;

    Line(Point p1,Point p2){
            x1=p1.x;
            x2=p2.x;
            y1=p1.y;
            y2=p2.y;
            slope=(float)(p2.y-p1.y)/(p2.x-p1.x);c=(y1-slope*x1);
    }
}
public class CGPrac4Clipping {
    public static void main(String[] args) {
        GLCapabilities capabilities = new GLCapabilities();
            // The canvas
            final GLCanvas glcanvas = new GLCanvas(capabilities);
             EdgeFiller p = new EdgeFiller();
             //passing center coordinates
             //first argument length
            glcanvas.addGLEventListener(p);
            glcanvas.setSize(400, 400);
            //creating frame
            final JFrame frame = new JFrame("Edge Flag Algorithm");
            //adding canvas to frame
            frame.add(glcanvas);
            frame.setSize(640, 480);
            frame.setVisible(true);
            }
}