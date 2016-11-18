package objetodevalor;

import java.util.regex.Pattern;

public enum Regex {
    REGEX_DELIMITA_LINHA_SOLUZINA ("\\t[\\s[0-9]*[a-zA-Z]*]*]*]*.soluziona[.[0-9]*[a-zA-Z]*[\\_\\(\\:\\s]*]*]*[\\)]"),
    REGEX_DELIMITA_LINHA_WAITING ("\\-\\swaiting[[\\s]*[0-9]*[a-zA-Z]*[\\_\\(\\:\\s\\<\\>\\.\\$]*]*[\\)]"),
    REGEX_DELIMITA_LINHA_LOCKED ("\\-\\slocked[[\\s]*[0-9]*[a-zA-Z]*[\\_\\(\\:\\s\\<\\>\\.\\$\\[\\]]*]*[\\)]"),
    REGEX_DELIMITA_NID ("\\snid=0x[[0-9]*[a-fA-F]*]*\\s");

    private final Pattern pattern;

    Regex(final String regex){
        this.pattern = Pattern.compile(regex);
    }

    public Pattern getPattern(){
        return this.pattern;
    }


}
