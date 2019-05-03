package com.backtracking;

import java.util.*;

/**
 * 425. Word Squares
 * https://leetcode.com/problems/word-squares/
 */
public class WordSquares {

    public static void main(String[] args) {
        WordSquares ws = new WordSquares();
        String[] words = {"area","lead","wall","lady","ball"};
        String[] words1 = {"buff","ulus","buns","rump","cuts","stum","murk","wuss","putt","pubs","bust","chub","burp","bubs","suns","puns","buhr","ughs","mums","cunt","bhut","guff","pung","phut","flux","snub","ruts","vugg","turd","hung","tups","xyst","puny","curr","curf","typy","busk","byrl","cusp","pups","pulp","duns","dunk","tugs","dull","bury","murr","slum","mumm","jugs","burn","purl","curl","runt","spry","typp","fugu","dunt","mump","cuds","juju","sudd","nuts","culm","dumb","gyps","buzz","surf","putz","tung","tuns","puds","urns","tuck","duct","hugs","jump","bums","lulu","myth","rynd","undy","hunh","gulf","guts","lutz","burl","lump","dung","gull","gush","bunk","tusk","dups","stub","gust","curs","juts","swum","luff","subs","psst","syph","junk","funs","flub","hurt","burg","muck","buts","furl","such","mull","huff","chug","kuru","dubs","guls","drum","bunt","blub","rhus","buss","hump","rust","stud","fund","cubs","plum","punk","brut","cuff","sugh","wyns","pugh","cuss","buhl","hulk","burd","lurk","hymn","shun","yurt","puts","scum","luny","muns","lung","glug","hunk","guru","cyst","sump","slut","bull","gnus","thud","spur","cups","hunt","busy","yups","durr","turf","guck","full","sulk","purr","smut","curn","butt","suqs","duly","fuds","curb","chum","husk","upby","crud","grum","mugg","scut","thru","vugs","urbs","pump","bulb","smug","kudu","sync","punt","gyms","ruly","frug","crus","tuft","must","bund","ruff","rugs","push","mush","drys","rung","slub","bulk","dust","puck","shut","yuch","gunk","shul","tuts","purs","luck","whys","surd","rubs","dump","numb","thus","buck","duff","duds","gums","muds","sums","hull","thug","bung","musk","lynx","dusk","huts","puff","bump","wych","fugs","runs","jury","tump","bumf","huns","lunk","guys","sunk","lull","buds","glut","bush","knur","pull","sung","muff","funk","plug","ugly","snug","bugs","rush","lush","wynd","furs","curd","suds","hurl","muss","umps","slug","rums","urds","bunn","slur","blur","fuzz","stun","luvs","pugs","yuck","suss","scry","turk","mutt","muts","glum","sulu","mugs","grub","much","plus","buys","tuff","burs","cull","just","urus","drub","hums","null","fury","tsks","scup","lunt","sunn","tutu","lugs","curt","vugh","sups","nuns","trug","durn","nurd","puss","lums","mumu","nurl","cusk","drug","cult","gulp","club","puls","brrr","scud","huck","fuss","turn","fuck","tubs","ruth","ruby","duty","nubs","cwms","guvs","crux","suck","spud","rusk","dugs","wynn","hubs","futz","tush","rudd","pfft","hush","burr","hyps","ruck","fumy","yuks","duck","lust","guns","spun","fubs","flus"};
        String[] words2 = {"buff","ulus","buns","rump","cuts","stum","murk","wuss","putt","pubs","bust","chub","burp","bubs","suns","puns","buhr","ughs","mums","cunt","bhut","guff","pung","phut","flux","snub","ruts","vugg","turd","hung","tups","xyst","puny","curr","curf","typy","busk","byrl","cusp","pups","pulp","duns","dunk","tugs","dull","bury","murr","slum","mumm","jugs","burn","purl","curl","runt","spry","typp","fugu","dunt","mump","cuds","juju","sudd","nuts","culm","dumb","gyps","buzz","surf","putz","tung","tuns","puds","urns","tuck","duct","hugs","jump","bums","lulu","myth","rynd","undy","hunh","gulf","guts","lutz","burl","lump","dung","gull","gush","bunk","tusk","dups","stub","gust","curs","juts","swum","luff","subs","psst","syph","junk","funs","flub","hurt","burg","muck","buts","furl","such","mull","huff","chug","kuru","dubs","guls","drum","bunt","blub","rhus","buss","hump","rust","stud","fund","cubs","plum","punk","brut","cuff","sugh","wyns","pugh","cuss","buhl","hulk","burd","lurk","hymn","shun","yurt","puts","scum","luny","muns","lung","glug","hunk","guru","cyst","sump","slut","bull","gnus","thud","spur","cups","hunt","busy","yups","durr"};
        long startTime1, startTime2, endTime1, endTime2;
        startTime1 = System.currentTimeMillis();
        System.out.println(ws.wordSquares(words));
        endTime1 = System.currentTimeMillis();
        System.out.println("Executed in " + (endTime1 - startTime1));

        startTime2 = System.currentTimeMillis();
        System.out.println(ws.wordSquaresBruteForce(words));
        endTime2 = System.currentTimeMillis();
        System.out.println("Executed in " + (endTime2 - startTime2));

        startTime1 = System.currentTimeMillis();
        ws.wordSquares(words2);
        endTime1 = System.currentTimeMillis();
        System.out.println("Executed in " + (endTime1 - startTime1));

        startTime2 = System.currentTimeMillis();
        ws.wordSquaresBruteForce(words2);
        endTime2 = System.currentTimeMillis();
        System.out.println("Executed in " + (endTime2 - startTime2));
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        //build prefixes
        Map<String,List<String>> map = prefixes(words);
        //backtrack
        backtrack(ans, new ArrayList<>(), 0 , words[0].length(), map);
        return ans;
    }

    private void backtrack(List<List<String>> ans, List<String> temp, int cnt, int size, Map<String,List<String>> map) {
        //if current counts is same as size, we found out solution
        if(cnt == size) {
            ans.add(new ArrayList(temp));
        } else {
            //build pattern
            StringBuilder sb = new StringBuilder();
            //copy i-th letter from each word from, where i is current cnt
            //since we looking for a word that starts from that
            //refer : https://leetcode.com/problems/word-squares/discuss/91333/Explained.-My-Java-solution-using-Trie-126ms-1616
            for(int i = 0 ; i < cnt; i++) {
                sb.append(temp.get(i).charAt(cnt));
            }
            //get list of words that match current pattern
            List<String> candidates = map.get(sb.toString());
            if(candidates == null) return;
            for(String str : candidates) {
                //add current string to possible solution
                temp.add(str);
                //explore further
                backtrack(ans, temp, cnt+1, size, map);
                //remove last entry and try next string
                temp.remove(temp.size()-1);
            }
        }
    }

    private Map<String,List<String>> prefixes(String[] words) {
        HashMap<String,List<String>> prefixes = new HashMap<>();
        for(String word : words) {
            //-1 because we want to enter empty string as well
            //for each prefix substring from 0 to length, add current word as a value
            for(int j = -1; j < word.length(); j++) {
                List<String> p = prefixes.get(word.substring(0, j+1));
                if(p == null) {
                    p = new ArrayList<String>();
                    prefixes.put(word.substring(0, j+1), p);
                }
                p.add(word);
            }
        }
        return prefixes;
    }

    //################brute force#############################
    public List<List<String>> wordSquaresBruteForce(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        backtrack(ans, new String[words[0].length()], 0 , words);
        return ans;
    }

    private void backtrack(List<List<String>> ans, String[] temp, int cnt, String[] words) {
        // System.out.println("temp:" + Arrays.toString(temp) + " cnt:"+cnt+" start:"+start);
        if(cnt == temp.length) {
            if(isWordSquare(temp)) {
                ans.add(new ArrayList(Arrays.asList(temp)));
            }
        } else {
            for(String word : words) {
                temp[cnt++] = word;
                backtrack(ans, temp, cnt, words);
                cnt--;
            }
        }
    }

    private boolean isWordSquare(String[] temp) {
        for(int r = 0 ; r < temp.length; r++) {
            for(int c = r ; c < temp.length; c++) {
                if(temp[r].charAt(c) != temp[c].charAt(r)) {
                    return false;
                }
            }
        }
        return true;
    }
}
