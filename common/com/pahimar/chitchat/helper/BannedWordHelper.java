package com.pahimar.chitchat.helper;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.pahimar.chitchat.blacklist.BannedWord;
import com.pahimar.chitchat.lib.Strings;

public class BannedWordHelper {
    
    private static String regexSpecialCharacters = "\\^$.|?*+()[{";

    public static boolean findBannedWords(String line) {

        return true;
    }
    
    public static Pattern generatePatternFromBannedWord(BannedWord bannedWord) {
        
        if (bannedWord != null && bannedWord.getBannedText() != null) {
            
            String bannedWordString = bannedWord.getBannedText();
            bannedWordString = bannedWordString.toLowerCase();
            bannedWordString = StringUtils.normalizeSpace(bannedWordString);
            
            if (bannedWordString != null && bannedWordString.length() > 0) {
                StringBuilder regexStringBuilder = new StringBuilder();
                
                // Does the banned word have to start with the banned text?
                if (bannedWord.mustStartWithBannedText()) {
                    regexStringBuilder.append(Strings.REGEX_WORD_DELIMITER);
                }
                
                /*
                 * For each character in the string, build a group to match the character
                 */
                for (int i = 0; i < bannedWordString.length(); i++) {
                    
                    String character = String.valueOf(bannedWordString.charAt(i));
                    
                    // If the character is a whitespace character add the whitespace regex matching character
                    if (character.equals(" ")) {
                        regexStringBuilder.append("(\\s)");
                    }
                    // Else if the character is a regex meta-character, escape the character and add it with a one or more modifier
                    else if (regexSpecialCharacters.contains(character)) {
                        regexStringBuilder.append(String.format("(\\%s+)", character));
                    }
                    // Else the character is a character that doesn't require special escapes, so add it with a one or more modifier
                    else {
                        regexStringBuilder.append(String.format("(%s+)", character));
                    }
                }
                
                // Does the banned word have to end with the banned text?
                if (bannedWord.mustEndWithBannedText()) {
                    regexStringBuilder.append(Strings.REGEX_WORD_DELIMITER);
                }
                
                return Pattern.compile(regexStringBuilder.toString());
            }
        }
        
        return null;
    }
}
