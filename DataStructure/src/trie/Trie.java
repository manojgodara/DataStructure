package trie;


class TrieNode{
	int value;
	TrieNode[] childern = new TrieNode[26]; 
	
	public TrieNode(){
		for(int i = 0; i <26; i++)
			this.childern[i] = null;
	}
}

class Trie_t{
	TrieNode root;
	int count;
	
	public Trie_t(){
		this.root = new TrieNode();
		this.count = 0;
	}
}


public class Trie {
	

	static void insertWord(Trie_t trie, char[] word, int i, int n){

		trie.count++;
		
		TrieNode pCrawl = trie.root;
		int index;
		for(int level = 0; level< word.length; level++){
			
			index = word[level] - 'a';
			if(pCrawl.childern[index] == null){
				pCrawl.childern[index] = new TrieNode();
			}
			
			pCrawl = pCrawl.childern[index];
			
		}
		
		pCrawl.value = trie.count;
		
		/*if(trie.root == null){
			trie.root = new TrieNode();
		}
			int index = word[i]-'a';
			if(i == n-1){
				trie.root.value = count;
			}else{
				insertWord(root.childern[index], word, i+1, n);
			}*/
	}
	
	static int searchWord(Trie_t trie, char[] word){
		
		
		TrieNode pCrawl = trie.root;
		int index;
		for(int level = 0; level<word.length; level++){
			
			index = word[level]-'a';
			if(pCrawl.childern[index] == null){
				return 0;
			}
			
			pCrawl = pCrawl.childern[index];
		}
		
		return (pCrawl.value);
		
		/*if(root == null) return 0;
		
		System.out.println(word[i]);
		if(i == n-1) return root.value;
		 
		int index = word[i]-'a';
		return searchWord(root.childern[index], word, i+1, n);*/
	}

	private static boolean isFreeNode(TrieNode tnode){
		
		for(int i = 0; i< 26; i++)
			if(tnode.childern[i] != null)
				return false;
		return true;
	}
	
	private static boolean isLeafNode(TrieNode node){
		
		if(node.value != 0) return true;
		return false;
	}
	
	private static boolean deleteHelper(TrieNode tnode, char[] word, int i, int n){
		
		if(tnode != null){
			if(i == n){
				
				if(tnode.value != 0){
					tnode.value = 0;
					
					if(isFreeNode(tnode)){
						return true;
					}
					return false;
				}
			}else{
				int index = word[i] - 'a';
				if(deleteHelper(tnode.childern[index], word, i+1, n)){
					
					tnode.childern[index] = null; // free the node
					
					return !isLeafNode(tnode) && isFreeNode(tnode);
						
				}
			}
			
		}
		return false;
	}
	
	static boolean deleteWord(Trie_t trie, char[] word){
		
		int len = word.length;
		if(len>0){
			
			TrieNode tnode = trie.root;
			deleteHelper(tnode, word, 0, len);
			return true;
		}
		return false;
	}
	
	
	public static void main(String args[]){
		
		String[] key ={"she", "sells", "sea", "shore", "the", "by", "sheer"};// {"the", "there"};//{"the", "a", "there", "answer", "any", "by", "bye", "their"};
		Trie_t trie = new Trie_t();
		
		
		for(int i = 0; i< key.length; i++ ){
			char[] ch = key[i].toCharArray();
			insertWord(trie, ch, 0, ch.length);
		}
		
		char[] word = "she".toCharArray();
			System.out.println(searchWord(trie, word));
			
		if(deleteWord(trie, word))
			System.out.println(searchWord(trie, word)+" Deleted.");
		else
			System.out.println("Word is not exist.");
		
		char[] word1 = "sheer".toCharArray();
		System.out.println(searchWord(trie, word1));
	}
	
}
