package com.pahimar.chitchat.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.pahimar.chitchat.blacklist.BannedWord;
import com.pahimar.chitchat.blacklist.BannedWordRegistry;
import com.pahimar.chitchat.lib.Strings;

public class BannedWordHelper {
    
    private static String regexSpecialCharacters = "\\^$.|?*+()[{";

    private static Map<String, List<String>> equivalentCharacters = new HashMap<String, List<String>>();
    
    public static boolean checkForBannedWords(String line) {

        line = line.toLowerCase();
        line = StringUtils.normalizeSpace(line);
        
        for (String bannedWordKey : BannedWordRegistry.getBannedWordMap().keySet()) {
            
            Pattern bannedPattern = BannedWordRegistry.getBannedWordMap().get(bannedWordKey).getPattern();
            
            if (bannedPattern != null) {
                Matcher matcher = bannedPattern.matcher(line);
                
                if (matcher.matches()) {
                    return true;
                }
            }
        }
        
        
        return false;
    }
    
    public static List<String> getBannedWordsUsed(String line) {
        
        List<String> bannedWordsUsed = new ArrayList<String>();
        
        line = line.toLowerCase();
        line = StringUtils.normalizeSpace(line);
        
        for (String bannedWordKey : BannedWordRegistry.getBannedWordMap().keySet()) {
            
            BannedWord bannedWord = BannedWordRegistry.getBannedWordMap().get(bannedWordKey);
            
            if (bannedWord.getPattern() != null) {
                Matcher matcher = bannedWord.getPattern().matcher(line);
                
                if (matcher.matches()) {
                    if (!bannedWordsUsed.contains(bannedWord.getBannedText())) {
                        bannedWordsUsed.add(bannedWord.getBannedText());
                    }
                }
            }
        }
        
        return bannedWordsUsed;
    }
    
    public static Pattern generatePatternFromBannedWord(String bannedWord) {
        return generatePatternFromBannedWord(new BannedWord(bannedWord));
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
                        if (equivalentCharacters.get(character) != null) {
                            
                            regexStringBuilder.append(String.format("(%s+", character));
                            
                            List<String> otherCharacters = equivalentCharacters.get(character);
                            
                            for (String otherCharacter : otherCharacters) {
                                if (regexSpecialCharacters.contains(otherCharacter)) {
                                    regexStringBuilder.append(String.format("|\\%s+", otherCharacter));
                                }
                                else {
                                    regexStringBuilder.append(String.format("|%s+", otherCharacter));
                                }
                            }
                            
                            regexStringBuilder.append(String.format(")", character));
                            
                        }
                        else {
                            regexStringBuilder.append(String.format("(%s+)", character));
                        }
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
    
    // Ugly, but initialize the equivalent characters map
    static {
        equivalentCharacters.put("a", Arrays.asList("@", "4"));
        equivalentCharacters.put("b", Arrays.asList("8"));
        equivalentCharacters.put("c", null);
        equivalentCharacters.put("d", null);
        equivalentCharacters.put("e", Arrays.asList("3"));
        equivalentCharacters.put("f", null);
        equivalentCharacters.put("g", null);
        equivalentCharacters.put("h", null);
        equivalentCharacters.put("i", Arrays.asList("!", "l", "1"));
        equivalentCharacters.put("j", null);
        equivalentCharacters.put("k", null);
        equivalentCharacters.put("l", Arrays.asList("1"));
        equivalentCharacters.put("m", null);
        equivalentCharacters.put("n", null);
        equivalentCharacters.put("o", Arrays.asList("0"));
        equivalentCharacters.put("p", null);
        equivalentCharacters.put("q", null);
        equivalentCharacters.put("r", null);
        equivalentCharacters.put("s", Arrays.asList("5","$", "z"));
        equivalentCharacters.put("t", Arrays.asList("+", "7"));
        equivalentCharacters.put("u", null);
        equivalentCharacters.put("v", null);
        equivalentCharacters.put("w", null);
        equivalentCharacters.put("x", null);
        equivalentCharacters.put("y", null);
        equivalentCharacters.put("z", Arrays.asList("2", "s"));
    }
}
