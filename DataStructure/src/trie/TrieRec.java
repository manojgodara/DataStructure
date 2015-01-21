package trie;


interface TrieStructure{
	public boolean search(String word);
	public void insert(String word);
}


class TNode implements TrieStructure{
	
	private TNode[] childern;
	private boolean flag = false;
	
	public TNode(){
		childern = new TNode[26];
	}
	
	public void insert(String str){
		
		if(str.length() == 0) {
			this.flag = true;
			return;
		}
		
		int index = str.charAt(0) - 'a';
		
		if(childern[index] == null){
			childern[index] = new TNode();
		}
		
		childern[index].insert(str.substring(1));
	}
	
	
	public boolean search(String word){
		if(word.length() == 0)
			return this.flag;
				
		int index = word.charAt(0) - 'a';
		
		return this.childern[index].search(word.substring(1));
	}
	
}

public class TrieRec{

	
	public static void main(String args[]){
		
		TrieStructure t = new TNode();
		t.insert("the");
		t.insert("there");
		
		System.out.println(t.search("th"));
	}

}
