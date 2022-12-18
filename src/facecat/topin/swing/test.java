/*
* FaceCat图形通讯框架(非开源)
* 著作权编号:2015SR229355
* 上海卷卷猫信息技术有限公司
*/

package facecat.topin.swing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;   
import java.io.FileInputStream;  
import java.io.FileWriter;  
import java.nio.charset.Charset;  
import java.util.Properties;  
   
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;
import facecat.topin.log.*;
import facecat.topin.chart.*;
import facecat.topin.core.*;
import java.util.*;
import java.util.zip.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

/*
* 程序入口
*/
class sshToolInterface
{
	/*
        * Swing的Frame
        */
	private JFrame m_fra = new JFrame("FaceCat/Swing");
        
        /*
        * 视图
        */
        private FCUIView m_view = new FCUIView();
        
        /*
        * 创建Swing
        */
	public sshToolInterface() 
	{
            FCSize windowSize = new FCSize(1000, 800);
		//Frame
            this.m_fra.setSize(windowSize.cx, windowSize.cy);
            this.m_fra.setLocation(0,0);
            //this.m_fra.setLayout(null);
            //Label
            m_view.setBounds(0, 0, windowSize.cx, windowSize.cy - 20);
            m_view.onLoad();
            this.m_fra.add(this.m_view);
	    //Frame Config
            this.m_fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.m_fra.setVisible(true);
            this.m_fra.addKeyListener(m_view);
	}
}

public class test
{
	public static void main(String[] args) 
	{
            new sshToolInterface();
	}
}
