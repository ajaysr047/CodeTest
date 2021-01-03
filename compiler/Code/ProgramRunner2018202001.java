class Program
 {
 	public static char print(String s)
 	{
 /* Write your code here. Do not modify this function definition */ 
		
		return s.charAt(0);
	} 
}
class ProgramRunner2018202001
 {
 	public static void main(String args[])throws Exception
 	{
 		Program obj = new Program();
		char s = obj.print("hey");
		if(s == 'h')
			System.out.println("Main test  one passed!");
		else
			System.out.println("Main test one failed!");
		
		s = obj.print("They");
		if(s == 'T')
			System.out.println("Main test  two passed!");
		else
			System.out.println("Main test two failed!");
 	}
 }