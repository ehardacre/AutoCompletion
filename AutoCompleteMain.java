package autocomplete;

import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AutoCompleteMain {

	public static void main(String[] args){
		ArrayList<Term> allTerms = new ArrayList<Term>();
		int shown = Integer.parseInt(args[0]);
		String fileName = args[1];
		Scanner scn;
		try {
			//Scan the file and load all of the terms
			scn = new Scanner(new File(fileName));
			while(scn.hasNext()){
				String lng = scn.next();
				String rest = scn.nextLine().trim();
				Term trm = new Term(rest, Long.parseLong(lng));
				allTerms.add(trm);
			}
			//loop to prompt the user at the end of each round
			while(true){
				//find the matches and format them printing out
				Autocomplete aC = new Autocomplete(allTerms);
				Scanner input = new Scanner(System.in);
				System.out.print("Enter a new prefix: ");
				String prefix = input.nextLine();
				List<Term> matches = aC.allMatches(prefix);
				System.out.println("There are "+matches.size()+" matches.");
				if(matches.size() < shown){
					System.out.println("The matching items are:");
					for(Term match : matches){
						System.out.println(match);
					}
				}else{
					System.out.println("The "+shown+" largest are:");
					for(int i = 0; i < shown; i++){
						System.out.println(matches.get(i));
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
