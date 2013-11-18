package com.pahimar.chitchat.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.pahimar.chitchat.blacklist.BannedWord;
import com.pahimar.chitchat.blacklist.BannedWordRegistry;
import com.pahimar.chitchat.chat.message.CensoredChatMessage;
import com.pahimar.chitchat.configuration.Settings;
import com.pahimar.chitchat.lib.Reference;
import com.pahimar.chitchat.lib.Strings;

public class BannedWordHelper {
    
    private static Gson gson = new Gson();
    
    public static String censorPartialMessage(String message, boolean isServer) {
        
        if (isServer) {
            
            return "";
        }
        else {

            return new ChatMessageComponent().toJson();
        }
    }
    
    public static String censorEntireMessage(String message, boolean isServer) {
        
        if (isServer) {
            
            return Settings.CENSOR_REPLACEMENT_TEXT;
        }
        else {
            
            CensoredChatMessage censoredChatMessage = gson.fromJson(message, CensoredChatMessage.class);

            String[] chatMessageBody = new String[2];
            chatMessageBody[0] = censoredChatMessage.using[0];
            chatMessageBody[1] = StatCollector.translateToLocal(Strings.CENSOR_REPLACEMENT_TEXT);

            censoredChatMessage.translate = Reference.CHAT_TEXT_MESSAGE_TYPE;
            censoredChatMessage.using = chatMessageBody;

            return ChatMessageComponent.createFromJson(gson.toJson(censoredChatMessage)).toJson();
        }
    }
    
    public static boolean checkForBannedWords(String line) {

        line = line.toLowerCase();
        line = StringUtils.normalizeSpace(line);
        
        for (String bannedWordKey : BannedWordRegistry.getBannedWordMap().keySet()) {
            
            Pattern bannedPattern = BannedWordRegistry.getBannedWordMap().get(bannedWordKey).getPattern();

            if (bannedPattern != null) {
                Matcher matcher = bannedPattern.matcher(line);
                
                if (matcher.find()) {
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
                
                if (matcher.find()) {
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
                    regexStringBuilder.append("(?:\\b|^)");
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
                    else if (Strings.REGEX_SPECIAL_CHARACTERS.contains(character)) {
                        regexStringBuilder.append(String.format("(\\%s+)", character));
                    }
                    // Else the character is a character that doesn't require special escapes, so add it with a one or more modifier
                    else {
                        if (Reference.getEquivalentCharacterMap().get(character) != null) {
                            
                            regexStringBuilder.append(String.format("(%s+", character));
                            
                            List<String> otherCharacters = Reference.getEquivalentCharacterMap().get(character);
                            
                            for (String otherCharacter : otherCharacters) {
                                if (Strings.REGEX_SPECIAL_CHARACTERS.contains(otherCharacter)) {
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
                    regexStringBuilder.append("(?:\\b|$)");
                }
                
                return Pattern.compile(regexStringBuilder.toString());
            }
        }
        
        return null;
    }
}
