package org.yourorghere;

import static java.lang.Math.abs;
import static java.lang.Math.cos;
import static java.lang.Math.floor;
import static java.lang.Math.sin;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;


public class DemoCG implements GLEventListener {

private GLU glu;

public void init(GLAutoDrawable gld) {
    GL gl = gld.getGL();
    glu = new GLU();
    gl.glClearColor(0.5f,0.5f,0.5f,1.0f);
    gl.glViewport(0,0,900,900);
    gl.glMatrixMode(GL.GL_PROJECTION);
    gl.glLoadIdentity();
    glu.gluOrtho2D(0,640,0,480);
}

/**
 * Take care of drawing here.
     * @param drawable
 */
public void display(GLAutoDrawable drawable) {
    GL gl = drawable.getGL();

    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    
    //drawDDA(gl, 0, 0, 100, 100);
//    gl.glColor3f(1.0f, 1.0f, 0.36f );
    gl.glColor3f(0f,0f,0f);
    drawLine(gl);
//    drawDDA(gl);
//    drawMountain(gl);
//    drawCircle(gl);
//    
//    drawDDAthick(gl,100.0f,100.0f,400.0f,100.0f,10);
//    
//    drawDDA(gl,101.0f,100.0f,401.0f,100.0f,0);
//    
//        drawDDA(gl,101.0f+1,100.0f,401.0f+1,100.0f,0);
//
//            drawDDA(gl,101.0f+1,100.0f,401.0f+1,100.0f,0);


    drawPentagon(gl,250.0f,150.0f,155.0f,219.0f,155,219,191,331.0f,191.0f,331.0f,309.0f,331.0f,309.0f,331.0f,345.0f,219.0f,345.0f,219.0f,250.0f,150.0f,0);
   
    drawPentagon(gl,250.0f,130.0f,136.0f,213.0f,136.0f,213.0f,179.0f,347.0f,179.0f,347.0f,321,347.0f,321.0f,347.0f,364.0f,213.0f,364.0f,213.0f,250.0f,130.0f,1);
   
    drawPentagon(gl,250.0f,110.0f,117.0f,207.0f,117.0f,207.0f,168.0f,363.0f,168.0f,363.0f,332.0f,363.0f,332.0f,363.0f,383.0f,207.0f,383.0f,207.0f,250.0f,110.0f,2);
   
    drawPentagonBOLD(gl,250.0f,90.0f,98.0f,201.0f,98.0f,201.0f,156.0f,379.0f,156.0f,379.0f,344.0f,379.0f,344.0f,379.0f,402.0f,201.0f,402.0f,201.0f,250.0f,90.0f,10);
   
}
public void reshape(GLAutoDrawable drawable, int x, int y, int width,
        int height) {
}
public void displayChanged(GLAutoDrawable drawable,
        boolean modeChanged, boolean deviceChanged) {
}
public void drawPentagon(GL gl,float x11,float y11,float x12,float y12,float x21,float y21,float x22,float y22,float x31,float y31,float x32,float y32,float x41,float y41,float x42,float y42,float x51,float y51,float x52,float y52,int choice){
    drawDDA(gl,x11,y11,x12,y12,choice);
        drawDDA(gl,x21,y21,x22,y22,choice);

            drawDDA(gl,x31,y31,x32,y32,choice);    drawDDA(gl,x41,y41,x42,y42,choice);
                drawDDA(gl,x51,y51,x52,y52,choice);
               
               


}

public void drawPentagonBOLD(GL gl,float x11,float y11,float x12,float y12,float x21,float y21,float x22,float y22,float x31,float y31,float x32,float y32,float x41,float y41,float x42,float y42,float x51,float y51,float x52,float y52,int thickness){
   
   
   
    drawDDAthick(gl,x11,y11,x12,y12,thickness);
        drawDDAthick(gl,x21,y21,x22,y22,thickness);

            drawDDAthick(gl,x31,y31,x32,y32,thickness);    drawDDAthick(gl,x41,y41,x42,y42,thickness);
                drawDDAthick(gl,x51,y51,x52,y52,thickness);
               
               


}

private void drawDDA(GL gl) {
    //gl.glPointSize(3.0f);
    gl.glBegin(GL.GL_LINES);// begin plotting points
   
        gl.glVertex2i(0,0 );
        gl.glVertex2i(100,10 );
        gl.glVertex2i(150,150 );
        gl.glVertex2i(250,400);
   
    gl.glEnd();//end drawing of points
}

public void drawDDAthick(GL gl,float x1,float y1,float x2,float y2,int thickness){
   
   
    for(int i=01;i<thickness;i++){
        if(x1==x2){
        drawDDA(gl,x1,y1+i,x2,y2+i,0);
        }
        if(y1==y2){
                drawDDA(gl,x1,y1+i,x2,y2+i,0);

        }
        else{
                drawDDA(gl,x1,y1+i,x2,y2+i,1);

        }
       
    }
}

public void drawDDA(GL gl,float x1,float y1,float x2,float y2,int c){
 
        int lx=(int) floor(abs(x1-x2));
        int ly=(int) floor(abs(y1-y2));
        int length=0;
        if(lx>=ly){
            length=lx;
        }
        else
            length=ly;
        float dx,dy;
        dx=(x2-x1)/length;
        dy=(y2-y1)/length;
        float x,y;
        x=x1;
        y=y1;
         int flag=10;
        int i=1;
        while(i<=length){
              gl.glBegin(GL.GL_POINTS);
   
              if(c==1){
                  if(i%2==0)
                    gl.glVertex2i((int)floor(x),(int)floor(y));
              }
              if(c==0){
                    gl.glVertex2i((int)floor(x),(int)floor(y));
              }
             
              if(c==2){
                  if(i%19==0){
                         flag=0;
                         //if(flag<=2)
                         //System.out.println("SKIP");
                  }
                  else
                  {gl.glVertex2i((int)floor(x),(int)floor(y));
                      //System.out.println((int)floor(x)+"||"+(int)floor(y));
                      flag=3;
                  }

              }
              if(c==3){
              }
            x+=dx;
            y+=dy;
            i++;
           
            gl.glEnd();
        }
}

private void drawLine(GL gl) {
        gl.glPointSize(3.0f);
        
        //For DDA Algorithim
        /*AlgoDDA(100,100, 150, 100,3, gl);  //Thick Square
        AlgoDDA(150,100,150,150,4,gl);
        AlgoDDA(150,150,100,150,3,gl);
        AlgoDDA(100,150,100,100,4, gl);
        
        AlgoDDA(75,75,175,75,1, gl);  //dash Square
        AlgoDDA(175,75,175,175,1,gl);
        AlgoDDA(175,175,75,175,1,gl);
        AlgoDDA(75,175,75,75,1,gl);
        
        AlgoDDA(50,50,200,50,2, gl);  //dotted Square
        AlgoDDA(200,50,200,200,2,gl);
        AlgoDDA(200,200,50,200,2,gl);
        AlgoDDA(50,200,50,50,2,gl);
        
        AlgoDDA(25,25,225,25,5, gl);  //Plain Square
        AlgoDDA(225,25,225,225,5,gl);
        AlgoDDA(225,225,25,225,5,gl);
        AlgoDDA(25,225,25,25,5,gl);*/
        
        //Generlize B Algorithim
        /*
        //Plain Penta
        Bres(250,90,98,201,3,gl);
        Bres(98,201,156,379,3,gl);
        Bres(156,379,344,379,3,gl);
        Bres(344,379,402,201,3,gl);
        Bres(402,201,250,90,3,gl);
        
        //dotted Penta
        Bres(250,110,117,207,1,gl);
        Bres(117,207,168,363,1,gl);
        Bres(168,363,332,363,1,gl);
        Bres(332,363,383,207,1,gl);
        Bres(383,207,250,110,1,gl);
        
        
        //dashed
        Bres(250,130,136,213,2,gl);
        Bres(136,213,179,347,2,gl);
        Bres(179,347,321,347,2,gl);
        Bres(321,347,364,213,2,gl);
        Bres(364,213,250,130,2,gl);
        
        //Thick Penta
        Bres(250,150,155,219,5,gl);
        Bres(155,219,191,331,5,gl);
        Bres(191,331,309,331,5,gl);
        Bres(309,331,345,219,5,gl);
        Bres(345,219,250,150,5,gl);
                */
    }


public void Bres(int x1, int y1, int x2, int y2, int choice,GL gl){
        //Initialization
        int x=x1,y=y1,temp,ich;
        int dy= Math.abs(y2 - y1),dx =Math.abs(x2 - x1);
        int s1 = (int) Math.signum(x2-x1),s2 = (int) Math.signum(y2-y1);
        //Interchange
        if(dy>dx){
            temp=dx;
            dx=dy;
            dy=temp;
            ich=1;
        }
        else
            ich=0;
        //Error term
        int e1 = 2*dy-dx;
        
        //Point Pixel
        switch(choice){
            case 1: System.out.println("Dashed Line ::: ");
                    for(int i=0;i<dx;i++){  //dashed line
                        if(i%20!=0)
                            Plot(x,y,gl);
                        while(e1>0){
                            if(ich==1)
                                x=x+s1;
                            else
                                y=y+s2;
                            e1=e1-2*dx;
                        }
                        if(ich==1)
                            y=y+s2;
                        else
                            x=x+s1;
                        e1=e1+2*dy;
                    }
                    break;
            case 2: for(int i=0;i<dx;i++){  //dotted line
                        if(i%2==0)
                            Plot(x,y,gl);
                        while(e1>0){
                            if(ich==1)
                                x=x+s1;
                            else
                                y=y+s2;
                            e1=e1-2*dx;
                        }
                        if(ich==1)
                            y=y+s2;
                        else
                            x=x+s1;
                        e1=e1+2*dy;
                    }
                    break;
            case 3: for(int i=0;i<dx;i++){  //thick horizontal line
                        Plot(x,y,gl);
                        Plot(x,y+1,gl);
                        Plot(x,y+2,gl);
                        while(e1>0){
                            if(ich==1)
                                x=x+s1;
                            else
                                y=y+s2;
                            e1=e1-2*dx;
                        }
                        if(ich==1)
                            y=y+s2;
                        else
                            x=x+s1;
                        e1=e1+2*dy;
                    }
                    break;
            case 4: for(int i=0;i<dx;i++){  //thick vertical line
                        Plot(x,y,gl);
                        Plot(x+1,y,gl);
                        Plot(x+2,y,gl);
                        while(e1>0){
                            if(ich==1)
                                x=x+s1;
                            else
                                y=y+s2;
                            e1=e1-2*dx;
                        }
                        if(ich==1)
                            y=y+s2;
                        else
                            x=x+s1;
                        e1=e1+2*dy;
                    }
                    break;
            case 5:for(int i=0;i<dx;i++){  //plain line
                        Plot(x,y,gl);
                        while(e1>0){
                            if(ich==1)
                                x=x+s1;
                            else
                                y=y+s2;
                            e1=e1-2*dx;
                        }
                        if(ich==1)
                            y=y+s2;
                        else
                            x=x+s1;
                        e1=e1+2*dy;
                    } 
                    break;
        }
    }

public void Plot(int x, int y,GL gl){
            gl.glBegin(GL.GL_POINTS);// begin plotting points
            System.out.println("x: " + x + " y: " + y);
            gl.glVertex2i(x, y);
            gl.glEnd();//end drawing of points
    }

private void drawMountain(GL gl) {
    //gl.glPointSize(3.0f);
    gl.glBegin(GL.GL_TRIANGLES);// begin plotting points
   
        gl.glVertex2f(0.0f,0.0f);
        gl.glVertex2f(60.0f,120.0f);
        gl.glVertex2f(120.0f,0.0f);
       
        gl.glVertex2f(120.0f, 0.0f);
        gl.glVertex2f(180.0f,120.0f);
        gl.glVertex2f(240.0f,0.0f);
       
        gl.glVertex2f(240.0f,0.0f);
        gl.glVertex2f(300.0f, 120.0f);
        gl.glVertex2f(360.0f, 0.0f);
       
        gl.glVertex2f(360.0f, 0.0f);
        gl.glVertex2f(420.0f,120.0f);
        gl.glVertex2f(480.0f, 0.0f);
       
        gl.glVertex2f(480.0f,0.0f);
        gl.glVertex2f(540.0f, 120.0f);
        gl.glVertex2f(600.0f,0.0f);

    gl.glEnd();//end drawing of points
   
    ////////////////////////////////
    double x1,y1,x2,y2;
float angle;
double radius=100;

x1 = 300.0d;y1=300.0d;

gl.glColor3f(1.0f,1.0f,0.0f);

gl.glBegin(GL.GL_TRIANGLE_FAN);
gl.glVertex2d(x1,y1);

for (angle=1.0f;angle<361.0f;angle+=0.2)
{
    x2 = x1+sin(angle)*radius;
    y2 = y1+cos(angle)*radius;
    gl.glVertex2d(x2,y2);
}

gl.glEnd();
   
   
}
private void drawCircle(GL gl){
   
}

public void dispose(GLAutoDrawable arg0)
{

}
}