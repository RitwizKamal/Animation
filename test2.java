package lab4;
import java.applet.*;  
import java.awt.*;
import java.awt.event.*;

public class test2 extends Applet implements ActionListener,Runnable {
            int temp = 10;
            int size = temp;
            int a=0, b=0;
            int titli_status=0;
            Button ZoomIn,ZoomOut,Reset,R_Left,R_Right,H_Left,H_Right;
                
            //Create Thread
            Thread t;
            
            //Function to start thread
            public void start()
            {
                t = new Thread(this);
                t.start();
            }
    
            public void init()
            {
                this.setSize(new Dimension(2000,1500));
                //Color clr1 = new Color(175,225,250);
                Color clr1 = new Color(100,255,100);
                setBackground(clr1);
                
                ZoomIn = new Button("ZOOM IN");
                ZoomOut = new Button("ZOOM OUT");
                Reset = new Button("RESET");
                R_Left = new Button("R_LEFT");
                R_Right = new Button("R_RIGHT");
                H_Left = new Button("H_LEFT");
                H_Right = new Button("H_RIGHT");
                add(ZoomIn);
                add(ZoomOut);
                add(Reset);
                add(R_Left);
                add(R_Right);
                add(H_Left);
                add(H_Right);
                ZoomIn.addActionListener(this);
                ZoomOut.addActionListener(this);
                Reset.addActionListener(this);
                R_Left.addActionListener(this);
                R_Right.addActionListener(this);
                H_Left.addActionListener(this);
                H_Right.addActionListener(this);
            }
            
            //double Ox1 = (getX() + getWidth()) / 2;
            //double Oy1 = (getY() + getHeight()) / 2;
            double Ox1 = 962;
            double Oy1 = 503;
            int Ox_plant=962;
            int Oy_plant=503;
            double Ox_titli=962;
            double Oy_titli=503;
            int Ox_robot=962+(962/2)+(962/4)+(962/6);
            int Oy_robot=503+(503/2)+(503/7);
            int Ox_horse=962-(962/2)-(962/4)-(962/8);
            int Oy_horse=503+(503/2)+(503/7);
            
            public void actionPerformed(ActionEvent ae)
            {
                String action = ae.getActionCommand();
           
                if(action.equals("ZOOM OUT") && size >= 2)
                {
                    if( size > temp)
                        size -= temp;
                }
                else if(action.equals("ZOOM IN"))
                    size += temp;
                else if(action.equals("RESET"))
                    size = temp;
                if(action.equals("R_LEFT"))
                {
                    Ox_robot=Ox_robot-10;
                    a++;
                    if(Ox_robot<(962+(962/2)))
                        titli_status=1;
                }
                if(action.equals("R_RIGHT"))
                {
                    Ox_robot=Ox_robot+10;
                    a++;
                    if(Ox_robot>=(962+(962/2)))
                        titli_status=0;
                }
                if(action.equals("H_LEFT"))
                {
                    Ox_horse=Ox_horse-10;
                    b++;
                    if(Ox_horse<(962-(962/2)))
                        titli_status=0;
                }
                if(action.equals("H_RIGHT"))
                {
                    Ox_horse=Ox_horse+10;
                    b++;
                    if(Ox_horse>(962-(962/2)))
                        titli_status=1;
                }
                
                repaint();
            }
            
            public void run()
            {
                
                while(true)
                {
                    //Move object
                    if(titli_status==0)
                    {
                        int R=200;
                        Ox_titli=872; Oy_titli=503;
                        for(double i=0; i<(2*Math.PI); i++)
                        {
                            Ox_titli= Ox_titli+ (R*Math.cos(i));
                            Oy_titli= Oy_titli+ (-1)*(R*Math.sin(i));
                    
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        };
       
                        repaint();
                        }
                    }
                    if(titli_status==1)
                    {
                        int a=600;
                        int b=100;
                        //Ox_titli=(getX()+getWidth())/2 - 250; Oy_titli=(getY()+getHeight())/2;
                        Ox_titli=712; Oy_titli=-253;
                        for(double i=0; i<(2*Math.PI); i++)
                        {
                            Ox_titli= Ox_titli+ (a*Math.cos(i));
                            Oy_titli= Oy_titli+ (-1)*(b*Math.sin(i));
                    
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        };
       
                        repaint();
                        }
                    }
                }
            }
            
            public void stop()
            {
                t=null;
            }
            
            public void paint(Graphics g)
            {
                //ORIGINS
                int Ox = (getX() + getWidth()) / 2;
                int Oy = (getY() + getHeight()) / 2;
                //System.out.println(Ox);
                //System.out.println(Oy);
                
                //DRAWING GRID
                for(int i = Ox; i >= (getX()); i -= size)
                    g.drawLine(i, getY(), i, getHeight());
                for(int i = Ox; i <= (getWidth()); i += size)
                    g.drawLine(i, getY(), i, getHeight());
                for(int i = Oy; i >= (getY()); i -= size)
                    g.drawLine(getX(), i, getWidth(), i);
                for(int i = Oy; i <= (getHeight()); i += size)
                    g.drawLine(getX(), i, getWidth(), i);
                    
                //DRAWING X-AXIS & Y-AXIS
                g.fillRect(Ox-2, Oy-(getHeight()/2), 4, getHeight());
                g.fillRect(Ox-(getWidth()/2), Oy-2, getWidth(), 4);
                
                //create a random object
                g.setColor(Color.DARK_GRAY);
                g.fillRect(Ox-(962/2)-2, Oy+350, 4, 100);
                g.fillRect(Ox+(962/2)-2, Oy+350, 4, 100);
                //g.fillOval((int)(Ox1-(2.5*size)), (int)(Oy1-(2.5*size)), 5*size, 5*size);
                
                //drawPLANT
                PLANT P = new PLANT();
                g.setColor(Color.red);
                P.drawPLANT(g, Ox_plant, Oy_plant+300);
                
                //drawTITLI
                TITLI T = new TITLI();
                g.setColor(Color.blue);
                T.drawTITLI(g, (int)Ox_titli, (int)Oy_titli+500);
                
                //drawROBOT
                if(a%2==0)
                {
                    make_robot1 r = new make_robot1();
                    r.drawRobot1(g, Ox_robot, Oy_robot);
                }
                else
                {
                    make_robot2 r = new make_robot2();
                    r.drawRobot2(g, Ox_robot, Oy_robot);
                }
                
                //drawHORSE
                if(b%2==0)
                {
                    Horse h = new Horse();
                    h.drawHorse1(g, Ox_horse, Oy_horse);
                }
                else
                {
                    Horse h = new Horse();
                    h.drawHorse2(g, Ox_horse, Oy_horse);
                }
            }
            
            public class Polygon
            {
                public void drawPoly(Graphics g,int x[],int y[],int numofpoints)
                {
                    for(int i=0; i< numofpoints; i++)
                    {
                        DDA d = new DDA();
                        d.drawDDA(g,x[i], y[i], x[(i+1)%numofpoints], y[(i+1)%numofpoints]);
                    }
                    DDA d = new DDA();
                    d.drawDDA(g,x[0], y[0], x[numofpoints-1], y[numofpoints-1]);
                    
                }
            }
            public class DDA
            {   
                
                public void drawDDA(Graphics g,float x0,float y0,float x1,float y1)
                {
                    float dx = x1 - x0;
                    float dy = y1 - y0;
                    float steps;
                
                    if (dx > dy)
                        steps = Math.abs(dx);
                    else
                        steps = Math.abs(dy);
                
                    float Xincrement = (dx) / (float)steps;
                    float Yincrement = (dy) / (float)steps;
                    float X = x0;
                    float Y = y0;
                
                    for(int i=0 ; i < steps ; i++)
                    {
                        g.setColor(Color.RED);
                        g.fillRect((int)(X - temp/4), (int)(Y - temp/4), temp/2, temp/2);
                        X = X + Xincrement;
                        Y = Y + Yincrement;
                    }                    
                }
            }
            public class make_robot1{
                
                public void drawRobot1(Graphics g,int Ox,int Oy)
                {
                    int size=6;
                    int x1[]={Ox-4*size,Ox+4*size,Ox+4*size,Ox-4*size};
                    int y1[]={Oy-8*size,Oy-8*size,Oy-14*size,Oy-14*size};
                    Polygon p1 = new Polygon();
                    p1.drawPoly(g, x1, y1, 4);
                    Color brown = new Color(82,42,62);
                    g.setColor(brown);
                    g.fillPolygon(x1, y1, 4);
                    DDA d = new DDA();
                    d.drawDDA(g, Ox-4*size, Oy-14*size, Ox+4*size, Oy-14*size);
                    d.drawDDA(g, Ox+4*size, Oy-14*size, Ox+4*size, Oy-8*size);
                    
                    d.drawDDA(g,Ox+2*size,Oy-8*size,Ox+2*size,Oy-5*size);
                    d.drawDDA(g, Ox-2*size, Oy-8*size, Ox-2*size, Oy-5*size);
                    d.drawDDA(g, Ox-2*size, Oy-8*size, Ox+2*size, Oy-8*size);
                    d.drawDDA(g, Ox-2*size, Oy-5*size, Ox+2*size, Oy-5*size);
                    int x2[]={Ox+2*size,Ox-2*size,Ox-2*size,Ox+2*size};
                    int y2[]={Oy-8*size,Oy-8*size,Oy-5*size,Oy-5*size};
                    g.setColor(brown);
                    g.fillPolygon(x2, y2, 4);
                    
                    d.drawDDA(g,Ox-5*size,Oy-5*size,Ox+5*size,Oy-5*size);
                    int x3[]={Ox+5*size,Ox-5*size,Ox-5*size,Ox+5*size};
                    int y3[]={Oy-5*size,Oy-5*size,Oy+7*size,Oy+7*size};
                    Polygon p3 = new Polygon();
                    p3.drawPoly(g, x3, y3, 4);
                    g.setColor(brown);
                    g.fillPolygon(x3, y3, 4);
                    
                    MPcircle c1 = new MPcircle();
                    g.setColor(Color.green);
                    c1.drawMPcircle(g, Ox, Oy, -2*size, 12*size, (int)(0.6*size));
                    
                    int x4[]={Ox-(int)(4.3*size),Ox-(int)(4.8*size),Ox-(int)(4.3*size)};
                    int y4[]={Oy-11*size,Oy-(int)(10.7*size),Oy-(int)(10.4*size)};
                    Polygon p4 = new Polygon();
                    p4.drawPoly(g, x4, y4, 3);
                    
                    g.fillRect(Ox-4*size, Oy-10*size, 3*size, (int)(0.5*size));
                    
                    int x5[]={Ox+2*size,Ox+2*size,Ox+8*size,Ox+9*size};
                    int y5[]={Oy-3*size,Oy+1*size,Oy+7*size,Oy+5*size};
                    d.drawDDA(g,Ox+9*size,Oy+5*size,Ox+2*size,Oy-3*size);
                    d.drawDDA(g,Ox+8*size,Oy+7*size,Ox+2*size,Oy+1*size);
                    d.drawDDA(g,Ox+9*size,Oy+5*size,Ox+8*size,Oy+7*size);
                    d.drawDDA(g,Ox+2*size,Oy-3*size,Ox+2*size,Oy+1*size);
                    g.setColor(brown);
                    g.fillPolygon(x5, y5, 4);
                    
                    int x6[]={Ox-(int)(5.2*size),Ox-(int)(5.2*size),Ox-10*size,Ox-10*size};
                    int y6[]={Oy,Oy+(int)(3.3*size),Oy+7*size,Oy+4*size};
                    d.drawDDA(g,Ox-(int)(5.2*size),Oy,Ox-10*size,Oy+4*size);
                    d.drawDDA(g,Ox-10*size,Oy+4*size,Ox-10*size,Oy+7*size);
                    d.drawDDA(g,Ox-10*size,Oy+7*size,Ox-(int)(5.2*size),Oy+3*size);
                    d.drawDDA(g,Ox-(int)(5.2*size),Oy,Ox-(int)(5.2*size),Oy+(int)(3.3*size));
                    g.setColor(brown);
                    g.fillPolygon(x6, y6, 4);
                    
                    int x7[]={Ox+(int)(0.2*size),Ox+3*size,Ox+5*size,Ox+2*size};
                    int y7[]={Oy+(int)(7.5*size),Oy+(int)(7.5*size),Oy+18*size,Oy+18*size};
                    d.drawDDA(g,Ox+(int)(0.2*size),Oy+(int)(7.5*size),Ox+3*size,Oy+(int)(7.5*size));
                    d.drawDDA(g,Ox+(int)(0.2*size),Oy+(int)(7.5*size),Ox+2*size,Oy+18*size);
                    d.drawDDA(g,Ox+2*size,Oy+18*size,Ox+5*size,Oy+18*size);
                    d.drawDDA(g,Ox+5*size,Oy+18*size,Ox+3*size,Oy+(int)(7.5*size));
                    g.setColor(brown);
                    g.fillPolygon(x7, y7, 4);
                    
                    int x8[]={Ox-(int)(0.2*size),Ox-3*size,Ox-5*size,Ox-2*size};
                    int y8[]={Oy+(int)(7.5*size),Oy+(int)(7.5*size),Oy+18*size,Oy+18*size};
                    d.drawDDA(g,Ox-3*size,Oy+(int)(7.5*size),Ox-(int)(0.2*size),Oy+(int)(7.5*size));
                    d.drawDDA(g,Ox-(int)(0.2*size),Oy+(int)(7.5*size),Ox-2*size,Oy+18*size);
                    d.drawDDA(g,Ox-5*size,Oy+18*size,Ox-2*size,Oy+18*size);
                    d.drawDDA(g,Ox-5*size,Oy+18*size,Ox-3*size,Oy+(int)(7.5*size));
                    g.setColor(brown);
                    g.fillPolygon(x8, y8, 4);
                    
                }
            }
            
            public class make_robot2{
                
                public void drawRobot2(Graphics g,int Ox,int Oy)
                {
                    int size=6;
                    int x1[]={Ox-4*size,Ox+4*size,Ox+4*size,Ox-4*size};
                    int y1[]={Oy-8*size,Oy-8*size,Oy-14*size,Oy-14*size};
                    Polygon p1 = new Polygon();
                    p1.drawPoly(g, x1, y1, 4);
                    Color brown = new Color(82,42,62);
                    g.setColor(brown);
                    g.fillPolygon(x1, y1, 4);
                    DDA d = new DDA();
                    d.drawDDA(g, Ox-4*size, Oy-14*size, Ox+4*size, Oy-14*size);
                    d.drawDDA(g, Ox+4*size, Oy-14*size, Ox+4*size, Oy-8*size);
                    
                    d.drawDDA(g,Ox+2*size,Oy-8*size,Ox+2*size,Oy-5*size);
                    d.drawDDA(g, Ox-2*size, Oy-8*size, Ox-2*size, Oy-5*size);
                    d.drawDDA(g, Ox-2*size, Oy-8*size, Ox+2*size, Oy-8*size);
                    d.drawDDA(g, Ox-2*size, Oy-5*size, Ox+2*size, Oy-5*size);
                    int x2[]={Ox+2*size,Ox-2*size,Ox-2*size,Ox+2*size};
                    int y2[]={Oy-8*size,Oy-8*size,Oy-5*size,Oy-5*size};
                    g.setColor(brown);
                    g.fillPolygon(x2, y2, 4);
                    
                    d.drawDDA(g,Ox-5*size,Oy-5*size,Ox+5*size,Oy-5*size);
                    int x3[]={Ox+5*size,Ox-5*size,Ox-5*size,Ox+5*size};
                    int y3[]={Oy-5*size,Oy-5*size,Oy+7*size,Oy+7*size};
                    Polygon p3 = new Polygon();
                    p3.drawPoly(g, x3, y3, 4);
                    g.setColor(brown);
                    g.fillPolygon(x3, y3, 4);
                    
                    MPcircle c1 = new MPcircle();
                    g.setColor(Color.green);
                    c1.drawMPcircle(g, Ox, Oy, -2*size, 12*size, (int)(0.6*size));
                    
                    int x4[]={Ox-(int)(4.3*size),Ox-(int)(4.8*size),Ox-(int)(4.3*size)};
                    int y4[]={Oy-11*size,Oy-(int)(10.7*size),Oy-(int)(10.4*size)};
                    Polygon p4 = new Polygon();
                    p4.drawPoly(g, x4, y4, 3);
                    
                    g.fillRect(Ox-4*size, Oy-10*size, 3*size, (int)(0.5*size));
                    
                    int x5[]={Ox+1*size,Ox+1*size,Ox-9*size,Ox-9*size};
                    int y5[]={Oy-3*size,Oy,Oy+4*size,Oy+2*size};
                    d.drawDDA(g,Ox-9*size,Oy+2*size,Ox+1*size,Oy-3*size);
                    d.drawDDA(g,Ox-9*size,Oy+4*size,Ox+1*size,Oy);
                    d.drawDDA(g,Ox-9*size,Oy+2*size,Ox-9*size,Oy+4*size);
                    d.drawDDA(g,Ox+1*size,Oy-3*size,Ox+1*size,Oy);
                    g.setColor(brown);
                    g.fillPolygon(x5, y5, 4);
                    
                    int x6[]={Ox+(int)(5.1*size),Ox+(int)(5.1*size),Ox+8*size,Ox+8*size};
                    int y6[]={Oy+1*size,Oy+4*size,Oy+6*size,Oy+4*size};
                    d.drawDDA(g,Ox+(int)(5.1*size),Oy+1*size,Ox+(int)(5.1*size),Oy+4*size);
                    d.drawDDA(g,Ox+(int)(5.1*size),Oy+1*size,Ox+8*size,Oy+4*size);
                    d.drawDDA(g,Ox+8*size,Oy+4*size,Ox+8*size,Oy+6*size);
                    d.drawDDA(g,Ox+(int)(5.1*size),Oy+4*size,Ox+8*size,Oy+6*size);
                    g.setColor(brown);
                    g.fillPolygon(x6, y6, 4);
                    
                    int x7[]={Ox+(int)(0.2*size),Ox+3*size,Ox+7*size,Ox+4*size};
                    int y7[]={Oy+(int)(7.5*size),Oy+(int)(7.5*size),Oy+18*size,Oy+18*size};
                    d.drawDDA(g,Ox+(int)(0.2*size),Oy+(int)(7.5*size),Ox+3*size,Oy+(int)(7.5*size));
                    d.drawDDA(g,Ox+(int)(0.2*size),Oy+(int)(7.5*size),Ox+4*size,Oy+18*size);
                    d.drawDDA(g,Ox+4*size,Oy+18*size,Ox+7*size,Oy+18*size);
                    d.drawDDA(g,Ox+7*size,Oy+18*size,Ox+3*size,Oy+(int)(7.5*size));
                    g.setColor(brown);
                    g.fillPolygon(x7, y7, 4);
                    
                    int x8[]={Ox-(int)(0.2*size),Ox-3*size,Ox-7*size,Ox-4*size};
                    int y8[]={Oy+(int)(7.5*size),Oy+(int)(7.5*size),Oy+18*size,Oy+18*size};
                    d.drawDDA(g,Ox-3*size,Oy+(int)(7.5*size),Ox-(int)(0.2*size),Oy+(int)(7.5*size));
                    d.drawDDA(g,Ox-(int)(0.2*size),Oy+(int)(7.5*size),Ox-4*size,Oy+18*size);
                    d.drawDDA(g,Ox-7*size,Oy+18*size,Ox-4*size,Oy+18*size);
                    d.drawDDA(g,Ox-7*size,Oy+18*size,Ox-3*size,Oy+(int)(7.5*size));
                    g.setColor(brown);
                    g.fillPolygon(x8, y8, 4);
                    
                }
            }
            
            public class Horse
            {
                public void drawHorse1(Graphics g,int Ox,int Oy)
                {                    
                int size=4;
                int x1[]={Ox-3*size-size,Ox+6*size+size,Ox+8*size+size,Ox+6*size+size,Ox-7*size-size};
                int y1[]={Oy+3*size+size,Oy+6*size+size,Oy+(int)(5.5*size)+size,Oy-6*size-size,Oy-(int)(7.5*size)-size};
                Polygon p1 = new Polygon();
                p1.drawPoly(g, x1, y1, 5);
                //Color brown = new Color(82,42,62);
                Color brown = new Color(50,100,255);
                g.setColor(brown);
                g.fillPolygon(x1, y1, 5);
                
                int x2[]={Ox+9*size+size,Ox+7*size+size,Ox+10*size+size,Ox+17*size+size,Ox+25*size+size,Ox+23*size+size,Ox+12*size+size};
                int y2[]={Oy+7*size-size,Oy-6*size-size,Oy-9*size-size,Oy-(int)(13.5*size)-size,Oy-13*size-size,Oy-(int)(12.5*size)-size,Oy+(int)(3.5*size)+size};
                Polygon p2 = new Polygon();
                p2.drawPoly(g, x2, y2, 7);
                g.setColor(brown);
                g.fillPolygon(x2, y2, 7);
                
                int x3[]={Ox+23*size+size,Ox+26*size+size,Ox+(int)(27.5*size)+size,Ox+26*size+size,Ox+24*size+size,Ox+21*size+size};
                int y3[]={Oy-11*size-size,Oy-12*size-size,Oy-7*size-size,Oy+3*size+size,Oy+(int)(3.3*size)+size,Oy-(int)(7.5*size)-size};
                Polygon p3 = new Polygon();
                p3.drawPoly(g, x3, y3, 6);
                g.setColor(brown);
                g.fillPolygon(x3, y3, 6);
                
                int x4[]={Ox+9*size+size,Ox+12*size+size,Ox+24*size+size,Ox+24*size+size,Ox+10*size+size};
                int y4[]={Oy+(int)(6.3*size)+size,Oy+(int)(4.8*size)+size,Oy+8*size+size,Oy+10*size+size,Oy+8*size+size};
                Polygon p4 = new Polygon();
                p4.drawPoly(g, x4, y4, 5);
                g.setColor(brown);
                g.fillPolygon(x4, y4, 5);
                
                int x5[]={Ox+27*size+size,Ox+(int)(27.2*size)+size,Ox+37*size+size};
                int y5[]={Oy-12*size-size,Oy-11*size-size,Oy-17*size-size};
                Polygon p5 = new Polygon();
                p5.drawPoly(g, x5, y5, 3);
                g.setColor(Color.MAGENTA);
                g.fillPolygon(x5, y5, 3);
                                
                int x6[]={Ox+24*size+size,Ox+(int)(22.5*size)+size,Ox+(int)(20.5*size)+size,Ox+(int)(22.5*size)+size};
                int y6[]={Oy+11*size+size,Oy+(int)(10.5*size)+size,Oy+16*size+size,Oy+(int)(16.5*size)+size};
                Polygon p6 = new Polygon();
                p6.drawPoly(g, x6, y6, 4);
                g.setColor(brown);
                g.fillPolygon(x6, y6, 4);
                
                int x7[]={Ox+8*size+size,Ox+9*size+size,Ox+6*size+size,Ox+7*size+size,Ox+4*size+size,Ox+6*size+size};
                int y7[]={Oy+(int)(6.5*size)+size,Oy+(int)(8.5*size)+size,Oy+18*size+size,Oy+22*size+size,Oy+21*size+size,Oy+(int)(8.5*size)+size};
                Polygon p7 = new Polygon();
                p7.drawPoly(g, x7, y7, 6);
                g.setColor(brown);
                g.fillPolygon(x7, y7, 6);
                
                int x8[]={Ox+4*size+size,Ox+7*size+size,Ox+6*size+size,Ox+3*size+size};
                int y8[]={Oy+22*size+size,Oy+23*size+size,Oy+24*size+size,Oy+25*size+size};
                Polygon p8 = new Polygon();
                p8.drawPoly(g, x8, y8, 4);
                g.setColor(brown);
                g.fillPolygon(x8, y8, 4);
                
                int x9[]={Ox+22*size+size,Ox+20*size+size,Ox+21*size+size};
                int y9[]={Oy+(int)(17.5*size)+size,Oy+17*size+size,Oy+20*size+size};
                Polygon p9 = new Polygon();
                p9.drawPoly(g, x9, y9, 3);
                g.setColor(brown);
                g.fillPolygon(x9, y9, 3);
                
                int x10[]={Ox-(int)(4*size)-size,Ox-8*size-size,Ox-14*size-size,Ox-16*size-size,Ox-(int)(17.5*size)-size,Ox-6*size-size};
                int y10[]={Oy+3*size+size,Oy-(int)(7.5*size)-size,Oy-6*size-size,Oy-4*size-size,Oy+5*size+size,Oy+5*size+size};
                Polygon p10 = new Polygon();
                p10.drawPoly(g, x10, y10, 6);
                g.setColor(brown);
                g.fillPolygon(x10, y10, 6);
                
                int x11[]={Ox-17*size-size,Ox-6*size-size,Ox-11*size-size};
                int y11[]={Oy+6*size+size,Oy+6*size+size,Oy+13*size+size};
                Polygon p11 = new Polygon();
                p11.drawPoly(g, x11, y11, 3);
                g.setColor(brown);
                g.fillPolygon(x11, y11, 3);
                
                int x12[]={Ox-14*size-size,Ox-11*size-size,Ox-9*size-size,Ox-6*size-size,Ox-14*size-size};
                int y12[]={Oy+11*size+size,Oy+15*size+size,Oy+12*size+size,Oy+22*size+size,Oy+(int)(13.5*size)+size};
                Polygon p12 = new Polygon();
                p12.drawPoly(g, x12, y12, 5);
                g.setColor(brown);
                g.fillPolygon(x12, y12, 5);
                
                int x13[]={Ox-8*size-size,Ox-(int)(5.5*size)-size,Ox-(int)(4.5*size)-size,Ox-(int)(7.5*size)-size};
                int y13[]={Oy+21*size+size,Oy+(int)(23.5*size)+size,Oy+26*size+size,Oy+25*size+size};
                Polygon p13 = new Polygon();
                p13.drawPoly(g, x13, y13, 4);
                g.setColor(brown);
                g.fillPolygon(x13, y13, 4);
                
                int x14[]={Ox-17*size-size,Ox-15*size-size,Ox-20*size-size,Ox-22*size-size};
                int y14[]={Oy+8*size+size,Oy+10*size+size,Oy+20*size+size,Oy+16*size+size};
                Polygon p14 = new Polygon();
                p14.drawPoly(g, x14, y14, 4);
                g.setColor(brown);
                g.fillPolygon(x14, y14, 4);
                
                int x15[]={Ox-(int)(19.5*size)-size,Ox-18*size-size,Ox-18*size-size,Ox-20*size-size};
                int y15[]={Oy+(int)(21.5*size)+size,Oy+18*size+size,Oy+26*size+size,Oy+25*size+size};
                Polygon p15 = new Polygon();
                p15.drawPoly(g, x15, y15, 4);
                g.setColor(brown);
                g.fillPolygon(x15, y15, 4);
                
                int x16[]={Ox-17*size-size,Ox-(int)(17.5*size)-size,Ox-19*size-size,Ox-20*size-size};
                int y16[]={Oy-3*size-size,Oy,Oy-1*size-size,Oy-4*size-size};
                Polygon p16 = new Polygon();
                p16.drawPoly(g, x16, y16, 4);
                g.setColor(brown);
                g.fillPolygon(x16, y16, 4);
                
                int x17[]={Ox-20*size-size,Ox-21*size-size,Ox-30*size-size,Ox-31*size-size,Ox-31*size-size};
                int y17[]={Oy-1*size-size,Oy-4*size-size,Oy-2*size-size,Oy,Oy+1*size+size};
                Polygon p17 = new Polygon();
                p17.drawPoly(g, x17, y17, 5);
                g.setColor(brown);
                g.fillPolygon(x17, y17, 5);
                
                int x18[]={Ox-31*size-size,Ox-28*size-size,Ox-29*size-size,Ox-33*size-size};
                int y18[]={Oy+2*size+size,Oy+(int)(1.5*size)+size,Oy+5*size+size,Oy+9*size+size};
                Polygon p18 = new Polygon();
                p18.drawPoly(g, x18, y18, 4);
                g.setColor(brown);
                g.fillPolygon(x18, y18, 4);
                
                int x19[]={Ox+28*size+size,Ox+(int)(28.5*size)+size,Ox+30*size+size};
                int y19[]={Oy-(int)(9.5*size)-size,Oy-8*size-size,Oy-10*size-size};
                Polygon p19 = new Polygon();
                p19.drawPoly(g, x19, y19, 3);
                g.setColor(brown);
                g.fillPolygon(x19, y19, 3);
                
                int x20[]={Ox+28*size+size,Ox+28*size+size,Ox+30*size+size};
                int y20[]={Oy-6*size-size,Oy-4*size-size,Oy-4*size-size};
                Polygon p20 = new Polygon();
                p20.drawPoly(g, x20, y20, 3);
                g.setColor(brown);
                g.fillPolygon(x20, y20, 3);
                }
                
                public void drawHorse2(Graphics g,int Ox,int Oy)
                {
                int size=4;
                int x1[]={Ox-3*size-size,Ox+6*size+size,Ox+8*size+size,Ox+6*size+size,Ox-7*size-size};
                int y1[]={Oy+3*size+size,Oy+6*size+size,Oy+(int)(5.5*size)+size,Oy-6*size-size,Oy-(int)(7.5*size)-size};
                Polygon p1 = new Polygon();
                p1.drawPoly(g, x1, y1, 5);
                //Color brown = new Color(82,42,62);
                Color brown = new Color(50,100,255);
                g.setColor(brown);
                g.fillPolygon(x1, y1, 5);
                
                int x2[]={Ox+9*size+size,Ox+7*size+size,Ox+10*size+size,Ox+17*size+size,Ox+25*size+size,Ox+23*size+size,Ox+12*size+size};
                int y2[]={Oy+7*size-size,Oy-6*size-size,Oy-9*size-size,Oy-(int)(13.5*size)-size,Oy-13*size-size,Oy-(int)(12.5*size)-size,Oy+(int)(3.5*size)+size};
                Polygon p2 = new Polygon();
                p2.drawPoly(g, x2, y2, 7);
                g.setColor(brown);
                g.fillPolygon(x2, y2, 7);
                
                int x3[]={Ox+23*size+size,Ox+26*size+size,Ox+(int)(27.5*size)+size,Ox+26*size+size,Ox+24*size+size,Ox+21*size+size};
                int y3[]={Oy-11*size-size,Oy-12*size-size,Oy-7*size-size,Oy+3*size+size,Oy+(int)(3.3*size)+size,Oy-(int)(7.5*size)-size};
                Polygon p3 = new Polygon();
                p3.drawPoly(g, x3, y3, 6);
                g.setColor(brown);
                g.fillPolygon(x3, y3, 6);
                
                int x4[]={Ox+9*size+size,Ox+12*size+size,Ox+18*size+size,Ox+16*size+size,Ox+14*size+size};
                int y4[]={Oy+(int)(6.3*size)+size,Oy+(int)(4.8*size)+size,Oy+26*size+size,Oy+24*size+size,Oy+22*size+size};
                Polygon p4 = new Polygon();
                p4.drawPoly(g, x4, y4, 5);
                g.setColor(brown);
                g.fillPolygon(x4, y4, 5);
                
                int x5[]={Ox+27*size+size,Ox+(int)(27.2*size)+size,Ox+37*size+size};
                int y5[]={Oy-12*size-size,Oy-11*size-size,Oy-17*size-size};
                Polygon p5 = new Polygon();
                p5.drawPoly(g, x5, y5, 3);
                g.setColor(Color.MAGENTA);
                g.fillPolygon(x5, y5, 3);
                                
                int x6[]={Ox+24*size+size,Ox+(int)(22.5*size)+size,Ox+(int)(20.5*size)+size,Ox+(int)(22.5*size)+size};
                int y6[]={Oy+11*size+size,Oy+(int)(10.5*size)+size,Oy+16*size+size,Oy+(int)(16.5*size)+size};
                Polygon p6 = new Polygon();
                //p6.drawPoly(g, x6, y6, 4);
                g.setColor(brown);
                //g.fillPolygon(x6, y6, 4);
                
                int x7[]={Ox+8*size+size,Ox+9*size+size,Ox+6*size+size,Ox+7*size+size,Ox+4*size+size,Ox+6*size+size};
                int y7[]={Oy+(int)(6.5*size)+size,Oy+(int)(8.5*size)+size,Oy+18*size+size,Oy+22*size+size,Oy+21*size+size,Oy+(int)(8.5*size)+size};
                Polygon p7 = new Polygon();
                p7.drawPoly(g, x7, y7, 6);
                g.setColor(brown);
                g.fillPolygon(x7, y7, 6);
                
                int x8[]={Ox+4*size+size,Ox+7*size+size,Ox+6*size+size,Ox+3*size+size};
                int y8[]={Oy+22*size+size,Oy+23*size+size,Oy+24*size+size,Oy+25*size+size};
                Polygon p8 = new Polygon();
                p8.drawPoly(g, x8, y8, 4);
                g.setColor(brown);
                g.fillPolygon(x8, y8, 4);
                
                int x9[]={Ox+22*size+size,Ox+20*size+size,Ox+21*size+size};
                int y9[]={Oy+(int)(17.5*size)+size,Oy+17*size+size,Oy+20*size+size};
                Polygon p9 = new Polygon();
                //p9.drawPoly(g, x9, y9, 3);
                g.setColor(brown);
                //g.fillPolygon(x9, y9, 3);
                
                int x10[]={Ox-(int)(4*size)-size,Ox-8*size-size,Ox-14*size-size,Ox-16*size-size,Ox-(int)(17.5*size)-size,Ox-6*size-size};
                int y10[]={Oy+3*size+size,Oy-(int)(7.5*size)-size,Oy-6*size-size,Oy-4*size-size,Oy+5*size+size,Oy+5*size+size};
                Polygon p10 = new Polygon();
                p10.drawPoly(g, x10, y10, 6);
                g.setColor(brown);
                g.fillPolygon(x10, y10, 6);
                
                int x11[]={Ox-17*size-size,Ox-6*size-size,Ox-11*size-size};
                int y11[]={Oy+6*size+size,Oy+6*size+size,Oy+13*size+size};
                Polygon p11 = new Polygon();
                p11.drawPoly(g, x11, y11, 3);
                g.setColor(brown);
                g.fillPolygon(x11, y11, 3);
                
                int x12[]={Ox-14*size-size,Ox-11*size-size,Ox-9*size-size,Ox-6*size-size,Ox-14*size-size};
                int y12[]={Oy+11*size+size,Oy+15*size+size,Oy+12*size+size,Oy+22*size+size,Oy+(int)(13.5*size)+size};
                Polygon p12 = new Polygon();
                p12.drawPoly(g, x12, y12, 5);
                g.setColor(brown);
                g.fillPolygon(x12, y12, 5);
                
                int x13[]={Ox-8*size-size,Ox-(int)(5.5*size)-size,Ox-(int)(4.5*size)-size,Ox-(int)(7.5*size)-size};
                int y13[]={Oy+21*size+size,Oy+(int)(23.5*size)+size,Oy+26*size+size,Oy+25*size+size};
                Polygon p13 = new Polygon();
                p13.drawPoly(g, x13, y13, 4);
                g.setColor(brown);
                g.fillPolygon(x13, y13, 4);
                
                int x14[]={Ox-17*size-size,Ox-15*size-size,Ox-20*size-size,Ox-22*size-size};
                int y14[]={Oy+8*size+size,Oy+10*size+size,Oy+20*size+size,Oy+16*size+size};
                Polygon p14 = new Polygon();
                p14.drawPoly(g, x14, y14, 4);
                g.setColor(brown);
                g.fillPolygon(x14, y14, 4);
                
                int x15[]={Ox-(int)(19.5*size)-size,Ox-18*size-size,Ox-18*size-size,Ox-20*size-size};
                int y15[]={Oy+(int)(21.5*size)+size,Oy+18*size+size,Oy+26*size+size,Oy+25*size+size};
                Polygon p15 = new Polygon();
                p15.drawPoly(g, x15, y15, 4);
                g.setColor(brown);
                g.fillPolygon(x15, y15, 4);
                
                int x16[]={Ox-17*size-size,Ox-(int)(17.5*size)-size,Ox-19*size-size,Ox-20*size-size};
                int y16[]={Oy-3*size-size,Oy,Oy-1*size-size,Oy-4*size-size};
                Polygon p16 = new Polygon();
                p16.drawPoly(g, x16, y16, 4);
                g.setColor(brown);
                g.fillPolygon(x16, y16, 4);
                
                int x17[]={Ox-20*size-size,Ox-21*size-size,Ox-30*size-size,Ox-31*size-size,Ox-31*size-size};
                int y17[]={Oy-1*size-size,Oy-4*size-size,Oy-2*size-size,Oy,Oy+1*size+size};
                Polygon p17 = new Polygon();
                p17.drawPoly(g, x17, y17, 5);
                g.setColor(brown);
                g.fillPolygon(x17, y17, 5);
                
                int x18[]={Ox-31*size-size,Ox-28*size-size,Ox-29*size-size,Ox-33*size-size};
                int y18[]={Oy+2*size+size,Oy+(int)(1.5*size)+size,Oy+5*size+size,Oy+9*size+size};
                Polygon p18 = new Polygon();
                p18.drawPoly(g, x18, y18, 4);
                g.setColor(brown);
                g.fillPolygon(x18, y18, 4);
                
                int x19[]={Ox+28*size+size,Ox+(int)(28.5*size)+size,Ox+30*size+size};
                int y19[]={Oy-(int)(9.5*size)-size,Oy-8*size-size,Oy-10*size-size};
                Polygon p19 = new Polygon();
                p19.drawPoly(g, x19, y19, 3);
                g.setColor(brown);
                g.fillPolygon(x19, y19, 3);
                
                int x20[]={Ox+28*size+size,Ox+28*size+size,Ox+30*size+size};
                int y20[]={Oy-6*size-size,Oy-4*size-size,Oy-4*size-size};
                Polygon p20 = new Polygon();
                p20.drawPoly(g, x20, y20, 3);
                g.setColor(brown);
                g.fillPolygon(x20, y20, 3);
                }
            }
    
            public class PLANT{
                
                public void drawPLANT(Graphics g,int Ox,int Oy)
                {
                    int width=2, height=2;
                    MPcircle c = new MPcircle();
                    c.drawMPcircle(g, Ox, Oy, 0, 0, 5*width);
                    MPellipse e = new MPellipse();
                    e.drawMPellipse(g, Ox, Oy, 0, 15*width, 4*width, 10*width);
                    e.drawMPellipse(g, Ox, Oy, 0, -15*width, 4*width, 10*width);
                    e.drawMPellipse(g, Ox, Oy, 15*width, 0, 10*width, 4*width);
                    e.drawMPellipse(g, Ox, Oy, -15*width, 0, 10*width, 4*width);
                    e.drawMPellipse(g, Ox, Oy, 0, -35*height , 0, 10*height);
                    e.drawMPellipse(g, Ox, Oy, 0, -45*height , 10*width, 1);
                    e.drawMPellipse(g, Ox, Oy, 0, -55*height , 10*width, 1);
                    e.drawMPellipse(g, Ox, Oy, 9*width, -50*height , 1, 5*height);
                    e.drawMPellipse(g, Ox, Oy, -9*width, -50*height , 1, 5*height);
                
                    rotatedMPellipse re = new rotatedMPellipse();
                    re.drawrotatedMPellipse(g, Ox, Oy, 10*width, 15*width, 7*width, 40*width, 0.163);
                    re.drawrotatedMPellipse(g, Ox, Oy, 3*width, -16*width, 14*width, 10*width, 1.163);
                    re.drawrotatedMPellipse(g, Ox, Oy, -5*width, 20*width, 8*width, 13*width, 0.981);
                    re.drawrotatedMPellipse(g, Ox, Oy, -10*width, -15*width, 7*width, 40*width, 0.163);
                
                    DDA1 d = new DDA1();
                    d.drawDDA1(g, Ox, Oy, 0, -38*height, 20*width, -25*height);
                    d.drawDDA1(g, Ox, Oy, 0, -38*height, -20*width, -25*height);
                
                    d.drawDDA1(g, Ox, Oy, 17*width, -20*height, 23*width, -30*height);
                    d.drawDDA1(g, Ox, Oy, -17*width, -20*height, -23*width, -30*height);
                
                    d.drawDDA1(g, Ox, Oy, 17*width, -20*height, 26*width, -23*height);
                    d.drawDDA1(g, Ox, Oy, 26*width, -23*height, 23*width, -30*height);
                
                    d.drawDDA1(g, Ox, Oy, -17*width, -20*height, -26*width, -23*height);
                    d.drawDDA1(g, Ox, Oy, -26*width, -23*height, -23*width, -30*height);
                }
            }
            
            public class TITLI{
                
                public void drawTITLI(Graphics g,int Ox,int Oy)
                {
                    int width=2, height=2;
                    
                    MPcircle c = new MPcircle();
                    c.drawMPcircle(g, Ox, Oy, 0, 3*width, 3*width);
                    c.drawMPcircle(g, Ox, Oy, 0, 9*width, 3*width);
                    c.drawMPcircle(g, Ox, Oy, 0, 15*width, 3*width);
                    c.drawMPcircle(g, Ox, Oy, 0, -3*width, 3*width);
                    c.drawMPcircle(g, Ox, Oy, 0, -9*width, 3*width);
                    c.drawMPcircle(g, Ox, Oy, 0, -15*width, 3*width);
                
                    MPellipse e = new MPellipse();
                
                    DDA1 d = new DDA1();
                    d.drawDDA1(g, Ox, Oy, -5*width, 18*width, 5*width, 18*width);
                    d.drawDDA1(g, Ox, Oy, -5*width, 26*width, 5*width, 26*width);
                    e.drawMPellipse(g, Ox, Oy, -5*width, 22*height,1, 4*width);
                    e.drawMPellipse(g, Ox, Oy, 5*width, 22*height,1, 4*width);
                    d.drawDDA1(g, Ox, Oy, -4*width, 26*height, -7*width, 32*height);
                    d.drawDDA1(g, Ox, Oy, 4*width, 26*height, 7*width, 32*height);
                    c.drawMPcircle(g, Ox, Oy, -7*width, 33*height, 1*width);
                    c.drawMPcircle(g, Ox, Oy, 7*width, 33*height, 1*width);
                
                    rotatedMPellipse re = new rotatedMPellipse();
                    re.drawrotatedMPellipse(g, Ox, Oy, 16*width, 14*height, 15*width, 25*height, 0.343);
                    re.drawrotatedMPellipse(g, Ox, Oy, -1*width, 26*height, 16*width, 20*height, 1.025);
                    re.drawrotatedMPellipse(g, Ox, Oy, -20*width, -16*height, 39*width, 15*height, 1.343);
                    re.drawrotatedMPellipse(g, Ox, Oy, -16*width, -10*height, 13*width, 30*height, 0.343);
                }
            }
            
            
            public class MPcircle{
                
                public void drawMPcircle(Graphics g,int Ox,int Oy,int x0,int y0,int r)
                {
                    int xx=Ox+x0,yy = Oy + (-1)*y0;
                    int x=r,y=0,err=0;
                    while(x>=y)
                    {
                        g.fillRect((xx+x),(yy + (-1)*y),2,2);
                        g.fillRect((xx+y),(yy + (-1)*x),2,2);
                        g.fillRect((xx-y),(yy + (-1)*x),2,2);
                        g.fillRect((xx-x),(yy + (-1)*y),2,2);
                        g.fillRect((xx-x),(yy - (-1)*y),2,2);
                        g.fillRect((xx-y),(yy - (-1)*x),2,2);
                        g.fillRect((xx+y),(yy - (-1)*x),2,2);
                        g.fillRect((xx+x),(yy - (-1)*y),2,2);
                                                
                        if(err<=0)
                        {
                            y += 1;
                            err += 2*y + 1;
                        }
                        else
                        {
                            x -= 1;
                            err -= 2*x + 1;
                        }
                    }
                }
            }
            
            public class MPellipse{
                
                public void drawMPellipse(Graphics g,int Ox,int Oy,int x0,int y0,int rx,int ry)
                {
                    int xx=Ox+x0,yy = Oy + (-1)*y0;
                    int x=0,y=ry;
                    int p = (ry*ry) - (rx*rx*ry) + ((rx*rx)/4);
                    while((2*x*ry*ry)<(2*y*rx*rx))
                    {
                        g.fillRect((xx+x),(yy - (-1)*y),2,2);
                        g.fillRect((xx-x),(yy + (-1)*y),2,2);
                        g.fillRect((xx+x),(yy + (-1)*y),2,2);
                        g.fillRect((xx-x),(yy - (-1)*y),2,2);
                                                
                        if(p<0)
                        {
                            x += 1;
                            p = p + (2*ry*ry*x) + (ry*ry);
                        }
                        else
                        {
                            x += 1;
                            y -= 1;
                            p = p +(2*ry*ry*x + ry*ry) - (2*rx*rx*y);
                        }
                    }
                    
                    p = ((int)((float)x+0.5))*((int)((float)x+0.5)*ry*ry) + (y-1)*(y-1)*rx*rx - rx*rx*ry*ry;
                    while( y >= 0)
                    {
                        g.fillRect((xx+x),(yy - (-1)*y),2,2);
                        g.fillRect((xx-x),(yy + (-1)*y),2,2);
                        g.fillRect((xx+x),(yy + (-1)*y),2,2);
                        g.fillRect((xx-x),(yy - (-1)*y),2,2);
                                                
                        if(p>0)
                        {
                            y -= 1;
                            p = p - (2*rx*rx*y) + (rx*rx);
                        }
                        else
                        {
                            y -= 1;
                            x += 1;
                            p = p + (2*ry*ry*x) - (2*rx*rx*y) - (rx*rx);
                        }
                    }
                }
            }
            
            public class DDA1{
                
                public void drawDDA1(Graphics g,int Ox,int Oy,int x0,int y0,int x1,int y1)
                {
                    x0=Ox+x0;
                    y0=Oy + (-1)*y0;
                    x1=Ox+x1;
                    y1=Oy + (-1)*y1;
                    double dx = x1 - x0;
                    double dy = y1 - y0;
                    double steps,Xincrement,Yincrement;
                
                    if (dx > dy)
                        steps = Math.abs(dx);
                    else
                        steps = Math.abs(dy);
                    
                    if( steps != 0)
                    {
                        Xincrement = (dx) / steps;
                        Yincrement = (dy) / steps;
                    }
                    else
                    {
                        Xincrement = dx;
                        Yincrement = dy;
                    }
                    double X = x0;
                    double Y = y0;
                    g.fillRect((int)X,(int)Y,3,3);
                    for(int i=0 ; i <= steps ; i++)
                    {
                        g.fillRect((int)X,(int)Y,3,3);
                        X = X + Xincrement;
                        Y = Y + Yincrement;
                    }
                }
            }
            
            public class rotatedMPellipse{
                
                public void drawrotatedMPellipse(Graphics g,int Ox,int Oy,int x0,int y0,int rx,int ry,double theta)
                {
                    double xx=x0,yy =y0;
                    double x=0,y=ry;
                    double p = (ry*ry) - (rx*rx*ry) + ((rx*rx)/4);
                    while((2*x*ry*ry)<(2*y*rx*rx))
                    {
                        createPixel(g,xx+x, yy - (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx-x, yy + (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx+x, yy + (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx-x, yy - (-1)*y, theta,Ox,Oy);
                        
                        if(p<0)
                        {
                            x += 1;
                            p = p + (2*ry*ry*x) + (ry*ry);
                        }
                        else
                        {
                            x += 1;
                            y -= 1;
                            p = p +(2*ry*ry*x + ry*ry) - (2*rx*rx*y);
                        }
                    }
                    
                    p = ((int)((float)x+0.5))*((int)((float)x+0.5)*ry*ry) + (y-1)*(y-1)*rx*rx - rx*rx*ry*ry;
                    while( y >= 0)
                    {
                        createPixel(g,xx+x, yy - (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx-x, yy + (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx+x, yy + (-1)*y, theta,Ox,Oy);
                        createPixel(g,xx-x, yy - (-1)*y, theta,Ox,Oy);
                        
                        
                        if(p>0)
                        {
                            y -= 1;
                            p = p - (2*rx*rx*y) + (rx*rx);
                        }
                        else
                        {
                            y -= 1;
                            x += 1;
                            p = p + (2*ry*ry*x) - (2*rx*rx*y) - (rx*rx);
                        }
                    }
                }
            }
            
            public void createPixel(Graphics g,double x, double y,double i,int Ox,int Oy)
                {
                    x =(x*Math.cos(i) + y*Math.sin(i));
                    y =(y*Math.sin(i) - x*Math.cos(i));
                    x = getCoordinate(x,0,Ox,Oy);
                    y = getCoordinate(y,1,Ox,Oy);
                    g.fillRect((int)x, (int)y, 2, 2);
                }
            
            public double getCoordinate(double x, int a, int Ox, int Oy)
            {
                if(a == 0)
                {
                    x = Ox + x;
                    return x;
                }
                else
                {
                    x = Oy + x;
                    return x;
                }
            }
}
