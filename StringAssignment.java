package iNeuronJavaAtWork;

import java.util.Scanner;

public class StringAssignment {

	public static void main(String[] args) {
		System.out.println("Solution 1 :");
		System.out.println();
		String str=new String();
		Scanner revInp=new Scanner(System.in);
		System.out.println("Enter String to reverse :");
		str=revInp.nextLine();
		String revStr="";
		for(int i=str.length()-1;i>=0;i--)
		{
			revStr=revStr+str.charAt(i);
		}
		System.out.println("Reversed String : "+revStr);
		System.out.println();
		System.out.println("Solution 2 : ");
		System.out.println();
		String str1 = new String();
		Scanner str1Sc = new Scanner(System.in);
		System.out.println("Enter String to reverse characters without position change : ");
		str1 = str1Sc.nextLine();
		String str2="";
		String a[]=str1.split(" ");
		for(int i=0;i<a.length;i++)
		{
			for(int j=a[i].length()-1;j>=0;j--)
			{
				str2=str2+a[i].charAt(j);
			}
			str2=str2+" ";
		}
		System.out.println("Output : "+str2);
		System.out.println();
		System.out.println("Solution 3 : ");
		System.out.println();
		String anSt1 = new String();
		Scanner anInp1 = new Scanner(System.in);
		System.out.println("Enter 1st String to check Anagram or not : ");
		anSt1 = anInp1.nextLine();
		String anSt2 = new String();
		Scanner anInp2 = new Scanner(System.in);
		System.out.println("Enter 2nd String to check Anagram or not : ");
		anSt2 = anInp2.nextLine();
		anSt1=anSt1.toLowerCase();
		anSt2 =anSt2.toLowerCase();
		char an[]=new char[anSt2.length()];
		for(int i=0;i<anSt2.length();i++)
		{
			an[i]=anSt2.charAt(i);
		}
		if(anSt1.length()==anSt2.length())
		{ int check=0;
			for(int i=0;i<anSt1.length();i++)
			{
				for(int j=0;j<anSt2.length();j++)
				{
					if(anSt1.charAt(i)==an[j])
					{
						an[j]=' ';
						check++;
					}
				}
			}
			if(check==anSt2.length())
				System.out.println("Anagram!");
			else
				System.out.println("Not Anagram!");
		}
		else
			System.out.println("Not Anagram!");
		System.out.println();
		System.out.println("Solution 4 : ");
		System.out.println();
		String sp = new String();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String to check Pangram or not : ");
		sp = sc.nextLine();
		sp = sp.toLowerCase();
		int pan = 0;
		for (char i = 'a'; i <= 'z'; i++) {
			for (int j = 0; j < sp.length(); j++) {
				char c = sp.charAt(j);
				if (i == c) {
					pan++;
					break;
				}
			}
		}
		if (pan == 26)
			System.out.println("Pangram!");
		else
			System.out.println("Not Pangram!");
		System.out.println();
		System.out.println("Solution 5 : ");
		System.out.println();
		String rpos = new String();
		Scanner rposScan = new Scanner(System.in);
		System.out.println("Enter String to check repeated characters : ");
		rpos = rposScan.nextLine();
		rpos=rpos.toLowerCase();
		char c[]=new char[rpos.length()];
		
		for (int i = 0; i < rpos.length(); i++) {
			c[i]=rpos.charAt(i);
		}
		int repeat = 1;
		for (int i = 0; i < rpos.length(); i++) {
			for(int j=i+1;j<rpos.length();j++)
			{
				if(c[i]==c[j])
				{
					c[j]=' ';
					repeat++;
				}
			}
			if(repeat!=1 && c[i]!=' ')
			{
				System.out.printf("%d times ->"+c[i]+" are present in the String.\n",repeat	);
				repeat=1;
			}
			else
				repeat=1;
		}
		System.out.println();
		System.out.println("Solution 6 : ");
		System.out.println();
		String strSort=new String();
		Scanner sortInp=new Scanner(System.in);
		System.out.println("Enter String to sort Alphabetically :");
		strSort = sortInp.nextLine();
		strSort=strSort.toLowerCase();
		char a1[]=new char[strSort.length()];
		for(int i=0;i<strSort.length();i++)
		{
			a1[i]=strSort.charAt(i);
		}
		char swap;
		for(int i=0;i<strSort.length();i++)
		{
			for(int j=i+1;j<strSort.length();j++)
			{
				if(a1[i]>a1[j])
				{
				swap=a1[i];
				a1[i]=a1[j];
				a1[j]=swap;
				}
			}
		}
		System.out.println(a1);
		System.out.println();
		System.out.println("Solution 7 : ");
		System.out.println();
		String vcStr=new String();
		Scanner vcSc=new Scanner(System.in);
		System.out.println("Enter String to check no.of vowels & consonants in String :");
		vcStr = vcSc.nextLine();
		vcStr =vcStr.toLowerCase();
		int vowel=0,consonant=0;
		for(int i=0;i<vcStr.length();i++)
		{
			char c1 =vcStr.charAt(i);
			if(c1>='a' && c1<='z')
			{
				if(c1!='a' && c1!='e' && c1!='i' && c1!='o' && c1!='u') 
					consonant++;
				else
					vowel++;
						
			}
				
		}
		System.out.printf("%d vowels and %d consonants present in the given String.\n",vowel,consonant);
		System.out.println();
		System.out.println("Solution 8 :");
		System.out.println();
		String spStr=new String();
		Scanner spInp=new Scanner(System.in);
		System.out.println("Enter String to find no.of special characters :");
		spStr = spInp.nextLine();
		char c2;
		int charCount=0;
		for(int i=0;i<spStr.length();i++)
		{
			c2=spStr.charAt(i);
			if(c2>=65 && c2<=90 ||c2>=97 && c2<=122 || c2>=48 && c2<=57)
				continue;
			else
				charCount++;
		}
		System.out.println(charCount+" special characters present in the given String.");
	}
}
