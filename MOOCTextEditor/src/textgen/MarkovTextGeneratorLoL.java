package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		String[] words = sourceText.split("[ ]+");
		starter = words[0];
		String prevWord = starter;
		int wordStored = 0;
		for (int i=1; i<words.length; i++) { // loop over all the text word from the 2nd
			for (ListNode node: wordList) {
				if(node.getWord().equals(prevWord)) {
					node.addNextWord(words[i]);
					wordStored = 1;
				}
			}
			if(wordStored ==0) {
				ListNode newOne = new ListNode(prevWord);
				newOne.addNextWord(words[i]);
				wordList.add(newOne);
			}	
			prevWord = words[i];
			wordStored = 0;
		}
		//store starter as the next for the last word
		String lastWord = words[words.length-1];
		for (ListNode node: wordList) {
			if(node.getWord().equals(lastWord)) {
				node.addNextWord(starter);
				wordStored = 1;
			}
		}
		if(wordStored ==0) {
			ListNode newOne = new ListNode(lastWord);
			newOne.addNextWord(starter);
			wordList.add(newOne);
		}	
		wordStored = 0;
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if(wordList ==null) {
			throw new NullPointerException("sss");
		}
		else if(numWords ==0) {return "";}
		String currWord = starter;
		String output = "";
		output+=currWord;
		int numOfWords = 1;
		String w="";
		//System.out.println(wordList);
		
		while(numOfWords < numWords) {
			for(ListNode node: wordList) {
				if((node.getWord()).equals(currWord)) {
					w =node.getRandomNextWord(rnGenerator);
					//System.out.print(numOfWords+ " " + w+ " nodeWord&currWord: "+node.getWord()+currWord+") ");
					
				}
			}
			output+=" " + w;
			currWord = w;	
			numOfWords++;
			//System.out.print(numOfWords+ "\t");
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList.clear();
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		
		MarkovTextGeneratorLoL gen1 = new MarkovTextGeneratorLoL(new Random());
		String s = gen1.generateText(20);
		
		String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
        gen1.retrain(input);
        System.out.println(gen1);
        String res = gen1.generateText(500);

        String[] words = res.split("[\\s]+");
        System.out.println("word Count: "+ words.length);
		
		// feed the generator a fixed random value for repeatable behavior
		/*MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int upperLimit = nextWords.size();
		int randomInt = generator.nextInt(upperLimit);
	    return nextWords.get(randomInt);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


