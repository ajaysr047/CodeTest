class Program
 {
 	public static boolean isEven(int n)
 	{
 /* Write your code here. Do not modify this function definition */ 
		return n%2 == 0;
	} 
}
class ProgramRunner2018202002
 {
 	public static void main(String args[])throws Exception
 	{
 		Program obj = new Program();
		if(obj.isEven(16))
			System.out.println("Main 1 passed!");
		else
			System.out.println("Main 1 failed!");
		if(!obj.isEven(7))
			System.out.println("Main 2 passed!");
		else
			System.out.println("Main 2 failed!");
 	}
 }