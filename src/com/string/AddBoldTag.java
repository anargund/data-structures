package com.string;

/**
 * #616 Add Bold Tag in String
 * https://leetcode.com/problems/add-bold-tag-in-string/
 * T : O(n*k)
 * n : length of string
 * k : total length of words in dict
 *
 */
public class AddBoldTag {

    public static void main(String[] args) {
        AddBoldTag tag = new AddBoldTag();
        String[] dict1 = {"abc","123"};
        System.out.println(tag.addBoldTag("abcxyz123", dict1));
        String[] dict2 = {"aaa","aab","bc"};
        System.out.println(tag.addBoldTag("aaabbcc", dict2));
        System.out.println(tag.addBoldTag("ab", dict1));
    }

    public String addBoldTag(String s, String[] dict) {
        //create extra boolean array to keep track of bold characters
        boolean[] mark = new boolean[s.length()];
        //iterate over string
        for(int i = 0, end = 0 ; i < s.length() ; i ++) {
            //check if any word in dict starts from current index in a string
            for(String word : dict) {
                if(s.startsWith(word, i)) {
                    //if yes, calculate end which is max of current end and length of current
                    //word in the dictionary
                    end = Math.max(end, i + word.length());
                }
            }
            //mark it true if current index is less that end
            mark[i] = end > i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i++) {
            //add string as is to the result
            //if mark[i] is true
            if(!mark[i]) {
                sb.append(s.charAt(i));
                continue;
            }
            //find end index of bold
            int j = i;
            while(j < s.length() && mark[j]) {
                j++;
            }
            //append it to the result
            sb.append("<b>");
            sb.append(s.substring(i,j));
            sb.append("</b>");
            //note, when we did j++, we made 1 extra move
            //so set i = j - 1
            i = j-1;
        }
        return sb.toString();
    }
}
