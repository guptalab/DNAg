  
/*
 * output_jframe.java
 *For the DNAg Tool : as a Submission of BTECH. Project for Btech. ICT Degree, 2013.
 *Submitted to Prof. Manish.K.Gupta.
 *
 *
 *	Created in April,2013
 *
 * @Authors Sonam Jain (200901118)and Priyanka Shukla (200901102)
*/
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.net.*;



public class output_jframe extends JFrame
{
	
/* GUI variables*/
    JButton codebook,rules,dfa,dnastr,previous,refresh,greedy,okayg,stch,okays,nextrule,nextrule_DNA,showDNA,next,done,submit,showstr;
    JButton user_manual,authors,project,done_DNA,submit_DNA,home,codebook_gen,gendfa,nextruleDNA,submitDNA,doneDNA,show_nupack;
    JLabel feedback,stud,stud_1,stud_2,num,consol,Enter_rules,codebook_label,Abs_label,home_label,stud_1s,stud_1r,stud_2r,stud_1d,stud_out,stch_label,greedy_label,dfa1_label,dfa2_label,dfa3_label,stud_2rDNA,stud_1rDNA,stud_outDNA,stud_1dDNA,stud_agnDNA;
    JLabel glink,about_DNAg;
    JTextArea feed,consol_1,consol_stch,name_1r,consol_2;
    JTextField name,name_1,str,names,namer,numr,namerDNA,numrDNA,name_1DNA,strDNA;
    JComboBox options;
    JScrollPane scroll,scroll_1;
    JPanel jp=new JPanel();   	     
    JPanel jpnew=new JPanel();   	     
 	
  
  
/*codebook Variables*/
  	int lo=0;
	String getdisplay[]= new String[25];
	int word_size=0;
	int fl=0;
	int cnt=1;
	int fopen=0;	
    int wordsize=0;
 	String alphabet = "ACGT";
    String message = "";
 	int changepos=0;
 	String temp="";
 	int countgc=0,countat=0;
 	int hd=0;
	int counter=wordsize;
	private String dna;
    int revcntr=0;
    int cntr=0;
	int terminals=3;
	int point=0;
	String input="abccbabaa";
	int no_of_alphabets=0;
	int len=0;
	int lenv=0;
	int no_of_alpha=0;

    JPanel panelBgImg;
    JPanel panelBgImgb;

	String nullstr="";
	String finalstringtoshow="";
	String wc_of_finalstringtoshow="";
	String path="C://DNAg";
	
   public output_jframe()
   {
		setTitle("DNAg");
    	setupGUI();
   }   
   
  
// function for codebook action listener

	public void authoractionlistener()
	{
	try
		{ 
  			Process p=Runtime.getRuntime().exec("cmd /c start firefox Reference.html"); 
		}catch(IOException e1) {System.out.println(e1);}
	}

	public void usermanualactionlistener()
	{
	try
 	{
 		 Process userman=Runtime.getRuntime().exec("cmd /c start firefox user_manual.html"); }
 	 	 catch(Exception e){
 	 }	
	}


 	public void codebookactionlistener()
   	{ 
   		System.out.println("You clicked the codebook button ");
  		greedy.setVisible(true);
		stch.setVisible(true);
		Abs_label.setVisible(false);
		home_label.setVisible(false);
		codebook_label.setVisible(false);
        codebook_gen.setVisible(false);	
  		stud_1.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		num.setVisible(false);
		stud_2.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    okayg.setVisible(false);
	}

//end of codebook action listener button
  
   
// Codebook generation by Stochastic local search algorithm 
// also the action listener of stochastic button

   void stochiasticalgo_codebookgeneration()
   {	
   		String nt=names.getText().toString();
       	int word_size=new Integer(nt);
       	   	
       	if(word_size%2!=0)
		{
			word_size=word_size+1;
		}
		
		Random r=new Random();
		int m1 = r.nextInt(word_size);
		
		String input_string=input_string_gen(word_size);
		
		/* calling function*/
		int free_energy=free_energy_nussinov(input_string);
 	  		  	
  	  	String[] code_words = new String[wordsize];
  	  	String[] codebook = new String[wordsize];	
 	  	
 	  	
 	  	code_words=stringGenerator(input_string);
 	  	code_words=checkHamming(code_words,wordsize);
 	  	
 	  	stud_1.setVisible(false);
		okays.setVisible(false);
		name.setVisible(false);
	 
  		String stchoutput="";
  		for(int i=0;i<revcntr;i++)
     		{
     	 		System.out.println(code_words[i]);        
        		stchoutput=code_words[i]+"\n"+stchoutput;
  			}
  			consol_1.setText(stchoutput);
		
  		/*Setting variables to initial point again*/
	   	wordsize=0;
 		alphabet = "ACGT";
    	message = "";
 		changepos=0;
 		temp="";
 		countgc=0;countat=0;
 		hd=0;
		counter=wordsize;
		revcntr=0;
    	cntr=0;
		terminals=3;
		point=0;
		input="abccbabaa";
		no_of_alphabets=0;
		len=0;
   }
   
   
   //generates random {A,C,G,T} String of wordlength.
   public String input_string_gen(int wordlength)
 	{
 	   wordsize=wordlength;
 	   System.out.println(wordsize);    
       Random messageRandomization = new Random();    
       message="";
       for (int i = 0; i <wordsize; i++)
       { 
            message += alphabet.charAt(messageRandomization.nextInt(alphabet.length())); 
       } 
       checkingGC(message);
       return message;
  	}
 
 //Generates permutation of string after checkign GC Constraint.
   public String[] stringGenerator(String input)
	{
		String[] output = new String[input.length()];
		String tempStr="";
		for(int i=0;i<input.length();i++)
		{
			for(int cntr=0,j=i;cntr<input.length();cntr++,j++)
			{
				if(j>input.length()-1)
					j=0;
				tempStr = tempStr + input.charAt(j);
			}
			output[i] = tempStr;
			tempStr="";
		}
		return output;
	}

	//checks the percentage of {G,C} to be 50%, else regenerates.
   public String checkingGC(String input)
	{
		String codeword=input;
		int length=codeword.length();
		countgc=0;countat=0;
		
		for(int i=0;i<length;i++)
		{
			if('G'==codeword.charAt(i)||'C'==codeword.charAt(i))
			{
				countgc++;
			}
			else
			{
				countat++;
			}
		}

		if(countgc==countat)
		{
			return codeword;
		}
		else
		{
			codeword="";
		    return input_string_gen(length);
		}
	}	

	//checkes that the no. of places at which the two strings are same is more that a given a fixed hammign distance.
   public String[] checkHamming(String[] a, int size)
	{
		cntr=0;
		int hd=wordsize/2;
		int hdcnt=0;
		String[] output=new String[size];
		for(int i=0;i<size-1;i++)
	 	{
	 	for(int j=0;j<size-1;j++)
	 		{
	 			if(a[i].charAt(j)==a[i+1].charAt(j))
	 			{
	 				hdcnt++;
	 			}
	 		}
	 	if(hdcnt>hd)
	 	{
	 		System.out.println("??????????????----------------------------------------");
	 		System.out.println(cntr);
	 		output[cntr]=a[i];
	 		cntr++;
		 }
		
	 }
	 
	 output=checkRevHamming(output,size,cntr);
	 return output;
	}
	
	//checks that the no of places where the reverse of the string and the watson crick complement of the other are same is more than a fixed hamming distance.
   public String[] checkRevHamming(String[] output, int size,int cntr)
	{
		revcntr=0;
		int arrlen=cntr;
		int rhd=4;
		int rev_hdcnt=0;
			
		for(int i=0;i<cntr-1;i++)
	 	{
	 		for(int j=0;j<size;j++)
	 		{
	 			if(output[i].charAt(j)==(complementWC(output[i+1]).charAt(j)))
	 			{
	 				rev_hdcnt++;
	 			}
	 		}
	 		if(rev_hdcnt>rhd)
	 		{
	 			output[revcntr]=output[i];
	 			revcntr++;
		 	}
	 	}
	 
		String [] rev=new String[revcntr];
		for(int i=0;i<revcntr;i++)
	 	{
	 		rev[i]=output[i];
	 	}
	 	System.out.println("output after revhd");
	 	for(int i=0;i<revcntr;i++)
	 	{
	 		System.out.println(rev[i]);
	 	}
	 
	 return rev;
	}
	
	//givens the watson crick complement of the string.
   public String complementWC(String dna){
        String code=dna;
        String dnaWC ="";
        for(int i=0;i<dna.length();i++){
            if(dna.charAt(i) == 'T'){
                dnaWC=dnaWC+'A';
            }
            if(dna.charAt(i) == 'A'){
                dnaWC=dnaWC+'T';
            }
            if(dna.charAt(i) == 'C'){
				dnaWC=dnaWC+'G';
            }
            if(dna.charAt(i) == 'G'){
                dnaWC=dnaWC+'C';
            }   
        }
        return dnaWC;
    }
    
    //after finding the n. of alphabets in the Language, input strign and production rules, it selects that many unique random words from the codeboo.
   public String[] selection(String[] codebook,int alpha)
    {
    
    	String[] codebookfinal= new String[alpha];
    
    	Random r1=new Random();
    	
    	point=0;
    	int[] arr=new int[revcntr-1];
    	for(int i=0;i<alpha;i++)
    	{
    		arr[i]=0;
    	}
    	while(point<alpha)
    	{
    	   int m1 = r1.nextInt(revcntr-1);
    	if(arr[m1]<1)
    	{   
    		codebookfinal[point]=codebook[m1];
    		arr[m1]++;
    	   point++;
    	}
    	}
        return codebookfinal;
   }
   
   //finds the free energy of the codeword. using nussinov Algorithm.    
   public int free_energy_nussinov(String codeword) 
	{
		 len=wordsize;
		 int[][] e=new int[len+1][len+1];
		 int[][] a=new int[len+1][len+1];
			
		for(int i=0;i<len;i++)
		{
			for(int j=0;j<len;j++)
			{
				e[i][j]=1;
				e[i][len]=0;
				e[len][j]=0;
			}
		}
		e[0][0]=0;
	
		for(int i=1;i<len;i++)
		{
			
			e[i][i]=0;
			e[i][i-1]=0;
		}
		
		int j=0;
		
		for(int i=0;i<=len-1;i++)
		{
			char ch=codeword.charAt(i);
			
			if(ch=='A')
			{
				e[0][i+1]=65;
				e[i+1][0]=65;
				
			}
			if(ch=='G')
			{
				
				e[0][i+1]=71;
				e[i+1][0]=71;
				
			}
			if(ch=='C')
			{
				e[0][i+1]=67;
				e[i+1][0]=67;
			}
			if(ch=='T')
			{
				
				e[0][i+1]=84;
				e[i+1][0]=84;
				
		    }
		}
	
		for(int i=0;i<=len;i++)
		{
			for(j=0;j<=len;j++)
			{
				System.out.print("  " +e[i][j]);
			}
			System.out.println("   ");
		}
		
		for(int i=0;i<=len;i++)
		{
			for(j=0;j<=len;j++)
			{
				a[i][j]=0;
				a[i][len]=0;
				a[len][j]=0;
			}
		}
	
		for(int i=1;i<=len;i++)
		{
	       a[0][i]=e[0][i];
	       a[i][0]=e[i][0];
		}	
	
		for(int i=1;i<=len;i++)
		{
			for(j=1;j<=len;j++)
			{
				if (e[i][0]==65 && e[0][j]==84)
				{
					a[i][j]=-1;
				}
				if (e[i][0]==84 && e[0][j]==65)
				{
					a[i][j]=-1;
				}
				if (e[i][0]==67 && e[0][j]==71)
				{
					a[i][j]=-2;
				}
				if (e[i][0]==71 && e[0][j]==67)
				{
					a[i][j]=-2;
				}
						
			}
		}
		int a1,b1=0,c1,d1;
		int k=0,i=0,p=0,cnt1=0,cnt2=0;
		int[] arr1=new int[len];
		int[] arr2=new int[len];
		for(k=1;k<=len;k++)
		{
			j=1;
			
			for(i=k+1;i<=len;i++ )
			{ 
				cnt1=0;cnt2=0;
		
				for(p=j+1;p<=i-1;p++)
				{
					arr1[cnt1]=e[j][p];
					cnt1++;
				}	
				for(p=j+1;p<=i-1;p++)
				{
					arr2[cnt2]=e[p][i];
					cnt2++;
				}
				int min1=minimum(arr1,cnt1);
				int min2=minimum(arr2,cnt2);
				b1=Math.min(min1,min2);
				a1=e[j+1][i-1]+a[j][i];
				System.out.println("a1= "+a1);
				System.out.println("b1= "+b1);
					
				e[j][i]=Math.min(a1,b1);
			j++;
			}
		}
			
		for(i=0;i<=len;i++)
		{
			for(j=0;j<=len;j++)
			{
				System.out.print("  " +e[i][j]);
			}
			System.out.println("   ");
		}
		int min_free_energy=min_energy(e);
		return min_free_energy;
	}

	//finds the minimum value in an array.
   public int minimum(int[] find,int size)
		{
			int min=find[0];
			int i=0;
			while(i<size)
			{
				if(min>find[i])
				{
					min=find[i];
				}
				if(min<=find[i])
				{
					i++;
				}
			}
			
			System.out.println("min is "+min+ "i is "+i);
			return min;
		}
	
	//minimum most energy of the array formed.
   public int min_energy(int[][] e)
	{
		int min_energy=0,j=0;
		for(int i=0;i<len;i++)
		{ 
			j=0;
		 while(j<=len)
			{
				if(e[i][j]<=min_energy)
				{
					min_energy=e[i][j];
					
				}
				j++;
			}
		}
		return min_energy;
	}	
	
	
//end of stchastic local search algorithm
   
   
//Codebook generation by greedy algorithm   
//aldo the action listener of greedy button 

   public void greedyalgo_codebookgeneration()
   {
        String nt=name.getText().toString();
       	int word_size=new Integer(nt);
       	if(word_size%2==1)
       	{
       		word_size=word_size+1;
       	}
       			
       	int no_of_words=(int) Math.pow(4,word_size );
     	final char a[][]=new char[no_of_words][word_size];
		
		/* calling permution class */
		Permutation gen = new Permutation("ACGT",word_size);
		
		
		List<String> v = gen.getVariations();

		for (String s : v) 
		{
			String input=s;
  			int p;
            int cta=0;
            int cgc=0;
       		for(p=0;p<word_size;p++)
        	{    
        		char insert=input.charAt(p);
        							
 				if((insert=='A')||(insert=='T'))
 				cta++;
 				else 
 				cgc++;
 										
 				if(p==word_size-1 && cta==cgc)
 				{
 					fl++;
 					for(p=0;p<word_size;p++)
 						{
 							a[fl][p]=input.charAt(p); 
 						}
 				}
 			}
 		}
 	
 	 // finding hamming distance
 	  	 
 	 int hd=word_size/2;
     for(int i=0;i<fl;i++)
     {
         for(int k=i+1;k<fl;k++)
         {
     		int count=0;
     		for(int j=0;j<word_size;j++)
     		{
     			if(a[i][j]==a[k][j])
     			{
     				count++;
     			}
     		}
     		if(count>hd)
     		{    for(;k<fl;k++)
     		{		for(int j=0;j<word_size;j++)
     				{
     					a[k][j]=a[k+1][j];
     				}
     		}
     			k=i+1;
     			fl--;
     		}
        }
     }
    
	stud_1.setVisible(false);
	okayg.setVisible(false);
	name.setVisible(false);

	String groutput="";
  
    String finalstring[]=new String[fl];
  	for(int i=0;i<fl;i++)
     {
     	 String str="";
         for(int j=0;j<word_size;j++)
         {
     		str=str+a[i][j];
         }
         finalstring[i]=str;
        
        groutput=finalstring[i]+"    "+groutput;
 	 }
	consol_1.setText(groutput);

	jp.setVisible(true);

  /* reinitializing the variable */
  	word_size=0;
  	fl=0;
	groutput="";
  	no_of_words=0;
 }
//end of greedy algorithm

//action listener for Grammar rules button
	public void ruleactionlistener()
   {
   	 cnt=1;
	 System.out.println("You clicked the Grammar rules button");
	boolean flag_1 = false;
	boolean flag_2 = false;

	try
	{
		
		FileWriter cykForm = new FileWriter(path+"//contextfree.txt");
		BufferedWriter out =new BufferedWriter(cykForm);
		out.write("Productions:");
		getdisplay[lo]="Productions:";
		out.close();
		FileWriter chomsky = new FileWriter(path+"//chomsky.txt");
	}
	catch(Exception e)
	{
		System.out.println("error");
	}
		codebook_label.setVisible(false);
    	codebook_gen.setVisible(false);
    	stud_1.setVisible(false);
     	home_label.setVisible(false);
        Abs_label.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		num.setVisible(false);
		stud_2.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		stud_1r.setVisible(true);
		
		numr.setText(Integer.toString(cnt));
		numr.setVisible(true);
		namer.setVisible(true);
		stud_2r.setVisible(true);
		nextrule.setVisible(true);
		name_1.setVisible(true);
	}
    
// action listener for next button in Grammar rules
	public void nextactionlistener()
	{
		namer.setVisible(true);
		stud_2.setVisible(true);
		name_1.setVisible(true);
		nextrule.setVisible(true);
	
   		try
   		{   
   			String filename=path+"//contextfree.txt";
			FileWriter out = new FileWriter(filename,true); 
			out.write("\r\n");
			out.write(namer.getText());
			out.write("->");
			out.write(name_1.getText());
			System.out.println(name_1.getText());
			String frus=name_1.getText();
			String dollar="$";
			if(name_1.getText().isEmpty())
			{System.out.println("i have $$$$$");
			nullstr=nullstr+namer.getText();
			System.out.println(nullstr);
		}
		getdisplay[lo++] = name.getText() + "->" + name_1.getText();
		out.close();
		}
		catch(Exception e)
		{
			System.out.println("System error");
		}
		System.out.println("You clicked the Next rule button");
		cnt++;
		numr.setText(Integer.toString(cnt));
		numr.setVisible(true);
		namer.setText("");
		name_1.setText("");
		done.setVisible(true);
	}
//end of action listener of nextrule button in Grammar rules

//done button action listener in Grammar rules
	public void doneactionlistener()
   {
       	try
       	{   
       		String filename=path+"//contextfree.txt";

			FileWriter out = new FileWriter(filename,true); 
			out.write("\r\n");
			out.write(namer.getText());
			out.write("->");
			out.write(name_1.getText());
			if(name_1.getText()=="null")
			{System.out.println("i have $$$$$");
			nullstr=nullstr+namer.getText();}
			System.out.println("nullstr is" +nullstr);
			out.close();

		}
		catch(Exception e)
		{
			System.out.println("system error");
		}
            	
	System.out.println("You clicked the done button");


	stud_1d.setVisible(true);
	str.setVisible(true);
	stud_1r.setVisible(false);
	stud_out.setVisible(false);
	numr.setVisible(false);
	done.setVisible(false);
	namer.setVisible(false);
	stud_2r.setVisible(false);
	name_1.setVisible(false);
	nextrule.setVisible(false);
	submit.setVisible(true);
}
//end of done button action listener in Grammar rules

	void stringsat()   
{
		System.out.println("I have string");
		try
	{
		
		String filename=path+"//contextfree.txt";
		String chomsky=path +"//chomsky.txt";
		FileWriter out = new FileWriter(filename,true); 
		out.write("\r\nString: \r\n");
		out.write(str.getText());
		out.close();

		System.out.println("------------------- Begin Input -------------------");
        Rule rule = new Rule(filename);
        rule.readInput(true);
        System.out.println("-------------------  End Input  -------------------");
        System.out.println();
                      
         String inputString = "";
         for(int i = 0; i < rule.input.size(); i++)
         {
              inputString += rule.input.get(i);
         }
        
                      
        // temporary output files used during conversion
		int dot = chomsky.lastIndexOf('.');
        if(dot == -1)
        {
             System.out.println("Please rename the output file with a period.\n");
             throw new IOException();
        }
        String xmlOutput = chomsky.substring(0, dot) + ".xml";

        // first need to convert from CYK form to XML form
        ArrayList<IgnoredRule> ignoredRules = FormatConverter.CykToXml(filename, xmlOutput);
                        
        // now convert using stylesheets
        new CfgToCnf().convertGrammar(xmlOutput);

        // finally convert XML form to CYK form
        FormatConverter.XmlToCyk("C://DNAg//output.xml", chomsky, inputString, ignoredRules);
                        
        System.out.println("------------------- Begin Output -------------------");
        rule = new Rule(chomsky);
        rule.readInput(true);
        System.out.println("-------------------  End Output  -------------------");
        System.out.println();
                        
        // print the grammar to the console too??
        System.out.println("The CFG has successfully been converted to CNF!");
        System.out.println("Chomsky normal form saved to \"" + chomsky + "\"!\n");
                
        String ggg=new Cyk(chomsky).checkGrammar();
        stud_1d.setVisible(false);               
        stud_1.setVisible(false);
        
        
        int content;
		File file = new File(path+"//contextfree.txt");
		FileInputStream fis = null;
		String strout="<html><body>";
 
		try {
			fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
 
			
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
				if(content==13)
				{
					System.out.println();
					System.out.println("new line");
				strout= strout+"<br>";
				}
				
				else
				strout=strout+(char) content;
			}
			 strout=strout+"<br><br>"+ggg+"</body></html>";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
      
        System.out.println(strout);
		stud_out.setText(strout);
		stud_out.setVisible(true);
		
 	
		}
        catch(Exception e)
        {
        	System.out.println("system error");
        }
}

	void numbersat()
{
	int content;
	System.out.println("I have number");

		String filenameprev=path+"//contextfree.txt";
		String chomsky = path+"//chomsky.txt";
		
		System.out.println(chomsky);
try{
		FileWriter outp = new FileWriter(filenameprev,true); 
		outp.write("\r\nString: \r\n");
		outp.write(str.getText());
		outp.close();	
	}catch(Exception e){
	}
	
	
	
	File file = new File(path+"//contextfree.txt");
	FileInputStream fis = null;
 	
 	try{FileWriter cykForm = new FileWriter(path+"//contextfreenumber.txt");
	BufferedWriter outcn =new BufferedWriter(cykForm);}
	catch(Exception e){
	}
	try {
			fis = new FileInputStream(file);
    		System.out.println("Total file size to read (in bytes) : "+ fis.available());
 			while ((content = fis.read()) != -1) {
				// convert to char and display it
				
			if(content>=48&&content<=57)//whenever number came
			{
				System.out.println("errror1");
				try{
						System.out.println("errror2");
						String filename=path+"//contextfreenumber.txt";
						FileWriter out = new FileWriter(filename,true); 

						if(content==48)
							out.write("z");
						if(content==49)
							out.write("y");
						if(content==50)
							out.write("x");
						if(content==51)
							out.write("w");
						if(content==52)
							out.write("v");
						if(content==53)
							out.write("u");
						if(content==54)
							out.write("t");
						if(content==55)
							out.write("r");
						if(content==56)
							out.write("p");
						if(content==57)
							out.write("q");
				out.close();
				}
			catch(Exception e)
			{
				System.out.println("errror");
			}
		}
				
		else
		{
		try{
			String filename=path+"//contextfreenumber.txt";
			FileWriter out = new FileWriter(filename,true); 
			out.write((char)content);
		    out.close();
		   }catch(Exception e)
		{
			System.out.println("errror");
		}

	}
	System.out.print(content);
	System.out.println("content");
}
			
			
			
			//end of while
			
			String filename=path+"//contextfreenumber.txt";

		System.out.println("------------------- Begin Input -------------------");
        Rule rule = new Rule(filename);
        rule.readInput(true);
        System.out.println("-------------------  End Input  -------------------");
        System.out.println();
                      
         String inputString = "";
         for(int i = 0; i < rule.input.size(); i++)
         {
              inputString += rule.input.get(i);
         }
        

        // temporary output files used during conversion
		int dot = chomsky.lastIndexOf('.');
        if(dot == -1)
        {
             System.out.println("Please rename the output file with a period.\n");
             throw new IOException();
        }
        String xmlOutput = chomsky.substring(0, dot) + ".xml";

        // first need to convert from CYK form to XML form
        ArrayList<IgnoredRule> ignoredRules = FormatConverter.CykToXml(filename, xmlOutput);
                        
        // now convert using stylesheets
        new CfgToCnf().convertGrammar(xmlOutput);

        // finally convert XML form to CYK form
        FormatConverter.XmlToCyk("C://DNAg//output.xml", chomsky, inputString, ignoredRules);
                        
        System.out.println("------------------- Begin Output -------------------");
        rule = new Rule(chomsky);
        rule.readInput(true);
        System.out.println("-------------------  End Output  -------------------");
        System.out.println();
                        
        // print the grammar to the console too??
        System.out.println("The CFG has successfully been converted to CNF!");
        System.out.println("Chomsky normal form saved to \"" + chomsky + "\"!\n");
                
        String ggg=new Cyk(chomsky).checkGrammar();
        //System.out.println("Sonam here u r??????????????");
        //System.out.println(ggg);
         stud_1d.setVisible(false);               
        stud_1.setVisible(false);
        
        
        
	
	//	String strout="";
		String strout="<html><body>";
 
		try {
		 fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
			
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
				if(content==13)
				{
					
						System.out.println();
					System.out.println("new line");
				strout= strout+"<br>";
				}
				
				else
				strout=strout+(char) content;
			}
			 strout=strout+"<br><br>"+ggg+"</body></html>";
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
        
        
        
        
        
        System.out.println(strout);
		stud_out.setText(strout);
		stud_out.setVisible(true);
		
 	
		}
         catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		
	
	
}
//action listener of submit button in Grammar rule after done


	public void submitactionlistener()
   {

	str.setVisible(false);
	submit.setVisible(false);
	
	String check=str.getText();
	int flag=0;
	
//if check is all string
	for(int i=0;i<check.length();i++)
	{
	
	if(check.charAt(i)>=97&&check.charAt(i)<=122&&flag==0)
	{flag++;
		stringsat();
	}
	if(check.charAt(i)>=47&&check.charAt(i)<=57&&flag==0)
	{
		flag++;
		numbersat();
	}
 }
 }
 // end of action listener of submit button in Grammar rule after done
  
  
//action listener of DNA generation
	public void dfagenrationactionlistener()
 {
 	
 	System.out.println("Just clicked dfa generation button");
 	try
 	{
 		 Process dfagen=Runtime.getRuntime().exec("cmd /c start exorciser.jar"); }
 	 	 catch(Exception e){
 	 }
 }
    	
//end of action listener of DNA generation





	public void ruleDNAactionlistener()
   {
   	 cnt=1;
	 System.out.println("You clicked the Grammar rules button");
	boolean flag_1 = false;
	boolean flag_2 = false;

	try
	{
		FileWriter cykForm = new FileWriter(path+"//contextfree.txt");
		BufferedWriter out =new BufferedWriter(cykForm);
		out.write("Productions:");
		getdisplay[lo]="Productions:";
		out.close();
		FileWriter chomsky = new FileWriter(path+"//chomsky.txt");
	}
	catch(Exception e)
	{
		System.out.println("error");
	}
	codebook_label.setVisible(false);
    codebook_gen.setVisible(false);
    stud_1.setVisible(false);
     home_label.setVisible(false);
            Abs_label.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		num.setVisible(false);
		stud_2.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
//		jp_stch.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		
	numrDNA.setText(Integer.toString(cnt));
	numrDNA.setVisible(true);
	namerDNA.setVisible(true);
	stud_2rDNA.setVisible(true);
		nextruleDNA.setVisible(true);
		name_1DNA.setVisible(true);
	
	
}
   	
	public void nextDNAactionlistener()
	{
		
	
			namerDNA.setVisible(true);
			stud_2rDNA.setVisible(true);
			name_1DNA.setVisible(true);
			nextruleDNA.setVisible(true);
	
   		try
   		{   
   			String filename=path+"//contextfree.txt";

			FileWriter out = new FileWriter(filename,true); 
			out.write("\r\n");
			out.write(namerDNA.getText());
			out.write("->");
			out.write(name_1DNA.getText());
			getdisplay[lo++] = namerDNA.getText() + "->" + name_1DNA.getText();

			out.close();
		}
		catch(Exception e)
		{
			System.out.println("System error");
		}
	System.out.println("You clicked the Next rule button");
	cnt++;
	numrDNA.setText(Integer.toString(cnt));
	numr.setVisible(true);
	namerDNA.setText("");
	name_1DNA.setText("");
	doneDNA.setVisible(true);
}

	public void doneDNAactionlistener()
   {
       	try
       	{   
       		String filename=path+"//contextfree.txt";

			FileWriter out = new FileWriter(filename,true); 
			out.write("\r\n");
			out.write(namerDNA.getText());
			out.write("->");
			out.write(name_1DNA.getText());

			getdisplay[lo++]=namerDNA.getText()+"->"+name_1DNA.getText();
			out.close();

		}
		catch(Exception e)
		{
			System.out.println("system error");
		}
            	
	System.out.println("You clicked the done button");

	stud_1dDNA.setVisible(true);
	strDNA.setVisible(true);
	stud_1rDNA.setVisible(false);
	stud_outDNA.setVisible(false);
	numr.setVisible(false);
	doneDNA.setVisible(false);
	namerDNA.setVisible(false);
	stud_2rDNA.setVisible(false);
	name_1DNA.setVisible(false);
	nextruleDNA.setVisible(false);
	submitDNA.setVisible(true);
	numrDNA.setVisible(false);
}

	void stringsatDNA()   
{
		System.out.println("I have string");
		try
	{
		String filename=path+"//contextfree.txt";
		String chomsky=path+"//chomsky.txt";
		FileWriter out = new FileWriter(filename,true); 
		out.write("\r\nString: \r\n");
		out.write(strDNA.getText());
		out.close();

		System.out.println("------------------- Begin Input -------------------");
        Rule rule = new Rule(filename);
        rule.readInput(true);
        System.out.println("-------------------  End Input  -------------------");
        System.out.println();
                      
         String inputString = "";
         for(int i = 0; i < rule.input.size(); i++)
         {
              inputString += rule.input.get(i);
         }
        
                      
        // temporary output files used during conversion
		int dot = chomsky.lastIndexOf('.');
        if(dot == -1)
        {
             System.out.println("Please rename the output file with a period.\n");
             throw new IOException();
        }
        String xmlOutput = chomsky.substring(0, dot) + ".xml";

        // first need to convert from CYK form to XML form
        ArrayList<IgnoredRule> ignoredRules = FormatConverter.CykToXml(filename, xmlOutput);
                        
        // now convert using stylesheets
        new CfgToCnf().convertGrammar(xmlOutput);

        // finally convert XML form to CYK form
        FormatConverter.XmlToCyk("C://DNAg//output.xml", chomsky, inputString, ignoredRules);
                        
        System.out.println("------------------- Begin Output -------------------");
        rule = new Rule(chomsky);
        rule.readInput(true);
        System.out.println("-------------------  End Output  -------------------");
        System.out.println();
                        
        // print the grammar to the console too??
        System.out.println("The CFG has successfully been converted to CNF!");
        System.out.println("Chomsky normal form saved to \"" + chomsky + "\"!\n");
                
        String ggg=new Cyk(chomsky).checkGrammar();
        stud_1d.setVisible(false);               
        stud_1.setVisible(false);
        
        
        int content;
		File file = new File(path+"//contextfree.txt");
		FileInputStream fis = null;
	
		String strout="<html><body>";
 
		try {
			fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
 
			
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
				if(content==13)
				{
					System.out.println();
					System.out.println("new line");
				strout= strout+"<br>";
				}
				
				else
				strout=strout+(char) content;
			}
			 strout=strout+"<br><br>"+ggg;
			 
			 
			 
			 if(ggg == "This string is in L(G)")
        {
        	strout=strout+ "</body></html>";
        	stud_outDNA.setText(strout);
        	System.out.println(strout);
			showDNA.setVisible(true);
			stud_outDNA.setVisible(true);
		}
		else
		{
			strout=strout+ ". TRY AGAIN!!!!" + "</body></html>";
			System.out.println(strout);
			stud_outDNA.setText(strout);
			stud_outDNA.setVisible(true);
		}
		     
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
      	
		}
        catch(Exception e)
        {
        	System.out.println("system error");
        }
 }

	void numbersatDNA()
{
	int content;
	System.out.println("I have number");

		String filenameprev=path+"//contextfree.txt";
		String chomsky=path+"//chomsky.txt";
try{
		FileWriter outp = new FileWriter(filenameprev,true); 
		outp.write("\r\nString: \r\n");
		outp.write(strDNA.getText());
		outp.close();	
	}catch(Exception e){
	}
	
	
	
File file = new File(path+"//contextfree.txt");
		FileInputStream fis = null;
 	
 try{FileWriter cykForm = new FileWriter(path+"//contextfreenumber.txt");
BufferedWriter outcn =new BufferedWriter(cykForm);}
catch(Exception e){
}

		try {
			
			
			fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
 
			
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				
				if(content>=48&&content<=57)//whenever number came
				{
					System.out.println("errror1");
					try{
						System.out.println("errror2");
						String filename=path+"//contextfreenumber.txt";
						FileWriter out = new FileWriter(filename,true); 

if(content==48)
out.write("z");
if(content==49)
out.write("y");
if(content==50)
out.write("x");
if(content==51)
out.write("w");
if(content==52)
out.write("v");
if(content==53)
out.write("u");
if(content==54)
out.write("t");
if(content==55)
out.write("r");
if(content==56)
out.write("p");
if(content==57)
out.write("q");

		out.close();
}

catch(Exception e)
{
	System.out.println("errror");
}
				}
				
				else
				{
					try{
					String filename=path+"//contextfreenumber.txt";
						FileWriter out = new FileWriter(filename,true); 
						out.write((char)content);
		out.close();
		}
		catch(Exception e)
{
	System.out.println("errror");
}
				}
		
				System.out.print(content);
				System.out.println("content");
			}
			//end of while
	
		String filename=path+"//contextfreenumber.txt";
		System.out.println("------------------- Begin Input -------------------");
        Rule rule = new Rule(filename);
        rule.readInput(true);
        System.out.println("-------------------  End Input  -------------------");
        System.out.println();
                      
        String inputString = "";
        for(int i = 0; i < rule.input.size(); i++)
        {
             inputString += rule.input.get(i);
        }
                            
        // temporary output files used during conversion
		int dot = chomsky.lastIndexOf('.');
        if(dot == -1)
        {
             System.out.println("Please rename the output file with a period.\n");
             throw new IOException();
        }
        String xmlOutput = chomsky.substring(0, dot) + ".xml";

        // first need to convert from CYK form to XML form
        ArrayList<IgnoredRule> ignoredRules = FormatConverter.CykToXml(filename, xmlOutput);
                        
        // now convert using stylesheets
        new CfgToCnf().convertGrammar(xmlOutput);

        // finally convert XML form to CYK form
        FormatConverter.XmlToCyk("C://DNAg//output.xml", chomsky, inputString, ignoredRules);
                        
        System.out.println("------------------- Begin Output -------------------");
        rule = new Rule(chomsky);
        rule.readInput(true);
        System.out.println("-------------------  End Output  -------------------");
        System.out.println();
                        
        // print the grammar to the console too??
        System.out.println("The CFG has successfully been converted to CNF!");
        System.out.println("Chomsky normal form saved to \"" + chomsky + "\"!\n");
                
        String ggg=new Cyk(chomsky).checkGrammar();
        stud_1dDNA.setVisible(false);               
   
		String strout="<html><body>";
 
		try {
		 fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
			
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
				if(content==13)
				{
					
						System.out.println();
					System.out.println("new line");
				strout= strout+"<br>";
				}
				
				else
				strout=strout+(char) content;
			}
			 strout=strout+"<br><br>"+ggg+"</body></html>";
				 
	if(ggg == "This string is in L(G)")
        {
        	stud_outDNA.setText(strout);
        System.out.println(strout);
		showDNA.setVisible(true);
		stud_outDNA.setVisible(true);
			}
		else
		{
			System.out.println(strout);
			stud_outDNA.setText(strout+ "TRY AGAIN!!!!");
			stud_outDNA.setVisible(true);
		}
	} 
		catch (IOException e) {
			e.printStackTrace();
		} 
      }
         catch (IOException e) {
			e.printStackTrace();
		} 
}

	public void submitDNAactionlistener()
   {

	strDNA.setVisible(false);
	submitDNA.setVisible(false);
	stud_1dDNA.setVisible(false);
	
	String check=strDNA.getText();
	int flag=0;
	
//if check is all string
for(int i=0;i<check.length();i++)
{
	
	if(check.charAt(i)>=97&&check.charAt(i)<=122&&flag==0)
	{flag++;
		stringsatDNA();
	}
	if(check.charAt(i)>=47&&check.charAt(i)<=57&&flag==0)
	{
		flag++;
		numbersatDNA();
	}
 }
 }

  	void showDNAactionlistener(){
   		
   		 stud_outDNA.setVisible(false);
   		showDNA.setVisible(false);
   		showstr.setVisible(true);
		stud_agnDNA.setVisible(true);
   		System.out.println(strDNA.getText());
   		String input=strDNA.getText();
	    finding_no_of_alphabets(input);
   	}
    	
   	public void finding_no_of_alphabets(String inp)
	{
		int lenv=inp.length();
		char[] input=new char[lenv+1];
				
		for(int i=0;i<lenv;i++)
		{
			input[i]=inp.charAt(i);
		}	
		char [] output = new char[lenv];
		output[0]=input[0];
		input[lenv]=input[0];
		boolean flag=true;
		int pos=1;
		// iteration for each element in input
		for (int i=1;i<=lenv;i++)
		{
			for(int j=0;j<i;j++)
			{
				if(input[i]==output[j])
				{
					flag=false;
					break; // exit out of inner loop on first duplicate element
				}
			}
			if(flag)
			{
			
				output[pos++]=input[i];
				
			} else
			flag=true;
		}
		char [] tempOutput = new char[pos];
		// This for loop is required to filter out default values from the output
		for (int i = 0; i <pos; i++) 
		{
			tempOutput[i]=output[i];
		}
		
		for(int i=0;i<pos;i++)
		{
			
			System.out.println(tempOutput[i]);
		}
		System.out.println("no_of_alphabets"+pos);
		codebook_by_stch_algo(pos);
	}

 	public char [] unique_alphabets(String inp)
	{
			int k=0;
	
		char  output[]= new char  [inp.length()];
		output[0]=inp.charAt(0);
		int flag=0;
	for(int i=1;i<inp.length();i++)
	{
		flag=0;
		for(int j=0;j<=k;j++)
		{
			if(output[j]==inp.charAt(i))
			{	flag=0;
				break;
			}
			else
			flag=1;
		}
		if(flag==1)
		{
			//add in the array n do k++
			k++;
			output[k]=inp.charAt(i);
		}
	}
	System.out.println("----IN UNIQUE------");
	for(int i=0;i<=k;i++)
	System.out.println(output[i]);	
	return output;
	}
	
    void	codebook_by_greedy_algo(int noag){
		
	}

	void codebook_by_stch_algo(int noas ){
	
	System.out.println("String : " +strDNA.getText());
	Random ran=new Random();
	word_size=(ran.nextInt(6)+12);
	System.out.println(word_size);
	System.out.println(word_size);
       			if(word_size%2!=0)
		{
			word_size=word_size+1;
		}
	  
 	  String input_string=input_string_gen(word_size);
 	  int free_energy=free_energy_nussinov(input_string);
 	  System.out.println("free energy of input_string is :"+free_energy);
  	  String[] code_words = new String[wordsize];
  	  String[] codebook = new String[wordsize];	
 	  code_words=stringGenerator(input_string);
 	  code_words=checkHamming(code_words,wordsize);
 	  	
 	  	System.out.println("Final codebook :");	
 	  	System.out.println(cntr);	
 	  	for(int l=0;l<revcntr;l++)
		{
			System.out.println(code_words[l]);		
		}
		
		codebook=selection(code_words,noas);
		System.out.println("Selected codebook");
		
		for(int i=0;i<point;i++)
		{
		    System.out.println(codebook[i]);
		}  
		
		System.out.println(strDNA.getText()); 
		 
		 char[] inputstr= new char[noas];
		 int lengthofstring=strDNA.getText().length();
		 String[] inputst= new String[lengthofstring];
		String wholestr=strDNA.getText();
		
		try
	{
		FileWriter output = new FileWriter(path+"//output.txt");
		String fileout=path+"//output.txt";
		FileWriter outr = new FileWriter(fileout,true); 
		System.out.println("naos" +noas);
		 inputstr=unique_alphabets(strDNA.getText());
		 
		 for(int k=0;k<noas;k++)
		 {
		 	outr.write(inputstr[k]);
		 	System.out.println("array");
		 	System.out.println(inputstr[k]);	
		 
		 
		 outr.write( " : ");
		 outr.write(codebook[k]);
		 outr.write("\r\n");
		 }
		 outr.close();
		 }
		 catch(Exception e)
		 {
		 }
		 
		 for(int i=0;i<lengthofstring;i++){
		 for(int j=0;j<noas;j++)
		 {		 	
			if(wholestr.charAt(i)==inputstr[j]){
				finalstringtoshow=finalstringtoshow+codebook[j];
			}
			}
	}
	System.out.println(finalstringtoshow);
	File file = new File(path+"//output.txt");
		FileInputStream fis = null;
	try {
		 fis = new FileInputStream(file);
 			String stroutf="<html><body>";
			System.out.println("Total file size to read (in bytes) : "+ fis.available());
			int contentf;
			while ((contentf = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) contentf);
				if(contentf==13)
				{
					System.out.println();
					System.out.println("new line");
				stroutf= stroutf+"<br>";
				}
				
				else
				stroutf=stroutf+(char) contentf;
			}
			stud_outDNA.setVisible(false);
			stroutf=stroutf+"</body></html>";
			stud_agnDNA.setText(stroutf);
			stud_agnDNA.setVisible(true);
			stud_outDNA.setVisible(false);
	}		
	catch(Exception e){
		System.out.println("error!!!");
	}
	try
{ 
  Process p=Runtime.getRuntime().exec("cmd /c start casperjs test.js " + "" + finalstringtoshow); 
  System.out.println("DNA computation clicked!!!");
} 
catch(IOException e1) {System.out.println(e1);}


wc_of_finalstringtoshow=complementWC(finalstringtoshow);
System.out.println(wc_of_finalstringtoshow);
}

	void showpaperstr(String setoutcm)
{
	
	String setout=setoutcm;
	System.out.println(finalstringtoshow);
	setout=setout+finalstringtoshow.charAt(0);
	
	for(int g=1;g<finalstringtoshow.length();g++)
	setout=setout+"-"+finalstringtoshow.charAt(g);
	setout=setout+"\n";
	for(int g=0;g<finalstringtoshow.length();g++)
	setout=setout+" | "+" ";
	setout=setout+"\n"+wc_of_finalstringtoshow.charAt(0);
	for(int g=1;g<finalstringtoshow.length();g++)
	setout=setout+"-"+wc_of_finalstringtoshow.charAt(g);
	setout=setout+"\n";
	
	for(int p=0;p<strDNA.getText().length();p++)
	{
		setout=setout+"(";
		for(int y=0;y<(word_size/2)-2;y++)
		{setout=setout+"__";}
		setout=setout+strDNA.getText().charAt(p)+"_";
		for(int y=word_size/2;y<(word_size-2);y++)
		{setout=setout+"__";}
		setout=setout+")";
	}
	
	consol_2.setText(setout);
	
	jpnew.setVisible(true);
	show_nupack.setVisible(true);
	setout="";
	finalstringtoshow="";
	wc_of_finalstringtoshow="";
}
    
    void setupGUI()
   {
	   	 Enter_rules=new JLabel();
     	 str=new JTextField();
     	 num=new JLabel();
     	 Abs_label=new JLabel();
     	 nextrule = new JButton();
     	 nextrule_DNA = new JButton();
     	 submit = new JButton();
     	 done_DNA = new JButton();
     	 submit_DNA = new JButton();
     	 done = new JButton();
     	 greedy = new JButton();
     	 okayg = new JButton();
     	 okays = new JButton();
     	 showDNA=new JButton();
     	 stch = new JButton();
     	 stud_1 = new JLabel();
     	 stud_1s = new JLabel();
     	 stud_1r = new JLabel();
     	 stud_2r = new JLabel();
		 stud_2 = new JLabel();
		 name = new JTextField();
		 namer = new JTextField();
		 name_1 = new JTextField();
		 names = new JTextField();
		 str = new JTextField();
		 numr = new JTextField();
		 stud_1d=new JLabel();
		 stud_out=new JLabel();
		 
	     	 
//codebook button     	 
     	codebook = new JButton();
		codebook.setLocation(47,160);
		codebook.setSize(150,50);
		codebook.setText("CodeBook");
		getContentPane().add(codebook);
		codebook.setToolTipText("Shows you the set of string of A,C,G,T following a given set of constraints.. ");
		
		JLabel about_DNAg = new JLabel("<html><u>About DNAg\n</u></html>");
about_DNAg.setLocation(650,545);
about_DNAg.setSize(70,30);
about_DNAg.setForeground(Color.BLUE);
about_DNAg.setVisible(true);
getContentPane().add(about_DNAg);

about_DNAg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
about_DNAg.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent e) {
if (e.getClickCount() > 0) {
if (Desktop.isDesktopSupported()) {
Desktop desktop = Desktop.getDesktop();

new AboutDNAg();;
 
} else {
//do nothing
}
 
}
}
});

//home button
		home = new JButton();
		home.setLocation(327,55);
		home.setSize(100,25);
		home.setText("Home");
		getContentPane().add(home);
	
//home label	
		home_label =new JLabel();
   		home_label.setLocation(251,20);
        home_label.setSize(490,400);
        home_label.setForeground(Color.WHITE);
		home_label.setText("<html><h3>Hello, <br>Welcome to DNAg, your software tool for Learning Concepts of : <br>  1. Coding Theory <br>  2. DNA Computaton <br>  3. Models of Computation. <br><br>Happy Learning.. :)</h3> </html>");
		home_label.setVisible(true);
		getContentPane().add(home_label);
		
//	button inside codebook button
		codebook_gen = new JButton();
		codebook_gen.setLocation(370,420);
		codebook_gen.setSize(200,25);
		codebook_gen.setText("Generate Codebook");
		getContentPane().add(codebook_gen);
		codebook_gen.setVisible(false);
		codebook_gen.setToolTipText("Generates a codebook");
		
//description of codebook in codebook button
		codebook_label = new JLabel();
		codebook_label.setLocation(251,100);
		codebook_label.setSize(490,350);
		codebook_label.setForeground(Color.WHITE);
		codebook_label.setText("<html>DNA Codebook is a set of DNA Strand over the alphabets{A,C,G,T} that satisfy certain combinitorial Constraints .A (single) DNA strand is a sequence of nucleotides, at each position of the sequence which has chemically distinct ends known as the 3’ and 5’ ends . A DNA strand of the length n can be used to represent one of up to 4n possible values .These Strands combine with their Watson Crick Complement{A replaced by T,C by G and viz-a-viz} to form a Double Strand Helical Structure. <br><br>   1. GC Constraint : Fixed percentage of the nucleotides within each word is either G or C<br><br>    2. Hamming Distance Constraint: Satisfied if the number of positions in which two strings x and y  differ is greater than a fixed Hamming Distance d .<br><br>   3. The Reverse Complement Hamming Distance :Satisfied if the number of positions in which two strings x and Watson Crick complement of y, differ is greater than a fixed Hamming Distance d. </html>");
		codebook_label.setVisible(false);
		getContentPane().add(codebook_label);

//greedy algorithm button
		greedy.setLocation(250,250);
		greedy.setSize(180,30);
		greedy.setText("Greedy Algorithm ");
		greedy.setVisible(false);
		getContentPane().add(greedy);
		
		greedy_label = new JLabel();
		greedy_label.setLocation(250,100);
		greedy_label.setSize(500,190);
		greedy_label.setForeground(Color.WHITE);	
		greedy_label.setText("<html><body>Greedy algorithms can be characterized as being 'short sighted', and as 'non-recoverable’. Used for problems which have 'optimal substructure'. It  is  used as a selection algorithm to prioritize options within a search, or branch and bound algorithm. In our codebook we generate a permutation of all the {A,C,G,T} strings of given length (on the fly) and then check for the constraints on each to reduce it to the required length.</body></html>");
		greedy_label.setVisible(false);
		getContentPane().add(greedy_label);
		
//stochastic algorithm button		
		stch.setLocation(250,440);
		stch.setSize(180,30);
		stch.setText("Stochastic Algorithm ");
		stch.setVisible(false);
		getContentPane().add(stch);
		
		

	 	jp	=new JPanel();
		jp.setSize(515,290);
		jp.setLocation(220,150);
		consol_1= new JTextArea("",17,45);
		consol_1.setLineWrap(true);
		scroll =new JScrollPane(consol_1);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jp.add(scroll);
		jp.setVisible(false);
		getContentPane().add(jp);
	
	jpnew	=new JPanel();
	jpnew.setSize(515,290);
	jpnew.setLocation(220,150);
	 consol_2= new JTextArea("",17,45);
	scroll_1 =new JScrollPane(consol_2);
	scroll_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	jpnew.add(scroll_1);
jpnew.setVisible(false);
	getContentPane().add(jpnew);

stch_label = new JLabel();
		stch_label.setLocation(250,265);
		stch_label.setSize(500,190);	
		stch_label.setForeground(Color.WHITE);	
	stch_label.setText("<html><body>The Algorithm used here is a modified Stochastic Local Search Algorithms that  strongly use randomized decisions while searching for solutions to a given problem. The basic algorithm is initialized with a randomly selected DNA String of size n, satisfying the GC and minimum Free Energy constraint. Then a Cyclic Word Set Generated from this string, repeatedly a conflict, that is, a pair of words that violates a constraint, is selected and resolved by modifying one of the respective words. The algorithm terminates if a set of DNA words that satisfies all given constraints is found. The underlying search strategy that is based on a combination of randomized iterative improvement and conflict directed random walk.</body></html>");
stch_label.setVisible(false);
		getContentPane().add(stch_label);
		
//	button for DFA Generation link to exorcise
		dfa = new JButton();
		dfa.setLocation(46,283);
		dfa.setSize(150,50);
		dfa.setText("DFA Generation");
		dfa.setVisible(true);
		dfa.setToolTipText("Link to the 'exorcise' Software for working on Computational Models ");
		getContentPane().add(dfa);
	
		

dfa1_label = new JLabel();
dfa1_label.setLocation(250,85);
dfa1_label.setSize(500,190);
dfa1_label.setForeground(Color.WHITE);	
dfa1_label.setText("<html><body>Exorcise is a Teaching tool. We import this independent tool to our Software to add up to the functionality of  introductory course 'The Models of Computation' where user can test their mastery of basic concepts.</body></html>");
dfa1_label.setVisible(false);
getContentPane().add(dfa1_label);

dfa2_label = new JLabel();
dfa2_label.setLocation(250,145);
dfa2_label.setSize(500,190);
dfa2_label.setForeground(Color.WHITE);	
dfa2_label.setText("<html><body>We in our Software use Codebook and Production rules for DNA Computation. But to understand the Production Rules using the State diagrams, to know how the String can be satisfying the grammar and the implementation of the Algorithms used, we provide you with ‘Exorcise’ .</body></html>" );
dfa2_label.setVisible(false);
getContentPane().add(dfa2_label);

dfa3_label = new JLabel();
dfa3_label.setLocation(250,205);
dfa3_label.setSize(500,190);
dfa3_label.setForeground(Color.WHITE);	
dfa3_label.setText("<html><body><h5>*we do not take credit for the work done in the ‘exorcise’ software, this is just to make the learning experience of the user more interesting. And to give them this one software, with as many functionalities as possible.</h5></body></html>" );
dfa3_label.setVisible(false);
getContentPane().add(dfa3_label);



		gendfa= new JButton();
		gendfa.setLocation(350,355);
		gendfa.setSize(180,30);
		gendfa.setText("DFA Generation");
		gendfa.setVisible(false);
		gendfa.setToolTipText("Link to the 'exorcise' Software for working on Computational Models ");
		getContentPane().add(gendfa);
	
//user manual button gives HTML user manual
		user_manual = new JButton();
		user_manual.setLocation(643,55);
		user_manual.setSize(120,25);
		user_manual.setText("User Manual");
		getContentPane().add(user_manual);

// author button five reference
		authors = new JButton();
		authors.setLocation(537,55);
		authors.setSize(100,25);
		authors.setText("Reference");
		getContentPane().add(authors);
	
//Abstract button gives project description
	    project = new JButton();
		project.setLocation(430,55);
		project.setSize(100,25);
		project.setText("Abstract");
		getContentPane().add(project);
		
// Description of project
		Abs_label.setLocation(251,20);
        Abs_label.setSize(490,600);
        Abs_label.setForeground(Color.WHITE);	
		Abs_label.setText("<html>In this project, our main aim was to design a suitable software that can be used as a tool for teaching purpose as well to generate DNA self assembly structures and DNA sequences which could be further used for experimental purposes. Problem statement addresses the issue of constructing DNA sequences using the computational model. DNA Computation means “computation using DNA”. DNA has extremely dense information storage and parallel computing power, this makes it a unique computational element to study. The tool DNAg focuses on checking the Grammar Rules satisfying the Languages (Context Free Language and Regular Language) and designing DNA codewords to generate thermodynamic-ally stable DNA sequences that would be used to generate this computational model through the DNA Self Assembly</html>");
		Abs_label.setVisible(false);
		getContentPane().add(Abs_label);

//Button for Grammar rules 
		rules = new JButton();
		rules.setLocation(46,220);
		rules.setSize(150,50);
		rules.setText("Grammar Rules");
		rules.setVisible(true);
		getContentPane().add(rules);
		rules.setToolTipText("Check string for the input Production Rules.");


// DNA computation button
		dnastr = new JButton();
		dnastr.setLocation(47,346);
		dnastr.setSize(150,50);
		dnastr.setText("DNA Computation");
		getContentPane().add(dnastr);
		dnastr.setToolTipText("Show Linear as well as Secondary structure of the input string.");
		
	//greedy	
		stud_1.setLocation(250,211);
	stud_1.setSize(615,25);
	stud_1.setForeground(Color.WHITE);	
	stud_1.setText("Enter word size for Greedy Algorithm ");
	stud_1.setVisible(false);
	getContentPane().add(stud_1);
	
	name.setLocation(500,211);
	name.setSize(60,25);
	name.setVisible(false);
	name.setText("");
	getContentPane().add(name);
	
	okayg.setLocation(400,250);
	okayg.setSize(100,30);
	okayg.setText(" okay ");
	okayg.setVisible(false);
	getContentPane().add(okayg);
		
stud_1s.setLocation(250,211);
	stud_1s.setSize(615,25);
	stud_1s.setForeground(Color.WHITE);	
	stud_1s.setText("Enter word size for Stochastic Algorithm ");
	stud_1s.setVisible(false);
	getContentPane().add(stud_1s);
	
	
	names.setLocation(500,211);
	names.setSize(60,25);
	names.setVisible(false);
	names.setText("");
	getContentPane().add(names);
	

okays.setLocation(400,250);
	okays.setSize(100,30);
	okays.setText(" okay ");
	okays.setVisible(false);
	getContentPane().add(okays);

//rules
  stud_1r.setLocation(260,150);
	stud_1r.setSize(615,90);
	stud_1r.setForeground(Color.WHITE);	
	stud_1r.setText("<html><body>Enter Production rules like <br>1. S->AB|BCCcabb <br> 2.A->BAC|abbb<br>3.B->CAC|BbA<br>4.C->AB|aC|c</body</html>");
	stud_1r.setVisible(false);
	getContentPane().add(stud_1r);
	
	numr.setLocation(260,250);
	numr.setSize(20,25);
	System.out.println(cnt);
	numr.setVisible(false);
	getContentPane().add(numr);
	
	namer.setLocation(300,250);
	namer.setSize(60,25);
	namer.setVisible(false);
	namer.setText("");
	getContentPane().add(namer );
	
	stud_2r.setLocation(360,250);
	stud_2r.setSize(60,25);
	stud_2r.setText("  --->");
	stud_2r.setForeground(Color.WHITE);	
	stud_2r.setVisible(false);
	getContentPane().add(stud_2r);
	
	name_1.setLocation(400,250);
	name_1.setSize(60,25);
	name_1.setVisible(false);
	name_1.setText("");
	getContentPane().add(name_1 );
	
	nextrule.setLocation(400,310);
	nextrule.setSize(100,30);
	nextrule.setText("Next rule ");
	nextrule.setVisible(false);
	getContentPane().add(nextrule);
  
  	done.setLocation(520,310);
	done.setSize(100,30);
	done.setText("Done ");
	done.setVisible(false);
	getContentPane().add(done);
	
	stud_1d.setLocation(250,211);
	stud_1d.setSize(615,25);
	stud_1d.setForeground(Color.WHITE);	
	stud_1d.setText("Enter the string : ");
	stud_1d.setVisible(false);
	getContentPane().add(stud_1d);
	
	str.setLocation(400,211);
	str.setSize(60,25);
	str.setVisible(false);
	str.setText("");
	getContentPane().add(str);
		
	submit.setLocation(420,250);
	submit.setSize(100,30);
	submit.setText("Submit ");
	submit.setVisible(false);
	getContentPane().add(submit);
	
	stud_out.setLocation(310,100);
	stud_out.setSize(615,300);
	stud_out.setForeground(Color.WHITE);	
	stud_out.setVisible(false);
	getContentPane().add(stud_out);
		
//FOR DNASTRUCTURE
stud_1rDNA= new JLabel();
	stud_1rDNA.setLocation(260,150);
	stud_1rDNA.setSize(615,90);
	stud_1rDNA.setForeground(Color.WHITE);	
	stud_1rDNA.setText("<html><body>Enter Production rules like <br>1. S->AB|BCCcabb <br> 2.A->BAC|abbb<br>3.B->CAC|BbA<br>4.C->AB|aC|c</body</html>");
	stud_1rDNA.setVisible(false);
	getContentPane().add(stud_1rDNA);
	
	numrDNA= new JTextField();
	numrDNA.setLocation(260,250);
	numrDNA.setSize(20,25);
	System.out.println(cnt);
	numrDNA.setVisible(false);
	getContentPane().add(numrDNA);
		
	namerDNA= new JTextField();
	namerDNA.setLocation(300,250);
	namerDNA.setSize(60,25);
	namerDNA.setVisible(false);
	namerDNA.setText("");
	getContentPane().add(namerDNA );
	
	stud_2rDNA= new JLabel();
	stud_2rDNA.setLocation(360,250);
	stud_2rDNA.setSize(60,25);
	stud_2rDNA.setForeground(Color.WHITE);	
	stud_2rDNA.setText("  --->");
	stud_2rDNA.setVisible(false);
	getContentPane().add(stud_2rDNA);
	
	name_1DNA= new JTextField();
	name_1DNA.setLocation(400,250);
	name_1DNA.setSize(60,25);
	name_1DNA.setVisible(false);
	name_1DNA.setText("");
	getContentPane().add(name_1DNA );
	
	nextruleDNA=new JButton();
	nextruleDNA.setLocation(400,310);
	nextruleDNA.setSize(100,30);
	nextruleDNA.setText("Next rule ");
	nextruleDNA.setVisible(false);
	getContentPane().add(nextruleDNA);
  
  doneDNA=new JButton();
  	doneDNA.setLocation(520,310);
	doneDNA.setSize(100,30);
	doneDNA.setText("Done ");
	doneDNA.setVisible(false);
	getContentPane().add(doneDNA);
	
	stud_1dDNA= new JLabel();
	stud_1dDNA.setLocation(250,211);
	stud_1dDNA.setSize(615,25);
	stud_1dDNA.setForeground(Color.WHITE);	
	stud_1dDNA.setText("Enter the string : ");
	stud_1dDNA.setVisible(false);
	getContentPane().add(stud_1dDNA);
	
	strDNA= new JTextField();
	strDNA.setLocation(400,211);
	strDNA.setSize(60,25);
	strDNA.setVisible(false);
	strDNA.setText("");
	getContentPane().add(strDNA);
	
	submitDNA=new JButton();
	submitDNA.setLocation(420,250);
	submitDNA.setSize(100,30);
	submitDNA.setText("Submit ");
	submitDNA.setVisible(false);
	getContentPane().add(submitDNA);
	
	stud_outDNA= new JLabel();
	stud_outDNA.setLocation(310,100);
	stud_outDNA.setSize(615,300);
	stud_outDNA.setForeground(Color.WHITE);	
	stud_outDNA.setVisible(false);
	getContentPane().add(stud_outDNA);
	
	showDNA=new JButton();
  	showDNA.setLocation(400,340);
	showDNA.setSize(200,30);
	showDNA.setText(" Show Assignment ");
	showDNA.setVisible(false);
	getContentPane().add(showDNA);

	stud_agnDNA= new JLabel();
	stud_agnDNA.setLocation(310,100);
	stud_agnDNA.setSize(615,300);
	stud_agnDNA.setForeground(Color.WHITE);	
	stud_agnDNA.setVisible(false);
	getContentPane().add(stud_agnDNA);

	showstr=new JButton();
  	showstr.setLocation(400,340);
	showstr.setSize(200,30);
	showstr.setText(" Show Structure ");
	showstr.setVisible(false);
	getContentPane().add(showstr);
	
	show_nupack = new JButton();
		show_nupack.setLocation(400,450);
		show_nupack.setSize(200,30);
		show_nupack.setText("Show Secondary Structure");
		getContentPane().add(show_nupack);
		show_nupack.setVisible(false);
		show_nupack.setToolTipText("Shows Secondary Structure using NUPACK");
	
	
	
	
	JLabel glink = new JLabel("<html><u>Feedback\n</u></html>");
glink.setLocation(730,535);
glink.setSize(80,50);
glink.setForeground(Color.blue);
glink.setVisible(true);
getContentPane().add(glink);

glink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
glink.addMouseListener(new MouseAdapter() {
public void mouseClicked(MouseEvent ae) {
if (ae.getClickCount() > 0) {
if (Desktop.isDesktopSupported()) {
Desktop desktop1 = Desktop.getDesktop();
try {
URI uri = new URI("https://docs.google.com/forms/d/1YGu_I9z7Z56oAP1enGByBahqs-ItHbLqnBwCoJouOro/viewform");
desktop1.browse(uri);
} catch (IOException ex) {
// do nothing
} catch (URISyntaxException ex) {
//do nothing
}
} else {
//do nothing
}
 
}
}
});

	authors.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        authoractionlistener();
       	}});
        	
        user_manual.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {
        usermanualactionlistener();
        	}});
	home.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {show_nupack.setVisible(false);
        	jpnew.setVisible(false);
		namerDNA.setVisible(false);
		stud_1rDNA.setVisible(false);
		numrDNA.setVisible(false);
		stud_2rDNA.setVisible(false);
		name_1DNA.setVisible(false);
		nextruleDNA.setVisible(false);
		submitDNA.setVisible(false);        	
    	stud_outDNA.setVisible(false);    	
     	strDNA.setVisible(false);  
     	stud_1dDNA.setVisible(false);
     	doneDNA.setVisible(false);
      		okayg.setVisible(false);
         dfa1_label.setVisible(false);
        dfa2_label.setVisible(false);
        gendfa.setVisible(false);
        dfa3_label.setVisible(false);
        greedy_label.setVisible(false);
        stch_label.setVisible(false);
        stud_out.setVisible(false);
        stud_1s.setVisible(false);
        	stud_1.setVisible(false);
        	stud_1r.setVisible(false);
        	codebook_label.setVisible(false);
            codebook_gen.setVisible(false);
            stud_1.setVisible(false);
            home_label.setVisible(true);
            Abs_label.setVisible(false);
		name.setVisible(false);
		namer.setVisible(false);
		name_1.setVisible(false);
		numr.setVisible(false);
		stud_2r.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		nextrule_DNA.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		names.setVisible(false);
	okays.setVisible(false);
	done.setVisible(false);
	stud_1d.setVisible(false);
	str.setVisible(false);
	submit.setVisible(false);
	showDNA.setVisible(false);      	
	showstr.setVisible(false);
	stud_agnDNA.setVisible(false);
	panelBgImg.setVisible(true);
       panelBgImgb.setVisible(false);
   		}});


project.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e)
        {show_nupack.setVisible(false);
        	jpnew.setVisible(false);
        		namerDNA.setVisible(false);
	stud_1rDNA.setVisible(false);
	numrDNA.setVisible(false);
	stud_2rDNA.setVisible(false);
	name_1DNA.setVisible(false);
	nextruleDNA.setVisible(false);
	submitDNA.setVisible(false);        	
    stud_outDNA.setVisible(false);    	
     strDNA.setVisible(false);  
     stud_1dDNA.setVisible(false);
     doneDNA.setVisible(false);
      	
        showstr.setVisible(false);
	stud_agnDNA.setVisible(false);	
        	stud_1d.setVisible(false);
	str.setVisible(false);
	submit.setVisible(false);
	dfa3_label.setVisible(false);
        	stud_out.setVisible(false);
        	greedy_label.setVisible(false);
        stch_label.setVisible(false);
        	stud_1.setVisible(false);
        stud_1r.setVisible(false);
        namer.setVisible(false);
        stud_1s.setVisible(false);
        	name.setVisible(false);
        	okayg.setVisible(false);
        	codebook_label.setVisible(false);
            codebook_gen.setVisible(false);
            stud_1.setVisible(false);
            home_label.setVisible(false);
            Abs_label.setVisible(true);
		name.setVisible(false);
		name_1.setVisible(false);
		numr.setVisible(false);
		stud_2r.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
		greedy.setVisible(false);
		done.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		nextrule_DNA.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		names.setVisible(false);
	okays.setVisible(false);
	panelBgImg.setVisible(false);
	showDNA.setVisible(false);      	
       panelBgImgb.setVisible(true);
			dfa1_label.setVisible(false);
        dfa2_label.setVisible(false);
        gendfa.setVisible(false);
		}});

codebook.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {show_nupack.setVisible(false);
            	jpnew.setVisible(false);
            		namerDNA.setVisible(false);
	stud_1rDNA.setVisible(false);
	numrDNA.setVisible(false);
	stud_2rDNA.setVisible(false);
	name_1DNA.setVisible(false);
	nextruleDNA.setVisible(false);
	submitDNA.setVisible(false);        	
    stud_outDNA.setVisible(false);    	
     strDNA.setVisible(false);  
     stud_1dDNA.setVisible(false);
     doneDNA.setVisible(false);
      	
          showstr.setVisible(false);
	stud_agnDNA.setVisible(false);  	
            	stud_out.setVisible(false);
            dfa1_label.setVisible(false);
        dfa2_label.setVisible(false);
        gendfa.setVisible(false);
            greedy_label.setVisible(false);
        stch_label.setVisible(false);
            name.setText("");
            names.setText("");
            	stud_1d.setVisible(false);
	str.setVisible(false);
	dfa3_label.setVisible(false);
	submit.setVisible(false);
	stud_1r.setVisible(false);
        namer.setVisible(false);
            	codebook_label.setVisible(true);
            stud_1.setVisible(false);
            	name.setVisible(false);
            codebook_gen.setVisible(true);
            stud_1.setVisible(false);
            home_label.setVisible(false);
            stud_1s.setVisible(false);
             Abs_label.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		okayg.setVisible(false);
		numr.setVisible(false);
		stud_2r.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    done.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
//		jp_stch.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		nextrule_DNA.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		names.setVisible(false);
		showDNA.setVisible(false);      	
	okays.setVisible(false);
	panelBgImg.setVisible(false);
       panelBgImgb.setVisible(true);
            	}});

codebook_gen.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e)
            {show_nupack.setVisible(false);
               greedy_label.setVisible(true);
      			stch_label.setVisible(true);
               codebookactionlistener();
         }
        }); 
    
 greedy.addActionListener(new ActionListener() {
 public void actionPerformed(ActionEvent ae)
            {
      System.out.println("You clicked the greedy button ");
	greedy_label.setVisible(false);
        stch_label.setVisible(false);
	stud_1.setVisible(true);
	greedy.setVisible(false);
	stch.setVisible(false);
		name.setVisible(true);
		okayg.setVisible(true);
	
	panelBgImg.setVisible(false);
       panelBgImgb.setVisible(true);
   }});
   
	okayg.addActionListener(new ActionListener() 
	{
	       public void actionPerformed(ActionEvent ae)
            {
        	     int flag=0;
            	 for(int i=0;i<name.getText().length();i++)
             	 {
             		if(Character.isDigit(name.getText().charAt(i)))
             		{
             			flag=0;
             		}
             		else
             		{
             			flag++;
             		}
             	 }
               	if(name.getText().isEmpty())
            	{
            		flag=-1;
            		JOptionPane.showMessageDialog(null, "PLease Enter an input,Word Size can't be Empty");
   		    		name.setText("");
   		        	}
              	if(flag==0)
              	{
               	if(Integer.parseInt(name.getText())<8&&Integer.parseInt(name.getText())>0)
                	{
                    panelBgImg.setVisible(false);
       				panelBgImgb.setVisible(true);
                   	greedyalgo_codebookgeneration() ;
                	}
                	else
                	{
                		JOptionPane.showMessageDialog(null, "PLease Check the input again {Should be an integer in the range of 1 to 8} ");
   		    			name.setText("");
             	  	}
              	}
                if(flag>0)
                {
                	JOptionPane.showMessageDialog(null, "PLease Check the input ");
   		    		name.setText("");
                } 
        }});
     
stch.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
   greedy_label.setVisible(false);
        stch_label.setVisible(false);
	
	greedy.setVisible(false);
	stch.setVisible(false);
		
	stud_1s.setVisible(true);
	names.setVisible(true);
	okays.setVisible(true);
 }
}); 
 
	okays.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {int flag=0;
             for(int i=0;i<names.getText().length();i++)
             {
             	if(Character.isDigit(names.getText().charAt(i)))
             	{
             		flag=0;
             	}
             	else
             	{
             		flag++;
             	}
             }
             	if(names.getText().isEmpty())
            	{
            		flag=-1;
            		JOptionPane.showMessageDialog(null, "PLease Enter an input,Word Size can't be Empty");
   		    		names.setText("");
   		      	}
              	
              	if(flag==0)
              	{
              
                if(Integer.parseInt(names.getText())>10&&Integer.parseInt(names.getText())<80)
                {panelBgImg.setVisible(false);
       				panelBgImgb.setVisible(true);
                   	stud_1s.setVisible(false);
					names.setVisible(false);
					okays.setVisible(false);

                   stochiasticalgo_codebookgeneration() ;
                }
                else
                {
                	JOptionPane.showMessageDialog(null, "PLease Check the input again {Should be an integer in the range of 10 to 80} ");
   		    		names.setText("");
                }
              	}
              if(flag>0)
              {
                	JOptionPane.showMessageDialog(null, "PLease Check the input ");
   		    		names.setText("");
                } 
  }});        
        
rules.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae)
            {show_nupack.setVisible(false);
            	jpnew.setVisible(false);
            	namer.setText("");
            	name_1.setText("");
            	str.setText("");
            	stud_out.setVisible(false);
            	stud_1.setVisible(false);
            	dfa3_label.setVisible(false);
            	greedy_label.setVisible(false);
        stch_label.setVisible(false);
         dfa1_label.setVisible(false);
        dfa2_label.setVisible(false);
        gendfa.setVisible(false);
           ruleactionlistener();
          showstr.setVisible(false);
	stud_agnDNA.setVisible(false); 
           namerDNA.setVisible(false);
	stud_1rDNA.setVisible(false);
	numrDNA.setVisible(false);
	stud_2rDNA.setVisible(false);
	name_1DNA.setVisible(false);
	nextruleDNA.setVisible(false);
	submitDNA.setVisible(false);        	
    stud_outDNA.setVisible(false);    	
     strDNA.setVisible(false);  
     stud_1dDNA.setVisible(false);
     doneDNA.setVisible(false);
	showDNA.setVisible(false);      	
}});

nextrule.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae)
            {
         if(name_1.getText().isEmpty())
        {
        	System.out.println("there");
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               name_1.setText("");
           }
else     if(namer.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               namer.setText("");
           }
else
nextactionlistener();
}});
	

done.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
      if(namer.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               namer.setText("");
           }
          else
          if(name_1.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               namer.setText("");
           } 
           else     	
    doneactionlistener();
}
});	
	submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae)
            {
            	
            	if(str.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,String can't be Empty");
               str.setText("");
           }
           else
                      submitactionlistener();

}});
// END OF GRAMMAR RULE ACTION LISTENER

dfa.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {show_nupack.setVisible(false);
            jpnew.setVisible(false);
            namerDNA.setVisible(false);
	stud_1rDNA.setVisible(false);
	numrDNA.setVisible(false);
	stud_2rDNA.setVisible(false);
	name_1DNA.setVisible(false);
	nextruleDNA.setVisible(false);
	submitDNA.setVisible(false);        	
    stud_outDNA.setVisible(false);    	
     strDNA.setVisible(false);  
     stud_1dDNA.setVisible(false);
     doneDNA.setVisible(false);
            
             stud_out.setVisible(false);
            showstr.setVisible(false);
	stud_agnDNA.setVisible(false);
            greedy_label.setVisible(false);
        stch_label.setVisible(false);
            ;
            	stud_1d.setVisible(false);
	str.setVisible(false);
	submit.setVisible(false);
	stud_1r.setVisible(false);
        namer.setVisible(false);
            	codebook_label.setVisible(false);
            stud_1.setVisible(false);
            	name.setVisible(false);
            codebook_gen.setVisible(true);
            stud_1.setVisible(false);
            home_label.setVisible(false);
            stud_1s.setVisible(false);
             Abs_label.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		okayg.setVisible(false);
		numr.setVisible(false);
		stud_2r.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    done.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
//		jp_stch.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		nextrule_DNA.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		names.setVisible(false);
	okays.setVisible(false);
	panelBgImg.setVisible(false);
       panelBgImgb.setVisible(true);
       codebook_gen.setVisible(false);
                dfa1_label.setVisible(true);
        dfa2_label.setVisible(true);
        gendfa.setVisible(true);
               
     dfa3_label.setVisible(true);
    showDNA.setVisible(false);      	
            }
        }); 
        
gendfa.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e)
            {
     
     dfagenrationactionlistener();}});
     
//generating structure
   dnastr.addActionListener(new ActionListener() {		
 
            public void actionPerformed(ActionEvent ae)
            {
            	show_nupack.setVisible(false);
            jpnew.setVisible(false);
            	namerDNA.setText("");
            	name_1DNA.setText("");
            	strDNA.setText("");
            	        
          
            stud_out.setVisible(false);
            
            greedy_label.setVisible(false);
        stch_label.setVisible(false);
            ;showstr.setVisible(false);
	stud_agnDNA.setVisible(false);
            	stud_1d.setVisible(false);
	str.setVisible(false);
	submit.setVisible(false);
	stud_1r.setVisible(false);
        namer.setVisible(false);
            	codebook_label.setVisible(false);
            stud_1.setVisible(false);
            	name.setVisible(false);
            codebook_gen.setVisible(true);
            stud_1.setVisible(false);
            home_label.setVisible(false);
            stud_1s.setVisible(false);
             Abs_label.setVisible(false);
		name.setVisible(false);
		name_1.setVisible(false);
		okayg.setVisible(false);
		numr.setVisible(false);
		stud_2r.setVisible(false);
		nextrule.setVisible(false);
		done.setVisible(false);
		submit.setVisible(false);
	    okays.setVisible(false);
	    done.setVisible(false);
	    okayg.setVisible(false);
		jp.setVisible(false);
		greedy.setVisible(false);
		stch.setVisible(false);
		str.setVisible(false);
		nextrule_DNA.setVisible(false);
		done_DNA.setVisible(false);
		submit_DNA.setVisible(false);
		names.setVisible(false);
	okays.setVisible(false);
	panelBgImg.setVisible(false);
       panelBgImgb.setVisible(true);
       codebook_gen.setVisible(false);
                dfa1_label.setVisible(false);
        dfa2_label.setVisible(false);
        gendfa.setVisible(false);
               
     dfa3_label.setVisible(false);
           
           
            	namerDNA.setVisible(true);
	stud_1rDNA.setVisible(true);
	numrDNA.setVisible(true);
	stud_2rDNA.setVisible(true);
	name_1DNA.setVisible(true);
	nextruleDNA.setVisible(true);
	submitDNA.setVisible(false);        	
    stud_outDNA.setVisible(false);    	
     strDNA.setVisible(false);  
     stud_1dDNA.setVisible(false);
     doneDNA.setVisible(false);
            	
            
      showDNA.setVisible(false);      	
ruleDNAactionlistener();
}});    
       
nextruleDNA.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae)
            {
 if(name_1DNA.getText().isEmpty())
        {
        	System.out.println("there");
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               name_1DNA.setText("");
           }


else     if(namerDNA.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               namerDNA.setText("");
           }
else
nextDNAactionlistener();
}});
	

doneDNA.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
            	
            	
            	if(name_1DNA.getText().isEmpty())
        {
        	System.out.println("there");
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               name_1DNA.setText("");
           }


else     if(namerDNA.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,Production Rules can't be Empty");
               namerDNA.setText("");
           }
else
            	
    doneDNAactionlistener();
	
}
});	
	

submitDNA.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
            	
            	            	
            	if(strDNA.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "PLease Enter an input,String can't be Empty");
               strDNA.setText("");
           }
           else
            	
 submitDNAactionlistener();
	
}
});	

showDNA.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
       stud_outDNA.setVisible(false);
   		showDNA.setVisible(false);
   		showstr.setVisible(true);
	stud_agnDNA.setVisible(true);     	
    showDNAactionlistener();
	
}
});

show_nupack.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
try{            Process show_structure=Runtime.getRuntime().exec("cmd /c start weather1.png");	
    }catch(Exception e)        	{
    	System.out.println("error");
    }
            	}});
            	showstr.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent ae)
            {
            	showstr.setVisible(false);
            	String setout="";
            	showpaperstr(setout);
            	}}
            );
	 
        ImageIcon imh = new ImageIcon(path+"DNAg_Background.jpg");
        
        
        panelBgImg = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image img = new ImageIcon(path+"//DNAg_Background.jpg").getImage();
                Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        
                setLayout(null);
                g.drawImage(img, 0, 0, null);
            } 
        };
        
        getContentPane().add(panelBgImg);
        panelBgImg.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
        
        
        //blur image
        ImageIcon imhb = new ImageIcon(path+"//DNAg_Background.jpg");
        
        
        panelBgImgb = new JPanel()
        {
            public void paintComponent(Graphics g) 
            {
                Image imgb = new ImageIcon(path+"//DNAg_Background.jpg").getImage();
                Dimension size = new Dimension(imgb.getWidth(null), imgb.getHeight(null));
        
                setLayout(null);
                g.drawImage(imgb, 0, 0, null);
            } 
        };
        
        getContentPane().add(panelBgImgb);
        panelBgImg.setBounds(0, 0, imh.getIconWidth(), imh.getIconHeight());
       
        
        getContentPane().setLayout(null);

       panelBgImg.setSize(809,669);
       panelBgImgb.setSize(809,669);
       setSize(800,600);
       setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       panelBgImg.setVisible(true);
       panelBgImgb.setVisible(false);
       setVisible(true);
      
  	
   }
    
}  