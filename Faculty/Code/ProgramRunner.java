class Program
 {
 	public static boolean isEven(int n)
 	{
 /* Write your code here. Do not modify this function definition */ 
		return true;
	} 
}
class ProgramRunner
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