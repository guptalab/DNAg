/*
 * Permutation.java
 *For the DNAg Tool : as a Submission of BTECH. Project for Btech. ICT Degree, 2013.
 *Submitted to Prof. Manish.K.Gupta.
 *
 *
 *	Created in April,2013
 *
 * @Authors Sonam Jain (200901118)and Priyanka Shukla (200901102)
*/  
  
//package DNAg;
import java.util.*;
public class Permutation {
	private String a;
	private int n;
	public Permutation(String a, int n) {
		this.a = a;
		this.n = n;
	}
	public List<String> getVariations() {
		int permutations = (int) Math.pow(4, n);
		char[][] table = new char[permutations][n];
		
		//fill
		for (int x = 0; x < n; x++) {
    int t2 = (int) Math.pow(4, x);
    for (int p1 = 0; p1 < permutations;) {
    	
    	
        for (int al = 0; al < 4; al++) {
        	
            for (int p2 = 0; p2 < t2; p2++) {
                table[p1][x] = a.charAt(al);
                p1++;
                
            }           
          
        }
         
      
  }
}
List<String> result = new ArrayList<String>();
for (char[] permutation : table) {
    result.add(new String(permutation));
}
return result;
	}

}

